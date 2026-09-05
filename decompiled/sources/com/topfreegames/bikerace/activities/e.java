package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.View;
import java.util.Date;

/* JADX INFO: compiled from: BaseMultiplayerActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class e extends d {
    private Bitmap b = null;

    protected e() {
    }

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            ((BikeRaceApplication) getApplication()).c().d(this);
        } catch (Exception e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onResumeBMA", e);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.b != null) {
            this.b.recycle();
            this.b = null;
            System.gc();
        }
    }

    protected void a(final com.topfreegames.bikerace.multiplayer.l lVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.e.1
            @Override // java.lang.Runnable
            public void run() {
                e.this.g();
                com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) e.this.getApplication()).c();
                oVarC.n();
                oVarC.a(false);
                Intent intent = new Intent();
                intent.setClass(this, PlayActivity.class);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", lVar.q());
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", lVar.d());
                intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", true);
                intent.putExtra("com.topfreegames.bikerace.MultiplayerGameId", lVar.b());
                intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
                e.this.startActivity(intent);
                e.this.overridePendingTransition(2130968583, 2130968583);
                e.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        b(b());
        View viewB = b();
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        this.b = com.topfreegames.engine.b.a.a(getResources(), 2130837762, defaultDisplay.getWidth(), defaultDisplay.getHeight());
        viewB.setBackgroundDrawable(new BitmapDrawable(this.b));
        viewB.setVisibility(0);
        b().invalidate();
    }
}
