package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class af extends Dialog implements ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Activity f223a;
    private final com.applovin.a.j b;
    private AppLovinAdView c;
    private Runnable d;
    private e e;
    private volatile boolean f;

    af(com.applovin.a.k kVar, Activity activity) {
        super(activity, 16973840);
        this.e = null;
        this.f = false;
        if (kVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.b = kVar.f();
        this.f223a = activity;
        this.d = new d(this);
        this.c = new AppLovinAdView(kVar, com.applovin.a.f.c, activity);
        this.c.setAutoDestroy(false);
        requestWindowFeature(1);
        try {
            getWindow().setFlags(activity.getWindow().getAttributes().flags, activity.getWindow().getAttributes().flags);
        } catch (Exception e) {
            this.b.b("InterstitialAdDialog", "Set window flags failed.", e);
        }
    }

    private void a() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.c.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = new RelativeLayout(this.f223a);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1157627904);
        relativeLayout.addView(this.c);
        setContentView(relativeLayout);
    }

    public void a(com.applovin.a.a aVar) {
        this.f223a.runOnUiThread(new aj(this, aVar));
    }

    public void a(e eVar) {
        this.c.setAdDisplayListener(new ag(this, eVar));
        this.c.setAdClickListener(new ah(this, eVar));
        this.c.setAdVideoPlaybackListener(new ai(this, eVar));
        this.e = eVar;
        eVar.a(true);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.e != null) {
            this.e.i();
        }
        if (this.c != null) {
            this.c.b();
        }
        this.e = null;
        this.c = null;
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }
}
