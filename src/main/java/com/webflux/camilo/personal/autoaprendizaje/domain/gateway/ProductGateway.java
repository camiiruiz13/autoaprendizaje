package com.webflux.camilo.personal.autoaprendizaje.domain.gateway;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import reactor.core.publisher.Mono;

public interface ProductGateway {

    Mono<Producto> saveProducto(Producto producto);
}
