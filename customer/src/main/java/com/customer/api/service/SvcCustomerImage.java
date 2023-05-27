package com.customer.api.service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;

public interface SvcCustomerImage {

	 /**
     * Establece la imagen de un cliente.
     *
     * @param in El objeto CustomerImage que contiene la imagen del cliente a establecer.
     * @return Un objeto ApiResponse que indica el resultado de la operación de establecimiento de imagen.
     */
	ApiResponse setCustomerImage(CustomerImage in);
}
