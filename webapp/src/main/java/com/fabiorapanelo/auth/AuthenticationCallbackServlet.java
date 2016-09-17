package com.fabiorapanelo.auth;

import java.io.IOException;
import java.io.StringReader;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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

@WebServlet("/auth/callback")
public class AuthenticationCallbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String INVALID_EMAIL = "Invalid email.";

	@Inject
	private UserManagement userManagement;
	
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
			OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(request, "POST");
			
			String accessToken = oAuthResponse.getAccessToken();
			
			OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest("https://www.googleapis.com/oauth2/v1/userinfo?alt=json")
	                .setAccessToken(accessToken).buildQueryMessage();
			OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, "GET", OAuthResourceResponse.class);
			
			JsonReader jsonReader = Json.createReader(new StringReader(resourceResponse.getBody()));
			JsonObject userInformation = jsonReader.readObject();
			
			String email = userInformation.getString("email", "");
			if(StringUtils.isEmpty(email)){
				throw OAuthProblemException.error(INVALID_EMAIL);
			}
			
			User user = userManagement.getUser(email);
			if(user == null){
				user = parseJsonToUser(userInformation);
				userManagement.createUser(user);
			}else {
				userManagement.updateUser(user);
			}			
			
			HttpSession session = req.getSession();
			session.setAttribute(AuthConsts.SESSION_KEY_AUTH_CODE, authCode);
			session.setAttribute(AuthConsts.SESSION_KEY_ACCESS_TOKEN, accessToken);
			session.setAttribute(AuthConsts.SESSION_KEY_USER, user);
			
			resp.sendRedirect(AuthConsts.URL_AFTER_LOGIN);
		
		} catch (OAuthSystemException | OAuthProblemException e) {
			throw new ServletException(e);
		}		
	}

	private User parseJsonToUser(JsonObject userInformation) {
		User user = new User();
		String email = userInformation.getString("email", "");
		user.setEmail(email);
		
		String name = userInformation.getString("name", "");
		user.setName(name);
		
		String givenName = userInformation.getString("given_name", "");
		user.setGivenName(givenName);
		
		String familyName = userInformation.getString("family_name", "");
		user.setFamilyName(familyName);
		return user;
	}
	
	

}
