package com.fabiorapanelo.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabiorapanelo.catalog.Category;
import com.fabiorapanelo.catalog.CategoryManagement;
import com.fabiorapanelo.catalog.Product;
import com.fabiorapanelo.catalog.ProductManagement;
import com.fabiorapanelo.utils.WebDispatcher;

@WebServlet("/admin/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryManagement categoryManagement;

	@Inject
	private ProductManagement productManagement;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", categoryManagement.getCategories());
		req.setAttribute("products", productManagement.getProducts());
		WebDispatcher.dispatch(req, resp, "/WEB-INF/admin/product.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Product product = new Product();
		product.setName(req.getParameter("name"));
		
		Category category = categoryManagement.getCategory(Integer.parseInt(req.getParameter("category")));
		product.setCategory(category);

		productManagement.createProduct(product);
		
		resp.sendRedirect("/webapp/admin/product");
	}
	

}
