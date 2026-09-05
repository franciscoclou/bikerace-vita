package com.b.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class o extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f345a;

    o(bc bcVar) {
        this.f345a = bcVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.f345a.v = true;
    }
}
