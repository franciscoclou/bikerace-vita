package android.support.v4.view;

import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final ap f47a;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            f47a = new ao();
            return;
        }
        if (i >= 17) {
            f47a = new an();
            return;
        }
        if (i >= 16) {
            f47a = new am();
            return;
        }
        if (i >= 14) {
            f47a = new al();
            return;
        }
        if (i >= 11) {
            f47a = new ak();
        } else if (i >= 9) {
            f47a = new aj();
        } else {
            f47a = new ah();
        }
    }

    public static boolean a(View view, int i) {
        return f47a.a(view, i);
    }

    public static int a(View view) {
        return f47a.a(view);
    }

    public static void a(View view, a aVar) {
        f47a.a(view, aVar);
    }

    public static void b(View view) {
        f47a.b(view);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        f47a.a(view, i, i2, i3, i4);
    }

    public static void a(View view, Runnable runnable) {
        f47a.a(view, runnable);
    }

    public static int c(View view) {
        return f47a.c(view);
    }

    public static void b(View view, int i) {
        f47a.b(view, i);
    }

    public static void a(View view, int i, Paint paint) {
        f47a.a(view, i, paint);
    }

    public static int d(View view) {
        return f47a.d(view);
    }

    public static void a(View view, Paint paint) {
        f47a.a(view, paint);
    }

    public static int e(View view) {
        return f47a.e(view);
    }

    public static ViewParent f(View view) {
        return f47a.f(view);
    }

    public static boolean g(View view) {
        return f47a.g(view);
    }
}
