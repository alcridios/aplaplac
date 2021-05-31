package com.baufest.tennis.springtennis.controller;

import com.baufest.tennis.springtennis.dto.TorneoDTO;
import com.baufest.tennis.springtennis.enums.ModoJugador;
import com.baufest.tennis.springtennis.service.TorneoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    @Autowired
    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping("")
    public ResponseEntity<List<TorneoDTO>> listAll() {
        return ResponseEntity.ok(torneoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TorneoDTO> getById(@PathVariable Long id) {
    	TorneoDTO partido = torneoService.getById(id);
        return ResponseEntity.ok(partido);
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> saveTorneo(@RequestBody TorneoDTO torneo) {
    	TorneoDTO savedTorneo = torneoService.save(torneo);
        return new ResponseEntity<>(
        		savedTorneo.getId(),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Long> updateTorneo(@PathVariable Long id, @RequestBody TorneoDTO torneo) {
    	torneo.setId(id);
    	TorneoDTO updatedTorneo = torneoService.update(torneo);
        return ResponseEntity.ok(updatedTorneo.getId());
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable Long id) {
        ResponseEntity<Void> response;
        torneoService.delete(id);
        response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }
}
