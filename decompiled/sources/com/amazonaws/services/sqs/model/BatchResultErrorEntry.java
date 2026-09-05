package com.amazonaws.services.sqs.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchResultErrorEntry {
    private String code;
    private String id;
    private String message;
    private Boolean senderFault;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchResultErrorEntry)) {
            return false;
        }
        BatchResultErrorEntry batchResultErrorEntry = (BatchResultErrorEntry) obj;
        if ((batchResultErrorEntry.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (batchResultErrorEntry.getId() != null && !batchResultErrorEntry.getId().equals(getId())) {
            return false;
        }
        if ((batchResultErrorEntry.isSenderFault() == null) ^ (isSenderFault() == null)) {
            return false;
        }
        if (batchResultErrorEntry.isSenderFault() != null && !batchResultErrorEntry.isSenderFault().equals(isSenderFault())) {
            return false;
        }
        if ((batchResultErrorEntry.getCode() == null) ^ (getCode() == null)) {
            return false;
        }
        if (batchResultErrorEntry.getCode() != null && !batchResultErrorEntry.getCode().equals(getCode())) {
            return false;
        }
        if ((batchResultErrorEntry.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        return batchResultErrorEntry.getMessage() == null || batchResultErrorEntry.getMessage().equals(getMessage());
    }

    public String getCode() {
        return this.code;
    }

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public Boolean getSenderFault() {
        return this.senderFault;
    }

    public int hashCode() {
        return (((getCode() == null ? 0 : getCode().hashCode()) + (((isSenderFault() == null ? 0 : isSenderFault().hashCode()) + (((getId() == null ? 0 : getId().hashCode()) + 31) * 31)) * 31)) * 31) + (getMessage() != null ? getMessage().hashCode() : 0);
    }

    public Boolean isSenderFault() {
        return this.senderFault;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setSenderFault(Boolean bool) {
        this.senderFault = bool;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.id != null) {
            sb.append("Id: " + this.id + ", ");
        }
        if (this.senderFault != null) {
            sb.append("SenderFault: " + this.senderFault + ", ");
        }
        if (this.code != null) {
            sb.append("Code: " + this.code + ", ");
        }
        if (this.message != null) {
            sb.append("Message: " + this.message + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public BatchResultErrorEntry withCode(String str) {
        this.code = str;
        return this;
    }

    public BatchResultErrorEntry withId(String str) {
        this.id = str;
        return this;
    }

    public BatchResultErrorEntry withMessage(String str) {
        this.message = str;
        return this;
    }

    public BatchResultErrorEntry withSenderFault(Boolean bool) {
        this.senderFault = bool;
        return this;
    }
}
