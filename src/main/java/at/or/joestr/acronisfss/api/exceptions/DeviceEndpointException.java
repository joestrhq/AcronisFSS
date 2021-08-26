/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.exceptions;

/**
 *
 * @author joestr
 */
public class DeviceEndpointException extends RuntimeException {

  public DeviceEndpointException() {
  }

  public DeviceEndpointException(String message) {
    super(message);
  }

  public DeviceEndpointException(String message, Throwable cause) {
    super(message, cause);
  }

  public DeviceEndpointException(Throwable cause) {
    super(cause);
  }

  public DeviceEndpointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
