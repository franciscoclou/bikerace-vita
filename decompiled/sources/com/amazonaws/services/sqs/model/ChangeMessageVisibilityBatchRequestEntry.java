package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityBatchRequestEntry {
    private String id;
    private String receiptHandle;
    private Integer visibilityTimeout;

    public ChangeMessageVisibilityBatchRequestEntry() {
    }

    public ChangeMessageVisibilityBatchRequestEntry(String str, String str2) {
        this.id = str;
        this.receiptHandle = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ChangeMessageVisibilityBatchRequestEntry)) {
            return false;
        }
        ChangeMessageVisibilityBatchRequestEntry changeMessageVisibilityBatchRequestEntry = (ChangeMessageVisibilityBatchRequestEntry) obj;
        if ((changeMessageVisibilityBatchRequestEntry.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (changeMessageVisibilityBatchRequestEntry.getId() != null && !changeMessageVisibilityBatchRequestEntry.getId().equals(getId())) {
            return false;
        }
        if ((changeMessageVisibilityBatchRequestEntry.getReceiptHandle() == null) ^ (getReceiptHandle() == null)) {
            return false;
        }
        if (changeMessageVisibilityBatchRequestEntry.getReceiptHandle() != null && !changeMessageVisibilityBatchRequestEntry.getReceiptHandle().equals(getReceiptHandle())) {
            return false;
        }
        if ((changeMessageVisibilityBatchRequestEntry.getVisibilityTimeout() == null) ^ (getVisibilityTimeout() == null)) {
            return false;
        }
        return changeMessageVisibilityBatchRequestEntry.getVisibilityTimeout() == null || changeMessageVisibilityBatchRequestEntry.getVisibilityTimeout().equals(getVisibilityTimeout());
    }

    public String getId() {
        return this.id;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public Integer getVisibilityTimeout() {
        return this.visibilityTimeout;
    }

    public int hashCode() {
        return (((getReceiptHandle() == null ? 0 : getReceiptHandle().hashCode()) + (((getId() == null ? 0 : getId().hashCode()) + 31) * 31)) * 31) + (getVisibilityTimeout() != null ? getVisibilityTimeout().hashCode() : 0);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public void setVisibilityTimeout(Integer num) {
        this.visibilityTimeout = num;
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
        if (this.visibilityTimeout != null) {
            sb.append("VisibilityTimeout: " + this.visibilityTimeout + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ChangeMessageVisibilityBatchRequestEntry withId(String str) {
        this.id = str;
        return this;
    }

    public ChangeMessageVisibilityBatchRequestEntry withReceiptHandle(String str) {
        this.receiptHandle = str;
        return this;
    }

    public ChangeMessageVisibilityBatchRequestEntry withVisibilityTimeout(Integer num) {
        this.visibilityTimeout = num;
        return this;
    }
}
