package com.google.ads;

import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.AppEventsConstants;
import com.google.ads.internal.ActivationOverlay;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aa implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        if (webView instanceof ActivationOverlay) {
            try {
                int i = !TextUtils.isEmpty(map.get("w")) ? Integer.parseInt(map.get("w")) : -1;
                int i2 = !TextUtils.isEmpty(map.get("h")) ? Integer.parseInt(map.get("h")) : -1;
                int i3 = !TextUtils.isEmpty(map.get("x")) ? Integer.parseInt(map.get("x")) : -1;
                int i4 = TextUtils.isEmpty(map.get("y")) ? -1 : Integer.parseInt(map.get("y"));
                if (map.get("a") != null && map.get("a").equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                    dVar.a(null, true, i3, i4, i, i2);
                    return;
                } else if (map.get("a") != null && map.get("a").equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                    dVar.a(null, false, i3, i4, i, i2);
                    return;
                } else {
                    dVar.a(i3, i4, i, i2);
                    return;
                }
            } catch (NumberFormatException e) {
                com.google.ads.util.b.d("Invalid number format in activation overlay response.", e);
                return;
            }
        }
        com.google.ads.util.b.b("Trying to activate an overlay when this is not an overlay.");
    }
}
