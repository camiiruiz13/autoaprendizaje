package com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.mapper;

import com.webflux.camilo.personal.autoaprendizaje.domain.model.Categoria;
import com.webflux.camilo.personal.autoaprendizaje.infraestructure.entrypoints.dto.response.CategoriaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T10:56:59-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CategoriaDTOMapperImpl implements CategoriaDTOMapper {

    @Override
    public CategoriaDTO toDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setIdCategoria( categoria.getIdCategoria() );
        categoriaDTO.setNombre( categoria.getNombre() );

        return categoriaDTO;
    }

    @Override
    public Categoria toModel(CategoriaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setIdCategoria( dto.getIdCategoria() );
        categoria.setNombre( dto.getNombre() );

        return categoria;
    }

    @Override
    public List<CategoriaDTO> toDTOList(List<Categoria> categorias) {
        if ( categorias == null ) {
            return null;
        }

        List<CategoriaDTO> list = new ArrayList<CategoriaDTO>( categorias.size() );
        for ( Categoria categoria : categorias ) {
            list.add( toDTO( categoria ) );
        }

        return list;
    }
}
