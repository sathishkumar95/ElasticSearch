package com.stackz.elastic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@Document(indexName = "doctors", type = "doc", shards = 1)
public class Doctor{

    @Id
    Long id;

    String name;
    String type;
    String qualification;


}
