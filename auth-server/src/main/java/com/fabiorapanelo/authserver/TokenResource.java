package com.fabiorapanelo.authserver;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

@Path("/token")
public class TokenResource {

	private static final String MISSING_REDIRECT_URI_PARAM = "Missing redirect_uri param.";
	private static final String INVALID_CLIENT_ID = "Invalid client_id.";
	private static final String INVALID_AUTHORIZATION_CODE = "Invalid auth code.";
	private static final String INVALID_CLIENT_SECRET = "Invalid client_secret.";
	
	//http://localhost:8080/webapp/auth-server/token?redirect_uri=http://localhost:8080/webapp/&grant_type=authorization_code&code=123&client_id=123&client_secret=123
	
	@POST
	public Response token(@Context HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
		
		try {
			
			OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
			OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		
			validateRequest(oauthRequest);
		
			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();
		
			OAuthResponse response = OAuthASResponse
					.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(accessToken)
					.setExpiresIn("3600")
					.setRefreshToken(refreshToken)
					.buildJSONMessage();
			
			return Response.status(response.getResponseStatus()).location(new URI(oauthRequest.getRedirectURI())).entity(response.getBody()).build();
		
		} catch(OAuthProblemException e) {
		
			Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_UNAUTHORIZED);
			
			String redirectUri = e.getRedirectUri();
			if(redirectUri != null){
				responseBuilder.location(new URI(redirectUri));
			}
			
			OAuthResponse response = OAuthResponse
					.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
					.error(e)
					.buildJSONMessage();
		
			return responseBuilder.entity(response.getBody()).build();
			
		}
		
	}

	private void validateRequest(OAuthTokenRequest oauthRequest) throws OAuthProblemException {
		
		String authorizationCode = oauthRequest.getCode();
		//TODO: Check if exists in database
		if (!authorizationCode.equals("123")) {
			throw OAuthProblemException.error(INVALID_AUTHORIZATION_CODE);
		}
		
		String clientId = oauthRequest.getClientId();
		//TODO: Check if exists in database
		if (!clientId.equals("123")) {
			throw OAuthProblemException.error(INVALID_CLIENT_ID);
		}
		
		String clientSecret = oauthRequest.getClientSecret();
		//TODO: Check if exists in database
		if (!clientSecret.equals("123")) {
			throw OAuthProblemException.error(INVALID_CLIENT_SECRET);
		}
		
	}
}
