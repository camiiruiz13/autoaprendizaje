package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.repositories;

import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.ProductoDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<ProductoDocument, ObjectId> {
}
