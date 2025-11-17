package com.webflux.camilo.personal.autoaprendizaje.domain.usecase;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.CategoriaGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class FindCategoriaByIdUseCase {

    private final CategoriaGateway categoriaGateway;

    public Mono<Categoria> findById(String idCategoria) {
        return categoriaGateway.findById(idCategoria)
                .switchIfEmpty(Mono.error(new DomainException( ErrorMessage.CATEGORY_NOT_FOUND.getCode(),
                        ErrorMessage.CATEGORY_NOT_FOUND.getMessage())))
                .doOnSubscribe(sub -> log.info("Iniciando búsqueda de categoria ID={}", idCategoria))
                .doOnSuccess(prod -> log.info("categoria encontrado ID={}", idCategoria))
                .onErrorMap(TechnicalException.class, ex -> new DomainException(ex.getCode(), ex.getMessage()))
                .doOnError(err -> log.error("Error al buscar categoria ID={}: {}", idCategoria, err.getMessage()));
    }
}
