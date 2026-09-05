package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: compiled from: ViewCompatHC.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class as {
    static long a() {
        return ValueAnimator.getFrameDelay();
    }

    public static void a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    public static int a(View view) {
        return view.getLayerType();
    }
}
