package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.topfreegames.bikerace.worldcup.views.WorldCupShopTabView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupShopActivity extends com.topfreegames.bikerace.billing.a {
    private static /* synthetic */ int[] F;
    private static int b = 1;
    private static int c = 6;
    private static int d = 12;
    private static int e = 30;
    private static int f = 60;
    private static int g = 86;
    private Object A;
    private com.topfreegames.bikerace.worldcup.r B = com.topfreegames.bikerace.worldcup.r.BIKE;
    private com.topfreegames.bikerace.worldcup.i C = new com.topfreegames.bikerace.worldcup.i() { // from class: com.topfreegames.bikerace.activities.WorldCupShopActivity.1
        @Override // com.topfreegames.bikerace.worldcup.i
        public void a(long j) {
        }

        @Override // com.topfreegames.bikerace.worldcup.i
        public void a(com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.worldcup.p pVar, long j) {
            WorldCupShopActivity.this.a(cVar, pVar);
        }
    };
    private View.OnClickListener D = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldCupShopActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == WorldCupShopActivity.this.l) {
                Intent intent = new Intent();
                if (WorldCupShopActivity.this.A == null || WorldCupShopActivity.this.A == MainActivity.class) {
                    WorldCupShopActivity.this.A = MainActivity.class;
                    intent.setClass(WorldCupShopActivity.this, MainActivity.class);
                } else {
                    intent.setClass(WorldCupShopActivity.this, ShopActivity.class);
                }
                intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", (Serializable) WorldCupShopActivity.this.A);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", WorldCupShopActivity.this.w);
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", WorldCupShopActivity.this.x);
                intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", WorldCupShopActivity.this.y);
                intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
                intent.putExtra("com.topfreegames.bikerace.WorldPackSelected", WorldCupShopActivity.this.z);
                WorldCupShopActivity.this.a(intent, 2130968588, 2130968583);
            }
        }
    };
    private View.OnClickListener E = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldCupShopActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.topfreegames.bikerace.worldcup.r rVar;
            if (view != WorldCupShopActivity.this.m) {
                if (view != WorldCupShopActivity.this.o) {
                    if (view == WorldCupShopActivity.this.n) {
                        rVar = com.topfreegames.bikerace.worldcup.r.SLOT_RARE;
                    } else {
                        rVar = (view == WorldCupShopActivity.this.p || view == WorldCupShopActivity.this.u) ? com.topfreegames.bikerace.worldcup.r.GEMSHOP : null;
                    }
                } else {
                    rVar = com.topfreegames.bikerace.worldcup.r.SLOT_ORDINARY;
                }
            } else {
                rVar = com.topfreegames.bikerace.worldcup.r.BIKE;
            }
            WorldCupShopActivity.this.a(rVar, (Bundle) null);
        }
    };
    private com.topfreegames.bikerace.billing.b h;
    private com.topfreegames.bikerace.worldcup.q i;
    private com.topfreegames.bikerace.worldcup.f j;
    private ViewGroup k;
    private View l;
    private WorldCupShopTabView m;
    private WorldCupShopTabView n;
    private WorldCupShopTabView o;
    private WorldCupShopTabView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private View u;
    private Timer v;
    private int w;
    private int x;
    private boolean y;
    private int z;

    static /* synthetic */ int[] k() {
        int[] iArr = F;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.worldcup.p.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.worldcup.p.ORDINARY.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.worldcup.p.RARE.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            F = iArr;
        }
        return iArr;
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.A = bundle.get("com.topfreegames.bikerace.ReturnToActivity");
            if (this.A != null) {
                this.w = bundle.getInt("com.topfreegames.bikerace.WorldSelected");
                this.x = bundle.getInt("com.topfreegames.bikerace.PhaseSelected");
                this.y = bundle.getBoolean("com.topfreegame.bikerace.IsMultiplayer");
                this.z = bundle.getInt("com.topfreegames.bikerace.WorldPackSelected");
            }
        } else {
            Bundle extras = getIntent().getExtras();
            this.A = extras.get("com.topfreegames.bikerace.ReturnToActivity");
            if (this.A != null) {
                this.w = extras.getInt("com.topfreegames.bikerace.WorldSelected");
                this.x = extras.getInt("com.topfreegames.bikerace.PhaseSelected");
                this.y = extras.getBoolean("com.topfreegame.bikerace.IsMultiplayer");
                this.z = extras.getInt("com.topfreegames.bikerace.WorldPackSelected");
            }
        }
        com.topfreegames.bikerace.worldcup.localnotification.a.a((BikeRaceApplication) getApplicationContext());
        this.j = com.topfreegames.bikerace.worldcup.o.a().g();
        setContentView(2130903120);
        l();
        this.i = new com.topfreegames.bikerace.worldcup.q(this);
        this.l = findViewById(2131296856);
        this.l.setOnClickListener(this.D);
        this.m = (WorldCupShopTabView) findViewById(2131296864);
        this.m.setOnClickListener(this.E);
        this.m.setup(com.topfreegames.bikerace.worldcup.views.e.BIKES);
        this.o = (WorldCupShopTabView) findViewById(2131296865);
        this.o.setOnClickListener(this.E);
        this.o.setup(com.topfreegames.bikerace.worldcup.views.e.SLOT_ORDINARY);
        this.n = (WorldCupShopTabView) findViewById(2131296866);
        this.n.setOnClickListener(this.E);
        this.n.setup(com.topfreegames.bikerace.worldcup.views.e.SLOT_RARE);
        this.p = (WorldCupShopTabView) findViewById(2131296867);
        this.p.setOnClickListener(this.E);
        this.p.setup(com.topfreegames.bikerace.worldcup.views.e.GEM_SHOP);
        this.u = findViewById(2131296861);
        this.u.setOnClickListener(this.E);
        this.k = (ViewGroup) findViewById(2131296862);
        this.s = (TextView) findViewById(2131296858);
        this.t = (TextView) findViewById(2131296857);
        this.q = (TextView) findViewById(2131296860);
        this.r = (TextView) findViewById(2131296859);
        j();
        a(b());
    }

    public void j() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.WorldCupShopActivity.4
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.worldcup.o oVarA = com.topfreegames.bikerace.worldcup.o.a();
                int iJ = oVarA.j();
                int iH = oVarA.h();
                WorldCupShopActivity.this.r.setText(Integer.toString(iJ));
                WorldCupShopActivity.this.q.setText(Integer.toString(iH));
                WorldCupShopActivity.this.o.a(iJ / oVarA.b(com.topfreegames.bikerace.worldcup.p.ORDINARY).b());
                WorldCupShopActivity.this.n.a(iH / oVarA.b(com.topfreegames.bikerace.worldcup.p.RARE).a());
            }
        });
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!ag.a(this, WorldSelectionActivity.class) && !ah.a(this, WorldSelectionActivity.class)) {
            com.topfreegames.bikerace.worldcup.o oVarA = com.topfreegames.bikerace.worldcup.o.a();
            oVarA.a(this.C);
            m();
            com.topfreegames.bikerace.worldcup.h hVarV = oVarA.v();
            a(hVarV.c(), hVarV.d());
            j();
            ((BikeRaceApplication) getApplication()).b().b(getApplicationContext());
            a(true);
            this.j.d();
            if (this.j.a() && com.topfreegames.bikerace.worldcup.o.a().l()) {
                a(com.topfreegames.bikerace.worldcup.r.DAILY_BONUS, (Bundle) null);
                this.B = com.topfreegames.bikerace.worldcup.r.DAILY_BONUS;
            } else {
                a(this.B, (Bundle) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.topfreegames.bikerace.worldcup.r rVar, Bundle bundle) {
        this.B = rVar;
        this.k.removeAllViews();
        View viewA = this.i.a(rVar, bundle);
        a(viewA);
        this.k.addView(viewA);
        b(rVar);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void c() {
        this.D.onClick(this.l);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.DEFAULT;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296854);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        return false;
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putSerializable("com.topfreegames.bikerace.ReturnToActivity", (Class) this.A);
            if (this.A != MainActivity.class) {
                bundle.putInt("com.topfreegames.bikerace.WorldSelected", this.w);
                bundle.putInt("com.topfreegames.bikerace.PhaseSelected", this.x);
                bundle.putBoolean("com.topfreegame.bikerace.IsMultiplayer", this.y);
                bundle.putInt("com.topfreegames.bikerace.WorldPackSelected", this.z);
            }
        } catch (Error e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onSaveInstanceState", e2);
            throw e2;
        } catch (Exception e3) {
            if (com.topfreegames.bikerace.ap.d()) {
                e3.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onSaveInstanceState", e3);
        }
    }

    private void l() {
        ArrayList arrayList = new ArrayList();
        Resources resources = getResources();
        arrayList.add(resources.getString(2131100062));
        arrayList.add(resources.getString(2131100064));
        arrayList.add(resources.getString(2131100066));
        arrayList.add(resources.getString(2131100068));
        arrayList.add(resources.getString(2131100070));
        arrayList.add(resources.getString(2131100072));
        if (com.topfreegames.bikerace.ap.o()) {
            this.h = new com.topfreegames.bikerace.billing.google.j(this, arrayList);
        } else {
            if (com.topfreegames.bikerace.ap.p()) {
                this.h = new com.topfreegames.bikerace.billing.a.a(this, arrayList);
                return;
            }
            throw new IllegalStateException("Check the profile config");
        }
    }

    private void f(String str) {
        Resources resources = getResources();
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplicationContext()).a();
        if (str.equals(resources.getString(2131100062))) {
            zVarA.i(b);
        } else if (str.equals(resources.getString(2131100064))) {
            zVarA.i(c);
        } else if (str.equals(resources.getString(2131100066))) {
            zVarA.i(d);
        } else if (str.equals(resources.getString(2131100068))) {
            zVarA.i(e);
        } else if (str.equals(resources.getString(2131100070))) {
            zVarA.i(f);
        } else if (str.equals(resources.getString(2131100072))) {
            zVarA.i(g);
        }
        j();
        if (zVarA.c()) {
            zVarA.F();
        }
    }

    private void a(String str, aq aqVar) {
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void a(String str, int i) {
        f(str);
    }

    public void e(String str) {
        if (this.h != null && !this.h.b()) {
            a(ap.BILLING_UNAVAILABLE.ordinal());
        } else {
            this.h.a(str);
        }
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void b(String str) {
        a(str, aq.NOT_COMPLETED);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void d(String str) {
        a(str, aq.REFUND);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void c(String str) {
        Bundle bundle = null;
        if (str != null) {
            bundle = new Bundle();
            bundle.putString("productId", str);
        }
        a(ap.PURCHASE_FAILED.ordinal(), bundle);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void h() {
        c((String) null);
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void g() {
        a(ap.PURCHASE_CANCELED_BY_USER.ordinal());
    }

    @Override // com.topfreegames.bikerace.billing.a
    public void i() {
        a(ap.BILLING_UNAVAILABLE.ordinal());
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
            a(it.next(), aq.REVOKED);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            this.h.d();
        }
    }

    private void m() {
        this.v = new Timer();
        this.v.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.WorldCupShopActivity.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                WorldCupShopActivity.this.s.post(new Runnable() { // from class: com.topfreegames.bikerace.activities.WorldCupShopActivity.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String strA = com.topfreegames.bikerace.worldcup.l.a(com.topfreegames.bikerace.worldcup.o.a().s());
                        if (strA != null) {
                            WorldCupShopActivity.this.s.setVisibility(0);
                            WorldCupShopActivity.this.s.setText(strA);
                        } else {
                            WorldCupShopActivity.this.s.setVisibility(8);
                            WorldCupShopActivity.this.t.setVisibility(8);
                        }
                    }
                });
            }
        }, 0L, 1000L);
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onPause() {
        super.onPause();
        com.topfreegames.bikerace.worldcup.o.a().u();
        ((BikeRaceApplication) getApplication()).b().k();
        if (this.v != null) {
            this.v.cancel();
            this.v.purge();
            this.v = null;
        }
    }

    private void b(com.topfreegames.bikerace.worldcup.r rVar) {
        this.m.setClickable(rVar != com.topfreegames.bikerace.worldcup.r.BIKE);
        this.o.setClickable(rVar != com.topfreegames.bikerace.worldcup.r.SLOT_ORDINARY);
        this.n.setClickable(rVar != com.topfreegames.bikerace.worldcup.r.SLOT_RARE);
        this.p.setClickable(rVar != com.topfreegames.bikerace.worldcup.r.GEMSHOP);
        this.m.setSelected(rVar == com.topfreegames.bikerace.worldcup.r.BIKE);
        this.o.setSelected(rVar == com.topfreegames.bikerace.worldcup.r.SLOT_ORDINARY);
        this.n.setSelected(rVar == com.topfreegames.bikerace.worldcup.r.SLOT_RARE);
        this.p.setSelected(rVar == com.topfreegames.bikerace.worldcup.r.GEMSHOP);
    }

    public void a(boolean z) {
        com.topfreegames.bikerace.worldcup.o oVarA = com.topfreegames.bikerace.worldcup.o.a();
        this.m.setClickable(this.B != com.topfreegames.bikerace.worldcup.r.BIKE);
        this.o.setClickable(this.B != com.topfreegames.bikerace.worldcup.r.SLOT_ORDINARY);
        this.n.setClickable(this.B != com.topfreegames.bikerace.worldcup.r.SLOT_RARE);
        this.p.setClickable(this.B != com.topfreegames.bikerace.worldcup.r.GEMSHOP);
        this.u.setEnabled(z && oVarA.n());
        this.m.setEnabled((z && oVarA.m()) || this.B == com.topfreegames.bikerace.worldcup.r.BIKE);
        this.o.setEnabled((z && oVarA.o()) || this.B == com.topfreegames.bikerace.worldcup.r.SLOT_ORDINARY);
        this.n.setEnabled((z && oVarA.p()) || this.B == com.topfreegames.bikerace.worldcup.r.SLOT_RARE);
        this.p.setEnabled((z && oVarA.n()) || this.B == com.topfreegames.bikerace.worldcup.r.GEMSHOP);
    }

    public void a(com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.worldcup.views.c cVar2) {
        new com.topfreegames.bikerace.worldcup.views.a(this, cVar, cVar2).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:6:0x0010  */
    public void a(com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.worldcup.p pVar) {
        com.topfreegames.bikerace.c cVar2 = null;
        if (pVar != null) {
            switch (k()[pVar.ordinal()]) {
                case 1:
                    cVar2 = cVar;
                    cVar = null;
                    break;
                case 2:
                    break;
                default:
                    cVar = null;
                    break;
            }
        } else {
            cVar = null;
        }
        this.o.a(cVar2);
        this.n.a(cVar);
    }

    public void a(com.topfreegames.bikerace.worldcup.r rVar) {
        a(rVar, (Bundle) null);
        b(rVar);
    }

    public void a(com.topfreegames.bikerace.c cVar) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.topfreegames.bikerace.ShopCenter", cVar.ordinal());
        a(com.topfreegames.bikerace.worldcup.r.BIKE, bundle);
        b(com.topfreegames.bikerace.worldcup.r.BIKE);
    }

    public void a(Intent intent) {
        a(intent, 2130968588, 2130968583);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void f() {
        super.f();
        com.topfreegames.bikerace.k.a.a().a((com.topfreegames.bikerace.k.e) null);
    }
}
