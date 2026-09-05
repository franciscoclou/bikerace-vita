package com.applovin.a;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static k[] f157a = new k[0];
    private static final Object b = new Object();

    public static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        k kVarB = b(context);
        if (kVarB != null) {
            kVarB.j();
        } else {
            Log.e("AppLovinSdk", "Unable to initialize AppLovin SDK: SDK object not created");
        }
    }

    public static k b(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return b(m.a(context), m.b(context), context);
    }

    public static k b(String str, l lVar, Context context) {
        k dVar;
        synchronized (b) {
            if (f157a.length == 1 && f157a[0].a().equals(str)) {
                dVar = f157a[0];
            } else {
                k[] kVarArr = f157a;
                int length = kVarArr.length;
                for (int i = 0; i < length; i++) {
                    dVar = kVarArr[i];
                    if (!dVar.a().equals(str)) {
                    }
                }
                try {
                    dVar = new com.applovin.impl.a.d();
                    dVar.a(str, lVar, context.getApplicationContext());
                    k[] kVarArr2 = new k[f157a.length + 1];
                    System.arraycopy(f157a, 0, kVarArr2, 0, f157a.length);
                    kVarArr2[f157a.length] = dVar;
                    f157a = kVarArr2;
                } catch (Throwable th) {
                    Log.e("AppLovinSdk", "Failed to create AppLovin SDK. Try cleaning application data and starting the applion again.", th);
                    throw new RuntimeException("Unable to create AppLovin SDK");
                }
            }
        }
        return dVar;
    }

    public abstract String a();

    protected abstract void a(String str, l lVar, Context context);

    public abstract boolean c();

    public abstract e d();

    public abstract j f();

    public abstract void j();
}
