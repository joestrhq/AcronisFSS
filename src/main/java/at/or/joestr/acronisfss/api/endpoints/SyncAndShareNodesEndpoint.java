package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.exceptions.ApiException;
import at.or.joestr.acronisfss.api.structures.ConflictErrorResponse;
import at.or.joestr.acronisfss.api.structures.Error;
import at.or.joestr.acronisfss.api.structures.ErrorResponse;
import at.or.joestr.acronisfss.api.structures.FolderNode;
import at.or.joestr.acronisfss.api.structures.SyncAndShareNode;
import at.or.joestr.acronisfss.api.structures.SyncAndShareNodesRequest;
import at.or.joestr.acronisfss.api.utils.CustomUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author joestr
 */
public class SyncAndShareNodesEndpoint {

  public static String ENDPOINT_PATH = "/sync_and_share_nodes";

  public SyncAndShareNodesEndpoint() {
    throw new IllegalStateException("Utility class");
  }

  public static FolderNode createFolder(URI apiUri, String bearerToken, SyncAndShareNode node) throws URISyntaxException, IOException, InterruptedException {
    FolderNode result;

    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH);
    
    SyncAndShareNodesRequest request = new SyncAndShareNodesRequest(node);

    HttpRequest req = HttpRequest.newBuilder()
      .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(request)))
      .uri(uri.build())
      .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
      .header(HttpHeaders.ACCEPT, "application/json")
      .header(HttpHeaders.CONTENT_TYPE, "application/json")
      .build();

    HttpResponse<String> response = HttpClient
      .newBuilder()
      .build()
      .send(req, HttpResponse.BodyHandlers.ofString());

    Error error = null;

    if (response.statusCode() == 409) {
      ConflictErrorResponse errorResponse = new Gson().fromJson(response.body(), ConflictErrorResponse.class);
      error = errorResponse.getError();
    } else if (CustomUtil.contains(response.statusCode(), 404, 403)) {
      ErrorResponse errorResponse = new Gson().fromJson(response.body(), ErrorResponse.class);
      error = errorResponse.getError();
    }

    if (error != null) {
      throw new ApiException(error.getMessage());
    }

    result = new Gson().fromJson(response.body(), FolderNode.class);

    return result;
  }
  
  public static boolean deleteNode(URI apiUri, String bearerToken, UUID uuid) throws URISyntaxException, IOException, InterruptedException {
    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH + "/" + uuid);
    
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
    
    Error error = null;

    if (CustomUtil.contains(response.statusCode(), 403)) {
      ErrorResponse errorResponse = new Gson().fromJson(response.body(), ErrorResponse.class);
      error = errorResponse.getError();
    }

    if (error != null) {
      throw new ApiException(error.getMessage());
    }

    return true;
  }
}
