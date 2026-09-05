package com.amazonaws.services.dynamodb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class KeysAndAttributes {
    private List<String> attributesToGet;
    private List<Key> keys;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeysAndAttributes)) {
            return false;
        }
        KeysAndAttributes keysAndAttributes = (KeysAndAttributes) obj;
        if ((keysAndAttributes.getKeys() == null) ^ (getKeys() == null)) {
            return false;
        }
        if (keysAndAttributes.getKeys() != null && !keysAndAttributes.getKeys().equals(getKeys())) {
            return false;
        }
        if ((keysAndAttributes.getAttributesToGet() == null) ^ (getAttributesToGet() == null)) {
            return false;
        }
        return keysAndAttributes.getAttributesToGet() == null || keysAndAttributes.getAttributesToGet().equals(getAttributesToGet());
    }

    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public List<Key> getKeys() {
        return this.keys;
    }

    public int hashCode() {
        return (((getKeys() == null ? 0 : getKeys().hashCode()) + 31) * 31) + (getAttributesToGet() != null ? getAttributesToGet().hashCode() : 0);
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

    public void setKeys(Collection<Key> collection) {
        if (collection == null) {
            this.keys = null;
            return;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        arrayList.addAll(collection);
        this.keys = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.keys != null) {
            sb.append("Keys: " + this.keys + ", ");
        }
        if (this.attributesToGet != null) {
            sb.append("AttributesToGet: " + this.attributesToGet + ", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public KeysAndAttributes withAttributesToGet(Collection<String> collection) {
        if (collection == null) {
            this.attributesToGet = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.attributesToGet = arrayList;
        }
        return this;
    }

    public KeysAndAttributes withAttributesToGet(String... strArr) {
        if (getAttributesToGet() == null) {
            setAttributesToGet(new ArrayList(strArr.length));
        }
        for (String str : strArr) {
            getAttributesToGet().add(str);
        }
        return this;
    }

    public KeysAndAttributes withKeys(Collection<Key> collection) {
        if (collection == null) {
            this.keys = null;
        } else {
            ArrayList arrayList = new ArrayList(collection.size());
            arrayList.addAll(collection);
            this.keys = arrayList;
        }
        return this;
    }

    public KeysAndAttributes withKeys(Key... keyArr) {
        if (getKeys() == null) {
            setKeys(new ArrayList(keyArr.length));
        }
        for (Key key : keyArr) {
            getKeys().add(key);
        }
        return this;
    }
}
