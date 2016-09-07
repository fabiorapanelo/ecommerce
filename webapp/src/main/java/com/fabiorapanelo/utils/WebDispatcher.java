package com.fabiorapanelo.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebDispatcher {

	public static void dispatch(HttpServletRequest req, HttpServletResponse resp, String jsp)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
		
	}

}
