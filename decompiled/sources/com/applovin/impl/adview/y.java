package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Rect;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class y extends WebView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.applovin.a.j f250a;
    private com.applovin.a.a b;
    private boolean c;

    y(ab abVar, com.applovin.a.k kVar, Context context) {
        super(context);
        this.b = null;
        this.c = false;
        this.f250a = kVar.f();
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        setWebViewClient(abVar);
        setWebChromeClient(new x(kVar));
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setScrollBarStyle(33554432);
        setOnTouchListener(new z(this));
        setOnLongClickListener(new aa(this));
    }

    com.applovin.a.a a() {
        return this.b;
    }

    public void a(com.applovin.a.a aVar) {
        if (this.c) {
            this.f250a.e("AdWebView", "Ad can not be loaded in a destroyed web view");
            return;
        }
        this.b = aVar;
        try {
            loadDataWithBaseURL("/", aVar.f(), "text/html", null, "");
            this.f250a.a("AdWebView", "AppLovinAd rendered");
        } catch (Exception e) {
        }
    }

    @Override // android.webkit.WebView
    public void destroy() {
        this.c = true;
        try {
            super.destroy();
            this.f250a.a("AdWebView", "Web view destroyed");
        } catch (Throwable th) {
            if (this.f250a != null) {
                this.f250a.b("AdWebView", "destroy() threw exception", th);
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            super.onFocusChanged(z, i, rect);
        } catch (Exception e) {
            this.f250a.b("AdWebView", "onFocusChanged() threw exception", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    @Override // android.webkit.WebView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Exception e) {
            this.f250a.b("AdWebView", "onWindowFocusChanged() threw exception", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onWindowVisibilityChanged(int i) {
        try {
            super.onWindowVisibilityChanged(i);
        } catch (Exception e) {
            this.f250a.b("AdWebView", "onWindowVisibilityChanged() threw exception", e);
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, Rect rect) {
        try {
            return super.requestFocus(i, rect);
        } catch (Exception e) {
            this.f250a.b("AdWebView", "requestFocus() threw exception", e);
            return false;
        }
    }
}
