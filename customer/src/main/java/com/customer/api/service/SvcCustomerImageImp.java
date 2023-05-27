package com.customer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;
import com.customer.api.repository.RepoCustomerImage;
import com.customer.exception.ApiException;

@Service
public class SvcCustomerImageImp implements SvcCustomerImage {

	@Autowired
	RepoCustomerImage repo;
	
	/**
     * Establece la imagen de un cliente.
     *
     * @param in El objeto CustomerImage que contiene la imagen del cliente a establecer.
     * @return Un objeto ApiResponse que indica el resultado de la operación de establecimiento de imagen.
     * @throws ApiException si la imagen del cliente no existe.
     */
	@Override
	public ApiResponse setCustomerImage(CustomerImage in) {
		Integer updated = repo.setCustomerImage(in.getCustomer_image_id(), in.getCustomer_image());
		if(updated == 0) 
			throw new ApiException(HttpStatus.NOT_FOUND, "customer image does not exist");
		return new ApiResponse("customer image updated");
	}

}
