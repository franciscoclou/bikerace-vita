package com.amazonaws.services.dynamodb.model;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AttributeValue {
    private ByteBuffer b;
    private List<ByteBuffer> bS;
    private String n;
    private List<String> nS;
    private String s;
    private List<String> sS;

    public AttributeValue() {
    }

    public AttributeValue(String str) {
        this.s = str;
    }

    public AttributeValue(List<String> list) {
        this.sS = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributeValue)) {
            return false;
        }
        AttributeValue attributeValue = (AttributeValue) obj;
        if ((attributeValue.getS() == null) ^ (getS() == null)) {
            return false;
        }
        if (attributeValue.getS() != null && !attributeValue.getS().equals(getS())) {
            return false;
        }
        if ((attributeValue.getN() == null) ^ (getN() == null)) {
            return false;
        }
        if (attributeValue.getN() != null && !attributeValue.getN().equals(getN())) {
            return false;
        }
        if ((attributeValue.getB() == null) ^ (getB() == null)) {
            return false;
        }
        if (attributeValue.getB() != null && !attributeValue.getB().equals(getB())) {
            return false;
        }
        if ((attributeValue.getSS() == null) ^ (getSS() == null)) {
            return false;
        }
        if (attributeValue.getSS() != null && !attributeValue.getSS().equals(getSS())) {
            return false;
        }
        if ((attributeValue.getNS() == null) ^ (getNS() == null)) {
            return false;
        }
        if (attributeValue.getNS() != null && !attributeValue.getNS().equals(getNS())) {
            return false;
        }
        if ((attributeValue.getBS() == null) ^ (getBS() == null)) {
            return false;
        }
        return attributeValue.getBS() == null || attributeValue.getBS().equals(getBS());
    }

    public ByteBuffer getB() {
        return this.b;
    }

    public List<ByteBuffer> getBS() {
        return this.bS;
    }

    public String getN() {
        return this.n;
    }

    public List<String> getNS() {
        return this.nS;
    }

    public String getS() {
        return this.s;
    }

    public List<String> getSS() {
        return this.sS;
    }

    public int hashCode() {
        return (((getNS() == null ? 0 : getNS().hashCode()) + (((getSS() == null ? 0 : getSS().hashCode()) + (((getB() == null ? 0 : getB().hashCode()) + (((getN() == null ? 0 : getN().hashCode()) + (((getS() == null ? 0 : getS().hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31) + (getBS() != null ? getBS().hashCode() : 0);
    }

    public void setB(ByteBuffer byteBuffer) {
        this.b = byteBuffer;
    }

    public void setBS(Collection<ByteBuffer> collection) {
        if (collection == null) {
            this.bS = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.bS = arrayList;
    }

    public void setN(String str) {
        this.n = str;
    }

    public void setNS(Collection<String> collection) {
        if (collection == null) {
            this.nS = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.nS = arrayList;
    }

    public void setS(String str) {
        this.s = str;
    }

    public void setSS(Collection<String> collection) {
        if (collection == null) {
            this.sS = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.sS = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.s != null) {
            sb.append("S: " + this.s + ", ");
        }
        if (this.n != null) {
            sb.append("N: " + this.n + ", ");
        }
        if (this.b != null) {
            sb.append("B: " + this.b + ", ");
        }
        if (this.sS != null) {
            sb.append("SS: " + this.sS + ", ");
        }
        if (this.nS != null) {
            sb.append("NS: " + this.nS + ", ");
        }
        if (this.bS != null) {
            sb.append("BS: " + this.bS + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public AttributeValue withB(ByteBuffer byteBuffer) {
        this.b = byteBuffer;
        return this;
    }

    public AttributeValue withBS(Collection<ByteBuffer> collection) {
        if (collection == null) {
            this.bS = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.bS = arrayList;
        }
        return this;
    }

    public AttributeValue withBS(ByteBuffer... byteBufferArr) {
        if (getBS() == null) {
            setBS(new ArrayList(byteBufferArr.length));
        }
        for (ByteBuffer byteBuffer : byteBufferArr) {
            getBS().add(byteBuffer);
        }
        return this;
    }

    public AttributeValue withN(String str) {
        this.n = str;
        return this;
    }

    public AttributeValue withNS(Collection<String> collection) {
        if (collection == null) {
            this.nS = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.nS = arrayList;
        }
        return this;
    }

    public AttributeValue withNS(String... strArr) {
        if (getNS() == null) {
            setNS(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getNS().add(str);
        }
        return this;
    }

    public AttributeValue withS(String str) {
        this.s = str;
        return this;
    }

    public AttributeValue withSS(Collection<String> collection) {
        if (collection == null) {
            this.sS = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.sS = arrayList;
        }
        return this;
    }

    public AttributeValue withSS(String... strArr) {
        if (getSS() == null) {
            setSS(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getSS().add(str);
        }
        return this;
    }
}
