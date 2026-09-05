package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.view.View;

/* JADX INFO: compiled from: PlayActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ad implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ PlayActivity f1060a;
    private String b;
    private com.topfreegames.bikerace.c c;

    public ad(PlayActivity playActivity, String str, com.topfreegames.bikerace.c cVar) {
        this.f1060a = playActivity;
        this.b = str;
        this.c = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f1060a.c != null) {
            Intent intent = new Intent();
            intent.setClass(this.f1060a, ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", PlayActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.f1060a.c.e());
            intent.putExtra("com.topfreegames.bikerace.PhaseSelected", this.f1060a.c.a());
            intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", !this.f1060a.c.f());
            intent.putExtra("com.topfreegames.bikerace.ShopCenter", this.c.ordinal());
            if (this.b != null) {
                intent.putExtra("com.topfreegames.bikerace.shop.offerId", this.b);
            }
            this.f1060a.a(intent, 2130968587, 2130968583);
            if (!com.topfreegames.bikerace.ap.e()) {
                ((BikeRaceApplication) this.f1060a.getApplication()).c().a(true);
            }
        }
    }
}
