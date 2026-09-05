package android.support.v4.view;

import android.graphics.Paint;
import android.view.View;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ak extends aj {
    ak() {
    }

    @Override // android.support.v4.view.ah
    long a() {
        return as.a();
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public void a(View view, int i, Paint paint) {
        as.a(view, i, paint);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public int d(View view) {
        return as.a(view);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public void a(View view, Paint paint) {
        a(view, d(view), paint);
        view.invalidate();
    }
}
