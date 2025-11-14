package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;

import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.SaveProductoUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator.FormValidator;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request.ProductoRequestDTO;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoDTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoSaveController {

    private final SaveProductoUseCase usecase;
    private final ProductoDTOMapper mapper;

    @PostMapping("/crear-producto")
    public Mono<String> saveProducto(@Valid @ModelAttribute("producto") ProductoRequestDTO productoRequestDTO,
                                     BindingResult bindingResult,
                                     Model model) {

        Mono<String> validation = FormValidator.validate(bindingResult, model, "crear-producto");


        return validation.switchIfEmpty(
            usecase.saveProducto(mapper.toModel(productoRequestDTO))
                .thenReturn("redirect:/productos"));

    }


}
