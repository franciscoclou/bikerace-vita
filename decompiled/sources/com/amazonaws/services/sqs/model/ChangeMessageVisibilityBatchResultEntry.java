package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityBatchResultEntry {
    private String id;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ChangeMessageVisibilityBatchResultEntry)) {
            return false;
        }
        ChangeMessageVisibilityBatchResultEntry changeMessageVisibilityBatchResultEntry = (ChangeMessageVisibilityBatchResultEntry) obj;
        if ((changeMessageVisibilityBatchResultEntry.getId() == null) ^ (getId() == null)) {
            return false;
        }
        return changeMessageVisibilityBatchResultEntry.getId() == null || changeMessageVisibilityBatchResultEntry.getId().equals(getId());
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return (getId() == null ? 0 : getId().hashCode()) + 31;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.id != null) {
            sb.append("Id: " + this.id + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ChangeMessageVisibilityBatchResultEntry withId(String str) {
        this.id = str;
        return this;
    }
}
