package com.flurry.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class fe extends BroadcastReceiver {
    private static fe e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f562a;
    Boolean b;
    private boolean d = false;
    private List<WeakReference<fd>> c = new LinkedList();

    public enum a {
        NONE_OR_UNKNOWN(0),
        WIFI(1),
        CELL(2);

        private int d;

        a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    public static synchronized fe a() {
        if (e == null) {
            e = new fe();
        }
        return e;
    }

    public synchronized void b() {
        Context contextB = eg.a().b();
        this.d = contextB.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0;
        this.f562a = a(contextB);
        if (this.d) {
            d();
        }
    }

    public synchronized void a(fd fdVar) {
        if (fdVar != null) {
            this.c.add(new WeakReference<>(fdVar));
        }
    }

    public boolean c() {
        return this.b != null ? this.b.booleanValue() : this.f562a;
    }

    void d() {
        Context contextB = eg.a().b();
        this.f562a = a(contextB);
        contextB.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean zA = a(context);
        if (this.f562a != zA) {
            this.f562a = zA;
            Iterator it = new LinkedList(this.c).iterator();
            while (it.hasNext()) {
                fd fdVar = (fd) ((WeakReference) it.next()).get();
                if (fdVar != null) {
                    fdVar.b(this.f562a);
                }
            }
        }
    }

    private boolean a(Context context) {
        if (!this.d || context == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public a e() {
        if (!this.d) {
            return a.NONE_OR_UNKNOWN;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) eg.a().b().getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && networkInfo.isConnected()) {
            return a.WIFI;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 != null && networkInfo2.isConnected()) {
            return a.CELL;
        }
        return a.NONE_OR_UNKNOWN;
    }
}
