package com.topfreegames.bikerace.c;

import android.content.Context;
import android.content.SharedPreferences;
import com.topfreegames.bikerace.h.y;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

/* JADX INFO: compiled from: CollectiblesManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f1167a = null;
    private SharedPreferences b;
    private float d;
    private Map<c, Boolean> c = new HashMap();
    private com.topfreegames.engine.a.b e = new com.topfreegames.engine.a.b();

    private d(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.b = context.getSharedPreferences("com.topfreegames.bikerace.collectibles", 0);
        for (c cVar : c.valuesCustom()) {
            this.c.put(cVar, true);
        }
        b();
    }

    public static void a(Context context) {
        synchronized (d.class) {
            if (f1167a == null) {
                f1167a = new d(context);
            }
        }
    }

    public static d a() {
        d dVar;
        synchronized (d.class) {
            if (f1167a == null) {
                throw new IllegalStateException("Call init() first!");
            }
            dVar = f1167a;
        }
        return dVar;
    }

    public void a(c cVar, boolean z) {
        this.c.put(cVar, Boolean.valueOf(z));
    }

    private boolean b(c cVar) {
        return this.c.get(cVar).booleanValue();
    }

    public int a(c cVar) {
        return this.b.getInt(d(cVar), 0);
    }

    public void a(a[] aVarArr, boolean z) {
        int i = 0;
        if (aVarArr != null) {
            if (z) {
                int length = aVarArr.length;
                while (i < length) {
                    a aVar = aVarArr[i];
                    if (aVar.d == b.JUST_COLLECTED) {
                        c(aVar.b());
                        aVar.d = b.COLLECTED;
                        a(aVar);
                    }
                    i++;
                }
                return;
            }
            int length2 = aVarArr.length;
            while (i < length2) {
                a aVar2 = aVarArr[i];
                if (aVar2.e == b.JUST_COLLECTED) {
                    c(aVar2.b());
                    aVar2.e = b.NOT_COLLECTED;
                }
                i++;
            }
        }
    }

    public void a(a[] aVarArr) {
        if (aVarArr != null) {
            for (a aVar : aVarArr) {
                if (aVar.d == b.JUST_COLLECTED) {
                    aVar.d = b.NOT_COLLECTED;
                }
                if (aVar.e == b.JUST_COLLECTED) {
                    aVar.e = b.NOT_COLLECTED;
                }
            }
        }
    }

    public boolean b(a[] aVarArr) {
        if (aVarArr != null) {
            TreeSet treeSet = new TreeSet();
            for (a aVar : aVarArr) {
                if (treeSet.contains(Integer.valueOf(aVar.d()))) {
                    throw new IllegalArgumentException(String.format("Collectbles using the same id (%d)", Integer.valueOf(aVar.d())));
                }
                if (!y.a(aVar.b)) {
                    throw new IllegalArgumentException(String.format("Collectible using invalid world id (%)", Integer.valueOf(aVar.b)));
                }
                if (aVar.c <= 0 || aVar.c > y.b(aVar.b)) {
                    throw new IllegalArgumentException(String.format("Collectible using invalid level id (%d)", Integer.valueOf(aVar.c)));
                }
            }
        }
        return true;
    }

    public boolean a(a aVar, boolean z) {
        boolean zB = b(aVar.b());
        if (!zB) {
            return zB;
        }
        if (z) {
            return aVar.f && aVar.d == b.NOT_COLLECTED;
        }
        return this.d <= aVar.g && aVar.e == b.NOT_COLLECTED;
    }

    public boolean b(a aVar, boolean z) {
        boolean zB = b(aVar.b());
        if (zB) {
            return z && aVar.f && aVar.d == b.COLLECTED;
        }
        return zB;
    }

    public boolean a(com.topfreegames.engine.a.b bVar, a aVar, boolean z) {
        if (!a(aVar, z)) {
            return false;
        }
        this.e.a(bVar).c(aVar.a());
        return this.e.c() <= 0.5f;
    }

    public boolean a(int i, int i2, int i3, boolean z) {
        if (!z) {
            return false;
        }
        b bVarA = a(i, i2, i3);
        return bVarA == b.COLLECTED || bVarA == b.JUST_COLLECTED;
    }

    public void b() {
        this.d = new Random().nextFloat();
    }

    b a(int i, int i2, int i3) {
        return b.a(this.b.getInt(b(i, i2, i3), b.NOT_COLLECTED.ordinal()));
    }

    private void c(c cVar) {
        String strD = d(cVar);
        this.b.edit().putInt(strD, this.b.getInt(strD, 0) + 1).commit();
    }

    private void a(a aVar) {
        this.b.edit().putInt(b(aVar), aVar.d.ordinal()).commit();
    }

    private static String d(c cVar) {
        return String.format(Locale.US, "ck%d", Integer.valueOf(cVar.ordinal()));
    }

    private static String b(a aVar) {
        return b(aVar.b, aVar.c, aVar.f1164a);
    }

    private static String b(int i, int i2, int i3) {
        return String.format(Locale.US, "single_%d_%d_%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }
}
