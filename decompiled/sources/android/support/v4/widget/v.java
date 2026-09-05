package android.support.v4.widget;

import android.support.v4.view.ag;
import android.view.View;

/* JADX INFO: compiled from: SlidingPaneLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class v implements u {
    v() {
    }

    @Override // android.support.v4.widget.u
    public void a(SlidingPaneLayout slidingPaneLayout, View view) {
        ag.a(slidingPaneLayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }
}
