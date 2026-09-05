package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListTablesRequest extends e {
    private String exclusiveStartTableName;
    private Integer limit;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTablesRequest)) {
            return false;
        }
        ListTablesRequest listTablesRequest = (ListTablesRequest) obj;
        if ((listTablesRequest.getExclusiveStartTableName() == null) ^ (getExclusiveStartTableName() == null)) {
            return false;
        }
        if (listTablesRequest.getExclusiveStartTableName() != null && !listTablesRequest.getExclusiveStartTableName().equals(getExclusiveStartTableName())) {
            return false;
        }
        if ((listTablesRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return listTablesRequest.getLimit() == null || listTablesRequest.getLimit().equals(getLimit());
    }

    public String getExclusiveStartTableName() {
        return this.exclusiveStartTableName;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public int hashCode() {
        return (((getExclusiveStartTableName() == null ? 0 : getExclusiveStartTableName().hashCode()) + 31) * 31) + (getLimit() != null ? getLimit().hashCode() : 0);
    }

    public void setExclusiveStartTableName(String str) {
        this.exclusiveStartTableName = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.exclusiveStartTableName != null) {
            sb.append("ExclusiveStartTableName: " + this.exclusiveStartTableName + ", ");
        }
        if (this.limit != null) {
            sb.append("Limit: " + this.limit + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ListTablesRequest withExclusiveStartTableName(String str) {
        this.exclusiveStartTableName = str;
        return this;
    }

    public ListTablesRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
