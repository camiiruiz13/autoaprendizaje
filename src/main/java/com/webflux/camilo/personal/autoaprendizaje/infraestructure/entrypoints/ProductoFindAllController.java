package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;


import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindAllUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.ProductoDTO;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ProductoFindAllController {

    private final FindAllUseCase useCase;
    private final ProductoDTOMapper mapper;

    @GetMapping("/productos")
    public Mono<String> listarProductos(Model model) {
        var productosFlux = useCase.findAll()
                .collectList()
                .map(mapper::toDTOList)
                .flatMap(productos->{
                    model.addAttribute("productos", productos);
                    return Mono.just("productos");
                });

        return productosFlux;
    }


}
