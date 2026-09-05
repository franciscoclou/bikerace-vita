package com.topfreegames.e.b;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: TopFacebookUserInfoRequestsManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ q f1539a;
    private n b;

    public r(q qVar, n nVar) {
        this.f1539a = qVar;
        this.b = nVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean call() {
        boolean z;
        n nVar;
        List list;
        boolean z2;
        synchronized (this.f1539a.c) {
            if (this.b != null) {
                String strB = this.b.b();
                WeakReference<p> weakReferenceC = this.b.c();
                if (strB != null && weakReferenceC != null) {
                    if (!this.f1539a.d.keySet().contains(strB) || (nVar = (n) this.f1539a.d.get(strB)) == null || (list = (List) this.f1539a.e.get(nVar)) == null) {
                        z = true;
                    } else {
                        Iterator it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z2 = true;
                                break;
                            }
                            p pVar = (p) ((WeakReference) it.next()).get();
                            if (pVar != null && pVar.equals(weakReferenceC.get())) {
                                z2 = false;
                                break;
                            }
                        }
                        if (z2) {
                            list.add(weakReferenceC);
                        }
                        z = false;
                    }
                    if (z) {
                        this.f1539a.c.add(this.b);
                        this.f1539a.d.put(strB, this.b);
                        LinkedList linkedList = new LinkedList();
                        linkedList.add(weakReferenceC);
                        this.f1539a.e.put(this.b, linkedList);
                        this.b.a(new WeakReference<>(this.f1539a));
                        if (this.f1539a.c.size() == 1) {
                            this.b.e();
                        }
                    }
                }
            }
        }
        return true;
    }
}
