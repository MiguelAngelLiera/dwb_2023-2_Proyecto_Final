package com.customer.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Clase utilizada para representar una respuesta de excepción en la API.
 */
public class ExceptionResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh-mm-ss")
	private LocalDateTime timestamp;
	private Integer status;
	private HttpStatus error;
	private String message;
	private String path;
	
	/**
	 * Crea una nueva instancia de ExceptionResponse.
	 */
	public ExceptionResponse () {
		
	}

	/**
	 * Obtiene la marca de tiempo de la excepción.
	 *
	 * @return La marca de tiempo de la excepción
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Establece la marca de tiempo de la excepción.
	 *
	 * @param timestamp La marca de tiempo a establecer
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Obtiene el estado de la excepción.
	 *
	 * @return El estado de la excepción
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Establece el estado de la excepción.
	 *
	 * @param status El estado a establecer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Obtiene el error de la excepción.
	 *
	 * @return El error de la excepción
	 */
	public HttpStatus getError() {
		return error;
	}

	/**
	 * Establece el error de la excepción.
	 *
	 * @param error El error a establecer
	 */
	public void setError(HttpStatus error) {
		this.error = error;
	}

	/**
	 * Obtiene el mensaje de la excepción.
	 *
	 * @return El mensaje de la excepción
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Establece el mensaje de la excepción.
	 *
	 * @param message El mensaje a establecer
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Obtiene el path de la excepción.
	 *
	 * @return El path de la excepción
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Establece el path de la excepción.
	 *
	 * @param path El path a establecer
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
}
