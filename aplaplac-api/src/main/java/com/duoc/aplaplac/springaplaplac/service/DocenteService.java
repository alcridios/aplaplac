package com.duoc.aplaplac.springaplaplac.service;

import java.util.List;

import com.duoc.aplaplac.springaplaplac.dto.DocenteDTO;

public interface DocenteService {
	
	List<DocenteDTO> listAll();

	DocenteDTO getById(Long id);

	DocenteDTO save(DocenteDTO docente);

	DocenteDTO update(DocenteDTO docente);

	void delete(Long id);
}
