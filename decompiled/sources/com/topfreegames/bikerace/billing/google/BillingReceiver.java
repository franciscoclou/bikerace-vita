package com.topfreegames.bikerace.billing.google;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BillingReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(action)) {
            a(context, intent.getStringExtra("inapp_signed_data"), intent.getStringExtra("inapp_signature"));
            return;
        }
        if ("com.android.vending.billing.IN_APP_NOTIFY".equals(action)) {
            a(context, intent.getStringExtra("notification_id"));
        } else if ("com.android.vending.billing.RESPONSE_CODE".equals(action)) {
            a(context, intent.getLongExtra("request_id", -1L), intent.getIntExtra("response_code", c.RESULT_ERROR.ordinal()));
        } else {
            Log.w("BillingReceiver", "unexpected action: " + action);
        }
    }

    private void a(Context context, String str, String str2) {
        Intent intent = new Intent("com.android.vending.billing.PURCHASE_STATE_CHANGED");
        intent.setClass(context, BillingService.class);
        intent.putExtra("inapp_signed_data", str);
        intent.putExtra("inapp_signature", str2);
        context.startService(intent);
    }

    private void a(Context context, String str) {
        Intent intent = new Intent("com.topfreegames.billing.dungeons.GET_PURCHASE_INFORMATION");
        intent.setClass(context, BillingService.class);
        intent.putExtra("notification_id", str);
        context.startService(intent);
    }

    private void a(Context context, long j, int i) {
        Intent intent = new Intent("com.android.vending.billing.RESPONSE_CODE");
        intent.setClass(context, BillingService.class);
        intent.putExtra("request_id", j);
        intent.putExtra("response_code", i);
        context.startService(intent);
    }
}
