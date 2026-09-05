package com.topfreegames.bikerace.worldcup.a;

import android.view.View;

/* JADX INFO: compiled from: WorldCupGemShopMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f1424a;
    final /* synthetic */ a b;

    public b(a aVar, String str) {
        this.b = aVar;
        this.f1424a = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.b.f1432a != null) {
            this.b.f1432a.e(this.f1424a);
        }
    }
}
