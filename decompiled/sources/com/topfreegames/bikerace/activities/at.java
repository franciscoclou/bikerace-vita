package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.view.View;

/* JADX INFO: compiled from: WorldSelectionActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class at implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WorldSelectionActivity f1075a;
    private int b;

    public at(WorldSelectionActivity worldSelectionActivity, int i) {
        this.f1075a = worldSelectionActivity;
        this.b = -1;
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f1075a.c.e(this.b, this.f1075a.d.b(this.b), false);
        Intent intent = new Intent();
        intent.setClass(view.getContext(), LevelSelectionActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.b);
        this.f1075a.a(intent, 2130968587, 2130968583);
    }
}
