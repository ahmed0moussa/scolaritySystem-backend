package com.myserv.api.rh.services;

import com.myserv.api.rh.model.Classe;
import com.myserv.api.rh.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;

    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Optional<Classe> getClasseById(String id) {
        return classeRepository.findById(id);
    }

    public Classe createClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    public Classe updateClasse(String id, Classe updatedClasse) {
        if (classeRepository.existsById(id)) {
            updatedClasse.setId(id);
            return classeRepository.save(updatedClasse);
        }
        return null; // Handle not found case
    }

    public void deleteClasse(String id) {
        classeRepository.deleteById(id);
    }
}
