package com.applovin.impl.a;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class am extends x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ai f187a;
    private final com.applovin.a.f b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public am(ai aiVar, com.applovin.a.f fVar) {
        super("UpdateAdTask", aiVar.f184a);
        this.f187a = aiVar;
        this.b = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        al alVar = (al) ((Map) this.f187a.d.get(com.applovin.a.g.f156a)).get(this.b);
        synchronized (alVar.b) {
            boolean zA = this.f187a.a(this.b);
            boolean zA2 = this.f187a.a();
            boolean z = !alVar.f.isEmpty();
            boolean z2 = System.currentTimeMillis() > alVar.d;
            if (zA && z && z2 && zA2 && !alVar.e) {
                alVar.e = true;
                this.f187a.b(this.b, com.applovin.a.g.f156a);
            }
        }
    }
}
