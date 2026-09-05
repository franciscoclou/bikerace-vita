package com.topfreegames.bikerace.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.topfreegames.bikerace.views.MultiplayerFriendItemView;
import com.topfreegames.bikerace.views.ProgressMessageView;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class FacebookUsersListActivity extends e implements com.topfreegames.bikerace.a.i, com.topfreegames.bikerace.multiplayer.f, com.topfreegames.e.a.g, com.topfreegames.e.a.l, com.topfreegames.e.d {
    private static /* synthetic */ int[] l;
    private com.topfreegames.bikerace.multiplayer.o h;
    private com.topfreegames.e.e j;
    private View.OnClickListener b = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(FacebookUsersListActivity.this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", FacebookUsersListActivity.class);
            FacebookUsersListActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener c = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(FacebookUsersListActivity.this, ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", FacebookUsersListActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", -1);
            FacebookUsersListActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private View.OnClickListener d = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(FacebookUsersListActivity.this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", FacebookUsersListActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CreateNewRandomGame", true);
            FacebookUsersListActivity.this.a(intent, 2130968588, 2130968583);
        }
    };
    private View.OnClickListener e = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                FacebookUsersListActivity.this.i();
                FacebookUsersListActivity.this.j();
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FacebookButtonListener", e2);
            }
        }
    };
    private com.topfreegames.bikerace.views.l f = new com.topfreegames.bikerace.views.l() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.6
        @Override // com.topfreegames.bikerace.views.l
        public void a(String str, MultiplayerFriendItemView multiplayerFriendItemView) {
            if (str == null) {
                return;
            }
            try {
                FacebookUsersListActivity.this.h.b(FacebookUsersListActivity.this, str);
                FacebookUsersListActivity.this.h.a(str, com.topfreegames.bikerace.multiplayer.v.GAME_START_VIA_REGULAR_SELECTION);
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FriendPlayButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FriendPlayButtonListener", e2);
            }
        }
    };
    private com.topfreegames.bikerace.views.l g = new com.topfreegames.bikerace.views.l() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.7
        @Override // com.topfreegames.bikerace.views.l
        public void a(final String str, final MultiplayerFriendItemView multiplayerFriendItemView) {
            try {
                FacebookUsersListActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.topfreegames.e.b.a(FacebookUsersListActivity.this.getApplicationContext()).a(FacebookUsersListActivity.this, str, FacebookUsersListActivity.this.h.h(), multiplayerFriendItemView.getFriendName(), FacebookUsersListActivity.this);
                        } catch (Error e) {
                            if (com.topfreegames.bikerace.ap.d()) {
                                e.printStackTrace();
                            }
                            ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FriendInviteButtonListener_run", e);
                            throw e;
                        } catch (Exception e2) {
                            if (com.topfreegames.bikerace.ap.d()) {
                                e2.printStackTrace();
                            }
                            ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FriendInviteButtonListener_run", e2);
                        }
                    }
                });
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FriendInviteButtonListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) FacebookUsersListActivity.this.getApplication()).d().a(getClass().getName(), "FriendInviteButtonListener", e2);
            }
        }
    };
    private k i = null;
    private View k = null;

    static /* synthetic */ int[] h() {
        int[] iArr = l;
        if (iArr == null) {
            iArr = new int[com.topfreegames.e.e.valuesCustom().length];
            try {
                iArr[com.topfreegames.e.e.ALL_FRIENDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_HAVE_APP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            l = iArr;
        }
        return iArr;
    }

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(2130903074);
            this.h = ((BikeRaceApplication) getApplication()).c();
            this.h.b((com.topfreegames.bikerace.multiplayer.f) this);
            findViewById(2131296453).setOnClickListener(this.e);
            findViewById(2131296456).setOnClickListener(this.d);
            findViewById(2131296458).setOnClickListener(this.c);
            this.k = findViewById(2131296461);
            this.k.findViewById(2131296463).setOnClickListener(this.e);
            this.k.findViewById(2131296466).setOnClickListener(this.d);
            a(this.k);
            n();
            this.i = new k(this, this, 0);
            ListView listView = (ListView) findViewById(2131296460);
            listView.setClickable(false);
            listView.setFocusable(false);
            listView.setDividerHeight(0);
            listView.setOnScrollListener(new l(this, null));
            listView.setAdapter((ListAdapter) this.i);
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
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    com.topfreegames.e.a.a.b().a((Object) FacebookUsersListActivity.this);
                    com.topfreegames.bikerace.a.f.a(FacebookUsersListActivity.this.getApplicationContext()).b(FacebookUsersListActivity.this);
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
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.9
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
            if (!ag.a(this, FacebookUsersListActivity.class) && !ah.a(this, FacebookUsersListActivity.class)) {
                BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
                com.topfreegames.bikerace.a.f.a(getApplicationContext()).a((com.topfreegames.bikerace.a.i) this);
                if (hasWindowFocus()) {
                    bikeRaceApplication.b().e();
                }
                com.topfreegames.bikerace.t tVarD = bikeRaceApplication.d();
                if (this.j == com.topfreegames.e.e.FRIENDS_HAVE_APP) {
                    tVarD.h();
                } else if (this.j == com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP) {
                    tVarD.i();
                }
                a(com.topfreegames.e.e.FRIENDS_HAVE_APP);
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
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onWindowFocusChanged", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onWindowFocusChanged", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
            this.h.a((com.topfreegames.bikerace.multiplayer.f) this);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.j == com.topfreegames.e.e.FRIENDS_HAVE_APP) {
            this.j = com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP;
        } else {
            this.j = com.topfreegames.e.e.FRIENDS_HAVE_APP;
        }
        a(this.j);
    }

    private void a(com.topfreegames.e.e eVar) {
        try {
            k();
            this.i.clear();
            this.i.notifyDataSetChanged();
            this.j = eVar;
            switch (h()[this.j.ordinal()]) {
                case 2:
                    com.topfreegames.e.a.a.b().b(false, (com.topfreegames.e.a.l) this, (Object) this);
                    break;
                case 3:
                    com.topfreegames.e.a.a.b().a(false, (com.topfreegames.e.a.l) this, (Object) this);
                    break;
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "displayFacebookUsers", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "displayFacebookUsers", e2);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected View b() {
        return findViewById(2131296452);
    }

    public synchronized void a(List<com.topfreegames.e.l> list) {
        try {
            try {
                this.i.clear();
                if (list != null) {
                    Iterator<com.topfreegames.e.l> it = list.iterator();
                    while (it.hasNext()) {
                        this.i.add(it.next());
                    }
                }
                this.i.a();
                this.i.notifyDataSetChanged();
                if (this.i.getCount() > 0) {
                    n();
                } else if (this.j == com.topfreegames.e.e.FRIENDS_HAVE_APP) {
                    m();
                }
            } catch (Exception e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateList", e);
            }
        } catch (Error e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "updateList", e2);
            throw e2;
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.b.onClick(null);
    }

    @Override // com.topfreegames.e.a.l
    public void a(Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.e eVar) {
        try {
            if (eVar == this.j) {
                List<String> listM = this.h.m();
                List<String> listA = com.topfreegames.e.a.a.b().a(dictionary);
                ArrayList arrayList = new ArrayList();
                final Vector vector = new Vector();
                if (listA != null) {
                    for (String str : listA) {
                        if (!listM.contains(str)) {
                            vector.add(dictionary.get(str));
                            arrayList.add(str);
                        }
                    }
                }
                runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.10
                    @Override // java.lang.Runnable
                    public void run() {
                        FacebookUsersListActivity.this.a(vector);
                        FacebookUsersListActivity.this.l();
                    }
                });
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "handleReceivedCurrentFriends", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "handleReceivedCurrentFriends", e2);
        }
    }

    @Override // com.topfreegames.e.a.g
    public void a(boolean z) {
    }

    @Override // com.topfreegames.e.a.g
    public void g() {
    }

    @Override // com.topfreegames.bikerace.multiplayer.f
    public void b(com.topfreegames.bikerace.multiplayer.l lVar) {
        if (lVar != null) {
            super.a(lVar);
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return b.MULTIPLAYER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        try {
            TextView textView = (TextView) findViewById(2131296455);
            if (textView != null) {
                if (this.j == com.topfreegames.e.e.FRIENDS_HAVE_APP) {
                    textView.setText(getString(2131099713));
                } else {
                    textView.setText(getString(2131099714));
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

    private void k() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296468);
        if (progressMessageView != null) {
            progressMessageView.setMessage(getString(2131099729));
            progressMessageView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ProgressMessageView progressMessageView = (ProgressMessageView) findViewById(2131296468);
        if (progressMessageView != null) {
            progressMessageView.setVisibility(8);
        }
    }

    private void m() {
        this.k.setVisibility(0);
    }

    private void n() {
        this.k.setVisibility(8);
    }

    @Override // com.topfreegames.bikerace.a.i
    public void a(final com.topfreegames.bikerace.a.a aVar, final com.topfreegames.bikerace.a.j jVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.FacebookUsersListActivity.2
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.views.a.a(FacebookUsersListActivity.this.b(), aVar, jVar);
            }
        });
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", FacebookUsersListActivity.class);
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    @Override // com.topfreegames.e.d
    public void a(com.topfreegames.e.c cVar) {
    }
}
