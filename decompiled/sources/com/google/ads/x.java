package com.google.ads;

import android.text.TextUtils;
import android.webkit.WebView;
import com.google.ads.internal.ActivationOverlay;
import com.google.ads.internal.AdWebView;
import com.google.ads.util.AdUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class x implements o {
    @Override // com.google.ads.o
    public void a(com.google.ads.internal.d dVar, HashMap<String, String> map, WebView webView) {
        n nVarI = dVar.i();
        m.a aVarA = nVarI.d.a().b.a();
        c(map, "as_domains", aVarA.f698a);
        c(map, "bad_ad_report_path", aVarA.h);
        a(map, "min_hwa_banner", aVarA.b);
        a(map, "min_hwa_activation_overlay", aVarA.c);
        a(map, "min_hwa_overlay", aVarA.d);
        c(map, "mraid_banner_path", aVarA.e);
        c(map, "mraid_expanded_banner_path", aVarA.f);
        c(map, "mraid_interstitial_path", aVarA.g);
        b(map, "ac_max_size", aVarA.i);
        b(map, "ac_padding", aVarA.j);
        b(map, "ac_total_quota", aVarA.k);
        b(map, "db_total_quota", aVarA.l);
        b(map, "db_quota_per_origin", aVarA.m);
        b(map, "db_quota_step_size", aVarA.n);
        AdWebView adWebViewL = dVar.l();
        if (AdUtil.f712a >= 11) {
            com.google.ads.util.g.a(adWebViewL.getSettings(), nVarI);
            com.google.ads.util.g.a(webView.getSettings(), nVarI);
        }
        if (!nVarI.g.a().a()) {
            boolean zK = adWebViewL.k();
            boolean z = AdUtil.f712a < aVarA.b.a().intValue();
            if (!z && zK) {
                com.google.ads.util.b.a("Re-enabling hardware acceleration for a banner after reading constants.");
                adWebViewL.h();
            } else if (z && !zK) {
                com.google.ads.util.b.a("Disabling hardware acceleration for a banner after reading constants.");
                adWebViewL.g();
            }
        }
        ActivationOverlay activationOverlayA = nVarI.e.a();
        if (!nVarI.g.a().b() && activationOverlayA != null) {
            boolean zK2 = activationOverlayA.k();
            boolean z2 = AdUtil.f712a < aVarA.c.a().intValue();
            if (!z2 && zK2) {
                com.google.ads.util.b.a("Re-enabling hardware acceleration for an activation overlay after reading constants.");
                activationOverlayA.h();
            } else if (z2 && !zK2) {
                com.google.ads.util.b.a("Disabling hardware acceleration for an activation overlay after reading constants.");
                activationOverlayA.g();
            }
        }
        String strA = aVarA.f698a.a();
        al alVarA = nVarI.s.a();
        if (alVarA != null && !TextUtils.isEmpty(strA)) {
            alVarA.a(strA);
        }
        aVarA.o.a(true);
    }

    private void a(HashMap<String, String> map, String str, com.google.ads.util.i.c<Integer> cVar) {
        try {
            String str2 = map.get(str);
            if (!TextUtils.isEmpty(str2)) {
                cVar.a(Integer.valueOf(str2));
            }
        } catch (NumberFormatException e) {
            com.google.ads.util.b.a("Could not parse \"" + str + "\" constant.");
        }
    }

    private void b(HashMap<String, String> map, String str, com.google.ads.util.i.c<Long> cVar) {
        try {
            String str2 = map.get(str);
            if (!TextUtils.isEmpty(str2)) {
                cVar.a(Long.valueOf(str2));
            }
        } catch (NumberFormatException e) {
            com.google.ads.util.b.a("Could not parse \"" + str + "\" constant.");
        }
    }

    private void c(HashMap<String, String> map, String str, com.google.ads.util.i.c<String> cVar) {
        String str2 = map.get(str);
        if (!TextUtils.isEmpty(str2)) {
            cVar.a(str2);
        }
    }
}
