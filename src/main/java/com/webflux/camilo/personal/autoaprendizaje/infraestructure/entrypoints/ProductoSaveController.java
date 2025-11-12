package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;

import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.SaveProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductoSaveController {

    private final SaveProductoUseCase saveProductoUseCase;


}
