package com.amazonaws.services.dynamodb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ScanResult {
    private Double consumedCapacityUnits;
    private Integer count;
    private List<Map<String, AttributeValue>> items;
    private Key lastEvaluatedKey;
    private Integer scannedCount;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScanResult)) {
            return false;
        }
        ScanResult scanResult = (ScanResult) obj;
        if ((scanResult.getItems() == null) ^ (getItems() == null)) {
            return false;
        }
        if (scanResult.getItems() != null && !scanResult.getItems().equals(getItems())) {
            return false;
        }
        if ((scanResult.getCount() == null) ^ (getCount() == null)) {
            return false;
        }
        if (scanResult.getCount() != null && !scanResult.getCount().equals(getCount())) {
            return false;
        }
        if ((scanResult.getScannedCount() == null) ^ (getScannedCount() == null)) {
            return false;
        }
        if (scanResult.getScannedCount() != null && !scanResult.getScannedCount().equals(getScannedCount())) {
            return false;
        }
        if ((scanResult.getLastEvaluatedKey() == null) ^ (getLastEvaluatedKey() == null)) {
            return false;
        }
        if (scanResult.getLastEvaluatedKey() != null && !scanResult.getLastEvaluatedKey().equals(getLastEvaluatedKey())) {
            return false;
        }
        if ((scanResult.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return scanResult.getConsumedCapacityUnits() == null || scanResult.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
    }

    public Double getConsumedCapacityUnits() {
        return this.consumedCapacityUnits;
    }

    public Integer getCount() {
        return this.count;
    }

    public List<Map<String, AttributeValue>> getItems() {
        return this.items;
    }

    public Key getLastEvaluatedKey() {
        return this.lastEvaluatedKey;
    }

    public Integer getScannedCount() {
        return this.scannedCount;
    }

    public int hashCode() {
        return (((getLastEvaluatedKey() == null ? 0 : getLastEvaluatedKey().hashCode()) + (((getScannedCount() == null ? 0 : getScannedCount().hashCode()) + (((getCount() == null ? 0 : getCount().hashCode()) + (((getItems() == null ? 0 : getItems().hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + (getConsumedCapacityUnits() != null ? getConsumedCapacityUnits().hashCode() : 0);
    }

    public void setConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
    }

    public void setCount(Integer num) {
        this.count = num;
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

    public void setLastEvaluatedKey(Key key) {
        this.lastEvaluatedKey = key;
    }

    public void setScannedCount(Integer num) {
        this.scannedCount = num;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.items != null) {
            sb.append("Items: " + this.items + ", ");
        }
        if (this.count != null) {
            sb.append("Count: " + this.count + ", ");
        }
        if (this.scannedCount != null) {
            sb.append("ScannedCount: " + this.scannedCount + ", ");
        }
        if (this.lastEvaluatedKey != null) {
            sb.append("LastEvaluatedKey: " + this.lastEvaluatedKey + ", ");
        }
        if (this.consumedCapacityUnits != null) {
            sb.append("ConsumedCapacityUnits: " + this.consumedCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ScanResult withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }

    public ScanResult withCount(Integer num) {
        this.count = num;
        return this;
    }

    public ScanResult withItems(Collection<Map<String, AttributeValue>> collection) {
        if (collection == null) {
            this.items = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.items = arrayList;
        }
        return this;
    }

    public ScanResult withItems(Map<String, AttributeValue>... mapArr) {
        if (getItems() == null) {
            setItems(new ArrayList(mapArr.length));
        }
        for (Map<String, AttributeValue> map : mapArr) {
            getItems().add(map);
        }
        return this;
    }

    public ScanResult withLastEvaluatedKey(Key key) {
        this.lastEvaluatedKey = key;
        return this;
    }

    public ScanResult withScannedCount(Integer num) {
        this.scannedCount = num;
        return this;
    }
}
