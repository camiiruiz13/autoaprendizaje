package com.webflux.camilo.personal.autoaprendizaje.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Producto {

    private String idProducto;

    private String nombre;

    private Double precio;

    private LocalDateTime fechaCreacion;

    private String idCategoria;

    private String nombreImagen;

    private String imagenBase64;
}
