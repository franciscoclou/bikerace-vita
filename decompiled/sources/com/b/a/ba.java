package com.b.a;

import android.content.Context;
import com.b.a.a.cm;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ba extends com.b.a.a.az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Context f333a;
    private /* synthetic */ float b;
    private /* synthetic */ CountDownLatch c;
    private /* synthetic */ d d;

    ba(d dVar, Context context, float f, CountDownLatch countDownLatch) {
        this.d = dVar;
        this.f333a = context;
        this.b = f;
        this.c = countDownLatch;
    }

    @Override // com.b.a.a.az
    public final void a() {
        try {
            if (this.d.b(this.f333a, this.b)) {
                this.d.d.e();
            }
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Problem encountered during Crashlytics initialization.", e);
        } finally {
            this.c.countDown();
        }
    }
}
