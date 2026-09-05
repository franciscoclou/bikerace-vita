package android.support.v4.widget;

import android.view.View;

/* JADX INFO: compiled from: SlidingPaneLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ SlidingPaneLayout f69a;

    private r(SlidingPaneLayout slidingPaneLayout) {
        this.f69a = slidingPaneLayout;
    }

    @Override // android.support.v4.widget.z
    public boolean a(View view, int i) {
        if (this.f69a.k) {
            return false;
        }
        return ((s) view.getLayoutParams()).b;
    }

    @Override // android.support.v4.widget.z
    public void a(int i) {
        if (this.f69a.p.a() == 0) {
            if (this.f69a.h == 0.0f) {
                this.f69a.d(this.f69a.g);
                this.f69a.c(this.f69a.g);
                this.f69a.q = false;
            } else {
                this.f69a.b(this.f69a.g);
                this.f69a.q = true;
            }
        }
    }

    @Override // android.support.v4.widget.z
    public void b(View view, int i) {
        this.f69a.a();
    }

    @Override // android.support.v4.widget.z
    public void a(View view, int i, int i2, int i3, int i4) {
        this.f69a.a(i);
        this.f69a.invalidate();
    }

    @Override // android.support.v4.widget.z
    public void a(View view, float f, float f2) {
        s sVar = (s) view.getLayoutParams();
        int paddingLeft = sVar.leftMargin + this.f69a.getPaddingLeft();
        if (f > 0.0f || (f == 0.0f && this.f69a.h > 0.5f)) {
            paddingLeft += this.f69a.j;
        }
        this.f69a.p.a(paddingLeft, view.getTop());
        this.f69a.invalidate();
    }

    @Override // android.support.v4.widget.z
    public int a(View view) {
        return this.f69a.j;
    }

    @Override // android.support.v4.widget.z
    public int a(View view, int i, int i2) {
        s sVar = (s) this.f69a.g.getLayoutParams();
        int paddingLeft = sVar.leftMargin + this.f69a.getPaddingLeft();
        return Math.min(Math.max(i, paddingLeft), this.f69a.j + paddingLeft);
    }

    @Override // android.support.v4.widget.z
    public void b(int i, int i2) {
        this.f69a.p.a(this.f69a.g, i2);
    }
}
