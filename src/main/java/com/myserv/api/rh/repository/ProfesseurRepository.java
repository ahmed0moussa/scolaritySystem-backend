package com.myserv.api.rh.repository;

import com.myserv.api.rh.model.Professeur;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfesseurRepository extends MongoRepository<Professeur, String> {
}
