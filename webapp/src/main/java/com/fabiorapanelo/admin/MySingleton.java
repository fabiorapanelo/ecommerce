package com.fabiorapanelo.admin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class MySingleton {
	
	private String name;
	public MySingleton(){
		name = "Fabio";
		System.out.println("ma oi");
	}
	
	public String getName(){
		return name;
	}

}
