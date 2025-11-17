package com.webflux.camilo.personal.autoaprendizaje.domain.gateway;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaGateway {
    Flux<Categoria> findAll();
    Mono<Categoria> findById(String idCategoria);
}
