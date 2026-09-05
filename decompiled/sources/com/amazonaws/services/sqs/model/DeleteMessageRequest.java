package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageRequest extends e {
    private String queueUrl;
    private String receiptHandle;

    public DeleteMessageRequest() {
    }

    public DeleteMessageRequest(String str, String str2) {
        this.queueUrl = str;
        this.receiptHandle = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteMessageRequest)) {
            return false;
        }
        DeleteMessageRequest deleteMessageRequest = (DeleteMessageRequest) obj;
        if ((deleteMessageRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (deleteMessageRequest.getQueueUrl() != null && !deleteMessageRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((deleteMessageRequest.getReceiptHandle() == null) ^ (getReceiptHandle() == null)) {
            return false;
        }
        return deleteMessageRequest.getReceiptHandle() == null || deleteMessageRequest.getReceiptHandle().equals(getReceiptHandle());
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public int hashCode() {
        return (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31) + (getReceiptHandle() != null ? getReceiptHandle().hashCode() : 0);
    }

    public void setQueueUrl(String str) {
        this.queueUrl = str;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.queueUrl != null) {
            sb.append("QueueUrl: " + this.queueUrl + ", ");
        }
        if (this.receiptHandle != null) {
            sb.append("ReceiptHandle: " + this.receiptHandle + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public DeleteMessageRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }

    public DeleteMessageRequest withReceiptHandle(String str) {
        this.receiptHandle = str;
        return this;
    }
}
