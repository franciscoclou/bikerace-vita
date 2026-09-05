package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreateTableResult {
    private TableDescription tableDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTableResult)) {
            return false;
        }
        CreateTableResult createTableResult = (CreateTableResult) obj;
        if ((createTableResult.getTableDescription() == null) ^ (getTableDescription() == null)) {
            return false;
        }
        return createTableResult.getTableDescription() == null || createTableResult.getTableDescription().equals(getTableDescription());
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

    public CreateTableResult withTableDescription(TableDescription tableDescription) {
        this.tableDescription = tableDescription;
        return this;
    }
}
