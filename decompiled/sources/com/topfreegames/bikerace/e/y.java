package com.topfreegames.bikerace.e;

/* JADX INFO: compiled from: GiftsDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ s f1219a;
    private com.topfreegames.bikerace.f.a b;

    private y(s sVar, com.topfreegames.bikerace.f.a aVar) {
        this.f1219a = sVar;
        this.b = null;
        this.b = aVar;
    }

    /* synthetic */ y(s sVar, com.topfreegames.bikerace.f.a aVar, y yVar) {
        this(sVar, aVar);
    }

    public void a() {
        if (this.f1219a.j == null) {
            return;
        }
        this.f1219a.j.post(new Runnable() { // from class: com.topfreegames.bikerace.e.y.1
            @Override // java.lang.Runnable
            public void run() {
                y.this.f1219a.j.invalidate();
                y.this.f1219a.j.invalidateViews();
            }
        });
    }

    public void b() {
    }
}
