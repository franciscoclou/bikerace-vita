package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UpdateTableRequest extends e {
    private ProvisionedThroughput provisionedThroughput;
    private String tableName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateTableRequest)) {
            return false;
        }
        UpdateTableRequest updateTableRequest = (UpdateTableRequest) obj;
        if ((updateTableRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (updateTableRequest.getTableName() != null && !updateTableRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((updateTableRequest.getProvisionedThroughput() == null) ^ (getProvisionedThroughput() == null)) {
            return false;
        }
        return updateTableRequest.getProvisionedThroughput() == null || updateTableRequest.getProvisionedThroughput().equals(getProvisionedThroughput());
    }

    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getProvisionedThroughput() != null ? getProvisionedThroughput().hashCode() : 0);
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
        if (this.provisionedThroughput != null) {
            sb.append("ProvisionedThroughput: " + this.provisionedThroughput + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public UpdateTableRequest withProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
        return this;
    }

    public UpdateTableRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
