package com.topfreegames.bikerace.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: compiled from: ShopItemAchievementLine.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f1417a;
    private ImageView b;
    private View c;

    public s(Context context, com.topfreegames.bikerace.a.a aVar, boolean z, boolean z2) {
        this(context, aVar, z, z2, null);
    }

    public s(Context context, com.topfreegames.bikerace.a.a aVar, boolean z, boolean z2, View.OnClickListener onClickListener) {
        super(context);
        this.f1417a = null;
        this.b = null;
        this.c = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903089, this);
        this.f1417a = (TextView) findViewById(2131296657);
        this.b = (ImageView) findViewById(2131296656);
        this.c = findViewById(2131296658);
        this.f1417a.setText(String.valueOf(z ? "?" : aVar.e()) + ((!z2 || aVar.b()) ? "" : context.getString(2131099996, Integer.valueOf(aVar.d()))));
        setChecked(aVar.b());
        if (onClickListener == null) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            this.c.setOnClickListener(onClickListener);
        }
    }

    public void setChecked(boolean z) {
        this.b.setVisibility(z ? 0 : 4);
    }
}
