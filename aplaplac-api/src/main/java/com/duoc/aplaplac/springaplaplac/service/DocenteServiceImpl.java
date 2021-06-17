package com.duoc.aplaplac.springaplaplac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.aplaplac.springaplaplac.dto.DocenteDTO;
import com.duoc.aplaplac.springaplaplac.mapper.DocenteMapper;
import com.duoc.aplaplac.springaplaplac.repository.DocenteRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DocenteServiceImpl implements DocenteService {
    public static final String PLAYER_WITH_ID = "Player with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";
    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;
    

    @Autowired
    public DocenteServiceImpl(DocenteRepository docenteRepository,
    							DocenteMapper docenteMapper) {
        this.docenteRepository = docenteRepository;
        this.docenteMapper = docenteMapper;
    }

    @Override
    public List<DocenteDTO> listAll() {
        return docenteRepository.findAllByOrderByNombreAsc().stream()
                .map(this.docenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DocenteDTO getById(Long id) {
        return docenteRepository.findById(id).map(this.docenteMapper::toDTO) 
        		.orElseThrow(() -> new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST));
    }

    @Override
    public DocenteDTO save(DocenteDTO docente) {
        boolean exists = docente.getId() != null && docenteRepository.existsById(docente.getId());
        if (exists) {
            throw new IllegalArgumentException(PLAYER_WITH_ID + docente.getId() + ALREADY_EXISTS);
        }
        return this.docenteMapper.toDTO(docenteRepository.save(this.docenteMapper.fromDTO(docente)));
    }

    @Override
    public DocenteDTO update(DocenteDTO docente) {
        boolean exists = docenteRepository.existsById(docente.getId());
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + docente.getId() + DOES_NOT_EXIST);
        }
        return this.docenteMapper.toDTO(docenteRepository.save(this.docenteMapper.fromDTO(docente)));
    }

    @Override
    public void delete(Long id) {
        boolean exists = docenteRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
        }
        docenteRepository.deleteById(id);
    }


}
