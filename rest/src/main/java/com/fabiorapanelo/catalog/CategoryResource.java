package com.fabiorapanelo.catalog;

import java.net.URI;

import javax.inject.Inject;
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

@Path("/category")
public class CategoryResource {

	@Inject
	private CategoryManagement categoryManagement;
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCategories() {
		return Response.ok(categoryManagement.getCategories().toArray()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCategory(@Context UriInfo info, Category category) {

		categoryManagement.createCategory(category);
		String location = "/category/" + category.getId();
		URI absoluteURI = info.getBaseUriBuilder().path(location).build();

		return Response.created(absoluteURI).build();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getCategory(@PathParam("id") int id) {

		Category category = categoryManagement.getCategory(id);
		
		if(category != null)
			return Response.ok(category).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}

}
