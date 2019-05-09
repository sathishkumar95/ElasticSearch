package com.stackz.elastic.resource;


import com.stackz.elastic.model.MedicalRecord;
import com.stackz.elastic.repository.MedicalRecordElasticRepository;
import com.stackz.elastic.repository.MedicalRecordMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocResource {

    @Autowired
    MedicalRecordElasticRepository medicalRecordElasticRepository;

    @Autowired
    MedicalRecordMongoRepo medicalRecordMongoRepo;


    @PreAuthorize("@secured.hasPermission(authentication,'doc')")
    @GetMapping("/addMedical")
    public Long addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordElasticRepository.save(medicalRecord);
        return medicalRecordMongoRepo.save(medicalRecord).getId();
    }
}
