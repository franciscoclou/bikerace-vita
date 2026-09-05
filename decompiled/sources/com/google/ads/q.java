package com.google.ads;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.ads.internal.a f709a = com.google.ads.internal.a.f669a.b();

    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        String str = map.get("urls");
        if (str == null) {
            com.google.ads.util.b.e("Could not get the urls param from canOpenURLs gmsg.");
            return;
        }
        String[] strArrSplit = str.split(",");
        HashMap map2 = new HashMap();
        PackageManager packageManager = webView.getContext().getPackageManager();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(";", 2);
            map2.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(strArrSplit2.length >= 2 ? strArrSplit2[1] : "android.intent.action.VIEW", Uri.parse(strArrSplit2[0])), NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST) != null));
        }
        f709a.a(webView, map2);
    }
}
