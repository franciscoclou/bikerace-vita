package android.support.v4.view;

import android.os.Build;
import android.view.VelocityTracker;

/* JADX INFO: compiled from: VelocityTrackerCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final ae f46a;

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f46a = new ad();
        } else {
            f46a = new ac();
        }
    }

    public static float a(VelocityTracker velocityTracker, int i) {
        return f46a.a(velocityTracker, i);
    }

    public static float b(VelocityTracker velocityTracker, int i) {
        return f46a.b(velocityTracker, i);
    }
}
