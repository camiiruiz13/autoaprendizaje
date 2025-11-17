package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.CategoriaDocument;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaModelMapper {

    @Mapping(source = "id", target = "idCategoria")
    Categoria toModel(CategoriaDocument document);

    @Mapping(source = "idCategoria", target = "id")
    CategoriaDocument toDocument(Categoria categoria);

}
