package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ExpectedAttributeValue {
    private Boolean exists;
    private AttributeValue value;

    public ExpectedAttributeValue() {
    }

    public ExpectedAttributeValue(AttributeValue attributeValue) {
        this.value = attributeValue;
    }

    public ExpectedAttributeValue(Boolean bool) {
        this.exists = bool;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExpectedAttributeValue)) {
            return false;
        }
        ExpectedAttributeValue expectedAttributeValue = (ExpectedAttributeValue) obj;
        if ((expectedAttributeValue.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (expectedAttributeValue.getValue() != null && !expectedAttributeValue.getValue().equals(getValue())) {
            return false;
        }
        if ((expectedAttributeValue.isExists() == null) ^ (isExists() == null)) {
            return false;
        }
        return expectedAttributeValue.isExists() == null || expectedAttributeValue.isExists().equals(isExists());
    }

    public Boolean getExists() {
        return this.exists;
    }

    public AttributeValue getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((getValue() == null ? 0 : getValue().hashCode()) + 31) * 31) + (isExists() != null ? isExists().hashCode() : 0);
    }

    public Boolean isExists() {
        return this.exists;
    }

    public void setExists(Boolean bool) {
        this.exists = bool;
    }

    public void setValue(AttributeValue attributeValue) {
        this.value = attributeValue;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.value != null) {
            sb.append("Value: " + this.value + ", ");
        }
        if (this.exists != null) {
            sb.append("Exists: " + this.exists + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ExpectedAttributeValue withExists(Boolean bool) {
        this.exists = bool;
        return this;
    }

    public ExpectedAttributeValue withValue(AttributeValue attributeValue) {
        this.value = attributeValue;
        return this;
    }
}
