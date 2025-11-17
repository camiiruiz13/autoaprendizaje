package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;

import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriesAllUseCase;
import org.springframework.ui.Model;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request.ProductoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoFormController {

    private final FindCategoriesAllUseCase findCategoriesAllUseCase;

    @GetMapping("/crear-producto")
    public Mono<String> saveProducto(Model model) {
        model.addAttribute("producto", new ProductoRequestDTO());
        model.addAttribute("modoEdicion", false);
        return findCategoriesAllUseCase.findAll().collectList()
                .doOnNext(categorias -> model.addAttribute("categorias", categorias))
                .thenReturn("crear-producto");
    }
}
