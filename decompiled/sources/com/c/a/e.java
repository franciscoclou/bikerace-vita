package com.c.a;

/* JADX INFO: compiled from: BaseInterstitialManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f365a;
    private int b;

    private e(a aVar) {
        this.f365a = aVar;
        this.b = 0;
    }

    /* synthetic */ e(a aVar, e eVar) {
        this(aVar);
    }

    private h c(String str) {
        this.b = (this.b + 1) % this.f365a.e.size();
        return (h) this.f365a.e.get(this.b);
    }

    @Override // com.c.a.d
    public void a(String str) {
        if (!this.f365a.b.a(this.f365a.c) || !this.f365a.b.a(this.f365a.c, str)) {
            this.f365a.c = c(str);
            return;
        }
        if (!this.f365a.c.c(str)) {
            this.f365a.d++;
            if (this.f365a.d > this.f365a.h) {
                this.f365a.c = c(str);
            }
            this.f365a.a(str);
            return;
        }
        if (!this.f365a.b.a(str)) {
            return;
        }
        this.f365a.c.a(str);
        this.f365a.a(str);
        this.f365a.c = c(str);
        this.f365a.d = 0;
    }

    @Override // com.c.a.d
    public boolean b(String str) {
        return this.f365a.b.a(this.f365a.c) && this.f365a.b.a(this.f365a.c, str) && this.f365a.b.a(str) && this.f365a.c != null && this.f365a.c.c(str);
    }
}
