package com.amazonaws.javax.xml.transform;

/* JADX INFO: compiled from: FactoryFinder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c extends Error {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Exception f142a;

    c(String str, Exception exc) {
        super(str);
        this.f142a = exc;
    }

    Exception a() {
        return this.f142a;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f142a;
    }
}
