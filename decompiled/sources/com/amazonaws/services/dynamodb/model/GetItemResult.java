package com.amazonaws.services.dynamodb.model;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetItemResult {
    private Double consumedCapacityUnits;
    private Map<String, AttributeValue> item;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetItemResult)) {
            return false;
        }
        GetItemResult getItemResult = (GetItemResult) obj;
        if ((getItemResult.getItem() == null) ^ (getItem() == null)) {
            return false;
        }
        if (getItemResult.getItem() != null && !getItemResult.getItem().equals(getItem())) {
            return false;
        }
        if ((getItemResult.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return getItemResult.getConsumedCapacityUnits() == null || getItemResult.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
    }

    public Double getConsumedCapacityUnits() {
        return this.consumedCapacityUnits;
    }

    public Map<String, AttributeValue> getItem() {
        return this.item;
    }

    public int hashCode() {
        return (((getItem() == null ? 0 : getItem().hashCode()) + 31) * 31) + (getConsumedCapacityUnits() != null ? getConsumedCapacityUnits().hashCode() : 0);
    }

    public void setConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
    }

    public void setItem(Map<String, AttributeValue> map) {
        this.item = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.item != null) {
            sb.append("Item: " + this.item + ", ");
        }
        if (this.consumedCapacityUnits != null) {
            sb.append("ConsumedCapacityUnits: " + this.consumedCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public GetItemResult withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }

    public GetItemResult withItem(Map<String, AttributeValue> map) {
        setItem(map);
        return this;
    }
}
