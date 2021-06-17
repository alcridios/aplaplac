package com.baufest.tennis.springtennis.repository;

import com.baufest.tennis.springtennis.mapper.JugadorMapperImpl;
import com.duoc.aplaplac.springaplaplac.dto.EntrenadorDTO;
import com.duoc.aplaplac.springaplaplac.dto.JugadorDTO;
import com.duoc.aplaplac.springaplaplac.mapper.JugadorMapper;
import com.duoc.aplaplac.springaplaplac.model.Entrenador;
import com.duoc.aplaplac.springaplaplac.model.Jugador;
import com.duoc.aplaplac.springaplaplac.repository.JugadorRepository;
import com.duoc.aplaplac.springaplaplac.service.JugadorServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class JugadorRepositoryTest {
	
	List<Jugador> jugadoresDePrueba = new ArrayList<>();
	List<JugadorDTO> jugadoresDTODePrueba = new ArrayList<>();
	
	Entrenador entrenador1 = new Entrenador("pepe");
	Entrenador entrenador2 = new Entrenador("adan");
	
	EntrenadorDTO entrenadorDTO1 = new EntrenadorDTO("pepe");
	EntrenadorDTO entrenadorDTO2 = new EntrenadorDTO("adan");
	
	Jugador JugadorParaAgregar1 = new Jugador("A", 1, entrenador1);
	Jugador JugadorParaAgregar2 = new Jugador("B", 2, entrenador2);
	Jugador JugadorParaAgregar3 = new Jugador("C", 3, entrenador2);

	JugadorDTO jugadorDTOParaAgregar1 = new JugadorDTO("A", 1, entrenadorDTO1);
	JugadorDTO jugadorDTOParaAgregar2 = new JugadorDTO("B", 2, entrenadorDTO2);
	JugadorDTO jugadorDTOParaAgregar3 = new JugadorDTO("C", 3, entrenadorDTO1);

	@Mock()
    private JugadorRepository jugadorRepository;
	
	JugadorServiceImpl jugadorService;
	
	JugadorMapper mapper = new JugadorMapperImpl();
	
	@BeforeEach
    public void setUp() {
		jugadoresDePrueba.clear();
		jugadoresDePrueba.add(new Jugador("D", 2, entrenador1));
        jugadoresDePrueba.add(new Jugador());
        jugadoresDePrueba.add(new Jugador());
        jugadoresDePrueba.add(new Jugador());

        jugadoresDTODePrueba.clear();
        jugadoresDTODePrueba.add(new JugadorDTO("A", 1, entrenadorDTO1));
        jugadoresDTODePrueba.add(new JugadorDTO());
        jugadoresDTODePrueba.add(new JugadorDTO());
        jugadoresDTODePrueba.add(new JugadorDTO());

        jugadorService = new JugadorServiceImpl(jugadorRepository ,null, mapper);
    }

    @Test
    public void testListJugadores() {
    	when(jugadorRepository.findAllByOrderByNombreAsc()).thenReturn(jugadoresDePrueba);
        List<JugadorDTO> jugadoresConseguidos = jugadorService.listAll();
        assertEquals(jugadoresDePrueba.size(), jugadoresConseguidos.size());
        verify(jugadorRepository).findAll();
        
    }

}
