package com.topfreegames.e.a;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: TopFacebookManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1499a;
    private List<String> b;
    private h c;
    private long d;
    private Object e;

    public c(a aVar, List<String> list, h hVar, long j, Object obj) {
        this.f1499a = aVar;
        this.b = null;
        this.c = null;
        this.d = 0L;
        this.b = list;
        this.c = hVar;
        this.d = j;
        this.e = obj;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean call() throws InterruptedException {
        this.f1499a.b((List<String>) this.b, this.c, this.d, this.e);
        return true;
    }
}
