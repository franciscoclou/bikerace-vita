package com.amazonaws.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f131a;
    private long b;
    private final Map<String, List<l>> c;
    private final Map<String, Number> d;

    public l() {
        this(System.currentTimeMillis(), -1L);
    }

    public l(long j) {
        this(j, -1L);
    }

    public l(long j, long j2) {
        this.c = new HashMap();
        this.d = new HashMap();
        this.f131a = j;
        this.b = j2;
    }

    public double a() {
        return TimeUnit.NANOSECONDS.toMicros(this.b - this.f131a) / 1000.0d;
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(String str, long j) {
        this.d.put(str, Long.valueOf(j));
    }

    public void a(String str, l lVar) {
        List<l> arrayList = this.c.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.c.put(str, arrayList);
        }
        arrayList.add(lVar);
    }

    public String toString() {
        return String.valueOf(a());
    }
}
