package com.topfreegames.bikerace.worldcup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DailyBonus.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@SuppressLint({"UseSparseArrays"})
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f1452a;
    private SharedPreferences b;
    private g c;

    static {
        HashMap map = new HashMap();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(4, 1);
        map.put(5, 3);
        f1452a = Collections.unmodifiableMap(map);
    }

    f(Context context, g gVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (gVar == null) {
            throw new IllegalArgumentException("Listener cannot be nulL!");
        }
        this.b = context.getSharedPreferences("com.topfreegames.bikerace.wcdb", 0);
        this.c = gVar;
    }

    public boolean a() {
        return e() >= 1;
    }

    public void b() {
        if (e() >= 1) {
            a(com.topfreegames.c.a.a().getTime());
            h();
            if (this.c != null) {
                this.c.a(f1452a.get(Integer.valueOf(c())).intValue());
            }
        }
    }

    public int c() {
        return this.b.getInt("cds", 0);
    }

    public void d() {
        if (e() > 1) {
            i();
        } else if (c() >= f1452a.size()) {
            i();
        }
    }

    private int e() {
        return (int) ((f() - g()) / 86400000);
    }

    private long f() {
        long time = com.topfreegames.c.a.a().getTime();
        return time - ((time % 86400000) - 86400000);
    }

    private long g() {
        return this.b.getLong("lcd", f() - 86400000);
    }

    private void h() {
        this.b.edit().putInt("cds", c() + 1).commit();
    }

    private void a(long j) {
        this.b.edit().putLong("lcd", j).commit();
    }

    private void i() {
        this.b.edit().putInt("cds", 0).commit();
    }
}
