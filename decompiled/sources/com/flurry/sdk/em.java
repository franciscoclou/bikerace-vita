package com.flurry.sdk;

import android.telephony.TelephonyManager;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class em {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f538a = em.class.getSimpleName();
    private static byte[] b;

    public static byte[] a() {
        if (b != null) {
            return b;
        }
        if (eg.a().b().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return null;
        }
        b();
        return b;
    }

    private static void b() {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) eg.a().b().getSystemService("phone");
        if (telephonyManager != null && (deviceId = telephonyManager.getDeviceId()) != null && deviceId.trim().length() > 0) {
            try {
                byte[] bArrD = fh.d(deviceId);
                if (bArrD != null && bArrD.length == 20) {
                    b = bArrD;
                } else {
                    ex.a(6, f538a, "sha1 is not 20 bytes long: " + Arrays.toString(bArrD));
                }
            } catch (Exception e) {
                ex.a(6, f538a, "Exception in generateHashedImei()");
            }
        }
    }
}
