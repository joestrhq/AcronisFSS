/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import at.or.joestr.acronisfss.api.classes.OAuth2Token;
import at.or.joestr.acronisfss.api.endpoints.AuditLogEndpoint;
import at.or.joestr.acronisfss.api.endpoints.AuthorizationEndpoint;
import at.or.joestr.acronisfss.api.endpoints.DeviceEndpoint;
import at.or.joestr.acronisfss.api.endpoints.SyncAndShareNodesEndpoint;
import at.or.joestr.acronisfss.api.filter.AuditLogEntriesListFilter;
import at.or.joestr.acronisfss.api.filter.DeviceListFilter;
import at.or.joestr.acronisfss.api.filter.TenantFilter;
import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import at.or.joestr.acronisfss.api.structures.Device;
import at.or.joestr.acronisfss.api.structures.DevicesRequest;
import at.or.joestr.acronisfss.api.structures.FolderNode;
import at.or.joestr.acronisfss.api.structures.Node;
import at.or.joestr.acronisfss.api.structures.SyncAndShareNode;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joestr
 */
public class Api {

  private final static Logger LOGGER = Logger.getLogger(Api.class.getName());

  private final URI apiUri;
  private final URI authUri;
  private final String username;
  private final String password;

  private OAuth2Token token;

  public Api(URI apiUrl, URI authUrl, String username, String password) {
    this.apiUri = apiUrl;
    this.authUri = authUrl;
    this.username = username;
    this.password = password;
  }

  public URI getApiUri() {
    return apiUri;
  }

  public URI getAuthUri() {
    return authUri;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public OAuth2Token getToken() {
    return token;
  }

  /**
   * Authorizes the client.
   */
  public void authorize() {
    try {
      this.token = AuthorizationEndpoint.getToken(this.authUri, "password", this.username, this.password);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst authorizing client.", ex);
      Thread.currentThread().interrupt();
    }
  }

  public void reauthorize() {
    try {
      this.token = AuthorizationEndpoint.getToken(authUri, "refresh_token", this.token);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst re-authorizing client.", ex);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Gets a list of audit log entries.
   *
   * @param auditLogFilter A filter to find specific audit log entries.
   *
   * @return Audit log entries.
   */
  public List<AuditLogEntry> getAuditLogEntries(AuditLogEntriesListFilter auditLogFilter) {
    try {
      return AuditLogEndpoint.getAuditLogEntries(apiUri, this.token.getAccessToken(), auditLogFilter);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst getting audit log entries.", ex);
      Thread.currentThread().interrupt();
    }

    return new ArrayList<>();
  }

  /**
   * Gets a list of devices.
   *
   * @param deviceListFilter A filter to find specific devices.
   * @return
   */
  public List<Device> getDevices(DeviceListFilter deviceListFilter) {
    try {
      return DeviceEndpoint.getDevices(apiUri, this.token.getAccessToken(), deviceListFilter);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst getting devices.", ex);
      Thread.currentThread().interrupt();
    }

    return new ArrayList<>();
  }

  /**
   * Get information of a device by its UUID.
   *
   * @param uuid The UUID of the device.
   * @param tenantFilter A filter for specific tenants.
   * @return
   */
  public Device getDeviceInformation(UUID uuid, TenantFilter tenantFilter) {
    try {
      return DeviceEndpoint.getDeviceInformation(apiUri, this.token.getAccessToken(), uuid, tenantFilter);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst getting device information.", ex);
      Thread.currentThread().interrupt();
    }

    return null;
  }

  /**
   * Update a device by its UUID.
   *
   * @param uuid The UUID of the device.
   * @param devicesRequest The information of the device.
   */
  public void updateDevice(UUID uuid, DevicesRequest devicesRequest) {
    try {
      DeviceEndpoint.updateDevice(apiUri, this.token.getAccessToken(), uuid, devicesRequest);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst updating device.", ex);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Delete a device by its UUID.
   *
   * @param uuid The UUID of the device.
   */
  public void deleteDevice(UUID uuid) {
    try {
      DeviceEndpoint.deleteDevice(apiUri, this.token.getAccessToken(), uuid);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst deleting device.", ex);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Creates a folder identified by its name in the given parent folder
   * identified by its UUID.
   *
   * @param parentUuid The UUID of the parent node. Can be null.
   * @param name The name of the folder.
   * @return Created folder.
   */
  public FolderNode createFolder(UUID parentUuid, String name) {
    try {
      return SyncAndShareNodesEndpoint.createFolder(apiUri, this.token.getAccessToken(), new SyncAndShareNode(parentUuid, name, null));
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst creating folder.", ex);
      Thread.currentThread().interrupt();
    }

    return null;
  }

  /**
   * Creates a folder identified by its path.
   *
   * @param path The path of the folder.
   * @return Created folder.
   */
  public FolderNode createFolderByPath(String path) {
    try {
      SyncAndShareNodesEndpoint.createFolder(apiUri, this.token.getAccessToken(), new SyncAndShareNode(null, null, path));
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst creating folder by path.", ex);
      Thread.currentThread().interrupt();
    }

    return null;
  }
  
  /**
   * Get information about a folder or file identified by its UUID.
   *
   * @param uuid The UUID of the folder or file.
   * @return The folder or file node.
   */
  public Node getFolderOrFile(UUID uuid) {
    try {
      return SyncAndShareNodesEndpoint.getNode(apiUri, this.token.getAccessToken(), uuid);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst getting node info.", ex);
      Thread.currentThread().interrupt();
    }

    return null;
  }
  
  /**
   * Deletes a folder or file identified by its UUID.
   *
   * @param uuid The UUID of the folder or file.
   * @return Created folder.
   */
  public boolean deleteFolderOrFile(UUID uuid) {
    try {
      SyncAndShareNodesEndpoint.deleteNode(apiUri, this.token.getAccessToken(), uuid);
    } catch (IOException | InterruptedException | URISyntaxException ex) {
      LOGGER.log(Level.SEVERE, "Error whilst creating folder by path.", ex);
      Thread.currentThread().interrupt();
      return false;
    }

    return true;
  }
}
