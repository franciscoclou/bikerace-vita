package com.topfreegames.bikerace;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Process;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f implements GLSurfaceView.Renderer {
    private static /* synthetic */ int[] af;
    private g F;
    private ai J;
    private SharedPreferences W;
    private z X;
    private v Y;
    private t Z;
    private com.topfreegames.bikerace.j.b aa;
    private Context i;
    private Handler j;
    private bb l;
    private SparseArray<com.topfreegames.bikerace.h.x> u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.topfreegames.engine.a.b f1221a = new com.topfreegames.engine.a.b(0.0f, -4.0f);
    private static final com.topfreegames.engine.a.b b = new com.topfreegames.engine.a.b(0.0f, -3.0f);
    private static final com.topfreegames.engine.a.b c = new com.topfreegames.engine.a.b(0.0f, -6.0f);
    private static final com.topfreegames.engine.a.b d = new com.topfreegames.engine.a.b(0.0f, -4.2f);
    private static final com.topfreegames.engine.a.b e = new com.topfreegames.engine.a.b(0.0f, -4.3f);
    private static final com.topfreegames.engine.a.b f = new com.topfreegames.engine.a.b(0.0f, -4.5f);
    private static final com.topfreegames.engine.a.b g = new com.topfreegames.engine.a.b(0.0f, -5.0f);
    private static final com.topfreegames.engine.a.b h = new com.topfreegames.engine.a.b(0.0f, -5.8f);
    private boolean k = false;
    private ConcurrentLinkedQueue<n> m = new ConcurrentLinkedQueue<>();
    private m n = null;
    private k o = k.WAITING_START;
    private l p = l.SINGLE_PLAYER;
    private a q = null;
    private a r = null;
    private com.topfreegames.bikerace.h.a s = null;
    private com.topfreegames.bikerace.h.x t = null;
    private float v = 0.0f;
    private float w = 1.0f;
    private int x = -1;
    private int y = -2;
    private int z = -1;
    private int A = -2;
    private float B = 0.0f;
    private long C = 0;
    private boolean D = false;
    private boolean E = false;
    private com.topfreegames.engine.b.d G = null;
    private ac H = null;
    private boolean I = false;
    private com.topfreegames.engine.a.c K = new com.topfreegames.engine.a.c();
    private com.topfreegames.engine.a.b L = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b M = new com.topfreegames.engine.a.b();
    private b N = b.IDLE;
    private int O = -1;
    private int P = -1;
    private com.topfreegames.engine.a.b Q = new com.topfreegames.engine.a.b();
    private long R = -1;
    private long S = 0;
    private long T = 0;
    private long U = 0;
    private boolean V = false;
    private com.topfreegames.engine.a.b ab = new com.topfreegames.engine.a.b();
    private RectF ac = new RectF();
    private com.topfreegames.bikerace.h.b ad = new com.topfreegames.bikerace.h.b();
    private RectF ae = new RectF();

    static /* synthetic */ int[] n() {
        int[] iArr = af;
        if (iArr == null) {
            iArr = new int[c.valuesCustom().length];
            try {
                iArr[c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e36) {
            }
            af = iArr;
        }
        return iArr;
    }

    public f(Context context, Handler handler, z zVar, v vVar, t tVar) {
        this.i = null;
        this.j = null;
        this.l = null;
        this.u = null;
        this.F = null;
        this.J = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (zVar == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (vVar == null) {
            throw new IllegalArgumentException("Audio cannot be null!");
        }
        if (tVar == null) {
            throw new IllegalArgumentException("Analytics cannot be null!");
        }
        try {
            this.i = context.getApplicationContext();
            this.J = new ai(context);
            this.j = handler;
            this.X = zVar;
            this.u = new SparseArray<>();
            this.Y = vVar;
            this.Z = tVar;
            this.aa = new com.topfreegames.bikerace.j.b(context);
            this.F = new g(context);
            this.W = context.getSharedPreferences("com.topfreegames.bikerace.perf", 0);
            this.l = ((BikeRaceApplication) context.getApplicationContext()).a(false);
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            tVar.a(getClass().getName(), "constructor", e2);
        }
    }

    public int a() {
        if (this.z < 0) {
            this.Z.a(getClass().getName(), "Level was not previosly set!");
        }
        return this.z + 1;
    }

    public String b() {
        return this.n.e();
    }

    public q c() {
        return this.n.f();
    }

    public int d() {
        return this.n.g();
    }

    public int e() {
        if (this.x < 0) {
            this.Z.a(getClass().getName(), "World was not previously set!");
        }
        return this.x + 1;
    }

    public void a(int i, int i2, com.topfreegames.bikerace.multiplayer.l lVar, com.topfreegames.bikerace.multiplayer.d dVar, String str) {
        a(i, i2, lVar, dVar, str, 0, false);
    }

    public void a(int i, int i2, com.topfreegames.bikerace.multiplayer.l lVar, com.topfreegames.bikerace.multiplayer.d dVar, String str, int i3, boolean z) {
        if (lVar == null) {
            throw new IllegalArgumentException("Multiplayer Data cannot be null!");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("Multiplayer Listener cannot be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("UserName cannot be null!");
        }
        this.p = l.MULTI_PLAYER;
        this.n = new i(this, lVar, dVar, i3, com.topfreegames.bikerace.m.f.a(str), z);
        x();
        c(i, i2);
        com.topfreegames.bikerace.c.d.a().b();
    }

    public void a(int i, int i2) {
        this.p = l.SINGLE_PLAYER;
        this.n = new j(this, null);
        x();
        c(i, i2);
    }

    public void a(MotionEvent motionEvent) {
        if (this.q != null && this.o == k.RUNNING) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.O = motionEvent.getPointerId(0);
                    break;
                case 1:
                    this.N = b.IDLE;
                    this.O = -1;
                    return;
                case 2:
                    break;
                case 3:
                case 4:
                default:
                    return;
                case 5:
                    int action = (motionEvent.getAction() & 65280) >> 8;
                    this.P = this.O;
                    this.O = motionEvent.getPointerId(action);
                    if (this.O >= 0) {
                        a(motionEvent.getX(action), motionEvent.getY(action));
                        return;
                    }
                    return;
                case 6:
                    if (motionEvent.getPointerId((motionEvent.getAction() & 65280) >> 8) == this.O) {
                        this.O = this.P;
                        a(this.M.f1553a, this.M.b);
                        this.P = -1;
                        return;
                    }
                    return;
            }
            int iFindPointerIndex = motionEvent.findPointerIndex(this.O);
            if (iFindPointerIndex >= 0) {
                a(motionEvent.getX(iFindPointerIndex), motionEvent.getY(iFindPointerIndex));
                return;
            }
            return;
        }
        if (this.o == k.PAUSED) {
            this.N = b.IDLE;
            this.O = -1;
        }
    }

    public boolean f() {
        return this.p == l.SINGLE_PLAYER;
    }

    public boolean a(boolean z) {
        if (z) {
            this.Z.a(e(), a());
        } else {
            this.Z.b(e(), a());
        }
        return this.n.i();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        try {
            if (this.V) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = jCurrentTimeMillis - this.R;
                if (j < this.S) {
                    Thread.sleep(this.S - j);
                }
                this.R = jCurrentTimeMillis;
            }
            p();
            if (this.k) {
                w();
            }
        } catch (Error e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            this.Z.a(getClass().getName(), "onDrawFrame", e2);
            throw e2;
        } catch (Exception e3) {
            if (ap.d()) {
                e3.printStackTrace();
            }
            this.Z.a(getClass().getName(), "onDrawFrame", e3);
            af.a(this.j);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        try {
            if (this.G != null) {
                this.G.a(new com.topfreegames.engine.a.b(i, i2));
                this.G.a(new Rect(0, 0, i, i2));
            }
        } catch (Exception e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            this.Z.a(getClass().getName(), "onSurfaceChanged", e2);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        float f2;
        try {
            u();
            this.H = new ac(this.i, this.G);
            this.k = true;
            if (ap.b()) {
                int iW = this.W.getInt("group", -1);
                if (iW < 0) {
                    iW = this.X.p() > 0 ? 0 : this.l.w();
                    this.W.edit().putInt("group", iW);
                }
                this.V = iW >= this.l.w();
                if (this.V) {
                    float refreshRate = ((WindowManager) this.i.getSystemService("window")).getDefaultDisplay().getRefreshRate();
                    ((BikeRaceApplication) this.i.getApplicationContext()).d().a(refreshRate);
                    if (refreshRate <= 20.0f) {
                        f2 = 40.0f;
                    } else {
                        f2 = refreshRate > 60.0f ? 60.0f : refreshRate;
                    }
                    this.S = (long) (1000.0f / f2);
                    this.S = this.W.getLong("perf", this.S);
                }
            }
            this.y = -2;
            this.A = -2;
            Process.setThreadPriority(-1);
        } catch (Error e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            this.Z.a(getClass().getName(), "onSurfaceCreated", e2);
            af.a(this.j);
            o();
            throw e2;
        } catch (Exception e3) {
            if (ap.d()) {
                e3.printStackTrace();
            }
            this.Z.a(getClass().getName(), "onSurfaceCreated", e3);
            af.a(this.j);
            o();
        }
    }

    public void g() {
        this.n.m();
    }

    public void a(GLSurfaceView gLSurfaceView) {
        if (gLSurfaceView == null) {
            throw new IllegalArgumentException("GL Surface cannot be null!");
        }
        if (this.n != null) {
            this.n.r();
        }
        new r(this, null).a();
        gLSurfaceView.onPause();
    }

    public void b(GLSurfaceView gLSurfaceView) {
        if (gLSurfaceView == null) {
            throw new IllegalArgumentException("GL Surface cannot be null!");
        }
        this.R = -1L;
        this.T = 0L;
        this.U = 0L;
        gLSurfaceView.onResume();
    }

    public void c(GLSurfaceView gLSurfaceView) {
        if (gLSurfaceView == null) {
            throw new IllegalArgumentException("GL Surface cannot be null!");
        }
        this.Z.d(e(), a(), !f());
        if (this.n != null) {
            this.n.o();
        }
        gLSurfaceView.queueEvent(new Runnable() { // from class: com.topfreegames.bikerace.f.1
            @Override // java.lang.Runnable
            public void run() {
                f.this.o();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.k = false;
        if (this.H != null) {
            this.H.b();
            this.H = null;
        }
        if (this.G != null) {
            this.G.b();
            this.G = null;
        }
        this.j = null;
        this.i = null;
        this.Y.h();
        System.gc();
    }

    public void h() {
        a(new h(this, null));
    }

    private void b(int i, int i2) {
        if (!com.topfreegames.bikerace.h.y.a(i)) {
            throw new IllegalArgumentException("Invalid world value: " + i);
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("Invalid level value: " + i2);
        }
    }

    public void b(boolean z) {
        if (z) {
            this.Z.b(e(), a(), !f());
        }
        com.topfreegames.bikerace.c.d.a().a(this.s.b());
        a(new s(this, null));
    }

    public void a(Context context, Handler handler) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        q();
        this.i = context;
        this.j = handler;
        this.o = k.WAITING_START;
        this.q = null;
        this.v = 0.0f;
        this.w = 1.0f;
        this.x = -1;
        this.z = -1;
        this.y = -2;
        this.A = -2;
        this.B = 0.0f;
        this.C = 0L;
        this.D = false;
        this.E = false;
        this.N = b.IDLE;
        this.k = false;
    }

    public void i() {
        this.n.k();
    }

    public void j() {
        this.o = k.RUNNING;
        this.J.a();
        this.n.l();
    }

    public void k() {
        this.n.n();
        this.J.a();
    }

    public void l() {
        if (this.H != null) {
            this.H.c();
        }
    }

    public boolean m() {
        return this.H.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(n nVar) {
        if (nVar != null) {
            Iterator<n> it = this.m.iterator();
            Class<?> cls = nVar.getClass();
            boolean z = false;
            while (it.hasNext()) {
                if (it.next().getClass().equals(cls)) {
                    z = true;
                }
            }
            if (!z) {
                this.m.add(nVar);
            }
        }
    }

    private void p() {
        Iterator<n> it = this.m.iterator();
        while (it.hasNext()) {
            it.next().a();
            it.remove();
        }
    }

    private void q() {
        this.m.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r() {
        com.topfreegames.engine.a.b bVar = this.ab;
        bVar.a(this.q.e());
        com.topfreegames.engine.a.b bVarD = this.s.d();
        return bVar.f1553a > bVarD.f1553a && bVar.b > bVarD.b;
    }

    private boolean s() {
        com.topfreegames.engine.a.b bVar = this.ab;
        bVar.a(this.q.e());
        this.ac.set(this.s.a().a());
        return bVar.f1553a < this.ac.left - 1.0f || bVar.f1553a > this.ac.right + 1.0f || bVar.b < this.ac.bottom - 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean t() {
        return this.v > 180.0f;
    }

    private void u() {
        this.G = new com.topfreegames.engine.b.f();
        this.G.a();
        this.G.a(new com.topfreegames.engine.b.c(0.0f, 0.0f, 0.0f));
        com.topfreegames.engine.b.e eVar = new com.topfreegames.engine.b.e();
        eVar.a();
        this.G.b(eVar);
        Display defaultDisplay = ((WindowManager) this.i.getSystemService("window")).getDefaultDisplay();
        this.I = this.G.h() > 1024 && (defaultDisplay.getWidth() > 1024 || defaultDisplay.getHeight() > 1024);
    }

    private void c(int i, int i2) {
        b(i, i2);
        a(new o(this, i - 1, i2 - 1, false));
    }

    private void v() {
        this.J.a(this.K);
        if (this.K.f1554a > 0.5f) {
            this.K.f1554a = 0.5f;
        } else if (this.K.f1554a < -0.5f) {
            this.K.f1554a = -0.5f;
        }
    }

    private void w() {
        if (this.o != k.HELP) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = jCurrentTimeMillis - this.C;
            this.C = jCurrentTimeMillis;
            long j2 = (long) (1.2f * this.S);
            if (!this.V || j <= j2) {
                j2 = j;
            }
            float f2 = j2 / 1000.0f;
            float f3 = f2 <= 0.05f ? f2 : 0.05f;
            if (this.o == k.RUNNING) {
                a(j);
                this.v += f3;
                v();
                if (!this.q.h()) {
                    this.q.a(this.N);
                } else if (this.v - this.B > 0.5f) {
                    this.n.d();
                    y();
                }
                this.n.b(f3);
                if (this.n.b()) {
                    this.n.c();
                } else if (s()) {
                    this.n.d();
                    y();
                }
                if (this.q != null && this.s != null && this.s.b() != null) {
                    com.topfreegames.bikerace.c.d dVarA = com.topfreegames.bikerace.c.d.a();
                    for (com.topfreegames.bikerace.c.a aVar : this.s.b()) {
                        if (dVarA.a(this.q.f812a.f1561a, aVar, f())) {
                            aVar.a(f());
                        }
                    }
                }
            }
            this.n.a(f3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f2) {
        int i = ((int) (f2 / 0.003f)) + 1;
        float f3 = f2 / i;
        float f4 = f3 <= 0.003f ? f3 : 0.003f;
        this.D = false;
        this.E = false;
        if (ap.c()) {
            this.q.a(this.ae);
            this.s.a().a(this.ae, this.ad);
        } else {
            this.ad = this.s.a();
        }
        int iD = this.ad.d();
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            this.q.a(this.Q, f4);
            this.q.a(f4);
            for (int i3 = 0; i3 < iD; i3++) {
                d dVarA = this.ad.a(i3);
                if (ap.c() || this.q.a(dVarA)) {
                    float fA = this.q.a(dVarA, f4);
                    if (fA > 0.0f) {
                        this.D = true;
                        if (z) {
                            this.Y.a(fA, this.w);
                            z = false;
                        }
                    }
                    float fC = this.q.c(dVarA, f4);
                    if (fC > 0.0f) {
                        this.E = true;
                        if (z) {
                            this.Y.a(fC, this.w);
                            z = false;
                        }
                    }
                    float fB = this.q.b(dVarA, f4);
                    if (fB > 0.0f && z) {
                        this.Y.a(fB, this.w);
                        z = false;
                    }
                }
            }
            this.q.b(f4);
            if (!this.q.h()) {
                this.q.a(this.K.f1554a, f4, this.E);
            }
        }
        for (int i4 = 0; i4 < iD; i4++) {
            if (this.q.b(this.ad.a(i4))) {
                this.J.b();
                this.Y.d();
                this.q.a(b.CRASHED);
                this.B = this.v;
                break;
            }
        }
        this.w = (f4 * (((this.E || this.D) ? 1.0f : 0.0f) - this.w) * 3.0f) + this.w;
    }

    private void a(float f2, float f3) {
        this.M.a(this.L);
        this.L.a(f2, f3);
        if (this.G == null || this.o == k.PAUSED) {
            this.N = b.IDLE;
        } else if (this.L.f1553a / this.G.c().f1553a <= 0.5f) {
            this.N = b.BRAKING;
        } else {
            this.N = b.ACCELERATING;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f2, a aVar) {
        float f3;
        float f4;
        float f5 = 0.09f;
        if (aVar == null) {
            throw new IllegalArgumentException("Bike cannot be null!");
        }
        if (this.o == k.RUNNING) {
            f3 = 2.0f;
            if (f2 < 0.16666667f) {
                f4 = 6.0f;
            } else {
                f4 = 1.0f / f2;
            }
        } else {
            f3 = 4.0f;
            if (f2 < 0.25f) {
                f4 = 4.0f;
            } else {
                f4 = 1.0f / f2;
            }
        }
        int iE = e();
        int iA = a();
        if (iE != 3 || iA != 3) {
            if (iE == 10 && iA == 1) {
                f5 = 0.29f;
            } else if (iE == 10 && iA == 2) {
                f5 = 0.29f;
            } else if (iE == 10 && iA == 4) {
                f5 = 0.49f;
            } else if (iE == 10 && iA == 5) {
                f5 = 0.59f;
            } else if (iE == 10 && iA == 6) {
                f5 = 0.49f;
            } else if (iE == 10 && iA == 7) {
                f5 = 0.29f;
            } else if (iE != 10 || iA != 8) {
                f5 = 0.9f;
            }
        }
        this.H.a(aVar.c(), aVar.d(), f5, f2, f4, f3);
    }

    private void x() {
        if (this.Y != null) {
            this.Y.a(this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(c cVar) {
        switch (n()[cVar.ordinal()]) {
            case 3:
                this.Q = b;
                return;
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                this.Q = c;
                return;
            case 23:
                this.Q = h;
                return;
            case 24:
                this.Q = g;
                return;
            case 25:
                this.Q = d;
                return;
            case 29:
                this.Q = f;
                return;
            case 34:
                this.Q = e;
                break;
        }
        this.Q = f1221a;
    }

    private void y() {
        com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(this.i);
        fVarA.c("AchievDieXTimes");
        if (this.x == 998) {
            fVarA.c("AchievUserCreatedLevel");
        }
        if (this.F != null && this.F.e() > 9.5f) {
            fVarA.c("AchievHighSpeed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        com.topfreegames.bikerace.c.d dVarA = com.topfreegames.bikerace.c.d.a();
        dVarA.a(this.s.b(), z);
        com.topfreegames.bikerace.a.f.a(this.i).a("AchievEasterEggs", dVarA.a(com.topfreegames.bikerace.c.c.EASTER_EGG));
    }

    private void a(long j) {
        long j2 = 16;
        if (this.V) {
            this.U += j;
            this.T++;
            if (this.T > 600) {
                long j3 = this.U / this.T;
                if (j3 >= 16) {
                    j2 = j3 > 50 ? 50L : j3;
                }
                if (j2 > ((long) (1.2f * this.S)) || j2 < ((long) (0.8f * this.S))) {
                    this.S = j2;
                    this.W.edit().putLong("perf", j2).commit();
                }
                this.T = 0L;
                this.U = 0L;
            }
        }
    }
}
