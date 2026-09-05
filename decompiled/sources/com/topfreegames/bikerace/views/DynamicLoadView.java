package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class DynamicLoadView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1362a;

    protected abstract void c();

    public DynamicLoadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1362a = false;
    }

    public DynamicLoadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1362a = false;
    }

    public DynamicLoadView(Context context) {
        super(context);
        this.f1362a = false;
    }

    public void a() {
        if (!this.f1362a) {
            c();
            this.f1362a = true;
        }
    }

    public boolean b() {
        return this.f1362a;
    }
}
