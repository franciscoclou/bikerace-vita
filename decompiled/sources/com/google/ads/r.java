package com.google.ads;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class r implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        Uri uriA;
        Uri uri;
        String host;
        String str = map.get(AdActivity.URL_PARAM);
        if (str == null) {
            com.google.ads.util.b.e("Could not get URL from click gmsg.");
            return;
        }
        com.google.ads.internal.g gVarN = dVar.n();
        if (gVarN != null && (host = (uri = Uri.parse(str)).getHost()) != null && host.toLowerCase(Locale.US).endsWith(".admob.com")) {
            String str2 = null;
            String path = uri.getPath();
            if (path != null) {
                String[] strArrSplit = path.split("/");
                if (strArrSplit.length >= 4) {
                    str2 = strArrSplit[2] + "/" + strArrSplit[3];
                }
            }
            gVarN.a(str2);
        }
        n nVarI = dVar.i();
        Context contextA = nVarI.f.a();
        Uri uri2 = Uri.parse(str);
        try {
            al alVarA = nVarI.s.a();
            uriA = (alVarA == null || !alVarA.a(uri2)) ? uri2 : alVarA.a(uri2, contextA);
        } catch (am e) {
            com.google.ads.util.b.e("Unable to append parameter to URL: " + str);
        }
        new Thread(new ae(uriA.toString(), contextA)).start();
    }
}
