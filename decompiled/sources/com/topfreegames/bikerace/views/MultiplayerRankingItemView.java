package com.topfreegames.bikerace.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerRankingItemView extends RelativeLayout {
    private static Drawable f = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1376a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private ImageView e;

    public MultiplayerRankingItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, q.DEFAULT);
    }

    public MultiplayerRankingItemView(Context context, q qVar) {
        super(context);
        this.f1376a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903080, this);
        this.f1376a = (ImageView) findViewById(2131296517);
        this.c = (TextView) findViewById(2131296518);
        this.d = (TextView) findViewById(2131296520);
        this.b = (ImageView) findViewById(2131296521);
        this.e = (ImageView) findViewById(2131296516);
        if (f == null) {
            f = this.f1376a.getDrawable();
        }
        if (qVar == q.FOOTER) {
            this.e.setBackgroundResource(2130837822);
        } else if (qVar == q.FOOTER) {
            this.e.setBackgroundResource(2130837820);
        } else {
            this.e.setBackgroundResource(2130837821);
        }
    }

    public void a(String str, int i, int i2, boolean z) {
        this.c.setText(String.valueOf(com.topfreegames.bikerace.m.f.a(str)) + " ");
        this.d.setText(String.valueOf(Integer.toString(i)) + " ");
        if (z) {
            this.e.setBackgroundResource(2130837820);
        } else {
            this.e.setBackgroundResource(2130837821);
        }
        switch (i2) {
            case 1:
                this.b.setBackgroundResource(2130837839);
                this.b.setVisibility(0);
                break;
            case 2:
                this.b.setBackgroundResource(2130837840);
                this.b.setVisibility(0);
                break;
            case 3:
                this.b.setBackgroundResource(2130837841);
                this.b.setVisibility(0);
                break;
            default:
                this.b.setVisibility(8);
                break;
        }
    }

    public void setAvatarImage(Bitmap bitmap) {
        if (bitmap != null) {
            this.f1376a.setImageBitmap(bitmap);
        } else {
            this.f1376a.setImageDrawable(f);
        }
    }
}
