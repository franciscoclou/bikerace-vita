package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.topfreegames.bikerace.views.CustomSnappingHorizontalScrollView;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class LevelSelectionActivity extends c {
    private int b = -1;
    private Bitmap c = null;
    private com.topfreegames.bikerace.z d = null;
    private CustomSnappingHorizontalScrollView e = null;
    private boolean f = false;
    private int g = -1;
    private TextView h = null;
    private TextView i = null;
    private TextView j = null;
    private com.topfreegames.bikerace.views.i k = new com.topfreegames.bikerace.views.i() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.1
        @Override // com.topfreegames.bikerace.views.i
        public void a(int i) {
            ((BikeRaceApplication) LevelSelectionActivity.this.getApplication()).d().f(LevelSelectionActivity.this.b, i, false);
            Intent intent = new Intent();
            intent.setClass(LevelSelectionActivity.this, PlayActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", LevelSelectionActivity.this.b);
            intent.putExtra("com.topfreegames.bikerace.PhaseSelected", i);
            intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
            intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
            LevelSelectionActivity.this.g();
            ((BikeRaceApplication) LevelSelectionActivity.this.getApplication()).b().a();
            LevelSelectionActivity.this.a(intent, 2130968583, 2130968583);
        }
    };
    private com.topfreegames.bikerace.views.h l = new com.topfreegames.bikerace.views.h() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.3
        @Override // com.topfreegames.bikerace.views.h
        public void a(int i) {
            LevelSelectionActivity.this.g = i;
            LevelSelectionActivity.this.a(o.DELETE_CONFIRMATION.ordinal());
        }
    };
    private View.OnClickListener m = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            if (LevelSelectionActivity.this.b == 999) {
                intent.setClass(LevelSelectionActivity.this, CustomLevelsActivity.class);
            } else {
                intent.setClass(LevelSelectionActivity.this, WorldSelectionActivity.class);
            }
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", LevelSelectionActivity.this.b);
            LevelSelectionActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener n = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", LevelSelectionActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", LevelSelectionActivity.this.b);
            LevelSelectionActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private View.OnClickListener o = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LevelSelectionActivity.this.e.b();
        }
    };
    private View.OnClickListener p = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LevelSelectionActivity.this.e.c();
        }
    };
    private View.OnClickListener q = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LevelSelectionActivity.this.f = !LevelSelectionActivity.this.f;
            LevelSelectionActivity.this.h.setText(LevelSelectionActivity.this.f ? LevelSelectionActivity.this.getString(2131099954) : LevelSelectionActivity.this.getString(2131099953));
            LevelSelectionActivity.this.h();
        }
    };
    private com.topfreegames.bikerace.e.o r = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.9
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            LevelSelectionActivity.this.b(LevelSelectionActivity.this.g);
        }
    };

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (bundle != null) {
                this.b = bundle.getInt("com.topfreegames.bikerace.WorldSelected");
            } else {
                this.b = getIntent().getExtras().getInt("com.topfreegames.bikerace.WorldSelected");
            }
            setContentView(2130903069);
            findViewById(2131296399).setOnClickListener(this.m);
            View viewFindViewById = findViewById(2131296404);
            this.h = (TextView) findViewById(2131296405);
            View viewFindViewById2 = findViewById(2131296403);
            if (this.b == 999) {
                viewFindViewById.setVisibility(0);
                viewFindViewById.setOnClickListener(this.q);
                viewFindViewById2.setVisibility(8);
            } else {
                viewFindViewById.setVisibility(8);
                if (com.topfreegames.bikerace.ap.m()) {
                    viewFindViewById2.setOnClickListener(this.n);
                    viewFindViewById2.setVisibility(0);
                } else {
                    viewFindViewById2.setVisibility(4);
                }
            }
            this.i = (TextView) findViewById(2131296402);
            this.d = ((BikeRaceApplication) getApplicationContext()).a();
            this.e = (CustomSnappingHorizontalScrollView) findViewById(2131296406);
            this.j = (TextView) findViewById(2131296400);
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

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, LevelSelectionActivity.class) && !ah.a(this, LevelSelectionActivity.class)) {
                j();
                h();
                i();
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                bikeRaceApplication.d().a(this.b);
                if (hasWindowFocus()) {
                    bikeRaceApplication.b().e();
                }
                com.topfreegames.bikerace.m.a.a(this, (ViewGroup) findViewById(2131296407));
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
        super.onPause();
        com.topfreegames.bikerace.m.a.b(this, (ViewGroup) findViewById(2131296407));
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStop() {
        try {
            super.onStop();
            if (this.c != null) {
                this.c.recycle();
                this.c = null;
                System.gc();
            }
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

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
            System.gc();
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

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.m.onClick(null);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296397);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        if (this.b == 19) {
            return b.EASTER;
        }
        if (this.b == 13 || this.b == 14 || this.b == 18 || this.b == 15) {
            return b.HOLIDAY;
        }
        if (this.b == 16) {
            return b.HALLOWEEN;
        }
        if (this.b == 17) {
            return b.THANKSGIVING;
        }
        return b.DEFAULT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            View viewFindViewById = findViewById(2131296408);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            this.c = com.topfreegames.engine.b.a.a(getResources(), 2130837762, defaultDisplay.getWidth(), defaultDisplay.getHeight());
            viewFindViewById.setBackgroundDrawable(new BitmapDrawable(this.c));
            viewFindViewById.setVisibility(0);
            b().invalidate();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showLoadingScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showLoadingScreen", e2);
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putInt("com.topfreegames.bikerace.WorldSelected", this.b);
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
            this.b = bundle.getInt("com.topfreegames.bikerace.WorldSelected");
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

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == o.DELETE_CONFIRMATION.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099952), getString(2131099663), getString(2131099660), this.r, null);
        }
        return null;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", LevelSelectionActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.b);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LevelSelectionActivity.this.e.a();
                    int iB = com.topfreegames.bikerace.h.y.b(LevelSelectionActivity.this.b);
                    if (iB <= 0) {
                        iB = 1;
                    }
                    int iCeil = (int) Math.ceil(((double) iB) / 8.0d);
                    int i = 0;
                    while (i < iCeil) {
                        com.topfreegames.bikerace.views.k kVar = new com.topfreegames.bikerace.views.k(LevelSelectionActivity.this, LevelSelectionActivity.this.k, LevelSelectionActivity.this.l, LevelSelectionActivity.this.p, LevelSelectionActivity.this.o);
                        kVar.a(LevelSelectionActivity.this.d, LevelSelectionActivity.this.b, i, i == 0, i == iCeil + (-1), LevelSelectionActivity.this.f);
                        LevelSelectionActivity.this.e.a(kVar);
                        i++;
                    }
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) LevelSelectionActivity.this.getApplication()).d().a(getClass().getName(), "updateScroll", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) LevelSelectionActivity.this.getApplication()).d().a(getClass().getName(), "updateScroll", e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.LevelSelectionActivity.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    int iB = com.topfreegames.bikerace.h.y.b(LevelSelectionActivity.this.b);
                    com.topfreegames.bikerace.h.a.q.a().c(i - 1);
                    com.topfreegames.bikerace.ad.a(LevelSelectionActivity.this).b().a(LevelSelectionActivity.this.b, i, iB);
                    ((BikeRaceApplication) LevelSelectionActivity.this.getApplicationContext()).a().i().a(LevelSelectionActivity.this.b, i, iB);
                    LevelSelectionActivity.this.h();
                    LevelSelectionActivity.this.i();
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) LevelSelectionActivity.this.getApplication()).d().a(getClass().getName(), "deleteLevel", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) LevelSelectionActivity.this.getApplication()).d().a(getClass().getName(), "deleteLevel", e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.i.setText(String.format("%d/%d ", Integer.valueOf(((BikeRaceApplication) getApplication()).a().b(this.b)), Integer.valueOf(com.topfreegames.bikerace.h.y.b(this.b) * 3)));
    }

    private void j() {
        this.j.setText(String.valueOf(com.topfreegames.bikerace.h.y.a(this, this.b)) + " ");
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.b = extras.getInt("com.topfreegames.bikerace.WorldSelected");
        }
        j();
        h();
        i();
    }
}
