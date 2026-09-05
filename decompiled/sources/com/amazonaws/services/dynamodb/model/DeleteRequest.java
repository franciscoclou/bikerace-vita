package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteRequest {
    private Key key;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteRequest)) {
            return false;
        }
        DeleteRequest deleteRequest = (DeleteRequest) obj;
        if ((deleteRequest.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        return deleteRequest.getKey() == null || deleteRequest.getKey().equals(getKey());
    }

    public Key getKey() {
        return this.key;
    }

    public int hashCode() {
        return (getKey() == null ? 0 : getKey().hashCode()) + 31;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.key != null) {
            sb.append("Key: " + this.key + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public DeleteRequest withKey(Key key) {
        this.key = key;
        return this;
    }
}
