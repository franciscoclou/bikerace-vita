package com.heyzap.sdk.ads;

import android.R;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class HeyzapVideoActivity extends a {
    protected r f;
    protected e g;
    protected FrameLayout h;
    private int i = 0;

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void a(String str, String str2) {
        super.a(str, str2);
    }

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void e() {
        super.e();
    }

    @Override // com.heyzap.sdk.ads.a, android.app.Activity, android.content.ComponentCallbacks
    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.heyzap.sdk.ads.a, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.heyzap.internal.l.b() >= 9) {
            super.setRequestedOrientation(6);
        } else {
            super.setRequestedOrientation(0);
        }
    }

    @Override // com.heyzap.sdk.ads.a
    public Boolean a() {
        AnonymousClass1 anonymousClass1 = null;
        this.h = new FrameLayout(this);
        this.h.setBackgroundColor(0);
        this.f = new r(this, new n(this));
        this.f.a((w) this.f762a);
        this.g = new e(this, new m(this));
        this.g.b = ((w) this.f762a).o();
        this.g.f780a = ((w) this.f762a).n();
        this.g.c = ((w) this.f762a).p();
        this.g.d = Integer.valueOf(((w) this.f762a).q());
        this.g.a((w) this.f762a);
        this.h.addView(this.g, new FrameLayout.LayoutParams(-1, -1));
        this.i = 2;
        if (u.h != null) {
            u.h.a();
        }
        return true;
    }

    @Override // com.heyzap.sdk.ads.a
    public View b() {
        this.h.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return this.h;
    }

    protected void a(int i) {
        View view;
        View view2 = null;
        if (i != this.i) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            AnimationUtils.loadAnimation(this, R.anim.fade_out);
            switch (this.i) {
                case 1:
                    view = this.f;
                    break;
                case 2:
                    view = this.g;
                    break;
                default:
                    view = null;
                    break;
            }
            switch (i) {
                case 1:
                    view2 = this.f;
                    view2.invalidate();
                    if (u.h != null) {
                        u.h.b();
                    }
                    break;
                case 2:
                    view2 = this.g;
                    if (u.h != null) {
                        u.h.a();
                    }
                    break;
            }
            if (view != null && view2 != null) {
                this.h.addView(view2, new FrameLayout.LayoutParams(-1, -1));
                view2.startAnimation(animationLoadAnimation);
                this.h.removeView(view);
                this.i = i;
            }
        }
    }

    @Override // com.heyzap.sdk.ads.a
    public void c() {
        Boolean boolValueOf = Boolean.valueOf(this.d == 2);
        ((w) this.f762a).a(boolValueOf, this.g.g(), this.g.h(), this.e);
        if (u.i != null && boolValueOf.booleanValue()) {
            if (this.e.booleanValue()) {
                u.i.a();
            } else {
                u.i.b();
            }
        }
        super.c();
        this.f.a();
        this.g.d();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        if (Build.VERSION.SDK_INT < 11 && this.i == 2) {
            if (((w) this.f762a).r().booleanValue()) {
                a(1);
            } else {
                c();
            }
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.g != null) {
            this.g.e();
        }
    }
}
