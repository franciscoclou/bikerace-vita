package com.heyzap.sdk.ads;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: AbstractActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class a extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected c f762a;
    protected String b = null;
    protected String c = null;
    protected int d = 0;
    private AtomicBoolean f = new AtomicBoolean(false);
    protected Boolean e = false;

    public abstract Boolean a();

    public abstract View b();

    a() {
    }

    @Override // android.app.Activity
    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.requestWindowFeature(1);
        super.setTheme(R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        if (com.heyzap.internal.l.b() >= 11) {
            getWindow().setFlags(16777216, 16777216);
        }
        super.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if (!i.a().booleanValue()) {
            finish();
        } else {
            a(getIntent());
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        a(intent);
    }

    protected void a(Intent intent) {
        if (intent.getExtras() == null) {
            finish();
        }
        if (intent.getExtras().containsKey("action")) {
            switch (intent.getExtras().getInt("action")) {
                case 2:
                    if (this.f762a == null) {
                        finish();
                    } else {
                        c();
                    }
                    break;
                default:
                    this.c = intent.getStringExtra("impression_id");
                    this.d = intent.getIntExtra("ad_context", 0);
                    this.f762a = u.d().b(this.c);
                    if (this.f762a == null || this.f762a.h().booleanValue()) {
                        if (u.h != null) {
                            u.h.d(null);
                        }
                        finish();
                        return;
                    } else {
                        this.b = this.f762a.c();
                        f();
                        if (a().booleanValue()) {
                            setContentView(b());
                            e();
                        }
                    }
                    break;
            }
        }
        u.j = this;
    }

    @SuppressLint({"InlinedApi"})
    private void f() {
        int i = this.f762a.i();
        if (i != 0) {
            switch (i) {
                case 1:
                    if (com.heyzap.internal.l.b() > 8) {
                        setRequestedOrientation(7);
                    } else {
                        setRequestedOrientation(1);
                    }
                    break;
                case 2:
                    if (com.heyzap.internal.l.b() > 8) {
                        setRequestedOrientation(6);
                    } else {
                        setRequestedOrientation(0);
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x001c. Please report as an issue. */
    public void c() {
        if (this.f762a != null) {
            if (u.h != null) {
                u.h.c(this.f762a.c());
            }
            try {
                this.f762a.b();
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (this.d) {
            }
            u.d().c(this.f762a);
            this.f762a = null;
            this.c = null;
            this.b = null;
            finish();
            u.j = null;
        }
    }

    public void d() {
        a(this.f762a.c, (String) null);
    }

    public void a(String str, String str2) {
        if (this.f762a.a(str2).booleanValue()) {
            try {
                final ProgressDialog progressDialogShow = ProgressDialog.show(this, "", "Loading...", true);
                u.f.postDelayed(new Runnable() { // from class: com.heyzap.sdk.ads.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            progressDialogShow.dismiss();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                }, 3000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (u.h != null) {
                u.h.b(this.f762a.c());
            }
            a(str);
        }
    }

    public void e() {
        this.f762a.a();
        if (u.h != null) {
            u.h.a(this.f762a.c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        if (this.f.compareAndSet(false, true)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(131072);
            context.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context, String str) {
        if (com.heyzap.internal.l.a()) {
            if (str.startsWith("amzn")) {
                a(context, str);
                return true;
            }
            if (str.contains("amazon.com/gp/mas/dl/android?")) {
                a(context, "amzn://apps/" + str.substring(str.indexOf("android?")));
                return true;
            }
        } else {
            if (str.startsWith("market")) {
                a(context, str);
                return true;
            }
            if (str.contains("play.google")) {
                int iIndexOf = str.indexOf("details?");
                if (iIndexOf == -1) {
                    a(context, str);
                    return true;
                }
                a(context, "market://" + str.substring(iIndexOf));
                return true;
            }
        }
        return false;
    }

    protected void a(final String str) {
        this.f.set(false);
        if (!b(this, str)) {
            final WebView webView = new WebView(this);
            webView.setWebViewClient(new WebViewClient() { // from class: com.heyzap.sdk.ads.a.2
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView2, String str2) {
                    return super.shouldOverrideUrlLoading(webView2, str2);
                }

                @Override // android.webkit.WebViewClient
                public void onLoadResource(WebView webView2, String str2) {
                    super.onLoadResource(webView2, str2);
                    if (a.this.b(a.this, str2)) {
                        webView2.stopLoading();
                    }
                }

                @Override // android.webkit.WebViewClient
                public void onPageStarted(WebView webView2, String str2, Bitmap bitmap) {
                    super.onPageStarted(webView2, str2, bitmap);
                    if (a.this.b(a.this, str2)) {
                        webView2.stopLoading();
                    }
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedError(WebView webView2, int i, String str2, String str3) {
                    super.onReceivedError(webView2, i, str2, str3);
                }

                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView2, String str2) {
                    super.onPageFinished(webView2, str2);
                }
            });
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
            new Timer();
            webView.postDelayed(new Runnable() { // from class: com.heyzap.sdk.ads.a.3
                @Override // java.lang.Runnable
                public void run() {
                    a.this.runOnUiThread(new Runnable() { // from class: com.heyzap.sdk.ads.a.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            webView.loadUrl(str);
                        }
                    });
                }
            }, 250L);
            webView.postDelayed(new Runnable() { // from class: com.heyzap.sdk.ads.a.4
                @Override // java.lang.Runnable
                public void run() {
                    if (!a.this.f.get()) {
                        a.this.runOnUiThread(new Runnable() { // from class: com.heyzap.sdk.ads.a.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                webView.loadUrl(str);
                            }
                        });
                    }
                }
            }, 750L);
            webView.postDelayed(new Runnable() { // from class: com.heyzap.sdk.ads.a.5
                @Override // java.lang.Runnable
                public void run() {
                    if (!a.this.f.get()) {
                        a.this.a(a.this, str);
                    }
                }
            }, 1250L);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
