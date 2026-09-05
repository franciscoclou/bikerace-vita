package com.topfreegames.bikerace.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.facebook.internal.NativeProtocol;
import com.topfreegames.bikerace.ay;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.be;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BikeRaceApplication extends Application {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.f f827a = null;
    private com.topfreegames.bikerace.z b = null;
    private com.topfreegames.bikerace.v c = null;
    private a d = null;
    private com.topfreegames.bikerace.multiplayer.o e = null;
    private com.topfreegames.bikerace.t f = null;
    private bb g = null;

    @TargetApi(9)
    private void g() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().permitDiskReads().permitDiskWrites().permitNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().build());
    }

    @TargetApi(XMLStreamConstants.DTD)
    private void h() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().permitDiskReads().permitDiskWrites().permitNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().detectActivityLeaks().penaltyLog().build());
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        com.b.a.d.a(this);
        com.topfreegames.bikerace.ap.a();
        if (com.topfreegames.bikerace.ap.d()) {
            if (Build.VERSION.SDK_INT >= 11) {
                h();
            } else if (Build.VERSION.SDK_INT >= 9) {
                g();
            }
        }
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.BikeRaceApplication.1
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.c.a.a();
                BikeRaceApplication.this.d().a(com.topfreegames.c.a.a().getTime(), System.currentTimeMillis());
            }
        }).start();
        com.topfreegames.bikerace.n.a.a(this, new com.topfreegames.bikerace.n.c() { // from class: com.topfreegames.bikerace.activities.BikeRaceApplication.2
            @Override // com.topfreegames.bikerace.n.c
            public void a() {
                System.out.println("I: " + com.topfreegames.bikerace.n.a.a().a("kABTEST_chart"));
                com.topfreegames.bikerace.g.a.g().o();
            }
        });
        com.topfreegames.bikerace.g.a.a(getApplicationContext());
        com.topfreegames.bikerace.h.a.q.a(getApplicationContext());
        com.topfreegames.e.a.a.a(getApplicationContext());
        com.topfreegames.a.d.a(getApplicationContext(), d().t(), ay.a());
        com.topfreegames.bikerace.c.d.a(getApplicationContext());
        com.topfreegames.bikerace.worldcup.o.a(getApplicationContext(), a(false), a(), d());
        com.topfreegames.bikerace.j.a.a(a(), a(false), d());
        com.topfreegames.bikerace.k.a.a(getApplicationContext(), a());
        com.topfreegames.bikerace.k.a.a().a(c().g());
        a((Integer) 1);
        System.out.println((Object) 1);
    }

    private void a(Integer num) {
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        d().o();
    }

    public synchronized com.topfreegames.bikerace.f a(Context context, Handler handler) {
        if (this.f827a == null) {
            this.f827a = new com.topfreegames.bikerace.f(this, handler, a(), b(), d());
        } else {
            this.f827a.a(this, handler);
        }
        return this.f827a;
    }

    public synchronized com.topfreegames.bikerace.z a() {
        if (this.b == null) {
            this.b = new com.topfreegames.bikerace.z(getApplicationContext(), false);
            this.b.A();
        }
        return this.b;
    }

    public synchronized com.topfreegames.bikerace.v b() {
        if (this.c == null) {
            this.c = new com.topfreegames.bikerace.v(getApplicationContext(), a());
        }
        return this.c;
    }

    public synchronized a a(Activity activity) {
        if (this.d == null) {
            this.d = new a(activity);
        }
        return this.d;
    }

    public synchronized com.topfreegames.bikerace.multiplayer.o c() {
        if (this.e == null) {
            this.e = new com.topfreegames.bikerace.multiplayer.o(getApplicationContext());
        } else {
            this.e.l();
        }
        return this.e;
    }

    public synchronized com.topfreegames.bikerace.t d() {
        if (this.f == null) {
            this.f = new com.topfreegames.bikerace.t(getApplicationContext());
        }
        return this.f;
    }

    public synchronized bb a(boolean z) {
        if (this.g == null) {
            this.g = new bb(getApplicationContext());
            this.g.a(new be() { // from class: com.topfreegames.bikerace.activities.BikeRaceApplication.3
                @Override // com.topfreegames.bikerace.be
                public void a() {
                    com.topfreegames.bikerace.z zVarA = BikeRaceApplication.this.a();
                    if (zVarA.v() < 0) {
                        if (zVarA.p() <= 0) {
                            zVarA.c(BikeRaceApplication.this.g.v());
                        } else {
                            zVarA.c(0);
                        }
                    }
                    com.topfreegames.bikerace.g.a.g().o();
                    com.topfreegames.bikerace.worldcup.o oVarA = com.topfreegames.bikerace.worldcup.o.a();
                    if (oVarA.t()) {
                        oVarA.b();
                    }
                }
            });
        }
        if (z && e()) {
            this.g.aD();
        }
        return this.g;
    }

    public boolean e() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean f() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("sms_body", "");
        intent.setType("vnd.android-dir/mms-sms");
        boolean zA = a(intent);
        if (zA) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
            if (telephonyManager == null) {
                return false;
            }
            switch (telephonyManager.getSimState()) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return false;
            }
        }
        return zA;
    }

    private boolean a(Intent intent) {
        return getPackageManager().queryIntentActivities(intent, NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST).size() > 0;
    }

    public void a(String str) {
        ((ClipboardManager) getSystemService("clipboard")).setText(str);
    }

    @SuppressLint({"ServiceCast"})
    @TargetApi(XMLStreamConstants.DTD)
    public void b(String str) {
        ((android.content.ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("BikeRace link", str));
    }
}
