package com.customer.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;
import com.customer.api.service.SvcCustomerImage;
import com.customer.exception.ApiException;
/**
 * Esta clase es el controlador para las operaciones relacionadas con la imagen del cliente.
 */
@RestController
@RequestMapping("/customer-image")
public class CtrlCustomerImage {

	@Autowired
	SvcCustomerImage svc;
	
	/**
     * Establece la imagen de un cliente.
     *
     * @param in             El objeto CustomerImage que contiene la imagen a establecer.
     * @param bindingResult  El resultado de la validación del objeto CustomerImage.
     * @return Una respuesta con el objeto ApiResponse y el estado HTTP OK si se establece correctamente la imagen.
     * @throws ApiException Si hay errores de validación, se lanza una ApiException con el mensaje de error.
     */
	@PutMapping
	public ResponseEntity<ApiResponse> setCustomerImage(@Valid @RequestBody CustomerImage in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<ApiResponse>(svc.setCustomerImage(in), HttpStatus.OK);
	}
}
