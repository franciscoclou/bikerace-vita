package com.topfreegames.bikerace.billing.google;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BillingService extends Service implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static com.a.a.a.a f1145a;
    private static LinkedList<d> b = new LinkedList<>();
    private static HashMap<Long, d> c = new HashMap<>();

    public void a(Context context) {
        attachBaseContext(context);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        try {
            a(intent, i);
        } catch (Exception e) {
            Log.e("BillingService", "Exception while starting service: " + e.toString());
        }
    }

    public void a(Intent intent, int i) {
        String action = intent.getAction();
        if ("com.topfreegames.billing.CONFIRM_NOTIFICATION".equals(action)) {
            a(i, intent.getStringArrayExtra("notification_id"));
            return;
        }
        if ("com.topfreegames.billing.dungeons.GET_PURCHASE_INFORMATION".equals(action)) {
            b(i, new String[]{intent.getStringExtra("notification_id")});
        } else if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(action)) {
            a(i, intent.getStringExtra("inapp_signed_data"), intent.getStringExtra("inapp_signature"));
        } else if ("com.android.vending.billing.RESPONSE_CODE".equals(action)) {
            a(intent.getLongExtra("request_id", -1L), c.a(intent.getIntExtra("response_code", c.RESULT_BILLING_UNAVAILABLE.ordinal())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        try {
            if (bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), this, 1)) {
                return true;
            }
            Log.e("BillingService", "Could not bind to service.");
        } catch (SecurityException e) {
            Log.e("BillingService", "Security exception: " + e);
        }
        return false;
    }

    public boolean a() {
        return new e(this).b();
    }

    public boolean a(String str, String str2) {
        return new h(this, str, str2).b();
    }

    public boolean b() {
        return new i(this).b();
    }

    private boolean a(int i, String[] strArr) {
        return new f(this, i, strArr).b();
    }

    private boolean b(int i, String[] strArr) {
        return new g(this, i, strArr).b();
    }

    private void a(int i, String str, String str2) {
        ArrayList<o> arrayListA = n.a(str, str2);
        if (arrayListA != null) {
            ArrayList arrayList = new ArrayList();
            for (o oVar : arrayListA) {
                if (oVar.b != null) {
                    arrayList.add(oVar.b);
                }
                m.a(this, oVar.f1157a, oVar.c, oVar.d, oVar.e, oVar.f);
            }
            if (!arrayList.isEmpty()) {
                a(i, (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
    }

    private void a(long j, c cVar) {
        d dVar = c.get(Long.valueOf(j));
        Log.d("BillingService", "response received");
        if (dVar != null) {
            dVar.a(cVar);
        }
        c.remove(Long.valueOf(j));
    }

    private void h() {
        int iA = -1;
        while (true) {
            d dVarPeek = b.peek();
            if (dVarPeek != null) {
                if (dVarPeek.c()) {
                    b.remove();
                    if (iA < dVarPeek.a()) {
                        iA = dVarPeek.a();
                    }
                } else {
                    g();
                    return;
                }
            } else {
                if (iA >= 0) {
                    stopSelf(iA);
                    return;
                }
                return;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            f1145a = com.a.a.a.b.a(iBinder);
            h();
        } catch (Exception e) {
            Log.e("BillingService", "An exception occured when the service connected: " + e.toString());
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.w("BillingService", "Billing service disconnected");
        f1145a = null;
    }

    public void c() {
        try {
            unbindService(this);
            stopSelf();
        } catch (IllegalArgumentException e) {
        }
    }
}
