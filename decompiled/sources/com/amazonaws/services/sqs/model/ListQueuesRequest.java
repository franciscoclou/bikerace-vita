package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListQueuesRequest extends e {
    private String queueNamePrefix;

    public ListQueuesRequest() {
    }

    public ListQueuesRequest(String str) {
        this.queueNamePrefix = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListQueuesRequest)) {
            return false;
        }
        ListQueuesRequest listQueuesRequest = (ListQueuesRequest) obj;
        if ((listQueuesRequest.getQueueNamePrefix() == null) ^ (getQueueNamePrefix() == null)) {
            return false;
        }
        return listQueuesRequest.getQueueNamePrefix() == null || listQueuesRequest.getQueueNamePrefix().equals(getQueueNamePrefix());
    }

    public String getQueueNamePrefix() {
        return this.queueNamePrefix;
    }

    public int hashCode() {
        return (getQueueNamePrefix() == null ? 0 : getQueueNamePrefix().hashCode()) + 31;
    }

    public void setQueueNamePrefix(String str) {
        this.queueNamePrefix = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.queueNamePrefix != null) {
            sb.append("QueueNamePrefix: " + this.queueNamePrefix + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ListQueuesRequest withQueueNamePrefix(String str) {
        this.queueNamePrefix = str;
        return this;
    }
}
