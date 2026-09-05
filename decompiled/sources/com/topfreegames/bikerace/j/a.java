package com.topfreegames.bikerace.j;

import android.content.res.Resources;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.c;
import com.topfreegames.bikerace.t;
import com.topfreegames.bikerace.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RecommendedBikeManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    private static a h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<Integer, Integer> f1270a = new HashMap();
    private Map<Integer, Integer> b = new HashMap();
    private Map<Integer, Integer> c = new HashMap();
    private boolean d = false;
    private z e;
    private bb f;
    private t g;

    private a(z zVar, bb bbVar, t tVar) {
        if (zVar == null || bbVar == null || tVar == null) {
            throw new IllegalArgumentException("RecommendedBikeManager's arguments cannot be null");
        }
        this.e = zVar;
        this.f = bbVar;
        this.g = tVar;
    }

    public static void a(z zVar, bb bbVar, t tVar) {
        if (h == null) {
            synchronized (a.class) {
                if (h == null) {
                    h = new a(zVar, bbVar, tVar);
                }
            }
        }
    }

    public void a(int i, int i2) {
        int i3 = (i * 8) + i2;
        int iIntValue = this.b.containsKey(Integer.valueOf(i3)) ? this.b.get(Integer.valueOf(i3)).intValue() + 1 : 1;
        int iIntValue2 = this.c.containsKey(Integer.valueOf(i3)) ? 1 + this.c.get(Integer.valueOf(i3)).intValue() : 1;
        this.b.put(Integer.valueOf(i3), Integer.valueOf(iIntValue));
        this.c.put(Integer.valueOf(i3), Integer.valueOf(iIntValue2));
    }

    public void b(int i, int i2) {
        int i3 = (i * 8) + i2;
        int iIntValue = this.f1270a.containsKey(Integer.valueOf(i3)) ? this.f1270a.get(Integer.valueOf(i3)).intValue() + 1 : 1;
        this.c.put(Integer.valueOf(i3), 0);
        this.f1270a.put(Integer.valueOf(i3), Integer.valueOf(iIntValue));
    }

    public boolean c(int i, int i2) {
        int i3 = (i * 8) + i2;
        return b() && (i2 > this.f.az() || i > this.f.aA()) && this.d && this.b.containsKey(Integer.valueOf(i3)) && this.b.get(Integer.valueOf(i3)).intValue() > this.f.aB() && this.c.containsKey(Integer.valueOf(i3)) && this.c.get(Integer.valueOf(i3)).intValue() > this.f.aB() && d(i, i2) != null;
    }

    private boolean b() {
        return this.g.s() && this.f.ay();
    }

    public c d(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (this.e.a(c.ULTRA)) {
            arrayList.add(c.ULTRA);
        }
        if (this.e.a(c.SUPER)) {
            arrayList.add(c.SUPER);
        }
        if (this.e.a(c.GHOST)) {
            arrayList.add(c.GHOST);
        }
        if (arrayList.size() > 0) {
            return (c) arrayList.get(i2 % arrayList.size());
        }
        return null;
    }

    public String a(c cVar, Resources resources) {
        if (cVar == c.ULTRA) {
            return resources.getString(2131099801);
        }
        if (cVar == c.SUPER) {
            return resources.getString(2131099769);
        }
        if (cVar != c.GHOST) {
            return null;
        }
        return resources.getString(2131099772);
    }

    public void e(int i, int i2) {
        this.d = true;
    }

    public void f(int i, int i2) {
        this.d = false;
    }

    public static a a() {
        a aVar;
        synchronized (a.class) {
            if (h == null) {
                throw new IllegalArgumentException("Call init() first");
            }
            aVar = h;
        }
        return aVar;
    }
}
