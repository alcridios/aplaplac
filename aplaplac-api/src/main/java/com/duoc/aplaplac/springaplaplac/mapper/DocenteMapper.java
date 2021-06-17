package com.duoc.aplaplac.springaplaplac.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.duoc.aplaplac.springaplaplac.dto.DocenteDTO;
import com.duoc.aplaplac.springaplaplac.model.Docente;


@Mapper(componentModel = "spring")
public interface DocenteMapper {
	DocenteMapper INSTANCE = Mappers.getMapper(DocenteMapper.class);

	DocenteDTO toDTO(Docente entity);
	Docente fromDTO(DocenteDTO entity);

}