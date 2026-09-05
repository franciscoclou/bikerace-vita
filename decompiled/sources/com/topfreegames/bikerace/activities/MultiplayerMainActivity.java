package com.topfreegames.bikerace.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.topfreegames.bikerace.bm;
import com.topfreegames.bikerace.views.ProgressMessageView;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerMainActivity extends e implements com.topfreegames.bikerace.a.g, com.topfreegames.bikerace.a.i, com.topfreegames.bikerace.multiplayer.f, com.topfreegames.bikerace.multiplayer.g {
    private com.topfreegames.bikerace.multiplayer.b A;
    private com.topfreegames.bikerace.multiplayer.o s;
    private int z;
    private com.topfreegames.bikerace.multiplayer.k b = new com.topfreegames.bikerace.multiplayer.k() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.1
        @Override // com.topfreegames.bikerace.multiplayer.k
        public void a(final String str) {
            MultiplayerMainActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (str == null || str.equals("")) {
                        MultiplayerMainActivity.this.a(s.LINK_UNAVAILABLE.ordinal());
                    } else {
                        ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).c().f(str);
                        MultiplayerMainActivity.this.a(s.FIND.ordinal());
                    }
                }
            });
        }
    };
    private View.OnClickListener c = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.12
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultiplayerMainActivity.this.a(s.NEW_MULTIPLAYER_GAME.ordinal());
        }
    };
    private View.OnClickListener d = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.23
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (!((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).e()) {
                    MultiplayerMainActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.23.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MultiplayerMainActivity.this.a(s.RANDOM_UNAVAILABLE.ordinal());
                        }
                    });
                } else {
                    MultiplayerMainActivity.this.l();
                    MultiplayerMainActivity.this.s.b();
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "RandomButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "RandomButtonListener", e2);
            }
        }
    };
    private View.OnClickListener e = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.28
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (MultiplayerMainActivity.this.s.a()) {
                    Intent intent = new Intent();
                    intent.setClass(MultiplayerMainActivity.this, FacebookUsersListActivity.class);
                    MultiplayerMainActivity.this.a(intent, 2130968587, 2130968583);
                } else {
                    MultiplayerMainActivity.this.k();
                    MultiplayerMainActivity.this.q();
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e2);
            }
        }
    };
    private View.OnClickListener f = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.29
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) MultiplayerMainActivity.this.getApplication();
                com.topfreegames.bikerace.multiplayer.o oVarC = bikeRaceApplication.c();
                if (bikeRaceApplication.e()) {
                    com.topfreegames.bikerace.multiplayer.j.a(MultiplayerMainActivity.this.getApplicationContext(), oVarC.g(), oVarC.h(), MultiplayerMainActivity.this.b);
                } else {
                    MultiplayerMainActivity.this.a(s.FIND.ordinal());
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e2);
            }
        }
    };
    private View.OnClickListener g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.30
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (MultiplayerMainActivity.this.s.a()) {
                    Intent intent = new Intent();
                    intent.setClass(MultiplayerMainActivity.this, MultiplayerRankingActivity.class);
                    MultiplayerMainActivity.this.a(intent, 2130968587, 2130968583);
                } else {
                    MultiplayerMainActivity.this.k();
                    MultiplayerMainActivity.this.q();
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "RankingButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "RankingButtonListener", e2);
            }
        }
    };
    private View.OnClickListener h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.31
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MultiplayerMainActivity.this, MainActivity.class);
            MultiplayerMainActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener i = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.32
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MultiplayerMainActivity.this, ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", -1);
            MultiplayerMainActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private com.topfreegames.bikerace.views.o j = new com.topfreegames.bikerace.views.o() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.33
        @Override // com.topfreegames.bikerace.views.o
        public void a(com.topfreegames.bikerace.views.n nVar) {
            try {
                com.topfreegames.bikerace.multiplayer.l lVar = nVar.getMultiplayerDataReference().get();
                if (lVar != null) {
                    if (lVar.w()) {
                        MultiplayerMainActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.33.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MultiplayerMainActivity.this.a(s.WORLD_NOT_AVAILABLE.ordinal());
                            }
                        });
                    } else {
                        MultiplayerMainActivity.this.s.b(MultiplayerMainActivity.this, lVar.f());
                        MultiplayerMainActivity.this.r();
                        MultiplayerMainActivity.this.a(lVar);
                    }
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "PlayEventListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "PlayEventListener", e2);
            }
        }
    };
    private com.topfreegames.bikerace.views.o k = new com.topfreegames.bikerace.views.o() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.2
        @Override // com.topfreegames.bikerace.views.o
        public void a(final com.topfreegames.bikerace.views.n nVar) {
            try {
                com.topfreegames.bikerace.multiplayer.l lVar = nVar.getMultiplayerDataReference().get();
                if (lVar != null) {
                    MultiplayerMainActivity.this.s.a(MultiplayerMainActivity.this, MultiplayerMainActivity.this.s.h(), lVar.f(), lVar.g());
                    MultiplayerMainActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            nVar.a();
                            MultiplayerMainActivity.this.u.notifyDataSetChanged();
                        }
                    });
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "PokeEventListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "PokeEventListener", e2);
            }
        }
    };
    private com.topfreegames.bikerace.views.o l = new AnonymousClass3();
    private com.topfreegames.bikerace.e.l m = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.4
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            Resources resources = MultiplayerMainActivity.this.getResources();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.SUBJECT", resources.getString(2131099883));
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(String.format(resources.getString(2131099884), str)));
            try {
                MultiplayerMainActivity.this.startActivityForResult(intent, v.EMAIL.ordinal());
            } catch (ActivityNotFoundException e) {
                MultiplayerMainActivity.this.a(s.NO_EMAIL_CLIENT.ordinal());
            }
        }
    };
    private com.topfreegames.bikerace.e.l n = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.5
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            if (((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).f()) {
                Resources resources = MultiplayerMainActivity.this.getResources();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.putExtra("sms_body", String.format(resources.getString(2131099886), str));
                intent.setType("vnd.android-dir/mms-sms");
                try {
                    if (com.topfreegames.bikerace.a.f.a(MultiplayerMainActivity.this.getApplicationContext()).b("AchievCreateGameSMS").size() > 0) {
                        ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).a().z();
                    }
                    MultiplayerMainActivity.this.startActivityForResult(intent, v.SMS.ordinal());
                    return;
                } catch (ActivityNotFoundException e) {
                    MultiplayerMainActivity.this.a(s.NO_SMS_CLIENT.ordinal());
                    return;
                }
            }
            MultiplayerMainActivity.this.a(s.SMS_UNAVAILABLE.ordinal());
        }
    };
    private com.topfreegames.bikerace.e.l o = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.6
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).c();
            if (oVarC.a()) {
                com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
                Resources resources = MultiplayerMainActivity.this.getResources();
                String string = resources.getString(2131099888);
                aVarB.a(MultiplayerMainActivity.this, resources.getString(2131099889), oVarC.g(), resources.getString(2131099890), str, null, string, new com.topfreegames.e.a.g() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.6.1
                    @Override // com.topfreegames.e.a.g
                    public void a(boolean z) {
                    }

                    @Override // com.topfreegames.e.a.g
                    public void g() {
                    }
                });
                return;
            }
            MultiplayerMainActivity.this.a(s.SHARE_UNAVAILABLE.ordinal());
        }
    };
    private com.topfreegames.bikerace.e.m p = new com.topfreegames.bikerace.e.m() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.7
        /* JADX WARN: Code duplicated, block: B:17:0x005a A[PHI: r6
          0x005a: PHI (r6v1 java.lang.String) = (r6v0 java.lang.String), (r6v3 java.lang.String) binds: [B:3:0x0008, B:10:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // com.topfreegames.bikerace.e.m
        public boolean a(String str) {
            boolean z;
            if (str.length() > 11) {
                if (!str.substring(0, 4).equalsIgnoreCase("http") && !str.substring(0, 11).equalsIgnoreCase("bikerace://")) {
                    str = String.format("http://%1$s", str);
                }
                z = str.trim().length() > 0;
            }
            if (z) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                try {
                    MultiplayerMainActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    MultiplayerMainActivity.this.a(s.LINK_OPEN_ERROR.ordinal());
                }
            }
            return z;
        }
    };
    private com.topfreegames.bikerace.e.l q = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.8
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            if (Build.VERSION.SDK_INT < 11) {
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplicationContext()).a(str);
            } else {
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplicationContext()).b(str);
            }
        }
    };
    private com.topfreegames.bikerace.e.r r = new com.topfreegames.bikerace.e.r() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.9
        @Override // com.topfreegames.bikerace.e.r
        public void a(String str) {
            ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).c().d(str);
        }
    };
    private boolean t = false;
    private t u = null;
    private TextView v = null;
    private View w = null;
    private Object x = null;
    private Bitmap y = null;
    private com.topfreegames.bikerace.a.c B = null;

    /* JADX INFO: renamed from: com.topfreegames.bikerace.activities.MultiplayerMainActivity$3, reason: invalid class name */
    class AnonymousClass3 implements com.topfreegames.bikerace.views.o {
        AnonymousClass3() {
        }

        @Override // com.topfreegames.bikerace.views.o
        public void a(com.topfreegames.bikerace.views.n nVar) {
            try {
                com.topfreegames.bikerace.multiplayer.l lVar = nVar.getMultiplayerDataReference().get();
                if (lVar != null) {
                    MultiplayerMainActivity.this.runOnUiThread(new AnonymousClass1(nVar, lVar));
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "DeleteEventListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "DeleteEventListener", e2);
            }
        }

        /* JADX INFO: renamed from: com.topfreegames.bikerace.activities.MultiplayerMainActivity$3$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            private final /* synthetic */ com.topfreegames.bikerace.views.n b;
            private final /* synthetic */ com.topfreegames.bikerace.multiplayer.l c;

            AnonymousClass1(com.topfreegames.bikerace.views.n nVar, com.topfreegames.bikerace.multiplayer.l lVar) {
                this.b = nVar;
                this.c = lVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                final com.topfreegames.bikerace.multiplayer.l lVar = this.c;
                new com.topfreegames.bikerace.e.n(this.b.getContext(), MultiplayerMainActivity.this.getString(2131099721, new Object[]{this.b.getMultiplayerDataReference().get().g()}), MultiplayerMainActivity.this.getString(2131099661), MultiplayerMainActivity.this.getString(2131099662), new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.3.1.1
                    @Override // com.topfreegames.bikerace.e.o
                    public void a() {
                        MultiplayerMainActivity multiplayerMainActivity = MultiplayerMainActivity.this;
                        final com.topfreegames.bikerace.multiplayer.l lVar2 = lVar;
                        multiplayerMainActivity.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.3.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MultiplayerMainActivity.this.u.remove(lVar2);
                                MultiplayerMainActivity.this.u.notifyDataSetChanged();
                            }
                        });
                        MultiplayerMainActivity.this.s.e(lVar.b());
                    }
                }, null).show();
            }
        }
    }

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (com.topfreegames.bikerace.ap.e()) {
                finish();
                return;
            }
            if (((BikeRaceApplication) getApplication()).a().a()) {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                intent.putExtra("com.topfreegames.bikerace.MultiplayerLocked", true);
                a(intent, 2130968588, 2130968583);
                finish();
                return;
            }
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.x = extras.get("com.topfreegames.bikerace.CallingActivity");
            }
            boolean z = (this.x == PlayActivity.class) | (this.x == MultiplayerRankingActivity.class);
            com.topfreegames.bikerace.g.a.g().a((this.x == FacebookUsersListActivity.class) | z);
            setContentView(2130903077);
            this.s = ((BikeRaceApplication) getApplication()).c();
            this.s.b((com.topfreegames.bikerace.multiplayer.g) this);
            this.s.b((com.topfreegames.bikerace.multiplayer.f) this);
            this.z = this.s.e();
            this.A = this.s.r();
            this.t = false;
            findViewById(2131296491).setOnClickListener(this.c);
            findViewById(2131296497).setOnClickListener(this.g);
            findViewById(2131296499).setOnClickListener(this.i);
            findViewById(2131296493).setOnClickListener(this.e);
            this.u = new t(this, this, 0);
            this.v = (TextView) getLayoutInflater().inflate(2130903078, (ViewGroup) null);
            a(this.v);
            this.w = findViewById(2131296502);
            this.w.findViewById(2131296503).setOnClickListener(this.c);
            a(this.w);
            t();
            ListView listView = (ListView) findViewById(2131296501);
            listView.addFooterView(this.v, null, false);
            listView.setClickable(false);
            listView.setFocusable(false);
            listView.setDividerHeight(0);
            listView.setOnScrollListener(new u(this, null));
            listView.setAdapter((ListAdapter) this.u);
            com.topfreegames.bikerace.push.e.a(this);
            com.topfreegames.bikerace.localnotification.limitedtimebike.c.a(this);
            com.topfreegames.bikerace.g.a aVarG = com.topfreegames.bikerace.g.a.g();
            aVarG.i();
            aVarG.b(true);
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

    /* JADX WARN: Type inference failed for: r0v28, types: [com.topfreegames.bikerace.activities.MultiplayerMainActivity$10] */
    @Override // com.topfreegames.bikerace.activities.e, com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, MultiplayerMainActivity.class) && !ah.a(this, MultiplayerMainActivity.class)) {
                ((BikeRaceApplication) getApplication()).d().g();
                com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(getApplicationContext());
                fVarA.a((com.topfreegames.bikerace.a.i) this);
                fVarA.a((com.topfreegames.bikerace.a.g) this);
                v();
                if (hasWindowFocus()) {
                    ((BikeRaceApplication) getApplication()).b().e();
                }
                j();
                if (this.s.a()) {
                    o();
                }
                this.s.a(true);
                if (h()) {
                    if (!this.s.a() && !this.t) {
                        k();
                        new Thread() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.10
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                MultiplayerMainActivity.this.t = true;
                                MultiplayerMainActivity.this.s.c(MultiplayerMainActivity.this);
                            }
                        }.start();
                    } else if (this.t) {
                        k();
                    } else {
                        g();
                    }
                } else {
                    g();
                }
                i();
                u();
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

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onPause() {
        try {
            super.onPause();
            com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(getApplicationContext());
            fVarA.b(this);
            fVarA.b();
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    com.topfreegames.e.a.a.b().a(this);
                }
            }).start();
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

    @Override // com.topfreegames.bikerace.activities.c
    protected void e() {
        try {
            super.e();
            this.x = null;
            if (getIntent().getExtras() != null) {
                getIntent().getExtras().putSerializable("com.topfreegames.bikerace.CallingActivity", null);
            }
            com.topfreegames.bikerace.g.a.g().b(false);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onLeavingApp", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onLeavingApp", e2);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
            if (d() && z) {
                ((BikeRaceApplication) getApplication()).b().e();
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onWindowFocuesChanged", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onWindowFocusChanged", e2);
        }
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.13
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    MultiplayerMainActivity.this.p();
                }
                MultiplayerMainActivity.this.c(z);
            }
        });
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void b(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.14
            @Override // java.lang.Runnable
            public void run() {
                MultiplayerMainActivity.this.n();
                int size = MultiplayerMainActivity.this.s.m().size();
                if (z && size > MultiplayerMainActivity.this.z) {
                    MultiplayerMainActivity.this.a(s.MAX_GAMES_REACHED.ordinal());
                }
                if (MultiplayerMainActivity.this.u.getCount() <= 0) {
                    MultiplayerMainActivity.this.s();
                } else {
                    MultiplayerMainActivity.this.t();
                }
            }
        });
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(final List<com.topfreegames.bikerace.multiplayer.l> list, final int i, final int i2, final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.15
            @Override // java.lang.Runnable
            public void run() {
                MultiplayerMainActivity.this.a((List<com.topfreegames.bikerace.multiplayer.l>) list, false);
                MultiplayerMainActivity.this.a(i, i2, z);
            }
        });
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(final List<String> list) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.16
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    MultiplayerMainActivity.this.u.b((String) it.next());
                }
                MultiplayerMainActivity.this.u.notifyDataSetChanged();
            }
        });
    }

    @Override // com.topfreegames.bikerace.multiplayer.g
    public void a(boolean z, final boolean z2) {
        this.t = false;
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.17
            @Override // java.lang.Runnable
            public void run() {
                if (z2) {
                    MultiplayerMainActivity.this.p();
                    MultiplayerMainActivity.this.c(false);
                }
                MultiplayerMainActivity.this.j();
                MultiplayerMainActivity.this.o();
                MultiplayerMainActivity.this.u();
            }
        });
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

    @Override // com.topfreegames.bikerace.activities.e, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStop() {
        try {
            super.onStop();
            this.A.a();
            if (this.y != null) {
                this.y.recycle();
                this.y = null;
                System.gc();
            }
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.18
                @Override // java.lang.Runnable
                public void run() {
                    com.topfreegames.e.a.a.b().a(this);
                }
            }).start();
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

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
            if (this.s != null) {
                this.s.a((com.topfreegames.bikerace.multiplayer.g) this);
                this.s.a((com.topfreegames.bikerace.multiplayer.f) this);
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

    @Override // com.topfreegames.bikerace.activities.d, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            super.onActivityResult(i, i2, intent);
            if (i == v.EMAIL.ordinal() && com.topfreegames.bikerace.a.f.a(getApplicationContext()).b("AchievCreateGameEmail").size() > 0) {
                ((BikeRaceApplication) getApplication()).a().y();
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onActivityResult", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onActivityResult", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296490);
    }

    private void g() {
        boolean z = false;
        try {
            if (this.x == null || (this.x != null && this.x == MainActivity.class)) {
                z = true;
            }
            u();
            this.s.c(z);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "requestUpdateUI", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "requestUpdateUI", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<com.topfreegames.bikerace.multiplayer.l> list, boolean z) {
        if (z) {
            try {
                this.u.clear();
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateList", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateList", e2);
                return;
            }
        }
        synchronized (list) {
            Iterator<com.topfreegames.bikerace.multiplayer.l> it = list.iterator();
            while (it.hasNext()) {
                this.u.add(it.next());
            }
            this.u.a();
            if (this.u.getCount() > this.z) {
                while (this.u.getCount() > this.z) {
                    this.u.remove(this.u.getItem(this.u.getCount() - 1));
                }
            }
        }
        if (this.u.getCount() > 0) {
            t();
        } else {
            s();
        }
        this.u.notifyDataSetChanged();
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.h.onClick(null);
    }

    @Override // com.topfreegames.bikerace.multiplayer.f
    public void b(final com.topfreegames.bikerace.multiplayer.l lVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.19
            @Override // java.lang.Runnable
            public void run() {
                if (lVar != null) {
                    MultiplayerMainActivity.this.s.b(MultiplayerMainActivity.this, lVar.f());
                    MultiplayerMainActivity.this.a(lVar);
                }
                MultiplayerMainActivity.this.n();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [com.topfreegames.bikerace.activities.MultiplayerMainActivity$20] */
    private boolean h() {
        boolean z = true;
        try {
            Uri data = getIntent().getData();
            if (data == null || !"bikerace".equals(data.getScheme()) || !"newgame".equals(data.getHost())) {
                return true;
            }
            m();
            final String queryParameter = data.getQueryParameter("id");
            final String queryParameter2 = data.getQueryParameter("name");
            z = false;
            new Thread() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.20
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    MultiplayerMainActivity.this.s.a(MultiplayerMainActivity.this, queryParameter, queryParameter2);
                }
            }.start();
            return false;
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "checkIntentForNewGameFromLink", e);
            throw e;
        } catch (Exception e2) {
            boolean z2 = z;
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "checkIntentForNewGameFromLink", e2);
            return z2;
        }
    }

    private boolean i() {
        boolean booleanExtra = getIntent().getBooleanExtra("com.topfreegames.bikerace.CreateNewRandomGame", false);
        if (booleanExtra) {
            this.d.onClick(null);
        }
        return !booleanExtra;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.MULTIPLAYER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        try {
            TextView textView = (TextView) findViewById(2131296495);
            TextView textView2 = (TextView) findViewById(2131296496);
            if (textView != null && textView2 != null) {
                if (this.s.a()) {
                    textView.setText(getString(2131099709));
                    textView2.setText(getString(2131099710));
                } else {
                    textView.setText(getString(2131099711));
                    textView2.setText(getString(2131099712));
                }
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateFacebookButton", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateFacebookButton", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296504);
        if (progressMessageView != null) {
            if (z) {
                progressMessageView.setMessage(getString(2131099726));
            } else {
                progressMessageView.setMessage(getString(2131099727));
            }
            progressMessageView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, boolean z) {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296504);
        if (progressMessageView != null) {
            if (z) {
                progressMessageView.setMessage(getString(2131099733, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            } else {
                progressMessageView.setMessage(getString(2131099734, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            progressMessageView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296504);
        if (progressMessageView != null) {
            progressMessageView.setMessage(getString(2131099728));
            progressMessageView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296504);
        if (progressMessageView != null) {
            progressMessageView.setMessage(getString(2131099730));
            progressMessageView.setVisibility(0);
        }
    }

    private void m() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296504);
        if (progressMessageView != null) {
            progressMessageView.setMessage(getString(2131099731));
            progressMessageView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296504);
        if (progressMessageView != null) {
            progressMessageView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        String string;
        View.OnClickListener onClickListener = null;
        if (this.s.a()) {
            string = getString(2131099723);
        } else {
            string = getString(2131099724);
            onClickListener = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.21
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MultiplayerMainActivity.this.a(s.GUEST_NAME_INPUT.ordinal());
                }
            };
        }
        if (this.v != null) {
            this.v.setText(getString(2131099722, new Object[]{this.s.h(), string}));
            this.v.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.u.clear();
        this.u.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.topfreegames.bikerace.activities.MultiplayerMainActivity$22] */
    public void q() {
        new Thread() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.22
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    MultiplayerMainActivity.this.t = true;
                    MultiplayerMainActivity.this.s.a((Activity) MultiplayerMainActivity.this);
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "peformFacebookLogin", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "peformFacebookLogin", e2);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.24
            @Override // java.lang.Runnable
            public void run() {
                try {
                    View viewFindViewById = MultiplayerMainActivity.this.findViewById(2131296505);
                    if (viewFindViewById != null) {
                        Display defaultDisplay = MultiplayerMainActivity.this.getWindowManager().getDefaultDisplay();
                        MultiplayerMainActivity.this.y = com.topfreegames.engine.b.a.a(MultiplayerMainActivity.this.getResources(), 2130837762, defaultDisplay.getWidth(), defaultDisplay.getHeight());
                        viewFindViewById.setBackgroundDrawable(new BitmapDrawable(MultiplayerMainActivity.this.y));
                        viewFindViewById.setVisibility(0);
                        MultiplayerMainActivity.this.b().invalidate();
                    }
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "showLoadingScreen", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) MultiplayerMainActivity.this.getApplication()).d().a(getClass().getName(), "showLoadingScreen", e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.w.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        this.w.setVisibility(8);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(final int i) {
        Dialog bVar;
        try {
            if (i == s.NEW_MULTIPLAYER_GAME.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.ab(this, this.e, this.d, this.f);
            } else if (i == s.MAX_GAMES_REACHED.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099739), getString(2131099659), null);
            } else if (i == s.FIND.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.k(this, ((BikeRaceApplication) getApplication()).c().s(), this.q, this.m, this.n, this.o, this.p);
            } else if (i == s.LINK_UNAVAILABLE.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099741), getString(2131099659), null);
            } else if (i == s.LINK_OPEN_ERROR.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099748), getString(2131099659), null);
            } else if (i == s.SMS_UNAVAILABLE.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099742), getString(2131099659), null);
            } else if (i == s.SHARE_UNAVAILABLE.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099743), getString(2131099659), null);
            } else if (i == s.NO_EMAIL_CLIENT.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099885), getString(2131099659), null);
            } else if (i == s.NO_SMS_CLIENT.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099887), getString(2131099659), null);
            } else if (i == s.WORLD_NOT_AVAILABLE.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099745), getString(2131099659), null);
            } else if (i == s.RANDOM_UNAVAILABLE.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099746), getString(2131099659), null);
            } else if (i == s.TIMESTAMP_ERROR.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.n(this, getString(2131099747), getString(2131099659), null);
            } else if (i == s.GUEST_NAME_INPUT.ordinal()) {
                bVar = new com.topfreegames.bikerace.e.q(this, getString(2131099744), getString(2131099659), getString(2131099660), this.r, null);
            } else if (i != s.BIKE_UNLOCK.ordinal() || this.B == null) {
                bVar = null;
            } else {
                bVar = new com.topfreegames.bikerace.e.b(this, this.B, new r(this, null));
                this.B = null;
            }
            if (bVar != null) {
                bVar.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.25
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        MultiplayerMainActivity.this.removeDialog(i);
                    }
                });
                return bVar;
            }
            return bVar;
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onCreateDialog", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onCreateDialog", e2);
            return null;
        }
    }

    @Override // com.topfreegames.bikerace.a.i
    public void a(final com.topfreegames.bikerace.a.a aVar, final com.topfreegames.bikerace.a.j jVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.26
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.views.a.a(MultiplayerMainActivity.this.b(), aVar, jVar);
            }
        });
    }

    @Override // com.topfreegames.bikerace.a.g
    public void a(final com.topfreegames.bikerace.a.c cVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerMainActivity.27
            @Override // java.lang.Runnable
            public void run() {
                MultiplayerMainActivity.this.B = cVar;
                MultiplayerMainActivity.this.a(s.BIKE_UNLOCK.ordinal());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if ((this.x == null || (this.x != null && this.x == MainActivity.class)) && bm.a()) {
            a(s.TIMESTAMP_ERROR.ordinal());
            bm.b();
        }
    }

    private void v() {
        com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(getApplicationContext());
        fVarA.a("AchievConsecutiveWins", ((BikeRaceApplication) getApplicationContext()).a().j());
        fVarA.a("AchievMultiplayerWins", this.s.k());
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MultiplayerMainActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected void f() {
        super.f();
        com.topfreegames.bikerace.k.a.a().a((com.topfreegames.bikerace.k.e) null);
    }
}
