package android.support.v4.view;

import android.graphics.Paint;
import android.view.View;

/* JADX INFO: compiled from: ViewCompatJellybeanMr1.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class av {
    public static void a(View view, Paint paint) {
        view.setLayerPaint(paint);
    }

    public static int a(View view) {
        return view.getLayoutDirection();
    }
}
