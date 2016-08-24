package com.fabiorapanelo.helloworld;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/ws/hello", new HelloWorldImpl());
	}
}
