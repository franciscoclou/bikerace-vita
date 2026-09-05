package com.topfreegames.bikerace.activities;

import android.widget.AbsListView;

/* JADX INFO: compiled from: MultiplayerMainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class u implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerMainActivity f1107a;
    private int b;

    private u(MultiplayerMainActivity multiplayerMainActivity) {
        this.f1107a = multiplayerMainActivity;
        this.b = -1;
    }

    /* synthetic */ u(MultiplayerMainActivity multiplayerMainActivity, u uVar) {
        this(multiplayerMainActivity);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i != this.b) {
            this.b = i;
            if (i == 2) {
                this.f1107a.u.a(false);
            } else {
                this.f1107a.u.a(true);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
