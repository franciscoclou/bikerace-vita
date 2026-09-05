package android.support.v4.widget;

import android.view.View;

/* JADX INFO: compiled from: DrawerLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ DrawerLayout f64a;
    private final int b;
    private y c;
    private final Runnable d = new Runnable() { // from class: android.support.v4.widget.d.1
        @Override // java.lang.Runnable
        public void run() {
            d.this.c();
        }
    };

    public d(DrawerLayout drawerLayout, int i) {
        this.f64a = drawerLayout;
        this.b = i;
    }

    public void a(y yVar) {
        this.c = yVar;
    }

    public void a() {
        this.f64a.removeCallbacks(this.d);
    }

    @Override // android.support.v4.widget.z
    public boolean a(View view, int i) {
        return this.f64a.g(view) && this.f64a.a(view, this.b) && this.f64a.a(view) == 0;
    }

    @Override // android.support.v4.widget.z
    public void a(int i) {
        this.f64a.a(this.b, i, this.c.c());
    }

    @Override // android.support.v4.widget.z
    public void a(View view, int i, int i2, int i3, int i4) {
        float width;
        int width2 = view.getWidth();
        if (this.f64a.a(view, 3)) {
            width = (width2 + i) / width2;
        } else {
            width = (this.f64a.getWidth() - i) / width2;
        }
        this.f64a.b(view, width);
        view.setVisibility(width == 0.0f ? 4 : 0);
        this.f64a.invalidate();
    }

    @Override // android.support.v4.widget.z
    public void b(View view, int i) {
        ((c) view.getLayoutParams()).c = false;
        b();
    }

    private void b() {
        View viewA = this.f64a.a(this.b == 3 ? 5 : 3);
        if (viewA != null) {
            this.f64a.i(viewA);
        }
    }

    @Override // android.support.v4.widget.z
    public void a(View view, float f, float f2) {
        int width;
        float fD = this.f64a.d(view);
        int width2 = view.getWidth();
        if (this.f64a.a(view, 3)) {
            width = (f > 0.0f || (f == 0.0f && fD > 0.5f)) ? 0 : -width2;
        } else {
            width = this.f64a.getWidth();
            if (f < 0.0f || (f == 0.0f && fD > 0.5f)) {
                width -= width2;
            }
        }
        this.c.a(width, view.getTop());
        this.f64a.invalidate();
    }

    @Override // android.support.v4.widget.z
    public void a(int i, int i2) {
        this.f64a.postDelayed(this.d, 160L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        View view;
        int i;
        int iB = this.c.b();
        boolean z = this.b == 3;
        if (z) {
            View viewA = this.f64a.a(3);
            int i2 = (viewA != null ? -viewA.getWidth() : 0) + iB;
            view = viewA;
            i = i2;
        } else {
            View viewA2 = this.f64a.a(5);
            int width = this.f64a.getWidth() - iB;
            view = viewA2;
            i = width;
        }
        if (view != null) {
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && this.f64a.a(view) == 0) {
                c cVar = (c) view.getLayoutParams();
                this.c.a(view, i, view.getTop());
                cVar.c = true;
                this.f64a.invalidate();
                b();
                this.f64a.c();
            }
        }
    }

    @Override // android.support.v4.widget.z
    public boolean b(int i) {
        return false;
    }

    @Override // android.support.v4.widget.z
    public void b(int i, int i2) {
        View viewA;
        if ((i & 1) == 1) {
            viewA = this.f64a.a(3);
        } else {
            viewA = this.f64a.a(5);
        }
        if (viewA != null && this.f64a.a(viewA) == 0) {
            this.c.a(viewA, i2);
        }
    }

    @Override // android.support.v4.widget.z
    public int a(View view) {
        return view.getWidth();
    }

    @Override // android.support.v4.widget.z
    public int a(View view, int i, int i2) {
        if (this.f64a.a(view, 3)) {
            return Math.max(-view.getWidth(), Math.min(i, 0));
        }
        int width = this.f64a.getWidth();
        return Math.max(width - view.getWidth(), Math.min(i, width));
    }

    @Override // android.support.v4.widget.z
    public int b(View view, int i, int i2) {
        return view.getTop();
    }
}
