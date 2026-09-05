package com.amazonaws.f.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Throwable f118a;

    public b(String str) {
        super(str);
    }

    public b(Throwable th) {
        super(th.getMessage());
        this.f118a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f118a;
    }
}
