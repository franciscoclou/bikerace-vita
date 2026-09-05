package com.google.a.a.a;

import java.util.TimerTask;

/* JADX INFO: compiled from: GAServiceProxy.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class aa extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f567a;

    private aa(v vVar) {
        this.f567a = vVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.f567a.k();
    }
}
