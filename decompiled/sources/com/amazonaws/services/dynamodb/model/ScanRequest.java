package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ScanRequest extends e {
    private List<String> attributesToGet;
    private Boolean count;
    private Key exclusiveStartKey;
    private Integer limit;
    private Map<String, Condition> scanFilter;
    private String tableName;

    public ScanRequest() {
    }

    public ScanRequest(String str) {
        this.tableName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScanRequest)) {
            return false;
        }
        ScanRequest scanRequest = (ScanRequest) obj;
        if ((scanRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (scanRequest.getTableName() != null && !scanRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((scanRequest.getAttributesToGet() == null) ^ (getAttributesToGet() == null)) {
            return false;
        }
        if (scanRequest.getAttributesToGet() != null && !scanRequest.getAttributesToGet().equals(getAttributesToGet())) {
            return false;
        }
        if ((scanRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (scanRequest.getLimit() != null && !scanRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((scanRequest.isCount() == null) ^ (isCount() == null)) {
            return false;
        }
        if (scanRequest.isCount() != null && !scanRequest.isCount().equals(isCount())) {
            return false;
        }
        if ((scanRequest.getScanFilter() == null) ^ (getScanFilter() == null)) {
            return false;
        }
        if (scanRequest.getScanFilter() != null && !scanRequest.getScanFilter().equals(getScanFilter())) {
            return false;
        }
        if ((scanRequest.getExclusiveStartKey() == null) ^ (getExclusiveStartKey() == null)) {
            return false;
        }
        return scanRequest.getExclusiveStartKey() == null || scanRequest.getExclusiveStartKey().equals(getExclusiveStartKey());
    }

    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public Boolean getCount() {
        return this.count;
    }

    public Key getExclusiveStartKey() {
        return this.exclusiveStartKey;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Map<String, Condition> getScanFilter() {
        return this.scanFilter;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((getScanFilter() == null ? 0 : getScanFilter().hashCode()) + (((isCount() == null ? 0 : isCount().hashCode()) + (((getLimit() == null ? 0 : getLimit().hashCode()) + (((getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31) + (getExclusiveStartKey() != null ? getExclusiveStartKey().hashCode() : 0);
    }

    public Boolean isCount() {
        return this.count;
    }

    public void setAttributesToGet(Collection<String> collection) {
        if (collection == null) {
            this.attributesToGet = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.attributesToGet = arrayList;
    }

    public void setCount(Boolean bool) {
        this.count = bool;
    }

    public void setExclusiveStartKey(Key key) {
        this.exclusiveStartKey = key;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setScanFilter(Map<String, Condition> map) {
        this.scanFilter = map;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.tableName != null) {
            sb.append("TableName: " + this.tableName + ", ");
        }
        if (this.attributesToGet != null) {
            sb.append("AttributesToGet: " + this.attributesToGet + ", ");
        }
        if (this.limit != null) {
            sb.append("Limit: " + this.limit + ", ");
        }
        if (this.count != null) {
            sb.append("Count: " + this.count + ", ");
        }
        if (this.scanFilter != null) {
            sb.append("ScanFilter: " + this.scanFilter + ", ");
        }
        if (this.exclusiveStartKey != null) {
            sb.append("ExclusiveStartKey: " + this.exclusiveStartKey + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ScanRequest withAttributesToGet(Collection<String> collection) {
        if (collection == null) {
            this.attributesToGet = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributesToGet = arrayList;
        }
        return this;
    }

    public ScanRequest withAttributesToGet(String... strArr) {
        if (getAttributesToGet() == null) {
            setAttributesToGet(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAttributesToGet().add(str);
        }
        return this;
    }

    public ScanRequest withCount(Boolean bool) {
        this.count = bool;
        return this;
    }

    public ScanRequest withExclusiveStartKey(Key key) {
        this.exclusiveStartKey = key;
        return this;
    }

    public ScanRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public ScanRequest withScanFilter(Map<String, Condition> map) {
        setScanFilter(map);
        return this;
    }

    public ScanRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
