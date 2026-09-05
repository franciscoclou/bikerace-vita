package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageResult {
    private String mD5OfMessageBody;
    private String messageId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SendMessageResult)) {
            return false;
        }
        SendMessageResult sendMessageResult = (SendMessageResult) obj;
        if ((sendMessageResult.getMD5OfMessageBody() == null) ^ (getMD5OfMessageBody() == null)) {
            return false;
        }
        if (sendMessageResult.getMD5OfMessageBody() != null && !sendMessageResult.getMD5OfMessageBody().equals(getMD5OfMessageBody())) {
            return false;
        }
        if ((sendMessageResult.getMessageId() == null) ^ (getMessageId() == null)) {
            return false;
        }
        return sendMessageResult.getMessageId() == null || sendMessageResult.getMessageId().equals(getMessageId());
    }

    public String getMD5OfMessageBody() {
        return this.mD5OfMessageBody;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int hashCode() {
        return (((getMD5OfMessageBody() == null ? 0 : getMD5OfMessageBody().hashCode()) + 31) * 31) + (getMessageId() != null ? getMessageId().hashCode() : 0);
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
        if (this.mD5OfMessageBody != null) {
            sb.append("MD5OfMessageBody: " + this.mD5OfMessageBody + ", ");
        }
        if (this.messageId != null) {
            sb.append("MessageId: " + this.messageId + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public SendMessageResult withMD5OfMessageBody(String str) {
        this.mD5OfMessageBody = str;
        return this;
    }

    public SendMessageResult withMessageId(String str) {
        this.messageId = str;
        return this;
    }
}
