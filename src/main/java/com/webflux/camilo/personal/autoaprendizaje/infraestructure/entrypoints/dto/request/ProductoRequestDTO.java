package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoRequestDTO {

    private String idProducto;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @NotBlank(message = "Debe seleccionar una categoría")
    private String idCategoria;

    private String nombreImagen;

    private String imagenBase64;
}
