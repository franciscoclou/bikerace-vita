package com.amazonaws.services.dynamodb.model;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UpdateItemResult {
    private Map<String, AttributeValue> attributes;
    private Double consumedCapacityUnits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateItemResult)) {
            return false;
        }
        UpdateItemResult updateItemResult = (UpdateItemResult) obj;
        if ((updateItemResult.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (updateItemResult.getAttributes() != null && !updateItemResult.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((updateItemResult.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return updateItemResult.getConsumedCapacityUnits() == null || updateItemResult.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
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

    public UpdateItemResult withAttributes(Map<String, AttributeValue> map) {
        setAttributes(map);
        return this;
    }

    public UpdateItemResult withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }
}
