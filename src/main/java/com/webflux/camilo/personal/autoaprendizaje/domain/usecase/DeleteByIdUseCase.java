package com.webflux.camilo.personal.autoaprendizaje.domain.usecase;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.ProductGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class DeleteByIdUseCase {

    private final ProductGateway productGateway;

    public Mono<Void> deletedById(String idProducto) {
        return productGateway.findById(idProducto)
                .switchIfEmpty(Mono.error(new DomainException( ErrorMessage.PRODUCT_NOT_FOUND.getCode(),
                        ErrorMessage.PRODUCT_NOT_FOUND.getMessage()))).flatMap(productGateway::delete)
                .doOnSuccess(v -> log.info("UseCase: Producto {} eliminado correctamente", idProducto))
                .doOnError(error -> log.error("UseCase: Error al eliminar {}: {}", idProducto, error.getMessage()))
                .onErrorMap(TechnicalException.class, ex ->
                        new DomainException(ex.getCode(), ex.getMessage())
                );
    }
}
