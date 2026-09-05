package com.google.a.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.analytics.internal.Command;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: AnalyticsGmsCoreClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ServiceConnection f588a;
    private d b;
    private e c;
    private Context d;
    private com.google.android.gms.analytics.internal.a e;

    public b(Context context, d dVar, e eVar) {
        this.d = context;
        if (dVar == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.b = dVar;
        if (eVar == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.c = eVar;
    }

    @Override // com.google.a.a.a.a
    public void b() {
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", this.d.getPackageName());
        if (this.f588a != null) {
            ah.a("Calling connect() while still connected, missing disconnect().");
            return;
        }
        this.f588a = new c(this);
        boolean zBindService = this.d.bindService(intent, this.f588a, 129);
        ah.c("connect: bindService returned " + zBindService + " for " + intent);
        if (!zBindService) {
            this.f588a = null;
            this.c.a(1, null);
        }
    }

    @Override // com.google.a.a.a.a
    public void c() {
        this.e = null;
        if (this.f588a != null) {
            try {
                this.d.unbindService(this.f588a);
            } catch (IllegalArgumentException e) {
            } catch (IllegalStateException e2) {
            }
            this.f588a = null;
            this.b.b();
        }
    }

    @Override // com.google.a.a.a.a
    public void a(Map<String, String> map, long j, String str, List<Command> list) {
        try {
            f().a(map, j, str, list);
        } catch (RemoteException e) {
            ah.a("sendHit failed: " + e);
        }
    }

    @Override // com.google.a.a.a.a
    public void a() {
        try {
            f().a();
        } catch (RemoteException e) {
            ah.a("clear hits failed: " + e);
        }
    }

    private com.google.android.gms.analytics.internal.a f() {
        d();
        return this.e;
    }

    protected void d() {
        if (!e()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public boolean e() {
        return this.e != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        h();
    }

    private void h() {
        this.b.a();
    }
}
