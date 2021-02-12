/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

/**
 * Represents an
 * <a href="https://developer.acronis.com/doc/files/v1/#/http/models/structures/node-name">Acronis
 * Node Name</a>.
 *
 * @author Joel
 */
public class NodeName {

  private String name;
  private int length;

  public NodeName(String name, int length) {
    this.checkLength(name, length);
    this.length = length;
    this.name = name;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.checkLength(this.name, length);
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.checkLength(name, this.length);
    this.name = name;
  }

  private void checkLength(String name, int length) {
    if (name.length() > length) {
      throw new IllegalArgumentException(
        String.format(
          "The name had a length of {0} but it is only allowed to have a length of {1}",
          new Object[] {name.length(), length}
        )
      );
    }
  }
}
