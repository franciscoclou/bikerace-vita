package com.topfreegames.bikerace.activities;

import android.view.View;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class an implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ShopActivity f1069a;
    private com.topfreegames.bikerace.c b;

    public an(ShopActivity shopActivity, com.topfreegames.bikerace.c cVar) {
        this.f1069a = shopActivity;
        this.b = com.topfreegames.bikerace.c.REGULAR;
        this.b = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) this.f1069a.getApplication()).a();
        com.topfreegames.bikerace.c cVar = zVarA.e() != this.b ? this.b : com.topfreegames.bikerace.c.REGULAR;
        zVarA.c(cVar);
        ((ShopActivity) view.getContext()).c(cVar);
    }
}
