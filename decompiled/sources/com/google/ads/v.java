package com.google.ads;

import android.webkit.WebView;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class v implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        com.google.ads.util.b.e("Invalid " + map.get(ServerProtocol.DIALOG_PARAM_TYPE) + " request error: " + map.get("errors"));
        com.google.ads.internal.c cVarK = dVar.k();
        if (cVarK != null) {
            cVarK.a(AdRequest.ErrorCode.INVALID_REQUEST);
        }
    }
}
