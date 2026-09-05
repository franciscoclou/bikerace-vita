package com.topfreegames.bikerace.b;

import com.topfreegames.bikerace.m.f;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/* JADX INFO: compiled from: GameSession.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements Serializable {
    private List<com.topfreegames.bikerace.multiplayer.a> A;
    private Float B;
    private List<com.topfreegames.bikerace.multiplayer.a> C;
    private Integer D;
    private Integer E;
    private String F;
    private String G;
    private Integer H;
    private Integer I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1129a;
    private List<String> b;
    private String c;
    private String d;
    private Integer e;
    private String f;
    private String g;
    private Integer h;
    private Boolean i;
    private Integer j;
    private Integer k;
    private Integer l;
    private String m;
    private Float n;
    private List<com.topfreegames.bikerace.multiplayer.a> o;
    private Float p;
    private List<com.topfreegames.bikerace.multiplayer.a> q;
    private Integer r;
    private Integer s;
    private String t;
    private String u;
    private Integer v;
    private Integer w;
    private Integer x;
    private String y;
    private Float z;

    public static String a(String str, String str2) {
        if (a(str) && a(str2)) {
            if (new BigInteger(str).compareTo(new BigInteger(str2)) < 0) {
                return String.valueOf(str) + "_" + str2;
            }
            return String.valueOf(str2) + "_" + str;
        }
        if (str.compareTo(str2) > 0) {
            return String.valueOf(str) + "_" + str2;
        }
        return String.valueOf(str2) + "_" + str;
    }

    public static boolean a(String str) {
        return str.matches("^(\\d)+$");
    }

    public a(a aVar) {
        this.f1129a = aVar.a();
        this.b = aVar.b();
        this.c = aVar.c();
        this.d = aVar.d();
        this.e = aVar.e();
        this.f = aVar.f();
        this.g = aVar.g();
        this.h = aVar.h();
        this.i = aVar.i();
        this.j = aVar.j();
        this.k = aVar.k();
        this.l = aVar.l();
        this.m = aVar.m();
        this.n = aVar.n();
        this.o = aVar.D();
        this.p = aVar.o();
        this.q = aVar.E();
        this.r = aVar.p();
        this.s = aVar.q();
        this.t = aVar.r();
        this.u = aVar.s();
        this.v = aVar.t();
        this.w = aVar.u();
        this.x = aVar.v();
        this.y = aVar.w();
        this.z = aVar.x();
        this.A = aVar.F();
        this.B = aVar.y();
        this.C = aVar.G();
        this.D = aVar.z();
        this.E = aVar.A();
        this.F = aVar.B();
        this.G = aVar.C();
        this.H = aVar.H();
        this.I = aVar.I();
    }

    public a(String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3) {
        this.f1129a = a(str, str3);
        this.c = str;
        this.d = f.a(str2);
        this.f = str3;
        this.g = f.a(str4);
        this.y = str;
        this.w = num;
        this.x = num2;
        this.v = 0;
        this.e = 0;
        this.h = 0;
        this.i = true;
        this.H = num3;
        this.I = 0;
    }

    public a() {
    }

    public String a() {
        return this.f1129a;
    }

    public void b(String str) {
        this.f1129a = str;
    }

    public List<String> b() {
        return this.b;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public Integer e() {
        return this.e;
    }

    public void a(Integer num) {
        this.e = num;
    }

    public String f() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void f(String str) {
        this.g = str;
    }

    public Integer h() {
        return this.h;
    }

    public void b(Integer num) {
        this.h = num;
    }

    public Boolean i() {
        return this.i;
    }

    public void a(Boolean bool) {
        this.i = bool;
    }

    public Integer j() {
        return this.j;
    }

    public void c(Integer num) {
        this.j = num;
    }

    public Integer k() {
        return this.k;
    }

    public void d(Integer num) {
        this.k = num;
    }

    public Integer l() {
        return this.l;
    }

    public void e(Integer num) {
        this.l = num;
    }

    public String m() {
        return this.m;
    }

    public void g(String str) {
        this.m = str;
    }

    public Float n() {
        return this.n;
    }

    public void a(Float f) {
        this.n = f;
    }

    public Float o() {
        return this.p;
    }

    public void b(Float f) {
        this.p = f;
    }

    public Integer p() {
        return this.r;
    }

    public void f(Integer num) {
        this.r = num;
    }

    public Integer q() {
        return this.s;
    }

    public void g(Integer num) {
        this.s = num;
    }

    public String r() {
        return this.t;
    }

    public void h(String str) {
        this.t = str;
    }

    public String s() {
        return this.u;
    }

    public void i(String str) {
        this.u = str;
    }

    public Integer t() {
        return this.v;
    }

    public void h(Integer num) {
        this.v = num;
    }

    public Integer u() {
        return this.w;
    }

    public void i(Integer num) {
        this.w = num;
    }

    public Integer v() {
        return this.x;
    }

    public void j(Integer num) {
        this.x = num;
    }

    public String w() {
        return this.y;
    }

    public void j(String str) {
        this.y = str;
    }

    public Float x() {
        return this.z;
    }

    public void c(Float f) {
        this.z = f;
    }

    public Float y() {
        return this.B;
    }

    public void d(Float f) {
        this.B = f;
    }

    public Integer z() {
        return this.D;
    }

    public void k(Integer num) {
        this.D = num;
    }

    public Integer A() {
        return this.E;
    }

    public void l(Integer num) {
        this.E = num;
    }

    public String B() {
        return this.F;
    }

    public void k(String str) {
        this.F = str;
    }

    public String C() {
        return this.G;
    }

    public void l(String str) {
        this.G = str;
    }

    public int hashCode() {
        return (((this.H == null ? 0 : this.H.intValue()) + (((this.b == null ? 0 : this.b.hashCode()) + (((this.h == null ? 0 : this.h.hashCode()) + (((this.g == null ? 0 : this.g.hashCode()) + (((this.f == null ? 0 : this.f.hashCode()) + (((this.w == null ? 0 : this.w.hashCode()) + (((this.B == null ? 0 : this.B.hashCode()) + (((this.E == null ? 0 : this.E.hashCode()) + (((this.C == null ? 0 : this.C.hashCode()) + (((this.G == null ? 0 : this.G.hashCode()) + (((this.v == null ? 0 : this.v.hashCode()) + (((this.x == null ? 0 : this.x.hashCode()) + (((this.z == null ? 0 : this.z.hashCode()) + (((this.D == null ? 0 : this.D.hashCode()) + (((this.y == null ? 0 : this.y.hashCode()) + (((this.A == null ? 0 : this.A.hashCode()) + (((this.F == null ? 0 : this.F.hashCode()) + (((this.i == null ? 0 : this.i.hashCode()) + (((this.f1129a == null ? 0 : this.f1129a.hashCode()) + (((this.k == null ? 0 : this.k.hashCode()) + (((this.p == null ? 0 : this.p.hashCode()) + (((this.s == null ? 0 : this.s.hashCode()) + (((this.q == null ? 0 : this.q.hashCode()) + (((this.u == null ? 0 : this.u.hashCode()) + (((this.j == null ? 0 : this.j.hashCode()) + (((this.l == null ? 0 : this.l.hashCode()) + (((this.n == null ? 0 : this.n.hashCode()) + (((this.r == null ? 0 : this.r.hashCode()) + (((this.m == null ? 0 : this.m.hashCode()) + (((this.o == null ? 0 : this.o.hashCode()) + (((this.t == null ? 0 : this.t.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.I != null ? this.I.intValue() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            a aVar = (a) obj;
            if (this.c == null) {
                if (aVar.c != null) {
                    return false;
                }
            } else if (!this.c.equals(aVar.c)) {
                return false;
            }
            if (this.d == null) {
                if (aVar.d != null) {
                    return false;
                }
            } else if (!this.d.equals(aVar.d)) {
                return false;
            }
            if (this.e == null) {
                if (aVar.e != null) {
                    return false;
                }
            } else if (!this.e.equals(aVar.e)) {
                return false;
            }
            if (this.t == null) {
                if (aVar.t != null) {
                    return false;
                }
            } else if (!this.t.equals(aVar.t)) {
                return false;
            }
            if (this.o == null) {
                if (aVar.o != null) {
                    return false;
                }
            } else if (!this.o.equals(aVar.o)) {
                return false;
            }
            if (this.m == null) {
                if (aVar.m != null) {
                    return false;
                }
            } else if (!this.m.equals(aVar.m)) {
                return false;
            }
            if (this.r == null) {
                if (aVar.r != null) {
                    return false;
                }
            } else if (!this.r.equals(aVar.r)) {
                return false;
            }
            if (this.n == null) {
                if (aVar.n != null) {
                    return false;
                }
            } else if (!this.n.equals(aVar.n)) {
                return false;
            }
            if (this.l == null) {
                if (aVar.l != null) {
                    return false;
                }
            } else if (!this.l.equals(aVar.l)) {
                return false;
            }
            if (this.j == null) {
                if (aVar.j != null) {
                    return false;
                }
            } else if (!this.j.equals(aVar.j)) {
                return false;
            }
            if (this.u == null) {
                if (aVar.u != null) {
                    return false;
                }
            } else if (!this.u.equals(aVar.u)) {
                return false;
            }
            if (this.q == null) {
                if (aVar.q != null) {
                    return false;
                }
            } else if (!this.q.equals(aVar.q)) {
                return false;
            }
            if (this.s == null) {
                if (aVar.s != null) {
                    return false;
                }
            } else if (!this.s.equals(aVar.s)) {
                return false;
            }
            if (this.p == null) {
                if (aVar.p != null) {
                    return false;
                }
            } else if (!this.p.equals(aVar.p)) {
                return false;
            }
            if (this.k == null) {
                if (aVar.k != null) {
                    return false;
                }
            } else if (!this.k.equals(aVar.k)) {
                return false;
            }
            if (this.f1129a == null) {
                if (aVar.f1129a != null) {
                    return false;
                }
            } else if (!this.f1129a.equals(aVar.f1129a)) {
                return false;
            }
            if (this.i == null) {
                if (aVar.i != null) {
                    return false;
                }
            } else if (!this.i.equals(aVar.i)) {
                return false;
            }
            if (this.F == null) {
                if (aVar.F != null) {
                    return false;
                }
            } else if (!this.F.equals(aVar.F)) {
                return false;
            }
            if (this.A == null) {
                if (aVar.A != null) {
                    return false;
                }
            } else if (!this.A.equals(aVar.A)) {
                return false;
            }
            if (this.y == null) {
                if (aVar.y != null) {
                    return false;
                }
            } else if (!this.y.equals(aVar.y)) {
                return false;
            }
            if (this.D == null) {
                if (aVar.D != null) {
                    return false;
                }
            } else if (!this.D.equals(aVar.D)) {
                return false;
            }
            if (this.z == null) {
                if (aVar.z != null) {
                    return false;
                }
            } else if (!this.z.equals(aVar.z)) {
                return false;
            }
            if (this.x == null) {
                if (aVar.x != null) {
                    return false;
                }
            } else if (!this.x.equals(aVar.x)) {
                return false;
            }
            if (this.v == null) {
                if (aVar.v != null) {
                    return false;
                }
            } else if (!this.v.equals(aVar.v)) {
                return false;
            }
            if (this.G == null) {
                if (aVar.G != null) {
                    return false;
                }
            } else if (!this.G.equals(aVar.G)) {
                return false;
            }
            if (this.C == null) {
                if (aVar.C != null) {
                    return false;
                }
            } else if (!this.C.equals(aVar.C)) {
                return false;
            }
            if (this.E == null) {
                if (aVar.E != null) {
                    return false;
                }
            } else if (!this.E.equals(aVar.E)) {
                return false;
            }
            if (this.B == null) {
                if (aVar.B != null) {
                    return false;
                }
            } else if (!this.B.equals(aVar.B)) {
                return false;
            }
            if (this.w == null) {
                if (aVar.w != null) {
                    return false;
                }
            } else if (!this.w.equals(aVar.w)) {
                return false;
            }
            if (this.f == null) {
                if (aVar.f != null) {
                    return false;
                }
            } else if (!this.f.equals(aVar.f)) {
                return false;
            }
            if (this.g == null) {
                if (aVar.g != null) {
                    return false;
                }
            } else if (!this.g.equals(aVar.g)) {
                return false;
            }
            if (this.h == null) {
                if (aVar.h != null) {
                    return false;
                }
            } else if (!this.h.equals(aVar.h)) {
                return false;
            }
            if (this.b == null) {
                return aVar.b == null;
            }
            return this.b.equals(aVar.b);
        }
        return false;
    }

    public List<com.topfreegames.bikerace.multiplayer.a> D() {
        return this.o;
    }

    public void b(List<com.topfreegames.bikerace.multiplayer.a> list) {
        this.o = list;
    }

    public List<com.topfreegames.bikerace.multiplayer.a> E() {
        return this.q;
    }

    public void c(List<com.topfreegames.bikerace.multiplayer.a> list) {
        this.q = list;
    }

    public void d(List<com.topfreegames.bikerace.multiplayer.a> list) {
        this.A = list;
    }

    public void e(List<com.topfreegames.bikerace.multiplayer.a> list) {
        this.C = list;
    }

    public List<com.topfreegames.bikerace.multiplayer.a> F() {
        return this.A;
    }

    public List<com.topfreegames.bikerace.multiplayer.a> G() {
        return this.C;
    }

    public void m(Integer num) {
        this.H = num;
    }

    public Integer H() {
        return this.H;
    }

    public void n(Integer num) {
        this.I = num;
    }

    public Integer I() {
        return this.I;
    }

    public boolean J() {
        return c() != null && f() != null && c() != f() && this.i.booleanValue() && e().intValue() == 0 && h().intValue() == 0 && t().intValue() == 0 && w() == c() && d() != null && g() != null && u() != null && v() != null && G() == null && A() == null && y() == null && k() == null && l() == null && p() == null && n() == null && D() == null && q() == null && o() == null && E() == null;
    }

    public boolean a(boolean z) {
        boolean z2 = (c() == null || f() == null || c().equals(f())) ? false : true;
        if (z && !this.i.booleanValue()) {
            z2 = false;
        }
        if (d() == null || g() == null || u() == null || v() == null) {
            z2 = false;
        }
        if (t() == null) {
            return false;
        }
        if (t().intValue() >= 1) {
            if (F() == null || x() == null) {
                z2 = false;
            }
            if (t().intValue() >= 2) {
                if (G() == null || y() == null) {
                    return false;
                }
                return z2;
            }
            return z2;
        }
        return z2;
    }

    public void m(String str) {
        int iLastIndexOf;
        String strA = a();
        if (this.i.booleanValue() && strA != null) {
            if ((c() == null || f() == null || c().equals(f())) && (iLastIndexOf = strA.lastIndexOf("_")) > 0 && iLastIndexOf < strA.length()) {
                String strSubstring = strA.substring(0, iLastIndexOf);
                String strSubstring2 = strA.substring(iLastIndexOf + 1, strA.length());
                c(str);
                if (strSubstring.equals(str)) {
                    e(strSubstring2);
                } else if (strSubstring2.equals(str)) {
                    e(strSubstring);
                }
            }
            if (d() == null) {
                d("Guest");
            }
            if (g() == null) {
                f("Guest");
            }
            K();
            L();
            this.y = c();
            this.w = 1;
            this.x = 1;
            this.v = 0;
        }
    }

    public void K() {
        h((String) null);
        b((List<com.topfreegames.bikerace.multiplayer.a>) null);
        g((String) null);
        f((Integer) null);
        a((Float) null);
        i((String) null);
        c((List<com.topfreegames.bikerace.multiplayer.a>) null);
        g((Integer) null);
        b((Float) null);
        e((Integer) null);
        d((Integer) null);
        c((Integer) null);
    }

    public void L() {
        k((String) null);
        d((List<com.topfreegames.bikerace.multiplayer.a>) null);
        j((String) null);
        k((Integer) null);
        c((Float) null);
        l((String) null);
        e((List<com.topfreegames.bikerace.multiplayer.a>) null);
        l((Integer) null);
        d((Float) null);
        j((Integer) null);
        i((Integer) null);
        h((Integer) null);
    }
}
