package com.duoc.aplaplac.springaplaplac.service;

import java.util.List;

import com.duoc.aplaplac.springaplaplac.dto.AlumnoDTO;

public interface AlumnoService {
	
	List<AlumnoDTO> listAll();

	AlumnoDTO getById(Long id);

	AlumnoDTO save(AlumnoDTO jugador);

	AlumnoDTO update(AlumnoDTO jugador);

	void delete(Long id);
}
