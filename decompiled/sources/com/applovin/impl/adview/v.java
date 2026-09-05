package com.applovin.impl.adview;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class v implements com.applovin.a.d, com.applovin.a.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference f248a;
    private final com.applovin.a.e b;
    private final com.applovin.a.j c;

    v(a aVar, com.applovin.a.k kVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("No view specified");
        }
        if (kVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f248a = new WeakReference(aVar);
        this.c = kVar.f();
        this.b = kVar.d();
    }

    @Override // com.applovin.a.d
    public void a(int i) {
        a aVar = (a) this.f248a.get();
        if (aVar != null) {
            aVar.b(i);
        }
    }

    @Override // com.applovin.a.d
    public void a(com.applovin.a.a aVar) {
        a aVar2 = (a) this.f248a.get();
        if (aVar2 != null) {
            aVar2.c(aVar);
        } else {
            this.c.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was recieved");
        }
    }

    @Override // com.applovin.a.h
    public void b(com.applovin.a.a aVar) {
        a aVar2 = (a) this.f248a.get();
        if (aVar2 != null) {
            aVar2.c(aVar);
        } else {
            this.b.a(this, aVar.g());
            this.c.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
        }
    }

    public String toString() {
        return "[AdViewController listener: " + hashCode() + "]";
    }
}
