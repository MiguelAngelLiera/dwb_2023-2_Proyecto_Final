package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh-mm-ss")
    private LocalDateTime tiemstamp;
    private Integer status;
    private HttpStatus error;
    private String mensaje;
    private String path;

    public ExceptionResponse(){

    }

    public LocalDateTime getTiemstamp() {
        return this.tiemstamp;
    }

    public void setTiemstamp(LocalDateTime tiemstamp) {
        this.tiemstamp = tiemstamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public HttpStatus getError() {
        return this.error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
