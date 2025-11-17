package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.repositories;

import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.CategoriaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoriaRepository extends ReactiveMongoRepository<CategoriaDocument, String> {
}
