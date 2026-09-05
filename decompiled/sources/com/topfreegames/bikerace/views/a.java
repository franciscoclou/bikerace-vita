package com.topfreegames.bikerace.views;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: compiled from: AchievementNotificationView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends RelativeLayout {
    private static Typeface c = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f1394a;
    private com.topfreegames.bikerace.a.j b;

    public static void a(View view, com.topfreegames.bikerace.a.a aVar, com.topfreegames.bikerace.a.j jVar) {
        if (view != null && aVar != null) {
            new a(view.getContext(), view, jVar).a(aVar.e());
        }
    }

    protected a(Context context, View view, com.topfreegames.bikerace.a.j jVar) {
        super(context);
        this.f1394a = null;
        this.b = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903040, this);
        if (c == null) {
            c = Typeface.createFromAsset(context.getAssets(), getResources().getString(2131099651));
        }
        this.f1394a = (ViewGroup) view;
        this.f1394a.addView(this);
        this.b = jVar;
    }

    protected void a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Achievement cannot be null!");
        }
        TextView textView = (TextView) findViewById(2131296261);
        textView.setText(str);
        textView.setTypeface(c);
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), 2130968584);
        animationLoadAnimation.setAnimationListener(new b(this, null));
        startAnimation(animationLoadAnimation);
    }
}
