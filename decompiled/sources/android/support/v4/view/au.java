package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: ViewCompatJB.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class au {
    public static void a(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        view.postInvalidate(i, i2, i3, i4);
    }

    public static void a(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static int b(View view) {
        return view.getImportantForAccessibility();
    }

    public static void a(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static ViewParent c(View view) {
        return view.getParentForAccessibility();
    }
}
