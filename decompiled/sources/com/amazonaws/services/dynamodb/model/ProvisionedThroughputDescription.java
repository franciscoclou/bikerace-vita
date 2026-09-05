package com.amazonaws.services.dynamodb.model;

import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ProvisionedThroughputDescription {
    private Date lastDecreaseDateTime;
    private Date lastIncreaseDateTime;
    private Long readCapacityUnits;
    private Long writeCapacityUnits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisionedThroughputDescription)) {
            return false;
        }
        ProvisionedThroughputDescription provisionedThroughputDescription = (ProvisionedThroughputDescription) obj;
        if ((provisionedThroughputDescription.getLastIncreaseDateTime() == null) ^ (getLastIncreaseDateTime() == null)) {
            return false;
        }
        if (provisionedThroughputDescription.getLastIncreaseDateTime() != null && !provisionedThroughputDescription.getLastIncreaseDateTime().equals(getLastIncreaseDateTime())) {
            return false;
        }
        if ((provisionedThroughputDescription.getLastDecreaseDateTime() == null) ^ (getLastDecreaseDateTime() == null)) {
            return false;
        }
        if (provisionedThroughputDescription.getLastDecreaseDateTime() != null && !provisionedThroughputDescription.getLastDecreaseDateTime().equals(getLastDecreaseDateTime())) {
            return false;
        }
        if ((provisionedThroughputDescription.getReadCapacityUnits() == null) ^ (getReadCapacityUnits() == null)) {
            return false;
        }
        if (provisionedThroughputDescription.getReadCapacityUnits() != null && !provisionedThroughputDescription.getReadCapacityUnits().equals(getReadCapacityUnits())) {
            return false;
        }
        if ((provisionedThroughputDescription.getWriteCapacityUnits() == null) ^ (getWriteCapacityUnits() == null)) {
            return false;
        }
        return provisionedThroughputDescription.getWriteCapacityUnits() == null || provisionedThroughputDescription.getWriteCapacityUnits().equals(getWriteCapacityUnits());
    }

    public Date getLastDecreaseDateTime() {
        return this.lastDecreaseDateTime;
    }

    public Date getLastIncreaseDateTime() {
        return this.lastIncreaseDateTime;
    }

    public Long getReadCapacityUnits() {
        return this.readCapacityUnits;
    }

    public Long getWriteCapacityUnits() {
        return this.writeCapacityUnits;
    }

    public int hashCode() {
        return (((getReadCapacityUnits() == null ? 0 : getReadCapacityUnits().hashCode()) + (((getLastDecreaseDateTime() == null ? 0 : getLastDecreaseDateTime().hashCode()) + (((getLastIncreaseDateTime() == null ? 0 : getLastIncreaseDateTime().hashCode()) + 31) * 31)) * 31)) * 31) + (getWriteCapacityUnits() != null ? getWriteCapacityUnits().hashCode() : 0);
    }

    public void setLastDecreaseDateTime(Date date) {
        this.lastDecreaseDateTime = date;
    }

    public void setLastIncreaseDateTime(Date date) {
        this.lastIncreaseDateTime = date;
    }

    public void setReadCapacityUnits(Long l) {
        this.readCapacityUnits = l;
    }

    public void setWriteCapacityUnits(Long l) {
        this.writeCapacityUnits = l;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.lastIncreaseDateTime != null) {
            sb.append("LastIncreaseDateTime: " + this.lastIncreaseDateTime + ", ");
        }
        if (this.lastDecreaseDateTime != null) {
            sb.append("LastDecreaseDateTime: " + this.lastDecreaseDateTime + ", ");
        }
        if (this.readCapacityUnits != null) {
            sb.append("ReadCapacityUnits: " + this.readCapacityUnits + ", ");
        }
        if (this.writeCapacityUnits != null) {
            sb.append("WriteCapacityUnits: " + this.writeCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ProvisionedThroughputDescription withLastDecreaseDateTime(Date date) {
        this.lastDecreaseDateTime = date;
        return this;
    }

    public ProvisionedThroughputDescription withLastIncreaseDateTime(Date date) {
        this.lastIncreaseDateTime = date;
        return this;
    }

    public ProvisionedThroughputDescription withReadCapacityUnits(Long l) {
        this.readCapacityUnits = l;
        return this;
    }

    public ProvisionedThroughputDescription withWriteCapacityUnits(Long l) {
        this.writeCapacityUnits = l;
        return this;
    }
}
