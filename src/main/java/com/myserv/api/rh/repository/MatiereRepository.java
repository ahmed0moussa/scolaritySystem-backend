package com.myserv.api.rh.repository;

import com.myserv.api.rh.model.Matiere;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatiereRepository extends MongoRepository<Matiere, String> {
}
