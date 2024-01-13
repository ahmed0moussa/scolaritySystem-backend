package com.myserv.api.rh.repository;

import com.myserv.api.rh.model.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
}
