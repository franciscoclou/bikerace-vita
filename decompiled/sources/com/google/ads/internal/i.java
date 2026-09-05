package com.google.ads.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdActivity;
import com.google.ads.AdRequest;
import com.google.ads.al;
import com.google.ads.am;
import com.google.ads.util.AdUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i extends WebViewClient {
    private static final a c = a.f669a.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected d f681a;
    private final Map<String, com.google.ads.o> d;
    private final boolean e;
    private boolean f;
    private boolean g;
    protected boolean b = false;
    private boolean h = false;
    private boolean i = false;

    public i(d dVar, Map<String, com.google.ads.o> map, boolean z, boolean z2) {
        this.f681a = dVar;
        this.d = map;
        this.e = z;
        this.g = z2;
    }

    public static i a(d dVar, Map<String, com.google.ads.o> map, boolean z, boolean z2) {
        return AdUtil.f712a >= 11 ? new com.google.ads.util.g.b(dVar, map, z, z2) : new i(dVar, map, z, z2);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri uriA;
        try {
            com.google.ads.util.b.a("shouldOverrideUrlLoading(\"" + str + "\")");
            Uri uri = Uri.parse(str);
            if (c.a(uri)) {
                c.a(this.f681a, this.d, uri, webView);
                return true;
            }
            if (this.g) {
                if (AdUtil.a(uri)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                HashMap map = new HashMap();
                map.put(AdActivity.URL_PARAM, str);
                AdActivity.launchAdActivity(this.f681a, new e("intent", map));
                return true;
            }
            if (this.e) {
                try {
                    com.google.ads.n nVarI = this.f681a.i();
                    Context contextA = nVarI.f.a();
                    al alVarA = nVarI.s.a();
                    uriA = (alVarA == null || !alVarA.a(uri)) ? uri : alVarA.a(uri, contextA);
                } catch (am e) {
                    com.google.ads.util.b.e("Unable to append parameter to URL: " + str);
                }
                HashMap map2 = new HashMap();
                map2.put(AdActivity.URL_PARAM, uriA.toString());
                AdActivity.launchAdActivity(this.f681a, new e("intent", map2));
                return true;
            }
            com.google.ads.util.b.e("URL is not a GMSG and can't handle URL: " + str);
            return true;
        } catch (Throwable th) {
            com.google.ads.util.b.d("An unknown error occurred in shouldOverrideUrlLoading.", th);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f = true;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        this.f = false;
        if (this.h) {
            c cVarK = this.f681a.k();
            if (cVarK != null) {
                cVarK.c();
            } else {
                com.google.ads.util.b.a("adLoader was null while trying to setFinishedLoadingHtml().");
            }
            this.h = false;
        }
        if (this.i) {
            c.a(webView);
            this.i = false;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f = false;
        c cVarK = this.f681a.k();
        if (cVarK != null) {
            cVarK.a(AdRequest.ErrorCode.NETWORK_ERROR);
        }
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public void d(boolean z) {
        this.i = z;
    }

    public boolean a() {
        return this.f;
    }
}
