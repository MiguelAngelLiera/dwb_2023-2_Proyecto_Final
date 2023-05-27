package com.customer.api.dto;

/**
 * Esta clase representa la respuesta de una API.
 */
public class ApiResponse {
	/* Mensaje */
	private String message;

	 /**
     * Crea una nueva instancia de ApiResponse con el mensaje especificado.
     *
     * @param message El mensaje de la respuesta.
     */
	public ApiResponse(String message) {
		super();
		this.message = message;
	}

	 /**
     * Obtiene el mensaje de la respuesta.
     *
     * @return El mensaje de la respuesta.
     */
	public String getMessage() {
		return message;
	}

	 /**
     * Establece el mensaje de la respuesta.
     *
     * @param message El mensaje de la respuesta.
     */
	public void setMessage(String message) {
		this.message = message;
	}
}