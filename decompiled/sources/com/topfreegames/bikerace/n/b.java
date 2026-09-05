package com.topfreegames.bikerace.n;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ABTestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, Integer> f1342a;
    private Map<String, Integer> b;
    private boolean c;

    private b() {
        this.f1342a = new HashMap();
        this.b = new HashMap();
        this.c = true;
    }

    /* synthetic */ b(b bVar) {
        this();
    }
}
