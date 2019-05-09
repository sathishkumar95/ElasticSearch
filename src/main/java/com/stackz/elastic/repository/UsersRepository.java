package com.stackz.elastic.repository;

import com.stackz.elastic.model.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UsersRepository extends ElasticsearchRepository<Users, Long> {

    List<Users> findByName(String text);
}
