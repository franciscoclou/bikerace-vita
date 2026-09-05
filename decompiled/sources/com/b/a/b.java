package com.b.a;

import com.b.a.a.cm;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class b implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f332a;

    b(bc bcVar) {
        this.f332a = bcVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean call() {
        try {
            boolean zDelete = this.f332a.l.delete();
            cm.a().b().a("Crashlytics", "Initialization marker file removed: " + zDelete);
            return Boolean.valueOf(zDelete);
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Problem encountered deleting Crashlytics initialization marker.", e);
            return false;
        }
    }
}
