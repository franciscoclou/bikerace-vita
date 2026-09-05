package com.topfreegames.bikerace.e;

import android.content.Context;

/* JADX INFO: compiled from: EasterEggLocationDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1194a;
    public String b;
    public boolean c;

    public i(int i, int i2, int i3, Context context) {
        this.f1194a = i3;
        this.b = com.topfreegames.bikerace.h.y.a(context, i);
        this.c = com.topfreegames.bikerace.c.d.a().a(i, i2, i3, true);
        if (i == 19) {
            this.b = String.valueOf(this.b) + " Lvl. " + i2;
        }
    }
}
