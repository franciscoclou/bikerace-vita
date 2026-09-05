package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteItemRequest extends e {
    private Map<String, ExpectedAttributeValue> expected;
    private Key key;
    private String returnValues;
    private String tableName;

    public DeleteItemRequest() {
    }

    public DeleteItemRequest(String str, Key key) {
        this.tableName = str;
        this.key = key;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteItemRequest)) {
            return false;
        }
        DeleteItemRequest deleteItemRequest = (DeleteItemRequest) obj;
        if ((deleteItemRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (deleteItemRequest.getTableName() != null && !deleteItemRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((deleteItemRequest.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (deleteItemRequest.getKey() != null && !deleteItemRequest.getKey().equals(getKey())) {
            return false;
        }
        if ((deleteItemRequest.getExpected() == null) ^ (getExpected() == null)) {
            return false;
        }
        if (deleteItemRequest.getExpected() != null && !deleteItemRequest.getExpected().equals(getExpected())) {
            return false;
        }
        if ((deleteItemRequest.getReturnValues() == null) ^ (getReturnValues() == null)) {
            return false;
        }
        return deleteItemRequest.getReturnValues() == null || deleteItemRequest.getReturnValues().equals(getReturnValues());
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
        return (((getExpected() == null ? 0 : getExpected().hashCode()) + (((getKey() == null ? 0 : getKey().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31) + (getReturnValues() != null ? getReturnValues().hashCode() : 0);
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
        if (this.expected != null) {
            sb.append("Expected: " + this.expected + ", ");
        }
        if (this.returnValues != null) {
            sb.append("ReturnValues: " + this.returnValues + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public DeleteItemRequest withExpected(Map<String, ExpectedAttributeValue> map) {
        setExpected(map);
        return this;
    }

    public DeleteItemRequest withKey(Key key) {
        this.key = key;
        return this;
    }

    public DeleteItemRequest withReturnValues(ReturnValue returnValue) {
        this.returnValues = returnValue.toString();
        return this;
    }

    public DeleteItemRequest withReturnValues(String str) {
        this.returnValues = str;
        return this;
    }

    public DeleteItemRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
