package com.fabiorapanelo.authserver;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

@Path("/authorize")
public class AuthorizeResource {

	//http://localhost:8080/webapp/auth-server/authorize?redirect_uri=http://localhost:8080/webapp/&response_type=code&client_id=123
	
	private static final String MISSING_REDIRECT_URI_PARAM = "Missing redirect_uri param.";
	private static final String INVALID_CLIENT_ID = "Invalid client_id.";
	
	@GET
    public Response authorize(@Context HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
        
        try {
        	
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            
            String redirectUri = validateRequest(oauthRequest);

            OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);

            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            
            if (responseType.equalsIgnoreCase(ResponseType.CODE.toString())) {
                final String authorizationCode = oauthIssuerImpl.authorizationCode();
                //TODO: Save in database
                builder.setCode(authorizationCode);
            }
            
            if (responseType.equalsIgnoreCase(ResponseType.TOKEN.toString())) {
            	final String accessToken = oauthIssuerImpl.accessToken();
                //TODO: Save in database
                builder.setAccessToken(accessToken);
                builder.setExpiresIn(3600l);
            }            
            
            final OAuthResponse response = builder.location(redirectUri).buildQueryMessage();
            
            //return Response.status(response.getResponseStatus()).location(new URI(response.getLocationUri())).build();
            System.out.println("teste");
            return Response.ok().build();
            
        } catch (OAuthProblemException e) {
        	
			final Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_FOUND);
			
			String redirectUri = e.getRedirectUri();
			if (OAuthUtils.isEmpty(redirectUri)) {
				throw new WebApplicationException(responseBuilder.entity(MISSING_REDIRECT_URI_PARAM).build());
			}
			
			final OAuthResponse response = OAuthASResponse.
					errorResponse(HttpServletResponse.SC_FOUND).
					error(e).
					location(redirectUri).
					buildQueryMessage();

			return responseBuilder.location(new URI(response.getLocationUri())).build();
        	
        }
    }
	private String validateRequest(OAuthAuthzRequest oauthRequest) {
		
		String clientId = oauthRequest.getClientId();
		
		//TODO: Check if clientId is valid
		if(!clientId.equals("123")){
		    final Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_FOUND);
		    throw new WebApplicationException(responseBuilder.entity(INVALID_CLIENT_ID).build());
		}
		
		String redirectUri = oauthRequest.getRedirectURI();
		
		if (OAuthUtils.isEmpty(redirectUri)) {
			final Response.ResponseBuilder responseBuilder = Response.status(HttpServletResponse.SC_FOUND);
			throw new WebApplicationException(responseBuilder.entity(MISSING_REDIRECT_URI_PARAM).build());
		}
		return redirectUri;
	}
}
