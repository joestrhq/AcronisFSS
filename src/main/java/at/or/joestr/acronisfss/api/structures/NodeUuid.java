/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import java.util.UUID;

/**
 *
 * @author Joel
 */
public class NodeUuid {

  UUID uuid;

  public NodeUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  /**
   * This method converts an
   * <a href="https://developer.acronis.com/doc/files/v1/#/http/models/structures/node-uuid">Acronis
   * Node UUID</a> to a normal {@link UUID UUID} with the exception of the {@code 0} Node UUID being
   * represented as zero UUID.
   *
   * @param uuidInStringRepresentation The Acronis Node UUID
   *
   * @return A standard UUID
   */
  public UUID fromStringRepresentation(String uuidInStringRepresentation) {
    if (uuidInStringRepresentation.equalsIgnoreCase("0")) {
      this.uuid = new UUID(0, 0);
    }

    this.uuid = UUID.fromString(uuidInStringRepresentation);

    return this.uuid;
  }
}
