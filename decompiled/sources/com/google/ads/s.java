package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        if (webView instanceof AdWebView) {
            ((AdWebView) webView).f();
        } else {
            com.google.ads.util.b.b("Trying to close WebView that isn't an AdWebView");
        }
    }
}
