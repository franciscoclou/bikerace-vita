package com.b.a.a;

import android.os.Process;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class az implements Runnable {
    protected abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(10);
        a();
    }
}
