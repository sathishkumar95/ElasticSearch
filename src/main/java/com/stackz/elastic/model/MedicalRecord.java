package com.stackz.elastic.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "medicine", type = "medicalRecord", shards = 1)
@Data
public class MedicalRecord {

    @Id
    private Long id;

    private Long patientId;

    String disease;
    List<String> symptoms;
    List<Medicine> medicines;
    boolean isAdmitted;
    String roomNo;
}
