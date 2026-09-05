package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ShopItemWorldCup extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private RelativeLayout f1384a;
    private TextView b;
    private TextView c;
    private TextView d;
    private Button e;

    public ShopItemWorldCup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903098, this);
        this.c = (TextView) findViewById(2131296719);
        this.d = (TextView) findViewById(2131296718);
        this.b = (TextView) findViewById(2131296720);
        this.e = (Button) findViewById(2131296721);
        this.f1384a = (RelativeLayout) findViewById(2131296716);
    }

    public void setup(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
        this.f1384a.setOnClickListener(onClickListener);
    }

    public void a() {
        this.c.setVisibility(8);
        this.b.setVisibility(8);
        this.d.setVisibility(0);
    }

    public void a(String str) {
        this.b.setText(str);
    }
}
