package com.apipothi.amazon.service;

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
	public List<AmazonTO> getProductByProductID(String id);
	
}
