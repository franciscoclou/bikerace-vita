package com.b.a;

import java.io.File;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class g extends com.b.a.a.az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ File f340a;

    g(bc bcVar, File file) {
        this.f340a = file;
    }

    @Override // com.b.a.a.az
    public final void a() {
        z zVarT = d.a().t();
        if (zVarT != null) {
            new af(zVarT).a(new ad(this.f340a, bc.e));
        }
    }
}
