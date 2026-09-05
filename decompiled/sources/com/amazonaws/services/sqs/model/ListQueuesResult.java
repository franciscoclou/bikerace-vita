package com.amazonaws.services.sqs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListQueuesResult {
    private List<String> queueUrls;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListQueuesResult)) {
            return false;
        }
        ListQueuesResult listQueuesResult = (ListQueuesResult) obj;
        if ((listQueuesResult.getQueueUrls() == null) ^ (getQueueUrls() == null)) {
            return false;
        }
        return listQueuesResult.getQueueUrls() == null || listQueuesResult.getQueueUrls().equals(getQueueUrls());
    }

    public List<String> getQueueUrls() {
        if (this.queueUrls == null) {
            this.queueUrls = new ArrayList();
        }
        return this.queueUrls;
    }

    public int hashCode() {
        return (getQueueUrls() == null ? 0 : getQueueUrls().hashCode()) + 31;
    }

    public void setQueueUrls(Collection<String> collection) {
        if (collection == null) {
            this.queueUrls = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.queueUrls = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.queueUrls != null) {
            sb.append("QueueUrls: " + this.queueUrls + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ListQueuesResult withQueueUrls(Collection<String> collection) {
        if (collection == null) {
            this.queueUrls = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.queueUrls = arrayList;
        }
        return this;
    }

    public ListQueuesResult withQueueUrls(String... strArr) {
        if (getQueueUrls() == null) {
            setQueueUrls(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getQueueUrls().add(str);
        }
        return this;
    }
}
