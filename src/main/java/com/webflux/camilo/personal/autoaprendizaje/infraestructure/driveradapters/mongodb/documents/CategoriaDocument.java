package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categorias")
public class CategoriaDocument {
    @Id
    private String id;
    private String nombre;
}
