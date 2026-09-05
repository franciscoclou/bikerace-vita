package com.heyzap.sdk.ads;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

/* JADX INFO: compiled from: InterstitialWebView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t f792a;
    private b b;
    private Boolean c;

    public r(Context context, b bVar) {
        super(context);
        this.c = false;
        this.b = bVar;
        this.f792a = new t(this, context);
        addView(this.f792a);
        setFocusable(true);
        setFocusableInTouchMode(true);
        b();
        c();
    }

    public void a(o oVar) {
        a(oVar.j(), oVar.k(), oVar.l(), oVar.m());
    }

    public void a(w wVar) {
        a(wVar.j(), wVar.k(), wVar.l(), Integer.valueOf(wVar.m()));
    }

    public void a(final String str, final int i, final int i2, final Integer num) {
        final Activity activity = (Activity) this.f792a.getContext();
        activity.runOnUiThread(new Runnable() { // from class: com.heyzap.sdk.ads.r.1
            @Override // java.lang.Runnable
            public void run() {
                r.this.a(activity, i, i2);
                r.this.setBackgroundColor(num.intValue());
                r.this.f792a.b.loadDataWithBaseURL(null, str, "text/html", null, null);
            }
        });
    }

    public void a() {
        this.f792a.b.loadDataWithBaseURL(null, "<html></html>", "text/html", null, null);
    }

    private void b() {
        this.f792a.b.getSettings().setJavaScriptEnabled(true);
        this.f792a.b.getSettings().setLoadsImagesAutomatically(true);
        this.f792a.b.getSettings().setCacheMode(1);
        WebViewClient webViewClient = new WebViewClient() { // from class: com.heyzap.sdk.ads.r.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (r.this.b != null) {
                    if (str.contains("Heyzap.close")) {
                        r.this.b.b();
                        return true;
                    }
                    if (str.contains("Heyzap.restart")) {
                        r.this.b.g();
                        return true;
                    }
                    if (str.contains("Heyzap.installHeyzap")) {
                        r.this.b.d();
                        return true;
                    }
                    if (str.contains("Heyzap.clickAd")) {
                        r.this.b.c();
                        return true;
                    }
                    if (str.contains("Heyzap.clickManualAdUrl=")) {
                        int iIndexOf = str.indexOf("Heyzap.clickManualAdUrl=") + 24;
                        int iIndexOf2 = str.indexOf(":::");
                        r.this.b.a(str.substring(iIndexOf, iIndexOf2), str.substring(iIndexOf2 + 3));
                        return true;
                    }
                    return true;
                }
                return true;
            }
        };
        WebChromeClient webChromeClient = new WebChromeClient() { // from class: com.heyzap.sdk.ads.r.3
            @Override // android.webkit.WebChromeClient
            public void onConsoleMessage(String str, int i, String str2) {
                com.heyzap.internal.k.a("Console Message", str, Integer.valueOf(i), str2);
            }
        };
        this.f792a.b.setWebViewClient(webViewClient);
        this.f792a.b.setWebChromeClient(webChromeClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, int i, int i2) {
        Activity activity = (Activity) context;
        if (i == 0 && i2 == 0) {
            int iRound = Math.round(activity.getWindowManager().getDefaultDisplay().getWidth() * 0.98f);
            int iRound2 = Math.round(activity.getWindowManager().getDefaultDisplay().getHeight() * 0.98f);
            int iMin = Math.min(com.heyzap.internal.l.b(context, 360), iRound);
            int iMin2 = Math.min(com.heyzap.internal.l.b(context, 360), iRound2);
            i = Math.min(iMin, iMin2);
            i2 = Math.min(i, iMin2);
        }
        com.heyzap.internal.l.a(context, 10);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f792a.getLayoutParams();
        layoutParams.gravity = 17;
        layoutParams.width = i;
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }

    private void c() {
        this.f792a.b.setOnTouchListener(new View.OnTouchListener() { // from class: com.heyzap.sdk.ads.r.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (r.this.c.booleanValue() && motionEvent.getAction() == 0) {
                    if (r.this.b != null) {
                        r.this.b.c();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.b != null) {
            this.b.b();
        }
        return true;
    }
}
