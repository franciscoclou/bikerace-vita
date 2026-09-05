package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SetQueueAttributesRequest extends e {
    private Map<String, String> attributes;
    private String queueUrl;

    public SetQueueAttributesRequest() {
    }

    public SetQueueAttributesRequest(String str, Map<String, String> map) {
        this.queueUrl = str;
        this.attributes = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SetQueueAttributesRequest)) {
            return false;
        }
        SetQueueAttributesRequest setQueueAttributesRequest = (SetQueueAttributesRequest) obj;
        if ((setQueueAttributesRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (setQueueAttributesRequest.getQueueUrl() != null && !setQueueAttributesRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((setQueueAttributesRequest.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        return setQueueAttributesRequest.getAttributes() == null || setQueueAttributesRequest.getAttributes().equals(getAttributes());
    }

    public Map<String, String> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        return this.attributes;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31) + (getAttributes() != null ? getAttributes().hashCode() : 0);
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
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
        if (this.attributes != null) {
            sb.append("Attributes: " + this.attributes + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public SetQueueAttributesRequest withAttributes(Map<String, String> map) {
        setAttributes(map);
        return this;
    }

    public SetQueueAttributesRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
