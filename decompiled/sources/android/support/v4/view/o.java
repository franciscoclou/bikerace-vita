package android.support.v4.view;

import android.os.Build;
import android.view.KeyEvent;

/* JADX INFO: compiled from: KeyEventCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final s f57a;

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f57a = new r();
        } else {
            f57a = new p();
        }
    }

    public static boolean a(KeyEvent keyEvent, int i) {
        return f57a.a(keyEvent.getMetaState(), i);
    }

    public static boolean a(KeyEvent keyEvent) {
        return f57a.b(keyEvent.getMetaState());
    }

    public static void b(KeyEvent keyEvent) {
        f57a.a(keyEvent);
    }
}
