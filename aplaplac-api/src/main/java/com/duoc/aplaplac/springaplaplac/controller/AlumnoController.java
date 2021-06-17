package com.duoc.aplaplac.springaplaplac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.aplaplac.springaplaplac.dto.AlumnoDTO;
import com.duoc.aplaplac.springaplaplac.service.AlumnoService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }
    

    @GetMapping("")
    public ResponseEntity<List<AlumnoDTO>> listAll() {
        return ResponseEntity.ok(alumnoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.getById(id));
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> saveAlumno(@RequestBody AlumnoDTO alumno) {
        AlumnoDTO savedAlumno = alumnoService.save(alumno);
        return new ResponseEntity<>(savedAlumno.getId(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Long> updateAlumno(@PathVariable Long id, @RequestBody AlumnoDTO alumno) {
        alumno.setId(id);
        AlumnoDTO updatedJugador = alumnoService.update(alumno);
        return ResponseEntity.ok(updatedJugador.getId());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
    	alumnoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
	


