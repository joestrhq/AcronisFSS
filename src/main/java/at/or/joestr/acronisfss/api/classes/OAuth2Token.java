/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.classes;

import java.time.LocalDateTime;

/**
 *
 * @author joestr
 */
public class OAuth2Token {
	private LocalDateTime expiresOn;
	private String tokenType;
	private String accessToken;
	private String idToken;

	public OAuth2Token(LocalDateTime expiresOn, String tokenType, String accessToken, String idToken) {
		this.expiresOn = expiresOn;
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.idToken = idToken;
	}

	public LocalDateTime getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(LocalDateTime expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
}
