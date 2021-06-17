package com.duoc.aplaplac.springaplaplac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.aplaplac.springaplaplac.dto.ListadoLibrosDTO;
import com.duoc.aplaplac.springaplaplac.mapper.ListadoLibrosMapper;
import com.duoc.aplaplac.springaplaplac.repository.ListadoLibrosRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ListadoLibrosServiceImpl implements ListadoLibrosService {

    public static final String FIELD_WITH_ID = "Field with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";
    private final ListadoLibrosRepository listadoLibrosRepository;
    private final ListadoLibrosMapper listadoLibrosMapper;

    @Autowired
    public ListadoLibrosServiceImpl(ListadoLibrosRepository listadoLibrosRepository, ListadoLibrosMapper listadoLibrosMapper) {
        this.listadoLibrosRepository = listadoLibrosRepository;
        this.listadoLibrosMapper = listadoLibrosMapper;
    }

    @Override
    public List<ListadoLibrosDTO> listAll() {
        return listadoLibrosRepository.findAll()
                .stream()
                .map(this.listadoLibrosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ListadoLibrosDTO getById(Long id) {
        return listadoLibrosMapper.toDTO(listadoLibrosRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(FIELD_WITH_ID + id + DOES_NOT_EXIST)));
    }

    @Override
    public ListadoLibrosDTO save(ListadoLibrosDTO entrenador) {
        boolean exists = entrenador.getId() != null && listadoLibrosRepository.existsById(entrenador.getId());
        if (exists) {
            throw new IllegalArgumentException(FIELD_WITH_ID + entrenador.getId() + ALREADY_EXISTS);
        }
        return this.listadoLibrosMapper
                .toDTO(
                		listadoLibrosRepository.save(this.listadoLibrosMapper.fromDTO(entrenador)));
    }

    @Override
    public ListadoLibrosDTO update(ListadoLibrosDTO entrenador) {
        boolean exists = listadoLibrosRepository.existsById(entrenador.getId());
        if (!exists) {
            throw new NoSuchElementException(FIELD_WITH_ID + entrenador.getId() + DOES_NOT_EXIST);
        }
        return this.listadoLibrosMapper
                .toDTO(
                		listadoLibrosRepository.save(this.listadoLibrosMapper.fromDTO(entrenador)));
    }

    @Override
    public void delete(Long id) {
        boolean exists = listadoLibrosRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(FIELD_WITH_ID + id + DOES_NOT_EXIST);
        }
        listadoLibrosRepository.deleteById(id);
    }

}
