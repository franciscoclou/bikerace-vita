package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteQueueRequest extends e {
    private String queueUrl;

    public DeleteQueueRequest() {
    }

    public DeleteQueueRequest(String str) {
        this.queueUrl = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteQueueRequest)) {
            return false;
        }
        DeleteQueueRequest deleteQueueRequest = (DeleteQueueRequest) obj;
        if ((deleteQueueRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        return deleteQueueRequest.getQueueUrl() == null || deleteQueueRequest.getQueueUrl().equals(getQueueUrl());
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31;
    }

    public void setQueueUrl(String str) {
        this.queueUrl = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.queueUrl != null) {
            sb.append("QueueUrl: " + this.queueUrl + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public DeleteQueueRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
