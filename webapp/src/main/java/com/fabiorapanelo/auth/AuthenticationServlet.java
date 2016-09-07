package com.fabiorapanelo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if(session.getAttribute("AUTH_TOKEN") == null){
			try {
				
				OAuthClientRequest request = OAuthClientRequest
						   .authorizationProvider(OAuthProviderType.GOOGLE)
						   .setClientId(AuthConsts.GOOGLE_CLIENT_ID)
						   .setRedirectURI(AuthConsts.GOOGLE_AUTH_CALLBACK)
						   .setResponseType(AuthConsts.GOOGLE_RESPONSE_TYPE)
						   .setScope(AuthConsts.GOOGLE_SCOPE)
						   .buildQueryMessage();
				
				resp.sendRedirect(request.getLocationUri());
			
			} catch (OAuthSystemException e) {
				throw new ServletException(e);
			}
		} else {
			resp.sendRedirect("/webapp/user");
		}
		
		
	}
	
	
	

}
