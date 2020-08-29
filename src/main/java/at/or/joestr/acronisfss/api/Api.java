/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import java.net.URI;
import java.util.List;

/**
 *
 * @author joestr
 */
public class Api {
	
	private final URI apiUri;
	private final URI authUri;
	
	public Api(URI apiUrl, URI authUrl) {
		this.apiUri = apiUrl;
		this.authUri = authUrl;
	}
	
	public URI getApiUrl() {
		return apiUri;
	}

	public URI getAuthUrl() {
		return authUri;
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
