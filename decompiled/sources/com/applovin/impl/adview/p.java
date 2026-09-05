package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f242a;

    p(a aVar) {
        this.f242a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f242a.h.setVisibility(8);
    }
}
