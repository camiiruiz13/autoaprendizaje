package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;

import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriaByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindProductoByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator.FormValidator;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoDetailController {


    private final FindProductoByIdUseCase useCase;
    private final FindCategoriaByIdUseCase findCategoriaByIdUseCase;
    private final ProductoResponseMapper mapper;

    @GetMapping("/producto/{id}")
    public Mono<String> buscarProductoPorId(@PathVariable String id, Model model) {

        return FormValidator.validatePathParam(id, model, "productos", "id")
                .switchIfEmpty(useCase.findById(id)
                        .flatMap(producto ->
                                findCategoriaByIdUseCase.findById(producto.getIdCategoria())
                                        .map(categoria -> mapper.toDTO(producto, categoria))
                        ).doOnNext(dto -> model.addAttribute("producto", dto))
                        .thenReturn("detalle-producto"));
    }

    }
