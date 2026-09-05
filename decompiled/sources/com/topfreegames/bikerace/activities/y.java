package com.topfreegames.bikerace.activities;

import android.widget.AbsListView;

/* JADX INFO: compiled from: MultiplayerRankingActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class y implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerRankingActivity f1111a;
    private int b;

    private y(MultiplayerRankingActivity multiplayerRankingActivity) {
        this.f1111a = multiplayerRankingActivity;
        this.b = -1;
    }

    /* synthetic */ y(MultiplayerRankingActivity multiplayerRankingActivity, y yVar) {
        this(multiplayerRankingActivity);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i != this.b) {
            this.b = i;
            if (i == 2) {
                this.f1111a.p.a(false);
            } else {
                this.f1111a.p.a(true);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
