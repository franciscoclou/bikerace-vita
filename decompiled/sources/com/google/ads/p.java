package com.google.ads;

import android.webkit.WebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class p implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        String str = map.get("name");
        if (str == null) {
            com.google.ads.util.b.b("Error: App event with no name parameter.");
        } else {
            dVar.a(str, map.get("info"));
        }
    }
}
