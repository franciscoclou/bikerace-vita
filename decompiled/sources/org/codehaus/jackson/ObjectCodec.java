package org.codehaus.jackson;

import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ObjectCodec {
    public abstract JsonNode createArrayNode();

    public abstract JsonNode createObjectNode();

    public abstract JsonNode readTree(JsonParser jsonParser);

    public abstract <T> T readValue(JsonParser jsonParser, Class<T> cls);

    public abstract <T> T readValue(JsonParser jsonParser, JavaType javaType);

    public abstract <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference);

    public abstract JsonParser treeAsTokens(JsonNode jsonNode);

    public abstract <T> T treeToValue(JsonNode jsonNode, Class<T> cls);

    public abstract void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode);

    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj);

    protected ObjectCodec() {
    }
}
