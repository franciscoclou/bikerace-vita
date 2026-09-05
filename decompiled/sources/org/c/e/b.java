package org.c.e;

import java.util.concurrent.TimeUnit;
import org.c.d.f;
import org.c.d.h;

/* JADX INFO: compiled from: OAuth10aServiceImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b extends h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f1627a;
    private final TimeUnit b;

    public b(int i, TimeUnit timeUnit) {
        this.f1627a = i;
        this.b = timeUnit;
    }

    @Override // org.c.d.h
    public void a(f fVar) {
        fVar.a(this.f1627a, this.b);
    }
}
