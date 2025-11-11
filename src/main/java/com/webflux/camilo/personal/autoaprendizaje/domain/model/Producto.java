package com.webflux.camilo.personal.autoaprendizaje.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class Producto {

    private String idProducto;

    private String nombre;

    private Double precio;

    private Date fechaCreacion;
}
