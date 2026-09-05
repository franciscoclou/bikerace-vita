package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchRequest extends e {
    private List<SendMessageBatchRequestEntry> entries;
    private String queueUrl;

    public SendMessageBatchRequest() {
    }

    public SendMessageBatchRequest(String str) {
        this.queueUrl = str;
    }

    public SendMessageBatchRequest(String str, List<SendMessageBatchRequestEntry> list) {
        this.queueUrl = str;
        this.entries = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SendMessageBatchRequest)) {
            return false;
        }
        SendMessageBatchRequest sendMessageBatchRequest = (SendMessageBatchRequest) obj;
        if ((sendMessageBatchRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (sendMessageBatchRequest.getQueueUrl() != null && !sendMessageBatchRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((sendMessageBatchRequest.getEntries() == null) ^ (getEntries() == null)) {
            return false;
        }
        return sendMessageBatchRequest.getEntries() == null || sendMessageBatchRequest.getEntries().equals(getEntries());
    }

    public List<SendMessageBatchRequestEntry> getEntries() {
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

    public void setEntries(Collection<SendMessageBatchRequestEntry> collection) {
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

    public SendMessageBatchRequest withEntries(Collection<SendMessageBatchRequestEntry> collection) {
        if (collection == null) {
            this.entries = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.entries = arrayList;
        }
        return this;
    }

    public SendMessageBatchRequest withEntries(SendMessageBatchRequestEntry... sendMessageBatchRequestEntryArr) {
        if (getEntries() == null) {
            setEntries(new ArrayList(sendMessageBatchRequestEntryArr.length));
        }
        for (SendMessageBatchRequestEntry sendMessageBatchRequestEntry : sendMessageBatchRequestEntryArr) {
            getEntries().add(sendMessageBatchRequestEntry);
        }
        return this;
    }

    public SendMessageBatchRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
