package com.topfreegames.bikerace.activities;

import android.content.Intent;

/* JADX INFO: compiled from: MultiplayerMainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r implements com.topfreegames.bikerace.e.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerMainActivity f1102a;

    private r(MultiplayerMainActivity multiplayerMainActivity) {
        this.f1102a = multiplayerMainActivity;
    }

    /* synthetic */ r(MultiplayerMainActivity multiplayerMainActivity, r rVar) {
        this(multiplayerMainActivity);
    }

    @Override // com.topfreegames.bikerace.e.d
    public void a(com.topfreegames.bikerace.c cVar) {
        Intent intent = new Intent();
        intent.setClass(this.f1102a, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MultiplayerMainActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", true);
        intent.putExtra("com.topfreegames.bikerace.ShopCenter", cVar.ordinal());
        intent.putExtra("com.topfreegames.bikerace.ShopSelect", true);
        this.f1102a.a(intent, 2130968587, 2130968583);
        if (!com.topfreegames.bikerace.ap.e()) {
            ((BikeRaceApplication) this.f1102a.getApplication()).c().a(true);
        }
    }
}
