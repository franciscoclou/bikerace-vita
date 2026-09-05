package android.support.v4.view;

import android.view.VelocityTracker;

/* JADX INFO: compiled from: VelocityTrackerCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ac implements ae {
    ac() {
    }

    @Override // android.support.v4.view.ae
    public float a(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getXVelocity();
    }

    @Override // android.support.v4.view.ae
    public float b(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getYVelocity();
    }
}
