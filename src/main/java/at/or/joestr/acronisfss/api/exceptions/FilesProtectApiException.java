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
public class FilesProtectApiException extends RuntimeException {

  public FilesProtectApiException() {
  }

  public FilesProtectApiException(String message) {
    super(message);
  }

  public FilesProtectApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public FilesProtectApiException(Throwable cause) {
    super(cause);
  }

  public FilesProtectApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
