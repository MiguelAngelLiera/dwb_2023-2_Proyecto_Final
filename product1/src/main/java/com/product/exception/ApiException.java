package com.product.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public ApiException(HttpStatus status, String mensaje){
        super(mensaje);
        this.status = status;
    }
    
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
