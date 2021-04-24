package com.apipothi.amazon.dao;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apipothi.amazon.model.AmazonProduct;

public interface AmazonDAO extends MongoRepository<AmazonProduct, Integer> {

}
