package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchResultEntry {
    private String id;
    private String mD5OfMessageBody;
    private String messageId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SendMessageBatchResultEntry)) {
            return false;
        }
        SendMessageBatchResultEntry sendMessageBatchResultEntry = (SendMessageBatchResultEntry) obj;
        if ((sendMessageBatchResultEntry.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (sendMessageBatchResultEntry.getId() != null && !sendMessageBatchResultEntry.getId().equals(getId())) {
            return false;
        }
        if ((sendMessageBatchResultEntry.getMessageId() == null) ^ (getMessageId() == null)) {
            return false;
        }
        if (sendMessageBatchResultEntry.getMessageId() != null && !sendMessageBatchResultEntry.getMessageId().equals(getMessageId())) {
            return false;
        }
        if ((sendMessageBatchResultEntry.getMD5OfMessageBody() == null) ^ (getMD5OfMessageBody() == null)) {
            return false;
        }
        return sendMessageBatchResultEntry.getMD5OfMessageBody() == null || sendMessageBatchResultEntry.getMD5OfMessageBody().equals(getMD5OfMessageBody());
    }

    public String getId() {
        return this.id;
    }

    public String getMD5OfMessageBody() {
        return this.mD5OfMessageBody;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int hashCode() {
        return (((getMessageId() == null ? 0 : getMessageId().hashCode()) + (((getId() == null ? 0 : getId().hashCode()) + 31) * 31)) * 31) + (getMD5OfMessageBody() != null ? getMD5OfMessageBody().hashCode() : 0);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMD5OfMessageBody(String str) {
        this.mD5OfMessageBody = str;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.id != null) {
            sb.append("Id: " + this.id + ", ");
        }
        if (this.messageId != null) {
            sb.append("MessageId: " + this.messageId + ", ");
        }
        if (this.mD5OfMessageBody != null) {
            sb.append("MD5OfMessageBody: " + this.mD5OfMessageBody + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public SendMessageBatchResultEntry withId(String str) {
        this.id = str;
        return this;
    }

    public SendMessageBatchResultEntry withMD5OfMessageBody(String str) {
        this.mD5OfMessageBody = str;
        return this;
    }

    public SendMessageBatchResultEntry withMessageId(String str) {
        this.messageId = str;
        return this;
    }
}
