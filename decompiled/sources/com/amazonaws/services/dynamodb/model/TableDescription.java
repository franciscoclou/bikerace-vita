package com.amazonaws.services.dynamodb.model;

import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class TableDescription {
    private Date creationDateTime;
    private Long itemCount;
    private KeySchema keySchema;
    private ProvisionedThroughputDescription provisionedThroughput;
    private String tableName;
    private Long tableSizeBytes;
    private String tableStatus;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TableDescription)) {
            return false;
        }
        TableDescription tableDescription = (TableDescription) obj;
        if ((tableDescription.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (tableDescription.getTableName() != null && !tableDescription.getTableName().equals(getTableName())) {
            return false;
        }
        if ((tableDescription.getKeySchema() == null) ^ (getKeySchema() == null)) {
            return false;
        }
        if (tableDescription.getKeySchema() != null && !tableDescription.getKeySchema().equals(getKeySchema())) {
            return false;
        }
        if ((tableDescription.getTableStatus() == null) ^ (getTableStatus() == null)) {
            return false;
        }
        if (tableDescription.getTableStatus() != null && !tableDescription.getTableStatus().equals(getTableStatus())) {
            return false;
        }
        if ((tableDescription.getCreationDateTime() == null) ^ (getCreationDateTime() == null)) {
            return false;
        }
        if (tableDescription.getCreationDateTime() != null && !tableDescription.getCreationDateTime().equals(getCreationDateTime())) {
            return false;
        }
        if ((tableDescription.getProvisionedThroughput() == null) ^ (getProvisionedThroughput() == null)) {
            return false;
        }
        if (tableDescription.getProvisionedThroughput() != null && !tableDescription.getProvisionedThroughput().equals(getProvisionedThroughput())) {
            return false;
        }
        if ((tableDescription.getTableSizeBytes() == null) ^ (getTableSizeBytes() == null)) {
            return false;
        }
        if (tableDescription.getTableSizeBytes() != null && !tableDescription.getTableSizeBytes().equals(getTableSizeBytes())) {
            return false;
        }
        if ((tableDescription.getItemCount() == null) ^ (getItemCount() == null)) {
            return false;
        }
        return tableDescription.getItemCount() == null || tableDescription.getItemCount().equals(getItemCount());
    }

    public Date getCreationDateTime() {
        return this.creationDateTime;
    }

    public Long getItemCount() {
        return this.itemCount;
    }

    public KeySchema getKeySchema() {
        return this.keySchema;
    }

    public ProvisionedThroughputDescription getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public String getTableName() {
        return this.tableName;
    }

    public Long getTableSizeBytes() {
        return this.tableSizeBytes;
    }

    public String getTableStatus() {
        return this.tableStatus;
    }

    public int hashCode() {
        return (((getTableSizeBytes() == null ? 0 : getTableSizeBytes().hashCode()) + (((getProvisionedThroughput() == null ? 0 : getProvisionedThroughput().hashCode()) + (((getCreationDateTime() == null ? 0 : getCreationDateTime().hashCode()) + (((getTableStatus() == null ? 0 : getTableStatus().hashCode()) + (((getKeySchema() == null ? 0 : getKeySchema().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (getItemCount() != null ? getItemCount().hashCode() : 0);
    }

    public void setCreationDateTime(Date date) {
        this.creationDateTime = date;
    }

    public void setItemCount(Long l) {
        this.itemCount = l;
    }

    public void setKeySchema(KeySchema keySchema) {
        this.keySchema = keySchema;
    }

    public void setProvisionedThroughput(ProvisionedThroughputDescription provisionedThroughputDescription) {
        this.provisionedThroughput = provisionedThroughputDescription;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public void setTableSizeBytes(Long l) {
        this.tableSizeBytes = l;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus.toString();
    }

    public void setTableStatus(String str) {
        this.tableStatus = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.tableName != null) {
            sb.append("TableName: " + this.tableName + ", ");
        }
        if (this.keySchema != null) {
            sb.append("KeySchema: " + this.keySchema + ", ");
        }
        if (this.tableStatus != null) {
            sb.append("TableStatus: " + this.tableStatus + ", ");
        }
        if (this.creationDateTime != null) {
            sb.append("CreationDateTime: " + this.creationDateTime + ", ");
        }
        if (this.provisionedThroughput != null) {
            sb.append("ProvisionedThroughput: " + this.provisionedThroughput + ", ");
        }
        if (this.tableSizeBytes != null) {
            sb.append("TableSizeBytes: " + this.tableSizeBytes + ", ");
        }
        if (this.itemCount != null) {
            sb.append("ItemCount: " + this.itemCount + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public TableDescription withCreationDateTime(Date date) {
        this.creationDateTime = date;
        return this;
    }

    public TableDescription withItemCount(Long l) {
        this.itemCount = l;
        return this;
    }

    public TableDescription withKeySchema(KeySchema keySchema) {
        this.keySchema = keySchema;
        return this;
    }

    public TableDescription withProvisionedThroughput(ProvisionedThroughputDescription provisionedThroughputDescription) {
        this.provisionedThroughput = provisionedThroughputDescription;
        return this;
    }

    public TableDescription withTableName(String str) {
        this.tableName = str;
        return this;
    }

    public TableDescription withTableSizeBytes(Long l) {
        this.tableSizeBytes = l;
        return this;
    }

    public TableDescription withTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus.toString();
        return this;
    }

    public TableDescription withTableStatus(String str) {
        this.tableStatus = str;
        return this;
    }
}
