/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Joel
 */
public abstract class Node {

  @SerializedName("uuid")
  private NodeUuid nodeUuid;
  @SerializedName("name")
  private NodeName nodeName;
  @SerializedName("is_directory")
  private boolean directory;
  private long size;
  @SerializedName("file_modification_date")
  private LocalDateTime fileModificationDate;
  @SerializedName("is_deleted")
  private boolean deleted;
  private Synced synced;
  @SerializedName("preview_type")
  private PreviewType previewType;
  @SerializedName("expiration_date")
  private LocalDateTime expirationDate;
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
  @SerializedName("parent_uuid")
  private NodeUuid parentUuid;
  @SerializedName("has_writable_children")
  private boolean writableChildren;
  //private ? officeOnlinePermissions;
  private Owner owner;
  //private Permissions permissions;
  //private Actions actions;

  public Node() {
  }

  public NodeUuid getNodeUuid() {
    return nodeUuid;
  }

  public void setNodeUuid(NodeUuid nodeUuid) {
    this.nodeUuid = nodeUuid;
  }

  public NodeName getNodeName() {
    return nodeName;
  }

  public void setNodeName(NodeName nodeName) {
    this.nodeName = nodeName;
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

  public LocalDateTime getFileModificationDate() {
    return fileModificationDate;
  }

  public void setFileModificationDate(LocalDateTime fileModificationDate) {
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

  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDateTime expirationDate) {
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

  public NodeUuid getParentUuid() {
    return parentUuid;
  }

  public void setParentUuid(NodeUuid parentUuid) {
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
