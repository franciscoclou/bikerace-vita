package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AppLovinAdView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected a f159a;

    public AppLovinAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(null, null, context, attributeSet);
    }

    public AppLovinAdView(com.applovin.a.f fVar, Activity activity) {
        super(activity);
        Log.d("AppLovinSdk", "Created new AdView");
        a(fVar, null, activity, null);
    }

    public AppLovinAdView(com.applovin.a.k kVar, com.applovin.a.f fVar, Activity activity) {
        super(activity);
        a(fVar, kVar, activity, null);
    }

    private void a(AttributeSet attributeSet, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int iApplyDimension = (int) TypedValue.applyDimension(1, 50.0f, displayMetrics);
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(220, 220, 220));
        textView.setTextColor(-16777216);
        textView.setText("AppLovin Ad");
        textView.setGravity(17);
        addView(textView, i, iApplyDimension);
    }

    private void a(com.applovin.a.f fVar, com.applovin.a.k kVar, Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            a(attributeSet, context);
            return;
        }
        com.applovin.impl.adview.a aVar = new com.applovin.impl.adview.a();
        aVar.a(this, context, fVar, kVar, attributeSet);
        this.f159a = aVar;
    }

    public void a() {
        if (this.f159a != null) {
            this.f159a.a();
        } else {
            Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
        }
    }

    public void a(com.applovin.a.a aVar) {
        if (this.f159a != null) {
            this.f159a.a(aVar);
        }
    }

    public void b() {
        if (this.f159a != null) {
            this.f159a.b();
        }
    }

    public com.applovin.a.f getSize() {
        if (this.f159a != null) {
            return this.f159a.c();
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (this.f159a != null) {
            this.f159a.d();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.f159a != null) {
            this.f159a.a(i);
        }
    }

    public void setAdClickListener(com.applovin.a.b bVar) {
        if (this.f159a != null) {
            this.f159a.a(bVar);
        }
    }

    public void setAdDisplayListener(com.applovin.a.c cVar) {
        if (this.f159a != null) {
            this.f159a.a(cVar);
        }
    }

    public void setAdLoadListener(com.applovin.a.d dVar) {
        if (this.f159a != null) {
            this.f159a.a(dVar);
        }
    }

    public void setAdVideoPlaybackListener(com.applovin.a.i iVar) {
        if (this.f159a != null) {
            this.f159a.a(iVar);
        }
    }

    public void setAutoDestroy(boolean z) {
        if (this.f159a != null) {
            this.f159a.a(z);
        }
    }
}
