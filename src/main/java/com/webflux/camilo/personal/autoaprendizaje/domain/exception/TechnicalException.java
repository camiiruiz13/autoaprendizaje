package com.webflux.camilo.personal.autoaprendizaje.domain.exception;

import lombok.Getter;

import static com.webflux.camilo.personal.autoaprendizaje.domain.exception.ExceptionConstants.TECHNICAL_ERROR;

@Getter
public class TechnicalException extends RuntimeException {

    private final String code;

    public TechnicalException(String message) {
        super(message);
        this.code = TECHNICAL_ERROR;
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
        this.code = TECHNICAL_ERROR;
    }

    public TechnicalException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
