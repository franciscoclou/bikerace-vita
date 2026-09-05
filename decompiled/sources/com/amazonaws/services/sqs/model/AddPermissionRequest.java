package com.amazonaws.services.sqs.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AddPermissionRequest extends e {
    private List<String> aWSAccountIds;
    private List<String> actions;
    private String label;
    private String queueUrl;

    public AddPermissionRequest() {
    }

    public AddPermissionRequest(String str, String str2, List<String> list, List<String> list2) {
        this.queueUrl = str;
        this.label = str2;
        this.aWSAccountIds = list;
        this.actions = list2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AddPermissionRequest)) {
            return false;
        }
        AddPermissionRequest addPermissionRequest = (AddPermissionRequest) obj;
        if ((addPermissionRequest.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (addPermissionRequest.getQueueUrl() != null && !addPermissionRequest.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((addPermissionRequest.getLabel() == null) ^ (getLabel() == null)) {
            return false;
        }
        if (addPermissionRequest.getLabel() != null && !addPermissionRequest.getLabel().equals(getLabel())) {
            return false;
        }
        if ((addPermissionRequest.getAWSAccountIds() == null) ^ (getAWSAccountIds() == null)) {
            return false;
        }
        if (addPermissionRequest.getAWSAccountIds() != null && !addPermissionRequest.getAWSAccountIds().equals(getAWSAccountIds())) {
            return false;
        }
        if ((addPermissionRequest.getActions() == null) ^ (getActions() == null)) {
            return false;
        }
        return addPermissionRequest.getActions() == null || addPermissionRequest.getActions().equals(getActions());
    }

    public List<String> getAWSAccountIds() {
        if (this.aWSAccountIds == null) {
            this.aWSAccountIds = new ArrayList();
        }
        return this.aWSAccountIds;
    }

    public List<String> getActions() {
        if (this.actions == null) {
            this.actions = new ArrayList();
        }
        return this.actions;
    }

    public String getLabel() {
        return this.label;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public int hashCode() {
        return (((getAWSAccountIds() == null ? 0 : getAWSAccountIds().hashCode()) + (((getLabel() == null ? 0 : getLabel().hashCode()) + (((getQueueUrl() == null ? 0 : getQueueUrl().hashCode()) + 31) * 31)) * 31)) * 31) + (getActions() != null ? getActions().hashCode() : 0);
    }

    public void setAWSAccountIds(Collection<String> collection) {
        if (collection == null) {
            this.aWSAccountIds = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.aWSAccountIds = arrayList;
    }

    public void setActions(Collection<String> collection) {
        if (collection == null) {
            this.actions = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.actions = arrayList;
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
        if (this.aWSAccountIds != null) {
            sb.append("AWSAccountIds: " + this.aWSAccountIds + ", ");
        }
        if (this.actions != null) {
            sb.append("Actions: " + this.actions + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public AddPermissionRequest withAWSAccountIds(Collection<String> collection) {
        if (collection == null) {
            this.aWSAccountIds = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.aWSAccountIds = arrayList;
        }
        return this;
    }

    public AddPermissionRequest withAWSAccountIds(String... strArr) {
        if (getAWSAccountIds() == null) {
            setAWSAccountIds(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAWSAccountIds().add(str);
        }
        return this;
    }

    public AddPermissionRequest withActions(Collection<String> collection) {
        if (collection == null) {
            this.actions = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.actions = arrayList;
        }
        return this;
    }

    public AddPermissionRequest withActions(String... strArr) {
        if (getActions() == null) {
            setActions(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getActions().add(str);
        }
        return this;
    }

    public AddPermissionRequest withLabel(String str) {
        this.label = str;
        return this;
    }

    public AddPermissionRequest withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }
}
