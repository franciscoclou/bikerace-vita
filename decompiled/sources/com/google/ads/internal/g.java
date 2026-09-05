package com.google.ads.internal;

import android.os.SystemClock;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {
    private static long f = 0;
    private static long g = 0;
    private static long h = 0;
    private static long i = 0;
    private static long j = -1;
    private long b;
    private long c;
    private long d;
    private String m;
    private long n;
    private boolean k = false;
    private boolean l = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedList<Long> f679a = new LinkedList<>();
    private final LinkedList<Long> e = new LinkedList<>();
    private final LinkedList<Long> o = new LinkedList<>();
    private final LinkedList<com.google.ads.g.a> p = new LinkedList<>();

    public g() {
        a();
    }

    protected synchronized void a() {
        this.f679a.clear();
        this.b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.e.clear();
        this.n = -1L;
        this.o.clear();
        this.p.clear();
        this.k = false;
        this.l = false;
    }

    public synchronized void b() {
        this.o.clear();
        this.p.clear();
    }

    public synchronized void c() {
        this.n = SystemClock.elapsedRealtime();
    }

    public synchronized void a(com.google.ads.g.a aVar) {
        this.o.add(Long.valueOf(SystemClock.elapsedRealtime() - this.n));
        this.p.add(aVar);
    }

    public synchronized String d() {
        StringBuilder sb;
        sb = new StringBuilder();
        Iterator<Long> it = this.o.iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(jLongValue);
        }
        return sb.toString();
    }

    public synchronized String e() {
        StringBuilder sb;
        sb = new StringBuilder();
        for (com.google.ads.g.a aVar : this.p) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(aVar.ordinal());
        }
        return sb.toString();
    }

    protected void f() {
        com.google.ads.util.b.d("Ad clicked.");
        this.f679a.add(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    protected void g() {
        com.google.ads.util.b.d("Ad request loaded.");
        this.b = SystemClock.elapsedRealtime();
    }

    protected synchronized void h() {
        com.google.ads.util.b.d("Ad request before rendering.");
        this.c = SystemClock.elapsedRealtime();
    }

    protected void i() {
        com.google.ads.util.b.d("Ad request started.");
        this.d = SystemClock.elapsedRealtime();
        f++;
    }

    protected long j() {
        if (this.f679a.size() != this.e.size()) {
            return -1L;
        }
        return this.f679a.size();
    }

    protected String k() {
        if (this.f679a.isEmpty() || this.f679a.size() != this.e.size()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.f679a.size()) {
                if (i3 != 0) {
                    sb.append(",");
                }
                sb.append(Long.toString(this.e.get(i3).longValue() - this.f679a.get(i3).longValue()));
                i2 = i3 + 1;
            } else {
                return sb.toString();
            }
        }
    }

    protected String l() {
        if (this.f679a.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.f679a.size()) {
                if (i3 != 0) {
                    sb.append(",");
                }
                sb.append(Long.toString(this.f679a.get(i3).longValue() - this.b));
                i2 = i3 + 1;
            } else {
                return sb.toString();
            }
        }
    }

    protected long m() {
        return this.b - this.d;
    }

    protected synchronized long n() {
        return this.c - this.d;
    }

    protected long o() {
        return f;
    }

    protected synchronized long p() {
        return g;
    }

    protected synchronized void q() {
        com.google.ads.util.b.d("Ad request network error");
        g++;
    }

    protected synchronized void r() {
        g = 0L;
    }

    protected synchronized long s() {
        return h;
    }

    protected synchronized void t() {
        h++;
    }

    protected synchronized void u() {
        h = 0L;
    }

    protected synchronized long v() {
        return i;
    }

    protected synchronized void w() {
        i++;
    }

    protected synchronized void x() {
        i = 0L;
    }

    protected boolean y() {
        return this.k;
    }

    protected void z() {
        com.google.ads.util.b.d("Interstitial network error.");
        this.k = true;
    }

    protected boolean A() {
        return this.l;
    }

    protected void B() {
        com.google.ads.util.b.d("Interstitial no fill.");
        this.l = true;
    }

    public void C() {
        com.google.ads.util.b.d("Landing page dismissed.");
        this.e.add(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    protected String D() {
        return this.m;
    }

    public void a(String str) {
        com.google.ads.util.b.d("Prior impression ticket = " + str);
        this.m = str;
    }

    public static long E() {
        if (j != -1) {
            return SystemClock.elapsedRealtime() - j;
        }
        j = SystemClock.elapsedRealtime();
        return 0L;
    }
}
