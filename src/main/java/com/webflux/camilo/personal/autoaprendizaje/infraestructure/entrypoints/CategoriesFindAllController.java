package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;


import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriesAllUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.CategoriaDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CategoriesFindAllController {

    private final FindCategoriesAllUseCase useCase;
    private final CategoriaDTOMapper mapper;

    @GetMapping("/categorias")
    public Mono<String> listarCategorias(Model model) {

        return useCase.findAll()
                .collectList()
                .map(mapper::toDTOList)
                .flatMap(categorias -> {
                    model.addAttribute("categorias", categorias);
                    return Mono.just("categorias");
                });
    }
}

