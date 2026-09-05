package com.google.android.a;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GCMBaseIntentService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class a extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static PowerManager.WakeLock f731a;
    private static final Object b = a.class;
    private static int d = 0;
    private static final Random e = new Random();
    private static final int f = (int) TimeUnit.SECONDS.toMillis(3600);
    private static final String g = Long.toBinaryString(e.nextLong());
    private final String c;

    protected abstract void a(Context context, Intent intent);

    protected abstract void b(Context context, String str);

    protected abstract void c(Context context, String str);

    protected abstract void d(Context context, String str);

    /* JADX WARN: Illegal instructions before constructor call */
    protected a(String str) {
        StringBuilder sbAppend = new StringBuilder().append("GCMIntentService-").append(str).append("-");
        int i = d + 1;
        d = i;
        super(sbAppend.append(i).toString());
        this.c = str;
    }

    protected void a(Context context, int i) {
    }

    protected boolean a(Context context, String str) {
        return true;
    }

    @Override // android.app.IntentService
    public final void onHandleIntent(Intent intent) {
        try {
            Context applicationContext = getApplicationContext();
            String action = intent.getAction();
            if (action.equals("com.google.android.c2dm.intent.REGISTRATION")) {
                b(applicationContext, intent);
            } else if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                String stringExtra = intent.getStringExtra("message_type");
                if (stringExtra == null) {
                    a(applicationContext, intent);
                } else if (stringExtra.equals("deleted_messages")) {
                    String stringExtra2 = intent.getStringExtra("total_deleted");
                    if (stringExtra2 != null) {
                        try {
                            int i = Integer.parseInt(stringExtra2);
                            Log.v("GCMBaseIntentService", "Received deleted messages notification: " + i);
                            a(applicationContext, i);
                        } catch (NumberFormatException e2) {
                            Log.e("GCMBaseIntentService", "GCM returned invalid number of deleted messages: " + stringExtra2);
                        }
                    }
                } else {
                    Log.e("GCMBaseIntentService", "Received unknown special message: " + stringExtra);
                }
            } else if (action.equals("com.google.android.gcm.intent.RETRY")) {
                String stringExtra3 = intent.getStringExtra("token");
                if (!g.equals(stringExtra3)) {
                    Log.e("GCMBaseIntentService", "Received invalid token: " + stringExtra3);
                    synchronized (b) {
                        if (f731a != null) {
                            Log.v("GCMBaseIntentService", "Releasing wakelock");
                            f731a.release();
                        } else {
                            Log.e("GCMBaseIntentService", "Wakelock reference is null");
                        }
                    }
                    return;
                }
                if (c.d(applicationContext)) {
                    c.b(applicationContext);
                } else {
                    c.b(applicationContext, this.c);
                }
            }
            synchronized (b) {
                if (f731a != null) {
                    Log.v("GCMBaseIntentService", "Releasing wakelock");
                    f731a.release();
                } else {
                    Log.e("GCMBaseIntentService", "Wakelock reference is null");
                }
            }
        } catch (Throwable th) {
            synchronized (b) {
                if (f731a != null) {
                    Log.v("GCMBaseIntentService", "Releasing wakelock");
                    f731a.release();
                } else {
                    Log.e("GCMBaseIntentService", "Wakelock reference is null");
                }
                throw th;
            }
        }
    }

    static void a(Context context, Intent intent, String str) {
        synchronized (b) {
            if (f731a == null) {
                f731a = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "GCM_LIB");
            }
        }
        Log.v("GCMBaseIntentService", "Acquiring wakelock");
        f731a.acquire();
        intent.setClassName(context, str);
        context.startService(intent);
    }

    private void b(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("registration_id");
        String stringExtra2 = intent.getStringExtra("error");
        String stringExtra3 = intent.getStringExtra("unregistered");
        Log.d("GCMBaseIntentService", "handleRegistration: registrationId = " + stringExtra + ", error = " + stringExtra2 + ", unregistered = " + stringExtra3);
        if (stringExtra != null) {
            c.g(context);
            c.a(context, stringExtra);
            c(context, stringExtra);
            return;
        }
        if (stringExtra3 != null) {
            c.g(context);
            d(context, c.e(context));
            return;
        }
        Log.d("GCMBaseIntentService", "Registration error: " + stringExtra2);
        if ("SERVICE_NOT_AVAILABLE".equals(stringExtra2)) {
            if (a(context, stringExtra2)) {
                int iH = c.h(context);
                int iNextInt = e.nextInt(iH) + (iH / 2);
                Log.d("GCMBaseIntentService", "Scheduling registration retry, backoff = " + iNextInt + " (" + iH + ")");
                Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
                intent2.putExtra("token", g);
                ((AlarmManager) context.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + ((long) iNextInt), PendingIntent.getBroadcast(context, 0, intent2, 0));
                if (iH < f) {
                    c.a(context, iH * 2);
                    return;
                }
                return;
            }
            Log.d("GCMBaseIntentService", "Not retrying failed operation");
            return;
        }
        b(context, stringExtra2);
    }
}
