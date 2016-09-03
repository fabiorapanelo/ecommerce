package com.fabiorapanelo.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONObject;

@WebServlet("/auth/callback")
public class AuthenticationCallbackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(req);
			
			String authCode = oar.getCode();
			
			OAuthClientRequest request = OAuthClientRequest
	                .tokenProvider(OAuthProviderType.GOOGLE)
	                .setClientId(AuthConsts.GOOGLE_CLIENT_ID)
	                .setClientSecret(AuthConsts.GOOGLE_CLIENT_SECRET)
	                .setRedirectURI(AuthConsts.GOOGLE_AUTH_CALLBACK)
	                .setGrantType(GrantType.AUTHORIZATION_CODE)
	                .setCode(authCode)
	                .buildBodyMessage();
		
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient()); 
			final OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(request, "POST");
			
			
			String accessToken = oAuthResponse.getAccessToken();
			
			HttpSession session = req.getSession();
			session.setAttribute("AUTH_CODE", authCode);
			session.setAttribute("AUTH_TOKEN", accessToken);
			
			
			OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest("https://www.googleapis.com/oauth2/v1/userinfo?alt=json")
	                .setAccessToken(accessToken).buildQueryMessage();
			OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, "GET", OAuthResourceResponse.class);
			System.out.println(resourceResponse.getBody());
	
			resp.sendRedirect("/webapp/user");
		
		} catch (OAuthSystemException | OAuthProblemException e) {
			throw new ServletException(e);
		}		
	}
	
	

}
