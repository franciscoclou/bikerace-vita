package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.bn;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MainActivity extends c {
    private static boolean b = true;
    private static /* synthetic */ int[] k;
    private final View.OnClickListener c = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, WorldSelectionActivity.class);
            MainActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private final View.OnClickListener d = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", MainActivity.class);
            MainActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private final View.OnClickListener e = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainActivity.this.a(p.MULTI_LOCKED.ordinal());
        }
    };
    private final View.OnClickListener f = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, OptionsActivity.class);
            MainActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private final View.OnClickListener g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.9
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainActivity.this.a(p.MULTI_SOON.ordinal());
        }
    };
    private final View.OnClickListener h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.10
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainActivity.this.a(p.EMERGENCY_LOCK.ordinal());
        }
    };
    private final View.OnClickListener i = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.11
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (com.topfreegames.bikerace.worldcup.o.a().r()) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WorldCupShopActivity.class);
                intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MainActivity.class);
                MainActivity.this.a(intent, 2130968588, 2130968583);
            }
        }
    };
    private Timer j;

    static /* synthetic */ int[] g() {
        int[] iArr = k;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.i.b.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.i.b.ENGLISH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.i.b.JAPANESE.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.i.b.KOREAN.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            k = iArr;
        }
        return iArr;
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            Intent intent = getIntent();
            if (intent.getBooleanExtra("com.topfreegames.bikerace.OpenMulti", false)) {
                ag.a(this);
            }
            if (intent.getBooleanExtra("com.topfreegames.bikerace.RetentionNotification", false)) {
                ((BikeRaceApplication) getApplication()).d().p();
            }
            if (intent.getBooleanExtra("com.topfreegames.bikeraces.WorldCupShop", false)) {
                ah.a(this);
            }
            j();
            if (!isTaskRoot()) {
                String action = intent.getAction();
                if (intent.hasCategory("android.intent.category.LAUNCHER") && action != null && action.equals("android.intent.action.MAIN")) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        System.out.println("Main Activity is not the root.  Finishing Main Activity instead of launching.");
                    }
                    finish();
                    return;
                }
            }
            if (!i()) {
                setContentView(2130903099);
                ((BikeRaceApplication) getApplication()).b().e();
                bb bbVarA = ((BikeRaceApplication) getApplication()).a(false);
                View viewFindViewById = findViewById(2131296730);
                if (bbVarA.U()) {
                    viewFindViewById.setOnClickListener(this.h);
                } else {
                    viewFindViewById.setOnClickListener(this.f);
                }
                View viewFindViewById2 = findViewById(2131296727);
                View viewFindViewById3 = findViewById(2131296725);
                View viewFindViewById4 = findViewById(2131296726);
                View viewFindViewById5 = findViewById(2131296728);
                if (com.topfreegames.bikerace.worldcup.o.a().r()) {
                    viewFindViewById5.setOnClickListener(this.i);
                } else {
                    viewFindViewById5.setVisibility(8);
                }
                if (com.topfreegames.bikerace.ap.f()) {
                    viewFindViewById2.setVisibility(8);
                    viewFindViewById3.setVisibility(8);
                    viewFindViewById4.setVisibility(8);
                } else if (com.topfreegames.bikerace.ap.e()) {
                    viewFindViewById2.setVisibility(0);
                    if (bbVarA.U()) {
                        viewFindViewById2.setOnClickListener(this.h);
                    } else {
                        viewFindViewById2.setOnClickListener(this.c);
                    }
                    viewFindViewById3.setVisibility(8);
                    viewFindViewById4.setVisibility(8);
                } else {
                    viewFindViewById2.setVisibility(0);
                    if (bbVarA.U()) {
                        viewFindViewById2.setOnClickListener(this.h);
                    } else {
                        viewFindViewById2.setOnClickListener(this.c);
                    }
                    viewFindViewById3.setVisibility(0);
                    if (((BikeRaceApplication) getApplication()).a().a()) {
                        if (bbVarA.U()) {
                            viewFindViewById3.setOnClickListener(this.h);
                        } else {
                            viewFindViewById3.setOnClickListener(this.e);
                        }
                    } else {
                        viewFindViewById4.setVisibility(8);
                        if (bbVarA.U()) {
                            viewFindViewById3.setOnClickListener(this.h);
                        } else {
                            viewFindViewById3.setOnClickListener(this.d);
                        }
                    }
                    if (intent.getBooleanExtra("com.topfreegames.bikerace.MultiplayerLocked", false)) {
                        a(p.MULTI_LOCKED.ordinal());
                    } else if (intent.getBooleanExtra("com.topfreegames.bikerace.UserLevelsLocked", false)) {
                        a(p.USER_LEVELS_LOCKED.ordinal());
                    }
                }
                if (bbVarA.U()) {
                    a(p.EMERGENCY_LOCK.ordinal());
                }
                com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplication()).a();
                if (zVarA.u()) {
                    zVarA.a(false);
                    ((BikeRaceApplication) getApplication()).d().k();
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
        }
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        if (intent.getBooleanExtra("com.topfreegames.bikerace.OpenMulti", false)) {
            ag.a(this);
        }
        if (intent.getBooleanExtra("com.topfreegames.bikerace.RetentionNotification", false)) {
            ((BikeRaceApplication) getApplication()).d().p();
        }
        if (intent.getBooleanExtra("com.topfreegames.bikeraces.WorldCupShop", false)) {
            ah.a(this);
        }
        j();
        if (!isTaskRoot()) {
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && action != null && action.equals("android.intent.action.MAIN")) {
                if (com.topfreegames.bikerace.ap.d()) {
                    System.out.println("Main Activity is not the root.  Finishing Main Activity instead of launching.");
                }
                finish();
            }
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
            if (!ag.a(this, MainActivity.class) && !ah.a(this, MainActivity.class)) {
                if (hasWindowFocus()) {
                    ((BikeRaceApplication) getApplication()).b().e();
                }
                h();
                com.topfreegames.bikerace.g.a.g().k();
                k();
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
    public void onPause() {
        super.onPause();
        if (this.j != null) {
            this.j.cancel();
            this.j = null;
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
    public void onStop() {
        try {
            super.onStop();
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
    public void onDestroy() {
        try {
            super.onDestroy();
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
    protected void f() {
        super.f();
        com.topfreegames.bikerace.k.a.a().a((com.topfreegames.bikerace.k.e) null);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void e() {
        super.e();
        System.runFinalizersOnExit(true);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296722);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.START;
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        a(p.QUIT.ordinal());
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(final int i) {
        com.topfreegames.bikerace.e.n nVar;
        String string;
        q qVar = null;
        if (i == p.MULTI_SOON.ordinal()) {
            nVar = new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099762), getString(2131099659), null);
        } else if (i == p.MULTI_LOCKED.ordinal()) {
            int i2 = bn.a().f1162a;
            int iP = ((BikeRaceApplication) getApplication()).a().p();
            String string2 = getString(2131099725);
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(i2);
            objArr[1] = Integer.valueOf(iP);
            objArr[2] = iP > 1 ? "s" : "";
            nVar = new com.topfreegames.bikerace.e.n(this, String.format(string2, objArr), getString(2131099659), null);
        } else if (i == p.QUIT.ordinal()) {
            nVar = new com.topfreegames.bikerace.e.n(this, getString(2131099676), getString(2131099661), getString(2131099662), new q(this, qVar), null);
        } else if (i == p.EMERGENCY_LOCK.ordinal()) {
            nVar = new com.topfreegames.bikerace.e.n(this, ((BikeRaceApplication) getApplication()).a(false).x(), getString(2131099659), null);
        } else if (i == p.USER_LEVELS_LOCKED.ordinal()) {
            int iP2 = ((BikeRaceApplication) getApplicationContext()).a().p();
            int i3 = bn.a(999).f1162a;
            boolean z = com.topfreegames.bikerace.i.a.a() == com.topfreegames.bikerace.i.b.ENGLISH;
            Object[] objArr2 = new Object[3];
            objArr2[0] = Integer.valueOf(i3);
            objArr2[1] = Integer.valueOf(iP2);
            objArr2[2] = (iP2 <= 1 || !z) ? "" : "s";
            nVar = new com.topfreegames.bikerace.e.n(this, getString(2131099677, objArr2), getString(2131099659), null);
        } else if (i == p.CHOOSE_LANGUAGE.ordinal()) {
            final com.topfreegames.bikerace.i.b bVarB = com.topfreegames.bikerace.i.a.b();
            switch (g()[bVarB.ordinal()]) {
                case 2:
                    string = getString(2131099657);
                    break;
                case 3:
                    string = getString(2131099656);
                    break;
                default:
                    string = null;
                    break;
            }
            final com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplicationContext()).a();
            nVar = new com.topfreegames.bikerace.e.n(this, getString(2131099658), string, getString(2131099655), new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.MainActivity.12
                @Override // com.topfreegames.bikerace.e.o
                public void a() {
                    zVarA.B();
                    zVarA.C();
                    com.topfreegames.bikerace.i.a.a(bVarB, MainActivity.this);
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MainActivity.12.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.finish();
                            MainActivity.this.startActivity(MainActivity.this.getIntent());
                        }
                    });
                }
            }, new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.MainActivity.13
                @Override // com.topfreegames.bikerace.e.o
                public void a() {
                    zVarA.C();
                }
            });
        } else {
            nVar = null;
        }
        if (nVar != null) {
            nVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.topfreegames.bikerace.activities.MainActivity.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    MainActivity.this.removeDialog(i);
                }
            });
        }
        return nVar;
    }

    private void h() {
        if (b) {
            Thread thread = new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MainActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) MainActivity.this.getApplication();
                    bikeRaceApplication.a(true).aD();
                    bikeRaceApplication.a();
                    bikeRaceApplication.c();
                    bikeRaceApplication.a(MainActivity.this.getApplicationContext(), null);
                    bikeRaceApplication.b().a(MainActivity.this.getApplicationContext());
                    com.topfreegames.bikerace.a.f.a(MainActivity.this.getApplicationContext());
                }
            });
            thread.setPriority(4);
            thread.start();
            b = false;
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MainActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    private boolean i() {
        try {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplicationContext();
            final com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
            bb bbVarA = bikeRaceApplication.a(false);
            com.topfreegames.bikerace.i.b bVarB = com.topfreegames.bikerace.i.a.b();
            if (bbVarA.aE() && !bVarB.equals(com.topfreegames.bikerace.i.b.ENGLISH) && !zVarA.s()) {
                if (zVarA.p() <= 0) {
                    com.topfreegames.bikerace.i.a.a(bVarB, this);
                    zVarA.B();
                    finish();
                    startActivity(getIntent());
                    return true;
                }
                if (!zVarA.t()) {
                    new Timer().schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.MainActivity.4
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            MainActivity.this.a(p.CHOOSE_LANGUAGE.ordinal());
                            zVarA.C();
                        }
                    }, 500L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void j() {
        com.topfreegames.bikerace.localnotification.retention.a.b((BikeRaceApplication) getApplication());
        ((NotificationManager) getSystemService("notification")).cancelAll();
    }

    private void k() {
        if (this.j == null) {
            this.j = new Timer();
            this.j.schedule(new TimerTask() { // from class: com.topfreegames.bikerace.activities.MainActivity.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    try {
                        MainActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MainActivity.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    long jS = com.topfreegames.bikerace.worldcup.o.a().s();
                                    if (com.topfreegames.bikerace.worldcup.o.a().r()) {
                                        TextView textView = (TextView) MainActivity.this.findViewById(2131296729);
                                        if (textView != null) {
                                            textView.setText(com.topfreegames.bikerace.worldcup.l.a(jS));
                                        }
                                    } else {
                                        ((TextView) MainActivity.this.findViewById(2131296729)).setText(MainActivity.this.getString(2131100086));
                                        if (MainActivity.this.j != null) {
                                            MainActivity.this.j.cancel();
                                            MainActivity.this.j = null;
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
            }, 0L, 1000L);
        }
    }
}
