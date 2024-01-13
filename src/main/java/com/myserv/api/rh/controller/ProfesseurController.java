package com.myserv.api.rh.controller;

import com.myserv.api.rh.model.Professeur;
import com.myserv.api.rh.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return professeurService.getAllProfesseurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getProfesseurById(@PathVariable String id) {
        Optional<Professeur> professeur = professeurService.getProfesseurById(id);
        return professeur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Professeur> createProfesseur(@RequestBody Professeur professeur) {
        Professeur createdProfesseur = professeurService.createProfesseur(professeur);
        return new ResponseEntity<>(createdProfesseur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professeur> updateProfesseur(@PathVariable String id, @RequestBody Professeur professeur) {
        Professeur updatedProfesseur = professeurService.updateProfesseur(id, professeur);
        return updatedProfesseur != null ?
                new ResponseEntity<>(updatedProfesseur, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesseur(@PathVariable String id) {
        professeurService.deleteProfesseur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

