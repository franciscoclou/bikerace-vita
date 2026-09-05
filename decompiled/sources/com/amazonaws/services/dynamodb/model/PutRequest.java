package com.amazonaws.services.dynamodb.model;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PutRequest {
    private Map<String, AttributeValue> item;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRequest)) {
            return false;
        }
        PutRequest putRequest = (PutRequest) obj;
        if ((putRequest.getItem() == null) ^ (getItem() == null)) {
            return false;
        }
        return putRequest.getItem() == null || putRequest.getItem().equals(getItem());
    }

    public Map<String, AttributeValue> getItem() {
        return this.item;
    }

    public int hashCode() {
        return (getItem() == null ? 0 : getItem().hashCode()) + 31;
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
        sb.append("}");
        return sb.toString();
    }

    public PutRequest withItem(Map<String, AttributeValue> map) {
        setItem(map);
        return this;
    }
}
