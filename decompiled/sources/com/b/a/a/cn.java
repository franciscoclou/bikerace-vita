package com.b.a.a;

import android.app.Application;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class cn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ cm f295a;

    private cn(cm cmVar) {
        this.f295a = cmVar;
    }

    /* synthetic */ cn(cm cmVar, byte b) {
        this(cmVar);
    }

    static /* synthetic */ void a(cn cnVar, Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks(new co(cnVar));
        }
    }
}
