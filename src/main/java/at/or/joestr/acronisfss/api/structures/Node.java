/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import at.or.joestr.acronisfss.api.typeadapters.NodeUuidTypeAdapter;
import at.or.joestr.acronisfss.api.typeadapters.OffsetDateTimeTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Joel
 */
public abstract class Node {

  @JsonAdapter(value = NodeUuidTypeAdapter.class, nullSafe = false)
  @SerializedName("uuid")
  private UUID uuid;
  @SerializedName("name")
  private String name;
  @SerializedName("is_directory")
  private boolean directory;
  private long size;
  @JsonAdapter(OffsetDateTimeTypeAdapter.class)
  @SerializedName("file_modification_date")
  private OffsetDateTime fileModificationDate;
  @SerializedName("is_deleted")
  private boolean deleted;
  private Synced synced;
  @SerializedName("preview_type")
  private PreviewType previewType;
  @JsonAdapter(OffsetDateTimeTypeAdapter.class)
  @SerializedName("expiration_date")
  private OffsetDateTime expirationDate;
  @SerializedName("read_only")
  private boolean readOnly;
  @SerializedName("has_active_links")
  private boolean hasActiveLinks;
  @SerializedName("has_shared_child_nodes")
  private boolean hasSharedChildNodes;
  @SerializedName("is_shared")
  private boolean shared;
  @SerializedName("owner_email")
  private String ownerEmail;
  private String path;
  private List<Parents> parents;
  private String checksum;
  @JsonAdapter(value = NodeUuidTypeAdapter.class, nullSafe = false)
  @SerializedName("parent_uuid")
  private UUID parentUuid;
  @SerializedName("has_writable_children")
  private boolean writableChildren;
  //private ? officeOnlinePermissions;
  private Owner owner;
  //private Permissions permissions;
  //private Actions actions;

  public Node() {
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setNodeUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setNodeName(String name) {
    this.name = name;
  }

  public boolean isDirectory() {
    return directory;
  }

  public void setDirectory(boolean directory) {
    this.directory = directory;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public OffsetDateTime getFileModificationDate() {
    return fileModificationDate;
  }

  public void setFileModificationDate(OffsetDateTime fileModificationDate) {
    this.fileModificationDate = fileModificationDate;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Synced getSynced() {
    return synced;
  }

  public void setSynced(Synced synced) {
    this.synced = synced;
  }

  public PreviewType getPreviewType() {
    return previewType;
  }

  public void setPreviewType(PreviewType previewType) {
    this.previewType = previewType;
  }

  public OffsetDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(OffsetDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public boolean isReadOnly() {
    return readOnly;
  }

  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

  public boolean isHasActiveLinks() {
    return hasActiveLinks;
  }

  public void setHasActiveLinks(boolean hasActiveLinks) {
    this.hasActiveLinks = hasActiveLinks;
  }

  public boolean isHasSharedChildNodes() {
    return hasSharedChildNodes;
  }

  public void setHasSharedChildNodes(boolean hasSharedChildNodes) {
    this.hasSharedChildNodes = hasSharedChildNodes;
  }

  public boolean isShared() {
    return shared;
  }

  public void setShared(boolean shared) {
    this.shared = shared;
  }

  public String getOwnerEmail() {
    return ownerEmail;
  }

  public void setOwnerEmail(String ownerEmail) {
    this.ownerEmail = ownerEmail;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<Parents> getParents() {
    return parents;
  }

  public void setParents(List<Parents> parents) {
    this.parents = parents;
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public UUID getParentUuid() {
    return parentUuid;
  }

  public void setParentUuid(UUID parentUuid) {
    this.parentUuid = parentUuid;
  }

  public boolean isWritableChildren() {
    return writableChildren;
  }

  public void setWritableChildren(boolean writableChildren) {
    this.writableChildren = writableChildren;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }
  
  
}
