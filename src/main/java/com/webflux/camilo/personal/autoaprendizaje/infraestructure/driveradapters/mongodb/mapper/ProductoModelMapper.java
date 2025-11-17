package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.ProductoDocument;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductoModelMapper {

    @Mapping(source = "id", target = "idProducto", qualifiedByName = "objectIdToString")
    Producto toModel(ProductoDocument document);

    @Mapping(source = "idProducto", target = "id", qualifiedByName = "stringToObjectId")
    ProductoDocument toDocument(Producto model);

    @Named("objectIdToString")
    static String objectIdToString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }

    @Named("stringToObjectId")
    static ObjectId stringToObjectId(String id) {
        return (id != null && !id.isBlank()) ? new ObjectId(id) : null;
    }
}

