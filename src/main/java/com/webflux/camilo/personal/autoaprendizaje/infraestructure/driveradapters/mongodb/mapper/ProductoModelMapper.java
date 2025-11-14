package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.ProductoDocument;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductoModelMapper {

    @Mapping(source = "id", target = "idProducto")
    Producto toModel(ProductoDocument productoDocument);

    @Mapping(source = "idProducto", target = "id")
    ProductoDocument toDocument(Producto producto);

    @AfterMapping
    default void normalizeId(Producto source, @MappingTarget ProductoDocument target) {

        if (source.getIdProducto() == null || source.getIdProducto().isBlank()) {
            target.setId(null);
        }
    }

}
