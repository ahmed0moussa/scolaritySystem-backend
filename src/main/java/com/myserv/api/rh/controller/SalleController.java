package com.myserv.api.rh.controller;

import com.myserv.api.rh.model.Salle;
import com.myserv.api.rh.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    @Autowired
    private SalleService salleService;

    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable String id) {
        Optional<Salle> salle = salleService.getSalleById(id);
        return salle.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) {
        Salle createdSalle = salleService.createSalle(salle);
        return new ResponseEntity<>(createdSalle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salle> updateSalle(@PathVariable String id, @RequestBody Salle salle) {
        Salle updatedSalle = salleService.updateSalle(id, salle);
        return updatedSalle != null ?
                new ResponseEntity<>(updatedSalle, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable String id) {
        salleService.deleteSalle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

