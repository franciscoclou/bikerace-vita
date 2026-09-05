package com.b.a.a;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class z<V> extends ad<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Closeable f314a;
    private final boolean b;

    protected z(Closeable closeable, boolean z) {
        this.f314a = closeable;
        this.b = z;
    }

    @Override // com.b.a.a.ad
    protected final void a() throws IOException {
        if (this.f314a instanceof Flushable) {
            ((Flushable) this.f314a).flush();
        }
        if (this.b) {
            try {
                this.f314a.close();
            } catch (IOException e) {
            }
        } else {
            this.f314a.close();
        }
    }
}
