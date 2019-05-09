package com.stackz.elastic.repository;

import com.stackz.elastic.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientMongoRepository extends MongoRepository<Patient, Long> {
}
