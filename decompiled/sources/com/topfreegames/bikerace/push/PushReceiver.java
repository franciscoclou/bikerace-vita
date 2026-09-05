package com.topfreegames.bikerace.push;

import android.content.Context;
import android.content.Intent;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PushReceiver extends com.google.android.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f1346a = PushReceiver.class.toString();

    public PushReceiver() {
        super(c.d());
    }

    @Override // com.google.android.a.a
    public void b(Context context, String str) {
        if (c.a()) {
            System.err.println("Messaging registration error: " + str);
        }
        e.d(context);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009b  */
    @Override // com.google.android.a.a
    protected synchronized void a(Context context, Intent intent) {
        String str;
        String string = intent.getExtras().getString(c.j());
        if (c.a()) {
            System.out.println(string);
        }
        if (string != null) {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) getApplication();
            bikeRaceApplication.c().c(true);
            try {
                if (bikeRaceApplication.a().h() != com.topfreegames.bikerace.i.b.ENGLISH) {
                    String str2 = string.split(" ")[0];
                    if (string.contains("challenge")) {
                        str = String.format(context.getString(2131100117), str2);
                    } else if (string.contains("poked")) {
                        str = String.format(context.getString(2131100118), str2);
                    } else if (string.contains("playing")) {
                        str = String.format(context.getString(2131100119, str2), new Object[0]);
                    } else {
                        str = string;
                    }
                } else {
                    str = string;
                }
            } catch (Exception e) {
                str = string;
            }
            f.a(context, str);
        }
        e.d(context);
    }

    @Override // com.google.android.a.a
    public void c(Context context, String str) {
        if (c.a()) {
            System.out.println("Your registration ID is: " + str);
        }
        e.c(context, str);
        e.d(context);
    }

    @Override // com.google.android.a.a
    protected void d(Context context, String str) {
        if (c.a()) {
            System.out.println("Unregistered ID: " + str);
        }
        e.d(context);
    }
}
