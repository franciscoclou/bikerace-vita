package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ShopItemNoAdsRestore extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View f1382a;
    protected View b;
    protected View c;
    protected View d;

    public ShopItemNoAdsRestore(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1382a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903095, this);
        this.f1382a = findViewById(2131296690);
        this.b = findViewById(2131296695);
        this.c = findViewById(2131296688);
        this.d = findViewById(2131296693);
    }

    public void a(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z) {
        this.c.setVisibility(z ? 0 : 8);
        this.f1382a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener2);
    }

    public void a(boolean z) {
        if (z) {
            this.f1382a.setOnClickListener(null);
            this.f1382a.setClickable(false);
            this.f1382a.setEnabled(false);
        }
    }
}
