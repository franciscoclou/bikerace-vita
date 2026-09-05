package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ProvisionedThroughput {
    private Long readCapacityUnits;
    private Long writeCapacityUnits;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisionedThroughput)) {
            return false;
        }
        ProvisionedThroughput provisionedThroughput = (ProvisionedThroughput) obj;
        if ((provisionedThroughput.getReadCapacityUnits() == null) ^ (getReadCapacityUnits() == null)) {
            return false;
        }
        if (provisionedThroughput.getReadCapacityUnits() != null && !provisionedThroughput.getReadCapacityUnits().equals(getReadCapacityUnits())) {
            return false;
        }
        if ((provisionedThroughput.getWriteCapacityUnits() == null) ^ (getWriteCapacityUnits() == null)) {
            return false;
        }
        return provisionedThroughput.getWriteCapacityUnits() == null || provisionedThroughput.getWriteCapacityUnits().equals(getWriteCapacityUnits());
    }

    public Long getReadCapacityUnits() {
        return this.readCapacityUnits;
    }

    public Long getWriteCapacityUnits() {
        return this.writeCapacityUnits;
    }

    public int hashCode() {
        return (((getReadCapacityUnits() == null ? 0 : getReadCapacityUnits().hashCode()) + 31) * 31) + (getWriteCapacityUnits() != null ? getWriteCapacityUnits().hashCode() : 0);
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
        if (this.readCapacityUnits != null) {
            sb.append("ReadCapacityUnits: " + this.readCapacityUnits + ", ");
        }
        if (this.writeCapacityUnits != null) {
            sb.append("WriteCapacityUnits: " + this.writeCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ProvisionedThroughput withReadCapacityUnits(Long l) {
        this.readCapacityUnits = l;
        return this;
    }

    public ProvisionedThroughput withWriteCapacityUnits(Long l) {
        this.writeCapacityUnits = l;
        return this;
    }
}
