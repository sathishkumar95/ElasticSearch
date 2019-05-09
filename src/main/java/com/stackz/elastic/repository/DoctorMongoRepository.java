package com.stackz.elastic.repository;

import com.stackz.elastic.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorMongoRepository extends MongoRepository<Doctor, String> {

    List<Doctor> findByName(String text);

}
