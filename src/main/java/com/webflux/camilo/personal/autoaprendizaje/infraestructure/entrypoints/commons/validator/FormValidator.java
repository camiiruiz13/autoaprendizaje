package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.commons.validator;


import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.exception.WebValidationException;
import lombok.experimental.UtilityClass;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Mono;

@UtilityClass
public class FormValidator {
    public static Mono<String> validate(BindingResult bindingResult, Model model, String viewOnError) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("alertType", "warning");
            model.addAttribute("alertMessage", "Corrige los errores del formulario.");

            return Mono.just(viewOnError);
        }

        return Mono.empty();
    }

    public static Mono<String> validatePathParam(String value, Model model, String viewOnError,
                                                 String fieldName) {
        if (value == null || value.isBlank()) {
            model.addAttribute("alertType", "warning");
            model.addAttribute("alertMessage", "El parámetro de ruta '" + fieldName + "' es obligatorio y no puede estar vacío.");
            return Mono.just(viewOnError);
        }
        return Mono.empty();
    }

}
