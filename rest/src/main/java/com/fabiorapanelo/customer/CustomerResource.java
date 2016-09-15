package com.fabiorapanelo.customer;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
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

import com.fabiorapanelo.HibernateSessionFactory;

@Path("/customer")
public class CustomerResource {

	@Context
	private ServletContext context;
	
	@Inject
	private HibernateSessionFactory sessionFactory;

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCustomer(@PathParam("id") int id) {

		
		Session session = sessionFactory.openSessionAndBeginTransaction();
		
		TypedQuery<Customer> query = session.createQuery("from Customer where CUSTOMER_ID = :id ", Customer.class);
		query.setParameter("id", id);
		
		Customer customer = null;
		try{
			customer = query.getSingleResult();
			return Response.ok(customer).build();
		}catch (NoResultException nre){
			return Response.status(Status.NOT_FOUND).build();
		}finally{
			session.close();
		}
		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(@Context UriInfo info, Customer customer) {

		Session session = sessionFactory.openSessionAndBeginTransaction();
		
		session.save(customer);
		session.getTransaction().commit();
		session.close();

		URI absoluteURI = info.getBaseUriBuilder().path("/customer/" + customer.getId()).build();

		return Response.created(absoluteURI).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCustomers() {

		Session session = sessionFactory.openSessionAndBeginTransaction();

		TypedQuery<Customer> query = session.createQuery("from Customer", Customer.class);
		List<Customer> customers = query.getResultList();

		session.close();

		return Response.ok(customers.toArray()).build();
	}

}