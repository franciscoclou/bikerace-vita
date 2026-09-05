package com.amazonaws.services.dynamodb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchResponse {
    private Double consumedCapacityUnits;
    private List<Map<String, AttributeValue>> items;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchResponse)) {
            return false;
        }
        BatchResponse batchResponse = (BatchResponse) obj;
        if ((batchResponse.getItems() == null) ^ (getItems() == null)) {
            return false;
        }
        if (batchResponse.getItems() != null && !batchResponse.getItems().equals(getItems())) {
            return false;
        }
        if ((batchResponse.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return batchResponse.getConsumedCapacityUnits() == null || batchResponse.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
    }

    public Double getConsumedCapacityUnits() {
        return this.consumedCapacityUnits;
    }

    public List<Map<String, AttributeValue>> getItems() {
        return this.items;
    }

    public int hashCode() {
        return (((getItems() == null ? 0 : getItems().hashCode()) + 31) * 31) + (getConsumedCapacityUnits() != null ? getConsumedCapacityUnits().hashCode() : 0);
    }

    public void setConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
    }

    public void setItems(Collection<Map<String, AttributeValue>> collection) {
        if (collection == null) {
            this.items = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.items = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.items != null) {
            sb.append("Items: " + this.items + ", ");
        }
        if (this.consumedCapacityUnits != null) {
            sb.append("ConsumedCapacityUnits: " + this.consumedCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public BatchResponse withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }

    public BatchResponse withItems(Collection<Map<String, AttributeValue>> collection) {
        if (collection == null) {
            this.items = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.items = arrayList;
        }
        return this;
    }

    public BatchResponse withItems(Map<String, AttributeValue>... mapArr) {
        if (getItems() == null) {
            setItems(new ArrayList(mapArr.length));
        }
        for (Map<String, AttributeValue> map : mapArr) {
            getItems().add(map);
        }
        return this;
    }
}
