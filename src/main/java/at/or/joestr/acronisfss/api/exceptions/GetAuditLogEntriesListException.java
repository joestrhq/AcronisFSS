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
public class GetAuditLogEntriesListException extends RuntimeException {

  public GetAuditLogEntriesListException() {
  }

  public GetAuditLogEntriesListException(String message) {
    super(message);
  }

  public GetAuditLogEntriesListException(String message, Throwable cause) {
    super(message, cause);
  }

  public GetAuditLogEntriesListException(Throwable cause) {
    super(cause);
  }

  public GetAuditLogEntriesListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
