package com.duoc.aplaplac.springaplaplac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.aplaplac.springaplaplac.dto.ListadoLibrosDTO;
import com.duoc.aplaplac.springaplaplac.service.ListadoLibrosService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/listadocarreras")
public class ListadoLibrosController {

	private final ListadoLibrosService listadoLibrosService;

	@Autowired
	public ListadoLibrosController(ListadoLibrosService listadoLibrosService) {
		this.listadoLibrosService = listadoLibrosService;
	}

	@GetMapping("")
	public ResponseEntity<List<ListadoLibrosDTO>> listAll() {
		return ResponseEntity.ok(listadoLibrosService.listAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ListadoLibrosDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(listadoLibrosService.getById(id));
	}

	@PostMapping(value = "")
	public ResponseEntity<Long> saveListadoLibro(@RequestBody ListadoLibrosDTO listadolibros) {
		ListadoLibrosDTO savedListadoLibros = listadoLibrosService.save(listadolibros);
		return new ResponseEntity<>(savedListadoLibros.getId(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Long> updateListadoLibro(@PathVariable Long id, @RequestBody ListadoLibrosDTO listadoLibro) {
		listadoLibro.setId(id);
		ListadoLibrosDTO updatedListadoLibro = listadoLibrosService.update(listadoLibro);
		return ResponseEntity.ok(updatedListadoLibro.getId());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteListadoLibro(@PathVariable Long id) {
		listadoLibrosService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
