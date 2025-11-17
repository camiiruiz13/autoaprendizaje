package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.adapters;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.CategoriaGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper.CategoriaModelMapper;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoriaAdapter implements CategoriaGateway {

    private final CategoriaRepository repository;
    private final CategoriaModelMapper mapper;
    @Override
    public Flux<Categoria> findAll() {
        return repository.findAll()
                .map(mapper::toModel)
                .doOnSubscribe(sub->log.info("Iniciando listado de categorias"))
                .doOnComplete(()->log.info("Listado de categorias finalizado correctamente"))
                .onErrorMap(error -> (error instanceof MappingException || error instanceof NullPointerException),
                        error -> {
                            log.error("Error en el mapeo Categoria ↔ Documento: {}", error.getMessage(), error);
                            return new TechnicalException(
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getCode(),
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getMessage(),
                                    error);
                }).onErrorMap(error -> {
                    log.error("Error técnico general al listar categorías: {}", error.getMessage(), error);
                    return new TechnicalException(
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getCode(),
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getMessage(),
                            error);
                });
    }

    @Override
    public Mono<Categoria> findById(String idCategoria) {
        return repository.findById(idCategoria)
                .map(mapper::toModel)
                .doOnSubscribe(sub -> log.info("Iniciando consulta de categoría con ID: {}", idCategoria))
                .doOnSuccess(cat -> log.info("Consulta de categoría completada correctamente: {}", cat != null ? cat.getNombre() : "No encontrada"))
                .onErrorMap(error -> (error instanceof MappingException || error instanceof NullPointerException),
                        error -> {
                            log.error("Error en el mapeo Categoria ↔ Documento: {}", error.getMessage(), error);
                            return new TechnicalException(
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getCode(),
                                    ErrorMessage.MAPPING_ERROR_PRODUCTO.getMessage(),
                                    error);
                        }).onErrorMap(error -> {
                    log.error("Error técnico general al consultar categoría: {}", error.getMessage(), error);
                    return new TechnicalException(
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getCode(),
                            ErrorMessage.TECHNICAL_ERROR_RETRIEVE_PRODUCTO.getMessage(),
                            error);
                });
    }


}
