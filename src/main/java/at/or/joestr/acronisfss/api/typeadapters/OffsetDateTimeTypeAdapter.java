/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package at.or.joestr.acronisfss.api.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import static com.google.gson.stream.JsonToken.NULL;
import static com.google.gson.stream.JsonToken.STRING;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author joestr
 */
public class OffsetDateTimeTypeAdapter extends TypeAdapter<OffsetDateTime> {

  @Override
  public void write(JsonWriter out, OffsetDateTime value) throws IOException {
    out.value(value.toString());
  }

  @Override
  public OffsetDateTime read(JsonReader in) throws IOException {
    JsonToken token = in.peek();
    
    if (null == token) {
      throw new MalformedJsonException("Malformed JSON at token: " + token);
    } else switch (token) {
      case NULL:
        in.nextNull();
        return null;
      case STRING:
        String value = in.nextString();
        
        return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
      default:
        throw new MalformedJsonException("Malformed JSON at token: " + token);
    }
  }
  
}
