/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import at.or.joestr.acronisfss.api.typeadapters.NodeUuidTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import java.util.UUID;

/**
 *
 * @author Joel
 */
public class Parents {
  private String name;
  @JsonAdapter(NodeUuidTypeAdapter.class)
  private UUID uuid;
  private boolean root;
}
