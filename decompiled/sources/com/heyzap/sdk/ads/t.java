package com.heyzap.sdk.ads;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/* JADX INFO: compiled from: InterstitialWebView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class t extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public FrameLayout f798a;
    public s b;
    final /* synthetic */ r c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(r rVar, Context context) {
        super(context);
        this.c = rVar;
        setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        setGravity(17);
        this.f798a = new FrameLayout(context);
        com.heyzap.internal.l.b(context, 10);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(9);
        layoutParams.addRule(10);
        addView(this.f798a, layoutParams);
        this.b = new s(rVar, context);
        this.b.setVisibility(0);
        this.b.setVerticalScrollBarEnabled(false);
        this.b.setHorizontalScrollBarEnabled(false);
        this.b.setScrollBarStyle(33554432);
        this.b.setBackgroundColor(0);
        this.f798a.addView(this.b, new FrameLayout.LayoutParams(-1, -1));
    }
}
