package com.chartboost.sdk.impl;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class t extends LinearLayout implements u.a {
    private static /* synthetic */ int[] d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f491a;
    private v b;
    private w c;

    static /* synthetic */ int[] b() {
        int[] iArr = d;
        if (iArr == null) {
            iArr = new int[CBOrientation.Difference.valuesCustom().length];
            try {
                iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            d = iArr;
        }
        return iArr;
    }

    public t(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        setGravity(17);
        this.f491a = new TextView(getContext());
        this.f491a.setTextColor(-1);
        this.f491a.setTextSize(2, 16.0f);
        this.f491a.setTypeface(null, 1);
        this.f491a.setText("Loading...");
        this.f491a.setGravity(17);
        this.b = new v(context, this.f491a);
        this.c = new w(getContext());
        addView(this.b);
        addView(this.c);
        a();
    }

    @Override // com.chartboost.sdk.impl.u.a
    public void a() {
        removeView(this.b);
        removeView(this.c);
        float f = getContext().getResources().getDisplayMetrics().density;
        int iRound = Math.round(20.0f * f);
        switch (b()[Chartboost.sharedChartboost().getForcedOrientationDifference().ordinal()]) {
            case 2:
                setOrientation(0);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(f * 32.0f), -1);
                layoutParams.setMargins(iRound, iRound, 0, iRound);
                addView(this.c, layoutParams);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams2.setMargins(iRound, iRound, iRound, iRound);
                addView(this.b, layoutParams2);
                break;
            case 3:
                setOrientation(1);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
                layoutParams3.setMargins(iRound, iRound, iRound, 0);
                addView(this.c, layoutParams3);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams4.setMargins(iRound, iRound, iRound, iRound);
                addView(this.b, layoutParams4);
                break;
            case 4:
                setOrientation(0);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams5.setMargins(iRound, iRound, 0, iRound);
                addView(this.b, layoutParams5);
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(Math.round(f * 32.0f), -1);
                layoutParams6.setMargins(iRound, iRound, iRound, iRound);
                addView(this.c, layoutParams6);
                break;
            default:
                setOrientation(1);
                LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams7.setMargins(iRound, iRound, iRound, 0);
                addView(this.b, layoutParams7);
                LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
                layoutParams8.setMargins(iRound, iRound, iRound, iRound);
                addView(this.c, layoutParams8);
                break;
        }
    }
}
