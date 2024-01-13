package com.myserv.api.rh.services;

import com.myserv.api.rh.model.Salle;
import com.myserv.api.rh.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Optional<Salle> getSalleById(String id) {
        return salleRepository.findById(id);
    }

    public Salle createSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle updateSalle(String id, Salle updatedSalle) {
        if (salleRepository.existsById(id)) {
            updatedSalle.setId(id);
            return salleRepository.save(updatedSalle);
        }
        return null; // Handle not found case
    }

    public void deleteSalle(String id) {
        salleRepository.deleteById(id);
    }
}
