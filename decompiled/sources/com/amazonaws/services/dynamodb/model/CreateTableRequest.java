package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreateTableRequest extends e {
    private KeySchema keySchema;
    private ProvisionedThroughput provisionedThroughput;
    private String tableName;

    public CreateTableRequest() {
    }

    public CreateTableRequest(String str, KeySchema keySchema) {
        this.tableName = str;
        this.keySchema = keySchema;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTableRequest)) {
            return false;
        }
        CreateTableRequest createTableRequest = (CreateTableRequest) obj;
        if ((createTableRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (createTableRequest.getTableName() != null && !createTableRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((createTableRequest.getKeySchema() == null) ^ (getKeySchema() == null)) {
            return false;
        }
        if (createTableRequest.getKeySchema() != null && !createTableRequest.getKeySchema().equals(getKeySchema())) {
            return false;
        }
        if ((createTableRequest.getProvisionedThroughput() == null) ^ (getProvisionedThroughput() == null)) {
            return false;
        }
        return createTableRequest.getProvisionedThroughput() == null || createTableRequest.getProvisionedThroughput().equals(getProvisionedThroughput());
    }

    public KeySchema getKeySchema() {
        return this.keySchema;
    }

    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((getKeySchema() == null ? 0 : getKeySchema().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31) + (getProvisionedThroughput() != null ? getProvisionedThroughput().hashCode() : 0);
    }

    public void setKeySchema(KeySchema keySchema) {
        this.keySchema = keySchema;
    }

    public void setProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
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
        if (this.keySchema != null) {
            sb.append("KeySchema: " + this.keySchema + ", ");
        }
        if (this.provisionedThroughput != null) {
            sb.append("ProvisionedThroughput: " + this.provisionedThroughput + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public CreateTableRequest withKeySchema(KeySchema keySchema) {
        this.keySchema = keySchema;
        return this;
    }

    public CreateTableRequest withProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
        return this;
    }

    public CreateTableRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
