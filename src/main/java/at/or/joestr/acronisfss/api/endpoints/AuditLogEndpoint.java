/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.exceptions.ApiException;
import at.or.joestr.acronisfss.api.filter.AuditLogEntriesListFilter;
import java.util.List;
import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import at.or.joestr.acronisfss.api.structures.ErrorResponse;
import at.or.joestr.acronisfss.api.structures.Severity;
import at.or.joestr.acronisfss.api.util.CustomUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author joestr
 */
public class AuditLogEndpoint {

  private static final String ENDPOINT_PATH = "/audit_log";

  private AuditLogEndpoint() {
    throw new IllegalStateException("Utility class");
  }

  public static List<AuditLogEntry> getAuditLogEntries(URI apiUri, String bearerToken, AuditLogEntriesListFilter auditLogFilter) throws IOException, InterruptedException, URISyntaxException {
    ArrayList<AuditLogEntry> result = null;

    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH)
        .addParameters(auditLogFilter.build());

    HttpRequest req = HttpRequest.newBuilder()
      .GET()
      .uri(uri.build())
      .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
      .header(HttpHeaders.ACCEPT, "application/json")
      .build();

    HttpResponse<String> response = HttpClient
      .newBuilder()
      .build()
      .send(req, HttpResponse.BodyHandlers.ofString());

    if (CustomUtil.contains(response.statusCode(), 403)) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
    }

    JsonArray jsonLogEntries = JsonParser.parseString(response.body()).getAsJsonArray();

    result = new ArrayList<>();

    for (JsonElement jsonLogEntry : jsonLogEntries) {
      JsonObject jsonLogEntryObject = jsonLogEntry.getAsJsonObject();

      AuditLogEntry resultEntry = new AuditLogEntry(
        UUID.fromString(jsonLogEntryObject.get("uuid").getAsString()),
        jsonLogEntryObject.get("code").getAsInt(),
        LocalDateTime.parse(
          jsonLogEntryObject.get("created_at").getAsString(),
          DateTimeFormatter.ISO_DATE_TIME
        ),
        jsonLogEntryObject.get("text").getAsString(),
        Severity.values()[jsonLogEntryObject.get("severity").getAsInt() - 1]
      );

      if (jsonLogEntryObject.has("node_uuid") && !jsonLogEntryObject.get("node_uuid").isJsonNull()) {
        resultEntry.setNodeUuid(
          UUID.fromString(jsonLogEntryObject.get("node_uuid").getAsString())
        );
      }

      if (jsonLogEntryObject.has("share_uuid") && !jsonLogEntryObject.get("share_uuid").isJsonNull()) {
        resultEntry.setShareUuid(
          UUID.fromString(jsonLogEntryObject.get("share_uuid").getAsString())
        );
      }

      if (jsonLogEntryObject.has("owner_uuid") && !jsonLogEntryObject.get("owner_uuid").isJsonNull()) {
        resultEntry.setOwnerUuid(
          UUID.fromString(jsonLogEntryObject.get("owner_uuid").getAsString())
        );
      }

      result.add(resultEntry);
    }

    return result;
  }
}
