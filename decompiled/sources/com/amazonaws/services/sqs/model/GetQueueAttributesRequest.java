package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueAttributesRequest extends e {
    private List<String> attributeNames;
    private String queueUrl;

    public GetQueueAttributesRequest() {
    }

    public GetQueueAttributesRequest(String str) {
        this.queueUrl = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetQueueAttributesRequest)) {
            return false;
        }
        GetQueueAttributesRequest getQueueAttributesRequest = (GetQueueAttributesRequest) obj;
        if ((getQueueAttributesRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (getQueueAttributesRequest.getQueueUrl() != null && !getQueueAttributesRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((getQueueAttributesRequest.getAttributeNames() == null) ^ (getAttributeNames() == null)) {
            return false;
        }
        return getQueueAttributesRequest.getAttributeNames() == null || getQueueAttributesRequest.getAttributeNames().equals(getAttributeNames());
    }

    public List<String> getAttributeNames() {
        if (this.attributeNames == null) {
            this.attributeNames = new ArrayList();
        }
        return this.attributeNames;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31) + (getAttributeNames() != null ? getAttributeNames().hashCode() : 0);
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

    public void setQueueUrl(String str) {
        this.queueUrl = str;
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
        sb.append("}");
        return sb.toString();
    }

    public GetQueueAttributesRequest withAttributeNames(Collection<String> collection) {
        if (collection == null) {
            this.attributeNames = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributeNames = arrayList;
        }
        return this;
    }

    public GetQueueAttributesRequest withAttributeNames(String... strArr) {
        if (getAttributeNames() == null) {
            setAttributeNames(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAttributeNames().add(str);
        }
        return this;
    }

    public GetQueueAttributesRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
