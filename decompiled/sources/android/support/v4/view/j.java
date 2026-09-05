package android.support.v4.view;

import android.os.Build;

/* JADX INFO: compiled from: GravityCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final k f56a;

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            f56a = new m();
        } else {
            f56a = new l();
        }
    }

    public static int a(int i, int i2) {
        return f56a.a(i, i2);
    }
}
