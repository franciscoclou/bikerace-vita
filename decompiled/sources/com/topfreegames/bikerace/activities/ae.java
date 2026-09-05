package com.topfreegames.bikerace.activities;

import android.view.View;
import android.view.animation.Animation;

/* JADX INFO: compiled from: PlayActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ae implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    View f1061a;
    final /* synthetic */ PlayActivity b;

    public ae(PlayActivity playActivity, View view) {
        this.b = playActivity;
        this.f1061a = view;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        this.f1061a.setVisibility(0);
    }
}
