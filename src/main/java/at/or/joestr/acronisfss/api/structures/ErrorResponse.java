/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package at.or.joestr.acronisfss.api.structures;

/**
 *
 * @author joestr
 */
public class ErrorResponse {

  private Error error;

  public ErrorResponse() {
  }

  public ErrorResponse(Error error) {
    this.error = error;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }
}
