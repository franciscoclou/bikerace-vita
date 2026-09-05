package com.topfreegames.bikerace.e;

import android.widget.AbsListView;

/* JADX INFO: compiled from: GiftsDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class w implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ s f1218a;
    private int b;

    private w(s sVar) {
        this.f1218a = sVar;
        this.b = -1;
    }

    /* synthetic */ w(s sVar, w wVar) {
        this(sVar);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i != this.b) {
            this.b = i;
            if (i == 2) {
                this.f1218a.c.a(false);
            } else {
                this.f1218a.c.a(true);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
