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
public class DevicesRequest {
  @SerializedName("device")
  private DeviceDevicesRequest deviceDevicesRequest;

  public DevicesRequest() {
  }
  
  public DevicesRequest(DeviceDevicesRequest deviceDevicesRequest) {
    this.deviceDevicesRequest = deviceDevicesRequest;
  }

  public DeviceDevicesRequest getDeviceDevicesRequest() {
    return deviceDevicesRequest;
  }

  public void setDeviceDevicesRequest(DeviceDevicesRequest deviceDevicesRequest) {
    this.deviceDevicesRequest = deviceDevicesRequest;
  }
  
  
}
