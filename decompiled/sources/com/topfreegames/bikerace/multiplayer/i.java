package com.topfreegames.bikerace.multiplayer;

/* JADX INFO: compiled from: LevelRandomizer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1312a;
    public int b;

    protected i(int i) {
        if (i < 0) {
            this.b = com.topfreegames.bikerace.h.y.c[com.topfreegames.bikerace.h.y.c.length - 1];
            this.f1312a = com.topfreegames.bikerace.h.y.b(this.b);
        } else {
            this.f1312a = (i % 8) + 1;
            this.b = (i / 8) + 1;
        }
    }
}
