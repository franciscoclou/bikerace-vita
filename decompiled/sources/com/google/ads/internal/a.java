package com.google.ads.internal;

import android.net.Uri;
import android.webkit.WebView;
import com.facebook.internal.NativeProtocol;
import com.google.ads.AdActivity;
import com.google.ads.aa;
import com.google.ads.ab;
import com.google.ads.ac;
import com.google.ads.q;
import com.google.ads.r;
import com.google.ads.s;
import com.google.ads.t;
import com.google.ads.u;
import com.google.ads.util.AdUtil;
import com.google.ads.v;
import com.google.ads.w;
import com.google.ads.x;
import com.google.ads.y;
import com.google.ads.z;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    private static final a e = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.ads.util.f<a> f669a = new com.google.ads.util.f<a>() { // from class: com.google.ads.internal.a.2
        @Override // com.google.ads.util.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return a.e;
        }
    };
    public static final Map<String, com.google.ads.o> b = Collections.unmodifiableMap(new HashMap<String, com.google.ads.o>() { // from class: com.google.ads.internal.a.3
        {
            put("/invalidRequest", new v());
            put("/loadAdURL", new w());
            put("/loadSdkConstants", new x());
            put("/log", new y());
        }
    });
    public static final Map<String, com.google.ads.o> c = Collections.unmodifiableMap(new HashMap<String, com.google.ads.o>() { // from class: com.google.ads.internal.a.4
        {
            put("/log", new y());
            put("/setNativeActivationOverlay", new aa());
        }
    });
    public static final Map<String, com.google.ads.o> d = Collections.unmodifiableMap(new HashMap<String, com.google.ads.o>() { // from class: com.google.ads.internal.a.1
        {
            put("/open", new z());
            put("/canOpenURLs", new q());
            put("/close", new s());
            put("/customClose", new t());
            put("/appEvent", new com.google.ads.p());
            put("/log", new y());
            put("/click", new r());
            put("/httpTrack", new u());
            put("/touch", new ab());
            put("/video", new ac());
        }
    });

    public String a(Uri uri, HashMap<String, String> map) {
        if (c(uri)) {
            String host = uri.getHost();
            if (host == null) {
                com.google.ads.util.b.e("An error occurred while parsing the AMSG parameters.");
                return null;
            }
            if (host.equals("launch")) {
                map.put("a", "intent");
                map.put(AdActivity.URL_PARAM, map.get(NativeProtocol.IMAGE_URL_KEY));
                map.remove(NativeProtocol.IMAGE_URL_KEY);
                return "/open";
            }
            if (host.equals("closecanvas")) {
                return "/close";
            }
            if (host.equals("log")) {
                return "/log";
            }
            com.google.ads.util.b.e("An error occurred while parsing the AMSG: " + uri.toString());
            return null;
        }
        if (b(uri)) {
            return uri.getPath();
        }
        com.google.ads.util.b.e("Message was neither a GMSG nor an AMSG.");
        return null;
    }

    public void a(d dVar, Map<String, com.google.ads.o> map, Uri uri, WebView webView) {
        HashMap<String, String> mapB = AdUtil.b(uri);
        if (mapB == null) {
            com.google.ads.util.b.e("An error occurred while parsing the message parameters.");
            return;
        }
        String strA = a(uri, mapB);
        if (strA == null) {
            com.google.ads.util.b.e("An error occurred while parsing the message.");
            return;
        }
        com.google.ads.o oVar = map.get(strA);
        if (oVar == null) {
            com.google.ads.util.b.e("No AdResponse found, <message: " + strA + ">");
        } else {
            oVar.a(dVar, mapB, webView);
        }
    }

    public boolean a(Uri uri) {
        if (uri == null || !uri.isHierarchical()) {
            return false;
        }
        return b(uri) || c(uri);
    }

    public boolean b(Uri uri) {
        String authority;
        String scheme = uri.getScheme();
        return scheme != null && scheme.equals("gmsg") && (authority = uri.getAuthority()) != null && authority.equals("mobileads.google.com");
    }

    public boolean c(Uri uri) {
        String scheme = uri.getScheme();
        return scheme != null && scheme.equals("admob");
    }

    public void a(WebView webView, String str, String str2) {
        if (str2 != null) {
            a(webView, "AFMA_ReceiveMessage('" + str + "', " + str2 + ");");
        } else {
            a(webView, "AFMA_ReceiveMessage('" + str + "');");
        }
    }

    public void a(WebView webView, String str) {
        com.google.ads.util.b.a("Sending JS to a WebView: " + str);
        webView.loadUrl("javascript:" + str);
    }

    public void a(WebView webView, Map<String, Boolean> map) {
        a(webView, "openableURLs", new JSONObject(map).toString());
    }

    public void a(WebView webView) {
        a(webView, "onshow", "{'version': 'afma-sdk-a-v6.4.1'}");
    }

    public void b(WebView webView) {
        a(webView, "onhide", null);
    }
}
