package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.topfreegames.bikerace.views.MultiplayerRankingHeaderView;
import com.topfreegames.bikerace.views.ProgressMessageView;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerRankingActivity extends e implements com.topfreegames.e.a.l {
    private com.topfreegames.bikerace.multiplayer.k b = new com.topfreegames.bikerace.multiplayer.k() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.1
        @Override // com.topfreegames.bikerace.multiplayer.k
        public void a(final String str) {
            MultiplayerRankingActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (str == null || str.equals("")) {
                        MultiplayerRankingActivity.this.a(x.LINK_UNAVAILABLE.ordinal());
                    } else {
                        ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).c().f(str);
                        MultiplayerRankingActivity.this.a(x.FIND.ordinal());
                    }
                }
            });
        }
    };
    private View.OnClickListener c = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.12
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultiplayerRankingActivity.this.a(x.NEW_MULTIPLAYER_GAME.ordinal());
        }
    };
    private View.OnClickListener d = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.13
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MultiplayerRankingActivity.this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", MultiplayerRankingActivity.class);
            MultiplayerRankingActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener e = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.14
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MultiplayerRankingActivity.this, ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MultiplayerRankingActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", -1);
            MultiplayerRankingActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private View.OnClickListener f = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.15
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MultiplayerRankingActivity.this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", MultiplayerRankingActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CreateNewRandomGame", true);
            MultiplayerRankingActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.16
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MultiplayerRankingActivity.this, FacebookUsersListActivity.class);
            MultiplayerRankingActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private View.OnClickListener h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.17
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) MultiplayerRankingActivity.this.getApplication();
                com.topfreegames.bikerace.multiplayer.o oVarC = bikeRaceApplication.c();
                if (bikeRaceApplication.e()) {
                    com.topfreegames.bikerace.multiplayer.j.a(MultiplayerRankingActivity.this.getApplicationContext(), oVarC.g(), oVarC.h(), MultiplayerRankingActivity.this.b);
                } else {
                    MultiplayerRankingActivity.this.a(x.FIND.ordinal());
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e2);
            }
        }
    };
    private com.topfreegames.bikerace.e.l i = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.18
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            Resources resources = MultiplayerRankingActivity.this.getResources();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.SUBJECT", resources.getString(2131099883));
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(String.format(resources.getString(2131099884), str)));
            try {
                MultiplayerRankingActivity.this.startActivityForResult(intent, aa.EMAIL.ordinal());
            } catch (ActivityNotFoundException e) {
                MultiplayerRankingActivity.this.a(x.NO_EMAIL_CLIENT.ordinal());
            }
        }
    };
    private com.topfreegames.bikerace.e.l j = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.19
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            if (((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).f()) {
                Resources resources = MultiplayerRankingActivity.this.getResources();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.putExtra("sms_body", String.format(resources.getString(2131099886), str));
                intent.setType("vnd.android-dir/mms-sms");
                try {
                    if (com.topfreegames.bikerace.a.f.a(MultiplayerRankingActivity.this.getApplicationContext()).b("AchievCreateGameSMS").size() > 0) {
                        ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).a().z();
                    }
                    MultiplayerRankingActivity.this.startActivityForResult(intent, aa.SMS.ordinal());
                    return;
                } catch (ActivityNotFoundException e) {
                    MultiplayerRankingActivity.this.a(x.NO_SMS_CLIENT.ordinal());
                    return;
                }
            }
            MultiplayerRankingActivity.this.a(x.SMS_UNAVAILABLE.ordinal());
        }
    };
    private com.topfreegames.bikerace.e.l k = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.2
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).c();
            if (oVarC.a()) {
                com.topfreegames.e.a.a aVarB = com.topfreegames.e.a.a.b();
                Resources resources = MultiplayerRankingActivity.this.getResources();
                String string = resources.getString(2131099888);
                aVarB.a(MultiplayerRankingActivity.this, resources.getString(2131099889), oVarC.g(), resources.getString(2131099890), str, null, string, new com.topfreegames.e.a.g() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.2.1
                    @Override // com.topfreegames.e.a.g
                    public void a(boolean z) {
                    }

                    @Override // com.topfreegames.e.a.g
                    public void g() {
                    }
                });
                return;
            }
            MultiplayerRankingActivity.this.a(x.SHARE_UNAVAILABLE.ordinal());
        }
    };
    private com.topfreegames.bikerace.e.m l = new com.topfreegames.bikerace.e.m() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.3
        /* JADX WARN: Code duplicated, block: B:14:0x004d A[PHI: r6
          0x004d: PHI (r6v1 java.lang.String) = (r6v0 java.lang.String), (r6v3 java.lang.String) binds: [B:3:0x0008, B:10:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
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
                MultiplayerRankingActivity.this.startActivity(intent);
            }
            return z;
        }
    };
    private com.topfreegames.bikerace.e.l m = new com.topfreegames.bikerace.e.l() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.4
        @Override // com.topfreegames.bikerace.e.l
        public void a(String str) {
            if (Build.VERSION.SDK_INT < 11) {
                ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplicationContext()).a(str);
            } else {
                ((BikeRaceApplication) MultiplayerRankingActivity.this.getApplicationContext()).b(str);
            }
        }
    };
    private View.OnClickListener n = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultiplayerRankingActivity.this.i();
        }
    };
    private View.OnClickListener o = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultiplayerRankingActivity.this.j();
        }
    };
    private z p = null;
    private View q = null;
    private HashMap<String, com.topfreegames.bikerace.b.b> r = new HashMap<>();
    private boolean s = true;
    private boolean t = true;

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(2130903079);
            findViewById(2131296508).setOnClickListener(this.c);
            findViewById(2131296510).setOnClickListener(this.e);
            this.p = new z(this, this, 0);
            this.q = new MultiplayerRankingHeaderView(this, this.n, this.o);
            a(this.q);
            ListView listView = (ListView) findViewById(2131296512);
            listView.addHeaderView(this.q, null, false);
            listView.setClickable(false);
            listView.setFocusable(false);
            listView.setDividerHeight(0);
            listView.setEmptyView(new RelativeLayout(this));
            listView.setOnScrollListener(new y(this, null));
            listView.setAdapter((ListAdapter) this.p);
            a(listView);
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

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onPause() {
        try {
            super.onPause();
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.7
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

    @Override // com.topfreegames.bikerace.activities.e, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStop() {
        try {
            super.onStop();
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.8
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

    @Override // com.topfreegames.bikerace.activities.e, com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (!ag.a(this, MultiplayerRankingActivity.class) && !ah.a(this, MultiplayerRankingActivity.class)) {
                ((BikeRaceApplication) getApplication()).d().j();
                i();
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

    @Override // com.topfreegames.e.a.l
    public void a(final Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.e eVar) {
        try {
            if (eVar == com.topfreegames.e.e.FRIENDS_HAVE_APP) {
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.9
                    @Override // java.lang.Runnable
                    public void run() {
                        final List<com.topfreegames.bikerace.multiplayer.an> listA;
                        synchronized (MultiplayerRankingActivity.this.r) {
                            listA = com.topfreegames.bikerace.multiplayer.am.a(((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).c(), Collections.list(dictionary.elements()), MultiplayerRankingActivity.this.r);
                        }
                        MultiplayerRankingActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MultiplayerRankingActivity.this.a((List<com.topfreegames.bikerace.multiplayer.an>) listA);
                                MultiplayerRankingActivity.this.h();
                            }
                        });
                    }
                }).start();
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "handleReceivedUserFriends", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "handleReceivedUserFriends", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.d.onClick(null);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296507);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.MULTIPLAYER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<com.topfreegames.bikerace.multiplayer.an> list) {
        try {
            this.p.clear();
            if (list != null) {
                Iterator<com.topfreegames.bikerace.multiplayer.an> it = list.iterator();
                while (it.hasNext()) {
                    this.p.add(it.next());
                }
            }
            this.p.a();
            this.p.notifyDataSetChanged();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateRanking", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateRanking", e2);
        }
    }

    private void g() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296513);
        if (progressMessageView != null) {
            progressMessageView.setVisibility(0);
            progressMessageView.setMessage(getString(2131099732));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296513);
        if (progressMessageView != null) {
            progressMessageView.setVisibility(8);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == x.NEW_MULTIPLAYER_GAME.ordinal()) {
            return new com.topfreegames.bikerace.e.ab(this, this.g, this.f, this.h);
        }
        if (i == x.FIND.ordinal()) {
            return new com.topfreegames.bikerace.e.k(this, ((BikeRaceApplication) getApplication()).c().s(), this.m, this.i, this.j, this.k, this.l);
        }
        if (i == x.LINK_UNAVAILABLE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099741), getString(2131099659), null);
        }
        if (i == x.SMS_UNAVAILABLE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099742), getString(2131099659), null);
        }
        if (i == x.SHARE_UNAVAILABLE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099743), getString(2131099659), null);
        }
        if (i == x.NO_EMAIL_CLIENT.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099885), getString(2131099659), null);
        }
        if (i == x.NO_SMS_CLIENT.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099887), getString(2131099659), null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.s) {
            this.s = false;
            g();
            this.p.clear();
            this.p.notifyDataSetChanged();
        }
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.10
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.e.a.a.b().b(false, (com.topfreegames.e.a.l) MultiplayerRankingActivity.this, (Object) MultiplayerRankingActivity.this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.t) {
            g();
            this.t = false;
            this.p.clear();
            this.p.notifyDataSetChanged();
        }
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.11
            @Override // java.lang.Runnable
            public void run() {
                final List<com.topfreegames.bikerace.multiplayer.an> listA;
                synchronized (MultiplayerRankingActivity.this.r) {
                    listA = com.topfreegames.bikerace.multiplayer.am.a(((BikeRaceApplication) MultiplayerRankingActivity.this.getApplication()).c(), MultiplayerRankingActivity.this.r);
                }
                MultiplayerRankingActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.MultiplayerRankingActivity.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MultiplayerRankingActivity.this.a((List<com.topfreegames.bikerace.multiplayer.an>) listA);
                        MultiplayerRankingActivity.this.h();
                    }
                });
            }
        }).start();
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MultiplayerRankingActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }
}
