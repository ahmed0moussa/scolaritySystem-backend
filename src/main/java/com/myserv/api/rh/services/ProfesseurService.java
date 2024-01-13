package com.myserv.api.rh.services;

import com.myserv.api.rh.model.Professeur;
import com.myserv.api.rh.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository professeurRepository;

    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    public Optional<Professeur> getProfesseurById(String id) {
        return professeurRepository.findById(id);
    }

    public Professeur createProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    public Professeur updateProfesseur(String id, Professeur updatedProfesseur) {
        if (professeurRepository.existsById(id)) {
            updatedProfesseur.setId(id);
            return professeurRepository.save(updatedProfesseur);
        }
        return null; // Handle not found case
    }

    public void deleteProfesseur(String id) {
        professeurRepository.deleteById(id);
    }
}
