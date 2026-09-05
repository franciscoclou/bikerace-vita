package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DescribeTableRequest extends e {
    private String tableName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeTableRequest)) {
            return false;
        }
        DescribeTableRequest describeTableRequest = (DescribeTableRequest) obj;
        if ((describeTableRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        return describeTableRequest.getTableName() == null || describeTableRequest.getTableName().equals(getTableName());
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (getTableName() == null ? 0 : getTableName().hashCode()) + 31;
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
        sb.append("}");
        return sb.toString();
    }

    public DescribeTableRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
