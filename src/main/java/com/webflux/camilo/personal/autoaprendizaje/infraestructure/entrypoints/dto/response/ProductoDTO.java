package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String idProducto;

    private String nombre;

    private Double precio;

    private Date fechaCreacion;
}
