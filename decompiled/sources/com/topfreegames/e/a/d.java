package com.topfreegames.e.a;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: TopFacebookManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1500a;
    private String b;
    private boolean c;
    private m d;
    private long e;
    private Object f;

    public d(a aVar, String str, boolean z, m mVar, long j, Object obj) {
        this.f1500a = aVar;
        this.b = str;
        this.c = z;
        this.d = mVar;
        this.f = obj;
        this.e = j;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean call() {
        this.f1500a.b(this.b, this.c, this.d, this.e, this.f);
        return true;
    }
}
