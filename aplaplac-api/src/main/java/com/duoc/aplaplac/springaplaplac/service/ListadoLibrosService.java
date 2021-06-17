package com.duoc.aplaplac.springaplaplac.service;

import java.util.List;

import com.duoc.aplaplac.springaplaplac.dto.ListadoLibrosDTO;

public interface ListadoLibrosService {
	List<ListadoLibrosDTO> listAll();

	ListadoLibrosDTO getById(Long id);

	ListadoLibrosDTO save(ListadoLibrosDTO cancha);

	ListadoLibrosDTO update(ListadoLibrosDTO cancha);

	void delete(Long id);

}
