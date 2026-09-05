package com.topfreegames.bikerace.multiplayer;

import com.topfreegames.bikerace.au;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MultiplayerData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.b.a f1314a;
    private boolean b;
    private boolean c;
    private String d;
    private boolean e;
    private boolean f;

    public l(com.topfreegames.bikerace.b.a aVar, String str) {
        this(aVar, str, false);
    }

    public l(com.topfreegames.bikerace.b.a aVar, String str, boolean z) {
        if (aVar == null) {
            throw new IllegalArgumentException("Session cannot be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        this.f1314a = aVar;
        this.b = str.equals(aVar.c());
        this.d = str;
        this.c = false;
        this.e = z;
        this.f = false;
    }

    public com.topfreegames.bikerace.b.a a() {
        return this.f1314a;
    }

    public String b() {
        return this.f1314a.a();
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.e;
    }

    public int d() {
        Integer numL;
        if (H()) {
            numL = this.f1314a.v();
        } else {
            numL = this.f1314a.l();
        }
        if (numL == null) {
            return 0;
        }
        return numL.intValue();
    }

    public com.topfreegames.bikerace.c e() {
        return d(false);
    }

    public String f() {
        return this.b ? this.f1314a.f() : this.f1314a.c();
    }

    public String g() {
        return this.b ? this.f1314a.g() : this.f1314a.d();
    }

    public ak h() {
        return e(false);
    }

    public Float i() {
        return Float.valueOf(f(false));
    }

    public int j() {
        Integer numE;
        if (this.b) {
            numE = this.f1314a.h();
        } else {
            numE = this.f1314a.e();
        }
        if (numE == null) {
            return 0;
        }
        return numE.intValue();
    }

    public com.topfreegames.bikerace.c k() {
        return d(true);
    }

    public String l() {
        return this.b ? this.f1314a.c() : this.f1314a.f();
    }

    public String m() {
        return this.b ? this.f1314a.d() : this.f1314a.g();
    }

    private void a(String str) {
        if (this.b) {
            this.f1314a.d(str);
        } else {
            this.f1314a.f(str);
        }
    }

    public ak n() {
        return e(true);
    }

    public float o() {
        return f(true);
    }

    public int p() {
        Integer numH;
        if (this.b) {
            numH = this.f1314a.e();
        } else {
            numH = this.f1314a.h();
        }
        if (numH == null) {
            return 0;
        }
        return numH.intValue();
    }

    public int q() {
        Integer numK;
        if (H()) {
            numK = this.f1314a.u();
        } else {
            numK = this.f1314a.k();
        }
        if (numK == null) {
            return 0;
        }
        return numK.intValue();
    }

    public void r() {
        if (this.b) {
            this.f1314a.b(Integer.valueOf(this.f1314a.h().intValue() + 1));
        } else {
            this.f1314a.a(Integer.valueOf(this.f1314a.e().intValue() + 1));
        }
    }

    public void s() {
        if (this.b) {
            this.f1314a.a(Integer.valueOf(this.f1314a.e().intValue() + 1));
        } else {
            this.f1314a.b(Integer.valueOf(this.f1314a.h().intValue() + 1));
        }
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void a(com.topfreegames.bikerace.c cVar, float f, ak akVar, String str, String str2) {
        a(cVar, f, akVar, str, str2, -1, -1);
    }

    public void a(com.topfreegames.bikerace.c cVar, float f, ak akVar, String str, String str2, int i, int i2) {
        if (akVar == null) {
            throw new IllegalArgumentException("RaceRecord cannot be null!");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("BikeType cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("UserName cannot be null!");
        }
        ArrayList<a> arrayListC = akVar.c();
        if (arrayListC == null) {
            throw new IllegalStateException("RaceRecord cannot be empty!");
        }
        a(str2);
        a(au.c());
        if (H()) {
            if (this.b) {
                this.f1314a.k(Integer.valueOf(cVar.ordinal()));
                this.f1314a.c(Float.valueOf(f));
                this.f1314a.d(arrayListC);
                this.f1314a.k(str);
            } else {
                this.f1314a.l(Integer.valueOf(cVar.ordinal()));
                this.f1314a.d(Float.valueOf(f));
                this.f1314a.e(arrayListC);
                this.f1314a.l(str);
            }
            if (i >= 0 && i2 >= 0) {
                this.f1314a.i(Integer.valueOf(i));
                this.f1314a.j(Integer.valueOf(i2));
            }
        } else {
            if (this.b) {
                this.f1314a.f(Integer.valueOf(cVar.ordinal()));
                this.f1314a.a(Float.valueOf(f));
                this.f1314a.b(arrayListC);
                this.f1314a.h(str);
            } else {
                this.f1314a.g(Integer.valueOf(cVar.ordinal()));
                this.f1314a.b(Float.valueOf(f));
                this.f1314a.c(arrayListC);
                this.f1314a.i(str);
            }
            if (i >= 0 && i2 >= 0) {
                this.f1314a.d(Integer.valueOf(i));
                this.f1314a.e(Integer.valueOf(i2));
            }
        }
        this.f1314a.a((Boolean) true);
    }

    public void a(int i, int i2) {
        if (this.f1314a.w() == null) {
            G();
            this.f1314a.j(this.d);
            this.f1314a.h((Integer) 0);
            this.f1314a.i(Integer.valueOf(i));
            this.f1314a.j(Integer.valueOf(i2));
        } else {
            if (this.f1314a.m() != null) {
                E();
            }
            this.f1314a.g(this.d);
            this.f1314a.c(Integer.valueOf(this.f1314a.t().intValue() + 1));
            this.f1314a.d(Integer.valueOf(i));
            this.f1314a.e(Integer.valueOf(i2));
        }
        this.c = false;
    }

    private com.topfreegames.bikerace.c d(boolean z) {
        Integer numP;
        if (H()) {
            if (this.b) {
                if (z) {
                    numP = this.f1314a.z();
                } else {
                    numP = this.f1314a.A();
                }
            } else if (z) {
                numP = this.f1314a.A();
            } else {
                numP = this.f1314a.z();
            }
        } else if (this.b) {
            if (z) {
                numP = this.f1314a.p();
            } else {
                numP = this.f1314a.q();
            }
        } else if (z) {
            numP = this.f1314a.q();
        } else {
            numP = this.f1314a.p();
        }
        if (numP == null) {
            return com.topfreegames.bikerace.c.REGULAR;
        }
        return com.topfreegames.bikerace.c.a(numP.intValue());
    }

    private m C() {
        Float fN = this.f1314a.n();
        Float fO = this.f1314a.o();
        if ((fN == null || fN.floatValue() == 0.0f) && (fO == null || fO.floatValue() == 0.0f)) {
            String strM = this.f1314a.m();
            if (strM != null) {
                if (strM.equals(this.d)) {
                    return m.READY;
                }
                return m.WAITING;
            }
            return m.READY;
        }
        if (this.b) {
            if (fN == null || fN.floatValue() == 0.0f) {
                return m.READY;
            }
            if (fO == null || fO.floatValue() == 0.0f) {
                return m.WAITING;
            }
        } else {
            if (fO == null || fO.floatValue() == 0.0f) {
                return m.READY;
            }
            if (fN == null || fN.floatValue() == 0.0f) {
                return m.WAITING;
            }
        }
        return m.WAITING;
    }

    private m D() {
        Float fX = this.f1314a.x();
        Float fY = this.f1314a.y();
        if (this.b) {
            if (fX == null || fX.floatValue() == 0.0f) {
                return m.READY;
            }
            if (fY == null || fY.floatValue() == 0.0f) {
                return m.WAITING;
            }
        } else {
            if (fY == null || fY.floatValue() == 0.0f) {
                return m.READY;
            }
            if (fX == null || fX.floatValue() == 0.0f) {
                return m.WAITING;
            }
        }
        String strW = this.f1314a.w();
        if (fX != null && fX.floatValue() != 0.0f && fY != null && fY.floatValue() != 0.0f && strW != null && strW.equals(this.d) && !this.c) {
            return m.SHOW_RESULT;
        }
        return m.FINISHED;
    }

    private ak e(boolean z) {
        List<a> listD;
        if (H()) {
            if (this.b) {
                if (z) {
                    listD = this.f1314a.F();
                } else {
                    listD = this.f1314a.G();
                }
            } else if (z) {
                listD = this.f1314a.G();
            } else {
                listD = this.f1314a.F();
            }
        } else if (this.b) {
            if (z) {
                listD = this.f1314a.D();
            } else {
                listD = this.f1314a.E();
            }
        } else if (z) {
            listD = this.f1314a.E();
        } else {
            listD = this.f1314a.D();
        }
        if (listD == null) {
            return null;
        }
        return new ak(listD);
    }

    private float f(boolean z) {
        Float fN;
        if (H()) {
            if (this.b) {
                if (z) {
                    fN = this.f1314a.x();
                } else {
                    fN = this.f1314a.y();
                }
            } else if (z) {
                fN = this.f1314a.y();
            } else {
                fN = this.f1314a.x();
            }
        } else if (this.b) {
            if (z) {
                fN = this.f1314a.n();
            } else {
                fN = this.f1314a.o();
            }
        } else if (z) {
            fN = this.f1314a.o();
        } else {
            fN = this.f1314a.n();
        }
        if (fN == null) {
            return 0.0f;
        }
        return fN.floatValue();
    }

    private void E() {
        G();
        this.f1314a.k(this.f1314a.r());
        this.f1314a.d(this.f1314a.D());
        this.f1314a.j(this.f1314a.m());
        this.f1314a.k(this.f1314a.p());
        this.f1314a.c(this.f1314a.n());
        this.f1314a.l(this.f1314a.s());
        this.f1314a.e(this.f1314a.E());
        this.f1314a.l(this.f1314a.q());
        this.f1314a.d(this.f1314a.o());
        this.f1314a.j(this.f1314a.l());
        this.f1314a.i(this.f1314a.k());
        this.f1314a.h(this.f1314a.j());
        F();
    }

    private void F() {
        this.f1314a.K();
    }

    private void G() {
        this.f1314a.L();
    }

    private boolean H() {
        return D() != m.FINISHED && C() == m.READY;
    }

    public m t() {
        return H() ? D() : C();
    }

    public boolean u() {
        m mVarT = t();
        return mVarT == m.READY || mVarT == m.SHOW_RESULT;
    }

    public boolean v() {
        return this.f1314a.F() != null && this.f1314a.G() == null && !this.b && this.f1314a.D() == null && this.f1314a.E() == null;
    }

    public boolean w() {
        Integer numU = this.f1314a.u();
        int iK = this.f1314a.k();
        Integer num = numU == null ? 1 : numU;
        if (iK == null) {
            iK = 1;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < com.topfreegames.bikerace.h.y.c.length; i++) {
            arrayList.add(Integer.valueOf(com.topfreegames.bikerace.h.y.c[i]));
        }
        return (arrayList.contains(num) && arrayList.contains(iK)) ? false : true;
    }

    public void c(boolean z) {
        this.f = z;
    }

    public boolean x() {
        return this.f;
    }

    private void a(int i) {
        if (this.b) {
            this.f1314a.m(Integer.valueOf(i));
        } else {
            this.f1314a.n(Integer.valueOf(i));
        }
    }

    public int y() {
        Integer numI;
        if (this.b) {
            numI = this.f1314a.H();
        } else {
            numI = this.f1314a.I();
        }
        if (numI == null) {
            return 0;
        }
        return numI.intValue();
    }

    public int z() {
        Integer numH;
        if (this.b) {
            numH = this.f1314a.I();
        } else {
            numH = this.f1314a.H();
        }
        if (numH == null) {
            return 0;
        }
        return numH.intValue();
    }

    public boolean A() {
        m mVarT = t();
        m mVarT2 = new l(this.f1314a, f()).t();
        if (mVarT == m.WAITING && mVarT2 == m.WAITING) {
            return true;
        }
        return mVarT == m.FINISHED && mVarT2 == m.FINISHED;
    }

    public void B() {
        int iT = this.f1314a.t();
        if (iT == null) {
            iT = 0;
        }
        this.f1314a.m(this.d);
        this.f1314a.h(iT);
    }
}
