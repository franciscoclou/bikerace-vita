package com.amazonaws.services.dynamodb.model;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PutItemResult {
    private Map<String, AttributeValue> attributes;
    private Double consumedCapacityUnits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutItemResult)) {
            return false;
        }
        PutItemResult putItemResult = (PutItemResult) obj;
        if ((putItemResult.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (putItemResult.getAttributes() != null && !putItemResult.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((putItemResult.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return putItemResult.getConsumedCapacityUnits() == null || putItemResult.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
    }

    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    public Double getConsumedCapacityUnits() {
        return this.consumedCapacityUnits;
    }

    public int hashCode() {
        return (((getAttributes() == null ? 0 : getAttributes().hashCode()) + 31) * 31) + (getConsumedCapacityUnits() != null ? getConsumedCapacityUnits().hashCode() : 0);
    }

    public void setAttributes(Map<String, AttributeValue> map) {
        this.attributes = map;
    }

    public void setConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.attributes != null) {
            sb.append("Attributes: " + this.attributes + ", ");
        }
        if (this.consumedCapacityUnits != null) {
            sb.append("ConsumedCapacityUnits: " + this.consumedCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public PutItemResult withAttributes(Map<String, AttributeValue> map) {
        setAttributes(map);
        return this;
    }

    public PutItemResult withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }
}
