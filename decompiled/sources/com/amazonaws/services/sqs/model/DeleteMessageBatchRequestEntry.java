package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageBatchRequestEntry {
    private String id;
    private String receiptHandle;

    public DeleteMessageBatchRequestEntry() {
    }

    public DeleteMessageBatchRequestEntry(String str, String str2) {
        this.id = str;
        this.receiptHandle = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteMessageBatchRequestEntry)) {
            return false;
        }
        DeleteMessageBatchRequestEntry deleteMessageBatchRequestEntry = (DeleteMessageBatchRequestEntry) obj;
        if ((deleteMessageBatchRequestEntry.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (deleteMessageBatchRequestEntry.getId() != null && !deleteMessageBatchRequestEntry.getId().equals(getId())) {
            return false;
        }
        if ((deleteMessageBatchRequestEntry.getReceiptHandle() == null) ^ (getReceiptHandle() == null)) {
            return false;
        }
        return deleteMessageBatchRequestEntry.getReceiptHandle() == null || deleteMessageBatchRequestEntry.getReceiptHandle().equals(getReceiptHandle());
    }

    public String getId() {
        return this.id;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public int hashCode() {
        return (((getId() == null ? 0 : getId().hashCode()) + 31) * 31) + (getReceiptHandle() != null ? getReceiptHandle().hashCode() : 0);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.id != null) {
            sb.append("Id: " + this.id + ", ");
        }
        if (this.receiptHandle != null) {
            sb.append("ReceiptHandle: " + this.receiptHandle + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public DeleteMessageBatchRequestEntry withId(String str) {
        this.id = str;
        return this;
    }

    public DeleteMessageBatchRequestEntry withReceiptHandle(String str) {
        this.receiptHandle = str;
        return this;
    }
}
