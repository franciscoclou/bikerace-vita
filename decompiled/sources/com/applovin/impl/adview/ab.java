package com.applovin.impl.adview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewParent;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.adview.AppLovinAdView;
import com.facebook.internal.NativeProtocol;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ab extends WebViewClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.applovin.a.k f221a;
    private final com.applovin.a.j b;
    private a c;

    public ab(a aVar, com.applovin.a.k kVar) {
        this.f221a = kVar;
        this.b = kVar.f();
        this.c = aVar;
    }

    private void a(Uri uri, y yVar) {
        try {
            yVar.getContext().startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (Throwable th) {
            this.b.b("AdWebViewClient", "Unable to show \"" + uri + "\".", th);
        }
    }

    private void c(y yVar) {
        com.applovin.a.a aVarA = yVar.a();
        if (aVarA != null) {
            this.c.b(aVarA);
        }
    }

    void a(WebView webView, String str) {
        this.b.a("AdWebViewClient", "Processing click on ad URL \"" + str + "\"");
        if (str == null || !(webView instanceof y)) {
            return;
        }
        Uri uri = Uri.parse(str);
        y yVar = (y) webView;
        String scheme = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();
        if (!"applovin".equals(scheme) || !"com.applovin.sdk".equals(host)) {
            c(yVar);
            a(uri, yVar);
            return;
        }
        if ("/adservice/next_ad".equals(path)) {
            a(yVar);
            return;
        }
        if ("/adservice/close_ad".equals(path)) {
            b(yVar);
            return;
        }
        if (path.startsWith("/launch/")) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() > 1) {
                String str2 = pathSegments.get(pathSegments.size() - 1);
                try {
                    Context context = webView.getContext();
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str2));
                    c(yVar);
                    return;
                } catch (Exception e) {
                    this.b.b("AdWebViewClient", "Threw Exception Trying to Launch App for Package: " + str2, e);
                    return;
                }
            }
            return;
        }
        if (!path.contains("/openurl")) {
            this.b.c("AdWebViewClient", "Unknown URL: " + str);
            this.b.c("AdWebViewClient", "Path: " + path);
        } else if (uri.getPathSegments().size() > 0) {
            String queryParameter = uri.getQueryParameter(NativeProtocol.IMAGE_URL_KEY);
            if (str == null || !URLUtil.isValidUrl(queryParameter)) {
                this.b.d("AdWebViewClient", "SDK was asked to launch invalid URL externally:" + queryParameter);
            } else {
                webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(queryParameter)));
            }
        }
    }

    protected void a(y yVar) {
        ViewParent parent = yVar.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView) parent).a();
        }
    }

    protected void b(y yVar) {
        this.c.b(yVar);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.c.a(webView);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a(webView, str);
        return true;
    }
}
