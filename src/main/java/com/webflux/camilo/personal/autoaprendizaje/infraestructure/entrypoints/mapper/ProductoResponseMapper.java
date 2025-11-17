package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response.ProductoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoriaDTOMapper.class})
public interface ProductoResponseMapper {

    @Mapping(source = "producto.idProducto", target = "idProducto")
    @Mapping(source = "producto.nombre", target = "nombre")
    @Mapping(source = "producto.precio", target = "precio")
    @Mapping(source = "producto.fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "categoria", target = "categoria")
    ProductoDTO toDTO(Producto producto, Categoria categoria);
}
