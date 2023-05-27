package com.customer.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase encargada de manejar las excepciones en la API y generar respuestas adecuadas.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * Maneja las excepciones de tipo ApiException y genera una respuesta adecuada.
	 *
	 * @param exception La excepción ApiException lanzada
	 * @param request La solicitud web actual
	 * @return La respuesta de excepción generada
	 */
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ExceptionResponse> handleApiException(ApiException exception, WebRequest request){
		ExceptionResponse response = new ExceptionResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(exception.getStatus().value());
		response.setError(exception.getStatus());
		response.setMessage(exception.getMessage());
		response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
		
		return new ResponseEntity<ExceptionResponse>(response, response.getError());
	}
}
