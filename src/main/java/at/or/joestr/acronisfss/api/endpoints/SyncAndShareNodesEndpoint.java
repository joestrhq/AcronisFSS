
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.structures.FolderNode;
import java.net.URI;
import java.util.UUID;

/**
 *
 * @author joestr
 */
public class SyncAndShareNodesEndpoint {
  public static String ENDPOINT_PATH = "/sync_and_share_nodes";
  
  public SyncAndShareNodesEndpoint() {
    throw new IllegalStateException("Utility class");
  }
  
  public FolderNode createFolder(URI apiUri, String bearerToken, UUID parentFolder) {
    FolderNode result = null;
    
    return result;
  }
}
