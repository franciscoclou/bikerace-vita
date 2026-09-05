package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PutItemRequest extends e {
    private Map<String, ExpectedAttributeValue> expected;
    private Map<String, AttributeValue> item;
    private String returnValues;
    private String tableName;

    public PutItemRequest() {
    }

    public PutItemRequest(String str, Map<String, AttributeValue> map) {
        this.tableName = str;
        this.item = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutItemRequest)) {
            return false;
        }
        PutItemRequest putItemRequest = (PutItemRequest) obj;
        if ((putItemRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (putItemRequest.getTableName() != null && !putItemRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((putItemRequest.getItem() == null) ^ (getItem() == null)) {
            return false;
        }
        if (putItemRequest.getItem() != null && !putItemRequest.getItem().equals(getItem())) {
            return false;
        }
        if ((putItemRequest.getExpected() == null) ^ (getExpected() == null)) {
            return false;
        }
        if (putItemRequest.getExpected() != null && !putItemRequest.getExpected().equals(getExpected())) {
            return false;
        }
        if ((putItemRequest.getReturnValues() == null) ^ (getReturnValues() == null)) {
            return false;
        }
        return putItemRequest.getReturnValues() == null || putItemRequest.getReturnValues().equals(getReturnValues());
    }

    public Map<String, ExpectedAttributeValue> getExpected() {
        return this.expected;
    }

    public Map<String, AttributeValue> getItem() {
        return this.item;
    }

    public String getReturnValues() {
        return this.returnValues;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((getExpected() == null ? 0 : getExpected().hashCode()) + (((getItem() == null ? 0 : getItem().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31) + (getReturnValues() != null ? getReturnValues().hashCode() : 0);
    }

    public void setExpected(Map<String, ExpectedAttributeValue> map) {
        this.expected = map;
    }

    public void setItem(Map<String, AttributeValue> map) {
        this.item = map;
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
        if (this.item != null) {
            sb.append("Item: " + this.item + ", ");
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

    public PutItemRequest withExpected(Map<String, ExpectedAttributeValue> map) {
        setExpected(map);
        return this;
    }

    public PutItemRequest withItem(Map<String, AttributeValue> map) {
        setItem(map);
        return this;
    }

    public PutItemRequest withReturnValues(ReturnValue returnValue) {
        this.returnValues = returnValue.toString();
        return this;
    }

    public PutItemRequest withReturnValues(String str) {
        this.returnValues = str;
        return this;
    }

    public PutItemRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
