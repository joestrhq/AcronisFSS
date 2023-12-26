/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package at.or.joestr.acronisfss.api.structures;

import at.or.joestr.acronisfss.api.typeadapters.NodeUuidTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;

/**
 *
 * @author joestr
 */
public class SyncAndShareNode {
  @JsonAdapter(value = NodeUuidTypeAdapter.class, nullSafe = false)
  @SerializedName("parent_uuid")
  private UUID parentUuid;
  private String name;
  private String path;

  public SyncAndShareNode() {
  }

  public SyncAndShareNode(UUID parentUuid, String name, String path) {
    if (name != null && path != null) {
      throw new IllegalArgumentException("'name' and 'path' are mutually exclusive");
    }
    
    this.parentUuid = parentUuid;
    this.name = name;
    this.path = path;
  }

  public UUID getParentUuid() {
    return parentUuid;
  }

  public void setParentUuid(UUID parentUuid) {
    this.parentUuid = parentUuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (path != null && name != null) {
      throw new IllegalArgumentException("'name' and 'path' are mutually exclusive");
    }
    
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    if (name != null && path != null) {
      throw new IllegalArgumentException("'name' and 'path' are mutually exclusive");
    }
    
    this.path = path;
  }

  
}
