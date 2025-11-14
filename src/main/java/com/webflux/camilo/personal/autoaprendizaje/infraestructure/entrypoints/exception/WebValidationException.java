package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.exception;

public class WebValidationException extends RuntimeException {

    public WebValidationException(String message) {
        super(message);
    }
}