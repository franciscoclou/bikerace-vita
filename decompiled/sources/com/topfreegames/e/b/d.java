package com.topfreegames.e.b;

import java.util.Iterator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: TopFacebookPictureRequestsManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f1526a;
    private a b;

    public d(c cVar, a aVar) {
        this.f1526a = cVar;
        this.b = aVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean call() {
        b bVarB;
        boolean z;
        synchronized (this.f1526a.c) {
            if (this.b != null && (bVarB = this.b.b()) != null) {
                String strA = this.b.a();
                if (!this.f1526a.d.keySet().contains(strA) || !this.f1526a.e.containsValue(bVarB)) {
                    z = true;
                    break;
                }
                Iterator it = this.f1526a.e.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    a aVar = (a) it.next();
                    if (strA.equals(aVar.a()) && ((b) this.f1526a.e.get(aVar)) == bVarB) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    this.f1526a.c.add(this.b);
                    this.f1526a.d.put(strA, this.b);
                    this.f1526a.e.put(this.b, bVarB);
                    this.b.a(this.f1526a);
                    if (this.f1526a.c.size() == 1) {
                        this.b.c();
                    }
                }
            }
        }
        return null;
    }
}
