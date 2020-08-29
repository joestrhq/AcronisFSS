/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.exceptions.FilesProtectApiException;
import at.or.joestr.acronisfss.api.classes.OAuth2Token;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author joestr
 */
public class AuthorizationEndpoint {

	public static String ENDPOINT_PATH = "/token";

	public OAuth2Token getToken(URI authUri, String grantType, String username, String password) throws IOException, InterruptedException {

		OAuth2Token result;

		UriBuilder requestUri = UriBuilder.fromUri(authUri);
		requestUri.path(ENDPOINT_PATH);

		HttpRequest req = HttpRequest.newBuilder()
			.POST(HttpRequest.BodyPublishers.ofString(
				new StringBuilder("")
					.append("grant_type=")
					.append(grantType)
					.append("&")
					.append("username=")
					.append(username)
					.append("&")
					.append("password=")
					.append(password)
					.toString(),
				StandardCharsets.UTF_8
			))
			.uri(requestUri.build())
			.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
			.build();

		HttpResponse<String> response
			= HttpClient
				.newBuilder()
				.build()
				.send(req, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() == 401) {
			Error error = new Gson().fromJson(
				JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
				Error.class
			);
			
			throw new FilesProtectApiException(error.toString());
		}
		
		JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
		
		result = new OAuth2Token(
			LocalDateTime.ofInstant(Instant.ofEpochSecond(
				jsonResponse.get("expires_on").getAsLong()),
				TimeZone.getTimeZone("UTC").toZoneId()
			),
			jsonResponse.get("token_type").getAsString(),
			jsonResponse.get("access_token").getAsString(),
			jsonResponse.get("id_token").getAsString()
		);

		return result;
	}
}
