package com.apipothi.amazon.feign;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.apipothi.amazon.response.AmazonResponse;

//@FeignClient(name = "productmanufacturer", url = "localhost:8080")
@FeignClient(name = "apigateway-service")
@RibbonClient(name = "productmanufacturer")
public interface AProductManufacturerProxy {
	@GetMapping("/productmanufacturer/getallproduct")
	public AmazonResponse getuserDetailsFromUserService();
	
}

