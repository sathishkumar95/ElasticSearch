package com.stackz.elastic.resource;

import com.stackz.elastic.model.Doctor;
import com.stackz.elastic.model.Users;
import com.stackz.elastic.repository.DoctorElasticRepository;
import com.stackz.elastic.repository.DoctorMongoRepository;
import com.stackz.elastic.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    DoctorElasticRepository doctorElasticRepository;

    @Autowired
    DoctorMongoRepository doctorMongoRepository;


    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text) {
        return usersRepository.findByName(text);
    }

    @PreAuthorize("@secured.hasPermission(authentication,'view')")
    @GetMapping(value = "/all")
    public List<Users> searchAll() {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }

    @PreAuthorize("@secured.hasPermission(authentication,'view')")
    @GetMapping(value = "/alldoc")
    public List<Doctor> searchAlldoc() {
        List<Doctor> doctorList = new ArrayList<>();
        Iterable<Doctor> doctors = doctorElasticRepository.findAll();
        doctors.forEach(doctorList::add);
        return doctorList;
    }

    @PreAuthorize("@secured.hasPermission(authentication,'view')")
    @GetMapping(value = "/edoc")
    public List<Doctor> searchdocByName(@RequestParam("docName") String docName) {
        Long startTime = System.nanoTime();
        List<Doctor> doctorList = doctorElasticRepository.findByName(docName);
        Long endTime = System.nanoTime();
        Long duration = (endTime - startTime)/1000000;
        log.info("Elastic search fetch time : " + duration.toString() + " micro seconds");
        return doctorList;
    }

    @PreAuthorize("@secured.hasPermission(authentication,'view')")
    @GetMapping(value = "/mdoc")
    public List<Doctor> searchdocByNameInMongo(@RequestParam("docName") String docName) {
        Long startTime = System.nanoTime();
        List<Doctor> doctorList = doctorMongoRepository.findByName(docName);
        Long endTime = System.nanoTime();
        Long duration = (endTime - startTime)/1000000;
        log.info("Indexed Mongo fetch time : " + duration.toString() + " micro seconds");
        return doctorList;
    }



    @GetMapping("/index")
    public String index () {
        return "It works";
    }


}
