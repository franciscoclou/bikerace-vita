package com.topfreegames.bikerace;

import android.annotation.SuppressLint;
import android.graphics.RectF;
import android.util.FloatMath;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;

/* JADX INFO: compiled from: Bike.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@SuppressLint({"FloatMath"})
public class a {
    private static /* synthetic */ int[] A;
    private static /* synthetic */ int[] z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.topfreegames.engine.c.a f812a;
    public com.topfreegames.engine.c.a b;
    public com.topfreegames.engine.c.a c;
    protected com.topfreegames.engine.c.b d;
    protected float e;
    protected float f;
    protected float g;
    protected float h;
    protected float i;
    protected boolean j;
    protected boolean k;
    protected boolean l;
    protected c m;
    protected b n;
    protected com.topfreegames.engine.c.c o;
    protected com.topfreegames.engine.c.c p;
    protected com.topfreegames.engine.c.c q;
    protected com.topfreegames.engine.c.c r;
    protected com.topfreegames.engine.a.b s;
    protected com.topfreegames.engine.a.b t;
    protected com.topfreegames.engine.a.b u;
    protected com.topfreegames.engine.a.b v;
    protected RectF w;
    protected RectF x;
    protected com.topfreegames.engine.c.a y;

    static /* synthetic */ int[] i() {
        int[] iArr = z;
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
            z = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] j() {
        int[] iArr = A;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.ACCELERATING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.BRAKING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.CRASHED.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[b.IDLE.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            A = iArr;
        }
        return iArr;
    }

    protected a(c cVar, float f, float f2, float f3, float f4, float f5, com.topfreegames.engine.a.b bVar, float f6, float f7, boolean z2, boolean z3) {
        this.f812a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.j = false;
        this.k = true;
        this.l = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = new com.topfreegames.engine.a.b();
        this.t = new com.topfreegames.engine.a.b();
        this.u = new com.topfreegames.engine.a.b();
        this.v = new com.topfreegames.engine.a.b();
        this.w = new RectF();
        this.x = new RectF();
        this.y = new com.topfreegames.engine.c.a(this.s, this.s, 1.0E9f, 0.0f, 0.0f);
        com.topfreegames.engine.a.b bVar2 = new com.topfreegames.engine.a.b();
        this.s.a(0.0f, 0.42f).b(bVar);
        this.f812a = new com.topfreegames.engine.c.a(this.s, bVar2, 1.0f, 0.0f, 0.0f);
        this.s.a(f6, 0.1f).b(bVar);
        this.b = new com.topfreegames.engine.c.a(this.s, bVar2, 1.0f, 0.0f, 0.0f);
        this.s.a(f7, 0.1f).b(bVar);
        this.c = new com.topfreegames.engine.c.a(this.s, bVar2, 1.0f, 0.0f, 0.0f);
        this.d = new com.topfreegames.engine.c.b();
        this.d.a(this.f812a);
        this.d.a(this.b);
        this.d.a(this.c);
        this.h = f4;
        this.e = f;
        this.f = f2;
        this.g = f3;
        this.i = f5;
        this.j = z2;
        this.k = z3;
        this.m = cVar;
        float fSqrt = FloatMath.sqrt((float) (Math.pow(0.3199999928474426d, 2.0d) + Math.pow(f6, 2.0d)));
        float fSqrt2 = FloatMath.sqrt((float) (Math.pow(0.3199999928474426d, 2.0d) + Math.pow(f7, 2.0d)));
        this.o = new com.topfreegames.engine.c.c(1600.0f, 40.0f, fSqrt);
        this.p = new com.topfreegames.engine.c.c(1600.0f, 40.0f, fSqrt2);
        this.q = new com.topfreegames.engine.c.c(1600.0f, 40.0f, f6 - f7);
        this.r = new com.topfreegames.engine.c.c(4900.0f, 112.0f, 0.1f);
    }

    protected a(c cVar, float f, float f2, float f3, float f4, float f5, com.topfreegames.engine.a.b bVar, float f6, float f7, boolean z2, boolean z3, boolean z4) {
        this(cVar, f, f2, f3, f4, f5, bVar, f6, f7, z2, z3);
        this.l = z4;
    }

    public static a a(c cVar, com.topfreegames.engine.a.b bVar) {
        if (cVar == null) {
            throw new IllegalArgumentException("Type cannot be null!");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        switch (i()[cVar.ordinal()]) {
            case 1:
                return c(bVar);
            case 2:
                return d(bVar);
            case 3:
                return b(bVar);
            case 4:
                return a(bVar);
            case 5:
                return e(bVar);
            case 6:
                return f(bVar);
            case 7:
                return g(bVar);
            case 8:
                return h(bVar);
            case 9:
                return i(bVar);
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return j(bVar);
            case XMLStreamConstants.DTD /* 11 */:
                return k(bVar);
            case XMLStreamConstants.CDATA /* 12 */:
                return l(bVar);
            case XMLStreamConstants.NAMESPACE /* 13 */:
                return m(bVar);
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                return n(bVar);
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return o(bVar);
            case 16:
                return p(bVar);
            case 17:
                return q(bVar);
            case 18:
                return r(bVar);
            case 19:
                return s(bVar);
            case 20:
                return t(bVar);
            case 21:
                return u(bVar);
            case 22:
                return A(bVar);
            case 23:
                return y(bVar);
            case 24:
                return x(bVar);
            case 25:
                return v(bVar);
            case 26:
            case 27:
            case 28:
            case 30:
            case 31:
            case 32:
            case 33:
            case 35:
                return a(bVar, cVar);
            case 29:
                return w(bVar);
            case 34:
                return z(bVar);
            default:
                return c(bVar);
        }
    }

    public static a a(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.GHOST, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, true, false);
    }

    public static a b(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.KIDS, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, true, true);
    }

    public static a c(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.REGULAR, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a d(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.SUPER, 132.0f, 32.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, true, true);
    }

    public static a e(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.NINJA, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a f(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.COP, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a g(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.RETRO, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a h(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.BRONZE, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a i(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.SILVER, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a j(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.GOLD, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a k(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.GIRL, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a l(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.ACROBATIC, 44.0f, 16.0f, 12.0f, 1.5f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a m(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.BEAT, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.25f, -0.285f, false, false);
    }

    public static a n(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.SPAM, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a o(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.ULTRA, 154.0f, 40.0f, 24.0f, 2.0f, 2000.0f, bVar, 0.25f, -0.285f, true, false);
    }

    public static a p(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.ZOMBIE, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, true, true);
    }

    public static a q(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.ARMY, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.25f, -0.285f, false, false);
    }

    public static a r(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.HALLOWEEN, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a s(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.THANKSGIVING, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a t(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.SANTA, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false, true);
    }

    public static a u(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.EASTER, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a a(com.topfreegames.engine.a.b bVar, c cVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(cVar, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a v(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.WORLDCUP_AUSTRALIA, 52.800003f, 17.6f, 13.200001f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a w(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.WORLDCUP_BRAZIL, 70.4f, 20.8f, 15.599999f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a x(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.WORLDCUP_ENGLAND, 96.8f, 25.6f, 19.2f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a y(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.WORLDCUP_USA, 132.0f, 32.0f, 24.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a z(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        return new a(c.WORLDCUP_ITALY, 61.6f, 19.2f, 14.400001f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public static a A(com.topfreegames.engine.a.b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Position cannot be null!");
        }
        return new a(c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER, 44.0f, 16.0f, 12.0f, 1.0f, 2000.0f, bVar, 0.17f, -0.2f, false, false);
    }

    public void a(com.topfreegames.engine.a.b bVar, float f) {
        if (bVar == null) {
            throw new IllegalArgumentException("Gravity cannot be null!");
        }
        this.d.a(bVar, f);
    }

    public boolean a(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("Board cannot be null!");
        }
        this.x.left = Math.min(this.f812a.f1561a.f1553a, Math.min(this.c.f1561a.f1553a, this.b.f1561a.f1553a)) - 0.38f;
        this.x.right = Math.max(this.f812a.f1561a.f1553a, Math.max(this.c.f1561a.f1553a, this.b.f1561a.f1553a)) + 0.38f;
        this.x.top = Math.min(this.f812a.f1561a.b, Math.min(this.c.f1561a.b, this.b.f1561a.b)) - 0.38f;
        this.x.bottom = Math.max(this.f812a.f1561a.b, Math.max(this.c.f1561a.b, this.b.f1561a.b)) + 0.38f;
        if (dVar.f1168a.f1553a < dVar.b.f1553a) {
            this.w.left = dVar.f1168a.f1553a - 0.38f;
            this.w.right = dVar.b.f1553a + 0.38f;
        } else {
            this.w.left = dVar.b.f1553a - 0.38f;
            this.w.right = dVar.f1168a.f1553a + 0.38f;
        }
        if (dVar.f1168a.b < dVar.b.b) {
            this.w.top = dVar.f1168a.b - 0.38f;
            this.w.bottom = dVar.b.b + 0.38f;
        } else {
            this.w.top = dVar.b.b - 0.38f;
            this.w.bottom = dVar.f1168a.b + 0.38f;
        }
        return this.x.intersect(this.w);
    }

    public boolean b(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("Board cannot be null!");
        }
        if (this.n == b.CRASHED || this.j || !a(this.f812a, dVar, 0.38f)) {
            return false;
        }
        if (com.topfreegames.engine.a.a.a(dVar.f1168a, dVar.b, this.f812a.f1561a, this.b.f1561a) || com.topfreegames.engine.a.a.a(this.f812a.f1561a, this.c.f1561a, dVar.f1168a, dVar.b)) {
            return true;
        }
        this.s.a(com.topfreegames.engine.a.a.b(this.f812a.f1561a, dVar.f1168a, dVar.b)).c(this.f812a.f1561a);
        return this.s.c() <= 0.05f;
    }

    public float a(d dVar, float f) {
        if (dVar == null) {
            throw new IllegalArgumentException("Board cannot be null!");
        }
        if (a(this.c, dVar, 0.2f)) {
            return a(this.c, dVar, f, this.n == b.ACCELERATING, this.n == b.BRAKING && !this.l, this.n == b.BRAKING && this.l);
        }
        return 0.0f;
    }

    public float b(d dVar, float f) {
        if (dVar == null) {
            throw new IllegalArgumentException("Board cannot be null!");
        }
        if (this.k && a(this.f812a, dVar, 0.38f)) {
            return a(this.f812a, dVar, f, false, true, false);
        }
        return 0.0f;
    }

    public float c(d dVar, float f) {
        if (dVar == null) {
            throw new IllegalArgumentException("Board cannot be null!");
        }
        if (a(this.b, dVar, 0.2f)) {
            return a(this.b, dVar, f, false, this.n == b.BRAKING && !this.l, false);
        }
        return 0.0f;
    }

    public float a() {
        return (float) Math.toDegrees(b());
    }

    public float b() {
        return (float) Math.atan2(this.b.f1561a.b - this.c.f1561a.b, this.b.f1561a.f1553a - this.c.f1561a.f1553a);
    }

    public com.topfreegames.engine.a.b c() {
        return this.d.b();
    }

    public com.topfreegames.engine.a.b d() {
        return this.d.c();
    }

    public com.topfreegames.engine.a.b e() {
        return this.f812a.f1561a;
    }

    public b f() {
        return this.n;
    }

    public c g() {
        return this.m;
    }

    public boolean h() {
        return this.n == b.CRASHED;
    }

    public void a(float f) {
        if (this.n != b.CRASHED) {
            this.p.a(this.f812a, this.c, f);
            this.o.a(this.f812a, this.b, f);
            this.q.a(this.c, this.b, f);
        }
    }

    public void a(float f, float f2, boolean z2) {
        if (this.n != b.CRASHED) {
            float fA = this.d.a(this.h * f, 15.0f, f2);
            if (!z2 || fA >= 0.0f) {
                this.d.a(fA);
            }
        }
    }

    public void a(float f, float f2, float f3, float f4) {
        float f5 = 0.25f;
        float f6 = -0.285f;
        if (f4 != 0.0f) {
            this.f812a.b.a(this.f812a.f1561a).b(-1.0f);
            this.f812a.f1561a.a(f, f2);
            this.f812a.b.b(this.f812a.f1561a).c(f4);
        } else {
            this.f812a.f1561a.a(f, f2);
        }
        float fC = (((this.f812a.b.c() * f4) / 0.1f) * 180.0f) / 3.1415927f;
        switch (i()[this.m.ordinal()]) {
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 17:
                break;
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 16:
            default:
                f6 = -0.2f;
                f5 = 0.17f;
                break;
        }
        this.s.a(f6, -0.32f).a(f3).b(this.f812a.f1561a);
        this.c.f1561a.a(this.s);
        this.c.d -= fC;
        this.s.a(f5, -0.32f).a(f3).b(this.f812a.f1561a);
        this.b.f1561a.a(this.s);
        this.b.d -= fC;
    }

    public void a(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("State cannot be null!");
        }
        this.n = bVar;
        switch (j()[this.n.ordinal()]) {
            case 1:
                this.c.e = -this.i;
                return;
            case 2:
                if (this.m != c.SANTA) {
                    this.c.e = 0.0f;
                    this.b.e = 0.0f;
                    return;
                } else {
                    this.c.e = this.i;
                    return;
                }
            case 3:
            default:
                return;
            case 4:
                this.d.b(this.f812a);
                return;
        }
    }

    public void b(float f) {
        this.d.b(f);
    }

    protected boolean a(com.topfreegames.engine.c.a aVar, d dVar, float f) {
        if (dVar.f1168a.f1553a < dVar.b.f1553a) {
            this.w.left = dVar.f1168a.f1553a - f;
            this.w.right = dVar.b.f1553a + f;
        } else {
            this.w.left = dVar.b.f1553a - f;
            this.w.right = dVar.f1168a.f1553a + f;
        }
        if (dVar.f1168a.b < dVar.b.b) {
            this.w.top = dVar.f1168a.b - f;
            this.w.bottom = dVar.b.b + f;
        } else {
            this.w.top = dVar.b.b - f;
            this.w.bottom = dVar.f1168a.b + f;
        }
        if (this.w.contains(aVar.f1561a.f1553a, aVar.f1561a.b)) {
            return true;
        }
        return false;
    }

    protected float a(com.topfreegames.engine.c.a aVar, d dVar, float f, boolean z2, boolean z3, boolean z4) {
        float fAbs;
        com.topfreegames.engine.a.b bVar = this.s;
        bVar.a(com.topfreegames.engine.a.a.b(aVar.f1561a, dVar.f1168a, dVar.b));
        com.topfreegames.engine.a.b bVar2 = this.t;
        bVar2.a(aVar.f1561a).c(bVar);
        com.topfreegames.engine.a.b bVar3 = this.u;
        if (bVar2.c() >= 0.1f) {
            return 0.0f;
        }
        com.topfreegames.engine.a.b bVar4 = this.t;
        bVar4.a(dVar.f1168a.b - dVar.b.b, dVar.b.f1553a - dVar.f1168a.f1553a);
        this.y.f1561a.a(bVar);
        float fA = this.r.a(aVar, this.y, bVar4, f);
        com.topfreegames.engine.a.b bVar5 = this.t;
        bVar5.a(dVar.b).c(dVar.f1168a);
        float fC = bVar5.c();
        if (z2 || z4) {
            if (Math.abs((bVar5.d(aVar.b) * this.f) / fC) < this.e) {
                fAbs = this.f / fC;
            } else {
                fAbs = this.e / Math.abs(bVar5.d(aVar.b));
            }
            if (com.topfreegames.engine.a.a.a(dVar.f1168a, dVar.b, aVar.f1561a) < 0.0f) {
                fAbs *= -1.0f;
            }
            if (z4) {
                fAbs *= -1.0f;
            }
            bVar3.a(bVar5).b(fAbs * f);
            aVar.b.b(bVar3);
        } else if (z3) {
            bVar3.a(bVar5).b((bVar5.d(aVar.b) > 0.0f ? -1.0f : 1.0f) * (this.g / fC) * f);
            aVar.b.b(bVar3);
        }
        com.topfreegames.engine.a.b bVar6 = this.v;
        bVar6.a(aVar.f1561a).c(bVar);
        aVar.e = (float) Math.toDegrees(((bVar6.f1553a * aVar.b.b) - (bVar6.b * aVar.b.f1553a)) / ((bVar6.b * bVar6.b) + (bVar6.f1553a * bVar6.f1553a)));
        return fA;
    }

    public void a(RectF rectF) {
        rectF.left = Math.min(this.f812a.f1561a.f1553a, Math.min(this.c.f1561a.f1553a, this.b.f1561a.f1553a)) - 0.2f;
        rectF.right = Math.max(this.f812a.f1561a.f1553a, Math.max(this.c.f1561a.f1553a, this.b.f1561a.f1553a)) + 0.2f;
        rectF.top = Math.min(this.f812a.f1561a.b, Math.min(this.c.f1561a.b, this.b.f1561a.b)) - 0.2f;
        rectF.bottom = Math.max(this.f812a.f1561a.b, Math.max(this.c.f1561a.b, this.b.f1561a.b)) + 0.2f;
    }
}
