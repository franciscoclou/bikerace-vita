package com.google.a.a.a;

import com.google.android.gms.analytics.internal.Command;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GAServiceProxy.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, String> f609a;
    private final long b;
    private final String c;
    private final List<Command> d;

    public z(Map<String, String> map, long j, String str, List<Command> list) {
        this.f609a = map;
        this.b = j;
        this.c = str;
        this.d = list;
    }

    public Map<String, String> a() {
        return this.f609a;
    }

    public long b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public List<Command> d() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PATH: ");
        sb.append(this.c);
        if (this.f609a != null) {
            sb.append("  PARAMS: ");
            for (Map.Entry<String, String> entry : this.f609a.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append(",  ");
            }
        }
        return sb.toString();
    }
}
