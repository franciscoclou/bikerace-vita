package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ProgressMessageView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f1378a;

    public ProgressMessageView(Context context, AttributeSet attributeSet, int i) {
        this(context);
        a(context);
    }

    public ProgressMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1378a = null;
        a(context);
    }

    public ProgressMessageView(Context context) {
        super(context);
        this.f1378a = null;
        a(context);
    }

    public void setMessage(String str) {
        this.f1378a.setText(str);
    }

    private void a(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903087, this);
        this.f1378a = (TextView) findViewById(2131296630);
    }
}
