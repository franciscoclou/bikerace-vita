package com.amazonaws;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private T f115a;
    private l b;

    public T a() {
        return this.f115a;
    }

    public void a(l lVar) {
        this.b = lVar;
    }

    public void a(T t) {
        this.f115a = t;
    }

    public l b() {
        return this.b;
    }

    public String c() {
        if (this.b == null) {
            return null;
        }
        return this.b.a();
    }
}
