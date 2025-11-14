package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;


import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator.FormValidator;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoFindByIdController {

    private final FindByIdUseCase useCase;
    private final ProductoDTOMapper mapper;

    @GetMapping("/crear-producto/{id}")
    public Mono<String> buscarProductoPorId(@PathVariable String id, Model model) {
        return FormValidator.validatePathParam(id, model, "productos", "id")
                .switchIfEmpty(useCase.findById(id).map(mapper::toDTO)
                .doOnNext(dto -> model.addAttribute("producto", dto))
                .thenReturn("crear-producto"));
    }


}
