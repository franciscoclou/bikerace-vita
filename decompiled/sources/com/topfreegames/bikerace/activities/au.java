package com.topfreegames.bikerace.activities;

import android.view.View;

/* JADX INFO: compiled from: WorldSelectionActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class au implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WorldSelectionActivity f1076a;
    private int b;

    public au(WorldSelectionActivity worldSelectionActivity, int i) {
        this.f1076a = worldSelectionActivity;
        this.b = -1;
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ((c) view.getContext()).a(this.b);
    }
}
