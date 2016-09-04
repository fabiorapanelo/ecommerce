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
            
            validateRequest(oauthRequest);

            OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);

            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            
            if (responseType.equalsIgnoreCase(ResponseType.CODE.toString())) {
                String authorizationCode = oauthIssuerImpl.authorizationCode();
                //TODO: Save in database
                builder.setCode(authorizationCode);
            }
            
            if (responseType.equalsIgnoreCase(ResponseType.TOKEN.toString())) {
            	String accessToken = oauthIssuerImpl.accessToken();
                //TODO: Save in database
                builder.setAccessToken(accessToken);
                builder.setExpiresIn(3600l);
            }            
            
            OAuthResponse response = builder
            		.location(oauthRequest.getRedirectURI())
            		.buildQueryMessage();
            
            return Response.status(response.getResponseStatus()).location(new URI(response.getLocationUri())).build();
            
        } catch (OAuthProblemException e) {
        	
			String redirectUri = e.getRedirectUri();
			if (OAuthUtils.isEmpty(redirectUri)) {
				throw new WebApplicationException(Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(MISSING_REDIRECT_URI_PARAM).build());
			}
			
			OAuthResponse response = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
					.error(e)
					.location(redirectUri)
					.buildQueryMessage();

			return Response.status(response.getResponseStatus()).location(new URI(response.getLocationUri())).build();
        	
        }
    }
	private void validateRequest(OAuthAuthzRequest oauthRequest) {
		
		//TODO: Check if clientId is valid
		if(!oauthRequest.getClientId().equals("123")){
		    throw new WebApplicationException(Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(INVALID_CLIENT_ID).build());
		}
		
		if (OAuthUtils.isEmpty(oauthRequest.getRedirectURI())) {
			throw new WebApplicationException(Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(MISSING_REDIRECT_URI_PARAM).build());
		}
		
	}
}
