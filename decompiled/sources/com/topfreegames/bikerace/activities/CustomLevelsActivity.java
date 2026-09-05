package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.topfreegames.bikerace.bn;
import com.topfreegames.bikerace.views.CustomGallery;
import com.topfreegames.bikerace.views.ProgressMessageView;
import com.topfreegames.bikerace.views.UserLevelItemView;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomLevelsActivity extends d implements com.topfreegames.bikerace.h.a.f {
    private com.topfreegames.bikerace.multiplayer.g b = new com.topfreegames.bikerace.multiplayer.g() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.1
        @Override // com.topfreegames.bikerace.multiplayer.g
        public void a(boolean z) {
        }

        @Override // com.topfreegames.bikerace.multiplayer.g
        public void a(List<String> list) {
        }

        @Override // com.topfreegames.bikerace.multiplayer.g
        public void a(List<com.topfreegames.bikerace.multiplayer.l> list, int i, int i2, boolean z) {
        }

        @Override // com.topfreegames.bikerace.multiplayer.g
        public void a(boolean z, boolean z2) {
            CustomLevelsActivity.this.k();
            if (z) {
                return;
            }
            CustomLevelsActivity.this.p();
            CustomLevelsActivity.this.H.a(CustomLevelsActivity.this.E, CustomLevelsActivity.this);
        }

        @Override // com.topfreegames.bikerace.multiplayer.g
        public void b(boolean z) {
        }
    };
    private View.OnClickListener c = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.12
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(CustomLevelsActivity.this, WorldSelectionActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", 999);
            CustomLevelsActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener d = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.18
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), LevelSelectionActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", 999);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", CustomLevelsActivity.class);
            CustomLevelsActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private AdapterView.OnItemClickListener e = new AdapterView.OnItemClickListener() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.19
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            com.topfreegames.bikerace.views.v data;
            if (view != null && (data = ((UserLevelItemView) view).getData()) != null) {
                com.topfreegames.bikerace.f.a aVarD = data.d();
                if (data.e()) {
                    CustomLevelsActivity.this.a(f.GIFTS.ordinal());
                    return;
                }
                if (aVarD != null) {
                    CustomLevelsActivity.this.a(f.SHOULD_SEND_GIFT_BACK.ordinal());
                    CustomLevelsActivity.this.I.a(new com.topfreegames.bikerace.f.i(new com.topfreegames.bikerace.f.d(aVarD.c(), com.topfreegames.bikerace.f.b.GIVE_ONE_TRACK), CustomLevelsActivity.this.s));
                    CustomLevelsActivity.this.J.a(new h(CustomLevelsActivity.this, aVarD));
                } else if (CustomLevelsActivity.this.D.c() <= 0) {
                    CustomLevelsActivity.this.a(f.OFFER_TRACKS.ordinal());
                } else {
                    CustomLevelsActivity.this.a(data.a(), new j(CustomLevelsActivity.this, true));
                }
            }
        }
    };
    private TextView.OnEditorActionListener f = new TextView.OnEditorActionListener() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.20
        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 0 || i == 6) {
                String string = textView.getText().toString();
                if (string.length() > 0) {
                    if (string.length() != 6) {
                        textView.setError(CustomLevelsActivity.this.getResources().getString(2131099925));
                    } else {
                        CustomLevelsActivity.this.b(string, new j(CustomLevelsActivity.this, false));
                    }
                }
            }
            ((InputMethodManager) CustomLevelsActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 0);
            textView.setText("");
            textView.clearFocus();
            return true;
        }
    };
    private View.OnClickListener g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.21
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CustomLevelsActivity.this.a(f.OFFER_TRACKS.ordinal());
        }
    };
    private View.OnClickListener h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.22
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (((BikeRaceApplication) CustomLevelsActivity.this.getApplication()).e()) {
                CustomLevelsActivity.this.a(f.LEVEL_EDITOR_INFO.ordinal());
            }
        }
    };
    private com.topfreegames.bikerace.e.f i = new com.topfreegames.bikerace.e.f() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.23
        @Override // com.topfreegames.bikerace.e.f
        public void a() {
            Intent intent = new Intent();
            intent.setClass(CustomLevelsActivity.this, ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", CustomLevelsActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", 999);
            intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", CustomLevelsActivity.this.getString(2131099853));
            CustomLevelsActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private com.topfreegames.bikerace.e.f j = new com.topfreegames.bikerace.e.f() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.24
        @Override // com.topfreegames.bikerace.e.f
        public void a() {
            CustomLevelsActivity.this.d("");
        }
    };
    private com.topfreegames.bikerace.e.f k = new com.topfreegames.bikerace.e.f() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.2
        @Override // com.topfreegames.bikerace.e.f
        public void a() {
            CustomLevelsActivity.this.c("");
        }
    };
    private com.topfreegames.bikerace.e.z l = new com.topfreegames.bikerace.e.z() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.3
        @Override // com.topfreegames.bikerace.e.z
        public void a(final com.topfreegames.bikerace.f.a aVar, final com.topfreegames.bikerace.e.y yVar) {
            CustomLevelsActivity.this.E.a(aVar.c(), CustomLevelsActivity.this, new com.topfreegames.bikerace.f.f() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.3.1
                @Override // com.topfreegames.bikerace.f.f
                public void a() {
                    CustomLevelsActivity.this.E.a(aVar);
                    CustomLevelsActivity.this.q();
                    yVar.a();
                }

                @Override // com.topfreegames.bikerace.f.f
                public void b() {
                    yVar.b();
                }
            });
        }
    };
    private com.topfreegames.bikerace.e.x m = new com.topfreegames.bikerace.e.x() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.4
        @Override // com.topfreegames.bikerace.e.x
        public void a(final com.topfreegames.bikerace.f.a aVar, final com.topfreegames.bikerace.e.y yVar) {
            CustomLevelsActivity.this.a(aVar.h(), new j(CustomLevelsActivity.this, false, new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.4.1
                @Override // java.lang.Runnable
                public void run() {
                    CustomLevelsActivity.this.E.a(aVar);
                    CustomLevelsActivity.this.q();
                    yVar.a();
                }
            }, new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.4.2
                @Override // java.lang.Runnable
                public void run() {
                    yVar.b();
                }
            }));
        }
    };
    private com.topfreegames.bikerace.e.t n = new com.topfreegames.bikerace.e.t() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.5
        @Override // com.topfreegames.bikerace.e.t
        public void a(com.topfreegames.bikerace.f.a aVar, com.topfreegames.bikerace.e.y yVar) {
            new com.topfreegames.bikerace.h.a.b(CustomLevelsActivity.this.getApplicationContext()).e();
            CustomLevelsActivity.this.E.a(aVar);
            CustomLevelsActivity.this.q();
            CustomLevelsActivity.this.o();
            yVar.a();
        }
    };
    private com.topfreegames.bikerace.e.u o = new com.topfreegames.bikerace.e.u() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.6
        @Override // com.topfreegames.bikerace.e.u
        public void a() {
            CustomLevelsActivity.this.a(f.EXPIRE_INFO.ordinal());
        }
    };
    private com.topfreegames.bikerace.e.o p = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.7
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            if (CustomLevelsActivity.this.F == null || CustomLevelsActivity.this.F.a()) {
                return;
            }
            CustomLevelsActivity.this.r();
        }
    };
    private com.topfreegames.bikerace.e.o q = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.8
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            CustomLevelsActivity.this.I.a(CustomLevelsActivity.this.E, CustomLevelsActivity.this);
        }
    };
    private com.topfreegames.bikerace.e.o r = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.9
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            CustomLevelsActivity.this.I.a();
            CustomLevelsActivity.this.J.a(CustomLevelsActivity.this.E, CustomLevelsActivity.this);
        }
    };
    private com.topfreegames.bikerace.f.f s = new com.topfreegames.bikerace.f.f() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.10
        @Override // com.topfreegames.bikerace.f.f
        public void a() {
            CustomLevelsActivity.this.J.a(CustomLevelsActivity.this.E, CustomLevelsActivity.this);
        }

        @Override // com.topfreegames.bikerace.f.f
        public void b() {
            CustomLevelsActivity.this.J.a(CustomLevelsActivity.this.E, CustomLevelsActivity.this);
        }
    };
    private g t = null;
    private com.topfreegames.bikerace.h.a.n u = null;
    private CustomGallery v = null;
    private TextView w = null;
    private EditText x = null;
    private TextView y = null;
    private View z = null;
    private View A = null;
    private View B = null;
    private ImageView C = null;
    private com.topfreegames.bikerace.h.a.b D = null;
    private com.topfreegames.bikerace.f.e E = null;
    private com.topfreegames.bikerace.multiplayer.o F = null;
    private com.topfreegames.bikerace.views.v G = null;
    private com.topfreegames.bikerace.f.k H = new com.topfreegames.bikerace.f.k();
    private com.topfreegames.bikerace.f.k I = new com.topfreegames.bikerace.f.k();
    private com.topfreegames.bikerace.f.k J = new com.topfreegames.bikerace.f.k();

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.MULTIPLAYER;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296300);
    }

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (t()) {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                intent.putExtra("com.topfreegames.bikerace.UserLevelsLocked", true);
                a(intent, 2130968588, 2130968583);
                finish();
            } else {
                setContentView(2130903057);
                this.u = new com.topfreegames.bikerace.h.a.n(this);
                this.t = new g(this, this, 0);
                this.v = (CustomGallery) findViewById(2131296309);
                this.v.setOnItemClickListener(this.e);
                this.v.setAdapter((SpinnerAdapter) this.t);
                g();
                this.x = (EditText) findViewById(2131296303);
                this.x.clearFocus();
                this.x.setOnEditorActionListener(this.f);
                this.B = findViewById(2131296302);
                findViewById(2131296308).setOnClickListener(this.d);
                this.A = findViewById(2131296307);
                this.A.setOnClickListener(this.h);
                this.C = (ImageView) findViewById(2131296305);
                this.w = (TextView) findViewById(2131296310);
                this.y = (TextView) findViewById(2131296306);
                this.z = findViewById(2131296304);
                this.z.setOnClickListener(this.g);
                this.D = new com.topfreegames.bikerace.h.a.b(this);
                this.F = ((BikeRaceApplication) getApplication()).c();
                this.F.b(this.b);
                if (this.F.a()) {
                    p();
                } else {
                    new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.11
                        @Override // java.lang.Runnable
                        public void run() {
                            CustomLevelsActivity.this.F.c(CustomLevelsActivity.this);
                        }
                    }).start();
                }
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

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStart() {
        try {
            super.onStart();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onStart", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onStart", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, WorldSelectionActivity.class) && !ah.a(this, WorldSelectionActivity.class)) {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                bikeRaceApplication.d().d();
                if (hasWindowFocus()) {
                    bikeRaceApplication.b().e();
                }
                s();
                l();
                o();
                q();
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

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStop() {
        try {
            super.onStop();
            this.u.a();
            com.topfreegames.bikerace.h.a.d.a();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onStop", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onStop", e2);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (d() && z) {
            ((BikeRaceApplication) getApplication()).b().e();
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.c.onClick(null);
    }

    private void g() {
        if (((BikeRaceApplication) getApplication()).e()) {
            h();
            com.topfreegames.bikerace.h.a.d.a(this);
        }
    }

    @Override // com.topfreegames.bikerace.h.a.f
    public void a(com.topfreegames.bikerace.h.a.a[] aVarArr) {
        if (aVarArr == null || aVarArr.length <= 0) {
            n();
            return;
        }
        com.topfreegames.bikerace.h.a.q qVarA = com.topfreegames.bikerace.h.a.q.a();
        for (int i = 0; i < aVarArr.length; i++) {
            if (!qVarA.b(aVarArr[i].a())) {
                this.t.add(new com.topfreegames.bikerace.views.v(aVarArr[i]));
            }
        }
        this.t.a();
        if (this.t.getCount() <= 0) {
            m();
        }
        this.t.notifyDataSetChanged();
        k();
    }

    private void h() {
        b(getString(2131099911));
    }

    private void i() {
        b(getString(2131099912));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        b(getString(2131099728));
    }

    private void b(final String str) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.13
            @Override // java.lang.Runnable
            public void run() {
                ProgressMessageView progressMessageView = (ProgressMessageView) CustomLevelsActivity.this.findViewById(2131296311);
                if (progressMessageView != null && str != null) {
                    progressMessageView.setMessage(str);
                    progressMessageView.setVisibility(0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.14
            @Override // java.lang.Runnable
            public void run() {
                ProgressMessageView progressMessageView = (ProgressMessageView) CustomLevelsActivity.this.findViewById(2131296311);
                if (progressMessageView != null) {
                    progressMessageView.setVisibility(8);
                }
            }
        });
    }

    private void l() {
        boolean zE = ((BikeRaceApplication) getApplication()).e();
        this.v.setVisibility(zE ? 0 : 8);
        this.w.setVisibility(zE ? 8 : 0);
        this.w.setText(getString(2131099914));
        this.x.setVisibility(zE ? 0 : 4);
        this.B.setVisibility(zE ? 0 : 4);
        this.A.setVisibility(zE ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.v.setVisibility(8);
        this.w.setText(getString(2131099915));
        this.w.setVisibility(0);
    }

    private void n() {
        this.v.setVisibility(8);
        this.w.setText(getString(2131099926));
        this.w.setVisibility(0);
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.15
            @Override // java.lang.Runnable
            public void run() {
                CustomLevelsActivity.this.b(CustomLevelsActivity.this.D.a());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i > 0) {
            this.y.setVisibility(0);
            this.C.setImageResource(2130837920);
            this.y.setText(String.valueOf(Integer.toString(i)) + " ");
        } else {
            this.y.setVisibility(8);
            this.C.setImageResource(2130837921);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == f.OFFER_TRACKS.ordinal()) {
            return new com.topfreegames.bikerace.e.e(this, this.i, this.j, this.k);
        }
        if (i == f.ERROR_DOWNLOAD_TRACK.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099916), getString(2131099659), null);
        }
        if (i == f.ALREADY_HAS.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099917), getString(2131099659), null);
        }
        if (i == f.LEVEL_EDITOR_INFO.ordinal()) {
            return new com.topfreegames.bikerace.e.p(this, "http://s3.topfreegames.com/bikerace/assets/img/level-editor-splash-iphone.png");
        }
        if (i == f.NEED_FB_LOGIN.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099928), getString(2131099929), getString(2131099930), this.p, null);
        }
        if (i == f.GIFTS.ordinal()) {
            return new com.topfreegames.bikerace.e.s(this, this.E.b(), this.E.c(), this.l, this.n, this.m, this.o);
        }
        if (i == f.SHOULD_SEND_GIFT_BACK.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099931), getString(2131099944), getString(2131099945), this.q, this.r);
        }
        if (i == f.EXPIRE_INFO.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099933), getString(2131099659), null);
        }
        if (i == f.GIFT_OFFLINE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099938), getString(2131099659), null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplicationContext()).a();
        zVarA.a(999, i, 0);
        zVarA.c(999, i);
        Intent intent = new Intent();
        intent.setClass(this, PlayActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", 999);
        intent.putExtra("com.topfreegames.bikerace.PhaseSelected", i);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
        ((BikeRaceApplication) getApplication()).b().a();
        a(intent, 2130968583, 2130968583);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", CustomLevelsActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (!((BikeRaceApplication) getApplicationContext()).e()) {
            a(f.GIFT_OFFLINE.ordinal());
            return;
        }
        if (this.F != null) {
            if (this.F.a() && this.E != null) {
                this.E.a(str, this, null);
            } else {
                this.H.a(new com.topfreegames.bikerace.f.i(new com.topfreegames.bikerace.f.d(str, com.topfreegames.bikerace.f.b.GIVE_ONE_TRACK), null));
                a(f.NEED_FB_LOGIN.ordinal());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (!((BikeRaceApplication) getApplicationContext()).e()) {
            a(f.GIFT_OFFLINE.ordinal());
            return;
        }
        if (this.F != null) {
            if (this.F.a() && this.E != null) {
                this.E.b(str, this, null);
            } else {
                this.H.a(new com.topfreegames.bikerace.f.i(new com.topfreegames.bikerace.f.d(str, com.topfreegames.bikerace.f.b.ASK_TRACK), null));
                a(f.NEED_FB_LOGIN.ordinal());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        String strG = this.F.g();
        if (this.E == null) {
            this.E = new com.topfreegames.bikerace.f.e(strG, getApplicationContext());
        }
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.E != null) {
            this.E.a(new com.topfreegames.bikerace.f.g() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.16
                @Override // com.topfreegames.bikerace.f.g
                public void a(final com.topfreegames.bikerace.f.a[] aVarArr) {
                    CustomLevelsActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int length = aVarArr.length;
                            for (int i = 0; i < length; i++) {
                                com.topfreegames.bikerace.f.a aVar = aVarArr[i];
                                if (aVar.g() == com.topfreegames.bikerace.f.b.GIVE_SPECIFIC_TRACK && !aVar.e()) {
                                    CustomLevelsActivity.this.t.add(new com.topfreegames.bikerace.views.v(aVar));
                                }
                            }
                            if (CustomLevelsActivity.this.G != null) {
                                CustomLevelsActivity.this.t.remove(CustomLevelsActivity.this.G);
                                CustomLevelsActivity.this.G = null;
                            }
                            if (CustomLevelsActivity.this.E.d() > 0) {
                                CustomLevelsActivity.this.G = com.topfreegames.bikerace.views.v.a(aVarArr[0]);
                                CustomLevelsActivity.this.t.add(CustomLevelsActivity.this.G);
                            }
                            CustomLevelsActivity.this.t.a();
                            CustomLevelsActivity.this.t.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.topfreegames.bikerace.activities.CustomLevelsActivity$17] */
    public void r() {
        new Thread() { // from class: com.topfreegames.bikerace.activities.CustomLevelsActivity.17
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    CustomLevelsActivity.this.F.b(CustomLevelsActivity.this.b);
                    CustomLevelsActivity.this.F.a(CustomLevelsActivity.this);
                    CustomLevelsActivity.this.j();
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) CustomLevelsActivity.this.getApplication()).d().a(getClass().getName(), "peformFacebookLogin", e);
                    CustomLevelsActivity.this.k();
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) CustomLevelsActivity.this.getApplication()).d().a(getClass().getName(), "peformFacebookLogin", e2);
                    CustomLevelsActivity.this.k();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, j jVar) {
        if (str != null) {
            com.topfreegames.bikerace.h.a.d.a(str, jVar);
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, j jVar) {
        if (str != null) {
            com.topfreegames.bikerace.h.a.d.b(str, jVar);
            i();
        }
    }

    private void s() {
        try {
            Uri data = getIntent().getData();
            if (data != null) {
                data.getScheme();
                if ("www.bikerace.com".equals(data.getHost())) {
                    i();
                    List<String> pathSegments = data.getPathSegments();
                    if (pathSegments.size() == 2 && pathSegments.get(0).toLowerCase().equals("tracks")) {
                        com.topfreegames.bikerace.h.a.d.b(pathSegments.get(1), new j(this, false));
                    }
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "checkIntentForNewGameFromLink", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "checkIntentForNewGameFromLink", e2);
        }
    }

    private boolean t() {
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplication()).a();
        com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) getApplication()).c();
        boolean zA = zVarA.a(999);
        if (zA) {
            if (zVarA.p() >= bn.a(999).f1162a && oVarC.k() >= bn.a(999).c && oVarC.o() >= bn.a(999).b) {
                zVarA.f(999);
                return false;
            }
            return zA;
        }
        return zA;
    }
}
