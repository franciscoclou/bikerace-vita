package com.chartboost.sdk;

import android.content.Context;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import com.chartboost.sdk.impl.m;
import com.chartboost.sdk.impl.n;
import com.facebook.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class a {
    private Chartboost c;
    private j d;
    private com.chartboost.sdk.impl.a h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList<C0003a> f388a = new ArrayList<>();
    private C0003a b = null;
    private com.chartboost.sdk.impl.a.InterfaceC0006a i = new com.chartboost.sdk.impl.a.InterfaceC0006a() { // from class: com.chartboost.sdk.a.1
        @Override // com.chartboost.sdk.impl.a.InterfaceC0006a
        public void a(com.chartboost.sdk.impl.a aVar) {
            boolean z = false;
            if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForCaching) {
                synchronized (this) {
                    C0003a c0003aA = a.this.a(aVar.d, aVar.e);
                    if (c0003aA != null && !c0003aA.f394a) {
                        z = true;
                    }
                }
                if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial && aVar.e != null) {
                    a.this.f.put(aVar.e, aVar);
                    if (a.this.c.getDelegate() != null && !z) {
                        a.this.c.getDelegate().didCacheInterstitial(aVar.e);
                    }
                } else if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
                    a.this.g = aVar;
                    if (a.this.c.getDelegate() != null && !z) {
                        a.this.c.getDelegate().didCacheMoreApps();
                    }
                }
                aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateCached;
            }
            if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay || z) {
                if (!z) {
                    aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateOther;
                }
                if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial) {
                    a.this.b(aVar);
                } else if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
                    a.this.c(aVar);
                }
            }
            a.this.b(aVar.d, aVar.e);
        }

        @Override // com.chartboost.sdk.impl.a.InterfaceC0006a
        public void b(com.chartboost.sdk.impl.a aVar) {
            com.chartboost.sdk.b bVarA;
            com.chartboost.sdk.b bVarA2;
            a.this.h = null;
            if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial) {
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didDismissInterstitial(aVar.e);
                }
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didCloseInterstitial(aVar.e);
                }
                if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController && (bVarA2 = a.this.c.a()) != null) {
                    bVarA2.a(aVar, true);
                    return;
                }
                return;
            }
            if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didDismissMoreApps();
                }
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didCloseMoreApps();
                }
                if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController && (bVarA = a.this.c.a()) != null) {
                    bVarA.a(aVar, true);
                }
            }
        }

        private void a(JSONObject jSONObject, String str, k kVar) {
            if (jSONObject != null) {
                try {
                    if (jSONObject.getString(str) != null) {
                        kVar.a(str, (Object) jSONObject.optString(str));
                    }
                } catch (JSONException e) {
                }
            }
        }

        @Override // com.chartboost.sdk.impl.a.InterfaceC0006a
        public void a(com.chartboost.sdk.impl.a aVar, final String str, JSONObject jSONObject) {
            com.chartboost.sdk.b bVarA;
            com.chartboost.sdk.b bVarA2;
            a.this.h = null;
            boolean z = (str == null || str.equals("") || str.equals("null")) ? false : true;
            if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial) {
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didDismissInterstitial(aVar.e);
                }
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didClickInterstitial(aVar.e);
                }
                if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController && (bVarA2 = a.this.c.a()) != null) {
                    bVarA2.a(aVar, !z);
                }
            } else if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didDismissMoreApps();
                }
                if (a.this.c.getDelegate() != null) {
                    a.this.c.getDelegate().didClickMoreApps();
                }
                if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController && (bVarA = a.this.c.a()) != null) {
                    bVarA.a(aVar, !z);
                }
            }
            k kVar = new k("api/click");
            Context contextC = a.this.c.f368a == null ? a.this.c.c() : a.this.c.f368a.b();
            if (contextC == null) {
                contextC = a.this.c.getContext();
            }
            kVar.a(contextC);
            a(aVar.f406a, "to", kVar);
            a(aVar.f406a, "cgn", kVar);
            a(aVar.f406a, "creative", kVar);
            a(aVar.f406a, "ad_id", kVar);
            a(jSONObject, "cgn", kVar);
            a(jSONObject, "creative", kVar);
            a(jSONObject, ServerProtocol.DIALOG_PARAM_TYPE, kVar);
            a(jSONObject, "more_type", kVar);
            kVar.b(a.this.c.getAppID(), a.this.c.getAppSignature());
            if (z) {
                a.this.c.a(new com.chartboost.sdk.b.a(true, null));
                a.this.d.a(kVar, new j.b() { // from class: com.chartboost.sdk.a.1.1
                    @Override // com.chartboost.sdk.impl.j.b
                    public void a(JSONObject jSONObject2, k kVar2) {
                        a.this.a(jSONObject2, str);
                    }

                    @Override // com.chartboost.sdk.impl.j.b
                    public void a(k kVar2, String str2) {
                        a.this.j.a(false, str);
                    }
                });
            } else {
                a.this.j.a(false, str);
                a.this.d.a(kVar);
            }
        }

        @Override // com.chartboost.sdk.impl.a.InterfaceC0006a
        public void c(com.chartboost.sdk.impl.a aVar) {
            a.this.c(aVar.d, aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial ? aVar.e : null);
        }
    };
    private n.a j = new n.a() { // from class: com.chartboost.sdk.a.2
        @Override // com.chartboost.sdk.impl.n.a
        public void a(boolean z, String str) {
            com.chartboost.sdk.b bVarA = a.this.c.a();
            if (bVarA != null && bVarA.a()) {
                bVarA.a(true);
            }
            if (!z && a.this.c.getDelegate() != null) {
                a.this.c.getDelegate().didFailToLoadUrl(str);
            }
        }
    };
    private n e = new n(this.j);
    private Map<String, com.chartboost.sdk.impl.a> f = new HashMap();
    private com.chartboost.sdk.impl.a g = null;

    /* JADX INFO: renamed from: com.chartboost.sdk.a$a, reason: collision with other inner class name */
    class C0003a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f394a;
        private String b;
        private com.chartboost.sdk.impl.a.c c;

        public C0003a(com.chartboost.sdk.impl.a.c cVar, String str, boolean z) {
            this.c = cVar;
            this.b = str;
            a(z);
        }

        public void a(boolean z) {
            this.f394a = z;
        }
    }

    protected com.chartboost.sdk.impl.a a() {
        return this.h;
    }

    protected void a(com.chartboost.sdk.impl.a aVar) {
        this.h = aVar;
    }

    public a(Chartboost chartboost) {
        this.c = chartboost;
        this.d = this.c.b;
    }

    public synchronized C0003a a(com.chartboost.sdk.impl.a.c cVar, String str) {
        C0003a c0003a;
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
            c0003a = this.b;
        } else if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial) {
            if (str == null) {
                str = "";
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f388a.size()) {
                    c0003a = null;
                    break;
                }
                if (!str.equals(this.f388a.get(i2).b)) {
                    i = i2 + 1;
                } else {
                    c0003a = this.f388a.get(i2);
                    break;
                }
            }
        } else {
            c0003a = null;
        }
        return c0003a;
    }

    public synchronized void b(com.chartboost.sdk.impl.a.c cVar, String str) {
        C0003a c0003aA;
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
            this.b = null;
        } else if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial && (c0003aA = a(cVar, str)) != null) {
            this.f388a.remove(c0003aA);
        }
    }

    public synchronized void a(com.chartboost.sdk.impl.a.c cVar, String str, boolean z) {
        C0003a c0003a = new C0003a(cVar, str, z);
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
            this.b = c0003a;
        } else if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial) {
            this.f388a.add(c0003a);
        }
    }

    public void a(String str) {
        if (this.c.getDelegate() == null || this.c.getDelegate().shouldRequestInterstitialsInFirstSession() || com.chartboost.sdk.Libraries.d.a().getInt("cbPrefSessionCount", 0) > 1) {
            a(str, true);
        }
    }

    protected void b(String str) {
        if (this.c.getDelegate() == null || this.c.getDelegate().shouldRequestInterstitialsInFirstSession() || com.chartboost.sdk.Libraries.d.a().getInt("cbPrefSessionCount", 0) != 1) {
            this.c.getHandler().post(new b(str));
        }
    }

    class b implements Runnable {
        private String b;

        public b(String str) {
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.c.hasCachedInterstitial(this.b)) {
                a.this.b((com.chartboost.sdk.impl.a) a.this.f.get(this.b));
            } else {
                a.this.a(this.b, false);
            }
        }
    }

    protected void b() {
        this.c.getHandler().post(new c(this, null));
    }

    class c implements Runnable {
        private c() {
        }

        /* synthetic */ c(a aVar, c cVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.g == null) {
                a.this.a(false);
            } else {
                a.this.c(a.this.g);
            }
        }
    }

    protected boolean c() {
        return this.g != null;
    }

    protected boolean c(String str) {
        com.chartboost.sdk.impl.a aVar = this.f.get(str);
        if (aVar == null) {
            return false;
        }
        return TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - aVar.b.getTime()) < 86400;
    }

    protected void d() {
        if (this.h != null) {
            this.i.b(this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.chartboost.sdk.impl.a.c cVar, String str) {
        b(cVar, str);
        com.chartboost.sdk.b bVarA = this.c.a();
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps && bVarA != null && bVarA.a()) {
            bVarA.a(true);
        }
        a((com.chartboost.sdk.impl.a) null);
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial && this.c.getDelegate() != null) {
            this.c.getDelegate().didFailToLoadInterstitial(str);
        }
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps && this.c.getDelegate() != null) {
            this.c.getDelegate().didFailToLoadMoreApps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject, com.chartboost.sdk.impl.a.c cVar, boolean z, String str, boolean z2) {
        com.chartboost.sdk.impl.a.b bVar;
        if (!jSONObject.optString("status", "").equals("200")) {
            c(cVar, str);
            return;
        }
        if (cVar == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps && !z) {
            boolean z3 = false;
            if (this.c.a() != null && this.c.a().a()) {
                z3 = true;
            }
            if (z2 && !z3) {
                b(cVar, str);
                return;
            }
        }
        if (z) {
            bVar = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForCaching;
        } else {
            bVar = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay;
        }
        new com.chartboost.sdk.impl.a(jSONObject, cVar, this.i, bVar, str, z2);
    }

    protected void e() {
        this.f = new HashMap();
        this.g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject, String str) {
        this.e.a(str, this.c.getHostActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.chartboost.sdk.impl.a aVar) {
        if (!aVar.k && this.c.getDelegate() != null && !this.c.getDelegate().shouldDisplayInterstitial(aVar.e)) {
            b(aVar.d, aVar.e);
            return;
        }
        if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateCached && this.f.get(aVar.e) == aVar) {
            this.f.remove(aVar.e);
            k kVar = new k("api/show");
            kVar.a(this.c.f368a.b());
            String strOptString = aVar.f406a.optString("ad_id");
            if (strOptString != null) {
                kVar.a("ad_id", (Object) strOptString);
            }
            kVar.b(this.c.getAppID(), this.c.getAppSignature());
            this.d.a(kVar);
        }
        aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay;
        this.c.a(new com.chartboost.sdk.b.a(aVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.chartboost.sdk.impl.a aVar) {
        if (!aVar.k && this.c.getDelegate() != null && !this.c.getDelegate().shouldDisplayMoreApps()) {
            b(aVar.d, aVar.e);
            return;
        }
        if (aVar == this.g) {
            this.g = null;
        }
        boolean z = aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateCached;
        aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateOther;
        boolean z2 = aVar.l;
        com.chartboost.sdk.b bVarA = this.c.a();
        if (bVarA != null) {
            if (bVarA.a() || !z2) {
                if (z2) {
                    bVarA.a(false);
                }
            } else if (!z && !aVar.j) {
                return;
            }
        }
        aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay;
        this.c.a(new com.chartboost.sdk.b.a(aVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final boolean z) {
        com.chartboost.sdk.b bVarA = this.c.a();
        if (!z && bVarA != null && bVarA.c()) {
            if (this.c.getDelegate() != null) {
                this.c.getDelegate().didFailToLoadInterstitial(str);
                return;
            }
            return;
        }
        if (this.c.getDelegate() == null || this.c.getDelegate().shouldRequestInterstitial(str)) {
            if (!m.a()) {
                if (this.c.getDelegate() != null) {
                    this.c.getDelegate().didFailToLoadInterstitial(str);
                    return;
                }
                return;
            }
            synchronized (this) {
                C0003a c0003aA = a(com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial, str);
                if (c0003aA != null) {
                    if (!z && c0003aA.f394a) {
                        c0003aA.f394a = false;
                        return;
                    } else {
                        if (this.c.getDelegate() != null) {
                            this.c.getDelegate().didFailToLoadInterstitial(str);
                        }
                        return;
                    }
                }
                a(com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial, str, z);
                k kVar = new k("api/get");
                kVar.a(this.c.f368a.b());
                kVar.a("location", (Object) str);
                if (z) {
                    kVar.a("cache", (Object) AppEventsConstants.EVENT_PARAM_VALUE_YES);
                }
                kVar.b(this.c.getAppID(), this.c.getAppSignature());
                this.d.a(kVar, new j.b() { // from class: com.chartboost.sdk.a.3
                    @Override // com.chartboost.sdk.impl.j.b
                    public void a(JSONObject jSONObject, k kVar2) {
                        a.this.a(jSONObject, com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial, z, str, false);
                    }

                    @Override // com.chartboost.sdk.impl.j.b
                    public void a(k kVar2, String str2) {
                        a.this.c(com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial, str);
                        com.chartboost.sdk.b bVarA2 = a.this.c.a();
                        if (bVarA2 != null && bVarA2.a()) {
                            bVarA2.a(true);
                        }
                    }
                });
            }
        }
    }

    protected void a(final boolean z) {
        final boolean z2 = false;
        com.chartboost.sdk.b bVarA = this.c.a();
        if (!z && bVarA != null && bVarA.c()) {
            if (this.c.getDelegate() != null) {
                this.c.getDelegate().didFailToLoadMoreApps();
                return;
            }
            return;
        }
        if (this.c.getDelegate() == null || this.c.getDelegate().shouldRequestMoreApps()) {
            if (!m.a()) {
                if (this.c.getDelegate() != null) {
                    this.c.getDelegate().didFailToLoadMoreApps();
                    return;
                }
                return;
            }
            synchronized (this) {
                C0003a c0003aA = a(com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps, (String) null);
                if (c0003aA != null) {
                    if (!z && c0003aA.f394a) {
                        c0003aA.f394a = false;
                        return;
                    } else {
                        if (this.c.getDelegate() != null) {
                            this.c.getDelegate().didFailToLoadMoreApps();
                        }
                        return;
                    }
                }
                a(com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps, (String) null, z);
                if (!z && (this.c.getDelegate() == null || this.c.getDelegate().shouldDisplayLoadingViewForMoreApps())) {
                    this.c.a(new com.chartboost.sdk.b.a(true, null));
                    z2 = true;
                }
                k kVar = new k("api/more");
                kVar.a(this.c.f368a.b());
                if (z) {
                    kVar.a("cache", (Object) AppEventsConstants.EVENT_PARAM_VALUE_YES);
                }
                kVar.b(this.c.getAppID(), this.c.getAppSignature());
                this.d.a(kVar, new j.b() { // from class: com.chartboost.sdk.a.4
                    @Override // com.chartboost.sdk.impl.j.b
                    public void a(JSONObject jSONObject, k kVar2) {
                        a.this.a(jSONObject, com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps, z, null, z2);
                    }

                    @Override // com.chartboost.sdk.impl.j.b
                    public void a(k kVar2, String str) {
                        a.this.c(com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps, (String) null);
                        com.chartboost.sdk.b bVarA2 = a.this.c.a();
                        if (bVarA2 != null && bVarA2.a()) {
                            bVarA2.a(true);
                        }
                    }
                });
            }
        }
    }
}
