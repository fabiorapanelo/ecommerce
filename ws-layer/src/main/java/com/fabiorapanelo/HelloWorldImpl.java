package com.fabiorapanelo;

import javax.jws.WebService;

@WebService(endpointInterface = "com.fabiorapanelo.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	
	public String helloWorld(String name){
		return "Hello World " + name;
	}

}
