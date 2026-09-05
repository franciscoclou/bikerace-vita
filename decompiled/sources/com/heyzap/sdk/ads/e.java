package com.heyzap.sdk.ads;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: FullscreenVideoView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Boolean f780a;
    public Boolean b;
    public Boolean c;
    public Integer d;
    public Timer e;
    public ProgressDialog f;
    public int g;
    public SurfaceView h;
    private Context i;
    private b j;
    private MediaPlayer k;
    private f l;
    private int m;
    private int n;
    private x o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Context context, b bVar) {
        super(context);
        AnonymousClass1 anonymousClass1 = null;
        this.f780a = false;
        this.b = true;
        this.c = true;
        this.d = 1;
        this.g = 0;
        this.m = 0;
        this.n = 0;
        this.i = context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.j = bVar;
        this.e = new Timer();
        this.k = new MediaPlayer();
        this.l = new f(this);
        this.k.setOnBufferingUpdateListener(this.l);
        this.k.setOnCompletionListener(this.l);
        this.k.setOnErrorListener(this.l);
        this.k.setOnPreparedListener(this.l);
        this.k.setOnVideoSizeChangedListener(this.l);
        this.k.setScreenOnWhilePlaying(true);
        setBackgroundColor(0);
        this.h = new SurfaceView(context);
        if (Build.VERSION.SDK_INT < 11) {
            this.h.getHolder().setType(3);
        }
        this.h.getHolder().addCallback(new h(this));
        this.h.setVisibility(8);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.h, layoutParams);
        this.o = new x(getContext(), null);
        this.o.a(new g(this));
        addView(this.o, new FrameLayout.LayoutParams(-1, -1));
        a();
    }

    public void a() {
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        animationLoadAnimation.setDuration(150L);
        this.o.setVisibility(0);
        this.o.startAnimation(animationLoadAnimation);
    }

    public void b() {
        if (this.k == null || this.k.isPlaying()) {
            this.o.a(this.k.getDuration() - this.k.getCurrentPosition(), this.k.getCurrentPosition() / this.k.getDuration());
        }
    }

    @SuppressLint({"NewApi"})
    public void c() {
        if (this.j != null) {
            this.j.a();
        }
        setBackgroundColor(-16777216);
        this.m = this.k.getDuration();
        this.h.setVisibility(0);
        if (this.e != null) {
            this.e.purge();
            this.e = null;
        }
        this.e = new Timer();
        this.e.scheduleAtFixedRate(new TimerTask() { // from class: com.heyzap.sdk.ads.e.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                e.this.b();
            }
        }, 0L, 100L);
        if (this.f780a.booleanValue()) {
            if (this.d.intValue() > 0) {
                this.o.a((Boolean) true, this.d.intValue());
                return;
            } else {
                this.o.a((Boolean) false, 0L);
                return;
            }
        }
        if (this.b.booleanValue()) {
            this.o.a();
        }
    }

    public void a(int i, int i2) {
        int i3;
        int i4 = -1;
        int width = ((Activity) getContext()).getWindowManager().getDefaultDisplay().getWidth();
        int height = ((Activity) getContext()).getWindowManager().getDefaultDisplay().getHeight();
        int videoHeight = this.k.getVideoHeight();
        int videoWidth = this.k.getVideoWidth();
        Float fValueOf = Float.valueOf(this.k.getVideoWidth() / this.k.getVideoHeight());
        if (videoWidth > videoHeight && fValueOf.floatValue() > 1.6d) {
            i3 = (int) ((i2 / i) * width);
        } else {
            i3 = -1;
            i4 = (int) (height * (i / i2));
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.h.getLayoutParams();
        layoutParams.width = i4;
        layoutParams.height = i3;
        this.h.setLayoutParams(layoutParams);
        this.o.setLayoutParams(layoutParams);
    }

    public Boolean a(Uri uri) {
        try {
            this.f = ProgressDialog.show(getContext(), "", "Loading...", true);
            this.k.setDataSource(this.i, uri);
            this.k.prepareAsync();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean a(String str) throws Throwable {
        boolean z;
        FileInputStream fileInputStream = null;
        try {
            try {
                File file = new File(u.f799a.getCacheDir() + "/" + str);
                if (!file.exists()) {
                    throw new Exception("File does not exist.");
                }
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    this.k.setDataSource(fileInputStream2.getFD());
                    this.k.prepareAsync();
                    z = true;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    e.printStackTrace();
                    if (this.j != null) {
                        this.j.f();
                    }
                    z = false;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public Boolean a(w wVar) {
        try {
            String strS = wVar.s();
            try {
                if (strS == null) {
                    throw new Exception("local");
                }
                if (!a(strS).booleanValue()) {
                    throw new Exception("load");
                }
                return true;
            } catch (Exception e) {
                if (!e.getMessage().equals("local")) {
                    e.printStackTrace();
                }
                com.heyzap.internal.k.b("Local file not found. Falling back to stream and cancelling download.");
                wVar.v();
                if (!a(wVar.t()).booleanValue()) {
                    return false;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.j != null) {
                this.j.f();
            }
        }
    }

    public void d() {
        if (this.e != null) {
            this.e.cancel();
            this.e.purge();
        }
        if (this.k != null) {
            this.k.reset();
            this.k.release();
            this.k = null;
        }
    }

    public void e() {
        if (this.k != null) {
            this.k.pause();
        }
    }

    public void a(Boolean bool) {
        if (this.e != null) {
            this.e.cancel();
            this.e.purge();
        }
        if (this.k != null && this.k.isPlaying()) {
            this.k.stop();
            this.k.reset();
        }
        d();
        if (bool.booleanValue() && this.j != null) {
            this.j.b();
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.k == null || !this.k.isPlaying()) {
                a((Boolean) true);
            }
            if (this.f780a.booleanValue() && this.k != null && this.k.isPlaying()) {
                if (this.d.intValue() != 0 && (this.d.intValue() <= 0 || this.k.getCurrentPosition() <= this.d.intValue())) {
                    return true;
                }
                a((Boolean) true);
                return true;
            }
            if (!this.b.booleanValue()) {
                return true;
            }
            a((Boolean) true);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void f() {
        this.k.seekTo(0);
    }

    public int g() {
        return this.n;
    }

    public int h() {
        return this.m;
    }
}
