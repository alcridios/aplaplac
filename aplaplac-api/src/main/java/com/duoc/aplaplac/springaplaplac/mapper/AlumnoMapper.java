package com.duoc.aplaplac.springaplaplac.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.duoc.aplaplac.springaplaplac.dto.AlumnoDTO;
import com.duoc.aplaplac.springaplaplac.model.Alumno;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {
    AlumnoMapper INSTANCE = Mappers.getMapper(AlumnoMapper.class);

    AlumnoDTO toDTO(Alumno entity);
    Alumno fromDTO(AlumnoDTO entity);

}
