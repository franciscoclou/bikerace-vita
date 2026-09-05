package com.google.ads;

import android.text.TextUtils;
import android.webkit.WebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class u implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        String str = map.get(AdActivity.URL_PARAM);
        if (TextUtils.isEmpty(str)) {
            com.google.ads.util.b.e("Could not get URL from track gmsg.");
        } else {
            new Thread(new ae(str, dVar.i().f.a())).start();
        }
    }
}
