package com.baufest.tennis.springtennis.service;

import java.util.List;

import com.baufest.tennis.springtennis.dto.TorneoDTO;
import com.baufest.tennis.springtennis.model.Torneo;

public interface TorneoService {
	List<TorneoDTO> listAll();

	TorneoDTO getById(Long id);
	
	void delete(Long id);

	TorneoDTO save(TorneoDTO Torneo);

	TorneoDTO update(TorneoDTO Torneo);
}