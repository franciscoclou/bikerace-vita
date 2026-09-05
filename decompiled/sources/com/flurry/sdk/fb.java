package com.flurry.sdk;

import android.content.Context;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class fb implements fc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final fc f561a;

    public fb(fc fcVar) {
        this.f561a = fcVar;
    }

    @Override // com.flurry.sdk.fc
    public void f(Context context) {
        if (this.f561a != null) {
            this.f561a.f(context);
        }
    }

    @Override // com.flurry.sdk.fc
    public void g(Context context) {
        if (this.f561a != null) {
            this.f561a.g(context);
        }
    }
}
