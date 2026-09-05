package android.support.v4.view;

import android.view.VelocityTracker;

/* JADX INFO: compiled from: VelocityTrackerCompatHoneycomb.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class af {
    public static float a(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getXVelocity(i);
    }

    public static float b(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getYVelocity(i);
    }
}
