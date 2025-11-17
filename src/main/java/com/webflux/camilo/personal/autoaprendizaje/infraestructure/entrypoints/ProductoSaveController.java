package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints;

import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.FindCategoriesAllUseCase;
import com.webflux.camilo.personal.autoaprendizaje.domain.usecase.SaveProductoUseCase;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator.FormValidator;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request.ProductoRequestDTO;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper.ProductoRequestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestPart;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class ProductoSaveController {

    private final SaveProductoUseCase usecase;
    private final FindCategoriesAllUseCase findCategoriesAllUseCase;
    private final ProductoRequestMapper requestMapper;

    @PostMapping(value = "/crear-producto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> saveProducto(@Valid @ModelAttribute("producto") ProductoRequestDTO productoRequestDTO,
                                     BindingResult bindingResult, @RequestPart(name = "imagen", required = false) FilePart imagen,
                                     Model model) {

        Mono<String> validation = FormValidator.validate(bindingResult, model, "crear-producto");


        return validation.switchIfEmpty(Mono.defer(() -> {
            Mono<ProductoRequestDTO> dtoConImagenMono;
            if (imagen == null || imagen.filename() == null || imagen.filename().isBlank())
                dtoConImagenMono = Mono.just(productoRequestDTO);
            else {
                dtoConImagenMono = DataBufferUtils.join(imagen.content())
                        .map(dataBuffer -> {
                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(bytes);
                            DataBufferUtils.release(dataBuffer);

                            productoRequestDTO.setNombreImagen(imagen.filename());
                            productoRequestDTO.setImagenBase64(
                                    Base64.getEncoder().encodeToString(bytes)
                            );
                            return productoRequestDTO;
                        });
            }
            return dtoConImagenMono.map(requestMapper::toModel)
                    .flatMap(usecase::saveProducto)
                    .thenReturn("redirect:/productos");

        }));

    }


}
