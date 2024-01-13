package com.myserv.api.rh.services;

import com.myserv.api.rh.model.Matiere;
import com.myserv.api.rh.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {
    @Autowired
    private MatiereRepository matiereRepository;

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Optional<Matiere> getMatiereById(String id) {
        return matiereRepository.findById(id);
    }

    public Matiere createMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Matiere updateMatiere(String id, Matiere updatedMatiere) {
        if (matiereRepository.existsById(id)) {
            updatedMatiere.setId(id);
            return matiereRepository.save(updatedMatiere);
        }
        return null; // Handle not found case
    }

    public void deleteMatiere(String id) {
        matiereRepository.deleteById(id);
    }
}
