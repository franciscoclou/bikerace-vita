package android.support.v4.view.a;

import android.os.Build;
import android.os.Bundle;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final l f40a;
    private final Object b;

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            f40a = new n();
        } else if (Build.VERSION.SDK_INT >= 16) {
            f40a = new m();
        } else {
            f40a = new o();
        }
    }

    public k() {
        this.b = f40a.a(this);
    }

    public k(Object obj) {
        this.b = obj;
    }

    public Object a() {
        return this.b;
    }

    public a a(int i) {
        return null;
    }

    public boolean a(int i, int i2, Bundle bundle) {
        return false;
    }

    public List<a> a(String str, int i) {
        return null;
    }
}
