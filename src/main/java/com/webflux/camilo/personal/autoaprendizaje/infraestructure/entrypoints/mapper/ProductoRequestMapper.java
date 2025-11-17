package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request.ProductoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoRequestMapper {
    @Mapping(target = "fechaCreacion", ignore = true)
    Producto toModel(ProductoRequestDTO dto);

    @Mapping(source = "idProducto", target = "idProducto")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "idCategoria", target = "idCategoria")
    ProductoRequestDTO toRequest(Producto model);
}
