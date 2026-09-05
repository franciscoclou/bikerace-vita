package com.topfreegames.bikerace.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AchievementGroup.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile ArrayList<a> f815a;
    private volatile ArrayList<c> b;
    private volatile d c;

    c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        this.c = new d();
        this.c.f816a = str;
        this.c.b = false;
        this.c.c = false;
        this.c.d = false;
        this.f815a = new ArrayList<>();
        this.b = new ArrayList<>();
    }

    c(d dVar, h hVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("Mediator cannot be null!");
        }
        this.c = dVar;
        this.f815a = new ArrayList<>();
        Iterator it = this.c.e.iterator();
        while (it.hasNext()) {
            this.f815a.add(new a((b) it.next(), hVar));
        }
        this.b = new ArrayList<>();
        Iterator it2 = this.c.f.iterator();
        while (it2.hasNext()) {
            this.b.add(new c((d) it2.next(), hVar));
        }
    }

    public String a() {
        String str;
        synchronized (this.c) {
            str = this.c.f816a;
        }
        return str;
    }

    void a(a aVar) {
        synchronized (this.f815a) {
            this.f815a.add(aVar);
        }
    }

    void a(c cVar) {
        if (cVar != null) {
            synchronized (this.b) {
                if (!this.b.contains(cVar)) {
                    this.b.add(cVar);
                }
            }
        }
    }

    public a a(String str) {
        synchronized (this.f815a) {
            for (a aVar : this.f815a) {
                if (aVar.a().equals(str)) {
                    return aVar;
                }
            }
            return null;
        }
    }

    public List<a> b() {
        ArrayList<a> arrayList;
        synchronized (this.f815a) {
            arrayList = this.f815a;
        }
        return arrayList;
    }

    private int c(boolean z) {
        int i;
        synchronized (this.b) {
            int size = this.b.size() - (z ? 2 : 0);
            for (c cVar : this.b) {
                if (cVar != null && cVar.e()) {
                    size--;
                }
            }
            i = size < 0 ? 0 : size;
        }
        return i;
    }

    public int c() {
        return c(true);
    }

    public boolean d() {
        boolean z;
        synchronized (this.c) {
            if (!this.c.b && c(false) <= 2) {
                this.c.b = true;
            }
            z = this.c.b;
        }
        return z;
    }

    public boolean e() {
        boolean z;
        synchronized (this.c) {
            if (!this.c.c) {
                this.c.c = true;
                Iterator<a> it = this.f815a.iterator();
                while (it.hasNext()) {
                    if (!it.next().b()) {
                        this.c.c = false;
                        break;
                    }
                }
            }
            z = this.c.c;
        }
        return z;
    }

    public void a(boolean z) {
        synchronized (this.c) {
            this.c.b = z;
        }
    }

    boolean f() {
        synchronized (this.c) {
            if (this.c.c) {
                return false;
            }
            return e();
        }
    }

    d g() {
        d dVar;
        synchronized (this.c) {
            this.c.e = new ArrayList();
            this.c.f = new ArrayList();
            Iterator<a> it = this.f815a.iterator();
            while (it.hasNext()) {
                this.c.e.add(it.next().f());
            }
            Iterator<c> it2 = this.b.iterator();
            while (it2.hasNext()) {
                this.c.f.add(it2.next().g());
            }
            dVar = this.c;
        }
        return dVar;
    }

    void b(boolean z) {
        synchronized (this.c) {
            this.c.c = z;
            boolean zE = e();
            this.c.c = z || zE;
        }
    }
}
