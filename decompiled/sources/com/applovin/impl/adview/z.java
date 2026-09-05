package com.applovin.impl.adview;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class z implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ y f251a;

    z(y yVar) {
        this.f251a = yVar;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2) {
            return true;
        }
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}
