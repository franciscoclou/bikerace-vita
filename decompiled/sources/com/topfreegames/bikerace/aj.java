package com.topfreegames.bikerace;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.Display;
import android.view.WindowManager;

/* JADX INFO: compiled from: InputAccelerometer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class aj implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.engine.a.c f1122a = new com.topfreegames.engine.a.c();
    private com.topfreegames.engine.a.c b = new com.topfreegames.engine.a.c();
    private Context c;
    private Display d;
    private int e;

    public aj(Context context) {
        this.c = null;
        this.e = 0;
        this.c = context;
        this.d = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay();
        this.e = this.d.getOrientation();
    }

    public com.topfreegames.engine.a.c a() {
        return this.f1122a;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        switch (this.d.getRotation()) {
            case 1:
                f = -f2;
                f2 = f;
                break;
            case 2:
                f = -f;
                f2 = -f2;
                break;
            case 3:
                float f4 = -f;
                f = f2;
                f2 = f4;
                break;
        }
        this.f1122a.f1554a = (f * 1.0f) + (this.f1122a.f1554a * 0.0f);
        this.f1122a.b = (f2 * 1.0f) + (this.f1122a.b * 0.0f);
        this.f1122a.c = (this.f1122a.c * 0.0f) + (f3 * 1.0f);
        this.b.f1554a = sensorEvent.values[0] - this.f1122a.f1554a;
        this.b.b = sensorEvent.values[1] - this.f1122a.b;
        this.b.c = sensorEvent.values[2] - this.f1122a.c;
    }

    public void b() {
        this.e = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getOrientation();
        this.f1122a.a(0.0f, 0.0f, 0.0f);
        this.b.a(0.0f, 0.0f, 0.0f);
    }
}
