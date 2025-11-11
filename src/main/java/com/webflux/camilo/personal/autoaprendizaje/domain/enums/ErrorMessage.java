package com.webflux.camilo.personal.autoaprendizaje.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    MAPPING_ERROR_PRODUCTO("ERR_MAPER_PRODUCT","Error al mapear el producto (mapper MapStruct)"),
    TECHNICAL_ERROR_SAVE_PRODUCTO("ERR_SAVE_PRODUCT", "Ocurrió un error al guardar el producto en la base de datos");

    private final String code;
    private final String message;
}
