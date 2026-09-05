package android.support.v4.view;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final az f48a;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 18) {
            f48a = new ba();
            return;
        }
        if (i >= 14) {
            f48a = new ay();
        } else if (i >= 11) {
            f48a = new ax();
        } else {
            f48a = new bb();
        }
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        f48a.a(viewGroup, z);
    }
}
