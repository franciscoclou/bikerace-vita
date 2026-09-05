package com.google.ads;

import android.app.Activity;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.util.AdUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ac implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.ads.internal.a f618a = com.google.ads.internal.a.f669a.b();

    protected int a(HashMap<String, String> map, String str, int i, DisplayMetrics displayMetrics) {
        String str2 = map.get(str);
        if (str2 != null) {
            try {
                return (int) TypedValue.applyDimension(1, Integer.parseInt(str2), displayMetrics);
            } catch (NumberFormatException e) {
                com.google.ads.util.b.a("Could not parse \"" + str + "\" in a video gmsg: " + str2);
                return i;
            }
        }
        return i;
    }

    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        String str = map.get("action");
        if (str == null) {
            com.google.ads.util.b.a("No \"action\" parameter in a video gmsg.");
            return;
        }
        if (webView instanceof AdWebView) {
            AdWebView adWebView = (AdWebView) webView;
            AdActivity adActivityI = adWebView.i();
            if (adActivityI == null) {
                com.google.ads.util.b.a("Could not get adActivity for a video gmsg.");
                return;
            }
            boolean zEquals = str.equals("new");
            boolean zEquals2 = str.equals("position");
            if (zEquals || zEquals2) {
                DisplayMetrics displayMetricsA = AdUtil.a((Activity) adActivityI);
                int iA = a(map, "x", 0, displayMetricsA);
                int iA2 = a(map, "y", 0, displayMetricsA);
                int iA3 = a(map, "w", -1, displayMetricsA);
                int iA4 = a(map, "h", -1, displayMetricsA);
                if (zEquals && adActivityI.getAdVideoView() == null) {
                    adActivityI.newAdVideoView(iA, iA2, iA3, iA4);
                    return;
                } else {
                    adActivityI.moveAdVideoView(iA, iA2, iA3, iA4);
                    return;
                }
            }
            AdVideoView adVideoView = adActivityI.getAdVideoView();
            if (adVideoView == null) {
                f618a.a(adWebView, "onVideoEvent", "{'event': 'error', 'what': 'no_video_view'}");
                return;
            }
            if (str.equals("click")) {
                DisplayMetrics displayMetricsA2 = AdUtil.a((Activity) adActivityI);
                int iA5 = a(map, "x", 0, displayMetricsA2);
                int iA6 = a(map, "y", 0, displayMetricsA2);
                long jUptimeMillis = SystemClock.uptimeMillis();
                adVideoView.a(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, iA5, iA6, 0));
                return;
            }
            if (str.equals("controls")) {
                String str2 = map.get("enabled");
                if (str2 == null) {
                    com.google.ads.util.b.a("No \"enabled\" parameter in a controls video gmsg.");
                    return;
                } else if (str2.equals("true")) {
                    adVideoView.setMediaControllerEnabled(true);
                    return;
                } else {
                    adVideoView.setMediaControllerEnabled(false);
                    return;
                }
            }
            if (str.equals("currentTime")) {
                String str3 = map.get("time");
                if (str3 == null) {
                    com.google.ads.util.b.a("No \"time\" parameter in a currentTime video gmsg.");
                    return;
                }
                try {
                    adVideoView.a((int) (Float.parseFloat(str3) * 1000.0f));
                    return;
                } catch (NumberFormatException e) {
                    com.google.ads.util.b.a("Could not parse \"time\" parameter: " + str3);
                    return;
                }
            }
            if (str.equals("hide")) {
                adVideoView.setVisibility(4);
                return;
            }
            if (str.equals("load")) {
                adVideoView.b();
                return;
            }
            if (str.equals("pause")) {
                adVideoView.c();
                return;
            }
            if (str.equals("play")) {
                adVideoView.d();
                return;
            }
            if (str.equals("show")) {
                adVideoView.setVisibility(0);
                return;
            } else if (str.equals("src")) {
                adVideoView.setSrc(map.get("src"));
                return;
            } else {
                com.google.ads.util.b.a("Unknown video action: " + str);
                return;
            }
        }
        com.google.ads.util.b.a("Could not get adWebView for a video gmsg.");
    }
}
