/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package at.or.joestr.acronisfss.api.structures;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author joestr
 */
public class SyncAndShareNodesRequest {
  @SerializedName("sync_and_share_node")
  private SyncAndShareNode syncAndShareNode;

  public SyncAndShareNodesRequest() {
  }

  public SyncAndShareNodesRequest(SyncAndShareNode syncAndShareNode) {
    this.syncAndShareNode = syncAndShareNode;
  }

  public SyncAndShareNode getSyncAndShareNode() {
    return syncAndShareNode;
  }

  public void setSyncAndShareNode(SyncAndShareNode syncAndShareNode) {
    this.syncAndShareNode = syncAndShareNode;
  }
  
  
}
