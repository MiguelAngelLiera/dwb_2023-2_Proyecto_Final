package com.invoice.api.dto;

/*
 * Clase para representar la respuesta de la API.
 */
public class ApiResponse {

	/* Mensaje */
	private String message;
	
	/* Constructor */
	public ApiResponse() {
	}

	/* Constructor con el mensaje */
	public ApiResponse(String message) {
		super();
		this.message = message;
	}

	/*
	 * Develvomos el mensaje.
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * Establecemos el mensaje.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
