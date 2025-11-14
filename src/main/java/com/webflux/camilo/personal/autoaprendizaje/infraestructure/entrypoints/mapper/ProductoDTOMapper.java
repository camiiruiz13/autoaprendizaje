package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request.ProductoRequestDTO;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response.ProductoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoDTOMapper {


    @Mapping(target = "fechaCreacion", ignore = true)
    Producto toModel(ProductoRequestDTO dto);
    ProductoDTO toDTO(Producto model);
    List<ProductoDTO> toDTOList(List<Producto> models);
}
