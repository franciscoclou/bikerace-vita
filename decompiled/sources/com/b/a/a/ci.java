package com.b.a.a;

import android.content.Context;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ci {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f293a;
    private boolean b;

    protected abstract void e();

    protected final synchronized void b(Context context) {
        if (!this.b) {
            if (context == null) {
                throw new IllegalArgumentException("context cannot be null.");
            }
            this.f293a = new cq(context.getApplicationContext(), y());
            this.b = true;
            e();
        }
    }

    public final Context w() {
        return this.f293a;
    }

    public final synchronized boolean x() {
        return this.b;
    }

    public final String y() {
        return getClass().getSimpleName().toLowerCase();
    }
}
