package com.myserv.api.rh.repository;

import com.myserv.api.rh.model.Classe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClasseRepository extends MongoRepository<Classe, String> {
}
