package com.duoc.aplaplac.springaplaplac.service;

import java.util.List;

import com.duoc.aplaplac.springaplaplac.dto.CarreraDTO;

public interface CarreraService {
	List<CarreraDTO> listAll();

	CarreraDTO getById(Long id);

	CarreraDTO save(CarreraDTO cancha);

	CarreraDTO update(CarreraDTO cancha);

	void delete(Long id);

}
