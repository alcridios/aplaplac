package com.duoc.aplaplac.springaplaplac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.aplaplac.springaplaplac.dto.CarreraDTO;
import com.duoc.aplaplac.springaplaplac.service.CarreraService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/carreras")
public class CarreraController {

	private final CarreraService carreraService;

	@Autowired
	public CarreraController(CarreraService carreraService) {
		this.carreraService = carreraService;
	}

	@GetMapping("")
	public ResponseEntity<List<CarreraDTO>> listAll() {
		return ResponseEntity.ok(carreraService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarreraDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(carreraService.getById(id));
	}

	@PostMapping(value = "")
	public ResponseEntity<Long> saveCarrera(@RequestBody CarreraDTO carrera) {
		CarreraDTO savedCancha = carreraService.save(carrera);
		return new ResponseEntity<>(savedCancha.getId(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Long> updateCarrera(@PathVariable Long id, @RequestBody CarreraDTO carrera) {
		carrera.setId(id);
		CarreraDTO updatedCancha = carreraService.update(carrera);
		return ResponseEntity.ok(updatedCancha.getId());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
		carreraService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
