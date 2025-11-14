package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.error;

import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.exception.WebValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;

import static com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.constanst.AlertConstants.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public Mono<Rendering> handleDomainException(DomainException ex, Model model) {
        log.warn("Error de dominio: {}", ex.getMessage());

        return Mono.just(
                Rendering.view("productos")
                        .modelAttribute(ALERT_TYPE, ALERT_WARNING)
                        .modelAttribute(ALERT_MESSAGE, ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(TechnicalException.class)
    public Mono<Rendering> handleTechnicalException(TechnicalException ex, Model model) {
        log.error("Error técnico: {}", ex.getMessage(), ex);

        return Mono.just(
                Rendering.view("productos")
                        .modelAttribute(ALERT_TYPE, ALERT_DANGER)
                        .modelAttribute(ALERT_MESSAGE, "Error interno del servidor. Intenta nuevamente.")
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<Rendering> handleGenericException(Exception ex, Model model) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);

        return Mono.just(
                Rendering.view("productos")
                        .modelAttribute(ALERT_TYPE, ALERT_DANGER)
                        .modelAttribute(ALERT_MESSAGE, "Ocurrió un error inesperado.")
                        .build()
        );
    }

    @ExceptionHandler(WebValidationException.class)
    public Mono<Rendering> handleWebValidationException(WebValidationException ex, Model model) {
        log.warn("Error de validación: {}", ex.getMessage());

        return Mono.just(
                Rendering.view("productos")
                        .modelAttribute(ALERT_TYPE, ALERT_WARNING)
                        .modelAttribute(ALERT_MESSAGE, ex.getMessage())
                        .build()
        );
    }
}
