package com.stackz.elastic.repository;

import com.stackz.elastic.model.MedicalRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MedicalRecordElasticRepository extends ElasticsearchRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByDisease(String disease);
}
