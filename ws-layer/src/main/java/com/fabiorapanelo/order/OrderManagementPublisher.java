package com.fabiorapanelo.order;

import javax.xml.ws.Endpoint;

public class OrderManagementPublisher {

	public static void main(String[] args) {
		String endpoint = "http://localhost:8080/ws-layer/OrderManagement";
		Endpoint.publish(endpoint, new OrderManagement());
		System.out.println("Order Managemenet Service has started: " + endpoint);
		
	}

}
