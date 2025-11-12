package com.webflux.camilo.personal.autoaprendizaje.domain.usecase;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.ProductGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class FindAllUseCase {

    private final ProductGateway productGateway;

    public Flux<Producto> findAll(){
        return productGateway.findAll()
                .doOnSubscribe(sub -> log.info("Iniciando proceso de listado de productos..."))
                .switchIfEmpty(Mono.error(new DomainException(ErrorMessage.NO_PRODUCTS_FOUND.getCode(),
                        ErrorMessage.NO_PRODUCTS_FOUND.getMessage())))
                .doOnComplete(() -> log.info("Listado de productos completado exitosamente"))
                .onErrorMap(TechnicalException.class, ex ->
                        new DomainException(ex.getCode(), ex.getMessage())
                )
                .doOnError(error -> log.error("Error en ListAllProductosUseCase: {}", error.getMessage(), error));
    }
}
