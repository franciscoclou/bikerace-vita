package com.google.ads.internal;

import android.app.Activity;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdVideoView extends FrameLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final a b = a.f669a.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MediaController f665a;
    private final WeakReference<Activity> c;
    private final AdWebView d;
    private long e;
    private final VideoView f;
    private String g;

    public AdVideoView(Activity activity, AdWebView adWebView) {
        super(activity);
        this.c = new WeakReference<>(activity);
        this.d = adWebView;
        this.f = new VideoView(activity);
        addView(this.f, new FrameLayout.LayoutParams(-1, -1, 17));
        this.f665a = null;
        this.g = null;
        this.e = 0L;
        a();
        this.f.setOnCompletionListener(this);
        this.f.setOnPreparedListener(this);
        this.f.setOnErrorListener(this);
    }

    protected void a() {
        new l(this).a();
    }

    public void b() {
        if (!TextUtils.isEmpty(this.g)) {
            this.f.setVideoPath(this.g);
        } else {
            b.a(this.d, "onVideoEvent", "{'event': 'error', 'what': 'no_src'}");
        }
    }

    public void setMediaControllerEnabled(boolean z) {
        Activity activity = this.c.get();
        if (activity == null) {
            com.google.ads.util.b.e("adActivity was null while trying to enable controls on a video.");
            return;
        }
        if (z) {
            if (this.f665a == null) {
                this.f665a = new MediaController(activity);
            }
            this.f.setMediaController(this.f665a);
        } else {
            if (this.f665a != null) {
                this.f665a.hide();
            }
            this.f.setMediaController(null);
        }
    }

    public void setSrc(String str) {
        this.g = str;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        b.a(this.d, "onVideoEvent", "{'event': 'ended'}");
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        com.google.ads.util.b.e("Video threw error! <what:" + i + ", extra:" + i2 + ">");
        b.a(this.d, "onVideoEvent", "{'event': 'error', 'what': '" + i + "', 'extra': '" + i2 + "'}");
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        b.a(this.d, "onVideoEvent", "{'event': 'canplaythrough', 'duration': '" + (this.f.getDuration() / 1000.0f) + "'}");
    }

    public void c() {
        this.f.pause();
    }

    public void d() {
        this.f.start();
    }

    public void a(int i) {
        this.f.seekTo(i);
    }

    public void a(MotionEvent motionEvent) {
        this.f.onTouchEvent(motionEvent);
    }

    public void e() {
        this.f.stopPlayback();
    }

    void f() {
        long currentPosition = this.f.getCurrentPosition();
        if (this.e != currentPosition) {
            b.a(this.d, "onVideoEvent", "{'event': 'timeupdate', 'time': " + (currentPosition / 1000.0f) + "}");
            this.e = currentPosition;
        }
    }
}
