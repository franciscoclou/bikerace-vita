package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ReceiveMessageRequest extends e {
    private List<String> attributeNames;
    private Integer maxNumberOfMessages;
    private String queueUrl;
    private Integer visibilityTimeout;

    public ReceiveMessageRequest() {
    }

    public ReceiveMessageRequest(String str) {
        this.queueUrl = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ReceiveMessageRequest)) {
            return false;
        }
        ReceiveMessageRequest receiveMessageRequest = (ReceiveMessageRequest) obj;
        if ((receiveMessageRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (receiveMessageRequest.getQueueUrl() != null && !receiveMessageRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((receiveMessageRequest.getAttributeNames() == null) ^ (getAttributeNames() == null)) {
            return false;
        }
        if (receiveMessageRequest.getAttributeNames() != null && !receiveMessageRequest.getAttributeNames().equals(getAttributeNames())) {
            return false;
        }
        if ((receiveMessageRequest.getMaxNumberOfMessages() == null) ^ (getMaxNumberOfMessages() == null)) {
            return false;
        }
        if (receiveMessageRequest.getMaxNumberOfMessages() != null && !receiveMessageRequest.getMaxNumberOfMessages().equals(getMaxNumberOfMessages())) {
            return false;
        }
        if ((receiveMessageRequest.getVisibilityTimeout() == null) ^ (getVisibilityTimeout() == null)) {
            return false;
        }
        return receiveMessageRequest.getVisibilityTimeout() == null || receiveMessageRequest.getVisibilityTimeout().equals(getVisibilityTimeout());
    }

    public List<String> getAttributeNames() {
        if (this.attributeNames == null) {
            this.attributeNames = new ArrayList();
        }
        return this.attributeNames;
    }

    public Integer getMaxNumberOfMessages() {
        return this.maxNumberOfMessages;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public Integer getVisibilityTimeout() {
        return this.visibilityTimeout;
    }

    public int hashCode() {
        return (((getMaxNumberOfMessages() == null ? 0 : getMaxNumberOfMessages().hashCode()) + (((getAttributeNames() == null ? 0 : getAttributeNames().hashCode()) + (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31)) * 31)) * 31) + (getVisibilityTimeout() != null ? getVisibilityTimeout().hashCode() : 0);
    }

    public void setAttributeNames(Collection<String> collection) {
        if (collection == null) {
            this.attributeNames = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.attributeNames = arrayList;
    }

    public void setMaxNumberOfMessages(Integer num) {
        this.maxNumberOfMessages = num;
    }

    public void setQueueUrl(String str) {
        this.queueUrl = str;
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
        if (this.attributeNames != null) {
            sb.append("AttributeNames: " + this.attributeNames + ", ");
        }
        if (this.maxNumberOfMessages != null) {
            sb.append("MaxNumberOfMessages: " + this.maxNumberOfMessages + ", ");
        }
        if (this.visibilityTimeout != null) {
            sb.append("VisibilityTimeout: " + this.visibilityTimeout + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ReceiveMessageRequest withAttributeNames(Collection<String> collection) {
        if (collection == null) {
            this.attributeNames = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributeNames = arrayList;
        }
        return this;
    }

    public ReceiveMessageRequest withAttributeNames(String... strArr) {
        if (getAttributeNames() == null) {
            setAttributeNames(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAttributeNames().add(str);
        }
        return this;
    }

    public ReceiveMessageRequest withMaxNumberOfMessages(Integer num) {
        this.maxNumberOfMessages = num;
        return this;
    }

    public ReceiveMessageRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }

    public ReceiveMessageRequest withVisibilityTimeout(Integer num) {
        this.visibilityTimeout = num;
        return this;
    }
}
