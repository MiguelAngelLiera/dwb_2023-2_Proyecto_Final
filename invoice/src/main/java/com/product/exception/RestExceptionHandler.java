package com.product.exception;

import java.time.LocalDateTime;

import javax.servlet.Servlet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ExceptionResponse> handleApiException(ApiException exception, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setTiemstamp(LocalDateTime.now());
        response.setStatus(exception.getStatus().value());
        response.setError(exception.getStatus());
        response.setMensaje(exception.getMessage());
        response.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());

        return new ResponseEntity<ExceptionResponse>(response, response.getError());
    }
}
