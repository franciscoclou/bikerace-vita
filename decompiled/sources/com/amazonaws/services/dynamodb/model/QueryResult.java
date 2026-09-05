package com.amazonaws.services.dynamodb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QueryResult {
    private Double consumedCapacityUnits;
    private Integer count;
    private List<Map<String, AttributeValue>> items;
    private Key lastEvaluatedKey;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof QueryResult)) {
            return false;
        }
        QueryResult queryResult = (QueryResult) obj;
        if ((queryResult.getItems() == null) ^ (getItems() == null)) {
            return false;
        }
        if (queryResult.getItems() != null && !queryResult.getItems().equals(getItems())) {
            return false;
        }
        if ((queryResult.getCount() == null) ^ (getCount() == null)) {
            return false;
        }
        if (queryResult.getCount() != null && !queryResult.getCount().equals(getCount())) {
            return false;
        }
        if ((queryResult.getLastEvaluatedKey() == null) ^ (getLastEvaluatedKey() == null)) {
            return false;
        }
        if (queryResult.getLastEvaluatedKey() != null && !queryResult.getLastEvaluatedKey().equals(getLastEvaluatedKey())) {
            return false;
        }
        if ((queryResult.getConsumedCapacityUnits() == null) ^ (getConsumedCapacityUnits() == null)) {
            return false;
        }
        return queryResult.getConsumedCapacityUnits() == null || queryResult.getConsumedCapacityUnits().equals(getConsumedCapacityUnits());
    }

    public Double getConsumedCapacityUnits() {
        return this.consumedCapacityUnits;
    }

    public Integer getCount() {
        return this.count;
    }

    public List<Map<String, AttributeValue>> getItems() {
        return this.items;
    }

    public Key getLastEvaluatedKey() {
        return this.lastEvaluatedKey;
    }

    public int hashCode() {
        return (((getLastEvaluatedKey() == null ? 0 : getLastEvaluatedKey().hashCode()) + (((getCount() == null ? 0 : getCount().hashCode()) + (((getItems() == null ? 0 : getItems().hashCode()) + 31) * 31)) * 31)) * 31) + (getConsumedCapacityUnits() != null ? getConsumedCapacityUnits().hashCode() : 0);
    }

    public void setConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public void setItems(Collection<Map<String, AttributeValue>> collection) {
        if (collection == null) {
            this.items = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.items = arrayList;
    }

    public void setLastEvaluatedKey(Key key) {
        this.lastEvaluatedKey = key;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.items != null) {
            sb.append("Items: " + this.items + ", ");
        }
        if (this.count != null) {
            sb.append("Count: " + this.count + ", ");
        }
        if (this.lastEvaluatedKey != null) {
            sb.append("LastEvaluatedKey: " + this.lastEvaluatedKey + ", ");
        }
        if (this.consumedCapacityUnits != null) {
            sb.append("ConsumedCapacityUnits: " + this.consumedCapacityUnits + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public QueryResult withConsumedCapacityUnits(Double d) {
        this.consumedCapacityUnits = d;
        return this;
    }

    public QueryResult withCount(Integer num) {
        this.count = num;
        return this;
    }

    public QueryResult withItems(Collection<Map<String, AttributeValue>> collection) {
        if (collection == null) {
            this.items = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.items = arrayList;
        }
        return this;
    }

    public QueryResult withItems(Map<String, AttributeValue>... mapArr) {
        if (getItems() == null) {
            setItems(new ArrayList(mapArr.length));
        }
        for (Map<String, AttributeValue> map : mapArr) {
            getItems().add(map);
        }
        return this;
    }

    public QueryResult withLastEvaluatedKey(Key key) {
        this.lastEvaluatedKey = key;
        return this;
    }
}
