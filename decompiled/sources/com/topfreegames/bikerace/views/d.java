package com.topfreegames.bikerace.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: compiled from: EasterEggLocationItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1397a;
    private TextView b;

    public d(Context context) {
        super(context);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903061, this);
        this.f1397a = (ImageView) findViewById(2131296338);
        this.b = (TextView) findViewById(2131296339);
    }

    public void a(String str, boolean z, int i) {
        int i2;
        switch (i % 9) {
            case 1:
                i2 = 2130837726;
                break;
            case 2:
                i2 = 2130837727;
                break;
            case 3:
                i2 = 2130837728;
                break;
            case 4:
                i2 = 2130837729;
                break;
            case 5:
                i2 = 2130837730;
                break;
            case 6:
                i2 = 2130837731;
                break;
            case 7:
                i2 = 2130837732;
                break;
            case 8:
                i2 = 2130837733;
                break;
            default:
                i2 = 2130837725;
                break;
        }
        ImageView imageView = this.f1397a;
        if (!z) {
            i2 = 2130837734;
        }
        imageView.setImageResource(i2);
        this.b.setText(str);
    }
}
