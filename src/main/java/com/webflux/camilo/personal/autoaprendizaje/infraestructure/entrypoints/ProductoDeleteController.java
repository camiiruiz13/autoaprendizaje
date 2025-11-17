package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;

import org.springframework.ui.Model;
import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.DeleteProductoByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator.FormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoDeleteController {

    private final DeleteProductoByIdUseCase deleteProductoByIdUseCase;

    @GetMapping("/eliminar-producto/{id}")
    public Mono<String> eliminarProducto(@PathVariable("id") String idProducto, Model model) {

        return FormValidator.validatePathParam(idProducto, model, "productos", "id")
                .switchIfEmpty(deleteProductoByIdUseCase.deletedById(idProducto)
                                .thenReturn("redirect:/productos?deleted=true"));
    }

}
