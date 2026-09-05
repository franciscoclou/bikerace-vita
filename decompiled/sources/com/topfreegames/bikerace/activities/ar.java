package com.topfreegames.bikerace.activities;

import android.view.View;
import android.view.animation.Animation;

/* JADX INFO: compiled from: WorldSelectionActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ar implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WorldSelectionActivity f1073a;
    private View b;

    public ar(WorldSelectionActivity worldSelectionActivity, View view) {
        this.f1073a = worldSelectionActivity;
        this.b = null;
        this.b = view;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        this.b.setVisibility(0);
    }
}
