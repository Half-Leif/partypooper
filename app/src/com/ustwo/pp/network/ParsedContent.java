package com.ustwo.pp.network;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsedContent {
	private static final String KEY_TOKEN_TYPE = "token_type";
	private static final String KEY_EXPIRES_IN = "expires_in";
	private static final String KEY_ACCESS_TOKEN = "access_token";
	private static final String KEY_REFRESH_TOKEN = "refresh_token";
	
	private String expiresIn;
	private String tokenType;
	private String accessToken;
	private String refreshToken;
	
	public ParsedContent(JSONObject root){
		this.expiresIn = getString(root, KEY_EXPIRES_IN);
		this.tokenType = getString(root, KEY_TOKEN_TYPE);
		this.accessToken = getString(root,KEY_ACCESS_TOKEN);
		this.refreshToken = getString(root, KEY_REFRESH_TOKEN);
	}
	
	private String getString(JSONObject root, String key) {
		try {
			return root.getString(key);
		} catch (JSONException e) {
			return "";
		}
	}

	public String getExpiresIn() {
		return expiresIn;
	}
	public String getTokenType() {
		return tokenType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
}
