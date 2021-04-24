package com.apipothi.amazon.service;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import java.util.List;

import com.apipothi.amazon.request.AmazonRequest;
import com.apipothi.amazon.to.AmazonTO;

public interface AmazonService {

	public String addProductFromManufacturer(List<AmazonTO> manufacturerProductDetails);
	public List<AmazonTO> getByIDProductInAmazon(int id);
	public List<AmazonTO> getAllProductInAmazon();
	public String addProductInAmazon(List<AmazonRequest> request);
	public String updateProductInAmazon(AmazonRequest request);
	public boolean deleteProductInAmazon(int id);
	
}
