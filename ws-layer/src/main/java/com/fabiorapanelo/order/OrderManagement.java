package com.fabiorapanelo.order;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@HandlerChain(file="handler-chain.xml")
public class OrderManagement {

	@WebMethod
	public String checkStatus(Long orderId) {
		System.out.println("OrderManagement.checkStatus: Order#" + orderId);
		return "Created";
	}

}
