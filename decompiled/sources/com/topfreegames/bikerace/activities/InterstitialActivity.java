package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class InterstitialActivity extends c {
    private int b;
    private int c;
    private boolean d = false;
    private LinearLayout e;

    @Override // com.topfreegames.bikerace.activities.c
    protected void c() {
        i();
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return null;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return null;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        return false;
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        this.b = extras.getInt("com.topfreegames.bikerace.WorldSelected", 1);
        this.c = extras.getInt("com.topfreegames.bikerace.PhaseSelected", 1);
        this.d = this.c > com.topfreegames.bikerace.h.y.b(this.b);
        this.e = new LinearLayout(this);
        this.e.setBackgroundResource(this.d ? 2130837736 : 2130837762);
        setContentView(this.e);
        com.topfreegames.bikerace.g.a aVarG = com.topfreegames.bikerace.g.a.g();
        aVarG.a(new com.topfreegames.bikerace.g.b() { // from class: com.topfreegames.bikerace.activities.InterstitialActivity.1
            @Override // com.topfreegames.bikerace.g.b
            public void a() {
                InterstitialActivity.this.i();
            }
        });
        aVarG.h();
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.topfreegames.bikerace.g.a.g().a((com.topfreegames.bikerace.g.b) null);
    }

    private void g() {
        Intent intent = new Intent();
        intent.setClass(this, PlayActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.b);
        intent.putExtra("com.topfreegames.bikerace.PhaseSelected", this.c);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
        a(intent, 2130968583, 2130968583);
    }

    private void h() {
        Intent intent = new Intent();
        intent.setClass(this, LevelSelectionActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.b);
        a(intent, 2130968583, 2130968583);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.d) {
            h();
        } else {
            g();
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putInt("com.topfreegames.bikerace.WorldSelected", this.b);
            bundle.putInt("com.topfreegames.bikerace.PhaseSelected", this.c);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onSaveInstanceState", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onSaveInstanceState", e2);
        }
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        try {
            super.onRestoreInstanceState(bundle);
            this.b = bundle.getInt("com.topfreegames.bikerace.WorldSelected", 1);
            this.c = bundle.getInt("com.topfreegames.bikerace.PhaseSelected", 1);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onRestoreInstanceState", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onRestoreInstanceState", e2);
        }
    }
}
