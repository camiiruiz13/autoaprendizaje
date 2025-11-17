package com.webflux.camilo.personal.autoaprendizaje.domain.usecase;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.CategoriaGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.ProductGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class SaveProductoUseCase {

    private final ProductGateway productGateway;
    private final CategoriaGateway categoriaGateway;

    public Mono<Producto> saveProducto(Producto producto) {


        return categoriaGateway.findById(producto.getIdCategoria())
                .switchIfEmpty(Mono.error(new DomainException(ErrorMessage.CATEGORY_NOT_FOUND.getCode(),
                        ErrorMessage.CATEGORY_NOT_FOUND.getMessage())))
                .flatMap(cat -> {
                    producto.setFechaCreacion(LocalDateTime.now());
                    log.info("Categoría válida: {} ({})", cat.getNombre(), cat.getIdCategoria());
                    return productGateway.saveProducto(producto)
                            .doOnSubscribe(sub -> log.info("Iniciando guardado de producto..."))
                            .doOnSuccess(p -> log.info("Producto guardado correctamente: {}", p.getIdProducto()))
                            .onErrorMap(TechnicalException.class, ex ->
                                    new DomainException(ex.getCode(), ex.getMessage())
                            )
                            .doOnError(err -> log.error("Error en SaveProductoUseCase: {}", err.getMessage(), err));
                });
    }
}