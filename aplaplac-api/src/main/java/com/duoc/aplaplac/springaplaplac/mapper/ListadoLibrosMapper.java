package com.duoc.aplaplac.springaplaplac.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.duoc.aplaplac.springaplaplac.dto.ListadoLibrosDTO;
import com.duoc.aplaplac.springaplaplac.model.ListadoLibros;

@Mapper(componentModel = "spring")
public interface ListadoLibrosMapper {
    ListadoLibrosMapper INSTANCE = Mappers.getMapper(ListadoLibrosMapper.class);

    ListadoLibrosDTO toDTO(ListadoLibros entity);
    ListadoLibros fromDTO(ListadoLibrosDTO entity);
}
