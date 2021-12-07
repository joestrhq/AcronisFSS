/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.exceptions.ApiException;
import at.or.joestr.acronisfss.api.classes.OAuth2Token;
import at.or.joestr.acronisfss.api.structures.ErrorResponse;
import at.or.joestr.acronisfss.api.utils.CustomUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

/**
 *
 * @author joestr
 */
public class AuthorizationEndpoint {

  public static String ENDPOINT_PATH = "/token";

  private AuthorizationEndpoint() {
    throw new IllegalStateException("Utility class");
  }

  public static OAuth2Token getToken(URI authUri, String grantType, String username, String password) throws IOException, InterruptedException, URISyntaxException {

    OAuth2Token result;

    HttpRequest req = HttpRequest.newBuilder()
      .uri(new URI(authUri.toString() + ENDPOINT_PATH))
      .header(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
      .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
      .POST(HttpRequest.BodyPublishers.ofString(
        new StringBuilder("")
          .append("grant_type=").append(grantType)
          .append("&").append("username=").append(URLEncoder.encode(username, StandardCharsets.UTF_8))
          .append("&").append("password=").append(URLEncoder.encode(password, StandardCharsets.UTF_8))
          .toString(),
        StandardCharsets.UTF_8
      ))
      .build();

    HttpResponse<String> response
      = HttpClient
        .newBuilder()
        .build()
        .send(req, HttpResponse.BodyHandlers.ofString());

    if (CustomUtil.contains(response.statusCode(), 401)) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
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

  public static OAuth2Token getToken(URI authUri, String grantType, OAuth2Token token) throws IOException, InterruptedException, URISyntaxException {

    OAuth2Token result;

    HttpRequest req = HttpRequest.newBuilder()
      .uri(new URI(authUri.toString() + ENDPOINT_PATH))
      .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
      .header(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
      .POST(HttpRequest.BodyPublishers.ofString(
        new StringBuilder("")
          .append("grant_type=").append(grantType)
          .append("&").append("refresh_token=").append(token.getAccessToken())
          .toString(),
        StandardCharsets.UTF_8
      ))
      .build();

    HttpResponse<String> response
      = HttpClient
        .newBuilder()
        .build()
        .send(req, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 401) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
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
