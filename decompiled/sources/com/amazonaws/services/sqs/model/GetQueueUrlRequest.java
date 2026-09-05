package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueUrlRequest extends e {
    private String queueName;
    private String queueOwnerAWSAccountId;

    public GetQueueUrlRequest() {
    }

    public GetQueueUrlRequest(String str) {
        this.queueName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetQueueUrlRequest)) {
            return false;
        }
        GetQueueUrlRequest getQueueUrlRequest = (GetQueueUrlRequest) obj;
        if ((getQueueUrlRequest.getQueueName() == null) ^ (getQueueName() == null)) {
            return false;
        }
        if (getQueueUrlRequest.getQueueName() != null && !getQueueUrlRequest.getQueueName().equals(getQueueName())) {
            return false;
        }
        if ((getQueueUrlRequest.getQueueOwnerAWSAccountId() == null) ^ (getQueueOwnerAWSAccountId() == null)) {
            return false;
        }
        return getQueueUrlRequest.getQueueOwnerAWSAccountId() == null || getQueueUrlRequest.getQueueOwnerAWSAccountId().equals(getQueueOwnerAWSAccountId());
    }

    public String getQueueName() {
        return this.queueName;
    }

    public String getQueueOwnerAWSAccountId() {
        return this.queueOwnerAWSAccountId;
    }

    public int hashCode() {
        return (((getQueueName() == null ? 0 : getQueueName().hashCode()) + 31) * 31) + (getQueueOwnerAWSAccountId() != null ? getQueueOwnerAWSAccountId().hashCode() : 0);
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public void setQueueOwnerAWSAccountId(String str) {
        this.queueOwnerAWSAccountId = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.queueName != null) {
            sb.append("QueueName: " + this.queueName + ", ");
        }
        if (this.queueOwnerAWSAccountId != null) {
            sb.append("QueueOwnerAWSAccountId: " + this.queueOwnerAWSAccountId + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public GetQueueUrlRequest withQueueName(String str) {
        this.queueName = str;
        return this;
    }

    public GetQueueUrlRequest withQueueOwnerAWSAccountId(String str) {
        this.queueOwnerAWSAccountId = str;
        return this;
    }
}
