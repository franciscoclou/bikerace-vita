package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* JADX INFO: compiled from: ScrollerCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements k {
    l() {
    }

    @Override // android.support.v4.widget.k
    public Object a(Context context, Interpolator interpolator) {
        return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
    }

    @Override // android.support.v4.widget.k
    public boolean a(Object obj) {
        return ((Scroller) obj).isFinished();
    }

    @Override // android.support.v4.widget.k
    public int b(Object obj) {
        return ((Scroller) obj).getCurrX();
    }

    @Override // android.support.v4.widget.k
    public int c(Object obj) {
        return ((Scroller) obj).getCurrY();
    }

    @Override // android.support.v4.widget.k
    public boolean d(Object obj) {
        return ((Scroller) obj).computeScrollOffset();
    }

    @Override // android.support.v4.widget.k
    public void a(Object obj, int i, int i2, int i3, int i4, int i5) {
        ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
    }

    @Override // android.support.v4.widget.k
    public void e(Object obj) {
        ((Scroller) obj).abortAnimation();
    }

    @Override // android.support.v4.widget.k
    public int f(Object obj) {
        return ((Scroller) obj).getFinalX();
    }

    @Override // android.support.v4.widget.k
    public int g(Object obj) {
        return ((Scroller) obj).getFinalY();
    }
}
