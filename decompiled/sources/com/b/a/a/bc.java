package com.b.a.a;

import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum bc {
    X86_32,
    X86_64,
    ARM_UNKNOWN,
    PPC,
    PPC64,
    ARMV6,
    ARMV7,
    UNKNOWN,
    ARMV7S,
    ARM64;

    private static final Map<String, bc> k;

    static {
        HashMap map = new HashMap(4);
        k = map;
        map.put("armeabi-v7a", ARMV7);
        k.put("armeabi", ARMV6);
        k.put("x86", X86_32);
    }

    static bc a() {
        String str = Build.CPU_ABI;
        if (TextUtils.isEmpty(str)) {
            cm.a().b().a("Crashlytics", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return UNKNOWN;
        }
        bc bcVar = k.get(str.toLowerCase(Locale.US));
        if (bcVar == null) {
            return UNKNOWN;
        }
        return bcVar;
    }
}
