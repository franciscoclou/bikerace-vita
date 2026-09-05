package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ u f306a;
    private /* synthetic */ boolean b;
    private /* synthetic */ n c;

    p(n nVar, u uVar, boolean z) {
        this.c = nVar;
        this.f306a = uVar;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.c.f304a.a(this.f306a);
            if (this.b) {
                this.c.f304a.d();
            }
        } catch (Exception e) {
            ba.d("Crashlytics failed to record session event.");
        }
    }
}
