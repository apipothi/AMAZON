package com.apipothi.amazon;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//http://localhost:8082/swagger-ui.html
	
@SpringBootApplication
@EnableFeignClients("com.apipothi.amazon")
@EnableDiscoveryClient
public class AmazonApplication {
	private final static Logger logger = LoggerFactory.getLogger(AmazonApplication.class);
	public static void main(String[] args) {
		logger.info("***AmazonApplication-START");
		SpringApplication.run(AmazonApplication.class, args);
		logger.info("***AmazonApplication-END");
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
