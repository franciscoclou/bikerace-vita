package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityBatchRequest extends e {
    private List<ChangeMessageVisibilityBatchRequestEntry> entries;
    private String queueUrl;

    public ChangeMessageVisibilityBatchRequest() {
    }

    public ChangeMessageVisibilityBatchRequest(String str, List<ChangeMessageVisibilityBatchRequestEntry> list) {
        this.queueUrl = str;
        this.entries = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ChangeMessageVisibilityBatchRequest)) {
            return false;
        }
        ChangeMessageVisibilityBatchRequest changeMessageVisibilityBatchRequest = (ChangeMessageVisibilityBatchRequest) obj;
        if ((changeMessageVisibilityBatchRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (changeMessageVisibilityBatchRequest.getQueueUrl() != null && !changeMessageVisibilityBatchRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((changeMessageVisibilityBatchRequest.getEntries() == null) ^ (getEntries() == null)) {
            return false;
        }
        return changeMessageVisibilityBatchRequest.getEntries() == null || changeMessageVisibilityBatchRequest.getEntries().equals(getEntries());
    }

    public List<ChangeMessageVisibilityBatchRequestEntry> getEntries() {
        if (this.entries == null) {
            this.entries = new ArrayList();
        }
        return this.entries;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31) + (getEntries() != null ? getEntries().hashCode() : 0);
    }

    public void setEntries(Collection<ChangeMessageVisibilityBatchRequestEntry> collection) {
        if (collection == null) {
            this.entries = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.entries = arrayList;
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
        if (this.entries != null) {
            sb.append("Entries: " + this.entries + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ChangeMessageVisibilityBatchRequest withEntries(Collection<ChangeMessageVisibilityBatchRequestEntry> collection) {
        if (collection == null) {
            this.entries = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.entries = arrayList;
        }
        return this;
    }

    public ChangeMessageVisibilityBatchRequest withEntries(ChangeMessageVisibilityBatchRequestEntry... changeMessageVisibilityBatchRequestEntryArr) {
        if (getEntries() == null) {
            setEntries(new ArrayList(changeMessageVisibilityBatchRequestEntryArr.length));
        }
        for (ChangeMessageVisibilityBatchRequestEntry changeMessageVisibilityBatchRequestEntry : changeMessageVisibilityBatchRequestEntryArr) {
            getEntries().add(changeMessageVisibilityBatchRequestEntry);
        }
        return this;
    }

    public ChangeMessageVisibilityBatchRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
