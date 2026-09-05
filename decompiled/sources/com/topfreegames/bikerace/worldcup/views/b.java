package com.topfreegames.bikerace.worldcup.views;

import android.view.animation.Animation;

/* JADX INFO: compiled from: WorldCupBikeUnlockDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1480a;
    private int b;

    private b(a aVar) {
        this.f1480a = aVar;
        this.b = 0;
    }

    /* synthetic */ b(a aVar, b bVar) {
        this(aVar);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.b--;
        if (this.b <= 0) {
            if (this.f1480a.i != null) {
                this.f1480a.i.a();
            }
            this.f1480a.cancel();
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
