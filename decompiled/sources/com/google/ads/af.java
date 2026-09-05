package com.google.ads;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class af implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<com.google.ads.internal.d> f620a;

    public af(com.google.ads.internal.d dVar) {
        this.f620a = new WeakReference<>(dVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar = this.f620a.get();
        if (dVar == null) {
            com.google.ads.util.b.a("The ad must be gone, so cancelling the refresh timer.");
        } else {
            dVar.A();
        }
    }
}
