package com.webflux.camilo.personal.autoaprendizaje.domain.gateway;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductGateway {

    Mono<Producto> saveProducto(Producto producto);
    Flux<Producto> findAll();

    Mono<Producto> findById(String idProducto);

    Mono<Void> delete(Producto producto);
}
