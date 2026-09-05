package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UpdateItemRequest extends e {
    private Map<String, AttributeValueUpdate> attributeUpdates;
    private Map<String, ExpectedAttributeValue> expected;
    private Key key;
    private String returnValues;
    private String tableName;

    public UpdateItemRequest() {
    }

    public UpdateItemRequest(String str, Key key, Map<String, AttributeValueUpdate> map) {
        this.tableName = str;
        this.key = key;
        this.attributeUpdates = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateItemRequest)) {
            return false;
        }
        UpdateItemRequest updateItemRequest = (UpdateItemRequest) obj;
        if ((updateItemRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (updateItemRequest.getTableName() != null && !updateItemRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((updateItemRequest.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (updateItemRequest.getKey() != null && !updateItemRequest.getKey().equals(getKey())) {
            return false;
        }
        if ((updateItemRequest.getAttributeUpdates() == null) ^ (getAttributeUpdates() == null)) {
            return false;
        }
        if (updateItemRequest.getAttributeUpdates() != null && !updateItemRequest.getAttributeUpdates().equals(getAttributeUpdates())) {
            return false;
        }
        if ((updateItemRequest.getExpected() == null) ^ (getExpected() == null)) {
            return false;
        }
        if (updateItemRequest.getExpected() != null && !updateItemRequest.getExpected().equals(getExpected())) {
            return false;
        }
        if ((updateItemRequest.getReturnValues() == null) ^ (getReturnValues() == null)) {
            return false;
        }
        return updateItemRequest.getReturnValues() == null || updateItemRequest.getReturnValues().equals(getReturnValues());
    }

    public Map<String, AttributeValueUpdate> getAttributeUpdates() {
        return this.attributeUpdates;
    }

    public Map<String, ExpectedAttributeValue> getExpected() {
        return this.expected;
    }

    public Key getKey() {
        return this.key;
    }

    public String getReturnValues() {
        return this.returnValues;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((getExpected() == null ? 0 : getExpected().hashCode()) + (((getAttributeUpdates() == null ? 0 : getAttributeUpdates().hashCode()) + (((getKey() == null ? 0 : getKey().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + (getReturnValues() != null ? getReturnValues().hashCode() : 0);
    }

    public void setAttributeUpdates(Map<String, AttributeValueUpdate> map) {
        this.attributeUpdates = map;
    }

    public void setExpected(Map<String, ExpectedAttributeValue> map) {
        this.expected = map;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setReturnValues(ReturnValue returnValue) {
        this.returnValues = returnValue.toString();
    }

    public void setReturnValues(String str) {
        this.returnValues = str;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.tableName != null) {
            sb.append("TableName: " + this.tableName + ", ");
        }
        if (this.key != null) {
            sb.append("Key: " + this.key + ", ");
        }
        if (this.attributeUpdates != null) {
            sb.append("AttributeUpdates: " + this.attributeUpdates + ", ");
        }
        if (this.expected != null) {
            sb.append("Expected: " + this.expected + ", ");
        }
        if (this.returnValues != null) {
            sb.append("ReturnValues: " + this.returnValues + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public UpdateItemRequest withAttributeUpdates(Map<String, AttributeValueUpdate> map) {
        setAttributeUpdates(map);
        return this;
    }

    public UpdateItemRequest withExpected(Map<String, ExpectedAttributeValue> map) {
        setExpected(map);
        return this;
    }

    public UpdateItemRequest withKey(Key key) {
        this.key = key;
        return this;
    }

    public UpdateItemRequest withReturnValues(ReturnValue returnValue) {
        this.returnValues = returnValue.toString();
        return this;
    }

    public UpdateItemRequest withReturnValues(String str) {
        this.returnValues = str;
        return this;
    }

    public UpdateItemRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
