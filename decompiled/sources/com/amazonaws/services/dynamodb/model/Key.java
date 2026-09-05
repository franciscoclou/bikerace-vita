package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Key {
    private AttributeValue hashKeyElement;
    private AttributeValue rangeKeyElement;

    public Key() {
    }

    public Key(AttributeValue attributeValue) {
        this.hashKeyElement = attributeValue;
    }

    public Key(AttributeValue attributeValue, AttributeValue attributeValue2) {
        this.hashKeyElement = attributeValue;
        this.rangeKeyElement = attributeValue2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Key)) {
            return false;
        }
        Key key = (Key) obj;
        if ((key.getHashKeyElement() == null) ^ (getHashKeyElement() == null)) {
            return false;
        }
        if (key.getHashKeyElement() != null && !key.getHashKeyElement().equals(getHashKeyElement())) {
            return false;
        }
        if ((key.getRangeKeyElement() == null) ^ (getRangeKeyElement() == null)) {
            return false;
        }
        return key.getRangeKeyElement() == null || key.getRangeKeyElement().equals(getRangeKeyElement());
    }

    public AttributeValue getHashKeyElement() {
        return this.hashKeyElement;
    }

    public AttributeValue getRangeKeyElement() {
        return this.rangeKeyElement;
    }

    public int hashCode() {
        return (((getHashKeyElement() == null ? 0 : getHashKeyElement().hashCode()) + 31) * 31) + (getRangeKeyElement() != null ? getRangeKeyElement().hashCode() : 0);
    }

    public void setHashKeyElement(AttributeValue attributeValue) {
        this.hashKeyElement = attributeValue;
    }

    public void setRangeKeyElement(AttributeValue attributeValue) {
        this.rangeKeyElement = attributeValue;
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

    public Key withHashKeyElement(AttributeValue attributeValue) {
        this.hashKeyElement = attributeValue;
        return this;
    }

    public Key withRangeKeyElement(AttributeValue attributeValue) {
        this.rangeKeyElement = attributeValue;
        return this;
    }
}
