package com.amazonaws.services.dynamodb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Condition {
    private List<AttributeValue> attributeValueList;
    private String comparisonOperator;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Condition)) {
            return false;
        }
        Condition condition = (Condition) obj;
        if ((condition.getAttributeValueList() == null) ^ (getAttributeValueList() == null)) {
            return false;
        }
        if (condition.getAttributeValueList() != null && !condition.getAttributeValueList().equals(getAttributeValueList())) {
            return false;
        }
        if ((condition.getComparisonOperator() == null) ^ (getComparisonOperator() == null)) {
            return false;
        }
        return condition.getComparisonOperator() == null || condition.getComparisonOperator().equals(getComparisonOperator());
    }

    public List<AttributeValue> getAttributeValueList() {
        return this.attributeValueList;
    }

    public String getComparisonOperator() {
        return this.comparisonOperator;
    }

    public int hashCode() {
        return (((getAttributeValueList() == null ? 0 : getAttributeValueList().hashCode()) + 31) * 31) + (getComparisonOperator() != null ? getComparisonOperator().hashCode() : 0);
    }

    public void setAttributeValueList(Collection<AttributeValue> collection) {
        if (collection == null) {
            this.attributeValueList = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.attributeValueList = arrayList;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
    }

    public void setComparisonOperator(String str) {
        this.comparisonOperator = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.attributeValueList != null) {
            sb.append("AttributeValueList: " + this.attributeValueList + ", ");
        }
        if (this.comparisonOperator != null) {
            sb.append("ComparisonOperator: " + this.comparisonOperator + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public Condition withAttributeValueList(Collection<AttributeValue> collection) {
        if (collection == null) {
            this.attributeValueList = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributeValueList = arrayList;
        }
        return this;
    }

    public Condition withAttributeValueList(AttributeValue... attributeValueArr) {
        if (getAttributeValueList() == null) {
            setAttributeValueList(new ArrayList(attributeValueArr.length));
        }
        for (AttributeValue attributeValue : attributeValueArr) {
            getAttributeValueList().add(attributeValue);
        }
        return this;
    }

    public Condition withComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
        return this;
    }

    public Condition withComparisonOperator(String str) {
        this.comparisonOperator = str;
        return this;
    }
}
