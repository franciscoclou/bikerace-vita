package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchGetItemRequest extends e {
    private Map<String, KeysAndAttributes> requestItems;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchGetItemRequest)) {
            return false;
        }
        BatchGetItemRequest batchGetItemRequest = (BatchGetItemRequest) obj;
        if ((batchGetItemRequest.getRequestItems() == null) ^ (getRequestItems() == null)) {
            return false;
        }
        return batchGetItemRequest.getRequestItems() == null || batchGetItemRequest.getRequestItems().equals(getRequestItems());
    }

    public Map<String, KeysAndAttributes> getRequestItems() {
        return this.requestItems;
    }

    public int hashCode() {
        return (getRequestItems() == null ? 0 : getRequestItems().hashCode()) + 31;
    }

    public void setRequestItems(Map<String, KeysAndAttributes> map) {
        this.requestItems = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.requestItems != null) {
            sb.append("RequestItems: " + this.requestItems + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public BatchGetItemRequest withRequestItems(Map<String, KeysAndAttributes> map) {
        setRequestItems(map);
        return this;
    }
}
