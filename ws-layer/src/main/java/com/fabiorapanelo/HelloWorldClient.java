package com.fabiorapanelo;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient{

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:8080/ws/hello?wsdl");

        QName qname = new QName("http://fabiorapanelo.com/", "HelloWorldImplService");

        Service service = Service.create(url, qname);

        HelloWorld hello = service.getPort(HelloWorld.class);

        System.out.println(hello.helloWorld("fabio"));

    }

}