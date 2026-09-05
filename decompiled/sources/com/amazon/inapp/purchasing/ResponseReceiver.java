package com.amazon.inapp.purchasing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ResponseReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ImplementationFactory.getResponseHandler().handleResponse(context, intent);
    }
}
