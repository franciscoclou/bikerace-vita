package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ n f309a;

    s(n nVar) {
        this.f309a = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            t tVar = this.f309a.f304a;
            this.f309a.f304a = new h();
            tVar.b();
        } catch (Exception e) {
            ba.d("Crashlytics failed to disable analytics.");
        }
    }
}
