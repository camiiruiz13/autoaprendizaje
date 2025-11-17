package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.request.ProductoRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T16:45:18-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProductoRequestMapperImpl implements ProductoRequestMapper {

    @Override
    public Producto toModel(ProductoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( dto.getIdProducto() );
        producto.setNombre( dto.getNombre() );
        producto.setPrecio( dto.getPrecio() );
        producto.setIdCategoria( dto.getIdCategoria() );
        producto.setNombreImagen( dto.getNombreImagen() );
        producto.setImagenBase64( dto.getImagenBase64() );

        return producto;
    }

    @Override
    public ProductoRequestDTO toRequest(Producto model) {
        if ( model == null ) {
            return null;
        }

        ProductoRequestDTO productoRequestDTO = new ProductoRequestDTO();

        productoRequestDTO.setIdProducto( model.getIdProducto() );
        productoRequestDTO.setNombre( model.getNombre() );
        productoRequestDTO.setPrecio( model.getPrecio() );
        productoRequestDTO.setIdCategoria( model.getIdCategoria() );
        productoRequestDTO.setNombreImagen( model.getNombreImagen() );
        productoRequestDTO.setImagenBase64( model.getImagenBase64() );

        return productoRequestDTO;
    }
}
