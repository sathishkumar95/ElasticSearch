package com.stackz.elastic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "users", type = "users", shards = 1)
@Data
public class Users {

    @Id
    private Long id;

    private String name;


    public Users(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Users() {
    }
}
