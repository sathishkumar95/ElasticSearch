package com.stackz.elastic.repository;

import com.stackz.elastic.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<Users, String> {
}
