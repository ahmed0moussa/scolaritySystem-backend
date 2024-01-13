package com.myserv.api.rh.controller;

import com.myserv.api.rh.model.Matiere;
import com.myserv.api.rh.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable String id) {
        Optional<Matiere> matiere = matiereService.getMatiereById(id);
        return matiere.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Matiere> createMatiere(@RequestBody Matiere matiere) {
        Matiere createdMatiere = matiereService.createMatiere(matiere);
        return new ResponseEntity<>(createdMatiere, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable String id, @RequestBody Matiere matiere) {
        Matiere updatedMatiere = matiereService.updateMatiere(id, matiere);
        return updatedMatiere != null ?
                new ResponseEntity<>(updatedMatiere, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable String id) {
        matiereService.deleteMatiere(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

