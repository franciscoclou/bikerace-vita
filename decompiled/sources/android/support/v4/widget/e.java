package android.support.v4.widget;

import android.graphics.Canvas;
import android.os.Build;

/* JADX INFO: compiled from: EdgeEffectCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {
    private static final h b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object f66a;

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            b = new g();
        } else {
            b = new f();
        }
    }

    public void a(int i, int i2) {
        b.a(this.f66a, i, i2);
    }

    public boolean a() {
        return b.a(this.f66a);
    }

    public void b() {
        b.b(this.f66a);
    }

    public boolean a(float f) {
        return b.a(this.f66a, f);
    }

    public boolean c() {
        return b.c(this.f66a);
    }

    public boolean a(Canvas canvas) {
        return b.a(this.f66a, canvas);
    }
}
