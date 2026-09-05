package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.topfreegames.bikerace.views.CustomSlowHorizontalScrollView;
import com.topfreegames.bikerace.views.ShopItemLimitedTimeView;
import com.topfreegames.bikerace.views.ShopItemNoAdsRestore;
import com.topfreegames.bikerace.views.ShopItemView;
import com.topfreegames.bikerace.views.ShopItemWorldCup;
import com.topfreegames.bikerace.views.ShopStatusView;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ShopActivity extends com.topfreegames.bikerace.billing.a implements com.topfreegames.bikerace.a.g, com.topfreegames.bikerace.a.i {
    private static /* synthetic */ int[] D;
    private static /* synthetic */ int[] E;
    private com.topfreegames.bikerace.a.c b;
    private Timer i;
    private Timer j;
    private long k;
    private Timer l;
    private long m;
    private Timer n;
    private long o;
    private Timer p;
    private long q;
    private ShopStatusView r;
    private ShopItemNoAdsRestore s;
    private ShopItemWorldCup t;
    private com.topfreegames.bikerace.z u;
    private com.topfreegames.bikerace.multiplayer.o v;
    private com.topfreegames.bikerace.a.f w;
    private com.topfreegames.bikerace.t x;
    private ak y;
    private com.topfreegames.bikerace.billing.b c = null;
    private Object d = null;
    private int e = -1;
    private int f = -1;
    private boolean g = false;
    private int h = 0;
    private final View.OnClickListener z = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                Intent intent = new Intent();
                intent.setClass(ShopActivity.this, (Class) ShopActivity.this.d);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", ShopActivity.this.e);
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", ShopActivity.this.f);
                intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", ShopActivity.this.g);
                intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
                intent.putExtra("com.topfreegames.bikerace.WorldPackSelected", ShopActivity.this.h);
                ShopActivity.this.a(intent, 2130968588, 2130968583);
            } catch (Exception e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
            }
        }
    };
    private final View.OnClickListener A = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.12
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ShopActivity.this.c != null && !ShopActivity.this.c.b()) {
                ShopActivity.this.a(aj.BILLING_UNAVAILABLE.ordinal());
                return;
            }
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) ShopActivity.this.getApplication();
            if (bikeRaceApplication.a().c() && ShopActivity.this.c != null) {
                ShopActivity.this.c.a(ShopActivity.this.getResources().getString(2131099774));
                bikeRaceApplication.d().e();
            }
        }
    };
    private final View.OnClickListener B = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.16
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ShopActivity.this.c != null && ((BikeRaceApplication) ShopActivity.this.getApplication()).e()) {
                ShopActivity.this.c.a();
                com.topfreegames.bikerace.k.a.a().a((com.topfreegames.bikerace.k.e) null);
            } else {
                ShopActivity.this.a(aj.RESTORE_OFFLINE.ordinal());
            }
        }
    };
    private final View.OnClickListener C = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.17
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.topfreegames.bikerace.worldcup.o.a().q()) {
                Intent intent = new Intent();
                intent.setClass(ShopActivity.this, WorldCupShopActivity.class);
                intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", (Serializable) ShopActivity.this.d);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", ShopActivity.this.e);
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", ShopActivity.this.f);
                intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", ShopActivity.this.g);
                intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
                intent.putExtra("com.topfreegames.bikerace.WorldPackSelected", ShopActivity.this.h);
                ShopActivity.this.a(intent, 2130968588, 2130968583);
            }
        }
    };

    static /* synthetic */ int[] j() {
        int[] iArr = D;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.c.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e35) {
            }
            D = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] k() {
        int[] iArr = E;
        if (iArr == null) {
            iArr = new int[al.valuesCustom().length];
            try {
                iArr[al.NOT_COMPLETED.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[al.REFUND.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[al.REVOKED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            E = iArr;
        }
        return iArr;
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.z.onClick(null);
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (bundle != null) {
                this.d = bundle.get("com.topfreegames.bikerace.ReturnToActivity");
                this.e = bundle.getInt("com.topfreegames.bikerace.WorldSelected");
                this.f = bundle.getInt("com.topfreegames.bikerace.PhaseSelected");
                this.g = bundle.getBoolean("com.topfreegame.bikerace.IsMultiplayer");
                this.h = bundle.getInt("com.topfreegames.bikerace.WorldPackSelected");
            } else {
                Bundle extras = getIntent().getExtras();
                this.d = extras.get("com.topfreegames.bikerace.ReturnToActivity");
                this.e = extras.getInt("com.topfreegames.bikerace.WorldSelected");
                this.f = extras.getInt("com.topfreegames.bikerace.PhaseSelected");
                this.g = extras.getBoolean("com.topfreegame.bikerace.IsMultiplayer");
                this.h = extras.getInt("com.topfreegames.bikerace.WorldPackSelected");
            }
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            this.u = bikeRaceApplication.a();
            this.v = bikeRaceApplication.c();
            this.w = com.topfreegames.bikerace.a.f.a((Context) this);
            this.x = bikeRaceApplication.d();
            setContentView(2130903088);
            q();
            p();
            w();
            String stringExtra = getIntent().getStringExtra("com.topfreegames.bikerace.shop.offerId");
            if (stringExtra != null && stringExtra != "") {
                if (this.c != null && this.c.b()) {
                    this.c.a(stringExtra);
                } else {
                    a(aj.BILLING_UNAVAILABLE.ordinal());
                }
            }
            com.topfreegames.bikerace.c cVarA = com.topfreegames.bikerace.c.a(getIntent().getIntExtra("com.topfreegames.bikerace.ShopCenter", com.topfreegames.bikerace.c.REGULAR.ordinal()));
            if (cVarA != com.topfreegames.bikerace.c.REGULAR) {
                c(a(cVarA));
                if (!this.u.a(cVarA) && getIntent().getBooleanExtra("com.topfreegames.bikerace.ShopSelect", false)) {
                    this.u.c(cVarA);
                }
            }
            this.y = new ak(this, cVarA);
            this.s = (ShopItemNoAdsRestore) findViewById(2131296655);
            if (com.topfreegames.bikerace.ap.n()) {
                this.s.a(this.A, this.B, !com.topfreegames.bikerace.ap.l());
            } else {
                this.s.setVisibility(8);
            }
            this.t = (ShopItemWorldCup) findViewById(2131296634);
            this.r = (ShopStatusView) findViewById(2131296633);
            a(b());
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
    public void onDestroy() {
        try {
            super.onDestroy();
            if (this.c != null) {
                this.c.d();
                this.c = null;
            }
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

    @Override // com.topfreegames.bikerace.billing.a
    public void a(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            f(it.next());
        }
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void b(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(it.next(), al.REVOKED);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onPause() {
        try {
            super.onPause();
            com.topfreegames.e.a.a.b().a((Object) this);
            com.topfreegames.bikerace.a.f.a((Context) this).b(this);
            n();
            m();
            this.y.b();
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

    @Override // com.topfreegames.bikerace.billing.a
    public void b(String str) {
        a(str, al.NOT_COMPLETED);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void g() {
        a(aj.PURCHASE_CANCELED_BY_USER.ordinal());
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void a(String str, int i) {
        f(str);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void h() {
        c((String) null);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void c(String str) {
        Bundle bundle = null;
        if (str != null) {
            bundle = new Bundle();
            bundle.putString("productId", str);
        }
        a(aj.PURCHASE_FAILED.ordinal(), bundle);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void d(String str) {
        a(str, al.REFUND);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void i() {
        a(aj.BILLING_UNAVAILABLE.ordinal());
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        try {
            super.onRestoreInstanceState(bundle);
            this.d = bundle.get("com.topfreegames.bikerace.ReturnToActivity");
            this.e = bundle.getInt("com.topfreegames.bikerace.WorldSelected");
            this.f = bundle.getInt("com.topfreegames.bikerace.PhaseSelected");
            this.g = bundle.getBoolean("com.topfreegame.bikerace.IsMultiplayer");
            this.h = bundle.getInt("com.topfreegames.bikerace.WorldPackSelected");
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

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, ShopActivity.class) && !ah.a(this, ShopActivity.class)) {
                com.topfreegames.bikerace.a.f.a((Context) this).a((com.topfreegames.bikerace.a.i) this);
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                bikeRaceApplication.d().g(this.e, this.f, this.g);
                if (hasWindowFocus()) {
                    bikeRaceApplication.b().e();
                }
                x();
                u();
                v();
                o();
                this.y.a();
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

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public void l() {
        for (com.topfreegames.bikerace.c cVar : com.topfreegames.bikerace.c.valuesCustom()) {
            b(cVar);
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putInt("com.topfreegames.bikerace.WorldSelected", this.e);
            bundle.putInt("com.topfreegames.bikerace.PhaseSelected", this.f);
            bundle.putBoolean("com.topfreegame.bikerace.IsMultiplayer", this.g);
            bundle.putSerializable("com.topfreegames.bikerace.ReturnToActivity", (Class) this.d);
            bundle.putInt("com.topfreegames.bikerace.WorldPackSelected", this.h);
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

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (d() && z) {
            ((BikeRaceApplication) getApplication()).b().e();
        }
    }

    @Override // com.topfreegames.bikerace.a.i
    public void a(final com.topfreegames.bikerace.a.a aVar, final com.topfreegames.bikerace.a.j jVar) {
        if (!isFinishing()) {
            runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.18
                @Override // java.lang.Runnable
                public void run() {
                    com.topfreegames.bikerace.views.a.a(ShopActivity.this.b(), aVar, jVar);
                    ShopActivity.this.l();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final View view) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.19
            @Override // java.lang.Runnable
            public void run() {
                final HorizontalScrollView horizontalScrollView;
                if (view != null && (horizontalScrollView = (HorizontalScrollView) ShopActivity.this.findViewById(2131296632)) != null) {
                    final View view2 = view;
                    horizontalScrollView.post(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.19.1
                        @Override // java.lang.Runnable
                        public void run() {
                            horizontalScrollView.scrollTo(((view2.getLeft() + view2.getRight()) - horizontalScrollView.getWidth()) / 2, 0);
                        }
                    });
                    ShopActivity.this.b().invalidate();
                }
            }
        });
    }

    private void m() {
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
    }

    private void n() {
        if (this.j != null) {
            this.j.cancel();
            this.j = null;
        }
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
    }

    private String e(String str) {
        if (str.equals(getString(2131099779))) {
            return getString(2131099780);
        }
        if (str.equals(getString(2131099781))) {
            return getString(2131099782);
        }
        if (str.equals(getString(2131099783))) {
            return getString(2131099784);
        }
        if (str.equals(getString(2131099785))) {
            return getString(2131099786);
        }
        if (str.equals(getString(2131099793))) {
            return getString(2131099794);
        }
        if (str.equals(getString(2131099795))) {
            return getString(2131099796);
        }
        if (str.equals(getString(2131099797))) {
            return getString(2131099798);
        }
        if (str.equals(getString(2131099789))) {
            return getString(2131099790);
        }
        if (str.equals(getString(2131099799))) {
            return getString(2131099800);
        }
        if (str.equals(getString(2131099772))) {
            return getString(2131099773);
        }
        if (str.equals(getString(2131099791))) {
            return getString(2131099792);
        }
        if (str.equals(getString(2131099766))) {
            return getString(2131099767);
        }
        if (str.equals(getString(2131099769))) {
            return getString(2131099770);
        }
        if (str.equals(getString(2131099801))) {
            return getString(2131099802);
        }
        if (str.equals(getString(2131099804))) {
            return getString(2131099805);
        }
        if (str.equals(getString(2131099787))) {
            return getString(2131099788);
        }
        if (str.equals(getString(2131099806))) {
            return getString(2131099807);
        }
        if (str.equals(getString(2131099808))) {
            return getString(2131099809);
        }
        if (str.equals(getString(2131099810))) {
            return getString(2131099811);
        }
        if (str.equals(getString(2131099812))) {
            return getString(2131099813);
        }
        if (str.equals(getString(2131099823))) {
            return getString(2131099824);
        }
        if (str.equals(getString(2131099825))) {
            return getString(2131099826);
        }
        if (str.equals(getString(2131099827))) {
            return getString(2131099828);
        }
        if (str.equals(getString(2131099829))) {
            return getString(2131099830);
        }
        if (str.equals(getString(2131099831))) {
            return getString(2131099832);
        }
        if (str.equals(getString(2131099833))) {
            return getString(2131099834);
        }
        if (str.equals(getString(2131099835))) {
            return getString(2131099836);
        }
        if (str.equals(getString(2131099837))) {
            return getString(2131099838);
        }
        if (str.equals(getString(2131099839))) {
            return getString(2131099840);
        }
        if (str.equals(getString(2131099841))) {
            return getString(2131099842);
        }
        if (str.equals(getString(2131099843))) {
            return getString(2131099844);
        }
        if (str.equals(getString(2131099853))) {
            return getString(2131099854);
        }
        return "";
    }

    private void o() {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.20
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a((Context) ShopActivity.this);
                fVarA.a(false);
                com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) ShopActivity.this.getApplicationContext()).a();
                fVarA.a("AchievAllStarsDesert", zVarA.b(1));
                fVarA.a("AchievAllStarsArtic", zVarA.b(2));
                fVarA.a("AchievAllStarsDunes", zVarA.b(3));
                fVarA.a("AchievAllStarsHills", zVarA.b(4));
                fVarA.a("AchievAllStarsBeach", zVarA.b(5));
                fVarA.a("AchievAllStarsSavanna", zVarA.b(6));
                fVarA.a("AchievAllStarsDesert2", zVarA.b(7));
                fVarA.a("AchievAllStarsArtic2", zVarA.b(8));
                fVarA.a("AchievAllStarsHalloween", zVarA.b(16));
                fVarA.a("AchievAllStarsThanksgiving", zVarA.b(17));
                fVarA.a("AchievAllStarsHoliday3", zVarA.b(18));
                com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) ShopActivity.this.getApplicationContext()).c();
                fVarA.a("AchievConsecutiveWins", zVarA.j());
                fVarA.a("AchievNumberFacebookFriends", oVarC.o());
                fVarA.a("AchievMultiplayerWins", oVarC.k());
                fVarA.a("AchievMultiplayerWinsLastLife", zVarA.l());
                fVarA.a("AchievGetStarsUserCreated", zVarA.b(999));
                fVarA.a("AchievEasterEggs", com.topfreegames.bikerace.c.d.a().a(com.topfreegames.bikerace.c.c.EASTER_EGG));
                fVarA.a(true);
                if (fVarA.c()) {
                    fVarA.a();
                }
            }
        }).start();
    }

    private void p() {
        if (this.c != null) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.21
                @Override // java.lang.Runnable
                public void run() {
                    Integer.valueOf(0);
                    Integer num = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099823));
                    if (num != null && num.intValue() > 0 && ShopActivity.this.u.a(2)) {
                        ShopActivity.this.u.f(2);
                    }
                    Integer num2 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099825));
                    if (num2 != null && num2.intValue() > 0 && ShopActivity.this.u.a(3)) {
                        ShopActivity.this.u.f(3);
                    }
                    Integer num3 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099827));
                    if (num3 != null && num3.intValue() > 0 && ShopActivity.this.u.a(4)) {
                        ShopActivity.this.u.f(4);
                    }
                    Integer num4 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099829));
                    if (num4 != null && num4.intValue() > 0) {
                        ShopActivity.this.u.f(5);
                    }
                    Integer num5 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099831));
                    if (num5 != null && num5.intValue() > 0 && ShopActivity.this.u.a(6)) {
                        ShopActivity.this.u.f(6);
                    }
                    Integer num6 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099833));
                    if (num6 != null && num6.intValue() > 0 && ShopActivity.this.u.a(7)) {
                        ShopActivity.this.u.f(7);
                    }
                    Integer num7 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099835));
                    if (num7 != null && num7.intValue() > 0 && ShopActivity.this.u.a(8)) {
                        ShopActivity.this.u.f(8);
                    }
                    Integer num8 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099837));
                    if (num8 != null && num8.intValue() > 0) {
                        ShopActivity.this.u.f(9);
                    }
                    Integer num9 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099839));
                    if (num9 != null && num9.intValue() > 0) {
                        ShopActivity.this.u.f(10);
                    }
                    Integer num10 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099841));
                    if (num10 != null && num10.intValue() > 0) {
                        ShopActivity.this.u.f(11);
                    }
                    Integer num11 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099843));
                    if (num11 != null && num11.intValue() > 0) {
                        ShopActivity.this.u.f(12);
                    }
                    Integer num12 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099779));
                    if (num12 != null && num12.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.GIRL)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.GIRL);
                    }
                    Integer num13 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099781));
                    if (num13 != null && num13.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.RETRO)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.RETRO);
                    }
                    Integer num14 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099783));
                    if (num14 != null && num14.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.ACROBATIC)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.ACROBATIC);
                    }
                    Integer num15 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099785));
                    if (num15 != null && num15.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.BRONZE)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.BRONZE);
                    }
                    Integer num16 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099793));
                    if (num16 != null && num16.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.NINJA)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.NINJA);
                    }
                    Integer num17 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099795));
                    if (num17 != null && num17.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.SPAM)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.SPAM);
                    }
                    Integer num18 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099797));
                    if (num18 != null && num18.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.COP)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.COP);
                    }
                    Integer num19 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099789));
                    if (num19 != null && num19.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.SILVER)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.SILVER);
                    }
                    Integer num20 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099799));
                    if (num20 != null && num20.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.BEAT)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.BEAT);
                    }
                    Integer num21 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099772));
                    if (num21 != null && num21.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.GHOST)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.GHOST);
                    }
                    Integer num22 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099791));
                    if (num22 != null && num22.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.GOLD)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.GOLD);
                    }
                    Integer num23 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099801));
                    if (num23 != null && num23.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.ULTRA)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.ULTRA);
                    }
                    Integer num24 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099804));
                    if (num24 != null && num24.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.ZOMBIE)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.ZOMBIE);
                    }
                    Integer num25 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099787));
                    if (num25 != null && num25.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.ARMY)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.ARMY);
                    }
                    Integer num26 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099806));
                    if (num26 != null && num26.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.HALLOWEEN)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.HALLOWEEN);
                    }
                    Integer num27 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099808));
                    if (num27 != null && num27.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.THANKSGIVING)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.THANKSGIVING);
                    }
                    Integer num28 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099810));
                    if (num28 != null && num28.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.SANTA)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.SANTA);
                    }
                    Integer num29 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099766));
                    if (num29 != null && num29.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.KIDS)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.KIDS);
                    }
                    Integer num30 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099769));
                    if (num30 != null && num30.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.SUPER)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.SUPER);
                    }
                    Integer num31 = ShopActivity.this.c.c().get(ShopActivity.this.getString(2131099812));
                    if (num31 != null && num31.intValue() > 0 && ShopActivity.this.u.a(com.topfreegames.bikerace.c.EASTER)) {
                        ShopActivity.this.u.d(com.topfreegames.bikerace.c.EASTER);
                    }
                }
            }).start();
        }
    }

    private void f(String str) {
        try {
            Resources resources = getResources();
            if (str.equals(resources.getString(2131099772))) {
                a(str, com.topfreegames.bikerace.c.GHOST);
                b(com.topfreegames.bikerace.c.GHOST);
            } else if (str.equals(resources.getString(2131099766))) {
                a(str, com.topfreegames.bikerace.c.KIDS);
                b(com.topfreegames.bikerace.c.KIDS);
            } else if (str.equals(resources.getString(2131099769))) {
                a(str, com.topfreegames.bikerace.c.SUPER);
                b(com.topfreegames.bikerace.c.SUPER);
            } else if (str.equals(resources.getString(2131099779))) {
                a(str, com.topfreegames.bikerace.c.GIRL);
                b(com.topfreegames.bikerace.c.GIRL);
            } else if (str.equals(resources.getString(2131099781))) {
                a(str, com.topfreegames.bikerace.c.RETRO);
                b(com.topfreegames.bikerace.c.RETRO);
            } else if (str.equals(resources.getString(2131099783))) {
                a(str, com.topfreegames.bikerace.c.ACROBATIC);
                b(com.topfreegames.bikerace.c.ACROBATIC);
            } else if (str.equals(resources.getString(2131099785))) {
                a(str, com.topfreegames.bikerace.c.BRONZE);
                b(com.topfreegames.bikerace.c.BRONZE);
            } else if (str.equals(resources.getString(2131099793))) {
                a(str, com.topfreegames.bikerace.c.NINJA);
                b(com.topfreegames.bikerace.c.NINJA);
            } else if (str.equals(resources.getString(2131099795))) {
                a(str, com.topfreegames.bikerace.c.SPAM);
                b(com.topfreegames.bikerace.c.SPAM);
            } else if (str.equals(resources.getString(2131099797))) {
                a(str, com.topfreegames.bikerace.c.COP);
                b(com.topfreegames.bikerace.c.COP);
            } else if (str.equals(resources.getString(2131099789))) {
                a(str, com.topfreegames.bikerace.c.SILVER);
                b(com.topfreegames.bikerace.c.SILVER);
            } else if (str.equals(resources.getString(2131099799))) {
                a(str, com.topfreegames.bikerace.c.BEAT);
                b(com.topfreegames.bikerace.c.BEAT);
            } else if (str.equals(resources.getString(2131099791))) {
                a(str, com.topfreegames.bikerace.c.GOLD);
                b(com.topfreegames.bikerace.c.GOLD);
            } else if (str.equals(resources.getString(2131099801))) {
                a(str, com.topfreegames.bikerace.c.ULTRA);
                b(com.topfreegames.bikerace.c.ULTRA);
            } else if (str.equals(resources.getString(2131099804))) {
                a(str, com.topfreegames.bikerace.c.ZOMBIE);
                b(com.topfreegames.bikerace.c.ZOMBIE);
            } else if (str.equals(resources.getString(2131099787))) {
                a(str, com.topfreegames.bikerace.c.ARMY);
                b(com.topfreegames.bikerace.c.ARMY);
            } else if (str.equals(resources.getString(2131099806))) {
                a(str, com.topfreegames.bikerace.c.HALLOWEEN);
                b(com.topfreegames.bikerace.c.HALLOWEEN);
            } else if (str.equals(resources.getString(2131099808))) {
                a(str, com.topfreegames.bikerace.c.THANKSGIVING);
                b(com.topfreegames.bikerace.c.THANKSGIVING);
            } else if (str.equals(resources.getString(2131099810))) {
                a(str, com.topfreegames.bikerace.c.SANTA);
                b(com.topfreegames.bikerace.c.SANTA);
            } else if (str.equals(resources.getString(2131099812))) {
                a(str, com.topfreegames.bikerace.c.EASTER);
                b(com.topfreegames.bikerace.c.EASTER);
            } else if (str.equals(resources.getString(2131099774))) {
                this.u.F();
                this.x.b(str);
            } else if (str.equals(resources.getString(2131099823))) {
                b(str, 2);
            } else if (str.equals(resources.getString(2131099825))) {
                b(str, 3);
            } else if (str.equals(resources.getString(2131099827))) {
                b(str, 4);
            } else if (str.equals(resources.getString(2131099829))) {
                b(str, 5);
            } else if (str.equals(resources.getString(2131099831))) {
                b(str, 6);
            } else if (str.equals(resources.getString(2131099833))) {
                b(str, 7);
            } else if (str.equals(resources.getString(2131099835))) {
                b(str, 8);
            } else if (str.equals(resources.getString(2131099837))) {
                b(str, 9);
            } else if (str.equals(resources.getString(2131099839))) {
                b(str, 10);
            } else if (str.equals(resources.getString(2131099841))) {
                b(str, 11);
            } else if (str.equals(resources.getString(2131099843))) {
                b(str, 12);
            } else if (str.equals(resources.getString(2131099853))) {
                new com.topfreegames.bikerace.h.a.b(this).d();
                Bundle bundle = new Bundle();
                bundle.putString("productId", str);
                a(aj.PURCHASE_COMPLETED.ordinal(), bundle);
            } else {
                this.x.a("onProductPurchase", "Invalid product: " + str);
            }
            t();
            p();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onProductPurchased", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onProductPurchased", e2);
        }
    }

    private void a(String str, al alVar) {
        try {
            Resources resources = getResources();
            com.topfreegames.bikerace.c cVarE = this.u.e();
            if (str.equals(resources.getString(2131099772))) {
                a(str, cVarE, com.topfreegames.bikerace.c.GHOST, alVar, "AchievGroupBike");
                b(com.topfreegames.bikerace.c.GHOST);
            } else if (str.equals(resources.getString(2131099766))) {
                a(str, cVarE, com.topfreegames.bikerace.c.KIDS, alVar, (String) null);
                b(com.topfreegames.bikerace.c.KIDS);
            } else if (str.equals(resources.getString(2131099769))) {
                a(str, cVarE, com.topfreegames.bikerace.c.SUPER, alVar, (String) null);
                b(com.topfreegames.bikerace.c.SUPER);
            } else if (str.equals(resources.getString(2131099779))) {
                a(str, cVarE, com.topfreegames.bikerace.c.GIRL, alVar, "AchievGroupBikeGirl");
                b(com.topfreegames.bikerace.c.GIRL);
            } else if (str.equals(resources.getString(2131099781))) {
                a(str, cVarE, com.topfreegames.bikerace.c.RETRO, alVar, "AchievGroupBikeRetro");
                b(com.topfreegames.bikerace.c.RETRO);
            } else if (str.equals(resources.getString(2131099783))) {
                a(str, cVarE, com.topfreegames.bikerace.c.ACROBATIC, alVar, "AchievGroupBikeAcrobatic");
                b(com.topfreegames.bikerace.c.ACROBATIC);
            } else if (str.equals(resources.getString(2131099785))) {
                a(str, cVarE, com.topfreegames.bikerace.c.BRONZE, alVar, "AchievGroupBikeBronze");
                b(com.topfreegames.bikerace.c.BRONZE);
            } else if (str.equals(resources.getString(2131099793))) {
                a(str, cVarE, com.topfreegames.bikerace.c.NINJA, alVar, "AchievGroupBikeNinja");
                b(com.topfreegames.bikerace.c.NINJA);
            } else if (str.equals(resources.getString(2131099795))) {
                a(str, cVarE, com.topfreegames.bikerace.c.SPAM, alVar, "AchievGroupBikeCreateGame");
                b(com.topfreegames.bikerace.c.SPAM);
            } else if (str.equals(resources.getString(2131099797))) {
                a(str, cVarE, com.topfreegames.bikerace.c.COP, alVar, "AchievGroupBikeCop");
                b(com.topfreegames.bikerace.c.COP);
            } else if (str.equals(resources.getString(2131099789))) {
                a(str, cVarE, com.topfreegames.bikerace.c.SILVER, alVar, "AchievGroupBikeSilver");
                b(com.topfreegames.bikerace.c.SILVER);
            } else if (str.equals(resources.getString(2131099799))) {
                a(str, cVarE, com.topfreegames.bikerace.c.BEAT, alVar, "AchievGroupBikeBeatOthers");
                b(com.topfreegames.bikerace.c.BEAT);
            } else if (str.equals(resources.getString(2131099791))) {
                a(str, cVarE, com.topfreegames.bikerace.c.GOLD, alVar, "AchievGroupBikeGold");
                b(com.topfreegames.bikerace.c.GOLD);
            } else if (str.equals(resources.getString(2131099801))) {
                a(str, cVarE, com.topfreegames.bikerace.c.ULTRA, alVar, (String) null);
                b(com.topfreegames.bikerace.c.ULTRA);
            } else if (str.equals(resources.getString(2131099804))) {
                a(str, cVarE, com.topfreegames.bikerace.c.ZOMBIE, alVar, "AchievGroupBikeZombie");
                b(com.topfreegames.bikerace.c.ZOMBIE);
            } else if (str.equals(resources.getString(2131099787))) {
                a(str, cVarE, com.topfreegames.bikerace.c.ARMY, alVar, "AchievGroupBikeArmy");
                b(com.topfreegames.bikerace.c.ARMY);
            } else if (str.equals(resources.getString(2131099806))) {
                a(str, cVarE, com.topfreegames.bikerace.c.HALLOWEEN, alVar, "AchievGroupBikeHalloween");
                b(com.topfreegames.bikerace.c.HALLOWEEN);
            } else if (str.equals(resources.getString(2131099808))) {
                a(str, cVarE, com.topfreegames.bikerace.c.THANKSGIVING, alVar, "AchievGroupBikeThanksgiving");
                b(com.topfreegames.bikerace.c.THANKSGIVING);
            } else if (str.equals(resources.getString(2131099810))) {
                a(str, cVarE, com.topfreegames.bikerace.c.SANTA, alVar, "AchievGroupBikeHoliday");
                b(com.topfreegames.bikerace.c.SANTA);
            } else if (str.equals(resources.getString(2131099812))) {
                a(str, cVarE, com.topfreegames.bikerace.c.EASTER, alVar, "AchievGroupBikeEaster");
                b(com.topfreegames.bikerace.c.EASTER);
            } else if (str.equals(resources.getString(2131099774))) {
                this.u.F();
                this.x.b(str);
            } else if (str.equals(resources.getString(2131099823))) {
                a(str, 2, alVar);
            } else if (str.equals(resources.getString(2131099825))) {
                a(str, 3, alVar);
            } else if (str.equals(resources.getString(2131099827))) {
                a(str, 4, alVar);
            } else if (str.equals(resources.getString(2131099829))) {
                a(str, 5, alVar);
            } else if (str.equals(resources.getString(2131099831))) {
                a(str, 6, alVar);
            } else if (str.equals(resources.getString(2131099833))) {
                a(str, 7, alVar);
            } else if (str.equals(resources.getString(2131099835))) {
                a(str, 8, alVar);
            } else if (str.equals(resources.getString(2131099837))) {
                a(str, 9, alVar);
            } else if (str.equals(resources.getString(2131099839))) {
                a(str, 10, alVar);
            } else if (str.equals(resources.getString(2131099841))) {
                a(str, 11, alVar);
            } else if (str.equals(resources.getString(2131099843))) {
                a(str, 12, alVar);
            } else {
                this.x.a("onProductPurchase", "Invalid product: " + str);
            }
            p();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onProductPurchased", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onProductPurchased", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View a(com.topfreegames.bikerace.c cVar) {
        switch (j()[cVar.ordinal()]) {
            case 2:
                return findViewById(2131296653);
            case 3:
                return findViewById(2131296652);
            case 4:
                return findViewById(2131296649);
            case 5:
                return findViewById(2131296643);
            case 6:
                return findViewById(2131296645);
            case 7:
                return findViewById(2131296640);
            case 8:
                return findViewById(2131296642);
            case 9:
                return findViewById(2131296646);
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return findViewById(2131296651);
            case XMLStreamConstants.DTD /* 11 */:
                return findViewById(2131296639);
            case XMLStreamConstants.CDATA /* 12 */:
                return findViewById(2131296641);
            case XMLStreamConstants.NAMESPACE /* 13 */:
                return findViewById(2131296648);
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                return findViewById(2131296644);
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return findViewById(2131296654);
            case 16:
                return findViewById(2131296647);
            case 17:
                return findViewById(2131296650);
            case 18:
                return findViewById(2131296638);
            case 19:
                return findViewById(2131296637);
            case 20:
                return findViewById(2131296636);
            case 21:
                return findViewById(2131296635);
            default:
                return null;
        }
    }

    private void q() {
        try {
            if (this.c == null && com.topfreegames.bikerace.ap.n()) {
                Resources resources = getResources();
                LinkedList linkedList = new LinkedList();
                linkedList.add(resources.getString(2131099772));
                linkedList.add(resources.getString(2131099766));
                linkedList.add(resources.getString(2131099769));
                linkedList.add(resources.getString(2131099779));
                linkedList.add(resources.getString(2131099781));
                linkedList.add(resources.getString(2131099783));
                linkedList.add(resources.getString(2131099785));
                linkedList.add(resources.getString(2131099793));
                linkedList.add(resources.getString(2131099795));
                linkedList.add(resources.getString(2131099797));
                linkedList.add(resources.getString(2131099789));
                linkedList.add(resources.getString(2131099799));
                linkedList.add(resources.getString(2131099791));
                linkedList.add(resources.getString(2131099801));
                linkedList.add(resources.getString(2131099804));
                linkedList.add(resources.getString(2131099787));
                linkedList.add(resources.getString(2131099806));
                linkedList.add(resources.getString(2131099808));
                linkedList.add(resources.getString(2131099810));
                linkedList.add(resources.getString(2131099812));
                linkedList.add(resources.getString(2131099774));
                linkedList.add(resources.getString(2131099823));
                linkedList.add(resources.getString(2131099825));
                linkedList.add(resources.getString(2131099827));
                linkedList.add(resources.getString(2131099829));
                linkedList.add(resources.getString(2131099831));
                linkedList.add(resources.getString(2131099833));
                linkedList.add(resources.getString(2131099835));
                linkedList.add(resources.getString(2131099837));
                linkedList.add(resources.getString(2131099839));
                linkedList.add(resources.getString(2131099853));
                if (com.topfreegames.bikerace.ap.o()) {
                    this.c = new com.topfreegames.bikerace.billing.google.j(this, linkedList);
                } else {
                    if (com.topfreegames.bikerace.ap.p()) {
                        this.c = new com.topfreegames.bikerace.billing.a.a(this, linkedList);
                        return;
                    }
                    throw new IllegalStateException("Check the profile config");
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "initShopData", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "initShopData", e2);
        }
    }

    private void a(String str, com.topfreegames.bikerace.c cVar, final com.topfreegames.bikerace.c cVar2, al alVar, String str2) {
        int iOrdinal = 0;
        boolean z = !this.u.a(cVar2);
        if (cVar == cVar2) {
            this.u.c(com.topfreegames.bikerace.c.REGULAR);
        }
        this.u.b(cVar2);
        if (str2 != null) {
            com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(getApplicationContext());
            fVarA.a(fVarA.a(str2), false);
        }
        if (z) {
            switch (k()[alVar.ordinal()]) {
                case 1:
                    iOrdinal = aj.PURCHSE_REFUND.ordinal();
                    this.x.c(cVar2);
                    break;
                case 2:
                    iOrdinal = aj.PURCHASE_NOT_COMPLETED.ordinal();
                    this.x.d(cVar2);
                    break;
                case 3:
                    iOrdinal = aj.PURCHASE_REVOKED.ordinal();
                    this.x.d(cVar2);
                    break;
            }
            Bundle bundle = new Bundle();
            bundle.putString("productId", str);
            a(iOrdinal, bundle);
            b().post(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.22
                @Override // java.lang.Runnable
                public void run() {
                    ShopActivity.this.c(ShopActivity.this.a(cVar2));
                }
            });
        }
    }

    private void a(String str, int i, al alVar) {
        int iOrdinal = 0;
        if (!this.u.a(i)) {
            this.u.d(i);
            switch (k()[alVar.ordinal()]) {
                case 1:
                    iOrdinal = aj.PURCHSE_REFUND.ordinal();
                    this.x.b(i);
                    break;
                case 2:
                    iOrdinal = aj.PURCHASE_NOT_COMPLETED.ordinal();
                    this.x.c(i);
                    break;
                case 3:
                    iOrdinal = aj.PURCHASE_REVOKED.ordinal();
                    this.x.c(i);
                    break;
            }
            Bundle bundle = new Bundle();
            bundle.putString("productId", str);
            a(iOrdinal, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://www.facebook.com/BikeRaceByTopFreeGames"));
            startActivity(intent);
            com.topfreegames.bikerace.a.f.a((Context) this).c("AchievLikeBikeRacePage");
        } catch (Exception e) {
            a(aj.LIKE_ERROR.ordinal());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://www.facebook.com/pages/Top-Free-Games/168539526551930"));
            startActivity(intent);
            com.topfreegames.bikerace.a.f.a((Context) this).c("AchievTopFreeGamesPage");
        } catch (Exception e) {
            a(aj.LIKE_ERROR.ordinal());
        }
    }

    private void t() {
        new Timer().schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.ShopActivity.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.j.b.a(true);
            }
        }, 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final com.topfreegames.bikerace.c cVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.3
            private static /* synthetic */ int[] c;

            static /* synthetic */ int[] a() {
                int[] iArr = c;
                if (iArr == null) {
                    iArr = new int[com.topfreegames.bikerace.c.valuesCustom().length];
                    try {
                        iArr[com.topfreegames.bikerace.c.ACROBATIC.ordinal()] = 12;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.ARMY.ordinal()] = 17;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.BEAT.ordinal()] = 13;
                    } catch (NoSuchFieldError e3) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.BRONZE.ordinal()] = 8;
                    } catch (NoSuchFieldError e4) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.COP.ordinal()] = 6;
                    } catch (NoSuchFieldError e5) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.EASTER.ordinal()] = 21;
                    } catch (NoSuchFieldError e6) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.GHOST.ordinal()] = 4;
                    } catch (NoSuchFieldError e7) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.GIRL.ordinal()] = 11;
                    } catch (NoSuchFieldError e8) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.GOLD.ordinal()] = 10;
                    } catch (NoSuchFieldError e9) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.HALLOWEEN.ordinal()] = 18;
                    } catch (NoSuchFieldError e10) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.KIDS.ordinal()] = 3;
                    } catch (NoSuchFieldError e11) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.NINJA.ordinal()] = 5;
                    } catch (NoSuchFieldError e12) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
                    } catch (NoSuchFieldError e13) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.REGULAR.ordinal()] = 1;
                    } catch (NoSuchFieldError e14) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.RETRO.ordinal()] = 7;
                    } catch (NoSuchFieldError e15) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.SANTA.ordinal()] = 20;
                    } catch (NoSuchFieldError e16) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.SILVER.ordinal()] = 9;
                    } catch (NoSuchFieldError e17) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.SPAM.ordinal()] = 14;
                    } catch (NoSuchFieldError e18) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.SUPER.ordinal()] = 2;
                    } catch (NoSuchFieldError e19) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.THANKSGIVING.ordinal()] = 19;
                    } catch (NoSuchFieldError e20) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.ULTRA.ordinal()] = 15;
                    } catch (NoSuchFieldError e21) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA.ordinal()] = 35;
                    } catch (NoSuchFieldError e22) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
                    } catch (NoSuchFieldError e23) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_BELGIUM.ordinal()] = 32;
                    } catch (NoSuchFieldError e24) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_BRAZIL.ordinal()] = 29;
                    } catch (NoSuchFieldError e25) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_ENGLAND.ordinal()] = 24;
                    } catch (NoSuchFieldError e26) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_FRANCE.ordinal()] = 27;
                    } catch (NoSuchFieldError e27) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_GERMANY.ordinal()] = 28;
                    } catch (NoSuchFieldError e28) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_ITALY.ordinal()] = 34;
                    } catch (NoSuchFieldError e29) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_JAPAN.ordinal()] = 31;
                    } catch (NoSuchFieldError e30) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_MEXICO.ordinal()] = 33;
                    } catch (NoSuchFieldError e31) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
                    } catch (NoSuchFieldError e32) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_SPAIN.ordinal()] = 30;
                    } catch (NoSuchFieldError e33) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.WORLDCUP_USA.ordinal()] = 23;
                    } catch (NoSuchFieldError e34) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.c.ZOMBIE.ordinal()] = 16;
                    } catch (NoSuchFieldError e35) {
                    }
                    c = iArr;
                }
                return iArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a((Context) ShopActivity.this);
                switch (a()[cVar.ordinal()]) {
                    case 2:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.SUPER, (com.topfreegames.bikerace.a.c) null, com.topfreegames.bikerace.ap.n() ? false : true);
                        break;
                    case 3:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.KIDS, (com.topfreegames.bikerace.a.c) null, com.topfreegames.bikerace.ap.n() ? false : true);
                        break;
                    case 4:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.GHOST, fVarA.a("AchievGroupBike"), false);
                        break;
                    case 5:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.NINJA, fVarA.a("AchievGroupBikeNinja"), false);
                        break;
                    case 6:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.COP, fVarA.a("AchievGroupBikeCop"), false);
                        break;
                    case 7:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.RETRO, fVarA.a("AchievGroupBikeRetro"), false);
                        break;
                    case 8:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.BRONZE, fVarA.a("AchievGroupBikeBronze"), false);
                        break;
                    case 9:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.SILVER, fVarA.a("AchievGroupBikeSilver"), false);
                        break;
                    case XMLStreamConstants.ATTRIBUTE /* 10 */:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.GOLD, fVarA.a("AchievGroupBikeGold"), false);
                        break;
                    case XMLStreamConstants.DTD /* 11 */:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.GIRL, fVarA.a("AchievGroupBikeGirl"), false);
                        break;
                    case XMLStreamConstants.CDATA /* 12 */:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.ACROBATIC, fVarA.a("AchievGroupBikeAcrobatic"), false);
                        break;
                    case XMLStreamConstants.NAMESPACE /* 13 */:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.BEAT, fVarA.a("AchievGroupBikeBeatOthers"), false);
                        break;
                    case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.SPAM, fVarA.a("AchievGroupBikeCreateGame"), false);
                        break;
                    case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.ULTRA, (com.topfreegames.bikerace.a.c) null, com.topfreegames.bikerace.ap.n() ? false : true);
                        break;
                    case 16:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.ZOMBIE, fVarA.a("AchievGroupBikeZombie"), false);
                        break;
                    case 17:
                        ShopActivity.this.a(com.topfreegames.bikerace.c.ARMY, fVarA.a("AchievGroupBikeArmy"), false);
                        break;
                    case 18:
                        if (ShopActivity.this.a(com.topfreegames.bikerace.c.HALLOWEEN, fVarA.a("AchievGroupBikeHalloween"), com.topfreegames.bikerace.am.b(ShopActivity.this), com.topfreegames.bikerace.am.a(ShopActivity.this) ? false : true)) {
                            ShopActivity.this.z();
                        }
                        break;
                    case 19:
                        if (ShopActivity.this.a(com.topfreegames.bikerace.c.THANKSGIVING, fVarA.a("AchievGroupBikeThanksgiving"), com.topfreegames.bikerace.ao.b(ShopActivity.this), com.topfreegames.bikerace.ao.a(ShopActivity.this) ? false : true)) {
                            ShopActivity.this.A();
                        }
                        break;
                    case 20:
                        if (ShopActivity.this.a(com.topfreegames.bikerace.c.SANTA, fVarA.a("AchievGroupBikeHoliday"), com.topfreegames.bikerace.an.b(ShopActivity.this), com.topfreegames.bikerace.an.a(ShopActivity.this) ? false : true)) {
                            ShopActivity.this.B();
                        }
                        break;
                    case 21:
                        if (ShopActivity.this.a(com.topfreegames.bikerace.c.EASTER, fVarA.a("AchievGroupBikeEaster"), com.topfreegames.bikerace.al.b(ShopActivity.this), com.topfreegames.bikerace.al.a(ShopActivity.this) ? false : true)) {
                            ShopActivity.this.C();
                        }
                        break;
                }
                ShopActivity.this.a(ShopActivity.this.b());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.a.c cVar2, boolean z) {
        try {
            ShopItemView shopItemView = (ShopItemView) a(cVar);
            if (com.topfreegames.bikerace.ap.n()) {
                shopItemView.setPurchaseListener(new ao(this, cVar));
            }
            if (z) {
                shopItemView.setVisibility(8);
            }
            shopItemView.setSelectListener(new an(this, cVar));
            a(shopItemView, cVar, cVar2);
            if (!com.topfreegames.bikerace.ap.n()) {
                shopItemView.setPurchasable(false);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "setupItemView", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "setupItemView", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.a.c cVar2, boolean z, boolean z2) {
        try {
            ShopItemLimitedTimeView shopItemLimitedTimeView = (ShopItemLimitedTimeView) a(cVar);
            if (com.topfreegames.bikerace.ap.n()) {
                shopItemLimitedTimeView.setPurchaseListener(new ao(this, cVar));
            }
            if (z) {
                shopItemLimitedTimeView.setVisibility(0);
                shopItemLimitedTimeView.setSelectListener(new an(this, cVar));
                a(shopItemLimitedTimeView, cVar, cVar2, z2);
                if (!com.topfreegames.bikerace.ap.n()) {
                    shopItemLimitedTimeView.setPurchasable(false);
                }
                return true;
            }
            shopItemLimitedTimeView.setVisibility(8);
            return false;
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "setupLimitedTimeItemView", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "setupLimitedTimeItemView", e2);
            return z;
        }
    }

    private void u() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.4
            @Override // java.lang.Runnable
            public void run() {
                ShopActivity.this.s.a(!ShopActivity.this.u.c());
            }
        });
    }

    private void v() {
        com.topfreegames.bikerace.worldcup.o oVarA = com.topfreegames.bikerace.worldcup.o.a();
        if (oVarA.q()) {
            this.t.setVisibility(0);
            this.t.setup(this.C);
            if (oVarA.r()) {
                y();
                return;
            } else {
                this.t.a();
                return;
            }
        }
        this.t.setVisibility(8);
    }

    private void w() {
        try {
            CustomSlowHorizontalScrollView customSlowHorizontalScrollView = (CustomSlowHorizontalScrollView) findViewById(2131296632);
            customSlowHorizontalScrollView.setSmoothScrollingEnabled(true);
            customSlowHorizontalScrollView.setAlwaysDrawnWithCacheEnabled(true);
            customSlowHorizontalScrollView.buildDrawingCache();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "setupSroll", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "setupScroll", e2);
        }
    }

    private void x() {
        this.r.a(com.topfreegames.bikerace.m.f.a(this.v.h()), this.v.k(), this.u.j(), this.u.k(), this.v.o());
        if (this.v.a()) {
            com.topfreegames.e.a.a.b().a(this.v.g(), true, new com.topfreegames.e.a.m() { // from class: com.topfreegames.bikerace.activities.ShopActivity.5
                @Override // com.topfreegames.e.a.m
                public void a(final com.topfreegames.e.l lVar, boolean z) {
                    ShopActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (lVar != null) {
                                ShopActivity.this.r.setAvatar(lVar.c());
                            }
                        }
                    });
                }
            }, (Object) this);
        }
    }

    private void a(String str, com.topfreegames.bikerace.c cVar) {
        if (this.u.a(cVar)) {
            this.u.d(cVar);
            this.u.c(cVar);
            this.x.a(cVar, str);
            this.u.F();
            Bundle bundle = new Bundle();
            bundle.putString("productId", str);
            a(aj.PURCHASE_COMPLETED.ordinal(), bundle);
            c(a(cVar));
        }
    }

    private void b(String str, int i) {
        if (this.u.a(i)) {
            this.u.f(i);
            this.x.a(i, str);
            this.u.F();
            Bundle bundle = new Bundle();
            bundle.putString("productId", str);
            a(aj.PURCHASE_COMPLETED.ordinal(), bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.topfreegames.bikerace.c cVar) {
        try {
            a((ShopItemView) findViewById(2131296641), com.topfreegames.bikerace.c.ACROBATIC.equals(cVar));
            a((ShopItemView) findViewById(2131296650), com.topfreegames.bikerace.c.ARMY.equals(cVar));
            a((ShopItemView) findViewById(2131296648), com.topfreegames.bikerace.c.BEAT.equals(cVar));
            a((ShopItemView) findViewById(2131296642), com.topfreegames.bikerace.c.BRONZE.equals(cVar));
            a((ShopItemView) findViewById(2131296644), com.topfreegames.bikerace.c.SPAM.equals(cVar));
            a((ShopItemView) findViewById(2131296649), com.topfreegames.bikerace.c.GHOST.equals(cVar));
            a((ShopItemView) findViewById(2131296639), com.topfreegames.bikerace.c.GIRL.equals(cVar));
            a((ShopItemView) findViewById(2131296651), com.topfreegames.bikerace.c.GOLD.equals(cVar));
            a((ShopItemView) findViewById(2131296652), com.topfreegames.bikerace.c.KIDS.equals(cVar));
            a((ShopItemView) findViewById(2131296643), com.topfreegames.bikerace.c.NINJA.equals(cVar));
            a((ShopItemView) findViewById(2131296645), com.topfreegames.bikerace.c.COP.equals(cVar));
            a((ShopItemView) findViewById(2131296640), com.topfreegames.bikerace.c.RETRO.equals(cVar));
            a((ShopItemView) findViewById(2131296646), com.topfreegames.bikerace.c.SILVER.equals(cVar));
            a((ShopItemView) findViewById(2131296653), com.topfreegames.bikerace.c.SUPER.equals(cVar));
            a((ShopItemView) findViewById(2131296654), com.topfreegames.bikerace.c.ULTRA.equals(cVar));
            a((ShopItemView) findViewById(2131296647), com.topfreegames.bikerace.c.ZOMBIE.equals(cVar));
            a((ShopItemLimitedTimeView) findViewById(2131296638), com.topfreegames.bikerace.c.HALLOWEEN.equals(cVar));
            a((ShopItemLimitedTimeView) findViewById(2131296637), com.topfreegames.bikerace.c.THANKSGIVING.equals(cVar));
            a((ShopItemLimitedTimeView) findViewById(2131296636), com.topfreegames.bikerace.c.SANTA.equals(cVar));
            a((ShopItemLimitedTimeView) findViewById(2131296635), com.topfreegames.bikerace.c.EASTER.equals(cVar));
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateBikeSelection", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateBikeSelection", e2);
            onBackPressed();
        }
    }

    private void a(ShopItemView shopItemView, boolean z) {
        shopItemView.setSelected(z);
    }

    private void a(ShopItemLimitedTimeView shopItemLimitedTimeView, boolean z) {
        shopItemLimitedTimeView.setSelected(z);
    }

    private void a(ShopItemView shopItemView, com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.a.c cVar2) {
        if (cVar2 != null) {
            try {
                if (!this.u.a(cVar)) {
                    if (!cVar2.e()) {
                        this.w.a(cVar2, true);
                    }
                } else if (cVar2.e()) {
                    this.u.d(cVar);
                    this.x.b(cVar);
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateItemView", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateItemView", e2);
                return;
            }
        }
        boolean z = !this.u.a(cVar);
        shopItemView.setItemPurchased(z);
        shopItemView.setSelected(this.u.e() == cVar);
        shopItemView.a(cVar2, z);
        shopItemView.d();
        if (cVar == com.topfreegames.bikerace.c.RETRO) {
            if (this.w.b("AchievLikeBikeRacePage").size() > 0) {
                shopItemView.a("Bike Race", new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ShopActivity.this.r();
                    }
                });
            }
            if (this.w.b("AchievTopFreeGamesPage").size() > 0) {
                shopItemView.a("Top Free Games", new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ShopActivity.this.s();
                    }
                });
            }
        }
        shopItemView.setEnabledAndClickable(com.topfreegames.bikerace.ap.n() || z);
    }

    private void y() {
        if (com.topfreegames.bikerace.worldcup.o.a().r()) {
            if (this.i == null) {
                this.i = new Timer();
                this.i.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.ShopActivity.8
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        try {
                            ShopActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.8.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        long jS = com.topfreegames.bikerace.worldcup.o.a().s();
                                        if (com.topfreegames.bikerace.worldcup.o.a().r()) {
                                            ShopActivity.this.t.a(com.topfreegames.bikerace.worldcup.l.a(jS));
                                        } else {
                                            ShopActivity.this.t.a();
                                            ShopActivity.this.i.cancel();
                                            ShopActivity.this.i = null;
                                        }
                                    } catch (Exception e) {
                                        if (com.topfreegames.bikerace.ap.d()) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }, 0L, 1000L);
                return;
            }
            return;
        }
        ShopItemWorldCup shopItemWorldCup = (ShopItemWorldCup) findViewById(2131296634);
        if (com.topfreegames.bikerace.worldcup.o.a().q()) {
            shopItemWorldCup.a();
        } else {
            shopItemWorldCup.setVisibility(8);
        }
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        if (((BikeRaceApplication) getApplication()).a().a(com.topfreegames.bikerace.c.HALLOWEEN)) {
            if (com.topfreegames.bikerace.am.b(this) && com.topfreegames.bikerace.am.a(this) && this.j == null) {
                this.j = new Timer();
                this.k = com.topfreegames.bikerace.am.c(this);
                this.j.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.ShopActivity.9
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        try {
                            ShopActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.9.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        ShopItemLimitedTimeView shopItemLimitedTimeView = (ShopItemLimitedTimeView) ShopActivity.this.findViewById(2131296638);
                                        shopItemLimitedTimeView.a(com.topfreegames.bikerace.am.d(ShopActivity.this));
                                        long jC = com.topfreegames.bikerace.am.c(ShopActivity.this);
                                        if (jC != ShopActivity.this.k) {
                                            ShopActivity.this.j.cancel();
                                            ShopActivity.this.j = null;
                                            if (jC >= 0) {
                                                ShopActivity.this.z();
                                            } else {
                                                shopItemLimitedTimeView.d();
                                                shopItemLimitedTimeView.setExpired(((BikeRaceApplication) ShopActivity.this.getApplicationContext()).a(false).h());
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (com.topfreegames.bikerace.ap.d()) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }, 0L, this.k);
                return;
            }
            return;
        }
        if (this.j != null) {
            this.j.cancel();
            this.j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        if (((BikeRaceApplication) getApplication()).a().a(com.topfreegames.bikerace.c.THANKSGIVING)) {
            if (com.topfreegames.bikerace.ao.b(this) && com.topfreegames.bikerace.ao.a(this) && this.l == null) {
                this.l = new Timer();
                this.m = com.topfreegames.bikerace.ao.c(this);
                this.l.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.ShopActivity.10
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        try {
                            ShopActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.10.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        ShopItemLimitedTimeView shopItemLimitedTimeView = (ShopItemLimitedTimeView) ShopActivity.this.findViewById(2131296637);
                                        shopItemLimitedTimeView.a(com.topfreegames.bikerace.ao.d(ShopActivity.this));
                                        long jC = com.topfreegames.bikerace.ao.c(ShopActivity.this);
                                        if (jC != ShopActivity.this.m) {
                                            ShopActivity.this.l.cancel();
                                            ShopActivity.this.l = null;
                                            if (jC >= 0) {
                                                ShopActivity.this.A();
                                            } else {
                                                shopItemLimitedTimeView.d();
                                                shopItemLimitedTimeView.setExpired(((BikeRaceApplication) ShopActivity.this.getApplicationContext()).a(false).K());
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (com.topfreegames.bikerace.ap.d()) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }, 0L, this.m);
                return;
            }
            return;
        }
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        if (((BikeRaceApplication) getApplication()).a().a(com.topfreegames.bikerace.c.SANTA)) {
            if (com.topfreegames.bikerace.an.b(this) && com.topfreegames.bikerace.an.a(this) && this.n == null) {
                this.n = new Timer();
                this.o = com.topfreegames.bikerace.an.c(this);
                this.n.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.ShopActivity.11
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        try {
                            ShopActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.11.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        ShopItemLimitedTimeView shopItemLimitedTimeView = (ShopItemLimitedTimeView) ShopActivity.this.findViewById(2131296636);
                                        shopItemLimitedTimeView.a(com.topfreegames.bikerace.an.d(ShopActivity.this));
                                        long jC = com.topfreegames.bikerace.an.c(ShopActivity.this);
                                        if (jC != ShopActivity.this.o) {
                                            ShopActivity.this.n.cancel();
                                            ShopActivity.this.n = null;
                                            if (jC >= 0) {
                                                ShopActivity.this.B();
                                            } else {
                                                shopItemLimitedTimeView.d();
                                                shopItemLimitedTimeView.setExpired(((BikeRaceApplication) ShopActivity.this.getApplicationContext()).a(false).O());
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (com.topfreegames.bikerace.ap.d()) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }, 0L, this.o);
                return;
            }
            return;
        }
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        if (((BikeRaceApplication) getApplication()).a().a(com.topfreegames.bikerace.c.EASTER)) {
            if (com.topfreegames.bikerace.al.b(this) && com.topfreegames.bikerace.al.a(this) && this.p == null) {
                this.p = new Timer();
                this.q = com.topfreegames.bikerace.al.c(this);
                this.p.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.ShopActivity.13
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        try {
                            ShopActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.13.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        ShopItemLimitedTimeView shopItemLimitedTimeView = (ShopItemLimitedTimeView) ShopActivity.this.findViewById(2131296635);
                                        shopItemLimitedTimeView.a(com.topfreegames.bikerace.al.d(ShopActivity.this));
                                        long jC = com.topfreegames.bikerace.al.c(ShopActivity.this);
                                        if (jC != ShopActivity.this.q) {
                                            ShopActivity.this.p.cancel();
                                            ShopActivity.this.p = null;
                                            if (jC >= 0) {
                                                ShopActivity.this.C();
                                            } else {
                                                shopItemLimitedTimeView.d();
                                                shopItemLimitedTimeView.setExpired(((BikeRaceApplication) ShopActivity.this.getApplicationContext()).a(false).S());
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (com.topfreegames.bikerace.ap.d()) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }, 0L, this.q);
                return;
            }
            return;
        }
        if (this.p != null) {
            this.p.cancel();
            this.p = null;
        }
    }

    private void a(ShopItemLimitedTimeView shopItemLimitedTimeView, com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.a.c cVar2, boolean z) {
        try {
            com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a((Context) this);
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
            if (cVar2 != null) {
                if (!zVarA.a(cVar)) {
                    if (!cVar2.e()) {
                        fVarA.a(cVar2, true);
                    }
                } else if (cVar2.e() && !z) {
                    zVarA.d(cVar);
                    bikeRaceApplication.d().b(cVar);
                }
            }
            if (z) {
                shopItemLimitedTimeView.d();
                shopItemLimitedTimeView.setExpired(getString(2131100015));
                return;
            }
            boolean z2 = !zVarA.a(cVar);
            shopItemLimitedTimeView.setItemPurchased(z2);
            shopItemLimitedTimeView.setSelected(zVarA.e() == cVar);
            if (cVar == com.topfreegames.bikerace.c.EASTER) {
                shopItemLimitedTimeView.a(cVar2, z2, new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.ShopActivity.14
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ShopActivity.this.a(aj.EASTER_EGGS_LOCATION.ordinal());
                    }
                });
            } else {
                shopItemLimitedTimeView.a(cVar2, z2);
            }
            if (shopItemLimitedTimeView.j != null) {
                if (!com.topfreegames.bikerace.ap.n() && !z2) {
                    shopItemLimitedTimeView.j.setClickable(false);
                    shopItemLimitedTimeView.j.setEnabled(false);
                } else {
                    shopItemLimitedTimeView.j.setClickable(true);
                    shopItemLimitedTimeView.j.setEnabled(true);
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateItemView", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateItemView", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.DEFAULT;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296631);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == aj.BILLING_UNAVAILABLE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099777), getString(2131099659), null);
        }
        if (i == aj.RESTORE_OFFLINE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099778), getString(2131099659), null);
        }
        if (i == aj.EASTER_EGGS_LOCATION.ordinal()) {
            return new com.topfreegames.bikerace.e.g(this);
        }
        return null;
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        ai aiVar = null;
        if (i == aj.BILLING_UNAVAILABLE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099777), getString(2131099659), null);
        }
        if (i == aj.RESTORE_OFFLINE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099778), getString(2131099659), null);
        }
        if (i == aj.PURCHSE_REFUND.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, String.format(getResources().getString(2131099856), e(bundle.getString("productId"))), getString(2131099659), null);
        }
        if (i == aj.PURCHASE_COMPLETED.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, String.format(getResources().getString(2131099860), e(bundle.getString("productId"))), getString(2131099659), null);
        }
        if (i == aj.PURCHASE_NOT_COMPLETED.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, String.format(getResources().getString(2131099861), e(bundle.getString("productId"))), getString(2131099659), getString(2131099664), null, new am(this, bundle.getString("productId")));
        }
        if (i == aj.PURCHASE_FAILED.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099857), getString(2131099659), null);
        }
        if (i == aj.PURCHASE_CANCELED_BY_USER.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099859), getString(2131099659), null);
        }
        if (i == aj.PURCHASE_REVOKED.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, String.format(getResources().getString(2131099862), e(bundle.getString("productId"))), getString(2131099659), null);
        }
        if (i == aj.LIKE_ERROR.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099864), getString(2131099659), null);
        }
        if (i != aj.BIKE_UNLOCK.ordinal() || this.b == null) {
            return null;
        }
        com.topfreegames.bikerace.e.b bVar = new com.topfreegames.bikerace.e.b(this, this.b, new ai(this, aiVar));
        this.b = null;
        return bVar;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        return false;
    }

    @Override // com.topfreegames.bikerace.a.g
    public void a(final com.topfreegames.bikerace.a.c cVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.ShopActivity.15
            @Override // java.lang.Runnable
            public void run() {
                ShopActivity.this.b = cVar;
                ShopActivity.this.a(aj.BIKE_UNLOCK.ordinal());
            }
        });
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void f() {
        super.f();
        com.topfreegames.bikerace.k.a.a().a((com.topfreegames.bikerace.k.e) null);
    }
}
