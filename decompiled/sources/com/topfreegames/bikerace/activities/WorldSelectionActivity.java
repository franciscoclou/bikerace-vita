package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.topfreegames.bikerace.bn;
import com.topfreegames.bikerace.views.WorldItemView;
import com.topfreegames.bikerace.views.WorldItemView_Easter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldSelectionActivity extends c {
    private com.topfreegames.bikerace.t c;
    private com.topfreegames.bikerace.z d;
    private com.topfreegames.bikerace.multiplayer.o e;
    private int b = 0;
    private final View.OnClickListener f = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (WorldSelectionActivity.this.b >= 0) {
                Intent intent = new Intent();
                intent.setClass(WorldSelectionActivity.this, MainActivity.class);
                WorldSelectionActivity.this.a(intent, 2130968588, 2130968583);
                return;
            }
            WorldSelectionActivity.this.a(2131296778, true);
        }
    };
    private final View.OnClickListener g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", WorldSelectionActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldPackSelected", WorldSelectionActivity.this.b);
            WorldSelectionActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private final View.OnClickListener h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), CustomLevelsActivity.class);
            WorldSelectionActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private final View.OnClickListener i = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WorldSelectionActivity.this.a(true);
        }
    };
    private final View.OnClickListener j = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(WorldSelectionActivity.this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", MainActivity.class);
            WorldSelectionActivity.this.a(intent, 2130968587, 2130968583);
        }
    };

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        int i;
        try {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            this.c = bikeRaceApplication.d();
            this.d = bikeRaceApplication.a();
            this.e = bikeRaceApplication.c();
            super.onCreate(bundle);
            setContentView(2130903102);
            if (bundle != null) {
                this.b = bundle.getInt("com.topfreegames.bikerace.WorldPackSelected");
            } else {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    this.b = extras.getInt("com.topfreegames.bikerace.WorldPackSelected");
                    if (this.b == 0 && ((i = extras.getInt("com.topfreegames.bikerace.WorldSelected")) == 13 || i == 14 || i == 18 || i == 15 || i == 16 || i == 17)) {
                        this.b = -1;
                    }
                }
            }
            findViewById(2131296746).setOnClickListener(this.f);
            View viewFindViewById = findViewById(2131296749);
            if (com.topfreegames.bikerace.ap.m()) {
                viewFindViewById.setOnClickListener(this.g);
                viewFindViewById.setVisibility(0);
            } else {
                viewFindViewById.setVisibility(4);
            }
            View viewFindViewById2 = findViewById(2131296747);
            View viewFindViewById3 = findViewById(2131296748);
            if (com.topfreegames.bikerace.ap.e() || ((BikeRaceApplication) getApplication()).a().a()) {
                viewFindViewById2.setVisibility(4);
            } else {
                viewFindViewById3.setOnClickListener(this.j);
            }
            if (this.b == 0) {
                a(getIntent().getIntExtra("com.topfreegames.bikerace.WorldSelected", 0), false);
            } else {
                a(false);
            }
            ((BikeRaceApplication) getApplication()).a(true);
            com.topfreegames.bikerace.localnotification.limitedtimebike.c.a(this);
            com.topfreegames.bikerace.g.a aVarG = com.topfreegames.bikerace.g.a.g();
            aVarG.j();
            aVarG.b(true);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "onCreate", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "onCreate", e2);
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
            this.c.a(getClass().getName(), "onStart", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "onStart", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, WorldSelectionActivity.class) && !ah.a(this, WorldSelectionActivity.class)) {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                bikeRaceApplication.d().d();
                if (hasWindowFocus()) {
                    bikeRaceApplication.b().e();
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "onResume", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "onResume", e2);
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
        this.f.onClick(null);
    }

    private Dialog a(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, final String str, final int i7) {
        Resources resources = getResources();
        String string = resources.getString(2131099687);
        Object[] objArr = new Object[1];
        objArr[0] = i7 == 999 ? getString(2131099697) : getString(2131099696);
        String string2 = String.format(string, objArr);
        if (i > 0) {
            string2 = String.valueOf(string2) + String.format(resources.getString(2131099688), Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (i3 > 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(string2));
            String string3 = resources.getString(2131099689);
            Object[] objArr2 = new Object[3];
            objArr2[0] = Integer.valueOf(i3);
            objArr2[1] = Integer.valueOf(i4);
            objArr2[2] = i3 > 1 ? "s" : "";
            string2 = sb.append(String.format(string3, objArr2)).toString();
        }
        if (i5 > 0) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(string2));
            String string4 = resources.getString(2131099690);
            Object[] objArr3 = new Object[3];
            objArr3[0] = Integer.valueOf(i5);
            objArr3[1] = Integer.valueOf(i6);
            objArr3[2] = i5 > 1 ? "s" : "";
            string2 = sb2.append(String.format(string4, objArr3)).toString();
        }
        if (z) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(string2));
            String string5 = resources.getString(2131099691);
            Object[] objArr4 = new Object[1];
            objArr4[0] = z2 ? getString(2131099694) : getString(2131099695);
            string2 = sb3.append(String.format(string5, objArr4)).toString();
        }
        if (z3) {
            StringBuilder sb4 = new StringBuilder(String.valueOf(string2));
            String string6 = resources.getString(2131099692);
            Object[] objArr5 = new Object[1];
            objArr5[0] = z4 ? getString(2131099694) : getString(2131099695);
            string2 = sb4.append(String.format(string6, objArr5)).toString();
        }
        if (z5) {
            StringBuilder sb5 = new StringBuilder(String.valueOf(string2));
            String string7 = resources.getString(2131099693);
            Object[] objArr6 = new Object[1];
            objArr6[0] = z6 ? getString(2131099694) : getString(2131099695);
            string2 = sb5.append(String.format(string7, objArr6)).toString();
        }
        if (str == null || str.equals("") || !com.topfreegames.bikerace.ap.n()) {
            return new com.topfreegames.bikerace.e.n(this, string2, getString(2131099659), null);
        }
        return new com.topfreegames.bikerace.e.n(this, string2, getString(2131099659), getString(2131099665), null, new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.6
            @Override // com.topfreegames.bikerace.e.o
            public void a() {
                Intent intent = new Intent();
                intent.setClass(WorldSelectionActivity.this, ShopActivity.class);
                intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", WorldSelectionActivity.class);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", i7);
                intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
                WorldSelectionActivity.this.a(intent, 2130968587, 2130968583);
            }
        }, true);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        int iE;
        int iF;
        int iG;
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplication()).a();
        String string = null;
        if (i == 2) {
            string = getResources().getString(2131099823);
        } else if (i == 3) {
            string = getResources().getString(2131099825);
        } else if (i == 4) {
            string = getResources().getString(2131099827);
        } else if (i == 5) {
            string = getResources().getString(2131099829);
        } else if (i == 6) {
            string = getResources().getString(2131099831);
        } else if (i == 7) {
            string = getResources().getString(2131099833);
        } else if (i == 8) {
            string = getResources().getString(2131099835);
        } else if (i == 9) {
            string = getResources().getString(2131099837);
        } else if (i == 10) {
            string = getResources().getString(2131099839);
        } else if (i == 11) {
            string = getResources().getString(2131099841);
        } else if (i == 12) {
            string = getResources().getString(2131099843);
        }
        com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) getApplication()).c();
        if (i > 0) {
            iE = e(i);
            iF = f(i);
            iG = g(i);
        } else if (i == -1) {
            iE = e(13);
            iF = f(13);
            iG = g(13);
        } else {
            iE = 0;
            iF = 0;
            iG = 0;
        }
        return a(iE, zVarA.p(), iF, oVarC.k(), iG, oVarC.o(), i == 10, zVarA.a(com.topfreegames.bikerace.c.SILVER), i == 9, zVarA.a(com.topfreegames.bikerace.c.NINJA), i == 11, zVarA.a(com.topfreegames.bikerace.c.BEAT), string, i);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296744);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return this.b == -1 ? b.EASTER : b.DEFAULT;
    }

    private void b(int i) {
        try {
            WorldItemView_Easter worldItemView_Easter = (WorldItemView_Easter) findViewById(i);
            if (com.topfreegames.bikerace.ap.a(worldItemView_Easter.getWorldID())) {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
                com.topfreegames.bikerace.multiplayer.o oVarC = bikeRaceApplication.c();
                boolean zA = zVarA.a(13) | zVarA.a(14) | zVarA.a(15) | zVarA.a(16) | zVarA.a(17) | zVarA.a(18) | zVarA.a(19);
                if (zA) {
                    boolean[] zArr = new boolean[3];
                    if (a(zVarA, oVarC, 13)) {
                        zVarA.f(13);
                        zArr[0] = true;
                    }
                    if (a(zVarA, oVarC, 14)) {
                        zVarA.f(14);
                        zArr[1] = true;
                    }
                    if (a(zVarA, oVarC, 15)) {
                        zVarA.f(15);
                        zArr[2] = true;
                    }
                    zA = !(zArr[2] & (zArr[0] & zArr[1]));
                }
                worldItemView_Easter.setLocked(zA);
                worldItemView_Easter.a(zVarA.b(19) <= 0 && zVarA.p() > 0);
                if (zA) {
                    worldItemView_Easter.setOnClickListener(new au(this, worldItemView_Easter.getWorldID()));
                } else {
                    worldItemView_Easter.setOnClickListener(this.i);
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateWorldPackItemView", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateWorldPackItemView", e2);
        }
    }

    private void c(int i) {
        try {
            WorldItemView worldItemView = (WorldItemView) findViewById(i);
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
            com.topfreegames.bikerace.multiplayer.o oVarC = bikeRaceApplication.c();
            int worldID = worldItemView.getWorldID();
            boolean zA = (com.topfreegames.bikerace.h.a.q.a().c() <= 0) & zVarA.a(worldID);
            if (zA && a(zVarA, oVarC, worldID)) {
                zVarA.f(worldID);
                zA = false;
            }
            worldItemView.setLocked(zA);
            worldItemView.setOnClickListener(zA ? new au(this, worldID) : this.h);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateUserLevelsWorldView", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateUserLevelsWorldView", e2);
        }
    }

    private void g() {
        try {
            WorldItemView worldItemView = (WorldItemView) findViewById(2131296781);
            if (com.topfreegames.bikerace.ap.n()) {
                worldItemView.setVisibility(0);
                worldItemView.setOnClickListener(this.g);
            } else {
                worldItemView.setVisibility(8);
                worldItemView.setOnClickListener(null);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateShopView", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateShopView", e2);
        }
    }

    private void d(int i) {
        try {
            WorldItemView worldItemView = (WorldItemView) findViewById(i);
            int worldID = worldItemView.getWorldID();
            if (com.topfreegames.bikerace.ap.a(worldID)) {
                boolean zA = this.d.a(worldItemView.getWorldID());
                boolean z = worldID == 9;
                boolean z2 = worldID == 10;
                boolean z3 = worldID == 11;
                int iP = this.d.p();
                if (zA && iP >= e(worldID) && this.e.k() >= f(worldID) && this.e.o() >= g(worldID)) {
                    if (z2) {
                        if (!this.d.a(com.topfreegames.bikerace.c.SILVER)) {
                            zA = false;
                        }
                    } else if (z) {
                        if (!this.d.a(com.topfreegames.bikerace.c.NINJA)) {
                            zA = false;
                        }
                    } else if (!z3 || !this.d.a(com.topfreegames.bikerace.c.BEAT)) {
                        zA = false;
                    }
                    if (!zA) {
                        this.d.f(worldID);
                    }
                }
                worldItemView.setLocked(zA);
                int iB = this.d.b(worldID);
                worldItemView.setCurrentStars(iB);
                worldItemView.a(iB <= 0 && iP > 0 && worldID == 19);
                if (zA) {
                    worldItemView.setOnClickListener(new au(this, worldID));
                    return;
                } else {
                    worldItemView.setOnClickListener(new at(this, worldID));
                    return;
                }
            }
            worldItemView.setVisibility(8);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateWorldViewItem", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "updateWorldViewItem", e2);
        }
    }

    private int e(int i) {
        try {
            return bn.a(i).f1162a;
        } catch (Exception e) {
            return 0;
        }
    }

    private int f(int i) {
        try {
            return bn.a(i).c;
        } catch (Exception e) {
            return 0;
        }
    }

    private int g(int i) {
        try {
            return bn.a(i).b;
        } catch (Exception e) {
            return 0;
        }
    }

    private View h(int i) {
        switch (i) {
            case 1:
                return findViewById(2131296766);
            case 2:
                return findViewById(2131296767);
            case 3:
                return findViewById(2131296768);
            case 4:
                return findViewById(2131296769);
            case 5:
                return findViewById(2131296770);
            case 6:
                return findViewById(2131296771);
            case 7:
                return findViewById(2131296772);
            case 8:
                return findViewById(2131296773);
            case 9:
                return findViewById(2131296774);
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return findViewById(2131296774);
            case XMLStreamConstants.DTD /* 11 */:
                return findViewById(2131296776);
            case XMLStreamConstants.CDATA /* 12 */:
                return findViewById(2131296777);
            case XMLStreamConstants.NAMESPACE /* 13 */:
                return findViewById(2131296787);
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                return findViewById(2131296788);
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return findViewById(2131296786);
            case 16:
                return findViewById(2131296785);
            case 17:
                return findViewById(2131296784);
            case 18:
                return findViewById(2131296783);
            case 19:
                return findViewById(2131296782);
            case 999:
                return findViewById(2131296780);
            default:
                return null;
        }
    }

    private void c(final View view) {
        if (view != null) {
            final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) (this.b >= 0 ? findViewById(2131296750) : findViewById(2131296752));
            horizontalScrollView.post(new Runnable() { // from class: com.topfreegames.bikerace.activities.WorldSelectionActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    horizontalScrollView.scrollTo(((view.getLeft() + view.getRight()) - horizontalScrollView.getWidth()) / 2, 0);
                }
            });
            b().invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        i();
        a(b.EASTER);
        View viewFindViewById = findViewById(2131296750);
        View viewFindViewById2 = findViewById(2131296752);
        if (z) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, 2130968581);
            Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this, 2130968582);
            animationLoadAnimation2.setAnimationListener(new as(this, viewFindViewById));
            viewFindViewById.setAnimation(animationLoadAnimation2);
            animationLoadAnimation.setAnimationListener(new ar(this, viewFindViewById2));
            viewFindViewById2.setAnimation(animationLoadAnimation);
            animationLoadAnimation.start();
            animationLoadAnimation2.start();
        } else {
            viewFindViewById.setVisibility(8);
            viewFindViewById2.setVisibility(0);
        }
        this.b = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        h();
        a(b.DEFAULT);
        View viewFindViewById = findViewById(2131296750);
        View viewFindViewById2 = findViewById(2131296752);
        if (z) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, 2130968581);
            Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this, 2130968582);
            animationLoadAnimation.setAnimationListener(new ar(this, viewFindViewById));
            viewFindViewById.setAnimation(animationLoadAnimation);
            animationLoadAnimation2.setAnimationListener(new as(this, viewFindViewById2));
            viewFindViewById2.setAnimation(animationLoadAnimation2);
            animationLoadAnimation.start();
            animationLoadAnimation2.start();
        } else {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(8);
        }
        this.b = 0;
        c(h(i));
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        try {
            super.onRestoreInstanceState(bundle);
            this.b = bundle.getInt("com.topfreegames.bikerace.WorldPackSelected");
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "onRestoreInstanceState", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "onRestoreInstanceState", e2);
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putInt("com.topfreegames.bikerace.WorldPackSelected", this.b);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            this.c.a(getClass().getName(), "onSaveInstanceState", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            this.c.a(getClass().getName(), "onSaveInstanceState", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", WorldSelectionActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    private boolean a(com.topfreegames.bikerace.z zVar, com.topfreegames.bikerace.multiplayer.o oVar, int i) {
        return zVar.p() >= e(i) && oVar.k() >= f(i) && oVar.o() >= g(i);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void f() {
        super.f();
        com.topfreegames.bikerace.k.a.a().a((com.topfreegames.bikerace.k.e) null);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void e() {
        super.e();
        com.topfreegames.bikerace.g.a.g().b(false);
    }

    private void h() {
        ViewStub viewStub = (ViewStub) findViewById(2131296751);
        if (viewStub != null) {
            viewStub.inflate();
            d(2131296766);
            d(2131296767);
            d(2131296768);
            d(2131296769);
            d(2131296770);
            d(2131296771);
            d(2131296772);
            d(2131296773);
            d(2131296774);
            d(2131296775);
            d(2131296776);
            d(2131296777);
            g();
            c(2131296780);
            b(2131296778);
            a(findViewById(2131296750));
        }
    }

    private void i() {
        ViewStub viewStub = (ViewStub) findViewById(2131296753);
        if (viewStub != null) {
            viewStub.inflate();
            d(2131296787);
            d(2131296788);
            d(2131296786);
            d(2131296785);
            d(2131296784);
            d(2131296783);
            d(2131296782);
            a(findViewById(2131296752));
        }
    }
}
