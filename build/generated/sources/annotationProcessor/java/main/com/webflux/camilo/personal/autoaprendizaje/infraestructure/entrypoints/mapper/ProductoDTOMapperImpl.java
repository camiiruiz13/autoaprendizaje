package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-12T16:54:55-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductoDTOMapperImpl implements ProductoDTOMapper {

    @Override
    public Producto toModel(ProductoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( dto.getIdProducto() );
        producto.setNombre( dto.getNombre() );
        producto.setPrecio( dto.getPrecio() );
        producto.setFechaCreacion( dto.getFechaCreacion() );

        return producto;
    }

    @Override
    public ProductoDTO toDTO(Producto model) {
        if ( model == null ) {
            return null;
        }

        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setIdProducto( model.getIdProducto() );
        productoDTO.setNombre( model.getNombre() );
        productoDTO.setPrecio( model.getPrecio() );
        productoDTO.setFechaCreacion( model.getFechaCreacion() );

        return productoDTO;
    }

    @Override
    public List<ProductoDTO> toDTOList(List<Producto> models) {
        if ( models == null ) {
            return null;
        }

        List<ProductoDTO> list = new ArrayList<ProductoDTO>( models.size() );
        for ( Producto producto : models ) {
            list.add( toDTO( producto ) );
        }

        return list;
    }
}
