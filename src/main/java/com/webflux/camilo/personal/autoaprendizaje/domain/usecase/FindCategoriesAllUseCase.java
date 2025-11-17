package com.webflux.camilo.personal.autoaprendizaje.domain.usecase;

import com.webflux.camilo.personal.autoaprendizaje.domain.enums.ErrorMessage;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.DomainException;
import com.webflux.camilo.personal.autoaprendizaje.domain.exception.TechnicalException;
import com.webflux.camilo.personal.autoaprendizaje.domain.gateway.CategoriaGateway;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class FindCategoriesAllUseCase {

    private final CategoriaGateway categoriaGateway;

    public Flux<Categoria> findAll(){
        return categoriaGateway.findAll()
                .doOnSubscribe(sub -> log.info("Iniciando proceso de listado de categorias..."))
                .switchIfEmpty(Mono.error(new DomainException(ErrorMessage.NO_CATEGORIES_FOUND.getCode(),
                        ErrorMessage.NO_CATEGORIES_FOUND.getMessage())))
                .doOnComplete(() -> log.info("Listado de categorias completado exitosamente"))
                .onErrorMap(TechnicalException.class, ex ->
                        new DomainException(ex.getCode(), ex.getMessage())
                )
                .doOnError(error -> log.error("Error en ListAllCategorieUseCase: {}", error.getMessage(), error));
    }
}
