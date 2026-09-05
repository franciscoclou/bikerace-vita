package org.codehaus.jackson.node;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isPojo() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public String getValueAsText() {
        return this._value == null ? "null" : this._value.toString();
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean getValueAsBoolean(boolean z) {
        if (this._value != null && (this._value instanceof Boolean)) {
            return ((Boolean) this._value).booleanValue();
        }
        return z;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int getValueAsInt(int i) {
        if (this._value instanceof Number) {
            return ((Number) this._value).intValue();
        }
        return i;
    }

    @Override // org.codehaus.jackson.JsonNode
    public long getValueAsLong(long j) {
        if (this._value instanceof Number) {
            return ((Number) this._value).longValue();
        }
        return j;
    }

    @Override // org.codehaus.jackson.JsonNode
    public double getValueAsDouble(double d) {
        if (this._value instanceof Number) {
            return ((Number) this._value).doubleValue();
        }
        return d;
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(this._value);
        }
    }

    public Object getPojo() {
        return this._value;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            POJONode pOJONode = (POJONode) obj;
            if (this._value == null) {
                return pOJONode._value == null;
            }
            return this._value.equals(pOJONode._value);
        }
        return false;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // org.codehaus.jackson.node.ValueNode, org.codehaus.jackson.JsonNode
    public String toString() {
        return String.valueOf(this._value);
    }
}
