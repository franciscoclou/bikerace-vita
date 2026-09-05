package android.support.v4.widget;

import android.content.Context;
import android.os.Build;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: ScrollerCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j {
    static final k b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Object f67a;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 14) {
            b = new n();
        } else if (i >= 9) {
            b = new m();
        } else {
            b = new l();
        }
    }

    public static j a(Context context, Interpolator interpolator) {
        return new j(context, interpolator);
    }

    j(Context context, Interpolator interpolator) {
        this.f67a = b.a(context, interpolator);
    }

    public boolean a() {
        return b.a(this.f67a);
    }

    public int b() {
        return b.b(this.f67a);
    }

    public int c() {
        return b.c(this.f67a);
    }

    public int d() {
        return b.f(this.f67a);
    }

    public int e() {
        return b.g(this.f67a);
    }

    public boolean f() {
        return b.d(this.f67a);
    }

    public void a(int i, int i2, int i3, int i4, int i5) {
        b.a(this.f67a, i, i2, i3, i4, i5);
    }

    public void g() {
        b.e(this.f67a);
    }
}
