/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import java.util.List;
import java.util.UUID;
import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author joestr
 */
public class AuditLogEndpoint {
	
	private static String ENDPOINT_PATH = "/audit_log";
	
	private AuditLogEndpoint() {
    throw new IllegalStateException("Utility class");
  }
	
	public static List<AuditLogEntry> getAuditLogEntries(URI apiUri, String bearerToken, int perPage, int page, UUID tenantId) throws IOException, InterruptedException {
		ArrayList<AuditLogEntry> result = null;
		
		UriBuilder requestUri = UriBuilder.fromUri(apiUri);
		requestUri.path(ENDPOINT_PATH);
		requestUri.queryParam("", "");
		
		HttpRequest req = HttpRequest.newBuilder()
			.GET()
			.uri(requestUri.build())
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.build();
		
		HttpResponse<String> response = HttpClient
	  .newBuilder()
	  .build()
	  .send(req, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() == 403) {
			throw new Error();
		}
		
		return result;
	}
}
