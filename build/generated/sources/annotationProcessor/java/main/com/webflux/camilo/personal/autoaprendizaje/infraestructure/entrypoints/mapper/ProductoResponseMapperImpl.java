package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response.ProductoDTO;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T16:45:18-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProductoResponseMapperImpl implements ProductoResponseMapper {

    @Autowired
    private CategoriaDTOMapper categoriaDTOMapper;

    @Override
    public ProductoDTO toDTO(Producto producto, Categoria categoria) {
        if ( producto == null && categoria == null ) {
            return null;
        }

        ProductoDTO productoDTO = new ProductoDTO();

        if ( producto != null ) {
            productoDTO.setIdProducto( producto.getIdProducto() );
            productoDTO.setNombre( producto.getNombre() );
            productoDTO.setPrecio( producto.getPrecio() );
            productoDTO.setFechaCreacion( producto.getFechaCreacion() );
            productoDTO.setNombreImagen( producto.getNombreImagen() );
            productoDTO.setImagenBase64( producto.getImagenBase64() );
        }
        productoDTO.setCategoria( categoriaDTOMapper.toDTO( categoria ) );

        return productoDTO;
    }
}
