package com.applovin.impl.a;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h implements com.applovin.a.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f202a;
    private final com.applovin.a.j b;
    private final String c = "PreloadManager";
    private final Object e = new Object();
    private final Map d = a();

    h(d dVar) {
        this.f202a = dVar;
        this.b = dVar.f();
    }

    private l a(com.applovin.a.g gVar, com.applovin.a.f fVar) {
        if (gVar.equals(com.applovin.a.g.b)) {
            return j.an;
        }
        if (fVar.equals(com.applovin.a.f.f155a)) {
            return j.aj;
        }
        if (fVar.equals(com.applovin.a.f.d)) {
            return j.ak;
        }
        if (fVar.equals(com.applovin.a.f.c)) {
            return j.al;
        }
        return fVar.equals(com.applovin.a.f.b) ? j.am : j.aj;
    }

    private Map a() {
        HashMap map = new HashMap(4);
        for (com.applovin.a.f fVar : com.applovin.a.f.d()) {
            map.put(fVar, new i(((Integer) this.f202a.a(a(com.applovin.a.g.f156a, fVar))).intValue()));
        }
        HashMap map2 = new HashMap(1);
        map2.put(com.applovin.a.f.c, new i(((Integer) this.f202a.a(a(com.applovin.a.g.b, com.applovin.a.f.c))).intValue()));
        HashMap map3 = new HashMap(2);
        map3.put(com.applovin.a.g.f156a, map);
        map3.put(com.applovin.a.g.b, map2);
        return map3;
    }

    private i e(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        return (i) ((Map) this.d.get(gVar)).get(fVar);
    }

    @Override // com.applovin.a.d
    public void a(int i) {
        this.b.a("PreloadManager", "Failed to pre-load an ad, error code " + i);
    }

    @Override // com.applovin.a.d
    public void a(com.applovin.a.a aVar) {
        synchronized (this.e) {
            e(aVar.g(), aVar.h()).a(aVar);
        }
        this.b.a("PreloadManager", "Pulled ad from network and saved to preload cache: " + aVar);
    }

    public void a(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        if (!((Boolean) this.f202a.a(j.G)).booleanValue() || c(fVar, gVar)) {
            return;
        }
        this.b.a("PreloadManager", "Preloading ad for size " + fVar + "...");
        s sVar = new s(fVar, gVar, this, this.f202a);
        sVar.a(true);
        this.f202a.k().a(sVar, w.BACKGROUND, 500L);
    }

    public void b(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        int iB;
        synchronized (this.e) {
            i iVarE = e(fVar, gVar);
            iB = iVarE.b() - iVarE.a();
        }
        if (iB > 0) {
            for (int i = 0; i < iB; i++) {
                a(fVar, gVar);
            }
        }
    }

    public boolean c(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        boolean zC;
        synchronized (this.e) {
            zC = e(fVar, gVar).c();
        }
        return zC;
    }

    public com.applovin.a.a d(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        com.applovin.a.a aVarE;
        synchronized (this.e) {
            aVarE = e(fVar, gVar).e();
        }
        return aVarE;
    }
}
