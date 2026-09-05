package com.topfreegames.bikerace.multiplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.IBinder;
import com.amazon.aws.tvmclient.AmazonClientManager;
import com.amazon.aws.tvmclient.PropertyLoader;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.bg;
import com.topfreegames.bikerace.bi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o implements com.topfreegames.e.a.f, com.topfreegames.e.a.i {
    private static /* synthetic */ int[] w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AmazonClientManager f1316a;
    private com.topfreegames.e.a.a d;
    private c e;
    private com.topfreegames.bikerace.l.a.c n;
    private SharedPreferences o;
    private Context p;
    private SharedPreferences q;
    private ServiceConnection r;
    private b t;
    private q u;
    private bb v;
    private int b = 0;
    private p c = p.NOT_LOGGED_IN;
    private g f = null;
    private f g = null;
    private s h = new s(this, null);
    private e i = null;
    private boolean j = false;
    private String k = null;
    private String l = null;
    private boolean m = false;
    private MultiplayerNotificationService s = null;

    static /* synthetic */ int[] t() {
        int[] iArr = w;
        if (iArr == null) {
            iArr = new int[v.valuesCustom().length];
            try {
                iArr[v.GAME_START_AGAINST_RANDOM_USER.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[v.GAME_START_VIA_LINK.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[v.GAME_START_VIA_REGULAR_SELECTION.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            w = iArr;
        }
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public o(Context context) {
        this.d = null;
        this.e = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.u = null;
        PropertyLoader propertyLoader = PropertyLoader.getInstance();
        this.f1316a = new AmazonClientManager(context.getSharedPreferences("com.topfreegames.bikerace.amazonClient", 0));
        this.f1316a.clearToken();
        com.topfreegames.bikerace.l.a.a aVar = new com.topfreegames.bikerace.l.a.a(propertyLoader.getGameSessionsTableName(), this.f1316a.ddb());
        com.topfreegames.bikerace.l.a.c cVar = new com.topfreegames.bikerace.l.a.c(propertyLoader.getUsersTableName(), this.f1316a.ddb());
        com.topfreegames.bikerace.l.b.a aVar2 = new com.topfreegames.bikerace.l.b.a(context);
        aa aaVar = new aa(aVar, cVar, new x(context.getApplicationContext(), 0 == true ? 1 : 0), new y(null), ((BikeRaceApplication) context.getApplicationContext()).d());
        aaVar.a(new com.topfreegames.bikerace.l.b.c(aVar2));
        aaVar.a(new com.topfreegames.bikerace.l.b.b(aVar2));
        this.d = com.topfreegames.e.a.a.b();
        this.e = c.a(context);
        this.u = new q(this, aaVar);
        this.v = ((BikeRaceApplication) context).a(false);
        this.n = cVar;
        this.o = context.getSharedPreferences("com.topfreegames.bikerace.login", 0);
        this.p = context.getApplicationContext();
        this.t = new b(this, this.p, ((BikeRaceApplication) context).a(false));
        this.q = context.getSharedPreferences("com.topfreegames.bikerace.links", 0);
        this.r = new ServiceConnection() { // from class: com.topfreegames.bikerace.multiplayer.o.1
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                o.this.s = null;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                o.this.s = ((z) iBinder).a();
            }
        };
        context.bindService(new Intent(context, (Class<?>) MultiplayerNotificationService.class), this.r, 1);
        com.topfreegames.bikerace.push.e.a(new t(this, 0 == true ? 1 : 0));
        l();
    }

    public void a(boolean z) {
        ((y) this.u.b().a()).a(z);
    }

    public boolean a() {
        return this.c == p.LOGGED_IN_AS_FACEBOOK_USER;
    }

    public void b() {
        List<String> listM = m();
        listM.add(g());
        this.d.a(listM, g(), this);
    }

    public void a(f fVar) {
        if (this.g == fVar) {
            this.g = null;
        }
    }

    public void a(g gVar) {
        if (this.f == gVar) {
            this.f = null;
        }
    }

    public AmazonClientManager c() {
        return this.f1316a;
    }

    public List<l> d() {
        return this.u.d();
    }

    public final int e() {
        return this.u.a();
    }

    public l a(String str) {
        for (l lVar : this.u.d()) {
            if (lVar.b().equals(str)) {
                return lVar;
            }
        }
        return null;
    }

    public s f() {
        return this.h;
    }

    public String g() {
        return this.o.getString("id", "");
    }

    public String h() {
        return this.o.getString("name", "");
    }

    public Bitmap i() {
        com.topfreegames.e.l lVarA;
        String strG = g();
        if (this.c != p.LOGGED_IN_AS_FACEBOOK_USER || (lVarA = this.d.a(strG, true)) == null) {
            return null;
        }
        return lVarA.c();
    }

    public com.topfreegames.bikerace.b.b b(String str) {
        return this.u.c(str);
    }

    public com.topfreegames.bikerace.b.b j() {
        String strG = g();
        if (strG == null || "".equals(strG)) {
            return null;
        }
        return b(strG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.o.2
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.b.b bVarJ = o.this.j();
                if (bVarJ != null) {
                    int iIntValue = bVarJ.e() != null ? bVarJ.e().intValue() : 0;
                    int i2 = i + iIntValue;
                    if (iIntValue == 0 && bVarJ.b().intValue() > i2) {
                        int iIntValue2 = bVarJ.b().intValue() - i2;
                        bVarJ.c(Integer.valueOf(iIntValue2));
                        o.this.u.c(bVarJ.c(), iIntValue2);
                    }
                    bVarJ.b(Integer.valueOf(i2));
                    o.this.u.a(bVarJ.c(), i2);
                    ((BikeRaceApplication) o.this.p).a().e(i2);
                }
            }
        }).start();
    }

    public int k() {
        if (bg.a(bi.FAKE_NUM_MULTI_WINS)) {
            return bg.b(bi.FAKE_NUM_MULTI_WINS);
        }
        com.topfreegames.bikerace.b.b bVarJ = j();
        if (bVarJ != null) {
            if (bVarJ.b() != null) {
                return bVarJ.b().intValue();
            }
            return 0;
        }
        return ((BikeRaceApplication) this.p).a().m();
    }

    @Override // com.topfreegames.e.a.f
    public void a(boolean z, boolean z2) {
        if (z) {
            this.d.a(false, new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.multiplayer.o.3
                @Override // com.topfreegames.e.a.m
                public void a(final com.topfreegames.e.l lVar, boolean z3) {
                    if (lVar != null && z3) {
                        if (o.this.c == p.LOGGED_IN_AS_GUEST) {
                            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.o.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Set<String> setD;
                                    boolean z4 = false;
                                    com.topfreegames.bikerace.b.b bVarD = o.this.u.d(lVar.a());
                                    if (bVarD != null && ((setD = bVarD.d()) == null || setD.size() <= 0)) {
                                        z4 = true;
                                    }
                                    if (z4) {
                                        o.this.u.a(o.this.e.a(), lVar.a());
                                    }
                                    o.this.c(lVar.a(), lVar.b());
                                }
                            }).start();
                            return;
                        } else {
                            o.this.c(lVar.a(), lVar.b());
                            return;
                        }
                    }
                    if (z3) {
                        o.this.d.b(o.this.p);
                    }
                    o.this.u();
                }
            }, (Object) null);
            return;
        }
        if (z2) {
            c(this.p.getString(2131100002));
        }
        u();
    }

    @Override // com.topfreegames.e.a.f
    public void b(boolean z) {
        u();
    }

    @Override // com.topfreegames.e.a.i
    public void a(String str, com.topfreegames.e.i iVar) {
        if (str != null) {
            if (str.startsWith("b")) {
                a(str, this.e.c(), v.GAME_START_AGAINST_RANDOM_USER);
                return;
            } else {
                this.d.a(str, false, new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.multiplayer.o.4
                    @Override // com.topfreegames.e.a.m
                    public void a(com.topfreegames.e.l lVar, boolean z) {
                        if (lVar != null && !z) {
                            o.this.a(lVar.a(), lVar.b(), v.GAME_START_AGAINST_RANDOM_USER);
                        }
                    }
                }, (Object) null);
                return;
            }
        }
        if (iVar == com.topfreegames.e.i.EXCEEDED_MAX_DAILY_REQUESTS) {
            c(this.p.getString(2131100003));
            a((l) null, v.GAME_START_AGAINST_RANDOM_USER);
        } else {
            c(this.p.getString(2131100004));
            a((l) null, v.GAME_START_AGAINST_RANDOM_USER);
        }
    }

    public void a(Activity activity) {
        this.d.a((com.topfreegames.e.a.f) this);
        this.d.a(activity, true);
    }

    public void b(Activity activity) {
        this.d.a((com.topfreegames.e.a.f) this);
        this.d.b(activity);
    }

    public void c(Activity activity) {
        this.d.a((com.topfreegames.e.a.f) this);
        this.d.a(activity, false);
    }

    public void a(Activity activity, String str, String str2) {
        this.j = true;
        this.k = str;
        this.l = str2;
        this.d.a((com.topfreegames.e.a.f) this);
        this.d.a(activity, false);
    }

    public void d(Activity activity) {
        this.d.a(activity, this);
    }

    public void l() {
        this.b = ((BikeRaceApplication) this.p).a().p();
    }

    public void b(f fVar) {
        this.g = fVar;
    }

    public void a(String str, String str2) {
        if (ap.d()) {
            System.out.println("\tMM -> userid = " + str);
        }
        if (str != null && !str.equals("") && str2 != null && !str2.equals("")) {
            this.n.c(g(), str2);
        }
    }

    public void b(g gVar) {
        this.f = gVar;
    }

    public void c(boolean z) {
        if (!((BikeRaceApplication) this.p.getApplicationContext()).e()) {
            z = false;
        }
        this.u.a(z);
    }

    public void a(String str, v vVar) {
        com.topfreegames.e.l lVarA = this.d.a(str, true);
        String strB = null;
        if (lVarA != null) {
            strB = lVarA.b();
        }
        if (strB == null) {
            strB = this.e.b();
        }
        a(str, strB, vVar);
    }

    public void a(String str, String str2, v vVar) {
        new Thread(new w(this, str, str2, true, vVar)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public l a(l lVar) {
        if (lVar != null) {
            synchronized (lVar) {
                i iVarA = h.a(this.u.d(), this.u.c(lVar.l()), this.u.c(lVar.f()), lVar.y(), lVar.z(), this.b);
                lVar.a(iVarA.b, iVarA.f1312a);
            }
            return lVar;
        }
        return null;
    }

    public void a(e eVar) {
        this.i = eVar;
    }

    public void b(String str, String str2) {
        if (str != null && !str.equals("") && str2 != null && !str2.equals("")) {
            Executors.newCachedThreadPool().submit(new u(this, str, str2));
        }
    }

    public void c(String str) {
        if (this.s != null) {
            this.s.a(str);
        }
    }

    public List<String> m() {
        ArrayList arrayList = null;
        com.topfreegames.bikerace.b.b bVarJ = j();
        if (bVarJ != null && bVarJ.d() != null) {
            arrayList = new ArrayList(bVarJ.d());
        }
        if (arrayList == null) {
            return new ArrayList();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, boolean z) {
        boolean z2 = false;
        p pVar = this.c;
        String strG = g();
        if (z) {
            this.c = p.LOGGED_IN_AS_GUEST;
            this.d.a((Object) this);
        } else {
            this.c = p.LOGGED_IN_AS_FACEBOOK_USER;
        }
        this.m = this.u.a(str);
        d(str, str2);
        if (pVar != this.c) {
            z2 = true;
        } else if (this.c == p.LOGGED_IN_AS_FACEBOOK_USER && strG != g()) {
            z2 = true;
        }
        if (z2) {
            w();
            n();
            this.u.f();
        }
        c(true);
        b(z, z2);
        if (!z) {
            com.topfreegames.bikerace.k.a.a().a(str);
        }
        v();
    }

    public void d(String str) {
        this.e.a(str);
        u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.o.5
            @Override // java.lang.Runnable
            public void run() {
                o.this.a(o.this.e.a(), o.this.e.b(), true);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        a(str, str2, false);
    }

    private void v() {
        if (this.j && this.k != null) {
            a(this.k, this.l, v.GAME_START_VIA_LINK);
        }
        this.j = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(l lVar, v vVar) {
        if (this.g != null) {
            this.g.b(lVar);
        }
        com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(this.p);
        if (lVar != null) {
            switch (t()[vVar.ordinal()]) {
                case 1:
                    fVarA.c("AchievCreateGameFacebook");
                    break;
                case 2:
                    if (this.g != null) {
                        c(this.p.getString(2131100005, lVar.g()));
                    } else {
                        c(this.p.getString(2131100006, lVar.g()));
                    }
                    fVarA.c("AchievCreateGameRandom");
                    break;
                case 3:
                    if (this.g == null) {
                        c(this.p.getString(2131100008, lVar.g()));
                    } else {
                        c(this.p.getString(2131100007, lVar.g()));
                    }
                    break;
            }
        }
    }

    public void n() {
        w();
        this.d.c();
    }

    private void w() {
        this.u.e();
        this.d.e();
    }

    private void b(boolean z, boolean z2) {
        if (this.f != null) {
            this.f.a(z, z2);
        }
    }

    private void d(String str, String str2) {
        String string = this.o.getString("id", "");
        if (!str.equals(string)) {
            com.topfreegames.bikerace.push.e.b(this.p, string);
        }
        SharedPreferences.Editor editorEdit = this.o.edit();
        editorEdit.putString("name", str2);
        editorEdit.putString("id", str);
        editorEdit.commit();
        j.a(this.p, str, str2);
        if (((BikeRaceApplication) this.p).a().d() && !str.equals(string)) {
            com.topfreegames.bikerace.push.e.b(this.p);
            com.topfreegames.bikerace.push.e.b(this.p, string);
            com.topfreegames.bikerace.push.e.a(this.p, str);
        }
    }

    public void e(String str) {
        this.u.b(str);
    }

    public int o() {
        return this.u.c();
    }

    public boolean a(Context context, String str) {
        return new Date().getTime() - context.getSharedPreferences("com.topfreegames.bikerace.poke", 0).getLong(str, new Date().getTime()) > 86400000;
    }

    public void a(Context context, String str, String str2, String str3) {
        if (a(context, str2)) {
            com.topfreegames.bikerace.push.g.a(str, str2, str3, com.topfreegames.bikerace.push.d.POKE, this.f1316a.sqs());
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.poke", 0).edit();
            editorEdit.putLong(str2, new Date().getTime());
            editorEdit.commit();
        }
    }

    public boolean p() {
        SharedPreferences sharedPreferences = this.p.getSharedPreferences("com.topfreegames.bikerace.multiplayer", 0);
        long jAx = this.v.ax();
        long jCurrentTimeMillis = System.currentTimeMillis();
        return jCurrentTimeMillis - sharedPreferences.getLong("FakePokeToday", jCurrentTimeMillis - (2 * jAx)) > jAx && this.v.aw();
    }

    public void q() {
        SharedPreferences sharedPreferences = this.p.getSharedPreferences("com.topfreegames.bikerace.multiplayer", 0);
        sharedPreferences.edit().putLong("FakePokeToday", System.currentTimeMillis()).commit();
    }

    public void b(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.poke", 0).edit();
        editorEdit.putLong(str, new Date().getTime());
        editorEdit.commit();
    }

    public b r() {
        return this.t;
    }

    public void f(String str) {
        SharedPreferences.Editor editorEdit = this.q.edit();
        editorEdit.putString(g(), str);
        editorEdit.commit();
    }

    public String s() {
        return this.q.getString(g(), "");
    }
}
