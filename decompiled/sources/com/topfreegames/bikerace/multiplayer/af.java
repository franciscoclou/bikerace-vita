package com.topfreegames.bikerace.multiplayer;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: MultiplayerService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class af implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ aa f1303a;
    private String b;
    private ad c;

    public af(aa aaVar, String str, ad adVar) {
        this.f1303a = aaVar;
        this.b = str;
        this.c = adVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        try {
            this.f1303a.a(this.b, this.c, true);
            this.f1303a.g = false;
            if (this.c != null) {
                this.c.a();
            }
            return null;
        } catch (InterruptedException e) {
            this.f1303a.f = false;
            return null;
        } finally {
            this.f1303a.f = false;
        }
    }
}
