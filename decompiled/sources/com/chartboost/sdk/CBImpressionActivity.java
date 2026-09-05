package com.chartboost.sdk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class CBImpressionActivity extends Activity {
    public static final String PARAM_FULLSCREEN = "paramFullscreen";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Chartboost f367a;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra(PARAM_FULLSCREEN, false)) {
            getWindow().addFlags(1024);
        }
        getWindow().setWindowAnimations(0);
        setContentView(new RelativeLayout(this));
        this.f367a = Chartboost.sharedChartboost();
        this.f367a.a(this);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.f367a.a((Activity) this);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.f367a.b(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.f367a.c(this);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.f367a.b()) {
            super.onBackPressed();
        }
    }
}
