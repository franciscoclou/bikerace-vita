package com.topfreegames.bikerace;

import android.content.Context;
import android.hardware.SensorManager;

/* JADX INFO: compiled from: InputAccelerometer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f1121a = 9.80665f;
    private SensorManager b;
    private aj c;
    private boolean d = false;

    public ai(Context context) {
        this.b = (SensorManager) context.getSystemService("sensor");
        this.c = new aj(context.getApplicationContext());
    }

    public void a(com.topfreegames.engine.a.c cVar) {
        if (this.d) {
            cVar.a(this.c.a()).a(f1121a);
        } else {
            cVar.a(0.0f, 0.0f, 0.0f);
        }
    }

    public void a() {
        if (!this.d) {
            this.d = true;
            this.b.registerListener(this.c, this.b.getDefaultSensor(1), 1);
        }
    }

    public void b() {
        if (this.d) {
            this.b.unregisterListener(this.c);
            this.c.b();
            this.d = false;
        }
    }
}
