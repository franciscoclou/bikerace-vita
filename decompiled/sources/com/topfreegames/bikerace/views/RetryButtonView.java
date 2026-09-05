package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class RetryButtonView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f1380a;
    protected View b;
    protected TextView c;

    public RetryButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1380a = 0;
        this.b = null;
        this.c = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903042, this);
        this.b = findViewById(2131296277);
        this.c = (TextView) findViewById(2131296278);
        setCounterValue(this.f1380a);
    }

    public void setCounterValue(int i) {
        this.f1380a = i;
        this.c.setText(Integer.toString(this.f1380a));
    }
}
