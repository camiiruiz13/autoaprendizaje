package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "productos")
public class ProductoDocument {

    @Id
    private ObjectId id;


    private String nombre;

    private Double precio;

    private LocalDateTime fechaCreacion;;

    private String idCategoria;

    private String nombreImagen;

    private String imagenBase64;

}
