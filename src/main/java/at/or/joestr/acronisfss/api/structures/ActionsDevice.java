/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

/**
 * Relative URLs to actions that can be performed on the device
 * @author joestr
 */
public class ActionsDevice {
  private String remove;

  public ActionsDevice(String remove) {
    this.remove = remove;
  }

  public String getRemove() {
    return remove;
  }

  public void setRemove(String remove) {
    this.remove = remove;
  }
}
