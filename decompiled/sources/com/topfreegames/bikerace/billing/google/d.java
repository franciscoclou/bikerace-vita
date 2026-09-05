package com.topfreegames.bikerace.billing.google;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: compiled from: BillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected long f1149a;
    final /* synthetic */ BillingService b;
    private final int c;

    protected abstract long d();

    public d(BillingService billingService, int i) {
        this.b = billingService;
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public boolean b() {
        if (c()) {
            return true;
        }
        if (this.b.g()) {
            BillingService.b.add(this);
            return true;
        }
        return false;
    }

    public boolean c() {
        if (BillingService.f1145a != null) {
            try {
                this.f1149a = d();
                if (this.f1149a >= 0) {
                    BillingService.c.put(Long.valueOf(this.f1149a), this);
                }
                return true;
            } catch (RemoteException e) {
                a(e);
            }
        }
        return false;
    }

    protected void a(RemoteException remoteException) {
        Log.w("BillingService", "remote billing service crashed");
        BillingService.f1145a = null;
    }

    protected void a(c cVar) {
    }

    protected Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("BILLING_REQUEST", str);
        bundle.putInt("API_VERSION", 1);
        bundle.putString("PACKAGE_NAME", this.b.getPackageName());
        return bundle;
    }
}
