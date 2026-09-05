package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.widget.PlacePickerFragment;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class cl implements cn.b, ei.a {
    private boolean B;
    private String C;
    private byte D;
    private long E;
    private long F;
    private boolean I;
    private int J;
    private int L;
    private int M;
    private Map<String, List<String>> O;
    private final Handler P;
    private cn Q;
    private a R;
    private int S;
    private File k;
    private String m;
    private String n;
    private List<cj> o;
    private boolean q;
    private long r;
    private String t;
    private long u;
    private long v;
    private long w;
    private long x;
    private String y;
    private String z;
    private static final String g = cl.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f524a = 100;
    static int b = 10;
    static int c = PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
    static int d = 160000;
    static int e = 50;
    static int f = 20;
    private final AtomicInteger h = new AtomicInteger(0);
    private final AtomicInteger i = new AtomicInteger(0);
    private final AtomicInteger j = new AtomicInteger(0);
    private volatile boolean l = false;
    private final Map<ej, ByteBuffer> p = new HashMap();
    private List<cj> s = new ArrayList();
    private byte A = -1;
    private final Map<String, bx.a> G = new HashMap();
    private final List<cb> H = new ArrayList();
    private final List<ca> K = new ArrayList();
    private final by N = new by();
    private boolean T = false;

    public interface a {
        void d(String str);
    }

    public void a(Map<String, List<String>> map) {
        this.O = map;
    }

    Map<String, List<String>> a() {
        return this.O;
    }

    public void a(fi fiVar) {
        this.P.post(fiVar);
    }

    public void b() {
        this.q = true;
    }

    public cl(Context context, String str, a aVar) {
        this.k = null;
        this.y = "";
        this.z = "";
        ex.a(4, g, "Initializing new Flurry session");
        HandlerThread handlerThread = new HandlerThread("FlurryAgentSession_" + str);
        handlerThread.start();
        this.P = new Handler(handlerThread.getLooper());
        u();
        this.Q = new cn(this);
        this.R = aVar;
        this.m = str;
        this.k = context.getFileStreamPath(A());
        this.n = ep.a();
        this.w = -1L;
        this.L = 0;
        this.z = TimeZone.getDefault().getID();
        this.y = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
        this.I = true;
        this.J = 0;
        this.M = 0;
        r();
    }

    private void r() {
        ei eiVarA = eh.a();
        this.D = ((Byte) eiVarA.a("Gender")).byteValue();
        eiVarA.a("Gender", (ei.a) this);
        ex.a(4, g, "initSettings, Gender = " + ((int) this.D));
        this.C = (String) eiVarA.a("UserId");
        eiVarA.a("UserId", (ei.a) this);
        ex.a(4, g, "initSettings, UserId = " + this.C);
        this.B = ((Boolean) eiVarA.a("LogEvents")).booleanValue();
        eiVarA.a("LogEvents", (ei.a) this);
        ex.a(4, g, "initSettings, LogEvents = " + this.B);
        this.E = ((Long) eiVarA.a("Age")).longValue();
        eiVarA.a("Age", (ei.a) this);
        ex.a(4, g, "initSettings, BirthDate = " + this.E);
        this.F = ((Long) eiVarA.a("ContinueSessionMillis")).longValue();
        eiVarA.a("ContinueSessionMillis", (ei.a) this);
        ex.a(4, g, "initSettings, ContinueSessionMillis = " + this.F);
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("Gender")) {
            this.D = ((Byte) obj).byteValue();
            ex.a(4, g, "onSettingUpdate, Gender = " + ((int) this.D));
            return;
        }
        if (str.equals("UserId")) {
            this.C = (String) obj;
            ex.a(4, g, "onSettingUpdate, UserId = " + this.C);
            return;
        }
        if (str.equals("LogEvents")) {
            this.B = ((Boolean) obj).booleanValue();
            ex.a(4, g, "onSettingUpdate, LogEvents = " + this.B);
        } else if (str.equals("Age")) {
            this.E = ((Long) obj).longValue();
            ex.a(4, g, "onSettingUpdate, Birthdate = " + this.E);
        } else if (str.equals("ContinueSessionMillis")) {
            this.F = ((Long) obj).longValue();
            ex.a(4, g, "onSettingUpdate, ContinueSessionMillis = " + this.F);
        } else {
            ex.a(6, g, "onSettingUpdate internal error!");
        }
    }

    public synchronized void c() {
        if (this.Q.b()) {
            this.Q.a();
        }
        y();
        if (!this.T) {
            s();
            this.T = true;
        } else {
            t();
        }
    }

    private void s() {
        ex.e(g, "Start session");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.u = System.currentTimeMillis();
        this.v = jElapsedRealtime;
        a(new fi() { // from class: com.flurry.sdk.cl.1
            @Override // com.flurry.sdk.fi
            public void a() {
                if (!cl.this.l) {
                    cl.this.d(eg.a().b());
                }
                cl.this.a(eg.a().b());
            }
        });
    }

    private void t() {
        ex.e(g, "Continuing previous session");
    }

    public synchronized void d() {
        ex.e(g, "Trying to end session");
        if (this.T) {
            this.w = SystemClock.elapsedRealtime() - this.v;
            a(this.w);
            v();
            if (i() > 0) {
                z();
            }
            if (i() == 0) {
                this.Q.a(this.F);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        try {
            byte[] bArrA = em.a();
            if (bArrA != null) {
                ex.a(3, g, "Fetched hashed IMEI");
                this.p.put(ej.Sha1Imei, ByteBuffer.wrap(bArrA));
            }
            c(context);
        } catch (Throwable th) {
            ex.a(6, g, "", th);
        }
    }

    public synchronized void e() {
        if (i() != 0) {
            ex.a(6, g, "Error! Session with apiKey = " + k() + " was ended while getSessionCount() is not 0");
        }
        if (this.T) {
            ex.e(g, "Ending session");
            this.S = 0;
            if (this.Q.b()) {
                this.Q.a();
            }
            w();
            if (this.R != null) {
                this.R.d(k());
            }
            eh.a().b("Gender", (ei.a) this);
            eh.a().b("UserId", (ei.a) this);
            eh.a().b("Age", (ei.a) this);
            eh.a().b("LogEvents", (ei.a) this);
            eh.a().b("ContinueSessionMillis", (ei.a) this);
            this.P.getLooper().quit();
        }
    }

    private void u() {
        if (TextUtils.isEmpty(this.t)) {
            a(new fi() { // from class: com.flurry.sdk.cl.2
                @Override // com.flurry.sdk.fi
                public void a() {
                    cl.this.t = eo.a();
                }
            });
        }
    }

    private void a(long j) {
        for (cb cbVar : this.H) {
            if (cbVar.a() && !cbVar.b()) {
                cbVar.a(j);
            }
        }
    }

    private void v() {
        a(new fi() { // from class: com.flurry.sdk.cl.3
            @Override // com.flurry.sdk.fi
            public void a() {
                cj cjVarF = cl.this.f();
                cl.this.s.clear();
                cl.this.s.add(cjVarF);
                cl.this.x();
            }
        });
    }

    private void w() {
        a(new fi() { // from class: com.flurry.sdk.cl.4
            @Override // com.flurry.sdk.fi
            public void a() {
                cl.this.b(eg.a().b());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        boolean z;
        try {
            synchronized (this) {
                z = this.s.size() > 0;
            }
            if (z) {
                c(context);
            }
        } catch (Throwable th) {
            ex.a(6, g, "", th);
        }
    }

    synchronized cj f() {
        cj cjVar;
        ck ckVar = new ck();
        ckVar.a(this.n);
        ckVar.a(this.u);
        ckVar.b(this.w);
        ckVar.c(this.x);
        ckVar.b(l());
        ckVar.c(m());
        ckVar.a((int) this.A);
        ckVar.d(j());
        ckVar.a(E());
        ckVar.b(h());
        ckVar.a(this.D);
        ckVar.a(Long.valueOf(this.E));
        ckVar.a(q());
        ckVar.a(o());
        ckVar.a(this.I);
        ckVar.b(p());
        ckVar.c(this.L);
        try {
            cjVar = new cj(ckVar);
        } catch (IOException e2) {
            e2.printStackTrace();
            cjVar = null;
        }
        if (cjVar == null) {
            ex.e(g, "New session report wasn't created");
        }
        return cjVar;
    }

    public synchronized void g() {
        this.M++;
    }

    int h() {
        return this.M;
    }

    public synchronized void a(String str, Map<String, String> map, boolean z) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.v;
        String strA = fh.a(str);
        if (strA.length() != 0) {
            bx.a aVar = this.G.get(strA);
            if (aVar == null) {
                if (this.G.size() < f524a) {
                    bx.a aVar2 = new bx.a();
                    aVar2.f504a = 1;
                    this.G.put(strA, aVar2);
                    ex.e(g, "Event count started: " + strA);
                } else {
                    ex.e(g, "Too many different events. Event not counted: " + strA);
                }
            } else {
                aVar.f504a++;
                ex.e(g, "Event count incremented: " + strA);
            }
            if (this.B && this.H.size() < c && this.J < d) {
                Map<String, String> mapEmptyMap = map == null ? Collections.emptyMap() : map;
                if (mapEmptyMap.size() > b) {
                    ex.e(g, "MaxEventParams exceeded: " + mapEmptyMap.size());
                } else {
                    cb cbVar = new cb(B(), strA, mapEmptyMap, jElapsedRealtime, z);
                    if (cbVar.d() + this.J <= d) {
                        this.H.add(cbVar);
                        this.J = cbVar.d() + this.J;
                    } else {
                        this.J = d;
                        this.I = false;
                        ex.e(g, "Event Log size exceeded. No more event details logged.");
                    }
                }
            } else {
                this.I = false;
            }
        }
    }

    public synchronized void a(String str, Map<String, String> map) {
        for (cb cbVar : this.H) {
            if (cbVar.a(str)) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - this.v;
                if (map != null && map.size() > 0 && this.J < d) {
                    int iD = this.J - cbVar.d();
                    HashMap map2 = new HashMap(cbVar.c());
                    cbVar.a(map);
                    if (cbVar.d() + iD <= d) {
                        if (cbVar.c().size() > b) {
                            ex.e(g, "MaxEventParams exceeded on endEvent: " + cbVar.c().size());
                            cbVar.b(map2);
                        } else {
                            this.J = iD + cbVar.d();
                        }
                    } else {
                        cbVar.b(map2);
                        this.I = false;
                        this.J = d;
                        ex.e(g, "Event Log size exceeded. No more event details logged.");
                    }
                }
                cbVar.a(jElapsedRealtime);
                break;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0059  */
    public synchronized void a(String str, String str2, String str3, Throwable th) {
        boolean z;
        int i = 0;
        synchronized (this) {
            if (str == null) {
                z = false;
            } else if ("uncaught".equals(str)) {
                z = true;
            } else {
                z = false;
            }
            this.L++;
            if (this.K.size() < e) {
                ca caVar = new ca(C(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th);
                this.K.add(caVar);
                ex.e(g, "Error logged: " + caVar.c());
            } else if (z) {
                while (true) {
                    int i2 = i;
                    if (i2 >= this.K.size()) {
                        break;
                    }
                    ca caVar2 = this.K.get(i2);
                    if (caVar2.c() == null || "uncaught".equals(caVar2.c())) {
                        i = i2 + 1;
                    } else {
                        this.K.set(i2, new ca(C(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th));
                        break;
                    }
                }
            } else {
                ex.e(g, "Max errors logged. No more errors logged.");
            }
        }
    }

    private void c(Context context) {
        try {
            ex.a(3, g, "generating agent report");
            cc ccVar = new cc(this.m, this.n, D(), this.q, this.r, this.u, this.s, this.p, this.N.a(false), a(), System.currentTimeMillis());
            this.o = new ArrayList(this.s);
            if (ccVar != null && ccVar.a() != null) {
                ex.a(3, g, "generated report of size " + ccVar.a().length + " with " + this.s.size() + " reports.");
                a(ccVar.a());
                this.s.removeAll(this.o);
                this.o = null;
                x();
            } else {
                ex.e(g, "Error generating report");
            }
        } catch (Throwable th) {
            ex.a(6, g, "", th);
        }
    }

    private void a(byte[] bArr) {
        bx.a().o().b(bArr, this.m, "" + bx.a().b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void x() {
        if (!et.a(this.k)) {
            ex.e(g, "Error persisting report: could not create directory");
        } else {
            try {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(this.k));
                    cm cmVar = new cm();
                    cmVar.a(this.q);
                    cmVar.a(this.r);
                    cmVar.a(this.s);
                    cmVar.a(dataOutputStream, this.m, D());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Closeable] */
    public synchronized void d(Context context) {
        DataInputStream dataInputStream;
        if (this.k.exists()) {
            ?? r1 = g;
            ex.a(4, (String) r1, "loading persistent data: " + this.k.getAbsolutePath());
            try {
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(this.k));
                    try {
                        try {
                            cm cmVar = new cm();
                            this.l = cmVar.a(dataInputStream, this.m);
                            if (this.l) {
                                this.q = cmVar.a();
                                this.r = cmVar.c();
                                this.s = cmVar.b();
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        fh.a(dataInputStream);
                    } catch (Throwable th) {
                        th = th;
                        ex.c(g, "Error when loading persistent file", th);
                        fh.a(dataInputStream);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fh.a((Closeable) r1);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                dataInputStream = null;
            }
            try {
                if (!this.l) {
                    if (this.k.delete()) {
                        ex.a(3, g, "Deleted persistence file");
                    } else {
                        ex.a(6, g, "Cannot delete persistence file");
                    }
                }
            } catch (Throwable th4) {
                ex.a(6, g, "", th4);
            }
        } else {
            ex.a(4, g, "Agent cache file doesn't exist.");
        }
        if (!this.l) {
            this.q = false;
            this.r = this.u;
            this.l = true;
        }
    }

    private void y() {
        this.S++;
    }

    private void z() {
        this.S--;
    }

    int i() {
        return this.S;
    }

    private String A() {
        return ".flurryagent." + Integer.toString(this.m.hashCode(), 16);
    }

    private int B() {
        return this.h.incrementAndGet();
    }

    private int C() {
        return this.i.incrementAndGet();
    }

    String j() {
        return this.C == null ? "" : this.C;
    }

    private String D() {
        return this.t;
    }

    public String k() {
        return this.m;
    }

    public String l() {
        return this.y;
    }

    public String m() {
        return this.z;
    }

    private Location E() {
        return bx.a().n();
    }

    @Override // com.flurry.sdk.cn.b
    public void n() {
        e();
    }

    List<cb> o() {
        return this.H;
    }

    List<ca> p() {
        return this.K;
    }

    Map<String, bx.a> q() {
        return this.G;
    }
}
