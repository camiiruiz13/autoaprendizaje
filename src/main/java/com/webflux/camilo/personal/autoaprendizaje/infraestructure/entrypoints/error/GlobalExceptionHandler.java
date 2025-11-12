package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.error;

import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
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
        model.addAttribute(ALERT_TYPE, ALERT_WARNING);
        model.addAttribute(ALERT_MESSAGE, ex.getMessage());
        return Mono.just(Rendering.view("productos").modelAttribute(ALERT_MESSAGE, ex.getMessage()).build());
    }

    @ExceptionHandler(TechnicalException.class)
    public Mono<Rendering> handleTechnicalException(TechnicalException ex, Model model) {
        log.error("Error técnico: {}", ex.getMessage(), ex);
        model.addAttribute(ALERT_TYPE, ALERT_DANGER);
        model.addAttribute(ALERT_MESSAGE, "Error interno del servidor. Intenta nuevamente.");
        return Mono.just(Rendering.view("productos").modelAttribute(ALERT_MESSAGE, ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public Mono<Rendering> handleGenericException(Exception ex, Model model) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        model.addAttribute(ALERT_TYPE, ALERT_DANGER);
        model.addAttribute(ALERT_MESSAGE, "Ocurrió un error inesperado.");
        return Mono.just(Rendering.view("productos").modelAttribute(ALERT_MESSAGE, ex.getMessage()).build());
    }

    @ExceptionHandler(DomainException.class)
    public Mono<Rendering> handleDomainException(DomainException ex) {
        log.warn("Error de dominio [{}]: {}", ex.getCode(), ex.getMessage());
        return Mono.just(
                Rendering.view("productos")
                        .modelAttribute("alertType", "warning")
                        .modelAttribute("alertMessage", ex.getMessage())
                        .build()
        );
    }

}
