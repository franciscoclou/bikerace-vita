package android.support.v4.widget;

import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: SlidingPaneLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class w extends v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Method f71a;
    private Field b;

    w() {
        try {
            this.f71a = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
        } catch (NoSuchMethodException e) {
            Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e);
        }
        try {
            this.b = View.class.getDeclaredField("mRecreateDisplayList");
            this.b.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
        }
    }

    @Override // android.support.v4.widget.v, android.support.v4.widget.u
    public void a(SlidingPaneLayout slidingPaneLayout, View view) {
        if (this.f71a != null && this.b != null) {
            try {
                this.b.setBoolean(view, true);
                this.f71a.invoke(view, (Object[]) null);
            } catch (Exception e) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e);
            }
            super.a(slidingPaneLayout, view);
            return;
        }
        view.invalidate();
    }
}
