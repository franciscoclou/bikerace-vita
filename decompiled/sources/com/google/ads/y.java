package com.google.ads;

import android.webkit.WebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class y implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        com.google.ads.util.b.c("Received log message: <\"string\": \"" + map.get("string") + "\", \"afmaNotifyDt\": \"" + map.get("afma_notify_dt") + "\">");
    }
}
