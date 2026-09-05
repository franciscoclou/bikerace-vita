package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchRequestEntry {
    private Integer delaySeconds;
    private String id;
    private String messageBody;

    public SendMessageBatchRequestEntry() {
    }

    public SendMessageBatchRequestEntry(String str, String str2) {
        this.id = str;
        this.messageBody = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SendMessageBatchRequestEntry)) {
            return false;
        }
        SendMessageBatchRequestEntry sendMessageBatchRequestEntry = (SendMessageBatchRequestEntry) obj;
        if ((sendMessageBatchRequestEntry.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (sendMessageBatchRequestEntry.getId() != null && !sendMessageBatchRequestEntry.getId().equals(getId())) {
            return false;
        }
        if ((sendMessageBatchRequestEntry.getMessageBody() == null) ^ (getMessageBody() == null)) {
            return false;
        }
        if (sendMessageBatchRequestEntry.getMessageBody() != null && !sendMessageBatchRequestEntry.getMessageBody().equals(getMessageBody())) {
            return false;
        }
        if ((sendMessageBatchRequestEntry.getDelaySeconds() == null) ^ (getDelaySeconds() == null)) {
            return false;
        }
        return sendMessageBatchRequestEntry.getDelaySeconds() == null || sendMessageBatchRequestEntry.getDelaySeconds().equals(getDelaySeconds());
    }

    public Integer getDelaySeconds() {
        return this.delaySeconds;
    }

    public String getId() {
        return this.id;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public int hashCode() {
        return (((getMessageBody() == null ? 0 : getMessageBody().hashCode()) + (((getId() == null ? 0 : getId().hashCode()) + 31) * 31)) * 31) + (getDelaySeconds() != null ? getDelaySeconds().hashCode() : 0);
    }

    public void setDelaySeconds(Integer num) {
        this.delaySeconds = num;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMessageBody(String str) {
        this.messageBody = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.id != null) {
            sb.append("Id: " + this.id + ", ");
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

    public SendMessageBatchRequestEntry withDelaySeconds(Integer num) {
        this.delaySeconds = num;
        return this;
    }

    public SendMessageBatchRequestEntry withId(String str) {
        this.id = str;
        return this;
    }

    public SendMessageBatchRequestEntry withMessageBody(String str) {
        this.messageBody = str;
        return this;
    }
}
