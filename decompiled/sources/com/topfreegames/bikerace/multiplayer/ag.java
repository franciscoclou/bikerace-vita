package com.topfreegames.bikerace.multiplayer;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: MultiplayerService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ag implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ aa f1304a;
    private String b;
    private ah c;

    public ag(aa aaVar, String str, ah ahVar) {
        this.f1304a = aaVar;
        this.b = str;
        this.c = ahVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        try {
            this.f1304a.a(this.b, this.c, true);
            this.f1304a.f = false;
            if (this.c != null) {
                this.c.a();
            }
            return null;
        } catch (InterruptedException e) {
            this.f1304a.f = false;
            return null;
        } finally {
            this.f1304a.f = false;
        }
    }
}
