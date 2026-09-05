package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flurry.sdk.by;
import com.flurry.sdk.ex;
import com.flurry.sdk.fh;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class InstallReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f502a = InstallReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ex.a(4, f502a, "Received an Install nofication of " + intent.getAction());
        String string = intent.getExtras().getString("referrer");
        ex.a(4, f502a, "Received an Install referrer of " + string);
        if (string == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            ex.a(5, f502a, "referrer is null");
            return;
        }
        if (!string.contains("=")) {
            ex.a(4, f502a, "referrer is before decoding: " + string);
            string = fh.c(string);
            ex.a(4, f502a, "referrer is: " + string);
        }
        new by(context).a(string);
    }
}
