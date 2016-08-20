package com.fabiorapanelo.order;

import javax.xml.ws.Endpoint;

public class OrderManagementPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/ws-layer/OrderManagement", new OrderManagement());
	}

}
