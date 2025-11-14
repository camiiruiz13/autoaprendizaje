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
public class FindByIdUseCase {

    private final ProductGateway productGateway;

    public Mono<Producto> findById(String idProducto) {
        return productGateway.findById(idProducto)
                .switchIfEmpty(Mono.error(new DomainException( ErrorMessage.PRODUCT_NOT_FOUND.getCode(),
                        ErrorMessage.PRODUCT_NOT_FOUND.getMessage())))
                .doOnSubscribe(sub -> log.info("Iniciando búsqueda de producto ID={}", idProducto))
                .doOnSuccess(prod -> log.info("Producto encontrado ID={}", idProducto))
                .onErrorMap(TechnicalException.class, ex -> new DomainException(ex.getCode(), ex.getMessage()))
                .doOnError(err -> log.error("Error al buscar producto ID={}: {}", idProducto, err.getMessage()));
    }
}
