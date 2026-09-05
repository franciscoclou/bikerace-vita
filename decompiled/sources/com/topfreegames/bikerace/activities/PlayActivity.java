package com.topfreegames.bikerace.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.bn;
import com.topfreegames.bikerace.views.MultiplayerResultView;
import com.topfreegames.bikerace.views.RecommendedBikeView;
import com.topfreegames.bikerace.views.ThreeStarsView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PlayActivity extends d implements com.topfreegames.bikerace.a.g, com.topfreegames.bikerace.a.i {
    private com.topfreegames.bikerace.f c = null;
    private Bitmap d = null;
    private Bitmap e = null;
    private int f = -1;
    private int g = -1;
    private Boolean h = null;
    private String i = null;
    private int j = -1;
    private boolean k = false;
    private boolean l = false;
    private long m = 0;
    private String n = null;
    private com.topfreegames.bikerace.a.c o = null;
    private com.topfreegames.bikerace.multiplayer.o p = null;
    private com.topfreegames.bikerace.f.e q = null;
    private com.topfreegames.bikerace.f.k r = new com.topfreegames.bikerace.f.k();
    private final View.OnTouchListener s = new View.OnTouchListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (PlayActivity.this.c == null) {
                return false;
            }
            PlayActivity.this.c.a(motionEvent);
            return true;
        }
    };
    private final View.OnTouchListener t = new View.OnTouchListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.12
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (PlayActivity.this.c != null) {
                PlayActivity.this.c.h();
                return true;
            }
            return true;
        }
    };
    private final View.OnClickListener u = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.23
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayActivity.this.c != null) {
                PlayActivity.this.c.g();
            }
        }
    };
    private final View.OnTouchListener v = new View.OnTouchListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.33
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (PlayActivity.this.c != null) {
                PlayActivity.this.c.j();
                PlayActivity.this.c.a(motionEvent);
                return false;
            }
            return false;
        }
    };
    private final View.OnClickListener w = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.34
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean zE;
            try {
                try {
                    com.topfreegames.bikerace.g.a aVarG = com.topfreegames.bikerace.g.a.g();
                    try {
                        zE = aVarG.e();
                    } catch (Exception e) {
                        if (com.topfreegames.bikerace.ap.d()) {
                            e.printStackTrace();
                        }
                        ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "nextButtonListener", e);
                        zE = false;
                    }
                    if (!zE) {
                        if (PlayActivity.this.c != null) {
                            PlayActivity.this.c.b(true);
                        }
                        aVarG.l();
                        return;
                    }
                    PlayActivity.this.a(true);
                } catch (Error e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "PlayMultiplayerListener", e2);
                    throw e2;
                }
            } catch (Exception e3) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e3.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "PlayMultiplayerListener", e3);
            }
        }
    };
    private final View.OnClickListener x = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.35
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayActivity.this.c != null) {
                PlayActivity.this.c.b(true);
            }
        }
    };
    private final View.OnClickListener y = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.36
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            if (PlayActivity.this.c.f()) {
                intent.setClass(PlayActivity.this, LevelSelectionActivity.class);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", PlayActivity.this.c.e());
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", PlayActivity.this.c.a());
            } else {
                intent.setClass(PlayActivity.this, MultiplayerMainActivity.class);
                intent.putExtra("com.topfreegames.bikerace.CallingActivity", PlayActivity.class);
            }
            PlayActivity.this.a(intent, 2130968588, 2130968583);
            if (!com.topfreegames.bikerace.ap.e()) {
                ((BikeRaceApplication) PlayActivity.this.getApplication()).c().a(true);
            }
        }
    };
    private final View.OnClickListener z = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.37
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayActivity.this.c != null) {
                Intent intent = new Intent();
                intent.setClass(PlayActivity.this, ShopActivity.class);
                intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", PlayActivity.class);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", PlayActivity.this.c.e());
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", PlayActivity.this.c.a());
                intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", !PlayActivity.this.c.f());
                PlayActivity.this.a(intent, 2130968587, 2130968583);
                if (!com.topfreegames.bikerace.ap.e()) {
                    ((BikeRaceApplication) PlayActivity.this.getApplication()).c().a(true);
                }
            }
        }
    };
    private final View.OnClickListener A = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.38
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean zE;
            try {
                try {
                    com.topfreegames.bikerace.g.a aVarG = com.topfreegames.bikerace.g.a.g();
                    try {
                        zE = aVarG.e();
                    } catch (Exception e) {
                        if (com.topfreegames.bikerace.ap.d()) {
                            e.printStackTrace();
                        }
                        ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "nextButtonListener", e);
                        zE = false;
                    }
                    if (!zE) {
                        if (PlayActivity.this.c.a(false)) {
                            com.topfreegames.bikerace.j.a.a().f(PlayActivity.this.c.e() - 1, PlayActivity.this.c.a() - 1);
                        } else {
                            PlayActivity.this.y.onClick(null);
                        }
                        aVarG.l();
                        return;
                    }
                    PlayActivity.this.a(false);
                } catch (Error e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "nextButtonListener", e2);
                    throw e2;
                }
            } catch (Exception e3) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e3.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "nextButtonListener", e3);
            }
        }
    };
    private final View.OnClickListener B = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayActivity.this.c != null) {
                PlayActivity.this.c.k();
            }
        }
    };
    private final View.OnClickListener C = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayActivity.this.c != null && !PlayActivity.this.c.a(true)) {
                PlayActivity.this.y.onClick(null);
            }
        }
    };
    private final View.OnTouchListener D = new View.OnTouchListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.4
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (PlayActivity.this.c != null) {
                view.setOnTouchListener(null);
                PlayActivity.this.c.j();
                return true;
            }
            return true;
        }
    };
    private final View.OnClickListener E = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                PlayActivity.this.c.i();
            } catch (Exception e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
            }
        }
    };
    private final View.OnClickListener F = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int i;
            try {
                PlayActivity.this.c.l();
                if (PlayActivity.this.c.m()) {
                    i = 2130837660;
                    com.topfreegames.bikerace.af.g(PlayActivity.this.b);
                } else {
                    i = 2130837659;
                    com.topfreegames.bikerace.af.a(PlayActivity.this.b, (String) null, 0.0f, false);
                }
                ((Button) view).setBackgroundResource(i);
            } catch (Exception e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
            }
        }
    };
    private final View.OnClickListener G = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", PlayActivity.class);
            PlayActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private final View.OnClickListener H = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.8
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlayActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.8.1
                @Override // java.lang.Runnable
                public void run() {
                    PlayActivity.this.a(af.MULTIPLAYER_LOCKED.ordinal());
                }
            });
        }
    };
    private final View.OnClickListener I = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.9
        private static /* synthetic */ int[] b;

        static /* synthetic */ int[] a() {
            int[] iArr = b;
            if (iArr == null) {
                iArr = new int[com.topfreegames.bikerace.q.valuesCustom().length];
                try {
                    iArr[com.topfreegames.bikerace.q.PLAYING_AGAINST.ordinal()] = 2;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[com.topfreegames.bikerace.q.PLAYING_FIRST.ordinal()] = 3;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[com.topfreegames.bikerace.q.WATCHING.ordinal()] = 1;
                } catch (NoSuchFieldError e3) {
                }
                b = iArr;
            }
            return iArr;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                switch (a()[PlayActivity.this.c.c().ordinal()]) {
                    case 1:
                        com.topfreegames.bikerace.af.c(PlayActivity.this.b);
                        PlayActivity.this.k = true;
                        PlayActivity.this.l = false;
                        break;
                    case 2:
                        com.topfreegames.bikerace.af.c(PlayActivity.this.b);
                        PlayActivity.this.k = true;
                        PlayActivity.this.l = false;
                        break;
                    case 3:
                        PlayActivity.this.k = false;
                        PlayActivity.this.l = true;
                        Intent intent = new Intent();
                        intent.setClass(PlayActivity.this, MultiplayerMainActivity.class);
                        intent.putExtra("com.topfreegames.bikerace.CallingActivity", PlayActivity.class);
                        PlayActivity.this.a(intent, 2130968588, 2130968583);
                        break;
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "PlayMultiplayerListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "PlayMultiplayerListener", e2);
            }
        }
    };
    private final View.OnClickListener J = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.10
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) PlayActivity.this.getApplication()).c();
            try {
                PlayActivity.this.l = true;
                com.topfreegames.bikerace.multiplayer.l lVarA = oVarC.a(PlayActivity.this.c.b());
                lVarA.b(false);
                PlayActivity.this.c.a(lVarA.q(), lVarA.d(), lVarA, oVarC.f(), oVarC.h(), 0, true);
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "ReplayMultiplayerListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "ReplayMultiplayerListener", e2);
            }
        }
    };
    private final View.OnClickListener K = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.11
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlayActivity.this.c("");
        }
    };
    private final View.OnClickListener L = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.13
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    };
    private final View.OnClickListener M = new View.OnClickListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.14
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayActivity.this.c != null) {
                Intent intent = new Intent();
                intent.setClass(PlayActivity.this, ShopActivity.class);
                intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", PlayActivity.class);
                intent.putExtra("com.topfreegames.bikerace.WorldSelected", PlayActivity.this.c.e());
                intent.putExtra("com.topfreegames.bikerace.PhaseSelected", PlayActivity.this.c.a());
                intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", !PlayActivity.this.c.f());
                PlayActivity.this.a(intent, 2130968587, 2130968583);
                if (!com.topfreegames.bikerace.ap.e()) {
                    ((BikeRaceApplication) PlayActivity.this.getApplication()).c().a(true);
                }
            }
        }
    };
    private final com.topfreegames.bikerace.e.o N = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.15
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) PlayActivity.this.getApplicationContext();
            if (bikeRaceApplication.a(false).F()) {
                bikeRaceApplication.d().f(false);
            } else {
                bikeRaceApplication.d().d(false);
            }
        }
    };
    private final com.topfreegames.bikerace.e.o O = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.16
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) PlayActivity.this.getApplicationContext();
            if (bikeRaceApplication.a(false).F()) {
                bikeRaceApplication.d().f(true);
            } else {
                bikeRaceApplication.d().d(true);
            }
            com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
            if (com.topfreegames.bikerace.ap.h()) {
                try {
                    PlayActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + PlayActivity.this.getPackageName())));
                    zVarA.a(true);
                    return;
                } catch (Exception e) {
                    zVarA.a(false);
                    PlayActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PlayActivity.this.a(af.RATING_FAILED_GOOGLE_PLAY.ordinal());
                        }
                    });
                    return;
                }
            }
            if (com.topfreegames.bikerace.ap.i()) {
                try {
                    PlayActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + PlayActivity.this.getPackageName())));
                    zVarA.a(true);
                    return;
                } catch (Exception e2) {
                    zVarA.a(false);
                    PlayActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.16.2
                        @Override // java.lang.Runnable
                        public void run() {
                            PlayActivity.this.a(af.RATING_FAILED_AMAZON.ordinal());
                        }
                    });
                    return;
                }
            }
            if (com.topfreegames.bikerace.ap.j()) {
                try {
                    PlayActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("samsungapps://ProductDetail/" + PlayActivity.this.getPackageName())));
                    zVarA.a(true);
                } catch (Exception e3) {
                    zVarA.a(false);
                    PlayActivity.this.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.16.3
                        @Override // java.lang.Runnable
                        public void run() {
                            PlayActivity.this.a(af.RATING_FAILED_SAMSUNG.ordinal());
                        }
                    });
                }
            }
        }
    };
    private final com.topfreegames.bikerace.e.o P = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.17
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            ((BikeRaceApplication) PlayActivity.this.getApplication()).d().e(true);
            PlayActivity.this.a(af.RATING.ordinal());
        }
    };
    private final com.topfreegames.bikerace.e.o Q = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.18
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            ((BikeRaceApplication) PlayActivity.this.getApplication()).d().e(false);
        }
    };
    private final com.topfreegames.bikerace.e.o R = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.19
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            Intent intent = new Intent();
            intent.setClass(PlayActivity.this, MultiplayerMainActivity.class);
            PlayActivity.this.a(intent, 2130968587, 2130968583);
        }
    };
    private com.topfreegames.bikerace.multiplayer.e S = new com.topfreegames.bikerace.multiplayer.e() { // from class: com.topfreegames.bikerace.activities.PlayActivity.20
        @Override // com.topfreegames.bikerace.multiplayer.e
        public void a(com.topfreegames.bikerace.multiplayer.l lVar) {
            try {
                if (PlayActivity.this.c.c() != com.topfreegames.bikerace.q.PLAYING_FIRST) {
                    while (!PlayActivity.this.k && !PlayActivity.this.l) {
                        Thread.sleep(10L);
                    }
                    if (!PlayActivity.this.k) {
                        PlayActivity.this.l = false;
                        return;
                    }
                    PlayActivity.this.l = false;
                    PlayActivity.this.k = false;
                    PlayActivity.this.f = lVar.q();
                    PlayActivity.this.g = lVar.d();
                    PlayActivity.this.i = lVar.b();
                    PlayActivity.this.h = true;
                    PlayActivity.this.j = 0;
                    com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) PlayActivity.this.getApplication()).c();
                    PlayActivity.this.c.a(lVar.q(), lVar.d(), lVar, oVarC.f(), oVarC.h());
                }
            } catch (Error e) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "MultiplayerStartNewTurnListener", e);
                throw e;
            } catch (Exception e2) {
                if (com.topfreegames.bikerace.ap.d()) {
                    e2.printStackTrace();
                }
                ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "MultiplayerStartNewTurnListener", e2);
                PlayActivity.this.j();
            }
        }
    };
    private com.topfreegames.bikerace.e.d T = new com.topfreegames.bikerace.e.d() { // from class: com.topfreegames.bikerace.activities.PlayActivity.21
        @Override // com.topfreegames.bikerace.e.d
        public void a(com.topfreegames.bikerace.c cVar) {
            Intent intent = new Intent();
            intent.setClass(PlayActivity.this, ShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", PlayActivity.class);
            intent.putExtra("com.topfreegames.bikerace.WorldSelected", PlayActivity.this.c.e());
            intent.putExtra("com.topfreegames.bikerace.PhaseSelected", PlayActivity.this.c.a());
            intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", !PlayActivity.this.c.f());
            intent.putExtra("com.topfreegames.bikerace.ShopCenter", cVar.ordinal());
            intent.putExtra("com.topfreegames.bikerace.ShopSelect", true);
            PlayActivity.this.a(intent, 2130968587, 2130968583);
            if (!com.topfreegames.bikerace.ap.e()) {
                ((BikeRaceApplication) PlayActivity.this.getApplication()).c().a(true);
            }
        }
    };
    private com.topfreegames.bikerace.e.o U = new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.22
        @Override // com.topfreegames.bikerace.e.o
        public void a() {
            if (PlayActivity.this.p == null || PlayActivity.this.p.a()) {
                return;
            }
            PlayActivity.this.y();
        }
    };
    private com.topfreegames.bikerace.multiplayer.g V = new com.topfreegames.bikerace.multiplayer.g() { // from class: com.topfreegames.bikerace.activities.PlayActivity.24
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
            if (z) {
                return;
            }
            PlayActivity.this.z();
            PlayActivity.this.r.a(PlayActivity.this.q, PlayActivity.this);
        }

        @Override // com.topfreegames.bikerace.multiplayer.g
        public void b(boolean z) {
        }
    };
    final Handler b = new Handler() { // from class: com.topfreegames.bikerace.activities.PlayActivity.25
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (!PlayActivity.this.isFinishing()) {
                String string = message.getData().getString("Type");
                if (string.equals("Help")) {
                    PlayActivity.this.r();
                    PlayActivity.this.l();
                    PlayActivity.this.m();
                    PlayActivity.this.o();
                    PlayActivity.this.u();
                    PlayActivity.this.q();
                    PlayActivity.this.v();
                    PlayActivity.this.n();
                    PlayActivity.this.x();
                } else if (string.equals("Loading")) {
                    PlayActivity.this.p();
                    PlayActivity.this.l();
                    PlayActivity.this.m();
                    PlayActivity.this.o();
                    PlayActivity.this.u();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.n();
                    PlayActivity.this.x();
                } else if (string.equals("ToStart")) {
                    String string2 = message.getData().getString("ToStartMsg");
                    float f = message.getData().getFloat("ToStartBestTime");
                    boolean z = message.getData().getBoolean("ToStartSinglePlayer");
                    PlayActivity.this.a(string2, f);
                    if (z) {
                        PlayActivity.this.w();
                    }
                    PlayActivity.this.l();
                    PlayActivity.this.o();
                    PlayActivity.this.q();
                    PlayActivity.this.u();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.n();
                } else if (string.equals("Play")) {
                    if (!message.getData().getBoolean("IsWatchingReplay")) {
                        PlayActivity.this.n();
                        PlayActivity.this.k();
                    }
                    PlayActivity.this.o();
                    PlayActivity.this.m();
                    PlayActivity.this.q();
                    PlayActivity.this.u();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.x();
                    com.topfreegames.bikerace.j.a.a().e(PlayActivity.this.c.e() - 1, PlayActivity.this.c.a() - 1);
                } else if (string.equals("Won")) {
                    Bundle data = message.getData();
                    PlayActivity.this.a(data.getInt("Text"), data.getFloat("Time"), data.getInt("NumStars"), data.getFloat("NextStarTime"), data.getFloat("BestTime"), data.getBoolean("CustomLevel"));
                    PlayActivity.this.l();
                    PlayActivity.this.m();
                    PlayActivity.this.q();
                    PlayActivity.this.u();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.n();
                    PlayActivity.this.x();
                } else if (string.equals("Pause")) {
                    PlayActivity.this.a(Boolean.valueOf(message.getData().getBoolean("PauseMultiplayer")).booleanValue(), message.getData().getInt("WorldID"), message.getData().getInt("LevelID"));
                    PlayActivity.this.o();
                    PlayActivity.this.m();
                    PlayActivity.this.q();
                    PlayActivity.this.l();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.n();
                    PlayActivity.this.x();
                } else if (string.equals("Error")) {
                    PlayActivity.this.j();
                } else if (string.equals("MultiplayerResult")) {
                    Bundle data2 = message.getData();
                    String string3 = data2.getString("UserID");
                    String string4 = data2.getString("OpponentID");
                    String string5 = data2.getString("UserName");
                    String string6 = data2.getString("OpponentName");
                    float f2 = data2.getFloat("UserTime");
                    float f3 = data2.getFloat("OpponentTime");
                    ((MultiplayerResultView) PlayActivity.this.findViewById(2131296599)).a(string3, string5, f2, data2.getInt("UserWins"), string4, string6, f3, data2.getInt("OpponentWins"), data2.getBoolean("IsWatching"), com.topfreegames.bikerace.p.valuesCustom()[data2.getInt("Result")], (((BikeRaceApplication) PlayActivity.this.getApplication()).a().c() || com.topfreegames.bikerace.ap.l()) ? false : true, data2.getBoolean("IsReplay"));
                    PlayActivity.this.m();
                    PlayActivity.this.l();
                    PlayActivity.this.s();
                    PlayActivity.this.m();
                    PlayActivity.this.q();
                    PlayActivity.this.o();
                    PlayActivity.this.n();
                    PlayActivity.this.u();
                    PlayActivity.this.x();
                } else if (string.equals("WatchingReplay")) {
                    PlayActivity.this.b(message.getData().getString("ReplayMsg"));
                    PlayActivity.this.o();
                    PlayActivity.this.m();
                    PlayActivity.this.q();
                    PlayActivity.this.l();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.u();
                    PlayActivity.this.x();
                } else if (string.equals("ZoomOut")) {
                    PlayActivity.this.w();
                    PlayActivity.this.o();
                    PlayActivity.this.m();
                    PlayActivity.this.q();
                    PlayActivity.this.l();
                    PlayActivity.this.s();
                    PlayActivity.this.v();
                    PlayActivity.this.u();
                } else if (string.equals("ShopOffer")) {
                    PlayActivity.this.n = message.getData().getString("OfferProductId");
                    PlayActivity.this.a(af.SHOP_OFFER.ordinal());
                } else if (string.equals("SkipDialog")) {
                    PlayActivity.this.a(af.SKIP.ordinal());
                } else if (string.equals("RatingDialog")) {
                    PlayActivity.this.a(((BikeRaceApplication) PlayActivity.this.getApplicationContext()).a(false).F() ? af.RATING_EXTRA_STEP.ordinal() : af.RATING.ordinal());
                } else if (string.equals("MultiUnlockDialog")) {
                    PlayActivity.this.a(af.MULTIPLAYER_UNLOCKED.ordinal());
                } else {
                    string.equals("Interstitial");
                }
                PlayActivity.this.b().invalidate();
            }
        }
    };

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(2130903085);
            com.topfreegames.bikerace.af.c(this.b);
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            bikeRaceApplication.a(this).a();
            if (!com.topfreegames.bikerace.ap.e()) {
                com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) getApplication()).c();
                oVarC.n();
                oVarC.a(false);
            }
            if (bundle != null) {
                onRestoreInstanceState(bundle);
            }
            this.c = bikeRaceApplication.a(this, this.b);
            this.h = Boolean.valueOf(getIntent().getExtras().getBoolean("com.topfreegame.bikerace.IsMultiplayer"));
            this.p = ((BikeRaceApplication) getApplication()).c();
            this.p.b(this.V);
            if (this.p.a()) {
                z();
            }
            GLSurfaceView gLSurfaceViewH = h();
            if (com.topfreegames.bikerace.ap.d()) {
                gLSurfaceViewH.setDebugFlags(3);
            }
            gLSurfaceViewH.setRenderer(this.c);
            getWindow().setFlags(XMLChar.MASK_NCNAME, XMLChar.MASK_NCNAME);
            findViewById(2131296576).setOnClickListener(this.u);
            findViewById(2131296577).setOnTouchListener(this.v);
            findViewById(2131296616).setOnClickListener(this.w);
            findViewById(2131296617).setOnClickListener(this.y);
            findViewById(2131296621).setOnClickListener(this.A);
            findViewById(2131296615).setOnClickListener(this.z);
            findViewById(2131296593).setOnClickListener(this.w);
            findViewById(2131296595).setOnClickListener(this.y);
            findViewById(2131296587).setOnClickListener(this.B);
            findViewById(2131296592).setOnClickListener(this.z);
            findViewById(2131296596).setOnClickListener(this.C);
            findViewById(2131296574).setOnTouchListener(this.t);
            findViewById(2131296584).setOnClickListener(this.E);
            MultiplayerResultView multiplayerResultView = (MultiplayerResultView) findViewById(2131296599);
            multiplayerResultView.setPlayOnClickListener(this.I);
            multiplayerResultView.setReplayOnClickListener(this.J);
            multiplayerResultView.setRetryOnClickListener(this.L);
            findViewById(2131296594).setOnClickListener(this.x);
            findViewById(2131296581).setOnClickListener(this.F);
            findViewById(2131296618).setOnClickListener(this.K);
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.26
                @Override // java.lang.Runnable
                public void run() {
                    com.topfreegames.bikerace.a.f.a((Context) PlayActivity.this).a((com.topfreegames.bikerace.a.g) PlayActivity.this);
                }
            }).start();
            com.topfreegames.bikerace.g.a.g().b(true);
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
            j();
        }
    }

    @Override // com.topfreegames.bikerace.activities.d, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            bundle.putLong("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
            bundle.putInt("com.topfreegames.bikerace.WorldSelected", this.c.e());
            bundle.putInt("com.topfreegames.bikerace.PhaseSelected", this.c.a());
            boolean z = !this.c.f();
            bundle.putBoolean("com.topfreegame.bikerace.IsMultiplayer", z);
            if (z) {
                bundle.putString("com.topfreegames.bikerace.MultiplayerGameId", this.c.b());
                bundle.putInt("com.topfreegames.bikerace.MultiplayerTryNumber", this.c.d());
            }
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
            this.f = bundle.getInt("com.topfreegames.bikerace.WorldSelected", 1);
            this.g = bundle.getInt("com.topfreegames.bikerace.PhaseSelected", 1);
            this.h = Boolean.valueOf(bundle.getBoolean("com.topfreegame.bikerace.IsMultiplayer"));
            this.i = bundle.getString("com.topfreegames.bikerace.MultiplayerGameId");
            this.j = bundle.getInt("com.topfreegames.bikerace.MultiplayerTryNumber");
            this.m = bundle.getLong("com.topfreegames.bikerace.IntentCreationTime");
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
    public void onPause() {
        try {
            super.onPause();
            t();
            this.c.a(h());
            com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a((Context) this);
            fVarA.b(this);
            fVarA.b();
            com.topfreegames.e.a.a.b().a((Object) this);
            b().setOnTouchListener(null);
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

    @Override // com.topfreegames.bikerace.activities.d, com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            if (!ag.a(this, PlayActivity.class) && !ah.a(this, PlayActivity.class)) {
                com.topfreegames.bikerace.a.f.a(getApplicationContext()).a((com.topfreegames.bikerace.a.i) this);
                bikeRaceApplication.a(true).z();
                b().setOnTouchListener(this.s);
                i();
                this.c.b(h());
                com.topfreegames.bikerace.j.a.a().f(this.c.e() - 1, this.c.a() - 1);
                com.topfreegames.bikerace.z zVarA = bikeRaceApplication.a();
                if (zVarA.u()) {
                    zVarA.a(false);
                    bikeRaceApplication.d().k();
                }
            }
        } catch (Error e) {
            h().onPause();
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onResume", e);
            throw e;
        } catch (Exception e2) {
            h().onPause();
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "onResume", e2);
            j();
        }
    }

    private void g() {
        com.topfreegames.bikerace.c.d.a().a(com.topfreegames.bikerace.c.c.EASTER_EGG, (com.topfreegames.bikerace.al.b(this) && com.topfreegames.bikerace.al.a(this)) || (!((BikeRaceApplication) getApplication()).a().a(com.topfreegames.bikerace.c.EASTER) && com.topfreegames.bikerace.a.f.a((Context) this).b("AchievEasterEggs").size() > 0));
    }

    @Override // com.topfreegames.bikerace.activities.c
    public void c() {
        this.c.g();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 82) {
            return super.onKeyDown(i, keyEvent);
        }
        this.c.g();
        return true;
    }

    @Override // com.topfreegames.bikerace.activities.c, android.app.Activity
    public void onStop() {
        try {
            super.onStop();
            this.c.c(h());
            this.f = this.c.e();
            this.g = this.c.a();
            if (!this.c.f()) {
                this.j = this.c.d();
            }
            this.c = null;
            t();
            b(findViewById(2131296573));
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
            b(findViewById(2131296574));
            if (this.e != null) {
                this.e.recycle();
                this.e = null;
            }
            this.b.removeCallbacksAndMessages(null);
            ((BikeRaceApplication) getApplication()).c().a((com.topfreegames.bikerace.multiplayer.e) null);
            System.gc();
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
    protected View b() {
        return findViewById(2131296570);
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected b a() {
        return null;
    }

    private GLSurfaceView h() {
        return (GLSurfaceView) findViewById(2131296572);
    }

    private void i() {
        com.topfreegames.bikerace.af.c(this.b);
        this.c = ((BikeRaceApplication) getApplication()).a(this, this.b);
        Bundle extras = getIntent().getExtras();
        long j = extras.getLong("com.topfreegames.bikerace.IntentCreationTime");
        if (j > this.m) {
            this.f = extras.getInt("com.topfreegames.bikerace.WorldSelected");
            this.g = extras.getInt("com.topfreegames.bikerace.PhaseSelected");
            this.h = Boolean.valueOf(extras.getBoolean("com.topfreegame.bikerace.IsMultiplayer"));
            this.i = extras.getString("com.topfreegames.bikerace.MultiplayerGameId");
            this.j = 0;
            this.m = j;
        }
        g();
        if (this.h != null && this.h.booleanValue()) {
            com.topfreegames.bikerace.multiplayer.o oVarC = ((BikeRaceApplication) getApplication()).c();
            com.topfreegames.bikerace.multiplayer.l lVarA = oVarC.a(this.i);
            oVarC.a(this.S);
            this.c.a(this.f, this.g, lVarA, oVarC.f(), oVarC.h(), this.j, false);
            return;
        }
        this.c.a(this.f, this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Intent intent = new Intent();
        if (this.h != null && this.h.booleanValue()) {
            intent.setClass(this, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", PlayActivity.class);
        } else {
            intent.setClass(this, WorldSelectionActivity.class);
        }
        ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "Using fallback");
        a(intent, 2130968587, 2130968583);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            findViewById(2131296575).setVisibility(0);
            findViewById(2131296576).setVisibility(0);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showPauseButton", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showPauseButton", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        try {
            findViewById(2131296575).setVisibility(8);
            findViewById(2131296576).setVisibility(8);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hidePauseButton", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hidePauseButton", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, float f) {
        try {
            findViewById(2131296577).setVisibility(0);
            com.topfreegames.bikerace.j.a aVarA = com.topfreegames.bikerace.j.a.a();
            RecommendedBikeView recommendedBikeView = (RecommendedBikeView) findViewById(2131296578);
            if (this.c != null && this.c.f() && aVarA.c(this.c.e() - 1, this.c.a() - 1)) {
                Resources resources = getResources();
                com.topfreegames.bikerace.c cVarD = aVarA.d(this.c.e() - 1, this.c.a() - 1);
                recommendedBikeView.setBikeType(cVarD);
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, 2130968586);
                animationLoadAnimation.setAnimationListener(new ae(this, recommendedBikeView));
                recommendedBikeView.setOnClickListener(new ad(this, aVarA.a(cVarD, resources), cVarD));
                recommendedBikeView.startAnimation(animationLoadAnimation);
            } else {
                recommendedBikeView.setVisibility(4);
            }
            TextView textView = (TextView) findViewById(2131296579);
            textView.setVisibility(0);
            if (str != null) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) findViewById(2131296580);
            if (f > 0.0f) {
                textView2.setVisibility(0);
                textView2.setText(String.format(getString(2131099670), a(f)));
            } else if (f == 0.0f) {
                textView2.setVisibility(0);
            } else {
                textView2.setVisibility(8);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showStartScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showStartScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            findViewById(2131296577).setVisibility(8);
            ((RecommendedBikeView) findViewById(2131296578)).setVisibility(8);
            findViewById(2131296579).setVisibility(8);
            ((TextView) findViewById(2131296580)).setVisibility(8);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideStartScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideStartScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            View viewFindViewById = findViewById(2131296582);
            viewFindViewById.setVisibility(0);
            TextView textView = (TextView) findViewById(2131296583);
            textView.setVisibility(0);
            textView.setText(str);
            findViewById(2131296584).setVisibility(0);
            viewFindViewById.setOnTouchListener(this.D);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showWatchingReplayScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showWatchingReplayScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        try {
            View viewFindViewById = findViewById(2131296582);
            viewFindViewById.setVisibility(8);
            viewFindViewById.setOnTouchListener(null);
            findViewById(2131296583).setVisibility(8);
            findViewById(2131296584).setVisibility(8);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideWatchingReplayScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideWatchingReplayScreen", e2);
        }
    }

    private String a(float f) {
        return new DecimalFormat("#.00").format(f).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, float f, int i2, float f2, float f3, boolean z) {
        try {
            com.topfreegames.bikerace.m.a.a(this, (ViewGroup) findViewById(2131296622));
            ArrayList arrayList = new ArrayList();
            arrayList.add(findViewById(2131296603));
            arrayList.add(findViewById(2131296600));
            arrayList.add(findViewById(2131296612));
            arrayList.add(findViewById(2131296621));
            arrayList.add(findViewById(2131296616));
            if (z) {
                arrayList.add(findViewById(2131296618));
            } else {
                arrayList.add(findViewById(2131296617));
                if (com.topfreegames.bikerace.ap.m()) {
                    arrayList.add(findViewById(2131296615));
                }
                if (!com.topfreegames.bikerace.ap.e()) {
                    View viewFindViewById = findViewById(2131296613);
                    arrayList.add(viewFindViewById);
                    if (((BikeRaceApplication) getApplication()).a().a()) {
                        arrayList.add(findViewById(2131296614));
                        viewFindViewById.setOnClickListener(this.H);
                    } else {
                        viewFindViewById.setOnClickListener(this.G);
                    }
                }
            }
            int i3 = i / 100;
            int i4 = (i % 100) / 10;
            int i5 = i % 10;
            ImageView imageView = (ImageView) findViewById(2131296604);
            imageView.setVisibility(i3 == 0 ? 8 : 0);
            if (imageView.getVisibility() == 0) {
                imageView.setImageResource(b(i3));
            }
            ImageView imageView2 = (ImageView) findViewById(2131296605);
            imageView2.setVisibility((i3 == 0 && i4 == 0) ? 8 : 0);
            if (imageView2.getVisibility() == 0) {
                imageView2.setImageResource(b(i4));
            }
            ImageView imageView3 = (ImageView) findViewById(2131296606);
            imageView3.setVisibility(0);
            imageView3.setImageResource(b(i5));
            TextView textView = (TextView) findViewById(2131296609);
            arrayList.add(textView);
            textView.setText(getString(2131099671, new Object[]{a(f)}));
            ThreeStarsView threeStarsView = (ThreeStarsView) findViewById(2131296607);
            arrayList.add(threeStarsView);
            threeStarsView.a(i2);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((View) it.next()).setVisibility(0);
            }
            TextView textView2 = (TextView) findViewById(2131296610);
            boolean z2 = com.topfreegames.bikerace.i.a.a() == com.topfreegames.bikerace.i.b.ENGLISH;
            if (i2 < 3) {
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i2 + 1);
                objArr[1] = (i2 <= 0 || !z2) ? "" : "s";
                objArr[2] = a(f2);
                textView2.setText(getString(2131099672, objArr));
                textView2.setVisibility(0);
            } else if (f3 > 0.0f) {
                textView2.setText(getString(2131099670, new Object[]{a(f3)}));
                textView2.setVisibility(0);
            }
            if (f3 < 0.0f) {
                f *= -1.0f;
            }
            if (f < f3) {
                findViewById(2131296611).setVisibility(0);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showSinglePlayerWonScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showSinglePlayerWonScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        try {
            com.topfreegames.bikerace.m.a.b(this, (ViewGroup) findViewById(2131296622));
            ArrayList arrayList = new ArrayList();
            arrayList.add(findViewById(2131296600));
            arrayList.add(findViewById(2131296603));
            arrayList.add(findViewById(2131296607));
            arrayList.add(findViewById(2131296612));
            arrayList.add(findViewById(2131296616));
            arrayList.add(findViewById(2131296621));
            arrayList.add(findViewById(2131296617));
            arrayList.add(findViewById(2131296615));
            arrayList.add(findViewById(2131296609));
            arrayList.add(findViewById(2131296610));
            arrayList.add(findViewById(2131296614));
            arrayList.add(findViewById(2131296613));
            arrayList.add(findViewById(2131296611));
            arrayList.add(findViewById(2131296618));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((View) it.next()).setVisibility(8);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideSinglePlayerWonScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideSinglePlayerWonScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        try {
            View viewFindViewById = findViewById(2131296573);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            this.d = com.topfreegames.engine.b.a.a(getResources(), 2130837762, defaultDisplay.getWidth(), defaultDisplay.getHeight());
            viewFindViewById.setBackgroundDrawable(new BitmapDrawable(this.d));
            viewFindViewById.setVisibility(0);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        try {
            View viewFindViewById = findViewById(2131296573);
            viewFindViewById.setVisibility(8);
            b(viewFindViewById);
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideLoadingScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideLoadingScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        try {
            View viewFindViewById = findViewById(2131296574);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            this.e = com.topfreegames.engine.b.a.a(getResources(), 2130837757, defaultDisplay.getWidth(), defaultDisplay.getHeight());
            viewFindViewById.setBackgroundDrawable(new BitmapDrawable(this.e));
            viewFindViewById.setVisibility(0);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showHelpScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showHelpScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        try {
            View viewFindViewById = findViewById(2131296574);
            viewFindViewById.setVisibility(8);
            b(viewFindViewById);
            if (this.e != null) {
                this.e.recycle();
                this.e = null;
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideHelpScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideHelpScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, int i, int i2) {
        try {
            com.topfreegames.bikerace.m.a.a(this, (ViewGroup) findViewById(2131296597));
            ArrayList arrayList = new ArrayList();
            arrayList.add(findViewById(2131296585));
            arrayList.add(findViewById(2131296589));
            arrayList.add(findViewById(2131296586));
            arrayList.add(findViewById(2131296588));
            arrayList.add(findViewById(2131296587));
            if (!z) {
                arrayList.add(findViewById(2131296593));
                arrayList.add(findViewById(2131296595));
                arrayList.add(findViewById(2131296596));
                if (com.topfreegames.bikerace.ap.m()) {
                    arrayList.add(findViewById(2131296592));
                }
                if (!com.topfreegames.bikerace.ap.e()) {
                    Button button = (Button) findViewById(2131296590);
                    arrayList.add(button);
                    if (((BikeRaceApplication) getApplication()).a().a()) {
                        arrayList.add(findViewById(2131296591));
                        button.setOnClickListener(this.H);
                    } else {
                        button.setOnClickListener(this.G);
                    }
                }
            } else {
                arrayList.add(findViewById(2131296594));
            }
            TextView textView = (TextView) findViewById(2131296598);
            textView.setText(String.valueOf(com.topfreegames.bikerace.h.y.a(this, i)) + " - Lv. " + i2 + "  ");
            arrayList.add(textView);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((View) it.next()).setVisibility(0);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showPauseScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showPauseScreen", e2);
        }
    }

    private void t() {
        com.topfreegames.bikerace.m.a.b(this, (ViewGroup) findViewById(2131296597));
        com.topfreegames.bikerace.m.a.b(this, (ViewGroup) findViewById(2131296622));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        try {
            com.topfreegames.bikerace.m.a.b(this, (ViewGroup) findViewById(2131296597));
            ArrayList arrayList = new ArrayList();
            arrayList.add(findViewById(2131296585));
            arrayList.add(findViewById(2131296586));
            arrayList.add(findViewById(2131296588));
            arrayList.add(findViewById(2131296589));
            arrayList.add(findViewById(2131296593));
            arrayList.add(findViewById(2131296587));
            arrayList.add(findViewById(2131296595));
            arrayList.add(findViewById(2131296592));
            arrayList.add(findViewById(2131296596));
            arrayList.add(findViewById(2131296590));
            arrayList.add(findViewById(2131296591));
            arrayList.add(findViewById(2131296598));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((View) it.next()).setVisibility(8);
            }
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hidePauseScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hidePauseScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        try {
            ((MultiplayerResultView) findViewById(2131296599)).a();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideMultiplayerResultScreen", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideMultiplayerResultScreen", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        int i;
        try {
            Button button = (Button) findViewById(2131296581);
            button.setVisibility(0);
            if (this.c.m()) {
                i = 2130837660;
            } else {
                i = 2130837659;
            }
            button.setBackgroundResource(i);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showZoomButton", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "showZoomButton", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        try {
            findViewById(2131296581).setVisibility(8);
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideZoomButton", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) getApplication()).d().a(getClass().getName(), "hideZoomButton", e2);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(final int i) {
        com.topfreegames.bikerace.e.b bVar = null;
        if (i == af.SKIP.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getResources().getString(2131099755), getString(2131099659), null);
        }
        if (i == af.RATING.ordinal()) {
            Resources resources = getResources();
            bb bbVarA = ((BikeRaceApplication) getApplication()).a(false);
            String strZ = bbVarA.z();
            if (strZ == null || strZ.equals("")) {
                strZ = resources.getString(2131099756);
            }
            String strB = bbVarA.B();
            if (strB == null || strB.equals("")) {
                strB = resources.getString(2131099757);
            }
            String strA = bbVarA.A();
            if (strA == null || strA.equals("")) {
                strA = resources.getString(2131099758);
            }
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            if (bikeRaceApplication.a(false).F()) {
                bikeRaceApplication.d().n();
            } else {
                bikeRaceApplication.d().l();
            }
            if (bbVarA.G()) {
                return new com.topfreegames.bikerace.e.n(this, strZ, strB, strA, this.O, this.N);
            }
            return new com.topfreegames.bikerace.e.n(this, strZ, strA, strB, this.N, this.O);
        }
        if (i == af.RATING_EXTRA_STEP.ordinal()) {
            Resources resources2 = getResources();
            bb bbVarA2 = ((BikeRaceApplication) getApplication()).a(false);
            String strC = bbVarA2.C();
            if (strC == null || strC.equals("")) {
                strC = resources2.getString(2131099763);
            }
            String strE = bbVarA2.E();
            if (strE == null || strE.equals("")) {
                strE = resources2.getString(2131099764);
            }
            String strD = bbVarA2.D();
            if (strD == null || strD.equals("")) {
                strD = resources2.getString(2131099765);
            }
            ((BikeRaceApplication) getApplication()).d().m();
            if (bbVarA2.H()) {
                return new com.topfreegames.bikerace.e.n(this, strC, strE, strD, this.P, this.Q);
            }
            return new com.topfreegames.bikerace.e.n(this, strC, strD, strE, this.Q, this.P);
        }
        if (i == af.RATING_FAILED_GOOGLE_PLAY.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099997), getString(2131099659), null);
        }
        if (i == af.RATING_FAILED_AMAZON.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099998), getString(2131099659), null);
        }
        if (i == af.RATING_FAILED_SAMSUNG.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099999), getString(2131099659), null);
        }
        if (i == af.MULTIPLAYER_UNLOCKED.ordinal()) {
            Resources resources3 = getResources();
            return new com.topfreegames.bikerace.e.n(this, resources3.getString(2131099759), resources3.getString(2131099760), resources3.getString(2131099761), this.R, null);
        }
        if (i == af.MULTIPLAYER_LOCKED.ordinal()) {
            int i2 = bn.a().f1162a;
            int iP = ((BikeRaceApplication) getApplication()).a().p();
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(i2);
            objArr[1] = Integer.valueOf(iP);
            objArr[2] = iP > 1 ? "s" : "";
            return new com.topfreegames.bikerace.e.n(this, getString(2131099725, objArr), getString(2131099659), null);
        }
        if (i == af.SHOP_OFFER.ordinal() && ((BikeRaceApplication) getApplication()).a(false).X()) {
            final String str = this.n;
            com.topfreegames.bikerace.e.n nVarA = com.topfreegames.bikerace.j.b.a(this, str, new com.topfreegames.bikerace.e.o() { // from class: com.topfreegames.bikerace.activities.PlayActivity.27
                @Override // com.topfreegames.bikerace.e.o
                public void a() {
                    Intent intent = new Intent();
                    intent.setClass(PlayActivity.this, ShopActivity.class);
                    intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", PlayActivity.class);
                    intent.putExtra("com.topfreegames.bikerace.WorldSelected", PlayActivity.this.c.e());
                    intent.putExtra("com.topfreegames.bikerace.PhaseSelected", PlayActivity.this.c.a());
                    intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", !PlayActivity.this.c.f());
                    intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
                    PlayActivity.this.a(intent, 2130968587, 2130968583);
                    if (!com.topfreegames.bikerace.ap.e()) {
                        ((BikeRaceApplication) PlayActivity.this.getApplication()).c().a(true);
                    }
                }
            }, null);
            this.n = null;
            return nVarA;
        }
        if (i == af.UNLOCK_BIKE.ordinal()) {
            if (this.o != null) {
                com.topfreegames.bikerace.e.b bVar2 = new com.topfreegames.bikerace.e.b(this, this.o, this.T);
                bVar2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.topfreegames.bikerace.activities.PlayActivity.28
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        PlayActivity.this.removeDialog(i);
                    }
                });
                this.o = null;
                bVar = bVar2;
            }
            return bVar;
        }
        if (i == af.NEED_FB_LOGIN.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099928), getString(2131099929), getString(2131099930), this.U, null);
        }
        if (i == af.GIFT_OFFLINE.ordinal()) {
            return new com.topfreegames.bikerace.e.n(this, getString(2131099938), getString(2131099659), null);
        }
        return null;
    }

    @Override // com.topfreegames.bikerace.a.i
    public void a(final com.topfreegames.bikerace.a.a aVar, final com.topfreegames.bikerace.a.j jVar) {
        runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.29
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.views.a.a(PlayActivity.this.b().findViewById(2131296571), aVar, jVar);
            }
        });
    }

    @Override // com.topfreegames.bikerace.a.g
    public void a(final com.topfreegames.bikerace.a.c cVar) {
        boolean z = false;
        com.topfreegames.bikerace.c cVarA = com.topfreegames.bikerace.a.f.a(cVar);
        if (cVarA == com.topfreegames.bikerace.c.HALLOWEEN) {
            if (com.topfreegames.bikerace.am.b(this) && com.topfreegames.bikerace.am.a(this)) {
                ((BikeRaceApplication) getApplicationContext()).a().d(cVarA);
                z = true;
            }
        } else if (cVarA == com.topfreegames.bikerace.c.THANKSGIVING) {
            if (com.topfreegames.bikerace.ao.b(this) && com.topfreegames.bikerace.ao.a(this)) {
                ((BikeRaceApplication) getApplicationContext()).a().d(cVarA);
                z = true;
            }
        } else {
            ((BikeRaceApplication) getApplicationContext()).a().d(cVarA);
            z = true;
        }
        if (z) {
            runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.activities.PlayActivity.30
                @Override // java.lang.Runnable
                public void run() {
                    PlayActivity.this.o = cVar;
                    PlayActivity.this.a(af.UNLOCK_BIKE.ordinal());
                }
            });
        }
    }

    private static int b(int i) {
        switch (i) {
            case 0:
                return 2130837890;
            case 1:
                return 2130837891;
            case 2:
                return 2130837892;
            case 3:
                return 2130837893;
            case 4:
                return 2130837894;
            case 5:
                return 2130837895;
            case 6:
                return 2130837896;
            case 7:
                return 2130837897;
            case 8:
                return 2130837898;
            case 9:
                return 2130837899;
            default:
                if (com.topfreegames.bikerace.ap.d()) {
                    System.err.println("Invalid level number at LevelItemView");
                }
                return 0;
        }
    }

    @Override // com.topfreegames.bikerace.activities.c
    protected boolean a(String str) {
        Intent intent = new Intent();
        intent.setClass(this, ShopActivity.class);
        intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", PlayActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.c.e());
        intent.putExtra("com.topfreegames.bikerace.PhaseSelected", this.c.a());
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", !this.c.f());
        if (str != null) {
            intent.putExtra("com.topfreegames.bikerace.shop.offerId", str);
        }
        a(intent, 2130968587, 2130968583);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.topfreegames.bikerace.activities.PlayActivity$31] */
    public void y() {
        new Thread() { // from class: com.topfreegames.bikerace.activities.PlayActivity.31
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    PlayActivity.this.p.b(PlayActivity.this.V);
                    PlayActivity.this.p.a(PlayActivity.this);
                } catch (Error e) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e.printStackTrace();
                    }
                    ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "peformFacebookLogin", e);
                    throw e;
                } catch (Exception e2) {
                    if (com.topfreegames.bikerace.ap.d()) {
                        e2.printStackTrace();
                    }
                    ((BikeRaceApplication) PlayActivity.this.getApplication()).d().a(getClass().getName(), "peformFacebookLogin", e2);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        String strG = this.p.g();
        if (this.q == null) {
            this.q = new com.topfreegames.bikerace.f.e(strG, getApplicationContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (!((BikeRaceApplication) getApplicationContext()).e()) {
            a(af.GIFT_OFFLINE.ordinal());
            return;
        }
        if (this.p != null) {
            String strA = com.topfreegames.bikerace.h.a.q.a().a(this.c.a() - 1);
            if (this.p.a() && this.q != null) {
                this.q.a(str, strA, this, new com.topfreegames.bikerace.f.f() { // from class: com.topfreegames.bikerace.activities.PlayActivity.32
                    @Override // com.topfreegames.bikerace.f.f
                    public void a() {
                        com.topfreegames.bikerace.a.f.a(PlayActivity.this.getApplicationContext()).c("AchievGiftUserCreated");
                    }

                    @Override // com.topfreegames.bikerace.f.f
                    public void b() {
                    }
                });
            } else {
                this.r.a(new com.topfreegames.bikerace.f.i(new com.topfreegames.bikerace.f.d(str, com.topfreegames.bikerace.f.b.GIVE_SPECIFIC_TRACK, strA), null));
                a(af.NEED_FB_LOGIN.ordinal());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        Intent intent = new Intent();
        intent.setClass(this, InterstitialActivity.class);
        intent.putExtra("com.topfreegames.bikerace.WorldSelected", this.c.e());
        intent.putExtra("com.topfreegames.bikerace.PhaseSelected", (z ? 0 : 1) + this.c.a());
        intent.putExtra("com.topfreegame.bikerace.IsMultiplayer", false);
        intent.putExtra("com.topfreegames.bikerace.IntentCreationTime", new Date().getTime());
        a(intent, 2130968581, 2130968582);
    }
}
