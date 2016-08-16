package com.fabiorapanelo.order;

import java.net.URI;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Session;

import com.fabiorapanelo.SessionFactorySingleton;

@Path("/order")
public class OrderResource {
	
	@Context
	private ServletContext context;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(@Context UriInfo info, Order order) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		
		session.save(order);
		session.getTransaction().commit();
		session.close();

		URI absoluteURI = info.getBaseUriBuilder().path("/order/" + order.getId()).build();

		return Response.created(absoluteURI).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrder(@PathParam("id") int id) {

		
		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		
		TypedQuery<Order> query = session.createQuery("from Order where ORDER_ID = :id ", Order.class);
		query.setParameter("id", id);
		
		Order order = null;
		try{
			order = query.getSingleResult();
			return Response.ok(order).build();
		}catch (NoResultException nre){
			return Response.status(Status.NOT_FOUND).build();
		}finally{
			session.close();
		}
		
	}
	
	@Path("/{id}/item")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrderItem(@PathParam("id") int id, OrderItem orderItem){
		
		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		
		TypedQuery<Order> query = session.createQuery("from Order where ORDER_ID = :id ", Order.class);
		query.setParameter("id", id);
		
		Order order = null;
		try{
			order = query.getSingleResult();
			order.getItems().add(orderItem);
			
			session.save(order);
			session.getTransaction().commit();
			session.close();
			
			return Response.ok(order).build();
		}catch (NoResultException nre){
			return Response.status(Status.NOT_FOUND).build();
		}finally{
			session.close();
		}
	}
	

}
