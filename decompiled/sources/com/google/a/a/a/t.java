package com.google.a.a.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/* JADX INFO: compiled from: GANetworkReceiver.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class t extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f598a = t.class.getName();
    private final aq b;

    t(aq aqVar) {
        this.b = aqVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            Bundle extras = intent.getExtras();
            Boolean boolValueOf = Boolean.FALSE;
            if (extras != null) {
                boolValueOf = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            }
            this.b.a(!boolValueOf.booleanValue());
            return;
        }
        if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(f598a)) {
            this.b.e();
        }
    }

    public void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter2.addCategory(context.getPackageName());
        context.registerReceiver(this, intentFilter2);
    }

    public static void b(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(f598a, true);
        context.sendBroadcast(intent);
    }
}
