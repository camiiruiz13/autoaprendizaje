package com.webflux.camilo.personal.autoaprendizaje.domain.exception;

import lombok.Getter;



@Getter
public class TechnicalException extends RuntimeException {

    private final String code;



    public TechnicalException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
