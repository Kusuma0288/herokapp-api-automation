package au.com.woolworths.helpers.rewards.deserializers;

import au.com.woolworths.model.rewards.Analytics;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class AnalyticsDeserializer extends StdDeserializer<Analytics> {

  public AnalyticsDeserializer() {
    this(null);
  }

  public AnalyticsDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Analytics deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String visitorId = node.get("data").get("visitorId").asText();

    return new Analytics(visitorId);
  }
}