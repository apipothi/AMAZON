package com.apipothi.amazon.service.impl;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apipothi.amazon.dao.AmazonDAO;
import com.apipothi.amazon.model.AmazonProduct;
import com.apipothi.amazon.request.AmazonRequest;
import com.apipothi.amazon.service.AmazonService;
import com.apipothi.amazon.to.AmazonTO;

@Component
public class AmazonServiceImpl implements AmazonService {

	private final static Logger logger = LoggerFactory.getLogger(AmazonServiceImpl.class);

	@Autowired
	AmazonDAO dao;

	@Override
	public String addProductFromManufacturer(List<AmazonTO> manufacturerProductDetails) {
		logger.info("START- addProductFromManufacturer{}" + manufacturerProductDetails);
		String statusmsg = "";
		try {
			for (AmazonTO amazonTO : manufacturerProductDetails) {
				AmazonProduct amazonProduct = new AmazonProduct();
				amazonProduct.setId(amazonTO.getId());
				amazonProduct.setProductid(amazonTO.getWproductid());
				amazonProduct.setProductname(amazonTO.getWproductname());
				//amazonProduct.setProductprice(amazonTO.getWproductprice());
				long costPrice = amazonTO.getWproductprice();
				long sellingPrice = amazonSellingPrice(costPrice);
				amazonProduct.setProductprice(sellingPrice);
				dao.save(amazonProduct);
			}
			statusmsg = "Data Inserted successfully";
		} catch (Exception e) {
			statusmsg = "Exception occured during data insertion";
			logger.error("-Exception{}", e);
		}
		return statusmsg;

	}

	@Override
	public List<AmazonTO> getByIDProductInAmazon(int id) {
		logger.info("START- getByIDProductInAmazon{} :: id{}" + id);
		AmazonTO to = new AmazonTO();
		List<AmazonTO> amazonProductList = new ArrayList<AmazonTO>();
		String statusmsg = "";
		try {
			Optional<AmazonProduct> amazonProduct = dao.findById(id);
			logger.info("***getByIDProductInAmazon ***" + amazonProduct);
			to.setId(amazonProduct.get().getId());
			to.setWproductid(amazonProduct.get().getProductid());
			to.setWproductname(amazonProduct.get().getProductname());
			to.setWproductprice(amazonProduct.get().getProductprice());
			amazonProductList.add(to);
		} catch (Exception e) {
			statusmsg = "Exception occured while fetching the data by ID";
			logger.error("-Exception{}", statusmsg);
		}
		logger.info("***END--getByIDProductInAmazon()--RESPONSE{} " + amazonProductList);
		return amazonProductList;
	}

	@Override
	public List<AmazonTO> getAllProductInAmazon() {
		logger.info("START- getAllProductInAmazon{}");
		List<AmazonTO> listOfAmazonProduct = new ArrayList<AmazonTO>();
		String statusmsg = "";
		try {
			List<AmazonProduct> amazonProductList = dao.findAll();
			for (AmazonProduct amazonProduct : amazonProductList) {
				AmazonTO to = new AmazonTO();
				to.setId(amazonProduct.getId());
				to.setWproductid(amazonProduct.getProductid());
				to.setWproductname(amazonProduct.getProductname());
				to.setWproductprice(amazonProduct.getProductprice());
				listOfAmazonProduct.add(to);
			}

		} catch (Exception e) {
			statusmsg = "Exception occured during getAllProductInAmazon";
			logger.error("-Exception{}", statusmsg);
		}
		logger.info("***END--getAllProductInAmazon()" + listOfAmazonProduct);
		return listOfAmazonProduct;
	}

	@Override
	public String addProductInAmazon(List<AmazonRequest> request) {
		logger.info("***START--addProductInAmazon()-- Request{}" + request);
		AmazonProduct product = new AmazonProduct();
		List<AmazonProduct> productList = new ArrayList<AmazonProduct>();
		String statusMsg = "";
		for (AmazonRequest amazonRequest : request) {
			product.setId(amazonRequest.getId());
			product.setProductid(amazonRequest.getWproductid());
			product.setProductname(amazonRequest.getWproductname());
			product.setProductprice(amazonRequest.getWproductprice());
			productList.add(product);
		}
		try {
			dao.insert(productList);
			statusMsg = "Data Inserted Successfully";
		} catch (Exception e) {
			statusMsg = "Exception occured while Inserting the Data";
			logger.error("-Exception{}", statusMsg);
		}
		logger.info("***END--addProductInAmazon()" + statusMsg);
		return statusMsg;
	}

	@Override
	public String updateProductInAmazon(AmazonRequest request) {
		logger.info("***START--updateProductInAmazon()-- Request{}" + request);
		String statusMsg = "";
		AmazonProduct product = new AmazonProduct();
		product.setId(request.getId());
		product.setProductid(request.getWproductid());
		product.setProductname(request.getWproductname());
		product.setProductprice(request.getWproductprice());
		try {
			dao.save(product);
			statusMsg = "Data Updated Successfully";
		} catch (Exception e) {
			statusMsg = "Exception occured while Updating the Data";
			logger.error("-Exception{}", statusMsg);
		}
		logger.info("***END--updateProductInAmazon()" + statusMsg);
		return statusMsg;
	}

	@Override
	public boolean deleteProductInAmazon(int id) {
		logger.info("***START--deleteProductInAmazon()-- id{}" + id);
		boolean success = false;
		dao.deleteById(id);
		success = true;
		logger.info("***END--deleteProductInAmazon()--");
		return success;
	}

	private long amazonSellingPrice(long costPrice) {

		long profit = 0L;
		long sellingPrice = 0L;

		if (costPrice <= 100) {
			profit = ((costPrice * 55) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "60%" + " PROFIT " + profit + " SELLING PRICE" + sellingPrice);
			return sellingPrice;

		} else if (costPrice > 100 && costPrice <= 500) {
			profit = ((costPrice * 48) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "50%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
			return sellingPrice;

		} else if (costPrice > 500 && costPrice <= 1000) {
			profit = ((costPrice * 43) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "40%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
			return sellingPrice;

		} else if (costPrice > 1000 && costPrice <= 2000) {
			profit = ((costPrice * 28) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "30%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
			return sellingPrice;

		} else if (costPrice > 2000 && costPrice <= 4000) {
			profit = ((costPrice * 21) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "20%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
			return sellingPrice;

		} else if (costPrice > 4000 && costPrice <= 7000) {
			profit = ((costPrice * 12) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "10%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
			return sellingPrice;

		} else{
			profit = ((costPrice * 5) / 100);
			sellingPrice = costPrice + profit;
			System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "05%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
			return sellingPrice;

		}
	}
}
