package com.webflux.camilo.personal.autoaprendizaje.domain.usecase;

import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.ProductGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class SaveProductoUseCase {

    private final ProductGateway productGateway;

    public Mono<Producto> saveProducto(Producto producto){

        return productGateway.saveProducto(producto)
                .doOnSubscribe(sub -> log.info("Iniciando guardado de producto..."))
                .doOnSuccess(p -> log.info("Producto guardado correctamente: {}", p.getIdProducto()))
                .onErrorMap(TechnicalException.class, ex ->
                        new DomainException(ex.getCode(), ex.getMessage())
                )
                .doOnError(err -> log.error("Error en SaveProductoUseCase: {}", err.getMessage(), err));
    }
}
