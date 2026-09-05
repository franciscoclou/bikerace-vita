package com.chartboost.sdk.Libraries;

import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.impl.an;
import com.chartboost.sdk.impl.ao;
import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f380a = null;

    private static String b() {
        if (d.c()) {
            return null;
        }
        return b.b(b.a(f()));
    }

    public static String a() {
        return b.b(c());
    }

    private static byte[] c() {
        String strD = d();
        if (strD == null || "9774d56d682e549c".equals(strD)) {
            strD = e();
        }
        String strB = b();
        ao aoVar = new ao();
        aoVar.put("uuid", strD);
        aoVar.put("macid", strB);
        return new an().a(aoVar);
    }

    private static String d() {
        if (d.c()) {
            return null;
        }
        return Settings.Secure.getString(Chartboost.sharedChartboost().getContext().getContentResolver(), "android_id");
    }

    private static String e() {
        if (f380a == null) {
            SharedPreferences sharedPreferencesA = d.a();
            f380a = sharedPreferencesA.getString("cbUUID", null);
            if (f380a == null) {
                f380a = UUID.randomUUID().toString();
                SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                editorEdit.putString("cbUUID", f380a);
                editorEdit.commit();
            }
        }
        return f380a;
    }

    private static byte[] f() {
        try {
            String macAddress = ((WifiManager) Chartboost.sharedChartboost().getContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null || macAddress.equals("")) {
                return null;
            }
            String[] strArrSplit = macAddress.split(":");
            byte[] bArr = new byte[6];
            for (int i = 0; i < strArrSplit.length; i++) {
                bArr[i] = Integer.valueOf(Integer.parseInt(strArrSplit[i], 16)).byteValue();
            }
            return bArr;
        } catch (Exception e) {
            return null;
        }
    }
}
