package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreateQueueRequest extends e {
    private Map<String, String> attributes;
    private String queueName;

    public CreateQueueRequest() {
    }

    public CreateQueueRequest(String str) {
        this.queueName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateQueueRequest)) {
            return false;
        }
        CreateQueueRequest createQueueRequest = (CreateQueueRequest) obj;
        if ((createQueueRequest.getQueueName() == null) ^ (getQueueName() == null)) {
            return false;
        }
        if (createQueueRequest.getQueueName() != null && !createQueueRequest.getQueueName().equals(getQueueName())) {
            return false;
        }
        if ((createQueueRequest.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        return createQueueRequest.getAttributes() == null || createQueueRequest.getAttributes().equals(getAttributes());
    }

    public Map<String, String> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        return this.attributes;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public int hashCode() {
        return (((getQueueName() == null ? 0 : getQueueName().hashCode()) + 31) * 31) + (getAttributes() != null ? getAttributes().hashCode() : 0);
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.queueName != null) {
            sb.append("QueueName: " + this.queueName + ", ");
        }
        if (this.attributes != null) {
            sb.append("Attributes: " + this.attributes + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public CreateQueueRequest withAttributes(Map<String, String> map) {
        setAttributes(map);
        return this;
    }

    public CreateQueueRequest withQueueName(String str) {
        this.queueName = str;
        return this;
    }
}
