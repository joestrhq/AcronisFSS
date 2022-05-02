/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.utils;

import at.or.joestr.acronisfss.api.structures.Device;
import at.or.joestr.acronisfss.api.structures.SyncAndShareNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author joestr
 */
public class RequestUtil {
  public static JsonObject makeDevicesRequest(Device device) {
    JsonObject request = new JsonObject();
    
    JsonObject requestDevice = new JsonObject();
    requestDevice.addProperty("notes", device.getNotes());
    
    request.add("device", requestDevice);
    return request;
  }
  
  public static JsonObject makeSyncAndShareRequest(SyncAndShareNode node) {
    JsonObject request = new JsonObject();
    
    request.add("sync_and_share_nodes", new Gson().toJsonTree(node));
    
    return request;
  }
}
