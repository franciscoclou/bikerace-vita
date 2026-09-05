package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.JsonParser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class NumericNode extends ValueNode {
    @Override // org.codehaus.jackson.JsonNode
    public abstract BigInteger getBigIntegerValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract BigDecimal getDecimalValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract double getDoubleValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract int getIntValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract long getLongValue();

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract JsonParser.NumberType getNumberType();

    @Override // org.codehaus.jackson.JsonNode
    public abstract Number getNumberValue();

    @Override // org.codehaus.jackson.JsonNode
    public abstract String getValueAsText();

    protected NumericNode() {
    }

    @Override // org.codehaus.jackson.JsonNode
    public final boolean isNumber() {
        return true;
    }

    @Override // org.codehaus.jackson.JsonNode
    public int getValueAsInt() {
        return getIntValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public int getValueAsInt(int i) {
        return getIntValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public long getValueAsLong() {
        return getLongValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public long getValueAsLong(long j) {
        return getLongValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public double getValueAsDouble() {
        return getDoubleValue();
    }

    @Override // org.codehaus.jackson.JsonNode
    public double getValueAsDouble(double d) {
        return getDoubleValue();
    }
}
