package com.amazonaws.services.sqs.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Message {
    private Map<String, String> attributes;
    private String body;
    private String mD5OfBody;
    private String messageId;
    private String receiptHandle;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        if ((message.getMessageId() == null) ^ (getMessageId() == null)) {
            return false;
        }
        if (message.getMessageId() != null && !message.getMessageId().equals(getMessageId())) {
            return false;
        }
        if ((message.getReceiptHandle() == null) ^ (getReceiptHandle() == null)) {
            return false;
        }
        if (message.getReceiptHandle() != null && !message.getReceiptHandle().equals(getReceiptHandle())) {
            return false;
        }
        if ((message.getMD5OfBody() == null) ^ (getMD5OfBody() == null)) {
            return false;
        }
        if (message.getMD5OfBody() != null && !message.getMD5OfBody().equals(getMD5OfBody())) {
            return false;
        }
        if ((message.getBody() == null) ^ (getBody() == null)) {
            return false;
        }
        if (message.getBody() != null && !message.getBody().equals(getBody())) {
            return false;
        }
        if ((message.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        return message.getAttributes() == null || message.getAttributes().equals(getAttributes());
    }

    public Map<String, String> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        return this.attributes;
    }

    public String getBody() {
        return this.body;
    }

    public String getMD5OfBody() {
        return this.mD5OfBody;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getReceiptHandle() {
        return this.receiptHandle;
    }

    public int hashCode() {
        return (((getBody() == null ? 0 : getBody().hashCode()) + (((getMD5OfBody() == null ? 0 : getMD5OfBody().hashCode()) + (((getReceiptHandle() == null ? 0 : getReceiptHandle().hashCode()) + (((getMessageId() == null ? 0 : getMessageId().hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + (getAttributes() != null ? getAttributes().hashCode() : 0);
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public void setMD5OfBody(String str) {
        this.mD5OfBody = str;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setReceiptHandle(String str) {
        this.receiptHandle = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.messageId != null) {
            sb.append("MessageId: " + this.messageId + ", ");
        }
        if (this.receiptHandle != null) {
            sb.append("ReceiptHandle: " + this.receiptHandle + ", ");
        }
        if (this.mD5OfBody != null) {
            sb.append("MD5OfBody: " + this.mD5OfBody + ", ");
        }
        if (this.body != null) {
            sb.append("Body: " + this.body + ", ");
        }
        if (this.attributes != null) {
            sb.append("Attributes: " + this.attributes + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public Message withAttributes(Map<String, String> map) {
        setAttributes(map);
        return this;
    }

    public Message withBody(String str) {
        this.body = str;
        return this;
    }

    public Message withMD5OfBody(String str) {
        this.mD5OfBody = str;
        return this;
    }

    public Message withMessageId(String str) {
        this.messageId = str;
        return this;
    }

    public Message withReceiptHandle(String str) {
        this.receiptHandle = str;
        return this;
    }
}
