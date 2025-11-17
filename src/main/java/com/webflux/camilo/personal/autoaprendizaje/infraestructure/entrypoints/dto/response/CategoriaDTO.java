package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private String idCategoria;
    private String nombre;
}
