
package at.or.joestr.acronisfss.api.endpoints;

import static at.or.joestr.acronisfss.api.endpoints.DeviceEndpoint.ENDPOINT_PATH;
import at.or.joestr.acronisfss.api.exceptions.ApiException;
import at.or.joestr.acronisfss.api.structures.ErrorResponse;
import at.or.joestr.acronisfss.api.structures.FolderNode;
import at.or.joestr.acronisfss.api.structures.SyncAndShareNode;
import at.or.joestr.acronisfss.api.utils.CustomUtil;
import at.or.joestr.acronisfss.api.utils.RequestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
  
  public FolderNode createFolder(URI apiUri, String bearerToken, SyncAndShareNode node) throws URISyntaxException, IOException, InterruptedException {    
    FolderNode result = null;
    
    URIBuilder uri
      = new URIBuilder(apiUri.toString() + ENDPOINT_PATH);
    
    HttpRequest req = HttpRequest.newBuilder()
      .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(node)))
      .uri(uri.build())
      .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
      .header(HttpHeaders.ACCEPT, "application/json")
      .build();
    
    HttpResponse<String> response = HttpClient
      .newBuilder()
      .build()
      .send(req, HttpResponse.BodyHandlers.ofString());

    if (CustomUtil.contains(response.statusCode(), 404, 409, 403)) {
      ErrorResponse error = new Gson().fromJson(
        JsonParser.parseString(response.body()).getAsJsonObject().get("error").getAsJsonObject(),
        ErrorResponse.class
      );

      throw new ApiException(error.toString());
    }
    
    return new Gson().fromJson(response.body(), FolderNode.class);
  }
}
