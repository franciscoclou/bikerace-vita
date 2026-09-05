package com.b.a.a;

import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bq implements bt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f281a = true;
    private /* synthetic */ StringBuilder b;

    bq(bp bpVar, StringBuilder sb) {
        this.b = sb;
    }

    @Override // com.b.a.a.bt
    public final void a(InputStream inputStream, int i) {
        if (this.f281a) {
            this.f281a = false;
        } else {
            this.b.append(", ");
        }
        this.b.append(i);
    }
}
