package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class HelpActivity extends c {
    private View.OnClickListener b = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.HelpActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HelpActivity.this.a(new Intent(HelpActivity.this, (Class<?>) OptionsActivity.class), 2130968588, 2130968583);
        }
    };

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(2130903068);
            b().setOnClickListener(this.b);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onCreate", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onCreate", e2);
            onBackPressed();
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, HelpActivity.class) && !ah.a(this, HelpActivity.class)) {
                ((BikeRaceApplication) getApplication()).d().f();
                if (hasWindowFocus()) {
                    ((BikeRaceApplication) getApplication()).b().e();
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onResume", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onResume", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        a(new Intent(this, (Class<?>) OptionsActivity.class), 2130968588, 2130968583);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (d() && z) {
            ((BikeRaceApplication) getApplication()).b().e();
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296396);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.HELP;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", HelpActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }
}
