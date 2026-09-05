package com.topfreegames.bikerace.activities;

import android.view.View;
import android.view.animation.Animation;

/* JADX INFO: compiled from: WorldSelectionActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class as implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WorldSelectionActivity f1074a;
    private View b;

    public as(WorldSelectionActivity worldSelectionActivity, View view) {
        this.f1074a = worldSelectionActivity;
        this.b = null;
        this.b = view;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.b.setVisibility(8);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}
