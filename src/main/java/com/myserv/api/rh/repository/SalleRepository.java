package com.myserv.api.rh.repository;

import com.myserv.api.rh.model.Roles;
import com.myserv.api.rh.model.Salle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalleRepository extends MongoRepository<Salle, String> {
}
