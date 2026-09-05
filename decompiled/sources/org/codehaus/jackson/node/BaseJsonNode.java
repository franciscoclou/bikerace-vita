package org.codehaus.jackson.node;

import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class BaseJsonNode extends JsonNode implements JsonSerializableWithType {
    @Override // org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer);

    protected BaseJsonNode() {
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        return null;
    }

    @Override // org.codehaus.jackson.JsonNode
    public final JsonNode findPath(String str) {
        JsonNode jsonNodeFindValue = findValue(str);
        if (jsonNodeFindValue == null) {
            return MissingNode.getInstance();
        }
        return jsonNodeFindValue;
    }

    @Override // org.codehaus.jackson.JsonNode
    public ObjectNode findParent(String str) {
        return null;
    }

    @Override // org.codehaus.jackson.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        return list;
    }

    @Override // org.codehaus.jackson.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        return list;
    }

    @Override // org.codehaus.jackson.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        return list;
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonParser traverse() {
        return new TreeTraversingParser(this);
    }

    @Override // org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return null;
    }

    @Override // org.codehaus.jackson.JsonNode
    public final void writeTo(JsonGenerator jsonGenerator) {
        serialize(jsonGenerator, null);
    }
}
