package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.adapters;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.ProductGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper.ProductoModelMapper;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
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

    @Override
    public Flux<Producto> findAll() {
        return repository.findAll()
                .map(modelMapper::toModel)
                .doOnSubscribe(sub -> log.info("Iniciando consulta de todos los productos..."))
                .doOnComplete(() -> log.info("Consulta de productos completada correctamente"))
                .onErrorMap(
                        error -> (error instanceof MappingException || error instanceof NullPointerException),
                        error -> {
                            log.error("Error en el mapeo Producto ↔ Documento al listar: {}", error.getMessage(), error);
                            return new TechnicalException(
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getCode(),
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getMessage(),
                                    error
                            );
                        }
                )
                .onErrorMap(error -> {
                    log.error("Error técnico general al listar productos: {}", error.getMessage(), error);
                    return new TechnicalException(
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getCode(),
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getMessage(),
                            error
                    );
                });
    }

    @Override
    public Mono<Producto> findById(String idProducto) {
        return repository.findById(idProducto)
                .switchIfEmpty(Mono.error(new DomainException(
                        ErrorMessage.PRODUCT_NOT_FOUND.getCode(),
                        ErrorMessage.PRODUCT_NOT_FOUND.getMessage()
                ))).map(modelMapper::toModel)
                .doOnSubscribe(sub -> log.info("Buscando producto con ID: {}", idProducto))
                .doOnSuccess(p -> log.info("Producto encontrado: {}", p.getIdProducto()))
                .onErrorMap(
                        error -> (error instanceof MappingException || error instanceof NullPointerException),
                        error -> {
                            log.error("Error en el mapeo Producto ↔ Documento (findById {}): {}", idProducto, error.getMessage(), error);
                            return new TechnicalException(
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getCode(),
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getMessage(),
                                    error
                            );
                        }
                ) .onErrorMap(error -> {
                    if (error instanceof DomainException) return error; // no lo toca
                    log.error("Error técnico general al buscar producto {}: {}", idProducto, error.getMessage(), error);
                    return new TechnicalException(
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getCode(),
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getMessage(),
                            error
                    );
                });
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return repository.delete(modelMapper.toDocument(producto))
                .doOnSubscribe(sub -> log.info("Iniciando eliminación de producto con ID: {}", producto.getIdProducto()))
                .doOnSuccess(v -> log.info("Producto eliminado correctamente: {}", producto.getIdProducto()))
                .onErrorMap(
                        error -> (error instanceof MappingException || error instanceof NullPointerException),
                        error -> {
                            log.error("Error de mapeo Producto ↔ Documento (delete {}) : {}", producto.getIdProducto(), error.getMessage(), error);
                            return new TechnicalException(
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getCode(),
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getMessage(),
                                    error
                            );
                        }
                ) .onErrorMap(error -> {
                    if (error instanceof DomainException) return error; // respeta tu negocio
                    log.error("Error técnico general al eliminar producto {}: {}", producto.getIdProducto(), error.getMessage(), error);
                    return new TechnicalException(
                            ErrorMessage.TECHNICAL_ERROR_DELETE_PRODUCTO.getCode(),
                            ErrorMessage.TECHNICAL_ERROR_DELETE_PRODUCTO.getMessage(),
                            error
                    );
                });
    }
}
