package com.fabiorapanelo.helloworld;

import javax.jws.WebService;

@WebService(endpointInterface = "com.fabiorapanelo.helloworld.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	
	public String helloWorld(String name){
		return "Hello World " + name;
	}

}
