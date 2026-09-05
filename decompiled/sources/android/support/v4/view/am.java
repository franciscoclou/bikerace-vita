package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class am extends al {
    am() {
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public void b(View view) {
        au.a(view);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public void a(View view, int i, int i2, int i3, int i4) {
        au.a(view, i, i2, i3, i4);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public void a(View view, Runnable runnable) {
        au.a(view, runnable);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public int c(View view) {
        return au.b(view);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public void b(View view, int i) {
        au.a(view, i);
    }

    @Override // android.support.v4.view.ah, android.support.v4.view.ap
    public ViewParent f(View view) {
        return au.c(view);
    }
}
