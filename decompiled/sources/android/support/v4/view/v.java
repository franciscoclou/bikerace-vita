package android.support.v4.view;

import android.os.Build;
import android.view.MotionEvent;

/* JADX INFO: compiled from: MotionEventCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final y f58a;

    static {
        if (Build.VERSION.SDK_INT >= 5) {
            f58a = new x();
        } else {
            f58a = new w();
        }
    }

    public static int a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static int b(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int a(MotionEvent motionEvent, int i) {
        return f58a.a(motionEvent, i);
    }

    public static int b(MotionEvent motionEvent, int i) {
        return f58a.b(motionEvent, i);
    }

    public static float c(MotionEvent motionEvent, int i) {
        return f58a.c(motionEvent, i);
    }

    public static float d(MotionEvent motionEvent, int i) {
        return f58a.d(motionEvent, i);
    }

    public static int c(MotionEvent motionEvent) {
        return f58a.a(motionEvent);
    }
}
