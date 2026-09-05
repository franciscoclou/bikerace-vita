package com.topfreegames.bikerace;

import android.content.Context;
import android.graphics.RectF;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: GameSceneDirector.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ac {
    private static final Map<c, com.topfreegames.engine.a.b> A;
    private static final com.topfreegames.engine.a.b B;
    private static final long[] C;
    private static final com.topfreegames.engine.a.b D;
    private static final long[] E;
    private static final com.topfreegames.engine.b.c F;
    private static final com.topfreegames.engine.b.c G;
    private static final com.topfreegames.engine.b.c H;
    private static final com.topfreegames.engine.b.c I;
    private static final com.topfreegames.engine.b.c J;
    private static final com.topfreegames.engine.b.c K;
    private static final com.topfreegames.engine.b.c L;
    private static /* synthetic */ int[] T;
    private static final Map<c, Float> z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.engine.b.d f826a;
    private ah b;
    private e c = new e();
    private int d = -1;
    private c e = null;
    private c f = null;
    private com.topfreegames.bikerace.h.a g = null;
    private boolean h = false;
    private com.topfreegames.engine.d.e i = null;
    private com.topfreegames.engine.d.e j = null;
    private com.topfreegames.engine.d.e k = null;
    private com.topfreegames.engine.d.e l = null;
    private com.topfreegames.engine.d.e m = null;
    private com.topfreegames.engine.d.e n = null;
    private com.topfreegames.engine.d.e o = null;
    private com.topfreegames.engine.d.e p = null;
    private com.topfreegames.engine.d.e q = null;
    private com.topfreegames.engine.d.e r = null;
    private com.topfreegames.engine.d.e[] s = null;
    private HashMap<c, com.topfreegames.engine.d.i> t = new HashMap<>();
    private com.topfreegames.engine.d.i u = null;
    private com.topfreegames.engine.d.i v = null;
    private com.topfreegames.engine.d.i w = null;
    private com.topfreegames.engine.d.i x = null;
    private com.topfreegames.engine.d.i y = null;
    private com.topfreegames.engine.a.b M = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b N = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b O = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b P = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.d.f[] Q = new com.topfreegames.engine.d.f[2];
    private a R = a.a(c.REGULAR, new com.topfreegames.engine.a.b());
    private com.topfreegames.engine.b.c S = new com.topfreegames.engine.b.c();

    static /* synthetic */ int[] e() {
        int[] iArr = T;
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
            T = iArr;
        }
        return iArr;
    }

    static {
        HashMap map = new HashMap();
        for (c cVar : c.valuesCustom()) {
            map.put(cVar, Float.valueOf(0.53866667f));
        }
        map.put(c.SUPER, Float.valueOf(0.61333334f));
        map.put(c.BEAT, Float.valueOf(0.7066667f));
        map.put(c.SPAM, Float.valueOf(0.61333334f));
        map.put(c.ULTRA, Float.valueOf(0.71466666f));
        map.put(c.RETRO, Float.valueOf(0.54933333f));
        map.put(c.BRONZE, Float.valueOf(0.54933333f));
        map.put(c.KIDS, Float.valueOf(0.50666666f));
        map.put(c.ARMY, Float.valueOf(0.7066667f));
        map.put(c.SILVER, Float.valueOf(0.54933333f));
        map.put(c.GOLD, Float.valueOf(0.54933333f));
        map.put(c.HALLOWEEN, Float.valueOf(0.55466664f));
        map.put(c.THANKSGIVING, Float.valueOf(0.5733333f));
        map.put(c.SANTA, Float.valueOf(0.47733334f));
        map.put(c.EASTER, Float.valueOf(0.588f));
        map.put(c.WORLDCUP_AUSTRALIA, Float.valueOf(0.5226667f));
        map.put(c.WORLDCUP_BRAZIL, Float.valueOf(0.52533334f));
        map.put(c.WORLDCUP_USA, Float.valueOf(0.528f));
        map.put(c.WORLDCUP_SPAIN, Float.valueOf(0.5093333f));
        map.put(c.WORLDCUP_ENGLAND, Float.valueOf(0.52533334f));
        map.put(c.WORLDCUP_NETHERLANDS, Float.valueOf(0.5413333f));
        map.put(c.WORLDCUP_FRANCE, Float.valueOf(0.52f));
        map.put(c.WORLDCUP_GERMANY, Float.valueOf(0.5413333f));
        map.put(c.WORLDCUP_JAPAN, Float.valueOf(0.5413333f));
        map.put(c.WORLDCUP_ARGENTINA, Float.valueOf(0.52533334f));
        map.put(c.WORLDCUP_BELGIUM, Float.valueOf(0.5466667f));
        map.put(c.WORLDCUP_MEXICO, Float.valueOf(0.504f));
        map.put(c.WORLDCUP_ITALY, Float.valueOf(0.5226667f));
        z = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        com.topfreegames.engine.a.b bVar = new com.topfreegames.engine.a.b(0.0f, -0.01f);
        for (c cVar2 : c.valuesCustom()) {
            map2.put(cVar2, bVar);
        }
        map2.put(c.REGULAR, new com.topfreegames.engine.a.b(0.0f, -0.01f));
        map2.put(c.GIRL, new com.topfreegames.engine.a.b(0.005f, -0.01f));
        map2.put(c.RETRO, new com.topfreegames.engine.a.b(-0.015f, -0.01f));
        map2.put(c.SUPER, new com.topfreegames.engine.a.b(-0.04f, 0.015f));
        map2.put(c.BRONZE, new com.topfreegames.engine.a.b(-0.02f, 0.0f));
        map2.put(c.SILVER, new com.topfreegames.engine.a.b(-0.02f, 0.0f));
        map2.put(c.GOLD, new com.topfreegames.engine.a.b(-0.02f, 0.0f));
        map2.put(c.NINJA, new com.topfreegames.engine.a.b(0.0f, -0.04f));
        map2.put(c.ACROBATIC, new com.topfreegames.engine.a.b(-0.0235f, 0.0f));
        map2.put(c.COP, new com.topfreegames.engine.a.b(-0.036f, 0.0f));
        map2.put(c.BEAT, new com.topfreegames.engine.a.b(-0.053f, -0.065f));
        map2.put(c.SPAM, new com.topfreegames.engine.a.b(0.0f, -0.015f));
        map2.put(c.ULTRA, new com.topfreegames.engine.a.b(0.01f, -0.033f));
        map2.put(c.ZOMBIE, new com.topfreegames.engine.a.b(0.0f, 0.016f));
        map2.put(c.KIDS, new com.topfreegames.engine.a.b(0.02f, -0.01f));
        map2.put(c.ARMY, new com.topfreegames.engine.a.b(-0.035f, -0.07f));
        map2.put(c.HALLOWEEN, new com.topfreegames.engine.a.b(-0.01f, 0.011f));
        map2.put(c.THANKSGIVING, new com.topfreegames.engine.a.b(-0.043f, 0.021f));
        map2.put(c.SANTA, new com.topfreegames.engine.a.b(-0.044f, 0.0f));
        map2.put(c.EASTER, new com.topfreegames.engine.a.b(-0.005f, 0.003f));
        map2.put(c.WORLDCUP_AUSTRALIA, new com.topfreegames.engine.a.b(-0.025f, 0.01f));
        map2.put(c.WORLDCUP_BRAZIL, new com.topfreegames.engine.a.b(-0.012f, 0.01f));
        map2.put(c.WORLDCUP_USA, new com.topfreegames.engine.a.b(0.0f, -0.01f));
        map2.put(c.WORLDCUP_FRANCE, new com.topfreegames.engine.a.b(0.0f, 0.01f));
        map2.put(c.WORLDCUP_GERMANY, new com.topfreegames.engine.a.b(-0.015f, -0.01f));
        map2.put(c.WORLDCUP_JAPAN, new com.topfreegames.engine.a.b(-0.015f, -0.01f));
        map2.put(c.WORLDCUP_NETHERLANDS, new com.topfreegames.engine.a.b(-0.01f, -0.03f));
        map2.put(c.WORLDCUP_SPAIN, new com.topfreegames.engine.a.b(-0.012f, 0.007f));
        map2.put(c.WORLDCUP_ENGLAND, new com.topfreegames.engine.a.b(-0.03f, 0.01f));
        map2.put(c.WORLDCUP_ARGENTINA, new com.topfreegames.engine.a.b(-0.01f, 0.006f));
        map2.put(c.WORLDCUP_ITALY, new com.topfreegames.engine.a.b(-0.013f, -0.01f));
        map2.put(c.WORLDCUP_MEXICO, new com.topfreegames.engine.a.b(-0.03f, 0.02f));
        map2.put(c.WORLDCUP_BELGIUM, new com.topfreegames.engine.a.b(-0.03f, -0.01f));
        A = Collections.unmodifiableMap(map2);
        B = new com.topfreegames.engine.a.b(0.018f, 0.6f);
        C = new long[]{55, 55, 55, 55, 55, 55, 55, 55};
        D = new com.topfreegames.engine.a.b(0.1f, 0.1f);
        E = new long[]{55, 55, 55, 55, 55, 55, 55, 55, 55};
        F = new com.topfreegames.engine.b.c(1.0f, 1.0f, 1.0f, 1.0f);
        G = new com.topfreegames.engine.b.c(0.3f, 1.0f, 0.3f, 0.35f);
        H = new com.topfreegames.engine.b.c(0.3f, 1.0f, 0.3f, 0.35f);
        I = new com.topfreegames.engine.b.c(1.0f, 0.3f, 0.3f, 0.35f);
        J = new com.topfreegames.engine.b.c(1.0f, 1.0f, 1.0f, 0.65f);
        K = new com.topfreegames.engine.b.c(1.0f, 1.0f, 1.0f, 0.45f);
        L = new com.topfreegames.engine.b.c(0.65f, 0.65f, 0.65f, 0.35f);
    }

    public ac(Context context, com.topfreegames.engine.b.d dVar) {
        this.f826a = null;
        if (dVar == null) {
            throw new IllegalArgumentException("Video Driver cannot be null!");
        }
        this.f826a = dVar;
        this.b = new ah(context, dVar);
    }

    public void a(com.topfreegames.bikerace.h.a aVar, a aVar2, a aVar3) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        if (aVar2 == null) {
            throw new IllegalArgumentException("Player Bike cannot be null!");
        }
        a(aVar, aVar2, F, aVar3, K, true, -1, -1);
    }

    public void b(com.topfreegames.bikerace.h.a aVar, a aVar2, a aVar3) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        if (aVar2 == null) {
            throw new IllegalArgumentException("Player Bike cannot be null!");
        }
        if (aVar3 == null) {
            throw new IllegalArgumentException("Opponent Bike cannot be null!");
        }
        a(aVar, aVar2, H, aVar3, I, false, -1, -1);
    }

    public void a(com.topfreegames.bikerace.h.a aVar, a aVar2, a aVar3, int i, int i2) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        if (aVar2 == null) {
            throw new IllegalArgumentException("Player Bike cannot be null!");
        }
        if (i < 0) {
            throw new IllegalArgumentException("NumLifes cannot be negative!");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("MaxLifes cannot be negative!");
        }
        a(aVar, aVar2, F, aVar3, G, false, i, i2);
    }

    public void b(com.topfreegames.bikerace.h.a aVar, a aVar2, a aVar3, int i, int i2) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        if (aVar2 == null) {
            throw new IllegalArgumentException("Player Bike cannot be null!");
        }
        if (aVar3 == null) {
            throw new IllegalArgumentException("Opponent Bike cannot be null!");
        }
        if (i < 0) {
            throw new IllegalArgumentException("NumLifes cannot be negative!");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("MaxLifes cannot be negative!");
        }
        a(aVar, aVar2, F, aVar3, I, false, i, i2);
    }

    private void a(com.topfreegames.bikerace.h.a aVar, a aVar2, com.topfreegames.engine.b.c cVar, a aVar3, com.topfreegames.engine.b.c cVar2, boolean z2, int i, int i2) {
        this.M.a(1.0f / this.f826a.d(), 1.0f);
        this.i.c(this.M);
        this.M.a(1.0f / this.c.c(), 1.0f / this.c.c());
        this.j.c(this.M);
        this.P.a(this.c.b());
        this.j.b(this.P);
        this.f826a.a(false, false);
        this.i.c();
        b(aVar);
        this.j.c();
        a(aVar, z2);
        if (aVar3 != null) {
            this.S.a(cVar2);
            if (this.S != F && ((aVar3.m == c.BRONZE || aVar3.m == c.SILVER || aVar3.m == c.GOLD) && i >= 0)) {
                this.S = J;
            }
            if (z2) {
                float fA = i < 0 ? a(aVar3, aVar2) : 0.0f;
                this.S.d -= fA;
                this.S.f1555a *= this.S.d;
                this.S.b *= this.S.d;
                this.S.c *= this.S.d;
            }
            a(aVar3, this.S);
        }
        if (cVar != F && (aVar2.m == c.BRONZE || aVar2.m == c.SILVER || (aVar2.m == c.GOLD && i >= 0))) {
            cVar = J;
        }
        a(aVar2, cVar);
        a(aVar2, i, i2);
        this.f826a.e();
    }

    public void a(com.topfreegames.bikerace.h.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException();
        }
        if (this.g != null && this.g.equals(aVar)) {
            a();
            return;
        }
        i();
        com.topfreegames.bikerace.h.w wVar = new com.topfreegames.bikerace.h.w(this.b.m);
        this.Q = new com.topfreegames.engine.d.f[2];
        this.s = new com.topfreegames.engine.d.h[2];
        this.Q[0] = wVar.a(aVar);
        this.s[0] = new com.topfreegames.engine.d.h(this.f826a, this.j, this.Q[0]);
        wVar.a(0.1f);
        this.Q[1] = wVar.a(aVar);
        this.s[1] = new com.topfreegames.engine.d.h(this.f826a, this.j, this.Q[1]);
        RectF rectF = new RectF(aVar.a().a());
        this.h = false;
        this.c = new e();
        this.c.a(rectF, this.f826a.d());
        this.g = aVar;
    }

    public void a(Context context, int i, c... cVarArr) {
        boolean z2;
        if (i != this.d) {
            g();
            this.b.a(i);
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z3 = z2;
        for (int i2 = 0; i2 < cVarArr.length; i2++) {
            if (cVarArr[i2] != this.e && cVarArr[i2] != this.f) {
                this.b.a(cVarArr[i2]);
                z3 = true;
            } else if (cVarArr[i2] != this.e || cVarArr[i2] != this.f) {
                z3 = true;
            }
        }
        this.d = i;
        this.e = cVarArr[0];
        this.f = cVarArr[1];
        if (z3) {
            f();
        }
    }

    private void f() {
        com.topfreegames.engine.a.b bVarA = this.M.a(0.0f, 0.0f);
        com.topfreegames.engine.a.b bVar = this.N;
        com.topfreegames.engine.a.b bVar2 = this.O;
        this.h = false;
        this.i = new com.topfreegames.engine.d.j(this.f826a, null);
        this.j = new com.topfreegames.engine.d.c(this.f826a, this.i);
        this.k = new com.topfreegames.engine.d.j(this.f826a, this.i);
        this.k.c(new com.topfreegames.engine.a.b(0.3f, 0.3f));
        com.topfreegames.engine.b.h hVar = this.b.c;
        if (hVar != null) {
            this.l = new com.topfreegames.engine.d.i(this.f826a, this.i, new com.topfreegames.engine.a.b(0.0f, 0.0f), new com.topfreegames.engine.a.b(1.0f, 1.0f), 0.0f, hVar);
        }
        if (this.l == null) {
            throw new NullPointerException("Null background back");
        }
        com.topfreegames.engine.b.h hVar2 = this.b.d;
        if (hVar2 != null) {
            this.m = new com.topfreegames.engine.d.d(this.f826a, this.i, bVarA, new com.topfreegames.engine.a.b(1.0f, 1.0f), 0.0f, hVar2, 2.8789062f);
        }
        if (this.m == null) {
            throw new NullPointerException("Null background middle");
        }
        com.topfreegames.engine.b.h hVar3 = this.b.e;
        if (hVar3 != null) {
            this.n = new com.topfreegames.engine.d.d(this.f826a, this.i, bVarA, new com.topfreegames.engine.a.b(1.0f, 1.0f), 0.0f, hVar3, 2.8789062f);
        }
        if (this.n == null) {
            throw new NullPointerException("Null background front");
        }
        com.topfreegames.engine.b.h hVar4 = this.b.l;
        if (hVar4 != null) {
            bVar.a(0.18f, (0.18f / hVar4.a()) * hVar4.b());
            this.r = new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar4);
        }
        if (this.r == null) {
            throw new NullPointerException("Null pole");
        }
        com.topfreegames.engine.b.h[] hVarArr = this.b.g;
        if (hVar4 != null) {
            this.p = new com.topfreegames.engine.d.a(this.f826a, this.j, bVarA, D, 0.0f, hVarArr, E);
            this.p.a(false);
            this.p.c(new com.topfreegames.engine.a.b(4.0f, 4.0f));
        }
        if (this.p == null) {
            throw new NullPointerException("Null explosion");
        }
        com.topfreegames.engine.b.h hVar5 = this.b.g[4];
        if (hVar5 != null) {
            this.q = new com.topfreegames.engine.d.i(this.f826a, this.k, bVarA, new com.topfreegames.engine.a.b(0.7f, 0.7f), 0.0f, hVar5);
        }
        if (this.q == null) {
            throw new NullPointerException("Null explosion overlay");
        }
        com.topfreegames.engine.b.h[] hVarArr2 = this.b.f;
        if (hVarArr2 != null) {
            bVar.a(0.6666667f, hVarArr2[0].b() * (0.6666667f / hVarArr2[0].a()));
            bVar2.a(bVar.f1553a / 2.0f, (-bVar.b) / 2.0f).b(B);
            this.o = new com.topfreegames.engine.d.a(this.f826a, this.r, bVar2, bVar, 0.0f, this.b.f, C);
        }
        if (this.o == null) {
            throw new NullPointerException("Null flag");
        }
        for (c cVar : c.valuesCustom()) {
            com.topfreegames.engine.b.h hVar6 = this.b.f1120a.get(cVar);
            if (hVar6 != null) {
                Float f = z.get(cVar);
                bVar.a(f.floatValue(), (f.floatValue() / hVar6.a()) * hVar6.b());
                this.t.put(cVar, new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar6));
            }
        }
        com.topfreegames.engine.b.h hVar7 = this.b.h;
        if (hVar7 != null) {
            bVar.a(0.2f, (0.2f / hVar7.a()) * hVar7.b());
            this.u = new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar7);
        }
        com.topfreegames.engine.b.h hVar8 = this.b.k;
        if (hVar8 != null) {
            bVar.a(0.2f, (0.2f / hVar8.a()) * hVar8.b());
            this.v = new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar8);
        }
        com.topfreegames.engine.b.h hVar9 = this.b.i;
        if (hVar9 != null) {
            bVar.a(0.2f, (0.2f / hVar9.a()) * hVar9.b());
            this.w = new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar9);
        }
        com.topfreegames.engine.b.h hVar10 = this.b.j;
        if (hVar10 != null) {
            bVar.a(0.2f, (0.2f / hVar10.a()) * hVar10.b());
            this.x = new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar10);
        }
        com.topfreegames.engine.b.h hVar11 = this.b.n;
        if (hVar11 != null) {
            bVar.a(0.2f, (0.2f / hVar11.a()) * hVar11.b());
            this.y = new com.topfreegames.engine.d.i(this.f826a, this.j, bVarA, bVar, 0.0f, hVar11);
        }
    }

    public void a() {
        RectF rectFA = this.c.a();
        this.h = false;
        this.c = new e();
        this.c.a(rectFA, this.f826a.d());
        for (com.topfreegames.engine.d.e eVar : this.s) {
            eVar.b(this.j);
        }
    }

    private void g() {
        if (this.l != null) {
            this.l.b();
            this.l = null;
        }
        if (this.m != null) {
            this.m.b();
            this.m = null;
        }
        if (this.n != null) {
            this.n.b();
            this.n = null;
        }
        if (this.r != null) {
            this.r.b();
            this.r = null;
        }
        if (this.p != null) {
            this.p.b();
            this.p = null;
        }
        if (this.o != null) {
            this.o.b();
            this.o = null;
        }
        if (this.u != null) {
            this.u.b();
            this.u = null;
        }
        if (this.v != null) {
            this.v.b();
            this.v = null;
        }
        if (this.w != null) {
            this.w.b();
            this.w = null;
        }
        if (this.x != null) {
            this.x.b();
            this.x = null;
        }
        this.b.a();
        System.gc();
    }

    private void h() {
        if (this.t != null) {
            for (c cVar : c.valuesCustom()) {
                com.topfreegames.engine.d.i iVar = this.t.get(cVar);
                if (iVar != null) {
                    iVar.b();
                    this.t.put(cVar, null);
                }
            }
        }
        System.gc();
    }

    private void i() {
        if (this.Q != null) {
            this.Q = null;
        }
        if (this.s != null) {
            for (int i = 0; i < this.s.length; i++) {
                this.s[i].b();
            }
            this.s = null;
        }
    }

    public void b() {
        g();
        h();
        i();
        if (this.b != null) {
            this.b.c();
            this.b = null;
        }
        if (this.j != null) {
            this.j.b();
            this.j = null;
        }
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        this.g = null;
        this.e = null;
        this.f = null;
        this.f826a.b();
    }

    public void a(com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, float f, float f2, float f3, float f4) {
        if (bVar == null) {
            throw new IllegalArgumentException("RefPos cannot be null!");
        }
        if (bVar2 == null) {
            throw new IllegalArgumentException("RefVel cannot be null!");
        }
        this.c.a(bVar, bVar2, f, f2, f3, f4, this.h, this.f826a.d());
    }

    public void c() {
        this.h = !this.h;
    }

    public void a(boolean z2) {
        this.h = z2;
    }

    public boolean d() {
        return this.h;
    }

    private void b(com.topfreegames.bikerace.h.a aVar) {
        this.M.a(this.f826a.d() * 2.0f, 2.0f);
        this.l.a(this.M);
        this.l.c();
        RectF rectFA = aVar.a().a();
        RectF rectFA2 = this.c.a();
        a(16.0f, this.m, rectFA, rectFA2);
        a(6.0f, this.n, rectFA, rectFA2);
    }

    private com.topfreegames.engine.d.i a(a aVar) {
        return this.t.get(aVar.m);
    }

    private com.topfreegames.engine.d.i b(a aVar) {
        switch (e()[aVar.g().ordinal()]) {
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return this.v;
            case 16:
            case 17:
            case 19:
            default:
                return this.u;
            case 18:
                return this.w;
            case 20:
                return this.x;
        }
    }

    private void a(a aVar, com.topfreegames.engine.b.c cVar) {
        a(aVar, cVar, false);
    }

    private void a(a aVar, com.topfreegames.engine.b.c cVar, boolean z2) {
        com.topfreegames.engine.d.i iVarA = a(aVar);
        com.topfreegames.engine.d.i iVarB = b(aVar);
        com.topfreegames.engine.a.b bVar = A.get(aVar.g());
        if (z2) {
            iVarB.b(this.k);
            iVarA.b(this.k);
        } else {
            iVarB.b(this.j);
            iVarA.b(this.j);
        }
        iVarB.a(cVar);
        iVarB.b(aVar.b.f1561a);
        iVarB.a(aVar.b.d);
        iVarB.c();
        iVarB.b(aVar.c.f1561a);
        iVarB.a(aVar.c.d);
        iVarB.c();
        iVarB.a(F);
        if (aVar.h()) {
            iVarA.a(false);
            this.p.b(aVar.f812a.f1561a);
            this.p.a(true);
            this.p.c();
            return;
        }
        float fA = aVar.a();
        this.M.a(0.0f, -0.11f).b(bVar).a((fA / 180.0f) * 3.1415927f);
        this.M.b(aVar.f812a.f1561a);
        iVarA.b(this.M);
        iVarA.a(fA);
        iVarA.a(true);
        iVarA.a(cVar);
        iVarA.c();
        iVarA.a(F);
    }

    private void a(float f, com.topfreegames.engine.d.e eVar, RectF rectF, RectF rectF2) {
        float fC = ((this.c.c() + f) - 1.0f) / f;
        this.M.a(1.0f / fC, 1.0f / fC);
        eVar.c(this.M);
        this.M.a(rectF.width(), -1.0f);
        eVar.a(this.M);
        float fC2 = (this.c.c() + this.c.b.bottom) - 0.2f;
        if (this.c.f1170a.b < fC2) {
            fC2 = this.c.f1170a.b;
        }
        this.M.a((-this.P.f1553a) / f, (fC2 / f) + ((-fC) - (this.P.b / f)));
        eVar.b(this.M);
        eVar.c();
    }

    private void a(com.topfreegames.bikerace.h.a aVar, boolean z2) {
        if (this.h) {
            this.s[1].c();
        } else {
            this.s[0].c();
        }
        a(aVar.b(), z2);
        this.M.a(0.0f, (-this.r.d().b) / 2.0f).b(aVar.d());
        this.r.b(this.M);
        this.r.c();
    }

    private void a(com.topfreegames.bikerace.c.a[] aVarArr, boolean z2) {
        if (aVarArr != null && this.c.c() < 2.5f) {
            com.topfreegames.bikerace.c.d dVarA = com.topfreegames.bikerace.c.d.a();
            for (com.topfreegames.bikerace.c.a aVar : aVarArr) {
                if (aVar != null) {
                    if (dVarA.a(aVar, z2)) {
                        this.y.b(aVar.a());
                        this.y.a(aVar.c());
                        this.y.a(F);
                        this.y.c();
                    } else if (dVarA.b(aVar, z2)) {
                        this.y.b(aVar.a());
                        this.y.a(aVar.c());
                        this.y.a(L);
                        this.y.c();
                    }
                }
            }
        }
    }

    private void a(a aVar, int i, int i2) {
        if (i >= 0 && i2 > 0) {
            float fD = this.f826a.d();
            com.topfreegames.engine.a.b bVar = this.O;
            bVar.a((-0.9f) * fD, 0.9f);
            this.k.b(bVar);
            this.k.c();
            bVar.a(0.0f, 0.0f);
            com.topfreegames.engine.a.b bVar2 = this.N;
            bVar2.a(fD * 0.4f, 0.0f);
            if (this.R.m != aVar.m) {
                this.R = a.a(aVar.m, bVar);
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 < i) {
                    this.R.a(bVar.f1553a, bVar.b, 45.0f, 0.0f);
                    a(this.R, F, true);
                } else {
                    this.M.a(0.075f, -0.125f).b(bVar);
                    this.q.b(this.M);
                    this.q.c();
                }
                bVar.b(bVar2);
            }
        }
    }

    private float a(a aVar, a aVar2) {
        this.M.a(aVar.e()).c(aVar2.e());
        float fC = this.M.c();
        if (fC >= 1.0773333f || fC <= 0.0f) {
            return 0.0f;
        }
        float f = fC / 1.0773333f;
        return (f >= 0.5f ? (1.0f - f) / 0.5f : 1.0f) * 0.25f;
    }
}
