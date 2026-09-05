package com.google.ads;

import android.webkit.WebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class z implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AdActivity.StaticMethodWrapper f730a;

    public z() {
        this(new AdActivity.StaticMethodWrapper());
    }

    public z(AdActivity.StaticMethodWrapper staticMethodWrapper) {
        this.f730a = staticMethodWrapper;
    }

    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        String str = map.get("a");
        if (str == null) {
            com.google.ads.util.b.a("Could not get the action parameter for open GMSG.");
            return;
        }
        if (str.equals("webapp")) {
            this.f730a.launchAdActivity(dVar, new com.google.ads.internal.e("webapp", map));
        } else if (str.equals("expand")) {
            this.f730a.launchAdActivity(dVar, new com.google.ads.internal.e("expand", map));
        } else {
            this.f730a.launchAdActivity(dVar, new com.google.ads.internal.e("intent", map));
        }
    }
}
