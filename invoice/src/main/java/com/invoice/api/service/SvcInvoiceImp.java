package com.invoice.api.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoProduct;
import com.invoice.api.entity.Cart;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoItem;
import com.invoice.exception.ApiException;

/*
 * Service Invoice Implementation.
 */
@Service
public class SvcInvoiceImp implements SvcInvoice {

	/* El repositorio */
	@Autowired
	RepoInvoice repo;
	
	/* Elemento del repositorio */
	@Autowired
	RepoItem repoItem;

	/* Obtenemos las facturas mediante un rfc. */
	@Override
	public List<Invoice> getInvoices(String rfc) {
		return repo.findByRfcAndStatus(rfc, 1);
	}

	/* Obtenemos los artículos de una factura en específico. */
	@Override
	public List<Item> getInvoiceItems(Integer invoice_id) {
		return repoItem.getInvoiceItems(invoice_id);
	}

	/**
	 * Implementación del punto 5 del proyecto.
	 * Dado un RFC consultamos el carrito de compras asociado a 
	 * dicho cliente, sí éste cliente no tiene elementos en su carrito
	 * envíamos el status NOT FOUND con el mensaje: "cart has no items."
	 * @param rfc el RFC de cliente.
	 * @return La respuesta de la API.
	 */
	@Override
	public ApiResponse generateInvoice(String rfc) {
		List<Cart> car = svcCart.getCart(rfc);
		if (car.isEmpty() || car == null) {
			throw new ApiException(HttpStatus.NOT_FOUND, "cart has no items");	
		}

		Invoice invoice = new Invoice();
		invoice.setRfc(rfc);
		invoice.setStatus(1);
		invoice.setSubtotal(0.0);
		invoice.setTotal(0.0);
		invoice.setTaxes(0.0);
		invoice.setCreated_at(LocalDateTime.now());
		repo.save(invoice);

		invoice = repo.findByRfcAndTotal(rfc, 0.0);

		/* Total de todos los productos (con impuestos incluidos). */
		double totalAux = 0.0;
		/* Total de todos los impuestos de los productos. */
		double taxesAux = 0.0;
		/* El subtotal de todos los productos. */
		double subtotal = 0.0;
		List<DtoProduct> dtos = new ArrayList<DtoProduct>();

		for (Cart item : car) {

			ResponseEntity<DtoProduct> response = productCl.getProduct(item.getGtin());

			/* Precio Unitario */
			double price = response.getBody().getPrice();
			int quantity = item.getQuantity();
			double totalWithTaxes = price * quantity;
			totalAux += totalWithTaxes;
			double taxes = totalWithTaxes * 0.16;
			taxesAux += taxes;
			subtotal += totalWithTaxes - taxes; 

			Item newItem = new Item();
			newItem.setId_invoice(invoice.getInvoice_id());
			newItem.setGtin(item.getGtin());
			newItem.setQuantity(quantity);
			newItem.setUnit_price(price);
			newItem.setTaxes(taxes);
			newItem.setSubtotal(totalWithTaxes - taxes);
			newItem.setTotal(totalWithTaxes);
			newItem.setStatus(1);
			repoItem.save(newItem);

			DtoProduct dto = new DtoProduct();
			dto.setGtin(item.getGtin());
			dto.setStock(quantity);
			dto.setPrice(price);
			dtos.add(dto);
		}

		/* Factura */
		invoice.setTotal(totalAux);
		invoice.setTaxes(taxesAux);
		invoice.setSubtotal(subtotal);
		invoice.setCreated_at(LocalDateTime.now());
		repo.save(invoice);

		/* Actualización del stock. */
		productCl.updateProductStock(dtos);
		/* Vacíamos el carrito ya que generamos la factura. */
		svcCart.clearCart(rfc);
		
		/* Regresamos como respuesta que la factura fue generada con éxito. */
		return new ApiResponse("invoice generated");
	}

}
