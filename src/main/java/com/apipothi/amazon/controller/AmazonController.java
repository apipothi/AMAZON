package com.apipothi.amazon.controller;
/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Web Site  : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apipothi.amazon.feign.AProductManufacturerProxy;
import com.apipothi.amazon.request.AmazonRequest;
import com.apipothi.amazon.response.AmazonResponse;
import com.apipothi.amazon.service.AmazonService;
import com.apipothi.amazon.to.AmazonTO;

@RestController
public class AmazonController {
	private final static Logger logger = LoggerFactory.getLogger(AmazonController.class);

	@Value("${server.port}")
	int port;

	@Value("${spring.application.name}")
	String appname;

	@Autowired
	AProductManufacturerProxy proxy;

	@Autowired
	AmazonService service;

	/*
	 * Take All the product from PRODUCT MANUFACTURER and save it in to AMAZON DB
	 */
	@GetMapping("/addAllProductFromManufacturer")
	public AmazonResponse addAllProductFromManufacturer() {
		String statusMsg = "";
		AmazonResponse myresponse = new AmazonResponse();
		AmazonResponse response = proxy.getuserDetailsFromUserService();
		logger.info("Response form Manufacturer proxy {}" + response);
		List<AmazonTO> manufacturerProductDetails = response.getProductDetails();
		try {
			statusMsg = service.addProductFromManufacturer(manufacturerProductDetails);
			myresponse.setMessage(statusMsg);
			myresponse.setPort(response.getPort());
			myresponse.setAppname(response.getAppname());
			myresponse.setStatuscode(response.getStatuscode());

		} catch (Exception e) {
			statusMsg = "Exception Occured during data insertion" + e;
			myresponse.setMessage(statusMsg);
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(400);
			logger.error("-Exception{}", e);
		}
		logger.info("-Response from Product Manufacturer-addAllProductFromManufacturer- {}", myresponse);
		return myresponse;
	}

	/*
	 * Get product details by ID from AMAZON DB
	 */
	@GetMapping("/getProductByIDInAmazon/{id}")
	public AmazonResponse getProductByIDInAmazon(@PathVariable int id) {
		logger.info("***START---getProductByIDInAmazon :: id{}" + id);
		AmazonResponse myresponse = new AmazonResponse();
		String statusMsg = "";
		try {
			List<AmazonTO> amazonProductList = service.getByIDProductInAmazon(id);

			myresponse.setProductDetails(amazonProductList);
			myresponse.setMessage("SUCCESS");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(200);

		} catch (Exception e) {
			statusMsg = "Exception Occured while getting the Amazon data" + e;
			myresponse.setMessage(statusMsg);
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(400);
			logger.error("-Exception{}", e);
		}
		logger.info("****END-Response getProductByIDInAmazon{}", myresponse);
		return myresponse;
	}

	/*
	 * Get All the product details from AMAZON DB
	 */
	@GetMapping("/getAmazonProduct")
	public AmazonResponse getAmazonProduct() {
		logger.info("***START---getAmazonProduct()");
		AmazonResponse myresponse = new AmazonResponse();
		try {
			List<AmazonTO> listOfflipkartProduct = service.getAllProductInAmazon();
			myresponse.setProductDetails(listOfflipkartProduct);
			myresponse.setMessage("SUCCESS");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(200);

		} catch (Exception e) {
			myresponse.setProductDetails(null);
			myresponse.setMessage("Exception Occured During getting the Amazon data");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(400);
		}
		logger.info("***END---getAmazonProduct() response{}", myresponse);
		return myresponse;
	}

	/*
	 * Add All the product details in to AMAZON DB
	 */
	@PostMapping("/addProductInAmazon")
	public AmazonResponse addProductInAmazon(@RequestBody List<AmazonRequest> request) {
		logger.info("***START---addProductInAmazon()" + request);
		String statusMsg = "";
		AmazonResponse myresponse = new AmazonResponse();
		try {
			statusMsg = service.addProductInAmazon(request);
			myresponse.setMessage(statusMsg);
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(200);

		} catch (Exception e) {
			myresponse.setProductDetails(null);
			myresponse.setMessage("Exception Occured During adding the data to Amazon");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(400);
		}
		logger.info("***END---addProductInAmazon() response{}", myresponse);
		return myresponse;
	}

	/*
	 * Update the product details by ID in to AMAZON DB
	 */
	@PutMapping("/updateProductInAmazon/{id}")
	public AmazonResponse updateProductInAmazon(@PathVariable int id, AmazonRequest request) {
		logger.info("***START---updateProductInAmazon(){} id{} request{}", id, request);
		// Update the data in Amazon DB
		AmazonResponse myresponse = new AmazonResponse();
		String statusMsg = "";
		try {
			request.setId(id);
			statusMsg = service.updateProductInAmazon(request);
			myresponse.setMessage(statusMsg);
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(200);
		} catch (Exception e) {
			myresponse.setMessage("Exception Occured During updating the Amazon data");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(400);
		}
		logger.info("***END---updateProductInAmazon() response{}", myresponse);
		return myresponse;
	}

	/*
	 * Delete the product details by ID in to AMAZON DB
	 */
	@DeleteMapping("/deleteProductInAmazon/{id}")
	public AmazonResponse deleteProductInAmazon(@PathVariable int id) {
		// Delete the data in Amazon DB
		boolean isSuccess;
		AmazonResponse myresponse = new AmazonResponse();
		isSuccess = service.deleteProductInAmazon(id);
		if (isSuccess) {
			myresponse.setMessage("Data Deleted sucessfully");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(200);
		} else {
			myresponse.setMessage("Exception Occured while deleting the Data");
			myresponse.setPort(port);
			myresponse.setAppname(appname);
			myresponse.setStatuscode(200);
		}
		return myresponse;
	}

}
