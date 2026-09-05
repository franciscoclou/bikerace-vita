package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AttributeValueUpdate {
    private String action;
    private AttributeValue value;

    public AttributeValueUpdate() {
    }

    public AttributeValueUpdate(AttributeValue attributeValue, AttributeAction attributeAction) {
        this.value = attributeValue;
        this.action = attributeAction.toString();
    }

    public AttributeValueUpdate(AttributeValue attributeValue, String str) {
        this.value = attributeValue;
        this.action = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributeValueUpdate)) {
            return false;
        }
        AttributeValueUpdate attributeValueUpdate = (AttributeValueUpdate) obj;
        if ((attributeValueUpdate.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (attributeValueUpdate.getValue() != null && !attributeValueUpdate.getValue().equals(getValue())) {
            return false;
        }
        if ((attributeValueUpdate.getAction() == null) ^ (getAction() == null)) {
            return false;
        }
        return attributeValueUpdate.getAction() == null || attributeValueUpdate.getAction().equals(getAction());
    }

    public String getAction() {
        return this.action;
    }

    public AttributeValue getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((getValue() == null ? 0 : getValue().hashCode()) + 31) * 31) + (getAction() != null ? getAction().hashCode() : 0);
    }

    public void setAction(AttributeAction attributeAction) {
        this.action = attributeAction.toString();
    }

    public void setAction(String str) {
        this.action = str;
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
        if (this.action != null) {
            sb.append("Action: " + this.action + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public AttributeValueUpdate withAction(AttributeAction attributeAction) {
        this.action = attributeAction.toString();
        return this;
    }

    public AttributeValueUpdate withAction(String str) {
        this.action = str;
        return this;
    }

    public AttributeValueUpdate withValue(AttributeValue attributeValue) {
        this.value = attributeValue;
        return this;
    }
}
