package com.stackz.elastic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "medicine", type = "med", shards = 1)
@Data
public class Medicine {

    @Id
    private Long id;

    private String medicineName;
    private Double price;
    private String dosage;
}
