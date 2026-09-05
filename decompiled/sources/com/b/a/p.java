package com.b.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class p extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f346a;

    p(bc bcVar) {
        this.f346a = bcVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.f346a.v = false;
    }
}
