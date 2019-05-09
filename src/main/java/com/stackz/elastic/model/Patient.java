package com.stackz.elastic.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Patient {

    double yearOfBirth;

    String gender;

    String name;

}
