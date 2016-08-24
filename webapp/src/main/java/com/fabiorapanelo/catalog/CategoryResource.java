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

@Path("/category")
public class CategoryResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCategories() {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		
		TypedQuery<Category> query = session.createQuery("from Category", Category.class);
		List<Category> categories = query.getResultList();

		session.close();

		return Response.ok(categories.toArray()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCategory(@Context UriInfo info, Category category) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();
		session.save(category);
		session.getTransaction().commit();
		session.close();

		URI absoluteURI = info.getBaseUriBuilder().path("/category/" + category.getId()).build();

		return Response.created(absoluteURI).build();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCategory(@PathParam("id") int id) {

		Session session = SessionFactorySingleton.getInstance().openSessionAndBeginTransaction();

		TypedQuery<Category> query = session.createQuery("from Category where CATEGORY_ID = :id ", Category.class);
		query.setParameter("id", id);

		Category category = null;
		try {
			category = query.getSingleResult();
			return Response.ok(category).build();
		} catch (NoResultException nre) {
			return Response.status(Status.NOT_FOUND).build();
		} finally {
			session.close();
		}

	}

}
