package com.stackz.elastic.resource;

import com.stackz.elastic.model.Doctor;
import com.stackz.elastic.model.Patient;
import com.stackz.elastic.repository.DoctorElasticRepository;
import com.stackz.elastic.repository.DoctorMongoRepository;
import com.stackz.elastic.repository.PatientMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class AdminResource {

    private final @NotNull  DoctorElasticRepository doctorElasticRepository;

    private final @NotNull  DoctorMongoRepository doctorMongoRepository;

    private final @NotNull  PatientMongoRepository patientMongoRepository;


    @PreAuthorize("@secured.hasPermission(authentication,'admin')")
    @GetMapping("/addDoc")
    public Long addDoc(@RequestBody Doctor doctor) {
        doctorElasticRepository.save(doctor);
        return doctorMongoRepository.save(doctor).getId();
    }

    @PreAuthorize("@secured.hasPermission(authentication,'admin')")
    @GetMapping("/addPat")
    public String addPatient(@RequestBody Patient patient) {
        return patientMongoRepository.save(patient).getName();
    }




}
