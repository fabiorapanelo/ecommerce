package com.fabiorapanelo.admin;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabiorapanelo.catalog.Category;
import com.fabiorapanelo.catalog.CategoryManagement;
import com.fabiorapanelo.utils.WebDispatcher;

@WebServlet("/admin/category")
public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryManagement categoryManagement;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", categoryManagement.getCategories());		
		WebDispatcher.dispatch(req, resp, "/WEB-INF/admin/category.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Category category = new Category();
		category.setName(req.getParameter("name"));
		category.setDescription(req.getParameter("description"));
		categoryManagement.createCategory(category);
		
		resp.sendRedirect("/webapp/admin/category");
	}
	

}
