package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isNull() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String getValueAsText() {
        return "null";
    }

    @Override // org.codehaus.jackson.JsonNode
    public int getValueAsInt(int i) {
        return 0;
    }

    @Override // org.codehaus.jackson.JsonNode
    public long getValueAsLong(long j) {
        return 0L;
    }

    @Override // org.codehaus.jackson.JsonNode
    public double getValueAsDouble(double d) {
        return 0.0d;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNull();
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }
}
