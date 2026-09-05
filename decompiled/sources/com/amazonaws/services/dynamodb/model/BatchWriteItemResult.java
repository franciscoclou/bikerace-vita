package com.amazonaws.services.dynamodb.model;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchWriteItemResult {
    private Map<String, BatchWriteResponse> responses;
    private Map<String, List<WriteRequest>> unprocessedItems;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchWriteItemResult)) {
            return false;
        }
        BatchWriteItemResult batchWriteItemResult = (BatchWriteItemResult) obj;
        if ((batchWriteItemResult.getResponses() == null) ^ (getResponses() == null)) {
            return false;
        }
        if (batchWriteItemResult.getResponses() != null && !batchWriteItemResult.getResponses().equals(getResponses())) {
            return false;
        }
        if ((batchWriteItemResult.getUnprocessedItems() == null) ^ (getUnprocessedItems() == null)) {
            return false;
        }
        return batchWriteItemResult.getUnprocessedItems() == null || batchWriteItemResult.getUnprocessedItems().equals(getUnprocessedItems());
    }

    public Map<String, BatchWriteResponse> getResponses() {
        return this.responses;
    }

    public Map<String, List<WriteRequest>> getUnprocessedItems() {
        return this.unprocessedItems;
    }

    public int hashCode() {
        return (((getResponses() == null ? 0 : getResponses().hashCode()) + 31) * 31) + (getUnprocessedItems() != null ? getUnprocessedItems().hashCode() : 0);
    }

    public void setResponses(Map<String, BatchWriteResponse> map) {
        this.responses = map;
    }

    public void setUnprocessedItems(Map<String, List<WriteRequest>> map) {
        this.unprocessedItems = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.responses != null) {
            sb.append("Responses: " + this.responses + ", ");
        }
        if (this.unprocessedItems != null) {
            sb.append("UnprocessedItems: " + this.unprocessedItems + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public BatchWriteItemResult withResponses(Map<String, BatchWriteResponse> map) {
        setResponses(map);
        return this;
    }

    public BatchWriteItemResult withUnprocessedItems(Map<String, List<WriteRequest>> map) {
        setUnprocessedItems(map);
        return this;
    }
}
