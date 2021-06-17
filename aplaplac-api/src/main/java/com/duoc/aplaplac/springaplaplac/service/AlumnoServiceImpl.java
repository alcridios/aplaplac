package com.duoc.aplaplac.springaplaplac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.aplaplac.springaplaplac.dto.AlumnoDTO;
import com.duoc.aplaplac.springaplaplac.mapper.AlumnoMapper;
import com.duoc.aplaplac.springaplaplac.repository.AlumnoRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    public static final String PLAYER_WITH_ID = "Player with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;
    

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository,
                              AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public List<AlumnoDTO> listAll() {
        return alumnoRepository.findAllByOrderByNombreAsc().stream()
                .map(this.alumnoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO getById(Long id) {
        return alumnoRepository.findById(id).map(this.alumnoMapper::toDTO) 
        		.orElseThrow(() -> new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST));
    }

    @Override
    public AlumnoDTO save(AlumnoDTO jugador) {
        boolean exists = jugador.getId() != null && alumnoRepository.existsById(jugador.getId());
        if (exists) {
            throw new IllegalArgumentException(PLAYER_WITH_ID + jugador.getId() + ALREADY_EXISTS);
        }
        return this.alumnoMapper.toDTO(alumnoRepository.save(this.alumnoMapper.fromDTO(jugador)));
    }

    @Override
    public AlumnoDTO update(AlumnoDTO jugador) {
        boolean exists = alumnoRepository.existsById(jugador.getId());
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + jugador.getId() + DOES_NOT_EXIST);
        }
        return this.alumnoMapper.toDTO(alumnoRepository.save(this.alumnoMapper.fromDTO(jugador)));
    }

    @Override
    public void delete(Long id) {
        boolean exists = alumnoRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
        }
        alumnoRepository.deleteById(id);
    }


}
