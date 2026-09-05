package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageBatchRequest extends e {
    private List<DeleteMessageBatchRequestEntry> entries;
    private String queueUrl;

    public DeleteMessageBatchRequest() {
    }

    public DeleteMessageBatchRequest(String str) {
        this.queueUrl = str;
    }

    public DeleteMessageBatchRequest(String str, List<DeleteMessageBatchRequestEntry> list) {
        this.queueUrl = str;
        this.entries = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteMessageBatchRequest)) {
            return false;
        }
        DeleteMessageBatchRequest deleteMessageBatchRequest = (DeleteMessageBatchRequest) obj;
        if ((deleteMessageBatchRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (deleteMessageBatchRequest.getQueueUrl() != null && !deleteMessageBatchRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((deleteMessageBatchRequest.getEntries() == null) ^ (getEntries() == null)) {
            return false;
        }
        return deleteMessageBatchRequest.getEntries() == null || deleteMessageBatchRequest.getEntries().equals(getEntries());
    }

    public List<DeleteMessageBatchRequestEntry> getEntries() {
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

    public void setEntries(Collection<DeleteMessageBatchRequestEntry> collection) {
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

    public DeleteMessageBatchRequest withEntries(Collection<DeleteMessageBatchRequestEntry> collection) {
        if (collection == null) {
            this.entries = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.entries = arrayList;
        }
        return this;
    }

    public DeleteMessageBatchRequest withEntries(DeleteMessageBatchRequestEntry... deleteMessageBatchRequestEntryArr) {
        if (getEntries() == null) {
            setEntries(new ArrayList(deleteMessageBatchRequestEntryArr.length));
        }
        for (DeleteMessageBatchRequestEntry deleteMessageBatchRequestEntry : deleteMessageBatchRequestEntryArr) {
            getEntries().add(deleteMessageBatchRequestEntry);
        }
        return this;
    }

    public DeleteMessageBatchRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
