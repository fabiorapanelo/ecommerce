package com.fabiorapanelo.catalog;

import java.net.URI;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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

@Path("/product")
public class ProductResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProducts() {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

		TypedQuery<Product> query = session.createQuery("from Product", Product.class);
		List<Product> products = query.getResultList();

		session.close();

		return Response.ok(products.toArray()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduct(@Context UriInfo info, Product product) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();

		URI absoluteURI = info.getBaseUriBuilder().path("/product/" + product.getId()).build();

		return Response.created(absoluteURI).build();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProduct(@PathParam("id") int id) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

		TypedQuery<Product> query = session.createQuery("from Product where PRODUCT_ID = :id ", Product.class);
		query.setParameter("id", id);

		Product product = null;
		try {
			product = query.getSingleResult();
			return Response.ok(product).build();
		} catch (NoResultException nre) {
			return Response.status(Status.NOT_FOUND).build();
		} finally {
			session.close();
		}

	}
}
