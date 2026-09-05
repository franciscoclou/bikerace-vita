package android.support.v4.view;

import android.view.View;

/* JADX INFO: compiled from: ViewCompatICS.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class at {
    public static boolean a(View view, int i) {
        return view.canScrollHorizontally(i);
    }

    public static void a(View view, Object obj) {
        view.setAccessibilityDelegate((View.AccessibilityDelegate) obj);
    }
}
