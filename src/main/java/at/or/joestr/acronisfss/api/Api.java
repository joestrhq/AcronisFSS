/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import at.or.joestr.acronisfss.api.classes.OAuth2Token;
import at.or.joestr.acronisfss.api.endpoints.AuthorizationEndpoint;
import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joestr
 */
public class Api {
	
	private final static Logger LOGGER = Logger.getLogger(Api.class.getName());
	
	private final URI apiUri;
	private final URI authUri;
	private final String username;
	private final String password;
	
	private OAuth2Token token;
	
	public Api(URI apiUrl, URI authUrl, String username, String password) {
		this.apiUri = apiUrl;
		this.authUri = authUrl;
		this.username = username;
		this.password = password;
	}
	
	public URI getApiUri() {
		return apiUri;
	}

	public URI getAuthUri() {
		return authUri;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public OAuth2Token getToken() {
		return token;
	}
	
	
	
	public void authorize() {
		try {
			this.token = AuthorizationEndpoint.getToken(this.authUri, "password", this.username, this.password);
		} catch (IOException | InterruptedException | URISyntaxException ex) {
			LOGGER.log(Level.SEVERE, "Error whilst authorizing client.", ex);
			Thread.currentThread().interrupt();
		}
	}
	
	public void reauthorize() {
		try {
			this.token = AuthorizationEndpoint.getToken(authUri, "refresh_token", this.token);
		} catch (IOException | InterruptedException | URISyntaxException ex) {
			LOGGER.log(Level.SEVERE, "Error whilst re-authorizing client.", ex);
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Gets a list of audit log entries.
	 * 
	 * @return Audit log entries.
	 */
	public List<AuditLogEntry> getAuditLog() {
		return null;
	}
}
