package com.amazonaws.services.sqs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchResult {
    private List<BatchResultErrorEntry> failed;
    private List<SendMessageBatchResultEntry> successful;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SendMessageBatchResult)) {
            return false;
        }
        SendMessageBatchResult sendMessageBatchResult = (SendMessageBatchResult) obj;
        if ((sendMessageBatchResult.getSuccessful() == null) ^ (getSuccessful() == null)) {
            return false;
        }
        if (sendMessageBatchResult.getSuccessful() != null && !sendMessageBatchResult.getSuccessful().equals(getSuccessful())) {
            return false;
        }
        if ((sendMessageBatchResult.getFailed() == null) ^ (getFailed() == null)) {
            return false;
        }
        return sendMessageBatchResult.getFailed() == null || sendMessageBatchResult.getFailed().equals(getFailed());
    }

    public List<BatchResultErrorEntry> getFailed() {
        if (this.failed == null) {
            this.failed = new ArrayList();
        }
        return this.failed;
    }

    public List<SendMessageBatchResultEntry> getSuccessful() {
        if (this.successful == null) {
            this.successful = new ArrayList();
        }
        return this.successful;
    }

    public int hashCode() {
        return (((getSuccessful() == null ? 0 : getSuccessful().hashCode()) + 31) * 31) + (getFailed() != null ? getFailed().hashCode() : 0);
    }

    public void setFailed(Collection<BatchResultErrorEntry> collection) {
        if (collection == null) {
            this.failed = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.failed = arrayList;
    }

    public void setSuccessful(Collection<SendMessageBatchResultEntry> collection) {
        if (collection == null) {
            this.successful = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.successful = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.successful != null) {
            sb.append("Successful: " + this.successful + ", ");
        }
        if (this.failed != null) {
            sb.append("Failed: " + this.failed + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public SendMessageBatchResult withFailed(Collection<BatchResultErrorEntry> collection) {
        if (collection == null) {
            this.failed = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.failed = arrayList;
        }
        return this;
    }

    public SendMessageBatchResult withFailed(BatchResultErrorEntry... batchResultErrorEntryArr) {
        if (getFailed() == null) {
            setFailed(new ArrayList(batchResultErrorEntryArr.length));
        }
        for (BatchResultErrorEntry batchResultErrorEntry : batchResultErrorEntryArr) {
            getFailed().add(batchResultErrorEntry);
        }
        return this;
    }

    public SendMessageBatchResult withSuccessful(Collection<SendMessageBatchResultEntry> collection) {
        if (collection == null) {
            this.successful = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.successful = arrayList;
        }
        return this;
    }

    public SendMessageBatchResult withSuccessful(SendMessageBatchResultEntry... sendMessageBatchResultEntryArr) {
        if (getSuccessful() == null) {
            setSuccessful(new ArrayList(sendMessageBatchResultEntryArr.length));
        }
        for (SendMessageBatchResultEntry sendMessageBatchResultEntry : sendMessageBatchResultEntryArr) {
            getSuccessful().add(sendMessageBatchResultEntry);
        }
        return this;
    }
}
