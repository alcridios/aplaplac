package com.duoc.aplaplac.springaplaplac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.aplaplac.springaplaplac.dto.CarreraDTO;
import com.duoc.aplaplac.springaplaplac.mapper.CarreraMapper;
import com.duoc.aplaplac.springaplaplac.repository.CarreraRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CarreraServiceImpl implements CarreraService {

    public static final String FIELD_WITH_ID = "Field with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";
    private final CarreraRepository carreraRepository;
    private final CarreraMapper carreraMapper;

    @Autowired
    public CarreraServiceImpl(CarreraRepository carreraRepository, CarreraMapper carreraMapper) {
        this.carreraRepository = carreraRepository;
        this.carreraMapper = carreraMapper;
    }

    @Override
    public List<CarreraDTO> listAll() {
        return carreraRepository.findAll()
                .stream()
                .map(this.carreraMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarreraDTO getById(Long id) {
        return carreraMapper.toDTO(carreraRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(FIELD_WITH_ID + id + DOES_NOT_EXIST)));
    }

    @Override
    public CarreraDTO save(CarreraDTO cancha) {
        boolean exists = cancha.getId() != null && carreraRepository.existsById(cancha.getId());
        if (exists) {
            throw new IllegalArgumentException(FIELD_WITH_ID + cancha.getId() + ALREADY_EXISTS);
        }
        return this.carreraMapper
                .toDTO(
                        carreraRepository.save(this.carreraMapper.fromDTO(cancha)));
    }

    @Override
    public CarreraDTO update(CarreraDTO cancha) {
        boolean exists = carreraRepository.existsById(cancha.getId());
        if (!exists) {
            throw new NoSuchElementException(FIELD_WITH_ID + cancha.getId() + DOES_NOT_EXIST);
        }
        return this.carreraMapper
                .toDTO(
                        carreraRepository.save(this.carreraMapper.fromDTO(cancha)));
    }

    @Override
    public void delete(Long id) {
        boolean exists = carreraRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(FIELD_WITH_ID + id + DOES_NOT_EXIST);
        }
        carreraRepository.deleteById(id);
    }

}
