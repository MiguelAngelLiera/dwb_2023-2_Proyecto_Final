package com.product.api.dto;

import com.product.exception.ApiException;

public class ApiResponse{
    
    private String mensaje;

    public ApiResponse(String mensaje){
        super();
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
