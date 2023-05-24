package com.invoice.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoItem;

@Service
public class SvcInvoiceImp implements SvcInvoice {

	@Autowired
	RepoInvoice repo;
	
	@Autowired
	RepoItem repoItem;

	@Override
	public List<Invoice> getInvoices(String rfc) {
		return repo.findByRfcAndStatus(rfc, 1);
	}

	@Override
	public List<Item> getInvoiceItems(Integer invoice_id) {
		return repoItem.getInvoiceItems(invoice_id);
	}

	@Override
	public ApiResponse generateInvoice(String rfc) {
		/*
		 * Requerimiento 5
		 * Implementar el método para generar una factura 
		 */
		List<Cart> car = svcCart.getCart(rfc);
		if (car == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "cart has no items");

		Invoice invoice = new Invoice();
		invoice.setRfc(rfc);
		invoice.setStatus(1);
		invoice.setSubtotal(0.0);
		invoice.setTotal(0.0);
		invoice.setTaxes(0.0);
		invoice.setCreated_at(LocalDateTime.now());
		repo.save(invoice);

		invoice = repo.findByRfcAndTotal(rfc, 0.0);

		double totalAux = 0.0;
		double taxesAux = 0.0;
		List<DtoProduct> dtos = new ArrayList<DtoProduct>();

		for (Cart item : car) {

			ResponseEntity<DtoProduct> response = productCl.getProduct(item.getGtin());

			int quantity = item.getQuantity();
			double price = response.getBody().getPrice();
			double total = quantity * price;
			totalAux += total;
			double taxes = total * 0.16;
			taxesAux += taxes;

			Item newItem = new Item();
			newItem.setId_invoice(invoice.getInvoice_id());
			newItem.setGtin(item.getGtin());
			newItem.setQuantity(quantity);
			newItem.setUnit_price(price);
			newItem.setTaxes(taxes);
			newItem.setSubtotal(total - taxes);
			newItem.setTotal(total);
			newItem.setStatus(1);
			repoItem.save(newItem);

			DtoProduct dto = new DtoProduct();
			dto.setGtin(item.getGtin());
			dto.setStock(quantity);
			dto.setPrice(price);
			dtos.add(dto);
		}

		invoice.setTotal(totalAux);
		invoice.setTaxes(taxesAux);
		invoice.setSubtotal(totalAux - taxesAux);
		invoice.setCreated_at(LocalDateTime.now());
		repo.save(invoice);

		productCl.updateProductStock(dtos);
		svcCart.clearCart(rfc);
		
		return new ApiResponse("invoice generated");
	}

}
