package com.stackz.elastic.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class OPD {

    Doctor doctor;
    List<Patient> patients;
}
