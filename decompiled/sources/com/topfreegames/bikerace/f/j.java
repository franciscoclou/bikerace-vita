package com.topfreegames.bikerace.f;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: GiftTasks.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class j implements Runnable {
    protected d b;
    protected e c = null;
    protected WeakReference<Activity> d = null;
    protected f e;

    public j(d dVar, f fVar) {
        this.b = null;
        this.e = null;
        this.b = dVar;
        this.e = fVar;
    }

    void a(e eVar, Activity activity) {
        this.c = eVar;
        this.d = new WeakReference<>(activity);
    }
}
