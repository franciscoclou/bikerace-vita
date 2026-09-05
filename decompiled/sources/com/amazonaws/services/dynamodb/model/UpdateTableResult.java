package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UpdateTableResult {
    private TableDescription tableDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateTableResult)) {
            return false;
        }
        UpdateTableResult updateTableResult = (UpdateTableResult) obj;
        if ((updateTableResult.getTableDescription() == null) ^ (getTableDescription() == null)) {
            return false;
        }
        return updateTableResult.getTableDescription() == null || updateTableResult.getTableDescription().equals(getTableDescription());
    }

    public TableDescription getTableDescription() {
        return this.tableDescription;
    }

    public int hashCode() {
        return (getTableDescription() == null ? 0 : getTableDescription().hashCode()) + 31;
    }

    public void setTableDescription(TableDescription tableDescription) {
        this.tableDescription = tableDescription;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.tableDescription != null) {
            sb.append("TableDescription: " + this.tableDescription + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public UpdateTableResult withTableDescription(TableDescription tableDescription) {
        this.tableDescription = tableDescription;
        return this;
    }
}
