package com.topfreegames.bikerace.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: compiled from: ShopItemLikeView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class t extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f1418a;
    private ImageView b;

    public t(Context context, String str, View.OnClickListener onClickListener) {
        super(context);
        this.f1418a = null;
        this.b = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903090, this);
        this.f1418a = (TextView) findViewById(2131296659);
        this.b = (ImageView) findViewById(2131296660);
        this.f1418a.setText(str);
        this.b.setOnClickListener(onClickListener);
    }
}
