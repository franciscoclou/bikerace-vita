package com.google.ads;

import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.google.ads.util.AdUtil;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class w implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        com.google.ads.internal.c.d dVar2;
        String strReplaceAll = map.get(NativeProtocol.IMAGE_URL_KEY);
        String str = map.get(ServerProtocol.DIALOG_PARAM_TYPE);
        String str2 = map.get("afma_notify_dt");
        String str3 = map.get("activation_overlay_url");
        String str4 = map.get("check_packages");
        boolean zEquals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("drt_include"));
        String str5 = map.get("request_scenario");
        boolean zEquals2 = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("use_webview_loadurl"));
        if (com.google.ads.internal.c.d.OFFLINE_EMPTY.e.equals(str5)) {
            dVar2 = com.google.ads.internal.c.d.OFFLINE_EMPTY;
        } else if (com.google.ads.internal.c.d.OFFLINE_USING_BUFFERED_ADS.e.equals(str5)) {
            dVar2 = com.google.ads.internal.c.d.OFFLINE_USING_BUFFERED_ADS;
        } else if (com.google.ads.internal.c.d.ONLINE_USING_BUFFERED_ADS.e.equals(str5)) {
            dVar2 = com.google.ads.internal.c.d.ONLINE_USING_BUFFERED_ADS;
        } else {
            dVar2 = com.google.ads.internal.c.d.ONLINE_SERVER_REQUEST;
        }
        com.google.ads.util.b.c("Received ad url: <url: \"" + strReplaceAll + "\" type: \"" + str + "\" afmaNotifyDt: \"" + str2 + "\" activationOverlayUrl: \"" + str3 + "\" useWebViewLoadUrl: \"" + zEquals2 + "\">");
        if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(strReplaceAll)) {
            BigInteger bigInteger = new BigInteger(new byte[1]);
            String[] strArrSplit = str4.split(",");
            BigInteger bit = bigInteger;
            for (int i = 0; i < strArrSplit.length; i++) {
                if (AdUtil.a(dVar.i().c.a(), strArrSplit[i])) {
                    bit = bit.setBit(i);
                }
            }
            String str6 = String.format(Locale.US, "%X", bit);
            strReplaceAll = strReplaceAll.replaceAll("%40installed_markets%40", str6);
            m.a().f697a.a(str6);
            com.google.ads.util.b.c("Ad url modified to " + strReplaceAll);
        }
        com.google.ads.internal.c cVarK = dVar.k();
        if (cVarK != null) {
            cVarK.d(zEquals);
            cVarK.a(dVar2);
            cVarK.e(zEquals2);
            cVarK.e(str3);
            cVarK.d(strReplaceAll);
        }
    }
}
