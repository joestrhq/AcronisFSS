/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package at.or.joestr.acronisfss.api.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author joestr
 */
public class NodeUuidTypeAdapter extends TypeAdapter<UUID> {

  @Override
  public void write(JsonWriter out, UUID value) throws IOException {
    if (value == null) {
      out.value("0");
    } else {
      out.value(value.toString());
    }
  }

  @Override
  public UUID read(JsonReader in) throws IOException {
    JsonToken token = in.peek();
    
    if (null == token) {
      throw new MalformedJsonException("Malformed JSON at token: " + token);
    } else switch (token) {
      case NULL:
        in.nextNull();
        return null;
      case STRING:
        String value = in.nextString();
        
        if (value.equals("0")) {
          return null;
        } else {
          return UUID.fromString(value);
        }
      default:
        throw new MalformedJsonException("Malformed JSON at token: " + token);
    }
  }
  
}
