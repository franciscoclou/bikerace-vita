package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class x implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f312a;
    private final t b;

    public x(j jVar, t tVar) {
        this.f312a = jVar;
        this.b = tVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            ba.c("Performing time based analytics file roll over.");
            if (!this.f312a.a()) {
                this.b.c();
            }
        } catch (Exception e) {
            ba.d("Crashlytics failed to roll over session analytics file");
        }
    }
}
