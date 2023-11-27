package com.myserv.api.rh.repository;

import com.myserv.api.rh.model.MenuItem;
import com.myserv.api.rh.model.SubItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubItemsRepository extends MongoRepository<SubItems, String> {
}
