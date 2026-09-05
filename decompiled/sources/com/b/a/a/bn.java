package com.b.a.a;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class bn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f278a = Pattern.compile("[^\\p{Alnum}]");
    private static final String b = Pattern.quote("/");
    private final ReentrantLock c = new ReentrantLock();
    private final boolean d;
    private final boolean e;
    private final Context f;

    public bn(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        }
        this.f = context;
        this.d = ba.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
        if (!this.d) {
            cm.a().b().a("Crashlytics", "Device ID collection disabled for " + context.getPackageName());
        }
        this.e = ba.a(context, "com.crashlytics.CollectUserIdentifiers", true);
        if (!this.e) {
            cm.a().b().a("Crashlytics", "User information collection disabled for " + context.getPackageName());
        }
    }

    public final boolean a() {
        return this.e;
    }

    private boolean a(String str) {
        return this.f.checkCallingPermission(str) == 0;
    }

    private static String b(String str) {
        if (str == null) {
            return null;
        }
        return f278a.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public final String b() {
        String strJ = cm.a().j();
        if (strJ == null) {
            SharedPreferences sharedPreferencesA = ba.a();
            String string = sharedPreferencesA.getString("crashlytics.installation.id", null);
            if (string == null) {
                return a(sharedPreferencesA);
            }
            return string;
        }
        return strJ;
    }

    public final String c() {
        return String.format(Locale.US, "%s/%s", c(Build.VERSION.RELEASE), c(Build.VERSION.INCREMENTAL));
    }

    public final String d() {
        return String.format(Locale.US, "%s/%s", c(Build.MANUFACTURER), c(Build.MODEL));
    }

    private static String c(String str) {
        return str.replaceAll(b, "");
    }

    public final String e() {
        if (!this.d) {
            return "";
        }
        String strG = g();
        if (strG == null) {
            SharedPreferences sharedPreferencesA = ba.a();
            String string = sharedPreferencesA.getString("crashlytics.installation.id", null);
            if (string == null) {
                return a(sharedPreferencesA);
            }
            return string;
        }
        return strG;
    }

    private String a(SharedPreferences sharedPreferences) {
        this.c.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = b(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            return string;
        } finally {
            this.c.unlock();
        }
    }

    public final Map<bo, String> f() {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        TelephonyManager telephonyManager;
        String strB = null;
        HashMap map = new HashMap();
        a(map, bo.ANDROID_ID, g());
        a(map, bo.ANDROID_DEVICE_ID, (this.d && a("android.permission.READ_PHONE_STATE") && (telephonyManager = (TelephonyManager) this.f.getSystemService("phone")) != null) ? b(telephonyManager.getDeviceId()) : null);
        a(map, bo.ANDROID_SERIAL, i());
        bo boVar = bo.WIFI_MAC_ADDRESS;
        if (this.d && a("android.permission.ACCESS_WIFI_STATE") && (wifiManager = (WifiManager) this.f.getSystemService("wifi")) != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
            strB = b(connectionInfo.getMacAddress());
        }
        a(map, boVar, strB);
        a(map, bo.BLUETOOTH_MAC_ADDRESS, h());
        return Collections.unmodifiableMap(map);
    }

    private static void a(Map<bo, String> map, bo boVar, String str) {
        if (str != null) {
            map.put(boVar, str);
        }
    }

    public final String g() {
        if (!this.d) {
            return null;
        }
        String string = Settings.Secure.getString(this.f.getContentResolver(), "android_id");
        if ("9774d56d682e549c".equals(string)) {
            return null;
        }
        return b(string);
    }

    public final String h() {
        if (this.d && a("android.permission.BLUETOOTH")) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    b(defaultAdapter.getAddress());
                    return null;
                }
                return null;
            } catch (Exception e) {
                cm.a().b().a("Crashlytics", "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", e);
                return null;
            }
        }
        return null;
    }

    private String i() {
        if (this.d && Build.VERSION.SDK_INT >= 9) {
            try {
                return b((String) Build.class.getField("SERIAL").get(null));
            } catch (Exception e) {
                cm.a().b().a("Crashlytics", "Could not retrieve android.os.Build.SERIAL value", e);
            }
        }
        return null;
    }
}
