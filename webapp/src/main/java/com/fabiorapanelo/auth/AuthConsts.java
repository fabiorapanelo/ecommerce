package com.fabiorapanelo.auth;

public interface AuthConsts {
	
	String GOOGLE_CLIENT_ID = "960656735066-3jsafbs1cqcoavfd97a2263go5ldnmf3.apps.googleusercontent.com";
	String GOOGLE_CLIENT_SECRET = "CAAqVMlWx6AmX1C7u-bG8pHQ";
	String GOOGLE_AUTH_CALLBACK = "http://localhost:8080/webapp/auth/callback";
	String GOOGLE_RESPONSE_TYPE = "code";
	String GOOGLE_SCOPE = "profile email";
	
	
	String SESSION_KEY_AUTH_CODE = "authCode";
	String SESSION_KEY_ACCESS_TOKEN = "accessToken";
	String SESSION_KEY_USER = "user";
	
	String URL_AFTER_LOGIN = "/webapp/";
	

}
