package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DescribeTableResult {
    private TableDescription table;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeTableResult)) {
            return false;
        }
        DescribeTableResult describeTableResult = (DescribeTableResult) obj;
        if ((describeTableResult.getTable() == null) ^ (getTable() == null)) {
            return false;
        }
        return describeTableResult.getTable() == null || describeTableResult.getTable().equals(getTable());
    }

    public TableDescription getTable() {
        return this.table;
    }

    public int hashCode() {
        return (getTable() == null ? 0 : getTable().hashCode()) + 31;
    }

    public void setTable(TableDescription tableDescription) {
        this.table = tableDescription;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.table != null) {
            sb.append("Table: " + this.table + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public DescribeTableResult withTable(TableDescription tableDescription) {
        this.table = tableDescription;
        return this;
    }
}
