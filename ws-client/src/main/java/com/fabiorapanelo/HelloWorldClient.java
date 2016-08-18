package com.fabiorapanelo;

public class HelloWorldClient{

	public static void main(String[] args) {

		HelloWorldImplService helloService = new HelloWorldImplService();
		HelloWorld hello = helloService.getHelloWorldImplPort();

		System.out.println(hello.helloWorld("fabio"));

    }

}