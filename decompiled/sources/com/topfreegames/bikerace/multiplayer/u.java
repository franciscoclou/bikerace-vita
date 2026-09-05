package com.topfreegames.bikerace.multiplayer;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class u implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f1335a;
    private String b;
    private String c;

    public u(o oVar, String str, String str2) {
        this.f1335a = oVar;
        this.b = str;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1335a.n != null) {
            this.f1335a.n.d(this.b, this.c);
        }
    }
}
