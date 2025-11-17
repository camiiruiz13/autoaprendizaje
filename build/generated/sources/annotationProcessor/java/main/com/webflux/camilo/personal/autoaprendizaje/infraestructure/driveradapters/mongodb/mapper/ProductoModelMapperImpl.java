package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Producto;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.ProductoDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T16:45:18-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProductoModelMapperImpl implements ProductoModelMapper {

    @Override
    public Producto toModel(ProductoDocument document) {
        if ( document == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( ProductoModelMapper.objectIdToString( document.getId() ) );
        producto.setNombre( document.getNombre() );
        producto.setPrecio( document.getPrecio() );
        producto.setFechaCreacion( document.getFechaCreacion() );
        producto.setIdCategoria( document.getIdCategoria() );
        producto.setNombreImagen( document.getNombreImagen() );
        producto.setImagenBase64( document.getImagenBase64() );

        return producto;
    }

    @Override
    public ProductoDocument toDocument(Producto model) {
        if ( model == null ) {
            return null;
        }

        ProductoDocument productoDocument = new ProductoDocument();

        productoDocument.setId( ProductoModelMapper.stringToObjectId( model.getIdProducto() ) );
        productoDocument.setNombre( model.getNombre() );
        productoDocument.setPrecio( model.getPrecio() );
        productoDocument.setFechaCreacion( model.getFechaCreacion() );
        productoDocument.setIdCategoria( model.getIdCategoria() );
        productoDocument.setNombreImagen( model.getNombreImagen() );
        productoDocument.setImagenBase64( model.getImagenBase64() );

        return productoDocument;
    }
}
