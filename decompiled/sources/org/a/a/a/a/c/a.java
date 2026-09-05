package org.a.a.a.a.c;

import java.util.Enumeration;
import java.util.Hashtable;
import org.a.a.a.a.h;
import org.a.a.a.a.m;

/* JADX INFO: compiled from: MemoryPersistence.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Hashtable f1599a;

    @Override // org.a.a.a.a.h
    public void a() {
        this.f1599a.clear();
    }

    @Override // org.a.a.a.a.h
    public Enumeration b() {
        return this.f1599a.keys();
    }

    @Override // org.a.a.a.a.h
    public m a(String str) {
        return (m) this.f1599a.get(str);
    }

    @Override // org.a.a.a.a.h
    public void a(String str, String str2) {
        this.f1599a = new Hashtable();
    }

    @Override // org.a.a.a.a.h
    public void a(String str, m mVar) {
        this.f1599a.put(str, mVar);
    }

    @Override // org.a.a.a.a.h
    public void b(String str) {
        this.f1599a.remove(str);
    }

    @Override // org.a.a.a.a.h
    public void c() {
        this.f1599a.clear();
    }

    @Override // org.a.a.a.a.h
    public boolean c(String str) {
        return this.f1599a.containsKey(str);
    }
}
