package com.topfreegames.bikerace.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.c.a.a.c;
import com.c.a.f;
import com.c.a.g;
import com.c.a.h;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.at;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.t;
import com.topfreegames.bikerace.z;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: InterstitialsManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends com.c.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f1237a;
    private long d;
    private bb e;
    private z f;
    private SharedPreferences g;
    private t h;
    private int b = 0;
    private int c = 0;
    private boolean i = true;
    private boolean j = false;
    private b k = null;
    private g l = new g() { // from class: com.topfreegames.bikerace.g.a.1
        @Override // com.c.a.g
        public void d(String str) {
        }

        @Override // com.c.a.g
        public void c(String str) {
            try {
                a.this.d = System.currentTimeMillis();
                if (a.this.k != null) {
                    a.this.k.a();
                }
            } catch (Error e) {
                Log.d("Interstitial", Log.getStackTraceString(e));
                a.this.h.a("Interstitial", "onClose", e);
                throw e;
            } catch (Exception e2) {
                Log.d("Interstitial", Log.getStackTraceString(e2));
                a.this.h.a("Interstitial", "onClose", e2);
            }
        }

        @Override // com.c.a.g
        public void b(String str) {
        }

        @Override // com.c.a.g
        public void a(String str) {
        }
    };
    private f m = new f() { // from class: com.topfreegames.bikerace.g.a.2
        @Override // com.c.a.f
        public boolean a(String str) {
            if (a.this.s()) {
                if (!str.equals("SinglePlayer_EndLevel")) {
                    if (!str.equals("MultiPlayer_Menu")) {
                        if (str.equals("WorldSelection_Menu")) {
                            return a.this.r();
                        }
                    } else {
                        return a.this.q();
                    }
                } else {
                    return a.this.p();
                }
            }
            return false;
        }

        @Override // com.c.a.f
        public boolean a() {
            return !ap.l() && com.topfreegames.bikerace.n.a.a().a("kABTEST_chart") == 1 && a.this.e.e() && a.this.e.l() > 0;
        }

        @Override // com.c.a.f
        public boolean a(h hVar) {
            return a.this.e.a(hVar.a());
        }

        @Override // com.c.a.f
        public boolean a(h hVar, String str) {
            return true;
        }
    };

    private a(Context context) {
        this.d = 0L;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) context.getApplicationContext();
        this.e = bikeRaceApplication.a(false);
        this.f = bikeRaceApplication.a();
        this.h = bikeRaceApplication.d();
        this.g = context.getSharedPreferences("com.topfreegames.bikerace.interstitial", 0);
        this.d = System.currentTimeMillis();
        o();
    }

    public static a g() {
        a aVar;
        synchronized (a.class) {
            if (f1237a == null) {
                throw new IllegalStateException("Call init() first!");
            }
            aVar = f1237a;
        }
        return aVar;
    }

    public static void a(Context context) {
        if (f1237a == null) {
            synchronized (a.class) {
                if (f1237a == null) {
                    f1237a = new a(context);
                }
            }
        }
    }

    public void h() {
        try {
            c("SinglePlayer_EndLevel");
        } catch (Error e) {
            Log.e("Interstitial", Log.getStackTraceString(e));
            throw e;
        } catch (Exception e2) {
            Log.e("Interstitial", Log.getStackTraceString(e2));
        }
    }

    public void i() {
        try {
            c("MultiPlayer_Menu");
        } catch (Error e) {
            Log.e("Interstitial", Log.getStackTraceString(e));
            throw e;
        } catch (Exception e2) {
            Log.e("Interstitial", Log.getStackTraceString(e2));
        }
    }

    public void j() {
        try {
            c("WorldSelection_Menu");
        } catch (Error e) {
            Log.e("Interstitial", Log.getStackTraceString(e));
            throw e;
        } catch (Exception e2) {
            Log.e("Interstitial", Log.getStackTraceString(e2));
        }
    }

    public void k() {
        try {
            super.b("WorldSelection_Menu");
            super.b("SinglePlayer_EndLevel");
            super.b("MultiPlayer_Menu");
        } catch (Error e) {
            Log.e("Interstitial", Log.getStackTraceString(e));
            throw e;
        } catch (Exception e2) {
            Log.e("Interstitial", Log.getStackTraceString(e2));
        }
    }

    public void l() {
        try {
            super.b("SinglePlayer_EndLevel");
        } catch (Error e) {
            Log.e("Interstitial", Log.getStackTraceString(e));
            throw e;
        } catch (Exception e2) {
            Log.e("Interstitial", Log.getStackTraceString(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p() {
        return this.b >= this.e.p() && System.currentTimeMillis() - this.d > this.e.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean q() {
        return this.c >= this.e.n() && System.currentTimeMillis() - this.d > this.e.o() && this.j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r() {
        return this.b >= this.e.p() && System.currentTimeMillis() - this.d > this.e.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean s() {
        return System.currentTimeMillis() - t() > this.e.m() && this.i && this.f.c() && this.h.q() > 1 && this.e.e() && this.f.v() >= this.e.u() && this.h.s();
    }

    public void m() {
        this.b++;
    }

    public void n() {
        this.c++;
    }

    public void o() {
        ArrayList<String> arrayListT = this.e.t();
        ArrayList<h> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        if (this.e.e() && this.f.v() >= this.e.u() && com.topfreegames.bikerace.n.a.a().a("kABTEST_chart") == 1) {
            if (Build.VERSION.SDK_INT >= 14) {
                arrayList2.add(new c());
            }
            arrayList2.add(new com.c.a.a.b(at.a(), at.b()));
            arrayList2.add(new com.c.a.a.a());
            int size = arrayListT.size();
            int size2 = arrayList2.size();
            for (int i = 0; i < size; i++) {
                for (int i2 = 0; i2 < size2; i2++) {
                    h hVar = (h) arrayList2.get(i2);
                    String str = arrayListT.get(i);
                    String strTrim = hVar.a().toLowerCase(Locale.US).trim();
                    if (strTrim != null && str != null && strTrim.equals(str)) {
                        arrayList.add(hVar);
                        break;
                    }
                }
            }
        }
        a(arrayList, this.e.r() ? com.c.a.b.PRIORITY_LIST : com.c.a.b.ROUND_ROBIN, this.e.s(), this.l, this.m);
    }

    private long t() {
        if (this.g != null) {
            return this.g.getLong("InstallDate", -1L);
        }
        return -1L;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void a(b bVar) {
        this.k = bVar;
    }

    public void b(boolean z) {
        this.i = z;
    }
}
