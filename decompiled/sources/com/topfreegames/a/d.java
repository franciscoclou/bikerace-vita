package com.topfreegames.a;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import com.topfreegames.bikerace.ag;

/* JADX INFO: compiled from: RetentionManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {
    private static d g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SharedPreferences f811a;
    private e b;
    private int[] c;
    private long d;
    private boolean e;
    private SparseBooleanArray f = new SparseBooleanArray();

    public static void a(Context context, e eVar, int[] iArr) {
        synchronized (d.class) {
            if (g == null) {
                g = new d(context, eVar, iArr);
            }
        }
    }

    public static d a() {
        d dVar;
        synchronized (d.class) {
            if (g == null) {
                throw new IllegalArgumentException("Call init() first");
            }
            dVar = g;
        }
        return dVar;
    }

    private d(Context context, e eVar, int[] iArr) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (eVar == null) {
            throw new IllegalArgumentException("Analytics cannot be null!");
        }
        this.f811a = context.getSharedPreferences(String.valueOf(context.getPackageName()) + ".retention", 0);
        this.b = eVar;
        this.c = iArr;
        this.d = this.f811a.getLong("a", -1L);
        this.e = this.f811a.getBoolean("b", false);
        for (int i : iArr) {
            this.f.put(i, b(i));
        }
    }

    public void a(Activity activity, boolean z) {
        if (this.d < 0) {
            if (z) {
                this.d = com.topfreegames.c.a.a().getTime();
                this.e = true;
                this.b.a(0);
                ag.a(activity, 0);
            } else {
                this.d = 0L;
                this.e = false;
            }
            this.f811a.edit().putLong("a", this.d).putBoolean("b", this.e).commit();
        }
    }

    public void a(Activity activity) {
        if (this.e && this.d > 0 && this.c != null) {
            int iFloor = (int) Math.floor(((com.topfreegames.c.a.a().getTime() - this.d) + 43200000) / 86400000);
            for (int i : this.c) {
                if (i == iFloor && this.f.get(i)) {
                    this.b.a(i);
                    ag.a(activity, i);
                    a(i);
                }
            }
        }
    }

    private void a(int i) {
        this.f811a.edit().putBoolean("c_" + Integer.toString(i), true).commit();
        this.f.put(i, false);
    }

    private boolean b(int i) {
        return !this.f811a.getBoolean(new StringBuilder("c_").append(Integer.toString(i)).toString(), false);
    }
}
