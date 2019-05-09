package com.stackz.elastic.load;

import com.stackz.elastic.DataLoaders.DataLoader;
import com.stackz.elastic.model.Doctor;
import com.stackz.elastic.repository.DoctorElasticRepository;
import com.stackz.elastic.repository.DoctorMongoRepository;
import com.stackz.elastic.repository.UserMongoRepository;
import com.stackz.elastic.repository.UsersRepository;
import com.stackz.elastic.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Loaders {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Autowired
    DataLoader dataLoader;

    @Autowired
    DoctorElasticRepository doctorElasticRepository;

    @Autowired
    DoctorMongoRepository doctorMongoRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){

        log.info("Deleting all the indexes ......... [ ok ]");
        usersRepository.deleteAll();
        userMongoRepository.deleteAll();
        doctorElasticRepository.deleteAll();
        log.info("Completed Deleting of all the indexes ......... [ ok ]");


        operations.putMapping(Users.class);
        operations.putMapping(Doctor.class);
        log.info("Indexing Elastic serach .................. [ ok ]");
        loadDoctors();
//        usersRepository.save(getData());
        log.info("Indexing Completed ................. [ ok ]");
//        userMongoRepository.save(getData());
    }

    private List<Users> getData() {
        List<Users> userses = new ArrayList<>();
//        userses.add(new Users("Ajay",123L));
//        userses.add(new Users("Jaga",456L));
//        userses.add(new Users("Thiru",789L));
        return userses;
    }

    private void loadDoctors () {
        List<Doctor> doctorList = dataLoader.getDoctorData();
        List<Doctor> onlyThousandDoctors = doctorList.subList(0, 1000);
        List<Doctor> firstHalfDoctors = doctorList.subList(0, doctorList.size()/2 - 1);
//        List<Doctor> secondHalfDoctors = doctorList.subList(doctorList.size()/2 , doctorList.size() - 1 );
        onlyThousandDoctors.forEach(doctorElasticRepository::save);
        doctorMongoRepository.save(firstHalfDoctors);
    }
}
