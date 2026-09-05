package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ah implements ap {
    ah() {
    }

    @Override // android.support.v4.view.ap
    public boolean a(View view, int i) {
        return false;
    }

    @Override // android.support.v4.view.ap
    public int a(View view) {
        return 2;
    }

    @Override // android.support.v4.view.ap
    public void a(View view, a aVar) {
    }

    @Override // android.support.v4.view.ap
    public void b(View view) {
        view.postInvalidateDelayed(a());
    }

    @Override // android.support.v4.view.ap
    public void a(View view, int i, int i2, int i3, int i4) {
        view.postInvalidateDelayed(a(), i, i2, i3, i4);
    }

    @Override // android.support.v4.view.ap
    public void a(View view, Runnable runnable) {
        view.postDelayed(runnable, a());
    }

    long a() {
        return 10L;
    }

    @Override // android.support.v4.view.ap
    public int c(View view) {
        return 0;
    }

    @Override // android.support.v4.view.ap
    public void b(View view, int i) {
    }

    @Override // android.support.v4.view.ap
    public void a(View view, int i, Paint paint) {
    }

    @Override // android.support.v4.view.ap
    public int d(View view) {
        return 0;
    }

    @Override // android.support.v4.view.ap
    public void a(View view, Paint paint) {
    }

    @Override // android.support.v4.view.ap
    public int e(View view) {
        return 0;
    }

    @Override // android.support.v4.view.ap
    public ViewParent f(View view) {
        return view.getParent();
    }

    @Override // android.support.v4.view.ap
    public boolean g(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }
}
