package com.amazonaws.services.dynamodb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListTablesResult {
    private String lastEvaluatedTableName;
    private List<String> tableNames;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTablesResult)) {
            return false;
        }
        ListTablesResult listTablesResult = (ListTablesResult) obj;
        if ((listTablesResult.getTableNames() == null) ^ (getTableNames() == null)) {
            return false;
        }
        if (listTablesResult.getTableNames() != null && !listTablesResult.getTableNames().equals(getTableNames())) {
            return false;
        }
        if ((listTablesResult.getLastEvaluatedTableName() == null) ^ (getLastEvaluatedTableName() == null)) {
            return false;
        }
        return listTablesResult.getLastEvaluatedTableName() == null || listTablesResult.getLastEvaluatedTableName().equals(getLastEvaluatedTableName());
    }

    public String getLastEvaluatedTableName() {
        return this.lastEvaluatedTableName;
    }

    public List<String> getTableNames() {
        return this.tableNames;
    }

    public int hashCode() {
        return (((getTableNames() == null ? 0 : getTableNames().hashCode()) + 31) * 31) + (getLastEvaluatedTableName() != null ? getLastEvaluatedTableName().hashCode() : 0);
    }

    public void setLastEvaluatedTableName(String str) {
        this.lastEvaluatedTableName = str;
    }

    public void setTableNames(Collection<String> collection) {
        if (collection == null) {
            this.tableNames = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.tableNames = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.tableNames != null) {
            sb.append("TableNames: " + this.tableNames + ", ");
        }
        if (this.lastEvaluatedTableName != null) {
            sb.append("LastEvaluatedTableName: " + this.lastEvaluatedTableName + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public ListTablesResult withLastEvaluatedTableName(String str) {
        this.lastEvaluatedTableName = str;
        return this;
    }

    public ListTablesResult withTableNames(Collection<String> collection) {
        if (collection == null) {
            this.tableNames = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.tableNames = arrayList;
        }
        return this;
    }

    public ListTablesResult withTableNames(String... strArr) {
        if (getTableNames() == null) {
            setTableNames(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getTableNames().add(str);
        }
        return this;
    }
}
