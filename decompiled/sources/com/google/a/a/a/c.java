package com.google.a.a.a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: compiled from: AnalyticsGmsCoreClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class c implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f589a;

    c(b bVar) {
        this.f589a = bVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ah.c("service connected, binder: " + iBinder);
        try {
            if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(iBinder.getInterfaceDescriptor())) {
                ah.c("bound to service");
                this.f589a.e = com.google.android.gms.analytics.internal.b.a(iBinder);
                this.f589a.g();
                return;
            }
        } catch (RemoteException e) {
        }
        this.f589a.d.unbindService(this);
        this.f589a.f588a = null;
        this.f589a.c.a(2, null);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        ah.c("service disconnected: " + componentName);
        this.f589a.f588a = null;
        this.f589a.b.b();
    }
}
