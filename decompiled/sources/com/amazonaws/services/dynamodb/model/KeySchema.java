package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class KeySchema {
    private KeySchemaElement hashKeyElement;
    private KeySchemaElement rangeKeyElement;

    public KeySchema() {
    }

    public KeySchema(KeySchemaElement keySchemaElement) {
        this.hashKeyElement = keySchemaElement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeySchema)) {
            return false;
        }
        KeySchema keySchema = (KeySchema) obj;
        if ((keySchema.getHashKeyElement() == null) ^ (getHashKeyElement() == null)) {
            return false;
        }
        if (keySchema.getHashKeyElement() != null && !keySchema.getHashKeyElement().equals(getHashKeyElement())) {
            return false;
        }
        if ((keySchema.getRangeKeyElement() == null) ^ (getRangeKeyElement() == null)) {
            return false;
        }
        return keySchema.getRangeKeyElement() == null || keySchema.getRangeKeyElement().equals(getRangeKeyElement());
    }

    public KeySchemaElement getHashKeyElement() {
        return this.hashKeyElement;
    }

    public KeySchemaElement getRangeKeyElement() {
        return this.rangeKeyElement;
    }

    public int hashCode() {
        return (((getHashKeyElement() == null ? 0 : getHashKeyElement().hashCode()) + 31) * 31) + (getRangeKeyElement() != null ? getRangeKeyElement().hashCode() : 0);
    }

    public void setHashKeyElement(KeySchemaElement keySchemaElement) {
        this.hashKeyElement = keySchemaElement;
    }

    public void setRangeKeyElement(KeySchemaElement keySchemaElement) {
        this.rangeKeyElement = keySchemaElement;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.hashKeyElement != null) {
            sb.append("HashKeyElement: " + this.hashKeyElement + ", ");
        }
        if (this.rangeKeyElement != null) {
            sb.append("RangeKeyElement: " + this.rangeKeyElement + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public KeySchema withHashKeyElement(KeySchemaElement keySchemaElement) {
        this.hashKeyElement = keySchemaElement;
        return this;
    }

    public KeySchema withRangeKeyElement(KeySchemaElement keySchemaElement) {
        this.rangeKeyElement = keySchemaElement;
        return this;
    }
}
