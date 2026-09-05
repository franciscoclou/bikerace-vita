package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ af f230a;

    d(af afVar) {
        this.f230a = afVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f230a.dismiss();
        } catch (Throwable th) {
            if (this.f230a.b != null) {
                this.f230a.b.b("InterstitialAdDialog", "dismiss() threw exception", th);
            }
        }
    }
}
