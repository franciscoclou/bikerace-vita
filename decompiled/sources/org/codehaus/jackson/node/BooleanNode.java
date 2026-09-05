package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class BooleanNode extends ValueNode {
    public static final BooleanNode TRUE = new BooleanNode();
    public static final BooleanNode FALSE = new BooleanNode();

    private BooleanNode() {
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return this == TRUE ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isBoolean() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean getBooleanValue() {
        return this == TRUE;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String getValueAsText() {
        return this == TRUE ? "true" : "false";
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean getValueAsBoolean() {
        return this == TRUE;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean getValueAsBoolean(boolean z) {
        return this == TRUE;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int getValueAsInt(int i) {
        return this == TRUE ? 1 : 0;
    }

    @Override // org.codehaus.jackson.JsonNode
    public long getValueAsLong(long j) {
        return this == TRUE ? 1L : 0L;
    }

    @Override // org.codehaus.jackson.JsonNode
    public double getValueAsDouble(double d) {
        return this == TRUE ? 1.0d : 0.0d;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBoolean(this == TRUE);
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }
}
