package com.heyzap.sdk.ads;

/* JADX INFO: compiled from: HeyzapVideoActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class m implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ HeyzapVideoActivity f787a;

    private m(HeyzapVideoActivity heyzapVideoActivity) {
        this.f787a = heyzapVideoActivity;
    }

    @Override // com.heyzap.sdk.ads.b
    public void a() {
    }

    @Override // com.heyzap.sdk.ads.b
    public void b() {
        if (!((w) this.f787a.f762a).r().booleanValue()) {
            this.f787a.c();
        } else {
            this.f787a.a(1);
        }
    }

    @Override // com.heyzap.sdk.ads.b
    public void c() {
        this.f787a.d();
    }

    @Override // com.heyzap.sdk.ads.b
    public void a(String str, String str2) {
        com.heyzap.internal.k.b(str);
    }

    @Override // com.heyzap.sdk.ads.b
    public void d() {
    }

    @Override // com.heyzap.sdk.ads.b
    public void e() {
        this.f787a.e = true;
        if (((w) this.f787a.f762a).r().booleanValue()) {
            this.f787a.a(1);
        }
    }

    @Override // com.heyzap.sdk.ads.b
    public void f() {
        if (!com.heyzap.internal.l.f(u.f799a)) {
            b();
        } else if (((w) this.f787a.f762a).r().booleanValue()) {
            this.f787a.a(1);
        } else {
            this.f787a.c();
        }
    }

    @Override // com.heyzap.sdk.ads.b
    public void g() {
    }
}
