package com.amazonaws.services.dynamodb.model;

import com.amazonaws.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QueryRequest extends e {
    private List<String> attributesToGet;
    private Boolean consistentRead;
    private Boolean count;
    private Key exclusiveStartKey;
    private AttributeValue hashKeyValue;
    private Integer limit;
    private Condition rangeKeyCondition;
    private Boolean scanIndexForward;
    private String tableName;

    public QueryRequest() {
    }

    public QueryRequest(String str, AttributeValue attributeValue) {
        this.tableName = str;
        this.hashKeyValue = attributeValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof QueryRequest)) {
            return false;
        }
        QueryRequest queryRequest = (QueryRequest) obj;
        if ((queryRequest.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (queryRequest.getTableName() != null && !queryRequest.getTableName().equals(getTableName())) {
            return false;
        }
        if ((queryRequest.getAttributesToGet() == null) ^ (getAttributesToGet() == null)) {
            return false;
        }
        if (queryRequest.getAttributesToGet() != null && !queryRequest.getAttributesToGet().equals(getAttributesToGet())) {
            return false;
        }
        if ((queryRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (queryRequest.getLimit() != null && !queryRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((queryRequest.isConsistentRead() == null) ^ (isConsistentRead() == null)) {
            return false;
        }
        if (queryRequest.isConsistentRead() != null && !queryRequest.isConsistentRead().equals(isConsistentRead())) {
            return false;
        }
        if ((queryRequest.isCount() == null) ^ (isCount() == null)) {
            return false;
        }
        if (queryRequest.isCount() != null && !queryRequest.isCount().equals(isCount())) {
            return false;
        }
        if ((queryRequest.getHashKeyValue() == null) ^ (getHashKeyValue() == null)) {
            return false;
        }
        if (queryRequest.getHashKeyValue() != null && !queryRequest.getHashKeyValue().equals(getHashKeyValue())) {
            return false;
        }
        if ((queryRequest.getRangeKeyCondition() == null) ^ (getRangeKeyCondition() == null)) {
            return false;
        }
        if (queryRequest.getRangeKeyCondition() != null && !queryRequest.getRangeKeyCondition().equals(getRangeKeyCondition())) {
            return false;
        }
        if ((queryRequest.isScanIndexForward() == null) ^ (isScanIndexForward() == null)) {
            return false;
        }
        if (queryRequest.isScanIndexForward() != null && !queryRequest.isScanIndexForward().equals(isScanIndexForward())) {
            return false;
        }
        if ((queryRequest.getExclusiveStartKey() == null) ^ (getExclusiveStartKey() == null)) {
            return false;
        }
        return queryRequest.getExclusiveStartKey() == null || queryRequest.getExclusiveStartKey().equals(getExclusiveStartKey());
    }

    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public Boolean getConsistentRead() {
        return this.consistentRead;
    }

    public Boolean getCount() {
        return this.count;
    }

    public Key getExclusiveStartKey() {
        return this.exclusiveStartKey;
    }

    public AttributeValue getHashKeyValue() {
        return this.hashKeyValue;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Condition getRangeKeyCondition() {
        return this.rangeKeyCondition;
    }

    public Boolean getScanIndexForward() {
        return this.scanIndexForward;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return (((isScanIndexForward() == null ? 0 : isScanIndexForward().hashCode()) + (((getRangeKeyCondition() == null ? 0 : getRangeKeyCondition().hashCode()) + (((getHashKeyValue() == null ? 0 : getHashKeyValue().hashCode()) + (((isCount() == null ? 0 : isCount().hashCode()) + (((isConsistentRead() == null ? 0 : isConsistentRead().hashCode()) + (((getLimit() == null ? 0 : getLimit().hashCode()) + (((getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode()) + (((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (getExclusiveStartKey() != null ? getExclusiveStartKey().hashCode() : 0);
    }

    public Boolean isConsistentRead() {
        return this.consistentRead;
    }

    public Boolean isCount() {
        return this.count;
    }

    public Boolean isScanIndexForward() {
        return this.scanIndexForward;
    }

    public void setAttributesToGet(Collection<String> collection) {
        if (collection == null) {
            this.attributesToGet = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.attributesToGet = arrayList;
    }

    public void setConsistentRead(Boolean bool) {
        this.consistentRead = bool;
    }

    public void setCount(Boolean bool) {
        this.count = bool;
    }

    public void setExclusiveStartKey(Key key) {
        this.exclusiveStartKey = key;
    }

    public void setHashKeyValue(AttributeValue attributeValue) {
        this.hashKeyValue = attributeValue;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setRangeKeyCondition(Condition condition) {
        this.rangeKeyCondition = condition;
    }

    public void setScanIndexForward(Boolean bool) {
        this.scanIndexForward = bool;
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
        if (this.attributesToGet != null) {
            sb.append("AttributesToGet: " + this.attributesToGet + ", ");
        }
        if (this.limit != null) {
            sb.append("Limit: " + this.limit + ", ");
        }
        if (this.consistentRead != null) {
            sb.append("ConsistentRead: " + this.consistentRead + ", ");
        }
        if (this.count != null) {
            sb.append("Count: " + this.count + ", ");
        }
        if (this.hashKeyValue != null) {
            sb.append("HashKeyValue: " + this.hashKeyValue + ", ");
        }
        if (this.rangeKeyCondition != null) {
            sb.append("RangeKeyCondition: " + this.rangeKeyCondition + ", ");
        }
        if (this.scanIndexForward != null) {
            sb.append("ScanIndexForward: " + this.scanIndexForward + ", ");
        }
        if (this.exclusiveStartKey != null) {
            sb.append("ExclusiveStartKey: " + this.exclusiveStartKey + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public QueryRequest withAttributesToGet(Collection<String> collection) {
        if (collection == null) {
            this.attributesToGet = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributesToGet = arrayList;
        }
        return this;
    }

    public QueryRequest withAttributesToGet(String... strArr) {
        if (getAttributesToGet() == null) {
            setAttributesToGet(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAttributesToGet().add(str);
        }
        return this;
    }

    public QueryRequest withConsistentRead(Boolean bool) {
        this.consistentRead = bool;
        return this;
    }

    public QueryRequest withCount(Boolean bool) {
        this.count = bool;
        return this;
    }

    public QueryRequest withExclusiveStartKey(Key key) {
        this.exclusiveStartKey = key;
        return this;
    }

    public QueryRequest withHashKeyValue(AttributeValue attributeValue) {
        this.hashKeyValue = attributeValue;
        return this;
    }

    public QueryRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public QueryRequest withRangeKeyCondition(Condition condition) {
        this.rangeKeyCondition = condition;
        return this;
    }

    public QueryRequest withScanIndexForward(Boolean bool) {
        this.scanIndexForward = bool;
        return this;
    }

    public QueryRequest withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
