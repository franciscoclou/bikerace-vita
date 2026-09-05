package com.applovin.adview;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.impl.a.ah;
import com.applovin.impl.a.aq;
import com.applovin.impl.adview.AppLovinVideoView;
import com.applovin.impl.adview.ac;
import com.applovin.impl.adview.ae;
import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AppLovinInterstitialActivity extends Activity implements ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile com.applovin.impl.adview.e f160a = null;
    private AppLovinAdView b;
    private com.applovin.impl.adview.e c;
    private com.applovin.a.j e;
    private com.applovin.impl.a.n f;
    private com.applovin.impl.a.d g;
    private Handler o;
    private FrameLayout p;
    private AppLovinVideoView q;
    private ac r;
    private View s;
    private TextView t;
    private int u;
    private volatile UUID v;
    private volatile boolean d = false;
    private volatile com.applovin.impl.a.a h = ah.a();
    private volatile boolean i = false;
    private volatile boolean j = false;
    private volatile boolean k = false;
    private volatile boolean l = false;
    private volatile boolean m = false;
    private volatile boolean n = false;

    public static int a(Display display) {
        if (display.getWidth() == display.getHeight()) {
            return 3;
        }
        return display.getWidth() < display.getHeight() ? 1 : 2;
    }

    private void a(float f) {
        float f2 = 1.0f;
        float f3 = 0.0f;
        if (!this.f.b().equals("left_to_right")) {
            f3 = 1.0f;
            f2 = 0.0f;
        }
        a(f3, f2, f);
    }

    private void a(float f, float f2, float f3) {
        try {
            ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, 1.0f, 1.0f);
            scaleAnimation.setDuration(ah.b(f3));
            scaleAnimation.setInterpolator(new LinearInterpolator());
            scaleAnimation.setAnimationListener(new q(this));
            this.s.startAnimation(scaleAnimation);
        } catch (Throwable th) {
            this.s.setVisibility(8);
        }
    }

    private void a(float f, float f2, int i) {
        float f3;
        float f4;
        float f5 = f2 / f;
        if (this.f.b().equals("left_to_right")) {
            f3 = f5;
            f4 = 1.0f;
        } else {
            f3 = 1.0f - f5;
            f4 = 0.0f;
        }
        a(f3, f4, i - ah.a(1.0f));
        a((int) Math.floor(ah.a(i)));
    }

    private void a(int i) {
        a(i, this.v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, UUID uuid) {
        if (uuid.equals(this.v)) {
            if (i <= 0) {
                this.t.setVisibility(8);
                return;
            }
            this.t.setVisibility(0);
            int i2 = i - 1;
            this.t.setText(Integer.toString(i2));
            this.o.postDelayed(new f(this, i2, uuid), 1000L);
        }
    }

    private void a(long j) {
        this.o.postDelayed(new p(this), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.applovin.a.a aVar) {
        com.applovin.a.c cVarE = this.c.e();
        if (cVarE != null) {
            cVarE.b(aVar);
        }
        this.i = true;
    }

    private void a(com.applovin.a.a aVar, double d, boolean z) {
        this.l = true;
        com.applovin.a.i iVarD = this.c.d();
        if (iVarD != null) {
            iVarD.a(aVar, d, z);
        }
    }

    private int b(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.applovin.a.a aVar) {
        c(aVar);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(-1157627904);
        frameLayout.addView(this.b);
        this.p.removeView(this.r);
        frameLayout.addView(this.r);
        setContentView(frameLayout);
        this.r.bringToFront();
        if (this.h.c() > 0.0f) {
            a(ah.c(this.h.c()));
        } else {
            this.r.setVisibility(0);
        }
        e();
    }

    private void c(com.applovin.a.a aVar) {
        if (this.j) {
            return;
        }
        this.j = true;
        com.applovin.a.c cVarE = this.c.e();
        if (cVarE != null) {
            cVarE.a(aVar);
        }
    }

    private void d() {
        SharedPreferences.Editor editorEdit = q().edit();
        editorEdit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        editorEdit.putInt("com.applovin.interstitial.last_video_position", 0);
        editorEdit.commit();
    }

    private void d(com.applovin.a.a aVar) {
        if (this.k) {
            return;
        }
        this.k = true;
        com.applovin.a.i iVarD = this.c.d();
        if (iVarD != null) {
            iVarD.a(aVar);
        }
    }

    private void e() {
        double currentPosition = 100.0d;
        if (this.l) {
            return;
        }
        if (!this.m) {
            if (this.q != null) {
                currentPosition = 100.0d * (((double) this.q.getCurrentPosition()) / ((double) this.q.getDuration()));
            } else {
                Log.e("AppLovinInterstitialActivity", "No video view detected on video end");
                currentPosition = 0.0d;
            }
        }
        a(this.h, currentPosition, currentPosition > 95.0d);
    }

    private void f() {
        this.u = aq.a(this).x;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.p = new FrameLayout(this);
        this.p.setLayoutParams(layoutParams);
        this.p.setBackgroundColor(-16777216);
        this.o = new Handler();
        Uri uriFromFile = Uri.fromFile(ah.a(this.h.j(), (Context) this, false));
        this.q = new AppLovinVideoView(this);
        this.q.setOnPreparedListener(new h(this));
        this.q.setOnCompletionListener(new k(this));
        this.q.setOnErrorListener(new l(this));
        this.q.setVideoURI(uriFromFile);
        this.q.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        this.p.addView(this.q);
        setContentView(this.p);
        h();
        i();
        l();
        m();
        n();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.f.i()) {
            finish();
        } else {
            k();
        }
    }

    private void h() {
        this.v = UUID.randomUUID();
    }

    private void i() {
        this.r = ac.a(this, this.h.e());
        this.r.setVisibility(8);
        this.r.setOnClickListener(new n(this));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(b(30), b(30), 53);
        this.r.a(b(30) / 30);
        layoutParams.setMargins(0, b(5), b(2), 0);
        this.p.addView(this.r, layoutParams);
    }

    private void j() {
        d(this.h);
        this.q.start();
        a(ah.a(this.h.d()));
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        runOnUiThread(new o(this));
    }

    private void l() {
        if (this.h.b() >= 0.0f) {
            a(ah.c(this.h.b()));
        }
    }

    private void m() {
        this.s = new View(this);
        this.s.setBackgroundColor(o());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.u, b(this.f.e()), 81);
        if (!this.f.f() || this.h.d() <= 0) {
            return;
        }
        this.p.addView(this.s, layoutParams);
        this.s.bringToFront();
    }

    private void n() {
        this.t = new TextView(this);
        this.t.setTextColor(o());
        this.t.setTextSize(0, b(22) * 0.8f);
        this.t.setText(Integer.toString(this.h.d()));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 83);
        layoutParams.setMargins(b(10), 0, this.s.getHeight() + b(3), 0);
        if (!this.f.g() || this.h.d() <= 0) {
            return;
        }
        this.p.addView(this.t, layoutParams);
        this.t.bringToFront();
    }

    private int o() {
        return Color.parseColor(this.f.c());
    }

    private void p() {
        a(this.h.d() + 1);
    }

    private SharedPreferences q() {
        return getSharedPreferences("com.applovin.interstitial.sharedpreferences", 0);
    }

    protected void a() {
        if (this.d) {
            return;
        }
        if (this.b == null) {
            a("AdView was null");
            return;
        }
        this.b.setAdDisplayListener(new e(this));
        this.b.setAdClickListener(new g(this));
        this.h = (com.applovin.impl.a.a) this.c.c();
        if (this.h.j() != null) {
            f();
        } else {
            b();
        }
        this.b.a(this.h);
        this.c.a(true);
    }

    public void a(String str) {
        Log.e("AppLovinInterstitialActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + com.applovin.impl.adview.e.f231a + "; CleanedUp = " + com.applovin.impl.adview.e.b));
        c(ah.a());
        finish();
    }

    public void b() {
        d();
        e();
        if (this.c != null) {
            if (this.h != null) {
                c(this.h);
            }
            this.c.a(false);
            this.c.i();
        }
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.c == null) {
            super.onBackPressed();
        } else if (this.c.j()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        try {
            getWindow().setFlags(1024, 1024);
        } catch (Throwable th) {
        }
        setTheme(R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        String stringExtra = getIntent().getStringExtra("com.applovin.interstitial.wrapper_id");
        if (stringExtra == null || stringExtra.isEmpty()) {
            a("Wrapper ID is null");
        } else {
            this.c = com.applovin.impl.adview.e.a(stringExtra);
            if (this.c == null && f160a != null) {
                this.c = f160a;
            }
            if (this.c != null) {
                com.applovin.a.a aVarC = this.c.c();
                this.g = (com.applovin.impl.a.d) this.c.b();
                this.e = this.c.b().f();
                this.f = new com.applovin.impl.a.n(this.c.b());
                if (aVarC != null) {
                    Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                    int iA = a(defaultDisplay);
                    int rotation = defaultDisplay.getRotation();
                    boolean z = (iA == 2 && rotation == 0) || (iA == 2 && rotation == 2) || ((iA == 1 && rotation == 1) || (iA == 1 && rotation == 3));
                    if (this.c.h() == com.applovin.impl.a.b.ACTIVITY_PORTRAIT) {
                        if (z) {
                            if (rotation != 1 && rotation != 3) {
                                this.d = true;
                                setRequestedOrientation(1);
                            }
                        } else if (rotation != 0 && rotation != 2) {
                            this.d = true;
                            setRequestedOrientation(1);
                        }
                    } else if (z) {
                        if (rotation != 0 && rotation != 2) {
                            this.d = true;
                            setRequestedOrientation(0);
                        }
                    } else if (rotation != 1 && rotation != 3) {
                        this.d = true;
                        setRequestedOrientation(0);
                    }
                    this.b = new AppLovinAdView(com.applovin.a.f.c, this);
                    this.b.setAutoDestroy(false);
                    this.c.a(this);
                } else {
                    a("No current ad found.");
                }
            } else {
                a("Wrapper is null; initialized state: " + Boolean.toString(com.applovin.impl.adview.e.f231a));
            }
        }
        SharedPreferences.Editor editorEdit = q().edit();
        editorEdit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        editorEdit.commit();
        d();
        a();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            if (this.b != null) {
                this.b.b();
            }
            if (this.q != null) {
                this.q.pause();
                this.q.stopPlayback();
            }
        } catch (Throwable th) {
            this.e.a("AppLovinInterstitialActivity", "Unable to destroy video view", th);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (!this.d) {
            SharedPreferences.Editor editorEdit = q().edit();
            editorEdit.putInt("com.applovin.interstitial.last_video_position", this.q.getCurrentPosition());
            editorEdit.putBoolean("com.applovin.interstitial.should_resume_video", true);
            editorEdit.commit();
            this.s.clearAnimation();
            this.p.removeView(this.s);
            this.p.removeView(this.t);
            this.q.pause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferencesQ = q();
        if (sharedPreferencesQ.getBoolean("com.applovin.interstitial.should_resume_video", false)) {
            if (this.q != null) {
                int duration = this.q.getDuration();
                int i = sharedPreferencesQ.getInt("com.applovin.interstitial.last_video_position", duration);
                m();
                n();
                h();
                this.q.seekTo(i);
                this.q.start();
                a(duration, i, duration - i);
            }
            if (this.r == null || !this.f.h()) {
                b();
            } else {
                a(0L);
            }
        }
    }
}
