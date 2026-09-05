package com.b.a.a;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentMap<Class<?>, Set<com.crashlytics.android.internal.g>> f289a;
    private final ConcurrentMap<Class<?>, com.crashlytics.android.internal.h> b;
    private final String c;
    private final cf d;
    private final cd e;
    private final ThreadLocal<ConcurrentLinkedQueue<com.crashlytics.android.internal.e>> f;
    private final ThreadLocal<Boolean> g;
    private final Map<Class<?>, Set<Class<?>>> h;

    public bz(cf cfVar) {
        this(cfVar, "default");
    }

    public bz(cf cfVar, String str) {
        this(cfVar, str, cd.f291a);
    }

    private bz(cf cfVar, String str, cd cdVar) {
        this.f289a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.f = new cb(this);
        this.g = new cc(this);
        this.h = new HashMap();
        this.d = cfVar;
        this.c = str;
        this.e = cdVar;
    }

    public String toString() {
        return "[Bus \"" + this.c + "\"]";
    }
}
