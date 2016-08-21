package com.fabiorapanelo.order;

public class OrderManagementConsumer {
	
	public static void main(String [] args){
		OrderManagementService orderService = new OrderManagementService();
		OrderManagement orderManagement = orderService.getOrderManagementPort();
		
		String status = orderManagement.checkStatus(1L);
		System.out.println(status);
	}
}