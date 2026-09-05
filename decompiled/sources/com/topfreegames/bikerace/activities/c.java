package com.topfreegames.bikerace.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;

/* JADX INFO: compiled from: BaseActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class c extends Activity {
    private static int e = -1;
    private static boolean f = true;
    private boolean b = true;
    private boolean c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected SparseArray<View> f1078a = new SparseArray<>();
    private boolean d = false;

    protected abstract b a();

    protected abstract boolean a(String str);

    protected abstract View b();

    protected abstract void c();

    @Override // android.app.Activity
    public View findViewById(int i) {
        View viewFindViewById = this.f1078a.get(i);
        if (viewFindViewById == null) {
            viewFindViewById = super.findViewById(i);
            if (!(viewFindViewById instanceof ViewStub)) {
                this.f1078a.put(i, viewFindViewById);
            }
        }
        return viewFindViewById;
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        boolean zA;
        try {
            zA = com.topfreegames.bikerace.g.a.g().a();
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            zA = true;
        }
        if (zA) {
            c();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        com.topfreegames.bikerace.i.a.a(this, configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.b.a.d.a(this);
        if (e < 0) {
            e = ((ActivityManager) getSystemService("activity")).getMemoryClass();
        }
        if (e <= 24) {
            getWindow().setFormat(4);
        }
        setVolumeControlStream(3);
        this.c = false;
        com.topfreegames.bikerace.i.a.a(this);
        com.topfreegames.bikerace.ag.a(getIntent());
        try {
            com.topfreegames.bikerace.g.a.g().a((Activity) this);
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b(b());
        this.c = false;
        try {
            com.topfreegames.bikerace.g.a.g().b();
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
        }
        System.gc();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.b) {
            e();
        }
        this.c = false;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        h();
        this.c = true;
        if (!this.d) {
            a(b());
            this.d = true;
        }
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) getApplication()).a();
        com.topfreegames.a.d dVarA = com.topfreegames.a.d.a();
        dVarA.a(this, zVarA.p() <= 0);
        dVarA.a(this);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        g();
        com.topfreegames.bikerace.t.a(this);
        com.topfreegames.bikerace.ag.a(this);
        ((BikeRaceApplication) getApplication()).d().a();
        if (f) {
            f();
            f = false;
        }
        this.c = false;
        try {
            com.topfreegames.bikerace.g.a.g().c();
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        com.topfreegames.bikerace.t.b(this);
        com.topfreegames.bikerace.ag.b(this);
        if (this.b) {
            ((BikeRaceApplication) getApplication()).d().b();
        }
        this.c = false;
        k();
        try {
            com.topfreegames.bikerace.g.a.g().d();
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
        }
        System.gc();
    }

    private final void g() {
        View viewB = b();
        if (viewB != null) {
            viewB.setBackgroundDrawable(((BikeRaceApplication) getApplication()).a(this).a(a()));
        }
    }

    private final void h() {
        View viewB = b();
        if (viewB != null && viewB.getBackground() == null) {
            viewB.setBackgroundDrawable(((BikeRaceApplication) getApplication()).a(this).a(a()));
        }
    }

    private void i() {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.c.1
            @Override // java.lang.Runnable
            public void run() {
                final String strB = new com.topfreegames.bikerace.j.b(c.this.getApplicationContext()).b(((BikeRaceApplication) c.this.getApplicationContext()).a());
                com.topfreegames.bikerace.e.n nVarA = com.topfreegames.bikerace.j.b.a(c.this, strB, new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.c.1.1
                    @Override // com.topfreegames.bikerace.e.o
                    public void a() {
                        if (c.this.a(strB)) {
                            com.topfreegames.bikerace.j.b.a(false);
                            if (!com.topfreegames.bikerace.ap.e()) {
                                ((BikeRaceApplication) c.this.getApplication()).c().a(true);
                            }
                        }
                    }
                }, new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.c.1.2
                    @Override // com.topfreegames.bikerace.e.o
                    public void a() {
                        com.topfreegames.bikerace.j.b.a(false);
                    }
                });
                if (nVarA != null) {
                    nVarA.show();
                }
            }
        });
    }

    private void j() {
        if (com.topfreegames.bikerace.j.b.c()) {
            i();
        }
    }

    private final void k() {
        View viewB = b();
        if (viewB != null) {
            viewB.setBackgroundDrawable(null);
        }
    }

    protected void a(b bVar) {
        a aVarA = ((BikeRaceApplication) getApplication()).a(this);
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{aVarA.a(a()), aVarA.a(bVar)});
        transitionDrawable.setCrossFadeEnabled(true);
        b().setBackgroundDrawable(transitionDrawable);
        transitionDrawable.startTransition(1500);
    }

    protected boolean d() {
        return this.c;
    }

    protected void e() {
        try {
            try {
                if (!com.topfreegames.bikerace.ap.e()) {
                    ((BikeRaceApplication) getApplication()).c().n();
                }
                com.topfreegames.bikerace.localnotification.retention.a.a((BikeRaceApplication) getApplication());
                ((BikeRaceApplication) getApplication()).b().i();
                com.topfreegames.bikerace.k.a.a().c();
                f = true;
            } catch (Error e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onAppClose", e2);
                throw e2;
            } catch (Exception e3) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e3.printStackTrace();
                }
                ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onAppClose", e3);
                f = true;
            }
        } catch (Throwable th) {
            f = true;
            throw th;
        }
    }

    protected void f() {
        try {
            com.topfreegames.bikerace.k.a.a().b();
        } catch (Error e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onAppOpen", e2);
            throw e2;
        } catch (Exception e3) {
            if (com.topfreegames.bikerace.ap.d()) {
                e3.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onAppOpen", e3);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        j();
    }

    protected final void a(View view) {
        n.a(this, view);
    }

    protected void a(int i) {
        a(i, (Bundle) null);
    }

    protected void a(final int i, final Bundle bundle) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.c.2
            @Override // java.lang.Runnable
            public void run() {
                Dialog dialogOnCreateDialog;
                if (bundle == null) {
                    dialogOnCreateDialog = c.this.onCreateDialog(i);
                    if (dialogOnCreateDialog != null) {
                        c.this.onPrepareDialog(i, dialogOnCreateDialog);
                    }
                } else {
                    dialogOnCreateDialog = c.this.onCreateDialog(i, bundle);
                    if (dialogOnCreateDialog != null) {
                        c.this.onPrepareDialog(i, dialogOnCreateDialog, bundle);
                    }
                }
                if (dialogOnCreateDialog != null) {
                    try {
                        dialogOnCreateDialog.show();
                    } catch (Exception e2) {
                    }
                }
            }
        });
    }

    protected final void a(Intent intent, int i, int i2) {
        this.b = false;
        startActivity(intent);
        overridePendingTransition(i, i2);
        finish();
    }

    protected final void b(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if ((view instanceof ViewGroup) && !(view instanceof AdapterView)) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < ((ViewGroup) view).getChildCount()) {
                        b(((ViewGroup) view).getChildAt(i2));
                        i = i2 + 1;
                    } else {
                        ((ViewGroup) view).removeAllViews();
                        return;
                    }
                }
            }
        }
    }
}
