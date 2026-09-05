package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchWriteItemRequest extends e {
    private Map<String, List<WriteRequest>> requestItems;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchWriteItemRequest)) {
            return false;
        }
        BatchWriteItemRequest batchWriteItemRequest = (BatchWriteItemRequest) obj;
        if ((batchWriteItemRequest.getRequestItems() == null) ^ (getRequestItems() == null)) {
            return false;
        }
        return batchWriteItemRequest.getRequestItems() == null || batchWriteItemRequest.getRequestItems().equals(getRequestItems());
    }

    public Map<String, List<WriteRequest>> getRequestItems() {
        return this.requestItems;
    }

    public int hashCode() {
        return (getRequestItems() == null ? 0 : getRequestItems().hashCode()) + 31;
    }

    public void setRequestItems(Map<String, List<WriteRequest>> map) {
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

    public BatchWriteItemRequest withRequestItems(Map<String, List<WriteRequest>> map) {
        setRequestItems(map);
        return this;
    }
}
