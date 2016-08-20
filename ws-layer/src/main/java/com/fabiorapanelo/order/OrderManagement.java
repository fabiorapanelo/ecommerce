package com.fabiorapanelo.order;

import javax.jws.WebService;

@WebService(endpointInterface = "com.fabiorapanelo.order.IOrderManagement")
public class OrderManagement implements IOrderManagement {

	public String checkStatus(Long orderId) {
		return "Not implemented";
	}	

}
