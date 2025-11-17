package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;


import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriaByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriesAllUseCase;
import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindProductoByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator.FormValidator;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoRequestMapper;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoFindByIdController {

    private final FindProductoByIdUseCase useCase;
    private final FindCategoriesAllUseCase categoriasUseCase;
    private final ProductoRequestMapper requestMapper;

    @GetMapping("/crear-producto/{id}")
    public Mono<String> buscarProductoPorId(@PathVariable String id, Model model) {

        return FormValidator.validatePathParam(id, model, "productos", "id")
                .switchIfEmpty(
                        Mono.zip(
                                useCase.findById(id),
                                categoriasUseCase.findAll().collectList()
                        ).map(tuple -> {

                            var producto = tuple.getT1();
                            var categorias = tuple.getT2();

                            model.addAttribute("producto", requestMapper.toRequest(producto));
                            model.addAttribute("categorias", categorias);
                            model.addAttribute("modoEdicion", true);

                            return "crear-producto";
                        })
                );
    }
}

