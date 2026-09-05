package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ aj f307a;
    private /* synthetic */ String b;
    private /* synthetic */ n c;

    q(n nVar, aj ajVar, String str) {
        this.c = nVar;
        this.f307a = ajVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.c.f304a.a(this.f307a, this.b);
        } catch (Exception e) {
            ba.d("Crashlytics failed to set analytics settings data.");
        }
    }
}
