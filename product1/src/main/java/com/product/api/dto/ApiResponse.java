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

    @Override
  	public int hashCode() {
  		return Objects.hash(message);
  	}

  	@Override
  	public boolean equals(Object obj) {
  		if (this == obj)
  			return true;
  		if (obj == null)
  			return false;
  		if (getClass() != obj.getClass())
  			return false;
  		ApiResponse other = (ApiResponse) obj;
  		return Objects.equals(message, other.message);
  	}

  	@Override
  	public String toString() {
  		return "ApiResponse [message=" + message + "]";
  	}
}
