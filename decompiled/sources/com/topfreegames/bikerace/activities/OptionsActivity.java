package com.topfreegames.bikerace.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.views.ProgressMessageView;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class OptionsActivity extends c implements com.topfreegames.bikerace.multiplayer.g {
    private boolean b = false;
    private com.topfreegames.bikerace.multiplayer.o c = null;
    private View.OnClickListener d = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(OptionsActivity.this, MainActivity.class);
            OptionsActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener e = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(OptionsActivity.this, HelpActivity.class);
            OptionsActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private View.OnClickListener f = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) OptionsActivity.this.getApplication();
            com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
            com.topfreegames.bikerace.v vVarB = bikeRaceApplication.b();
            ToggleButton toggleButton = (ToggleButton) view;
            zVarA.c(toggleButton.isChecked());
            if (toggleButton.isChecked()) {
                vVarB.e();
            } else {
                vVarB.b();
            }
            bikeRaceApplication.d().a(toggleButton.isChecked());
        }
    };
    private View.OnClickListener g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ToggleButton toggleButton = (ToggleButton) view;
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) OptionsActivity.this.getApplication();
            bikeRaceApplication.a().f(toggleButton.isChecked());
            bikeRaceApplication.d().b(toggleButton.isChecked());
        }
    };
    private View.OnClickListener h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((BikeRaceApplication) OptionsActivity.this.getApplication()).a().e(((ToggleButton) view).isChecked());
        }
    };
    private View.OnClickListener i = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ToggleButton toggleButton = (ToggleButton) view;
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) OptionsActivity.this.getApplication();
            bikeRaceApplication.a().d(toggleButton.isChecked());
            bikeRaceApplication.d().c(toggleButton.isChecked());
            OptionsActivity.this.b = true;
        }
    };
    private View.OnClickListener j = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OptionsActivity.this.a(ac.ACCOUNT.ordinal());
        }
    };

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(2130903084);
            final com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplication()).a();
            findViewById(2131296556).setOnClickListener(this.d);
            findViewById(2131296567).setOnClickListener(this.e);
            ToggleButton toggleButton = (ToggleButton) findViewById(2131296558);
            toggleButton.setOnClickListener(this.f);
            toggleButton.setChecked(zVarA.b());
            ToggleButton toggleButton2 = (ToggleButton) findViewById(2131296560);
            toggleButton2.setOnClickListener(this.g);
            toggleButton2.setChecked(zVarA.g());
            ToggleButton toggleButton3 = (ToggleButton) findViewById(2131296564);
            toggleButton3.setOnClickListener(this.h);
            toggleButton3.setChecked(zVarA.f());
            ToggleButton toggleButton4 = (ToggleButton) findViewById(2131296562);
            View viewFindViewById = findViewById(2131296566);
            if (com.topfreegames.bikerace.ap.e()) {
                toggleButton4.setVisibility(8);
                viewFindViewById.setVisibility(4);
                findViewById(2131296561).setVisibility(8);
            } else {
                this.c = ((BikeRaceApplication) getApplication()).c();
                this.c.b((com.topfreegames.bikerace.multiplayer.g) this);
                h();
                if (com.topfreegames.bikerace.ap.r()) {
                    toggleButton4.setChecked(zVarA.d());
                } else {
                    toggleButton4.setVisibility(8);
                    findViewById(2131296561).setVisibility(8);
                }
            }
            bb bbVarA = ((BikeRaceApplication) getApplicationContext()).a(false);
            View viewFindViewById2 = findViewById(2131296568);
            if ((bbVarA.aE() || zVarA.s()) && !com.topfreegames.bikerace.i.a.b().equals(com.topfreegames.bikerace.i.b.ENGLISH)) {
                viewFindViewById2.setVisibility(0);
                viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.9
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            zVarA.B();
                            com.topfreegames.bikerace.i.a.a(com.topfreegames.bikerace.i.a.c(), OptionsActivity.this);
                            OptionsActivity.this.finish();
                            OptionsActivity.this.startActivity(OptionsActivity.this.getIntent());
                        } catch (Exception e) {
                        }
                    }
                });
            } else {
                viewFindViewById2.setVisibility(8);
            }
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

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.d.onClick(null);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296555);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.MULTIPLAYER;
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, OptionsActivity.class) && !ah.a(this, OptionsActivity.class)) {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                bikeRaceApplication.d().c();
                if (hasWindowFocus()) {
                    bikeRaceApplication.b().e();
                }
                g();
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

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (d() && z) {
            ((BikeRaceApplication) getApplication()).b().e();
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onPause() {
        try {
            super.onPause();
            if (this.b) {
                if (((BikeRaceApplication) getApplication()).a().d()) {
                    com.topfreegames.bikerace.push.e.b(getApplicationContext());
                } else {
                    com.topfreegames.bikerace.push.e.c(getApplicationContext());
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onPause", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onPause", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
            if (this.c != null) {
                this.c.a((com.topfreegames.bikerace.multiplayer.g) this);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onDestroy", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onDestroy", e2);
        }
    }

    private void g() {
        ToggleButton toggleButton;
        if (!com.topfreegames.bikerace.ap.e() && (toggleButton = (ToggleButton) findViewById(2131296562)) != null) {
            if (((BikeRaceApplication) getApplication()).e()) {
                toggleButton.setOnClickListener(this.i);
            } else {
                toggleButton.setClickable(false);
                toggleButton.setEnabled(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        View viewFindViewById = findViewById(2131296566);
        if (viewFindViewById != null) {
            if (this.c == null) {
                this.c = ((BikeRaceApplication) getApplication()).c();
            }
            if (this.c.a()) {
                viewFindViewById.setOnClickListener(this.j);
                viewFindViewById.setVisibility(0);
            } else {
                viewFindViewById.setVisibility(8);
            }
        }
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(boolean z) {
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void b(boolean z) {
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(List<String> list) {
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(List<com.topfreegames.bikerace.multiplayer.l> list, int i, int i2, boolean z) {
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(boolean z, boolean z2) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.10
            @Override // java.lang.Runnable
            public void run() {
                OptionsActivity.this.h();
                OptionsActivity.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296569);
        progressMessageView.setMessage(getResources().getString(2131099738));
        progressMessageView.setVisibility(0);
        findViewById(2131296566).setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296569);
        if (progressMessageView != null) {
            progressMessageView.setVisibility(8);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == ac.ACCOUNT.ordinal()) {
            return new com.topfreegames.bikerace.e.ac(this, com.topfreegames.bikerace.m.f.a(this.c.h()), this.c.i(), new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.OptionsActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OptionsActivity.this.c.b((Activity) OptionsActivity.this);
                    OptionsActivity.this.i();
                    com.topfreegames.bikerace.push.e.b(OptionsActivity.this, OptionsActivity.this.c.g());
                    com.topfreegames.bikerace.k.a.a().a("");
                }
            }, true);
        }
        return null;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", OptionsActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }
}
