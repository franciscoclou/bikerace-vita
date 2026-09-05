package com.google.a.a.a;

import java.util.TimerTask;

/* JADX INFO: compiled from: GAServiceProxy.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class x extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f607a;

    private x(v vVar) {
        this.f607a = vVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (this.f607a.b != w.CONNECTED_SERVICE || !this.f607a.i.isEmpty() || this.f607a.f602a + this.f607a.s >= this.f607a.r.a()) {
            this.f607a.m.schedule(new x(this.f607a), this.f607a.s);
        } else {
            ah.c("Disconnecting due to inactivity");
            this.f607a.l();
        }
    }
}
