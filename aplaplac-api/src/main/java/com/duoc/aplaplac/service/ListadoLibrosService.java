package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.EntrenadorDTO;
import com.baufest.tennis.springtennis.model.Entrenador;

import java.util.List;

public interface EntrenadorService {
	List<EntrenadorDTO> listAll();

	EntrenadorDTO getById(Long id);

	EntrenadorDTO save(EntrenadorDTO cancha);

	EntrenadorDTO update(EntrenadorDTO cancha);

	void delete(Long id);

}
