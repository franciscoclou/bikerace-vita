package com.heyzap.sdk.ads;

/* JADX INFO: compiled from: FullscreenVideoView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f783a;

    private g(e eVar) {
        this.f783a = eVar;
    }

    @Override // com.heyzap.sdk.ads.y
    public void a() {
        b();
    }

    @Override // com.heyzap.sdk.ads.y
    public void b() {
        if (this.f783a.j != null) {
            if (this.f783a.k.isPlaying()) {
                int currentPosition = this.f783a.k.getCurrentPosition();
                if (currentPosition > this.f783a.n) {
                    this.f783a.n = currentPosition;
                }
                this.f783a.k.pause();
                this.f783a.j.b();
                return;
            }
            this.f783a.j.b();
        }
    }

    @Override // com.heyzap.sdk.ads.y
    public void c() {
        if (this.f783a.c.booleanValue()) {
            if (this.f783a.k != null) {
                this.f783a.k.pause();
            }
            if (this.f783a.j != null) {
                this.f783a.j.c();
            }
        }
    }
}
