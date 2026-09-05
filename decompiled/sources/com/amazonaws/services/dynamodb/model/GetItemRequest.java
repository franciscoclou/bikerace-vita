package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetItemRequest extends e {
    private List<String> attributesToGet;
    private Boolean consistentRead;
    private Key key;
    private String tableName;

    public GetItemRequest() {
    }

    public GetItemRequest(String str, Key key) {
        this.tableName = str;
        this.key = key;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetItemRequest)) {
            return false;
        }
        GetItemRequest getItemRequest = (GetItemRequest) obj;
        if ((getItemRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (getItemRequest.getTableName() != null && !getItemRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((getItemRequest.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (getItemRequest.getKey() != null && !getItemRequest.getKey().equals(getKey())) {
            return false;
        }
        if ((getItemRequest.getAttributesToGet() == null) ^ (getAttributesToGet() == null)) {
            return false;
        }
        if (getItemRequest.getAttributesToGet() != null && !getItemRequest.getAttributesToGet().equals(getAttributesToGet())) {
            return false;
        }
        if ((getItemRequest.isConsistentRead() == null) ^ (isConsistentRead() == null)) {
            return false;
        }
        return getItemRequest.isConsistentRead() == null || getItemRequest.isConsistentRead().equals(isConsistentRead());
    }

    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public Boolean getConsistentRead() {
        return this.consistentRead;
    }

    public Key getKey() {
        return this.key;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode()) + (((getKey() == null ? 0 : getKey().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31) + (isConsistentRead() != null ? isConsistentRead().hashCode() : 0);
    }

    public Boolean isConsistentRead() {
        return this.consistentRead;
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

    public void setConsistentRead(Boolean bool) {
        this.consistentRead = bool;
    }

    public void setKey(Key key) {
        this.key = key;
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
        if (this.key != null) {
            sb.append("Key: " + this.key + ", ");
        }
        if (this.attributesToGet != null) {
            sb.append("AttributesToGet: " + this.attributesToGet + ", ");
        }
        if (this.consistentRead != null) {
            sb.append("ConsistentRead: " + this.consistentRead + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public GetItemRequest withAttributesToGet(Collection<String> collection) {
        if (collection == null) {
            this.attributesToGet = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributesToGet = arrayList;
        }
        return this;
    }

    public GetItemRequest withAttributesToGet(String... strArr) {
        if (getAttributesToGet() == null) {
            setAttributesToGet(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAttributesToGet().add(str);
        }
        return this;
    }

    public GetItemRequest withConsistentRead(Boolean bool) {
        this.consistentRead = bool;
        return this;
    }

    public GetItemRequest withKey(Key key) {
        this.key = key;
        return this;
    }

    public GetItemRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
