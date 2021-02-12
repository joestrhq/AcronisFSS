/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author Joel
 */
public class Device {
  private UUID uuid;
  private String appVersion;
  //private Filesystem filesystem;
  private LocalDateTime lastContactTime;
  private String model;
  private String name;
  private String notes;
  private String systemPlatform;
  private String systemVersion;
  //private ClientType clientType;
  private UUID userUuid;
  private String userName;
  //private ? actions;
}
