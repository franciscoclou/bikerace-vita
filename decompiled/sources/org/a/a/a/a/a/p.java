package org.a.a.a.a.a;

import org.a.a.a.a.a.b.t;

/* JADX INFO: compiled from: Token.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class p {
    static final String d = p.class.getName();
    private String n;
    private volatile boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private Object i = new Object();
    private Object j = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected org.a.a.a.a.l f1597a = null;
    private t k = null;
    private org.a.a.a.a.k l = null;
    private String[] m = null;
    private org.a.a.a.a.b o = null;
    private org.a.a.a.a.a p = null;
    private Object q = null;
    public int b = 0;
    public boolean c = false;
    org.a.a.a.a.b.a e = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", d);

    public p(String str) {
        this.e.a(str);
    }

    public void a(int i) {
        this.b = i;
    }

    public boolean a() throws org.a.a.a.a.k {
        if (b() != null) {
            throw b();
        }
        return true;
    }

    public org.a.a.a.a.k b() {
        return this.l;
    }

    public boolean c() {
        return this.f;
    }

    protected boolean d() {
        return this.g;
    }

    public void a(org.a.a.a.a.a aVar) {
        this.p = aVar;
    }

    public org.a.a.a.a.a e() {
        return this.p;
    }

    public void a(long j) throws org.a.a.a.a.k {
        this.e.b(d, "waitForCompletion", "407", new Object[]{m(), new Long(j), this});
        if (b(j) == null && !this.f) {
            this.e.b(d, "waitForCompletion", "406", new Object[]{m(), this});
            throw new org.a.a.a.a.k(32000);
        }
        a();
    }

    protected t b(long j) {
        synchronized (this.i) {
            org.a.a.a.a.b.a aVar = this.e;
            String str = d;
            Object[] objArr = new Object[7];
            objArr[0] = m();
            objArr[1] = new Long(j);
            objArr[2] = new Boolean(this.h);
            objArr[3] = new Boolean(this.f);
            objArr[4] = this.l == null ? "false" : "true";
            objArr[5] = this.k;
            objArr[6] = this;
            aVar.a(str, "waitForResponse", "400", objArr, this.l);
            if (!this.f) {
                if (this.l == null) {
                    try {
                        this.e.b(d, "waitForResponse", "408", new Object[]{m(), new Long(j)});
                        if (j == -1) {
                            this.i.wait();
                        } else {
                            this.i.wait(j);
                        }
                    } catch (InterruptedException e) {
                        this.l = new org.a.a.a.a.k(e);
                    }
                }
                if (!this.f && this.l != null) {
                    this.e.a(d, "waitForResponse", "401", null, this.l);
                    throw this.l;
                }
            }
        }
        this.e.b(d, "waitForResponse", "402", new Object[]{m(), this.k});
        return this.k;
    }

    protected void a(t tVar, org.a.a.a.a.k kVar) {
        this.e.b(d, "markComplete", "404", new Object[]{m(), tVar, kVar});
        synchronized (this.i) {
            if (tVar instanceof org.a.a.a.a.a.b.b) {
                this.f1597a = null;
            }
            this.g = true;
            this.k = tVar;
            this.l = kVar;
        }
    }

    protected void f() {
        this.e.b(d, "notifyComplete", "404", new Object[]{m(), this.k, this.l});
        synchronized (this.i) {
            if (this.l == null && this.g) {
                this.f = true;
                this.g = false;
            } else {
                this.g = false;
            }
            this.i.notifyAll();
        }
        synchronized (this.j) {
            this.h = true;
            this.j.notifyAll();
        }
    }

    public void g() {
        synchronized (this.j) {
            synchronized (this.i) {
                if (this.l != null) {
                    throw this.l;
                }
            }
            if (!this.h) {
                try {
                    this.e.b(d, "waitUntilSent", "409", new Object[]{m()});
                    this.j.wait();
                } catch (InterruptedException e) {
                }
            }
            if (!this.h) {
                if (this.l == null) {
                    throw i.a(6);
                }
                throw this.l;
            }
        }
    }

    protected void h() {
        this.e.b(d, "notifySent", "403", new Object[]{m()});
        synchronized (this.i) {
            this.k = null;
            this.f = false;
        }
        synchronized (this.j) {
            this.h = true;
            this.j.notifyAll();
        }
    }

    public org.a.a.a.a.b i() {
        return this.o;
    }

    protected void a(org.a.a.a.a.b bVar) {
        this.o = bVar;
    }

    public t j() {
        return this.k;
    }

    public void a(org.a.a.a.a.l lVar) {
        this.f1597a = lVar;
    }

    public String[] k() {
        return this.m;
    }

    public void a(String[] strArr) {
        this.m = strArr;
    }

    public Object l() {
        return this.q;
    }

    public void a(Object obj) {
        this.q = obj;
    }

    public void a(String str) {
        this.n = str;
    }

    public String m() {
        return this.n;
    }

    public void a(org.a.a.a.a.k kVar) {
        synchronized (this.i) {
            this.l = kVar;
        }
    }

    public boolean n() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + m());
        stringBuffer.append(" ,topics=");
        if (k() != null) {
            for (int i = 0; i < k().length; i++) {
                stringBuffer.append(k()[i] + ", ");
            }
        }
        stringBuffer.append(" ,usercontext=" + l());
        stringBuffer.append(" ,isComplete=" + c());
        stringBuffer.append(" ,isNotified=" + n());
        stringBuffer.append(" ,exception=" + b());
        stringBuffer.append(" ,actioncallback=" + e());
        return stringBuffer.toString();
    }
}
