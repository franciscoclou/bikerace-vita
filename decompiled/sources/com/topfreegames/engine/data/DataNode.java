package com.topfreegames.engine.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DataNode implements Serializable {
    private static final long serialVersionUID = 1;
    protected volatile HashMap<String, Object> attributes = new HashMap<>();
    protected volatile HashMap<String, DataNode> children = new HashMap<>();
    protected volatile String key;

    public DataNode(String str) {
        this.key = null;
        this.key = new String(str);
    }

    public synchronized String getKey() {
        return this.key;
    }

    public synchronized void addChild(DataNode dataNode) {
        this.children.put(dataNode.key, dataNode);
    }

    public synchronized DataNode getChild(String str) {
        return this.children.get(str);
    }

    public synchronized void changeChildKey(String str, String str2) {
        DataNode child = getChild(str);
        if (child != null) {
            this.children.remove(str);
            child.key = str2;
            this.children.put(str2, child);
        }
    }

    public synchronized void putBoolean(String str, Boolean bool) {
        this.attributes.put(str, bool);
    }

    public synchronized Boolean getBoolean(String str) {
        return (Boolean) this.attributes.get(str);
    }

    public synchronized void putInteger(String str, Integer num) {
        this.attributes.put(str, num);
    }

    public synchronized Integer getInteger(String str) {
        return (Integer) this.attributes.get(str);
    }

    public synchronized void putString(String str, String str2) {
        this.attributes.put(str, str2);
    }

    public synchronized String getString(String str) {
        return (String) this.attributes.get(str);
    }

    public synchronized void putFloat(String str, Float f) {
        this.attributes.put(str, f);
    }

    public synchronized Float getFloat(String str) {
        return (Float) this.attributes.get(str);
    }

    public synchronized void putLong(String str, Long l) {
        this.attributes.put(str, l);
    }

    public synchronized Long getLong(String str) {
        return (Long) this.attributes.get(str);
    }

    public synchronized void putObject(String str, Object obj) {
        this.attributes.put(str, obj);
    }

    public synchronized Object getObject(String str) {
        return this.attributes.get(str);
    }

    public synchronized void removeChild(String str) {
        this.children.remove(str);
    }

    public synchronized List<DataNode> listAllChildren() {
        return new ArrayList(this.children.values());
    }
}
