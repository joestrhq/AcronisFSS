/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Joel
 */
public class Device {
  private UUID uuid;
  private String appVersion;
  private Filesystem filesystem;
  private LocalDateTime lastContactTime;
  private String model;
  private String name;
  private String notes;
  private String systemPlatform;
  private String systemVersion;
  private ClientType clientType;
  private UUID userUuid;
  private String userName;
  private ActionsDevice actions;

  public Device(UUID uuid, String appVersion, Filesystem filesystem, LocalDateTime lastContactTime, String name, String systemPlatform, String systemVersion, ClientType clientType, UUID userUuid, String userName, ActionsDevice actions) {
    this.uuid = uuid;
    this.appVersion = appVersion;
    this.filesystem = filesystem;
    this.lastContactTime = lastContactTime;
    this.name = name;
    this.systemPlatform = systemPlatform;
    this.systemVersion = systemVersion;
    this.clientType = clientType;
    this.userUuid = userUuid;
    this.userName = userName;
    this.actions = actions;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public Filesystem getFilesystem() {
    return filesystem;
  }

  public void setFilesystem(Filesystem filesystem) {
    this.filesystem = filesystem;
  }

  public LocalDateTime getLastContactTime() {
    return lastContactTime;
  }

  public void setLastContactTime(LocalDateTime lastContactTime) {
    this.lastContactTime = lastContactTime;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getSystemPlatform() {
    return systemPlatform;
  }

  public void setSystemPlatform(String systemPlatform) {
    this.systemPlatform = systemPlatform;
  }

  public String getSystemVersion() {
    return systemVersion;
  }

  public void setSystemVersion(String systemVersion) {
    this.systemVersion = systemVersion;
  }

  public ClientType getClientType() {
    return clientType;
  }

  public void setClientType(ClientType clientType) {
    this.clientType = clientType;
  }

  public UUID getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(UUID userUuid) {
    this.userUuid = userUuid;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ActionsDevice getActions() {
    return actions;
  }

  public void setActions(ActionsDevice actions) {
    this.actions = actions;
  }
}
