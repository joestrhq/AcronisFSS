/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.exceptions.DeviceEndpointException;
import at.or.joestr.acronisfss.api.exceptions.GetAuditLogEntriesListException;
import at.or.joestr.acronisfss.api.filter.DeviceListFilter;
import at.or.joestr.acronisfss.api.structures.ActionsDevice;
import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import at.or.joestr.acronisfss.api.structures.ClientType;
import at.or.joestr.acronisfss.api.structures.Device;
import at.or.joestr.acronisfss.api.structures.ErrorResponse;
import at.or.joestr.acronisfss.api.structures.Filesystem;
import at.or.joestr.acronisfss.api.structures.Severity;
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
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author joestr
 */
public class DeviceEndpoint {

  public static String ENDPOINT_PATH = "/devices";
  
  public DeviceEndpoint() {
    throw new IllegalStateException("Utility class");
  }

  public static List<Device> getDevices(URI apiUri, String bearerToken, DeviceListFilter deviceListFilter) throws URISyntaxException, IOException, InterruptedException {
    ArrayList<Device> result = null;
    
    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH)
        .addParameters(deviceListFilter.build());

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

    if (response.statusCode() == 403) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new DeviceEndpointException(error.toString());
    }

    JsonArray jsonDevices = JsonParser.parseString(response.body()).getAsJsonArray();

    result = new ArrayList<>();

    for (JsonElement jsonDevice : jsonDevices) {
      JsonObject jsonDeviceObject = jsonDevice.getAsJsonObject();
      
      Device resultEntry = new Device(
        UUID.fromString(jsonDeviceObject.get("uuid").getAsString()),
        jsonDeviceObject.get("app_version").getAsString(),
        Filesystem.values()[jsonDeviceObject.get("filesystem").getAsInt() - 1],
        LocalDateTime.parse(jsonDeviceObject.get("last_contact_time").getAsString(), DateTimeFormatter.ISO_DATE_TIME),
        jsonDeviceObject.get("name").getAsString(),
        jsonDeviceObject.get("system_platform").getAsString(),
        jsonDeviceObject.get("system_version").getAsString(),
        ClientType.valueOf(jsonDeviceObject.get("client_type").getAsString()),
        UUID.fromString(jsonDeviceObject.get("user_uuid").getAsString()),
        jsonDeviceObject.get("user_name").getAsString(),
        new ActionsDevice(
          jsonDeviceObject.get("actions").getAsJsonObject().get("remove").getAsString()
        )
      );

      if (jsonDeviceObject.has("model") && !jsonDeviceObject.get("model").isJsonNull()) {
        resultEntry.setModel(jsonDeviceObject.get("model").getAsString());
      }

      if (jsonDeviceObject.has("notes") && !jsonDeviceObject.get("notes").isJsonNull()) {
        resultEntry.setNotes(jsonDeviceObject.get("notes").getAsString());
      }

      result.add(resultEntry);
    }

    return result;
  }

  public static Device getDeviceInformation(UUID deviceUuuid, UUID tenantId) {
    Device result = null;

    return result;
  }

  public static void updateDevice(UUID deviceUuid, Device device) {

    return;
  }

  public static void deleteDevice(UUID deviceUuid, Device device) {

    return;
  }
}
