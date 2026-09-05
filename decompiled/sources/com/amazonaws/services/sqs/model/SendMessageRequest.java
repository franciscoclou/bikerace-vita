package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageRequest extends e {
    private Integer delaySeconds;
    private String messageBody;
    private String queueUrl;

    public SendMessageRequest() {
    }

    public SendMessageRequest(String str, String str2) {
        this.queueUrl = str;
        this.messageBody = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SendMessageRequest)) {
            return false;
        }
        SendMessageRequest sendMessageRequest = (SendMessageRequest) obj;
        if ((sendMessageRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (sendMessageRequest.getQueueUrl() != null && !sendMessageRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((sendMessageRequest.getMessageBody() == null) ^ (getMessageBody() == null)) {
            return false;
        }
        if (sendMessageRequest.getMessageBody() != null && !sendMessageRequest.getMessageBody().equals(getMessageBody())) {
            return false;
        }
        if ((sendMessageRequest.getDelaySeconds() == null) ^ (getDelaySeconds() == null)) {
            return false;
        }
        return sendMessageRequest.getDelaySeconds() == null || sendMessageRequest.getDelaySeconds().equals(getDelaySeconds());
    }

    public Integer getDelaySeconds() {
        return this.delaySeconds;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (((getMessageBody() == null ? 0 : getMessageBody().hashCode()) + (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31)) * 31) + (getDelaySeconds() != null ? getDelaySeconds().hashCode() : 0);
    }

    public void setDelaySeconds(Integer num) {
        this.delaySeconds = num;
    }

    public void setMessageBody(String str) {
        this.messageBody = str;
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
        if (this.messageBody != null) {
            sb.append("MessageBody: " + this.messageBody + ", ");
        }
        if (this.delaySeconds != null) {
            sb.append("DelaySeconds: " + this.delaySeconds + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public SendMessageRequest withDelaySeconds(Integer num) {
        this.delaySeconds = num;
        return this;
    }

    public SendMessageRequest withMessageBody(String str) {
        this.messageBody = str;
        return this;
    }

    public SendMessageRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
