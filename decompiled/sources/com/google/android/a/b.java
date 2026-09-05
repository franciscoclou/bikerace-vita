package com.google.android.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: compiled from: GCMBroadcastReceiver.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Log.v("GCMBroadcastReceiver", "onReceive: " + intent.getAction());
        String strA = a(context);
        Log.v("GCMBroadcastReceiver", "GCM IntentService class: " + strA);
        a.a(context, intent, strA);
        setResult(-1, null, null);
    }

    protected String a(Context context) {
        return context.getPackageName() + ".GCMIntentService";
    }
}
