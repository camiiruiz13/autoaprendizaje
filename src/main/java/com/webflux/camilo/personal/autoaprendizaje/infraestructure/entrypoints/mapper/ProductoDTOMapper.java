package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.ProductoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoDTOMapper {

    Producto toModel(ProductoDTO dto);
    ProductoDTO toDTO(Producto model);
    List<ProductoDTO> toDTOList(List<Producto> models);
}
