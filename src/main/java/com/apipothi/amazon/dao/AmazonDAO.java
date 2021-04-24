package com.apipothi.amazon.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apipothi.amazon.model.AmazonProduct;

public interface AmazonDAO extends MongoRepository<AmazonProduct, Integer> {

}
