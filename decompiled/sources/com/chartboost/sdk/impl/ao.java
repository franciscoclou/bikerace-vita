package com.chartboost.sdk.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ao extends LinkedHashMap<String, Object> implements al {
    public ao() {
    }

    public ao(String str, Object obj) {
        put(str, obj);
    }

    @Override // com.chartboost.sdk.impl.al
    public boolean b(String str) {
        return super.containsKey(str);
    }

    @Override // com.chartboost.sdk.impl.al
    public Object a(String str) {
        return super.get(str);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Object put(String str, Object obj) {
        return super.put(str, obj);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey().toString(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return ae.a(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (!(obj instanceof al)) {
            return false;
        }
        al alVar = (al) obj;
        if (!keySet().equals(alVar.keySet())) {
            return false;
        }
        for (String str : keySet()) {
            Object objA = a(str);
            Object objA2 = alVar.a(str);
            if (objA == null && objA2 != null) {
                return false;
            }
            if (objA2 == null) {
                if (objA != null) {
                    return false;
                }
            } else if ((objA instanceof Number) && (objA2 instanceof Number)) {
                if (((Number) objA).doubleValue() != ((Number) objA2).doubleValue()) {
                    return false;
                }
            } else if ((objA instanceof Pattern) && (objA2 instanceof Pattern)) {
                Pattern pattern = (Pattern) objA;
                Pattern pattern2 = (Pattern) objA2;
                if (!pattern.pattern().equals(pattern2.pattern()) || pattern.flags() != pattern2.flags()) {
                    return false;
                }
            } else if (!objA.equals(objA2)) {
                return false;
            }
        }
        return true;
    }
}
