package a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.facebook.AppEventsConstants;
import com.flurry.android.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* JADX INFO: compiled from: OpenUDID.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f0a;

    private static void b(String str) {
    }

    public static void a(Context context) {
        if (f0a == null) {
            try {
                context = context.createPackageContext("net.openudid.android", 2);
            } catch (PackageManager.NameNotFoundException e) {
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("openudid_prefs", 1);
            String string = sharedPreferences.getString("openudid", null);
            if (string == null) {
                b(context);
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("openudid", f0a);
                editorEdit.commit();
                return;
            }
            f0a = string;
        }
    }

    public static String a() {
        return f0a;
    }

    public static String a(String str) {
        return c(String.format("%s.%s", str, a()));
    }

    private static void b(Context context) {
        c(context);
        if (f0a == null) {
            String lowerCase = Settings.Secure.getString(context.getContentResolver(), "android_id").toLowerCase();
            if (lowerCase != null && lowerCase.length() > 14 && !lowerCase.equals("9774d56d682e549c")) {
                f0a = "ANDROID:" + lowerCase;
                return;
            }
            b();
            b(f0a);
            b("done");
        }
    }

    private static void c(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            b(String.format("%s", connectionInfo.getMacAddress()));
            String macAddress = connectionInfo.getMacAddress();
            if (macAddress != null) {
                f0a = "WIFIMAC:" + macAddress;
            }
        } catch (Exception e) {
        }
    }

    private static String c(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(str.getBytes(), 0, str.length());
        byte[] bArrDigest = messageDigest.digest();
        String str2 = new String();
        for (byte b : bArrDigest) {
            int i = b & Constants.UNKNOWN;
            if (i <= 15) {
                str2 = String.valueOf(str2) + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            str2 = String.valueOf(str2) + Integer.toHexString(i);
        }
        return str2.toUpperCase();
    }

    private static void b() {
        f0a = c(UUID.randomUUID().toString());
    }
}
