package com.applovin.impl.a;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ak implements com.applovin.a.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ai f185a;
    private final al b;

    private ak(ai aiVar, al alVar) {
        this.f185a = aiVar;
        this.b = alVar;
    }

    @Override // com.applovin.a.d
    public void a(int i) {
        HashSet hashSet;
        synchronized (this.b.b) {
            hashSet = new HashSet(this.b.g);
            this.b.g.clear();
            this.b.e = false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                ((com.applovin.a.d) it.next()).a(i);
            } catch (Throwable th) {
                this.f185a.b.b("AppLovinAdService", "Unable to notify listener about ad load failure", th);
            }
        }
    }

    @Override // com.applovin.a.d
    public void a(com.applovin.a.a aVar) {
        HashSet hashSet;
        HashSet hashSet2;
        synchronized (this.b.b) {
            if (this.f185a.a(this.b.f186a)) {
                long jB = this.f185a.b(this.b.f186a);
                if (jB > 0) {
                    this.b.d = (jB * 1000) + System.currentTimeMillis();
                } else if (jB == 0) {
                    this.b.d = Long.MAX_VALUE;
                }
                this.b.c = aVar;
            } else {
                this.b.c = null;
                this.b.d = 0L;
            }
            hashSet = new HashSet(this.b.g);
            this.b.g.clear();
            hashSet2 = new HashSet(this.b.f);
            this.b.e = false;
        }
        this.f185a.c(this.b.f186a);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                ((com.applovin.a.d) it.next()).a(aVar);
            } catch (Throwable th) {
                this.f185a.b.b("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
            }
        }
        Iterator it2 = hashSet2.iterator();
        while (it2.hasNext()) {
            try {
                ((com.applovin.a.h) it2.next()).b(aVar);
            } catch (Throwable th2) {
                this.f185a.b.b("AppLovinAdService", "Unable to notify listener about an updated loaded ad", th2);
            }
        }
    }
}
