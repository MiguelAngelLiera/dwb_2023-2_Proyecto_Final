package com.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para manejar errores de la API.
 */
public class ApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	
	/**
	 * Crea una nueva instancia de ApiException con el estado y mensaje especificados.
	 *
	 * @param status El estado HTTP correspondiente al error
	 * @param message El mensaje descriptivo del error
	 */
	public ApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	/**
	 * Obtiene el estado HTTP asociado con la excepción.
	 *
	 * @return El estado HTTP de la excepción
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Establece el estado HTTP de la excepción.
	 *
	 * @param status El estado HTTP a establecer
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}