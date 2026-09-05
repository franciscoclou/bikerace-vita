package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ n f308a;

    r(n nVar) {
        this.f308a = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f308a.f304a.a();
        } catch (Exception e) {
            ba.d("Crashlytics failed to send analytics files.");
        }
    }
}
