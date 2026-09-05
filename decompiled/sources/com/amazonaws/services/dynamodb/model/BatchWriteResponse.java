package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchWriteResponse {
    private Double consumedCapacityUnits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchWriteResponse)) {
            return false;
        }
        BatchWriteResponse batchWriteResponse = (BatchWriteResponse) obj;
        if ((batchWriteResponse.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return batchWriteResponse.getConsumedCapacityUnits() == null || batchWriteResponse.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
    }

    public Double getConsumedCapacityUnits() {
        return this.consumedCapacityUnits;
    }

    public int hashCode() {
        return (getConsumedCapacityUnits() == null ? 0 : getConsumedCapacityUnits().hashCode()) + 31;
    }

    public void setConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.consumedCapacityUnits != null) {
            sb.append("ConsumedCapacityUnits: " + this.consumedCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public BatchWriteResponse withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }
}
