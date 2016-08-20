package com.fabiorapanelo.order;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IOrderManagement {
	
	@WebMethod
	String checkStatus(Long orderId);

}
