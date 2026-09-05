package com.amazonaws.services.sqs.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class RemovePermissionRequest extends e {
    private String label;
    private String queueUrl;

    public RemovePermissionRequest() {
    }

    public RemovePermissionRequest(String str, String str2) {
        this.queueUrl = str;
        this.label = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RemovePermissionRequest)) {
            return false;
        }
        RemovePermissionRequest removePermissionRequest = (RemovePermissionRequest) obj;
        if ((removePermissionRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (removePermissionRequest.getQueueUrl() != null && !removePermissionRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((removePermissionRequest.getLabel() == null) ^ (getLabel() == null)) {
            return false;
        }
        return removePermissionRequest.getLabel() == null || removePermissionRequest.getLabel().equals(getLabel());
    }

    public String getLabel() {
        return this.label;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31) + (getLabel() != null ? getLabel().hashCode() : 0);
    }

    public void setLabel(String str) {
        this.label = str;
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
        if (this.label != null) {
            sb.append("Label: " + this.label + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public RemovePermissionRequest withLabel(String str) {
        this.label = str;
        return this;
    }

    public RemovePermissionRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
