package com.stackz.elastic.repository;

import com.stackz.elastic.model.Medicine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MedicineElasticRepository extends ElasticsearchRepository<Medicine, Long> {

    List<Medicine> findByMedicineName(String name);
}
