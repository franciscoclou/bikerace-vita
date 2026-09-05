package com.b.a;

import com.b.a.a.cm;
import java.io.IOException;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class a implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f252a;

    a(bc bcVar) {
        this.f252a = bcVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws IOException {
        this.f252a.l.createNewFile();
        cm.a().b().a("Crashlytics", "Initialization marker file created.");
        return null;
    }
}
