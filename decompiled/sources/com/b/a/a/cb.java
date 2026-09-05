package com.b.a.a;

import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class cb extends ThreadLocal<ConcurrentLinkedQueue<com.crashlytics.android.internal.e>> {
    cb(bz bzVar) {
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ ConcurrentLinkedQueue<com.crashlytics.android.internal.e> initialValue() {
        return new ConcurrentLinkedQueue<>();
    }
}
