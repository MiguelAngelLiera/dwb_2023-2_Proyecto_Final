package com.invoice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.service.SvcInvoice;

/**
 * Esta clase es un controlador de la API REST para operaciones relacionadas con las facturas.
 */
@RestController
@RequestMapping("/invoice")
public class CtrlInvoice {

	@Autowired
	SvcInvoice svcInvoice;
	
	 /**
     * Obtiene las facturas para el RFC especificado.
     *
     * @param rfc El RFC del cliente.
     * @return ResponseEntity con una lista de objetos Invoice y el estado HTTP 200 (OK).
     */
	@GetMapping("/{rfc}")
	public ResponseEntity<List<Invoice>> getInvoices(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcInvoice.getInvoices(rfc), HttpStatus.OK);
	}
	
	/**
     * Obtiene los elementos de una factura específica.
     *
     * @param id El ID de la factura.
     * @return ResponseEntity con una lista de objetos Item y el estado HTTP 200 (OK).
     */
	@GetMapping("/{id}/item")
	public ResponseEntity<List<Item>> getInvoiceItems(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svcInvoice.getInvoiceItems(id), HttpStatus.OK);
	}
	
	 /**
     * Genera una nueva factura para el RFC especificado.
     *
     * @param rfc El RFC del cliente.
     * @return ResponseEntity con un objeto ApiResponse y el estado HTTP 201 (CREATED).
     */
	@PostMapping("/{rfc}")
	public ResponseEntity<ApiResponse> generateInvoice(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcInvoice.generateInvoice(rfc),HttpStatus.CREATED);
	}
}
