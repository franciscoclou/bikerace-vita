package com.google.ads;

import android.webkit.WebView;
import com.facebook.AppEventsConstants;
import com.google.ads.internal.AdWebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class t implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        if (webView instanceof AdWebView) {
            ((AdWebView) webView).setCustomClose(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get(AdActivity.CUSTOM_CLOSE_PARAM)));
        } else {
            com.google.ads.util.b.b("Trying to set a custom close icon on a WebView that isn't an AdWebView");
        }
    }
}
