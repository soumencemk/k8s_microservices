package com.soumen.kubemongodemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Soumen Karmakar
 * @Date 13/04/2021
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {}
