package com.duoc.aplaplac.springaplaplac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.aplaplac.springaplaplac.dto.DocenteDTO;
import com.duoc.aplaplac.springaplaplac.service.DocenteService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/docentes")
public class DocenteController {

    private final DocenteService docenteService;

    @Autowired
    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }
    

    @GetMapping("")
    public ResponseEntity<List<DocenteDTO>> listAll() {
        return ResponseEntity.ok(docenteService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.getById(id));
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> saveDocente(@RequestBody DocenteDTO docente) {
    	DocenteDTO savedDocente = docenteService.save(docente);
        return new ResponseEntity<>(savedDocente.getId(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Long> updateDocente(@PathVariable Long id, @RequestBody DocenteDTO docente) {
        docente.setId(id);
        DocenteDTO updatedDocente = docenteService.update(docente);
        return ResponseEntity.ok(updatedDocente.getId());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
    	docenteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
	


