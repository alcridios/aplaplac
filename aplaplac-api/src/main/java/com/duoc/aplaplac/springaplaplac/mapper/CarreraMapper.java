package com.duoc.aplaplac.springaplaplac.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.duoc.aplaplac.springaplaplac.dto.CarreraDTO;
import com.duoc.aplaplac.springaplaplac.model.Carrera;

@Mapper(componentModel = "spring")
public interface CarreraMapper {
    CarreraMapper INSTANCE = Mappers.getMapper(CarreraMapper.class);

    CarreraDTO toDTO(Carrera entity);
    Carrera fromDTO(CarreraDTO entity);

}
