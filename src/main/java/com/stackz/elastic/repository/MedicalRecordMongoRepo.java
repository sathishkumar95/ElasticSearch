package com.stackz.elastic.repository;

import com.stackz.elastic.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRecordMongoRepo extends MongoRepository<MedicalRecord, Long> {
}
