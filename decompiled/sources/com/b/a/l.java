package com.b.a;

import com.b.a.a.cm;
import java.util.concurrent.Callable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class l<T> implements Callable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Callable f344a;

    l(bc bcVar, Callable callable) {
        this.f344a = callable;
    }

    @Override // java.util.concurrent.Callable
    public final T call() {
        try {
            return (T) this.f344a.call();
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Failed to execute task.", e);
            return null;
        }
    }
}
