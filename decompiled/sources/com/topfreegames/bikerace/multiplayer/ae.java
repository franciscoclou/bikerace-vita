package com.topfreegames.bikerace.multiplayer;

import java.util.Date;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: MultiplayerService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ae implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ aa f1302a;
    private com.topfreegames.bikerace.b.a b;

    public ae(aa aaVar, com.topfreegames.bikerace.b.a aVar) {
        this.f1302a = aaVar;
        this.b = aVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        try {
            this.f1302a.c(this.b);
            this.f1302a.a(this.b, new Date());
            return null;
        } catch (com.amazonaws.a e) {
            e.printStackTrace();
            this.f1302a.f1300a.b("PutTask", e);
            return null;
        }
    }
}
