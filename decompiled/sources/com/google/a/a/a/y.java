package com.google.a.a.a;

import java.util.TimerTask;

/* JADX INFO: compiled from: GAServiceProxy.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class y extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f608a;

    private y(v vVar) {
        this.f608a = vVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (this.f608a.b == w.CONNECTING) {
            this.f608a.j();
        }
    }
}
