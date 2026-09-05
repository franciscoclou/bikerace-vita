package com.amazonaws.services.dynamodb.model;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchGetItemResult {
    private Map<String, BatchResponse> responses;
    private Map<String, KeysAndAttributes> unprocessedKeys;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchGetItemResult)) {
            return false;
        }
        BatchGetItemResult batchGetItemResult = (BatchGetItemResult) obj;
        if ((batchGetItemResult.getResponses() == null) ^ (getResponses() == null)) {
            return false;
        }
        if (batchGetItemResult.getResponses() != null && !batchGetItemResult.getResponses().equals(getResponses())) {
            return false;
        }
        if ((batchGetItemResult.getUnprocessedKeys() == null) ^ (getUnprocessedKeys() == null)) {
            return false;
        }
        return batchGetItemResult.getUnprocessedKeys() == null || batchGetItemResult.getUnprocessedKeys().equals(getUnprocessedKeys());
    }

    public Map<String, BatchResponse> getResponses() {
        return this.responses;
    }

    public Map<String, KeysAndAttributes> getUnprocessedKeys() {
        return this.unprocessedKeys;
    }

    public int hashCode() {
        return (((getResponses() == null ? 0 : getResponses().hashCode()) + 31) * 31) + (getUnprocessedKeys() != null ? getUnprocessedKeys().hashCode() : 0);
    }

    public void setResponses(Map<String, BatchResponse> map) {
        this.responses = map;
    }

    public void setUnprocessedKeys(Map<String, KeysAndAttributes> map) {
        this.unprocessedKeys = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.responses != null) {
            sb.append("Responses: " + this.responses + ", ");
        }
        if (this.unprocessedKeys != null) {
            sb.append("UnprocessedKeys: " + this.unprocessedKeys + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public BatchGetItemResult withResponses(Map<String, BatchResponse> map) {
        setResponses(map);
        return this;
    }

    public BatchGetItemResult withUnprocessedKeys(Map<String, KeysAndAttributes> map) {
        setUnprocessedKeys(map);
        return this;
    }
}
