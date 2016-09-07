package com.fabiorapanelo.catalog;

import java.net.URI;

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

@Path("/product")
public class ProductResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProducts() {
		return Response.ok(ProductManagement.getProducts().toArray()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduct(@Context UriInfo info, Product product) {

		ProductManagement.createProduct(product);

		String location = "/product/" + product.getId();
		URI absoluteURI = info.getBaseUriBuilder().path(location).build();

		return Response.created(absoluteURI).build();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProduct(@PathParam("id") int id) {

		Product product = ProductManagement.getProduct(id);
		if(product != null)
			Response.ok(product).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}
}
