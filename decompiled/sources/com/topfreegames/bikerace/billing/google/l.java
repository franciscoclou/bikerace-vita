package com.topfreegames.bikerace.billing.google;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: PurchaseObserver.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class l {
    private static final Class<?>[] e = {IntentSender.class, Intent.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Activity f1152a;
    private final Handler b;
    private Method c;
    private Object[] d = new Object[5];

    public abstract void a(b bVar, String str, int i, long j, String str2);

    public abstract void a(h hVar, c cVar);

    public abstract void a(i iVar, c cVar);

    public abstract void a(boolean z);

    public l(Activity activity, Handler handler) {
        this.f1152a = activity;
        this.b = handler;
        a();
    }

    private void a() {
        try {
            this.c = this.f1152a.getClass().getMethod("startIntentSender", e);
        } catch (NoSuchMethodException e2) {
            this.c = null;
        } catch (SecurityException e3) {
            this.c = null;
        }
    }

    void a(PendingIntent pendingIntent, Intent intent) {
        if (this.c != null) {
            try {
                this.d[0] = pendingIntent.getIntentSender();
                this.d[1] = intent;
                this.d[2] = 0;
                this.d[3] = 0;
                this.d[4] = 0;
                Log.d("PurchaseObserver", "startBuyPageActivity");
                this.c.invoke(this.f1152a, this.d);
                return;
            } catch (Exception e2) {
                Log.e("PurchaseObserver", "error starting activity", e2);
                return;
            }
        }
        try {
            pendingIntent.send(this.f1152a, 0, intent);
        } catch (PendingIntent.CanceledException e3) {
            Log.e("PurchaseObserver", "error starting activity", e3);
        }
    }

    void b(final b bVar, final String str, final int i, final long j, final String str2) {
        this.b.post(new Runnable() { // from class: com.topfreegames.bikerace.billing.google.l.1
            @Override // java.lang.Runnable
            public void run() {
                l.this.a(bVar, str, i, j, str2);
            }
        });
    }
}
