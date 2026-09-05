package android.support.v4.widget;

import android.support.v4.view.ag;
import android.view.View;

/* JADX INFO: compiled from: SlidingPaneLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final View f68a;
    final /* synthetic */ SlidingPaneLayout b;

    q(SlidingPaneLayout slidingPaneLayout, View view) {
        this.b = slidingPaneLayout;
        this.f68a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f68a.getParent() == this.b) {
            ag.a(this.f68a, 0, null);
            this.b.g(this.f68a);
        }
        this.b.t.remove(this);
    }
}
