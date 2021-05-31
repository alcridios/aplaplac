package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.dto.TorneoDTO;
import com.baufest.tennis.springtennis.mapper.TorneoMapper;
import com.baufest.tennis.springtennis.repository.TorneoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TorneoServicesImpl implements TorneoService {
    public static final String DOES_NOT_EXIST = " no existe.";
    public static final String TORNEO_WITH_ID = "Torneo con id = ";
    public static final String ALREADY_EXISTS = " ya existe.";
    private static final String PLAYER_MISSING = "Se deben agregar todos los jugadores.";
    private static final String PLAYER_DUPLICATED = "Los jugadores agregados deben ser distintos.";
    private static final String INVALID_DATE = "La fecha/hora de inicio debe ser mayor o igual a la fecha/hora actual.";

    private final TorneoRepository torneoRepository;
    private final TorneoMapper torneoMapper;

    @Autowired
    public TorneoServicesImpl(TorneoRepository torneoRepository, TorneoMapper torneoMapper) {
        this.torneoRepository = torneoRepository;
        this.torneoMapper = torneoMapper;
    }

    @Override
    public List<TorneoDTO> listAll() {
        return torneoRepository.findAll()
                .stream()
                .map(this.torneoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TorneoDTO getById(Long id) {
        return torneoRepository.findById(id).map(this.torneoMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException(TORNEO_WITH_ID + id + DOES_NOT_EXIST));
    }

    private void validarJugadores(TorneoDTO torneo) {
        JugadorDTO jugador_1 = torneo.getJugador_1();
        JugadorDTO jugador_2 = torneo.getJugador_2();
        JugadorDTO jugador_3 = torneo.getJugador_3();
        JugadorDTO jugador_4 = torneo.getJugador_4();

        if (jugador_1 != null && jugador_2 != null && jugador_3 != null && jugador_4 != null) {
            if (jugador_1.getId().equals(jugador_2.getId())
            		|| jugador_1.getId().equals(jugador_3.getId())
            		|| jugador_1.getId().equals(jugador_4.getId())
            		|| jugador_2.getId().equals(jugador_3.getId())
            		|| jugador_2.getId().equals(jugador_4.getId())
            		|| jugador_3.getId().equals(jugador_4.getId())) {
                throw new IllegalArgumentException(PLAYER_DUPLICATED);
            }
        } else {
            throw new IllegalArgumentException(PLAYER_MISSING);
        }
    }

    private void validarFechaYHora(TorneoDTO torneo) {
        Date now = new Date();
        if (torneo.getFechaComienzo().compareTo(now) < 0) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    private void validarNuevoTorneo(TorneoDTO torneo) {
        boolean exists = torneo.getId() != null && torneoRepository.existsById(torneo.getId());
        if (exists) {
            throw new IllegalArgumentException(TORNEO_WITH_ID + torneo.getId() + ALREADY_EXISTS);
        }
        this.validarJugadores(torneo);
        this.validarFechaYHora(torneo);
    }
    
    private void creacionPartidos(TorneoDTO torneo) {
    	
    }

    @Override
    public TorneoDTO save(TorneoDTO torneo) {
        this.validarNuevoTorneo(torneo);
        return this.torneoMapper.toDTO(torneoRepository.save(this.torneoMapper.fromDTO(torneo)));
    }

    private void validarTorneoEditado(TorneoDTO torneo) {
        boolean exists = torneoRepository.existsById(torneo.getId());
        if (!exists) {
            throw new NoSuchElementException(TORNEO_WITH_ID + torneo.getId() + DOES_NOT_EXIST);
        }
        this.validarJugadores(torneo);
        this.validarFechaYHora(torneo);
    }

    @Override
    public TorneoDTO update(TorneoDTO torneo) {
        this.validarTorneoEditado(torneo);
        return this.torneoMapper.toDTO(torneoRepository.save(this.torneoMapper.fromDTO(torneo)));
    }

    private void validarTorneoEliminado(Long id) {
        boolean exists = torneoRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(TORNEO_WITH_ID + id + DOES_NOT_EXIST);
        }
    }

    @Override
    public void delete(Long id) {
        this.validarTorneoEliminado(id);
        torneoRepository.deleteById(id);
    }
}
