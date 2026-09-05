package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class KeySchemaElement {
    private String attributeName;
    private String attributeType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeySchemaElement)) {
            return false;
        }
        KeySchemaElement keySchemaElement = (KeySchemaElement) obj;
        if ((keySchemaElement.getAttributeName() == null) ^ (getAttributeName() == null)) {
            return false;
        }
        if (keySchemaElement.getAttributeName() != null && !keySchemaElement.getAttributeName().equals(getAttributeName())) {
            return false;
        }
        if ((keySchemaElement.getAttributeType() == null) ^ (getAttributeType() == null)) {
            return false;
        }
        return keySchemaElement.getAttributeType() == null || keySchemaElement.getAttributeType().equals(getAttributeType());
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public String getAttributeType() {
        return this.attributeType;
    }

    public int hashCode() {
        return (((getAttributeName() == null ? 0 : getAttributeName().hashCode()) + 31) * 31) + (getAttributeType() != null ? getAttributeType().hashCode() : 0);
    }

    public void setAttributeName(String str) {
        this.attributeName = str;
    }

    public void setAttributeType(ScalarAttributeType scalarAttributeType) {
        this.attributeType = scalarAttributeType.toString();
    }

    public void setAttributeType(String str) {
        this.attributeType = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.attributeName != null) {
            sb.append("AttributeName: " + this.attributeName + ", ");
        }
        if (this.attributeType != null) {
            sb.append("AttributeType: " + this.attributeType + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public KeySchemaElement withAttributeName(String str) {
        this.attributeName = str;
        return this;
    }

    public KeySchemaElement withAttributeType(ScalarAttributeType scalarAttributeType) {
        this.attributeType = scalarAttributeType.toString();
        return this;
    }

    public KeySchemaElement withAttributeType(String str) {
        this.attributeType = str;
        return this;
    }
}
