package com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.driveradapters.mongodb.documents.CategoriaDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T10:56:59-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CategoriaModelMapperImpl implements CategoriaModelMapper {

    @Override
    public Categoria toModel(CategoriaDocument document) {
        if ( document == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setIdCategoria( document.getId() );
        categoria.setNombre( document.getNombre() );

        return categoria;
    }

    @Override
    public CategoriaDocument toDocument(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDocument categoriaDocument = new CategoriaDocument();

        categoriaDocument.setId( categoria.getIdCategoria() );
        categoriaDocument.setNombre( categoria.getNombre() );

        return categoriaDocument;
    }
}
