/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.exceptions.ApiException;
import at.or.joestr.acronisfss.api.filter.DeviceListFilter;
import at.or.joestr.acronisfss.api.filter.TenantFilter;
import at.or.joestr.acronisfss.api.structures.ActionsDevice;
import at.or.joestr.acronisfss.api.structures.ClientType;
import at.or.joestr.acronisfss.api.structures.Device;
import at.or.joestr.acronisfss.api.structures.ErrorResponse;
import at.or.joestr.acronisfss.api.structures.Filesystem;
import at.or.joestr.acronisfss.api.util.CustomUtil;
import at.or.joestr.acronisfss.api.util.RequestUtil;
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

      throw new ApiException(error.toString());
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

  public static Device getDeviceInformation(URI apiUri, String bearerToken, UUID deviceUuid, TenantFilter tenantFilter) throws URISyntaxException, IOException, InterruptedException {
    Device result = null;

    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH + "/" + deviceUuid.toString())
        .addParameters(tenantFilter.build());

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

    if (CustomUtil.contains(response.statusCode(), 403, 404)) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
    }

    JsonObject jsonDeviceObject = JsonParser.parseString(response.body()).getAsJsonObject();

    result = new Device(
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
      result.setModel(jsonDeviceObject.get("model").getAsString());
    }

    if (jsonDeviceObject.has("notes") && !jsonDeviceObject.get("notes").isJsonNull()) {
      result.setNotes(jsonDeviceObject.get("notes").getAsString());
    }
    
    return result;
  }

  public static void updateDevice(URI apiUri, String bearerToken, UUID deviceUuid, Device device) throws URISyntaxException, IOException, InterruptedException {
    JsonObject request = RequestUtil.makeDevicesRequest(device);
    
    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH + "/" + deviceUuid.toString());
    
    HttpRequest req = HttpRequest.newBuilder()
      .POST(HttpRequest.BodyPublishers.ofString(request.toString()))
      .uri(uri.build())
      .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
      .header(HttpHeaders.ACCEPT, "application/json")
      .build();
    
    HttpResponse<String> response = HttpClient
      .newBuilder()
      .build()
      .send(req, HttpResponse.BodyHandlers.ofString());

    if (CustomUtil.contains(response.statusCode(), 404)) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
    }
  }

  public static void deleteDevice(URI apiUri, String bearerToken, UUID deviceUuid) throws URISyntaxException, IOException, InterruptedException {
    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH + "/" + deviceUuid.toString());
    
    HttpRequest req = HttpRequest.newBuilder()
      .DELETE()
      .uri(uri.build())
      .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
      .header(HttpHeaders.ACCEPT, "application/json")
      .build();
    
    HttpResponse<String> response = HttpClient
      .newBuilder()
      .build()
      .send(req, HttpResponse.BodyHandlers.ofString());

    if (CustomUtil.contains(response.statusCode(), 404)) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
    }
  }
}
