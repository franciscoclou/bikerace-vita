package com.google.ads;

import android.webkit.WebView;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ab implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        if (dVar.i().c.a() == null) {
            com.google.ads.util.b.e("Activity was null while responding to touch gmsg.");
            return;
        }
        String str = map.get("tx");
        String str2 = map.get("ty");
        String str3 = map.get("td");
        try {
            int i = Integer.parseInt(str);
            int i2 = Integer.parseInt(str2);
            int i3 = Integer.parseInt(str3);
            ak akVarA = dVar.i().r.a();
            if (akVarA != null) {
                akVarA.a(i, i2, i3);
            }
        } catch (NumberFormatException e) {
            com.google.ads.util.b.e("Could not parse touch parameters from gmsg.");
        }
    }
}
