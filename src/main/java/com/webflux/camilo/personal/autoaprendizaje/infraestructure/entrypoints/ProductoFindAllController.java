package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;


import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriaByIdUseCase;
import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindProductoAllUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoFindAllController {

    private final FindProductoAllUseCase useCase;
    private final FindCategoriaByIdUseCase findCategoriaByIdUseCase;
    private final ProductoResponseMapper mapper;

    @GetMapping({"/productos", "/"})
    public Mono<String> listarProductos(Model model) {
        var productosFlux = useCase.findAll()
                .flatMap(producto ->
                        findCategoriaByIdUseCase.findById(producto.getIdCategoria())
                                .map(categoria -> mapper.toDTO(producto, categoria))
                )
                .collectList()
                .map(productos -> {
                    model.addAttribute("productos", productos);
                    return "productos";
                });

        return productosFlux;
    }


}
