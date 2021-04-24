package com.apipothi.amazon.response;

import java.util.List;

import com.apipothi.amazon.to.AmazonTO;

/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * WEB       : http://apipothi.com/
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 1.8
*/
public class AmazonResponse {

	private int statuscode;
	private String message;
	private int port;
	private String appname;
	private List<AmazonTO> productDetails;

	public AmazonResponse() {
	}

	public AmazonResponse(int statuscode, String message, int port, String appname, List<AmazonTO> productDetails) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.port = port;
		this.appname = appname;
		this.productDetails = productDetails;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public List<AmazonTO> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<AmazonTO> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return "AmazonResponse [statuscode=" + statuscode + ", message=" + message + ", port=" + port + ", appname="
				+ appname + ", productDetails=" + productDetails + "]";
	}

}
