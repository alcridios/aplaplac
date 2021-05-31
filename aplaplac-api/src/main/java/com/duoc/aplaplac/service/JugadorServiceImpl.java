package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.JugadorMapper;
import com.baufest.tennis.springtennis.model.Ganancia;
import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.JugadorRepository;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {
    public static final String PLAYER_WITH_ID = "Player with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;
    private final JugadorMapper jugadorMapper;
    

    @Autowired
    public JugadorServiceImpl(JugadorRepository jugadorRepository,
                              PartidoRepository partidoRepository,
                              JugadorMapper jugadorMapper) {
        this.jugadorRepository = jugadorRepository;
        this.partidoRepository = partidoRepository;
        this.jugadorMapper = jugadorMapper;
    }

    @Override
    public List<JugadorDTO> listAll() {
        return jugadorRepository.findAllByOrderByNombreAsc().stream()
                .map(this.jugadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JugadorDTO getById(Long id) {
        return jugadorRepository.findById(id).map(this.jugadorMapper::toDTO) 
        		.orElseThrow(() -> new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST));
    }

    @Override
    public JugadorDTO save(JugadorDTO jugador) {
        boolean exists = jugador.getId() != null && jugadorRepository.existsById(jugador.getId());
        if (exists) {
            throw new IllegalArgumentException(PLAYER_WITH_ID + jugador.getId() + ALREADY_EXISTS);
        }
        return this.jugadorMapper.toDTO(jugadorRepository.save(this.jugadorMapper.fromDTO(jugador)));
    }

    @Override
    public JugadorDTO update(JugadorDTO jugador) {
        boolean exists = jugadorRepository.existsById(jugador.getId());
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + jugador.getId() + DOES_NOT_EXIST);
        }
        return this.jugadorMapper.toDTO(jugadorRepository.save(this.jugadorMapper.fromDTO(jugador)));
    }

    @Override
    public void delete(Long id) {
        boolean exists = jugadorRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
        }
        jugadorRepository.deleteById(id);
    }

    @Override
    public JugadorDTO recalculateRanking(Long id) {
        Optional<Jugador> optJugador = jugadorRepository.findById(id);
        if (optJugador.isPresent()) {
            Jugador jugador = optJugador.get();

            List<Partido> partidos = partidoRepository.findAll();

            // Define las constantes con los indices definidos para el calculo
            final int INDICADOR_GANADOS_LOCAL = 10;
            final int INDICADOR_GANADOS_VISITANTE = 15;
            final int INDICADOR_PERDIDOS_LOCAL = -5;

            // Inicializa el ranking inicial
            int ranking = 0;

            List<Partido> partidosGanadosLocal;
            List<Partido> partidosGanadosVisitante;
            List<Partido> partidosPerdidosLocal;

            partidosGanadosLocal = listPartidosGanadosLocalDeJugador(id, partidos);
            partidosGanadosVisitante = listPartidosGanadosVisitanteDeJugador(id, partidos);
            partidosPerdidosLocal = listPartidosPerdidosLocalDeJugador(id, partidos);

            // Realiza el calculo por cada indicador
            ranking += partidosGanadosLocal.size() * INDICADOR_GANADOS_LOCAL;
            ranking += partidosGanadosVisitante.size() * INDICADOR_GANADOS_VISITANTE;
            ranking += partidosPerdidosLocal.size() * INDICADOR_PERDIDOS_LOCAL;

            // Si el ranking obtenido es menor a 0 se setea en 0
            ranking = Math.max(ranking, 0);

            // Se resetean los puntos en el jugador
            jugador.setPuntos(ranking);
            return this.jugadorMapper.toDTO(jugadorRepository.save(jugador));
        } else{
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
        }
    }
    
    
    

    private List<Partido> listPartidosGanadosLocalDeJugador(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorLocal().getId().equals(id) && p.getCantidadGamesLocal() == 6)
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }

    private List<Partido> listPartidosGanadosVisitanteDeJugador(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorVisitante().getId().equals(id) && p.getCantidadGamesVisitante() == 6)
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }

    private List<Partido> listPartidosPerdidosLocalDeJugador(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorLocal().getId().equals(id) && p.getCantidadGamesVisitante() == 6)
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }
    
    
	@Override
    public List<Ganancia> calcularGanancias() {
		List<Ganancia> listaGananciaJugador = new ArrayList<>();
		List<Jugador> listaJugadores = jugadorRepository.findAll();
		
		for (Jugador jugador : listaJugadores) {
			Ganancia gananciaJugadorActual = new Ganancia();
			List<Partido> partidos = partidoRepository.findAll();

            // Define las constantes con los indices definidos para el calculo
            final int INDICADOR_GANADOS_SUPERIOR = 300;
            final int INDICADOR_GANADOS_INFERIOR = 200;

            // Inicializa el ganancia inicial
            int ganancia = 0;

            List<Partido> partidosGanadosSuperiorLocal;
            List<Partido> partidosGanadosSuperiorVisitante;
            List<Partido> partidosGanadosInferiorLocal;
            List<Partido> partidosGanadosInferiorVisitante;

            partidosGanadosSuperiorLocal = listPartidosGanadosSuperiorLocal(jugador.getId(), partidos);
            partidosGanadosSuperiorVisitante = listPartidosGanadosSuperiorVisitante(jugador.getId(), partidos);
            partidosGanadosInferiorLocal = listPartidosGanadosInferiorLocal(jugador.getId(), partidos);
            partidosGanadosInferiorVisitante = listPartidosGanadosInferiorVisitante(jugador.getId(), partidos);

            // Realiza el calculo por cada indicador
            ganancia += partidosGanadosSuperiorLocal.size() * INDICADOR_GANADOS_SUPERIOR;
            ganancia += partidosGanadosSuperiorVisitante.size() * INDICADOR_GANADOS_SUPERIOR;
            ganancia += partidosGanadosInferiorLocal.size() * INDICADOR_GANADOS_INFERIOR;
            ganancia += partidosGanadosInferiorVisitante.size() * INDICADOR_GANADOS_INFERIOR;

            // Si la ganancia obtenida es menor a 0 se setea en 0
            //ganancia = Math.max(ganancia, 0);
            
            gananciaJugadorActual.setGanancia(ganancia);
            gananciaJugadorActual.setId(jugador.getId());
            listaGananciaJugador.add(gananciaJugadorActual);
		}
            // se retornan las ganancias
            return listaGananciaJugador; 
    }
	
	private List<Partido> listPartidosGanadosSuperiorLocal(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorLocal().getId().equals(id) && p.getCantidadGamesLocal() == 6 
            		&& p.getCantidadGamesVisitante() > -1 && p.getCantidadGamesVisitante() < 4 )
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }
	
	private List<Partido> listPartidosGanadosSuperiorVisitante(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorVisitante().getId().equals(id) && p.getCantidadGamesVisitante() == 6 
            		&& p.getCantidadGamesLocal() > -1 && p.getCantidadGamesLocal() < 4 )
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }


    private List<Partido> listPartidosGanadosInferiorLocal(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorLocal().getId().equals(id) && p.getCantidadGamesLocal() == 6 
            		&& p.getCantidadGamesVisitante() > 3 && p.getCantidadGamesVisitante() < 6)
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }
    
    private List<Partido> listPartidosGanadosInferiorVisitante(Long id, List<Partido> partidos) {
        List<Partido> partidosFiltrados = new ArrayList<>();
        for(Partido p : partidos){
            if(p.getEstado() == Estado.FINALIZADO && p.getJugadorVisitante().getId().equals(id) && p.getCantidadGamesVisitante() == 6 
            		&& p.getCantidadGamesLocal() > 3 && p.getCantidadGamesLocal() < 6)
                partidosFiltrados.add(p);
        }
        return partidosFiltrados;
    }

}
