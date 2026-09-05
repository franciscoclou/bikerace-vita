package com.google.ads.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdActivity;
import com.google.ads.AdSize;
import com.google.ads.ak;
import com.google.ads.util.AdUtil;
import com.google.ads.util.IcsUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdWebView extends WebView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final com.google.ads.n f666a;
    private WeakReference<AdActivity> b;
    private AdSize c;
    private boolean d;
    private boolean e;
    private boolean f;

    public AdWebView(com.google.ads.n nVar, AdSize adSize) {
        super(nVar.f.a());
        this.f666a = nVar;
        this.c = adSize;
        this.b = null;
        this.d = false;
        this.e = false;
        this.f = false;
        setBackgroundColor(0);
        AdUtil.a(this);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        setDownloadListener(new DownloadListener() { // from class: com.google.ads.internal.AdWebView.1
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(Uri.parse(str), str4);
                    AdActivity adActivityI = AdWebView.this.i();
                    if (adActivityI != null && AdUtil.a(intent, adActivityI)) {
                        adActivityI.startActivity(intent);
                    }
                } catch (ActivityNotFoundException e) {
                    com.google.ads.util.b.a("Couldn't find an Activity to view url/mimetype: " + str + " / " + str4);
                } catch (Throwable th) {
                    com.google.ads.util.b.b("Unknown error trying to start activity to view URL: " + str, th);
                }
            }
        });
        if (AdUtil.f712a >= 17) {
            com.google.ads.util.h.a(settings, nVar);
        } else if (AdUtil.f712a >= 11) {
            com.google.ads.util.g.a(settings, nVar);
        }
        setScrollBarStyle(33554432);
        if (AdUtil.f712a >= 14) {
            setWebChromeClient(new IcsUtil.a(nVar));
        } else if (AdUtil.f712a >= 11) {
            setWebChromeClient(new com.google.ads.util.g.a(nVar));
        }
    }

    public void f() {
        AdActivity adActivityI = i();
        if (adActivityI != null) {
            adActivityI.finish();
        }
    }

    public void g() {
        if (AdUtil.f712a >= 11) {
            com.google.ads.util.g.a(this);
        }
        this.e = true;
    }

    public void h() {
        if (this.e && AdUtil.f712a >= 11) {
            com.google.ads.util.g.b(this);
        }
        this.e = false;
    }

    public AdActivity i() {
        if (this.b != null) {
            return this.b.get();
        }
        return null;
    }

    public boolean j() {
        return this.f;
    }

    public boolean k() {
        return this.e;
    }

    public void setAdActivity(AdActivity adActivity) {
        this.b = new WeakReference<>(adActivity);
    }

    @Override // android.webkit.WebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        try {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } catch (Throwable th) {
            com.google.ads.util.b.d("An error occurred while loading data in AdWebView:", th);
        }
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str) {
        try {
            super.loadUrl(str);
        } catch (Throwable th) {
            com.google.ads.util.b.d("An error occurred while loading a URL in AdWebView:", th);
        }
    }

    @Override // android.webkit.WebView
    public void stopLoading() {
        try {
            super.stopLoading();
        } catch (Throwable th) {
            com.google.ads.util.b.d("An error occurred while stopping loading in AdWebView:", th);
        }
    }

    @Override // android.webkit.WebView
    public void destroy() {
        try {
            super.destroy();
        } catch (Throwable th) {
            com.google.ads.util.b.d("An error occurred while destroying an AdWebView:", th);
        }
        try {
            setWebViewClient(new WebViewClient());
        } catch (Throwable th2) {
        }
    }

    public synchronized void setAdSize(AdSize adSize) {
        this.c = adSize;
        requestLayout();
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        synchronized (this) {
            if (isInEditMode() || this.c == null || this.d) {
                super.onMeasure(i, i2);
            } else {
                int mode = View.MeasureSpec.getMode(i);
                int size = View.MeasureSpec.getSize(i);
                int mode2 = View.MeasureSpec.getMode(i2);
                int size2 = View.MeasureSpec.getSize(i2);
                float f = getContext().getResources().getDisplayMetrics().density;
                int width = (int) (this.c.getWidth() * f);
                int height = (int) (this.c.getHeight() * f);
                int i3 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
                int i4 = (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) ? size2 : Integer.MAX_VALUE;
                if (width - (f * 6.0f) > i3 || height > i4) {
                    com.google.ads.util.b.b("Not enough space to show ad! Wants: <" + width + ", " + height + ">, Has: <" + size + ", " + size2 + ">");
                    setVisibility(8);
                    setMeasuredDimension(size, size2);
                } else {
                    setMeasuredDimension(width, height);
                }
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ak akVarA = this.f666a.r.a();
        if (akVarA != null) {
            akVarA.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCustomClose(boolean z) {
        AdActivity adActivity;
        this.f = z;
        if (this.b != null && (adActivity = this.b.get()) != null) {
            adActivity.setCustomClose(z);
        }
    }

    public void setIsExpandedMraid(boolean z) {
        this.d = z;
    }

    public void a(boolean z) {
        if (z) {
            setOnTouchListener(new View.OnTouchListener() { // from class: com.google.ads.internal.AdWebView.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return motionEvent.getAction() == 2;
                }
            });
        } else {
            setOnTouchListener(null);
        }
    }
}
