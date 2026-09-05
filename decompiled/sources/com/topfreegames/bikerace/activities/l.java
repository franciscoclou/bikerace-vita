package com.topfreegames.bikerace.activities;

import android.widget.AbsListView;

/* JADX INFO: compiled from: FacebookUsersListActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ FacebookUsersListActivity f1096a;
    private int b;

    private l(FacebookUsersListActivity facebookUsersListActivity) {
        this.f1096a = facebookUsersListActivity;
        this.b = -1;
    }

    /* synthetic */ l(FacebookUsersListActivity facebookUsersListActivity, l lVar) {
        this(facebookUsersListActivity);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i != this.b) {
            this.b = i;
            if (i == 2) {
                this.f1096a.i.a(false);
            } else {
                this.f1096a.i.a(true);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
