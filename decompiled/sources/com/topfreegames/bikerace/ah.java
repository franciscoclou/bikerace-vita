package com.topfreegames.bikerace;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.WindowManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: GraphicsResources.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ah {
    private static final Map<c, Integer> o;
    private static final Map<c, Integer> p;
    private static /* synthetic */ int[] s;
    private static /* synthetic */ int[] t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<c, com.topfreegames.engine.b.h> f1120a = new HashMap<>();
    public com.topfreegames.engine.b.h b = null;
    public com.topfreegames.engine.b.h c = null;
    public com.topfreegames.engine.b.h d = null;
    public com.topfreegames.engine.b.h e = null;
    public com.topfreegames.engine.b.h[] f = null;
    public com.topfreegames.engine.b.h[] g = null;
    public com.topfreegames.engine.b.h h = null;
    public com.topfreegames.engine.b.h i = null;
    public com.topfreegames.engine.b.h j = null;
    public com.topfreegames.engine.b.h k = null;
    public com.topfreegames.engine.b.h l = null;
    public com.topfreegames.engine.b.h m = null;
    public com.topfreegames.engine.b.h n = null;
    private Context q;
    private com.topfreegames.engine.b.d r;

    static /* synthetic */ int[] d() {
        int[] iArr = s;
        if (iArr == null) {
            iArr = new int[c.valuesCustom().length];
            try {
                iArr[c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e35) {
            }
            s = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] e() {
        int[] iArr = t;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.c.c.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.c.c.EASTER_EGG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            t = iArr;
        }
        return iArr;
    }

    static {
        HashMap map = new HashMap();
        map.put(c.ACROBATIC, 2130837773);
        map.put(c.BEAT, 2130837776);
        map.put(c.BRONZE, 2130837768);
        map.put(c.COP, 2130837780);
        map.put(c.GHOST, 2130837779);
        map.put(c.GIRL, 2130837778);
        map.put(c.GOLD, 2130837770);
        map.put(c.KIDS, 2130837771);
        map.put(c.NINJA, 2130837781);
        map.put(c.REGULAR, 2130837766);
        map.put(c.RETRO, 2130837777);
        map.put(c.SILVER, 2130837769);
        map.put(c.SPAM, 2130837767);
        map.put(c.SUPER, 2130837775);
        map.put(c.ULTRA, 2130837772);
        map.put(c.ZOMBIE, 2130837774);
        map.put(c.ARMY, 2130837784);
        map.put(c.HALLOWEEN, 2130837785);
        map.put(c.THANKSGIVING, 2130837786);
        map.put(c.SANTA, 2130837787);
        map.put(c.EASTER, 2130837788);
        map.put(c.WORLDCUP_AUSTRALIA, 2130837790);
        map.put(c.WORLDCUP_BRAZIL, 2130837791);
        map.put(c.WORLDCUP_USA, 2130837792);
        map.put(c.WORLDCUP_FRANCE, 2130837793);
        map.put(c.WORLDCUP_GERMANY, 2130837789);
        map.put(c.WORLDCUP_JAPAN, 2130837796);
        map.put(c.WORLDCUP_NETHERLANDS, 2130837794);
        map.put(c.WORLDCUP_SPAIN, 2130837797);
        map.put(c.WORLDCUP_ENGLAND, 2130837795);
        map.put(c.WORLDCUP_ARGENTINA, 2130837801);
        map.put(c.WORLDCUP_BELGIUM, 2130837798);
        map.put(c.WORLDCUP_ITALY, 2130837800);
        map.put(c.WORLDCUP_MEXICO, 2130837799);
        map.put(c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER, 2130837766);
        if (ap.d()) {
            for (c cVar : c.valuesCustom()) {
                if (!map.containsKey(cVar)) {
                    throw new Error("Missing resource " + cVar.toString());
                }
            }
        }
        o = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put(c.SANTA, 2130837865);
        p = Collections.unmodifiableMap(map2);
    }

    public ah(Context context, com.topfreegames.engine.b.d dVar) {
        this.q = context;
        this.r = dVar;
    }

    public void a(int i) {
        Bitmap bitmapA = a(this.q, i);
        this.b = this.r.a(bitmapA);
        a(bitmapA);
        a(this.b);
        b(this.b);
        c(this.b);
    }

    public void a(c... cVarArr) {
        for (c cVar : cVarArr) {
            if (cVar != null) {
                a(cVar);
                c(cVar);
            }
        }
    }

    public void a() {
        f();
        g();
        h();
    }

    public void b() {
        for (c cVar : c.valuesCustom()) {
            b(cVar);
            d(cVar);
        }
    }

    public void c() {
        a();
        b();
    }

    private static int b(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    private void a(com.topfreegames.engine.b.h hVar) {
        this.c = this.r.a(hVar, bk.c);
        this.d = this.r.a(hVar, bk.b);
        this.e = this.r.a(hVar, bk.f1158a);
        this.l = this.r.a(hVar, bk.l);
        this.m = this.r.a(hVar, bk.A);
        this.f = new com.topfreegames.engine.b.h[8];
        this.f[7] = this.r.a(hVar, bk.k);
        this.f[6] = this.r.a(hVar, bk.j);
        this.f[5] = this.r.a(hVar, bk.i);
        this.f[4] = this.r.a(hVar, bk.h);
        this.f[3] = this.r.a(hVar, bk.g);
        this.f[2] = this.r.a(hVar, bk.f);
        this.f[1] = this.r.a(hVar, bk.e);
        this.f[0] = this.r.a(hVar, bk.d);
        this.g = new com.topfreegames.engine.b.h[9];
        this.g[0] = this.r.a(hVar, bk.m);
        this.g[1] = this.r.a(hVar, bk.n);
        this.g[2] = this.r.a(hVar, bk.o);
        this.g[3] = this.r.a(hVar, bk.p);
        this.g[4] = this.r.a(hVar, bk.q);
        this.g[5] = this.r.a(hVar, bk.r);
        this.g[6] = this.r.a(hVar, bk.s);
        this.g[7] = this.r.a(hVar, bk.t);
        this.g[8] = this.r.a(hVar, bk.u);
    }

    private void f() {
        if (this.c != null) {
            this.r.a(this.c);
            this.c = null;
        }
        if (this.d != null) {
            this.r.a(this.d);
            this.d = null;
        }
        if (this.e != null) {
            this.r.a(this.e);
            this.d = null;
        }
        if (this.l != null) {
            this.r.a(this.l);
            this.l = null;
        }
        if (this.m != null) {
            this.r.a(this.m);
            this.m = null;
        }
        if (this.f != null) {
            for (int i = 0; i < this.f.length; i++) {
                if (this.f[i] != null) {
                    this.r.a(this.f[i]);
                }
            }
            this.f = null;
        }
        if (this.g != null) {
            for (int i2 = 0; i2 < this.g.length; i2++) {
                if (this.g[i2] != null) {
                    this.r.a(this.g[i2]);
                }
            }
            this.g = null;
        }
    }

    private void b(com.topfreegames.engine.b.h hVar) {
        this.i = this.r.a(hVar, bk.w);
        this.k = this.r.a(hVar, bk.x);
        this.h = this.r.a(hVar, bk.v);
    }

    private void g() {
        if (this.i != null) {
            this.r.a(this.i);
            this.i = null;
        }
        if (this.k != null) {
            this.r.a(this.k);
            this.k = null;
        }
        if (this.h != null) {
            this.r.a(this.h);
            this.h = null;
        }
    }

    private void a(c cVar) {
        if (cVar != null) {
            Bitmap bitmapA = a(this.q, o.get(cVar).intValue());
            com.topfreegames.engine.b.h hVarA = this.r.a(bitmapA);
            a(bitmapA);
            this.f1120a.put(cVar, this.r.a(hVarA, bk.z.get(cVar)));
        }
    }

    private void b(c cVar) {
        if (cVar != null) {
            com.topfreegames.engine.b.h hVar = this.f1120a.get(cVar);
            if (hVar != null) {
                this.r.a(hVar);
            }
            this.f1120a.put(cVar, null);
        }
    }

    private void c(c cVar) {
        if (cVar != null) {
            switch (d()[cVar.ordinal()]) {
                case 20:
                    Bitmap bitmapA = a(this.q, p.get(cVar).intValue());
                    com.topfreegames.engine.b.h hVarA = this.r.a(bitmapA);
                    a(bitmapA);
                    this.j = this.r.a(hVarA, bk.y);
                    break;
            }
        }
    }

    private void d(c cVar) {
        switch (d()[cVar.ordinal()]) {
            case 20:
                if (this.j != null) {
                    this.r.a(this.j);
                    this.j = null;
                }
                break;
        }
    }

    private void c(com.topfreegames.engine.b.h hVar) {
        for (com.topfreegames.bikerace.c.c cVar : com.topfreegames.bikerace.c.c.valuesCustom()) {
            switch (e()[cVar.ordinal()]) {
                case 1:
                    this.n = this.r.a(hVar, bk.B);
                    break;
            }
        }
    }

    private void h() {
        if (this.n != null) {
            this.r.a(this.n);
            this.n = null;
        }
    }

    private static Bitmap a(Context context, int i) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        return com.topfreegames.engine.b.a.a(context.getResources(), i, b((int) (defaultDisplay.getWidth() / bl.f1159a.width())), b(defaultDisplay.getHeight()));
    }

    private static void a(Bitmap bitmap) {
        bitmap.recycle();
        System.gc();
    }
}
