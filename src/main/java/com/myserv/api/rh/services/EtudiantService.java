package com.myserv.api.rh.services;

import com.myserv.api.rh.model.Etudiant;
import com.myserv.api.rh.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(String id) {
        return etudiantRepository.findById(id);
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(String id, Etudiant updatedEtudiant) {
        if (etudiantRepository.existsById(id)) {
            updatedEtudiant.setId(id);
            return etudiantRepository.save(updatedEtudiant);
        }
        return null; // Handle not found case
    }

    public void deleteEtudiant(String id) {
        etudiantRepository.deleteById(id);
    }
}
