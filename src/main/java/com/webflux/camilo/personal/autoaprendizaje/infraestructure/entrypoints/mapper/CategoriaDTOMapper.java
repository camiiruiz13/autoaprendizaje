package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response.CategoriaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaDTOMapper {
    CategoriaDTO toDTO(Categoria categoria);
    Categoria toModel(CategoriaDTO dto);

    List<CategoriaDTO> toDTOList(List<Categoria> categorias);
}
