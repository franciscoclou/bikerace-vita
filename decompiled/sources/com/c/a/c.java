package com.c.a;

/* JADX INFO: compiled from: BaseInterstitialManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f364a;
    private int b;

    private c(a aVar) {
        this.f364a = aVar;
        this.b = 0;
    }

    /* synthetic */ c(a aVar, c cVar) {
        this(aVar);
    }

    private h c(String str) {
        return a(str, 0);
    }

    private h d(String str) {
        return a(str, this.b + 1);
    }

    private h a(String str, int i) {
        int size = this.f364a.e.size();
        this.b = i;
        while (this.b < size) {
            h hVar = (h) this.f364a.e.get(this.b);
            if (this.f364a.b.a(hVar) && this.f364a.b.a(hVar, str)) {
                return hVar;
            }
            this.b++;
        }
        return null;
    }

    @Override // com.c.a.d
    public void a(String str) {
        this.f364a.c = c(str);
        while (this.f364a.c != null) {
            if (!this.f364a.c.c(str)) {
                this.f364a.a(str);
                this.f364a.c = d(str);
            } else {
                if (!this.f364a.b.a(str)) {
                    return;
                }
                this.f364a.c.a(str);
                this.f364a.a(str);
                return;
            }
        }
    }

    @Override // com.c.a.d
    public boolean b(String str) {
        this.f364a.c = c(str);
        while (this.f364a.c != null) {
            if (!this.f364a.c.c(str)) {
                this.f364a.a(str);
                this.f364a.c = d(str);
            } else {
                if (!this.f364a.b.a(str)) {
                    break;
                }
                return true;
            }
        }
        this.f364a.c = c(str);
        return false;
    }
}
