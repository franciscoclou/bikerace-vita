package com.topfreegames.bikerace.e;

import android.view.animation.Animation;

/* JADX INFO: compiled from: BikeUnlockDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f1186a;
    private int b;

    private c(b bVar) {
        this.f1186a = bVar;
        this.b = 0;
    }

    /* synthetic */ c(b bVar, c cVar) {
        this(bVar);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.b--;
        if (this.b <= 0) {
            this.f1186a.e();
            this.f1186a.d();
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        this.b++;
    }
}
