package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.adapters;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.ProductGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper.ProductoModelMapper;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductoAdapter implements ProductGateway {

    private final ProductoRepository repository;
    private final ProductoModelMapper modelMapper;

    @Override
    public Mono<Producto> saveProducto(Producto producto) {
        return Mono.fromCallable(() -> modelMapper.toDocument(producto))
                .flatMap(repository::save)
                .map(modelMapper::toModel)
                .doOnSuccess(p -> log.info("Producto guardado exitosamente: {}", p.getNombre()))
                .onErrorMap(
                        error -> (error instanceof MappingException || error instanceof NullPointerException),
                        error -> {
                            log.error("Error en el mapeo Producto ↔ Documento: {}", error.getMessage(), error);
                            return new TechnicalException(
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getCode(),
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getMessage(),
                                    error
                            );
                        }
                )
                .onErrorMap(error -> {
                    log.error("Error técnico general al guardar producto: {}", error.getMessage(), error);
                    return new TechnicalException(
                            ErrorMessage.TECHNICAL_ERROR_SAVE_PRODUCTO.getCode(),
                            ErrorMessage.TECHNICAL_ERROR_SAVE_PRODUCTO.getMessage(),
                            error
                    );
                });
    }
}
