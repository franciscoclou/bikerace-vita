package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityRequest extends e {
    private String queueUrl;
    private String receiptHandle;
    private Integer visibilityTimeout;

    public ChangeMessageVisibilityRequest() {
    }

    public ChangeMessageVisibilityRequest(String str, String str2, Integer num) {
        this.queueUrl = str;
        this.receiptHandle = str2;
        this.visibilityTimeout = num;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ChangeMessageVisibilityRequest)) {
            return false;
        }
        ChangeMessageVisibilityRequest changeMessageVisibilityRequest = (ChangeMessageVisibilityRequest) obj;
        if ((changeMessageVisibilityRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (changeMessageVisibilityRequest.getQueueUrl() != null && !changeMessageVisibilityRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((changeMessageVisibilityRequest.getReceiptHandle() == null) ^ (getReceiptHandle() == null)) {
            return false;
        }
        if (changeMessageVisibilityRequest.getReceiptHandle() != null && !changeMessageVisibilityRequest.getReceiptHandle().equals(getReceiptHandle())) {
            return false;
        }
        if ((changeMessageVisibilityRequest.getVisibilityTimeout() == null) ^ (getVisibilityTimeout() == null)) {
            return false;
        }
        return changeMessageVisibilityRequest.getVisibilityTimeout() == null || changeMessageVisibilityRequest.getVisibilityTimeout().equals(getVisibilityTimeout());
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public Integer getVisibilityTimeout() {
        return this.visibilityTimeout;
    }

    public int hashCode() {
        return (((getReceiptHandle() == null ? 0 : getReceiptHandle().hashCode()) + (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31)) * 31) + (getVisibilityTimeout() != null ? getVisibilityTimeout().hashCode() : 0);
    }

    public void setQueueUrl(String str) {
        this.queueUrl = str;
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
        if (this.queueUrl != null) {
            sb.append("QueueUrl: " + this.queueUrl + ", ");
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

    public ChangeMessageVisibilityRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }

    public ChangeMessageVisibilityRequest withReceiptHandle(String str) {
        this.receiptHandle = str;
        return this;
    }

    public ChangeMessageVisibilityRequest withVisibilityTimeout(Integer num) {
        this.visibilityTimeout = num;
        return this;
    }
}
