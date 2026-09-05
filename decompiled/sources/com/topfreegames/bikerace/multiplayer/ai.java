package com.topfreegames.bikerace.multiplayer;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: MultiplayerService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ai implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ aa f1305a;
    private com.topfreegames.bikerace.b.a b;

    public ai(aa aaVar, com.topfreegames.bikerace.b.a aVar) {
        this.f1305a = aaVar;
        this.b = aVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        this.f1305a.e(this.b);
        return null;
    }
}
