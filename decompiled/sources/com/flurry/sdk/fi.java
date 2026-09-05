package com.flurry.sdk;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class fi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f566a = fi.class.getSimpleName();
    PrintStream i;
    PrintWriter j;

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Throwable th) {
            if (this.i != null) {
                th.printStackTrace(this.i);
            } else if (this.j != null) {
                th.printStackTrace(this.j);
            } else {
                th.printStackTrace();
            }
            ex.a(6, f566a, "", th);
        }
    }
}
