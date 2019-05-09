package com.stackz.elastic.repository;

import com.stackz.elastic.model.Doctor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DoctorElasticRepository extends ElasticsearchRepository<Doctor, Long> {

    List<Doctor> findByName(String text);
}
