package com.fabiorapanelo.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/admin/login")
public class LoginAdminServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){
			HttpSession session = req.getSession();
			session.setAttribute("ADMIN_LOGIN", "1");
			resp.sendRedirect("/webapp/admin/home");
		}else {
			resp.sendRedirect("/webapp/admin/?failed=true");
		}
		
		
	}
}
