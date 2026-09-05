package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.ads.Ad;
import com.google.ads.AdActivity;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AppEventListener;
import com.google.ads.InterstitialAd;
import com.google.ads.ae;
import com.google.ads.af;
import com.google.ads.at;
import com.google.ads.doubleclick.SwipeableDfpAdView;
import com.google.ads.util.AdUtil;
import com.google.ads.util.IcsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f676a = new Object();
    private final com.google.ads.n b;
    private AdWebView f;
    private i g;
    private long i;
    private boolean j;
    private SharedPreferences o;
    private af q;
    private boolean r;
    private LinkedList<String> s;
    private LinkedList<String> t;
    private LinkedList<String> u;
    private Boolean w;
    private com.google.ads.d x;
    private com.google.ads.e y;
    private com.google.ads.f z;
    private boolean h = false;
    private int v = -1;
    private String A = null;
    private String B = null;
    private g e = new g();
    private c c = null;
    private AdRequest d = null;
    private boolean k = false;
    private long p = 60000;
    private boolean l = false;
    private boolean n = false;
    private boolean m = true;

    public d(Ad ad, Activity activity, AdSize adSize, String str, ViewGroup viewGroup, boolean z) {
        this.r = z;
        h hVarA = adSize == null ? h.f680a : h.a(adSize, activity.getApplicationContext());
        if (ad instanceof SwipeableDfpAdView) {
            hVarA.a(true);
        }
        if (activity == null) {
            this.b = new com.google.ads.n(com.google.ads.m.a(), ad, ad instanceof AdView ? (AdView) ad : null, ad instanceof InterstitialAd ? (InterstitialAd) ad : null, str, null, null, viewGroup, hVarA, this);
            return;
        }
        synchronized (f676a) {
            this.o = activity.getApplicationContext().getSharedPreferences("GoogleAdMobAdsPrefs", 0);
            if (z) {
                long j = this.o.getLong("Timeout" + str, -1L);
                if (j < 0) {
                    this.i = 5000L;
                } else {
                    this.i = j;
                }
            } else {
                this.i = 60000L;
            }
        }
        this.b = new com.google.ads.n(com.google.ads.m.a(), ad, ad instanceof AdView ? (AdView) ad : null, ad instanceof InterstitialAd ? (InterstitialAd) ad : null, str, activity, activity.getApplicationContext(), viewGroup, hVarA, this);
        this.q = new af(this);
        this.s = new LinkedList<>();
        this.t = new LinkedList<>();
        this.u = new LinkedList<>();
        a();
        AdUtil.h(this.b.f.a());
        this.x = new com.google.ads.d();
        this.y = new com.google.ads.e(this);
        this.w = null;
        this.z = null;
    }

    public synchronized void a() {
        AdSize adSizeC = this.b.g.a().c();
        this.f = AdUtil.f712a >= 14 ? new IcsUtil.IcsAdWebView(this.b, adSizeC) : new AdWebView(this.b, adSizeC);
        this.f.setVisibility(8);
        this.g = i.a(this, a.d, true, this.b.b());
        this.f.setWebViewClient(this.g);
        if (AdUtil.f712a < this.b.d.a().b.a().b.a().intValue() && !this.b.g.a().a()) {
            com.google.ads.util.b.a("Disabling hardware acceleration for a banner.");
            this.f.g();
        }
    }

    public synchronized void b() {
        if (this.y != null) {
            this.y.b();
        }
        this.b.o.a(null);
        this.b.p.a(null);
        C();
        f();
        if (this.f != null) {
            this.f.destroy();
        }
        this.h = true;
    }

    public void a(String str) {
        this.B = str;
        Uri uriBuild = new Uri.Builder().encodedQuery(str).build();
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> mapB = AdUtil.b(uriBuild);
        for (String str2 : mapB.keySet()) {
            sb.append(str2).append(" = ").append(mapB.get(str2)).append("\n");
        }
        this.A = sb.toString().trim();
        if (TextUtils.isEmpty(this.A)) {
            this.A = null;
        }
    }

    public String c() {
        return this.A;
    }

    public String d() {
        return this.B;
    }

    public synchronized void e() {
        this.m = false;
        com.google.ads.util.b.a("Refreshing is no longer allowed on this AdView.");
    }

    public synchronized void f() {
        if (this.l) {
            com.google.ads.util.b.a("Disabling refreshing.");
            com.google.ads.m.a().c.a().removeCallbacks(this.q);
            this.l = false;
        } else {
            com.google.ads.util.b.a("Refreshing is already disabled.");
        }
    }

    public synchronized void g() {
        this.n = false;
        if (this.b.a()) {
            if (this.m) {
                if (!this.l) {
                    com.google.ads.util.b.a("Enabling refreshing every " + this.p + " milliseconds.");
                    com.google.ads.m.a().c.a().postDelayed(this.q, this.p);
                    this.l = true;
                } else {
                    com.google.ads.util.b.a("Refreshing is already enabled.");
                }
            } else {
                com.google.ads.util.b.a("Refreshing disabled on this AdView");
            }
        } else {
            com.google.ads.util.b.a("Tried to enable refreshing on something other than an AdView.");
        }
    }

    public void h() {
        g();
        this.n = true;
    }

    public com.google.ads.n i() {
        return this.b;
    }

    public synchronized com.google.ads.d j() {
        return this.x;
    }

    public synchronized c k() {
        return this.c;
    }

    public synchronized AdWebView l() {
        return this.f;
    }

    public synchronized i m() {
        return this.g;
    }

    public g n() {
        return this.e;
    }

    public synchronized void a(int i) {
        this.v = i;
    }

    public synchronized int o() {
        return this.v;
    }

    public long p() {
        return this.i;
    }

    public synchronized boolean q() {
        return this.c != null;
    }

    public synchronized boolean r() {
        return this.j;
    }

    public synchronized boolean s() {
        return this.k;
    }

    public synchronized boolean t() {
        return this.l;
    }

    public synchronized void a(AdRequest adRequest) {
        com.google.ads.util.b.d("v6.4.1 RC00");
        if (this.h) {
            com.google.ads.util.b.e("loadAd called after ad was destroyed.");
        } else if (q()) {
            com.google.ads.util.b.e("loadAd called while the ad is already loading, so aborting.");
        } else if (AdActivity.isShowing()) {
            com.google.ads.util.b.e("loadAd called while an interstitial or landing page is displayed, so aborting");
        } else if (AdUtil.c(this.b.f.a()) && AdUtil.b(this.b.f.a())) {
            if (at.a(this.b.f.a(), this.o.getLong("GoogleAdMobDoritosLife", 60000L))) {
                at.a(this.b.c.a());
            }
            this.k = false;
            this.s.clear();
            this.t.clear();
            this.d = adRequest;
            if (this.x.a()) {
                this.y.a(this.x.b(), adRequest);
            } else {
                com.google.ads.l lVar = new com.google.ads.l(this.b);
                this.b.m.a(lVar);
                this.c = lVar.b.a();
                this.c.a(adRequest);
            }
        }
    }

    public synchronized void a(AdRequest.ErrorCode errorCode) {
        this.c = null;
        if (errorCode == AdRequest.ErrorCode.NETWORK_ERROR) {
            a(60.0f);
            if (!t()) {
                h();
            }
        }
        if (this.b.b()) {
            if (errorCode == AdRequest.ErrorCode.NO_FILL) {
                this.e.B();
            } else if (errorCode == AdRequest.ErrorCode.NETWORK_ERROR) {
                this.e.z();
            }
        }
        com.google.ads.util.b.c("onFailedToReceiveAd(" + errorCode + ")");
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onFailedToReceiveAd(this.b.f708a.a(), errorCode);
        }
    }

    public synchronized void a(com.google.ads.c cVar) {
        this.c = null;
        this.y.a(cVar, this.d);
    }

    public synchronized void a(View view, com.google.ads.h hVar, com.google.ads.f fVar, boolean z) {
        com.google.ads.util.b.a("AdManager.onReceiveGWhirlAd() called.");
        this.k = true;
        this.z = fVar;
        if (this.b.a()) {
            a(view);
            a(fVar, Boolean.valueOf(z));
        }
        this.y.d(hVar);
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onReceiveAd(this.b.f708a.a());
        }
    }

    public synchronized void a(com.google.ads.f fVar, boolean z) {
        com.google.ads.util.b.a(String.format(Locale.US, "AdManager.onGWhirlAdClicked(%b) called.", Boolean.valueOf(z)));
        b(fVar, Boolean.valueOf(z));
    }

    public synchronized void b(com.google.ads.c cVar) {
        com.google.ads.util.b.a("AdManager.onGWhirlNoFill() called.");
        a(cVar.i(), cVar.c());
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onFailedToReceiveAd(this.b.f708a.a(), AdRequest.ErrorCode.NO_FILL);
        }
    }

    public synchronized void u() {
        this.e.C();
        com.google.ads.util.b.c("onDismissScreen()");
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onDismissScreen(this.b.f708a.a());
        }
    }

    public synchronized void v() {
        com.google.ads.util.b.c("onPresentScreen()");
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onPresentScreen(this.b.f708a.a());
        }
    }

    public synchronized void w() {
        com.google.ads.util.b.c("onLeaveApplication()");
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onLeaveApplication(this.b.f708a.a());
        }
    }

    public synchronized void a(String str, String str2) {
        AppEventListener appEventListenerA = this.b.p.a();
        if (appEventListenerA != null) {
            appEventListenerA.onAppEvent(this.b.f708a.a(), str, str2);
        }
    }

    public void x() {
        this.e.f();
        D();
    }

    private void a(com.google.ads.f fVar, Boolean bool) {
        List<String> listD = fVar.d();
        if (listD == null) {
            listD = new ArrayList<>();
            listD.add("http://e.admob.com/imp?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&ad_network_id=@gw_adnetid@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&nr=@gw_adnetrefresh@&adt=@gw_adt@&aec=@gw_aec@");
        }
        a(listD, fVar.a(), fVar.b(), fVar.c(), bool, this.e.d(), this.e.e());
    }

    private void b(com.google.ads.f fVar, Boolean bool) {
        List<String> listE = fVar.e();
        if (listE == null) {
            listE = new ArrayList<>();
            listE.add("http://e.admob.com/clk?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&ad_network_id=@gw_adnetid@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&nr=@gw_adnetrefresh@");
        }
        a(listE, fVar.a(), fVar.b(), fVar.c(), bool, null, null);
    }

    private void a(List<String> list, String str) {
        List<String> arrayList;
        if (list == null) {
            arrayList = new ArrayList<>();
            arrayList.add("http://e.admob.com/nofill?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&adt=@gw_adt@&aec=@gw_aec@");
        } else {
            arrayList = list;
        }
        a(arrayList, null, null, str, null, this.e.d(), this.e.e());
    }

    private void a(List<String> list, String str, String str2, String str3, Boolean bool, String str4, String str5) {
        String strA = AdUtil.a(this.b.f.a());
        com.google.ads.b bVarA = com.google.ads.b.a();
        String string = bVarA.b().toString();
        String string2 = bVarA.c().toString();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            new Thread(new ae(com.google.ads.g.a(it.next(), this.b.h.a(), bool, strA, str, str2, str3, string, string2, str4, str5), this.b.f.a())).start();
        }
        this.e.b();
    }

    public synchronized void y() {
        Activity activityA = this.b.c.a();
        if (activityA == null) {
            com.google.ads.util.b.e("activity was null while trying to ping tracking URLs.");
        } else {
            Iterator<String> it = this.s.iterator();
            while (it.hasNext()) {
                new Thread(new ae(it.next(), activityA.getApplicationContext())).start();
            }
        }
    }

    public synchronized void z() {
        Activity activityA = this.b.c.a();
        if (activityA == null) {
            com.google.ads.util.b.e("activity was null while trying to ping manual tracking URLs.");
        } else {
            Iterator<String> it = this.t.iterator();
            while (it.hasNext()) {
                new Thread(new ae(it.next(), activityA.getApplicationContext())).start();
            }
        }
    }

    public synchronized void A() {
        if (!this.h) {
            if (this.d != null) {
                if (this.b.a()) {
                    if (this.b.j.a().isShown() && AdUtil.d()) {
                        com.google.ads.util.b.c("Refreshing ad.");
                        a(this.d);
                    } else {
                        com.google.ads.util.b.a("Not refreshing because the ad is not visible.");
                    }
                    if (this.n) {
                        f();
                    } else {
                        com.google.ads.m.a().c.a().postDelayed(this.q, this.p);
                    }
                } else {
                    com.google.ads.util.b.a("Tried to refresh an ad that wasn't an AdView.");
                }
            } else {
                com.google.ads.util.b.a("Tried to refresh before calling loadAd().");
            }
        }
    }

    public void a(long j) {
        synchronized (f676a) {
            SharedPreferences.Editor editorEdit = this.o.edit();
            editorEdit.putLong("Timeout" + this.b.h, j);
            editorEdit.commit();
            if (this.r) {
                this.i = j;
            }
        }
    }

    public synchronized void a(boolean z) {
        this.j = z;
    }

    public void a(View view) {
        this.b.i.a().setVisibility(0);
        this.b.i.a().removeAllViews();
        this.b.i.a().addView(view);
        if (this.b.g.a().b()) {
            this.b.b.a().a(this.b.l.a(), false, -1, -1, -1, -1);
            if (this.b.e.a().a()) {
                this.b.i.a().addView(this.b.e.a(), AdUtil.a(this.b.f.a(), this.b.g.a().c().getWidth()), AdUtil.a(this.b.f.a(), this.b.g.a().c().getHeight()));
            }
        }
    }

    public synchronized void a(float f) {
        long j = this.p;
        this.p = (long) (1000.0f * f);
        if (t() && this.p != j) {
            f();
            g();
        }
    }

    public synchronized void b(long j) {
        if (j > 0) {
            this.o.edit().putLong("GoogleAdMobDoritosLife", j).commit();
        }
    }

    public synchronized void B() {
        com.google.ads.util.a.a(this.b.b());
        if (this.k) {
            this.k = false;
            if (this.w == null) {
                com.google.ads.util.b.b("isMediationFlag is null in show() with isReady() true. we should have an ad and know whether this is a mediation request or not. ");
            } else if (this.w.booleanValue()) {
                if (this.y.c()) {
                    a(this.z, (Boolean) false);
                }
            } else {
                AdActivity.launchAdActivity(this, new e("interstitial"));
                y();
            }
        } else {
            com.google.ads.util.b.c("Cannot show interstitial because it is not loaded and ready.");
        }
    }

    public synchronized void C() {
        if (this.c != null) {
            this.c.a();
            this.c = null;
        }
        if (this.f != null) {
            this.f.stopLoading();
        }
    }

    protected synchronized void D() {
        Activity activityA = this.b.c.a();
        if (activityA == null) {
            com.google.ads.util.b.e("activity was null while trying to ping click tracking URLs.");
        } else {
            Iterator<String> it = this.u.iterator();
            while (it.hasNext()) {
                new Thread(new ae(it.next(), activityA.getApplicationContext())).start();
            }
        }
    }

    protected synchronized void E() {
        this.c = null;
        this.k = true;
        this.f.setVisibility(0);
        if (this.b.a()) {
            a(this.f);
        }
        this.e.g();
        if (this.b.a()) {
            y();
        }
        com.google.ads.util.b.c("onReceiveAd()");
        AdListener adListenerA = this.b.o.a();
        if (adListenerA != null) {
            adListenerA.onReceiveAd(this.b.f708a.a());
        }
        this.b.l.a(this.b.m.a());
        this.b.m.a(null);
    }

    protected synchronized void b(String str) {
        com.google.ads.util.b.a("Adding a tracking URL: " + str);
        this.s.add(str);
    }

    protected synchronized void c(String str) {
        com.google.ads.util.b.a("Adding a manual tracking URL: " + str);
        F().add(str);
    }

    protected synchronized void a(LinkedList<String> linkedList) {
        Iterator<String> it = linkedList.iterator();
        while (it.hasNext()) {
            com.google.ads.util.b.a("Adding a click tracking URL: " + it.next());
        }
        this.u = linkedList;
    }

    public void b(boolean z) {
        this.w = Boolean.valueOf(z);
    }

    public void a(com.google.ads.l lVar, boolean z, int i, int i2, int i3, int i4) {
        this.b.e.a().setOverlayActivated(!z);
        a(i, i2, i3, i4);
        if (this.b.q.a() != null) {
            if (z) {
                this.b.q.a().onAdActivated(this.b.f708a.a());
            } else {
                this.b.q.a().onAdDeactivated(this.b.f708a.a());
            }
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        int iC;
        int i5;
        ActivationOverlay activationOverlayA = this.b.e.a();
        int iA = AdUtil.a(this.b.f.a(), i3 < 0 ? this.b.g.a().c().getWidth() : i3);
        Context contextA = this.b.f.a();
        if (i4 < 0) {
            i4 = this.b.g.a().c().getHeight();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iA, AdUtil.a(contextA, i4));
        if (i3 < 0) {
            iC = 0;
            i5 = 0;
        } else {
            iC = i2;
            i5 = i;
        }
        int iD = i5 < 0 ? this.b.e.a().d() : i5;
        if (iC < 0) {
            iC = this.b.e.a().c();
        }
        this.b.e.a().setXPosition(iD);
        this.b.e.a().setYPosition(iC);
        layoutParams.setMargins(AdUtil.a(this.b.f.a(), iD), AdUtil.a(this.b.f.a(), iC), 0, 0);
        activationOverlayA.setLayoutParams(layoutParams);
    }

    public LinkedList<String> F() {
        return this.t;
    }
}
