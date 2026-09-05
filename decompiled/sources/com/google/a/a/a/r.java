package com.google.a.a.a;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: compiled from: ExceptionReporter.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class r implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Thread.UncaughtExceptionHandler f597a;
    private final au b;
    private final aq c;
    private q d;

    public r(au auVar, aq aqVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (auVar == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (aqVar == null) {
            throw new NullPointerException("serviceManager cannot be null");
        }
        this.f597a = uncaughtExceptionHandler;
        this.b = auVar;
        this.c = aqVar;
        this.d = new at(context, new ArrayList());
        ah.c("ExceptionReporter created, original handler is " + (uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName()));
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String strA = "UncaughtException";
        if (this.d != null) {
            strA = this.d.a(thread != null ? thread.getName() : null, th);
        }
        ah.c("Tracking Exception: " + strA);
        this.b.a(ak.a(strA, (Boolean) true).a());
        this.c.c();
        if (this.f597a != null) {
            ah.c("Passing exception to original handler.");
            this.f597a.uncaughtException(thread, th);
        }
    }
}
