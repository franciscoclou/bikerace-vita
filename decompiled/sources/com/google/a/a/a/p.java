package com.google.a.a.a;

import java.util.TimerTask;

/* JADX INFO: compiled from: EasyTracker.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class p extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f596a;

    private p(o oVar) {
        this.f596a = oVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.f596a.p = false;
    }
}
