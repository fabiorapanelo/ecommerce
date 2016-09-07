package com.fabiorapanelo.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabiorapanelo.utils.WebDispatcher;

@WebServlet("/admin/")
public class HomeAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebDispatcher.dispatch(req, resp, "/WEB-INF/admin/home.jsp");
	}

}
