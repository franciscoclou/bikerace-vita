package com.applovin.impl.a;

import android.os.PowerManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ai implements com.applovin.a.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f184a;
    private final com.applovin.a.j b;
    private final h c;
    private final Map d;

    ai(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f184a = dVar;
        this.b = dVar.f();
        this.c = new h(dVar);
        this.d = new HashMap(2);
        Iterator it = com.applovin.a.g.b().iterator();
        while (it.hasNext()) {
            this.d.put((com.applovin.a.g) it.next(), new HashMap());
        }
        ((Map) this.d.get(com.applovin.a.g.f156a)).put(com.applovin.a.f.f155a, new al(com.applovin.a.f.f155a));
        ((Map) this.d.get(com.applovin.a.g.f156a)).put(com.applovin.a.f.d, new al(com.applovin.a.f.d));
        ((Map) this.d.get(com.applovin.a.g.f156a)).put(com.applovin.a.f.c, new al(com.applovin.a.f.c));
        ((Map) this.d.get(com.applovin.a.g.f156a)).put(com.applovin.a.f.b, new al(com.applovin.a.f.b));
        ((Map) this.d.get(com.applovin.a.g.b)).put(com.applovin.a.f.c, new al(com.applovin.a.f.c));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return ((PowerManager) this.f184a.h().getSystemService("power")).isScreenOn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(com.applovin.a.f fVar) {
        if (fVar == com.applovin.a.f.f155a) {
            return ((Boolean) this.f184a.a(j.z)).booleanValue();
        }
        if (fVar == com.applovin.a.f.d) {
            return ((Boolean) this.f184a.a(j.B)).booleanValue();
        }
        if (fVar == com.applovin.a.f.b) {
            return ((Boolean) this.f184a.a(j.D)).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long b(com.applovin.a.f fVar) {
        if (fVar == com.applovin.a.f.f155a) {
            return ((Long) this.f184a.a(j.A)).longValue();
        }
        if (fVar == com.applovin.a.f.d) {
            return ((Long) this.f184a.a(j.C)).longValue();
        }
        if (fVar == com.applovin.a.f.b) {
            return ((Long) this.f184a.a(j.E)).longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        ak akVar = new ak(this, (al) ((Map) this.d.get(gVar)).get(fVar));
        com.applovin.a.a aVarD = this.c.d(fVar, gVar);
        if (aVarD != null) {
            this.b.a("AppLovinAdService", "Using pre-loaded ad: " + aVarD + " for size " + fVar + " and type " + gVar);
            akVar.a(aVarD);
        } else {
            this.f184a.k().a(new s(fVar, gVar, akVar, this.f184a), w.MAIN);
        }
        this.c.a(fVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.applovin.a.f fVar) {
        long jB = b(fVar);
        if (jB > 0) {
            this.f184a.k().a(new am(this, fVar), w.MAIN, (jB + 2) * 1000);
        }
    }

    public void a(com.applovin.a.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        a aVar2 = (a) aVar;
        al alVar = (al) ((Map) this.d.get(aVar2.h())).get(aVar2.g());
        synchronized (alVar.b) {
            alVar.c = null;
            alVar.d = 0L;
        }
    }

    @Override // com.applovin.a.e
    public void a(com.applovin.a.f fVar, com.applovin.a.d dVar) {
        a(fVar, com.applovin.a.g.f156a, dVar);
    }

    public void a(com.applovin.a.f fVar, com.applovin.a.g gVar) {
        this.c.b(fVar, gVar);
    }

    public void a(com.applovin.a.f fVar, com.applovin.a.g gVar, com.applovin.a.d dVar) {
        com.applovin.a.a aVar;
        if (fVar == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        if (gVar == null) {
            throw new IllegalArgumentException("No ad type specificed");
        }
        al alVar = (al) ((Map) this.d.get(gVar)).get(fVar);
        synchronized (alVar.b) {
            boolean z = System.currentTimeMillis() > alVar.d;
            if (alVar.c == null || z) {
                this.b.a("AppLovinAdService", "Loading next ad...");
                alVar.g.add(dVar);
                if (!alVar.e) {
                    alVar.e = true;
                    b(fVar, gVar);
                }
                aVar = null;
            } else {
                aVar = alVar.c;
            }
        }
        if (aVar != null) {
            dVar.a(aVar);
        }
    }

    @Override // com.applovin.a.e
    public void a(com.applovin.a.h hVar, com.applovin.a.f fVar) {
        if (hVar == null) {
            return;
        }
        al alVar = (al) ((Map) this.d.get(com.applovin.a.g.f156a)).get(fVar);
        synchronized (alVar.b) {
            alVar.f.remove(hVar);
        }
        this.b.a("AppLovinAdService", "Removed update listener: " + hVar);
    }

    @Override // com.applovin.a.e
    public void b(com.applovin.a.h hVar, com.applovin.a.f fVar) {
        boolean z;
        if (hVar == null) {
            throw new IllegalArgumentException("No ad listener specified");
        }
        al alVar = (al) ((Map) this.d.get(com.applovin.a.g.f156a)).get(fVar);
        synchronized (alVar.b) {
            if (alVar.f.contains(hVar)) {
                z = false;
            } else {
                alVar.f.add(hVar);
                z = true;
                this.b.a("AppLovinAdService", "Added update listener: " + hVar);
            }
        }
        if (z) {
            this.f184a.k().a(new am(this, fVar), w.MAIN);
        }
    }
}
