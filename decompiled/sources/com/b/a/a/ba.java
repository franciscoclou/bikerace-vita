package com.b.a.a;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import com.flurry.android.Constants;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ba {
    private static Boolean b = null;
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long d = -1;
    private static Boolean e = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator<File> f270a = new bb();

    public static SharedPreferences a() {
        return cm.a().w().getSharedPreferences("com.crashlytics.prefs", 0);
    }

    private static String a(File file, String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            String[] strArrSplit = Pattern.compile("\\s*:\\s*").split(line, 2);
                            if (strArrSplit.length > 1 && strArrSplit[0].equals(str)) {
                                str2 = strArrSplit[1];
                                break;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cm.a().b().a("Crashlytics", "Error parsing " + file, e);
                            a(bufferedReader, "Failed to close system file reader.");
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        a(bufferedReader, "Failed to close system file reader.");
                        throw th;
                    }
                }
                a(bufferedReader, "Failed to close system file reader.");
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                a(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
        }
        return str2;
    }

    public static int b() {
        return bc.a().ordinal();
    }

    public static synchronized long c() {
        long jA;
        if (d == -1) {
            String strA = a(new File("/proc/meminfo"), "MemTotal");
            if (TextUtils.isEmpty(strA)) {
                jA = 0;
                d = jA;
            } else {
                String upperCase = strA.toUpperCase(Locale.US);
                try {
                    if (upperCase.endsWith("KB")) {
                        jA = a(upperCase, "KB", 1024);
                    } else if (upperCase.endsWith("MB")) {
                        jA = a(upperCase, "MB", 1048576);
                    } else if (upperCase.endsWith("GB")) {
                        jA = a(upperCase, "GB", 1073741824);
                    } else {
                        cm.a().b().a("Crashlytics", "Unexpected meminfo format while computing RAM: " + upperCase);
                        jA = 0;
                    }
                } catch (NumberFormatException e2) {
                    cm.a().b().a("Crashlytics", "Unexpected meminfo format while computing RAM: " + upperCase, e2);
                    jA = 0;
                }
                d = jA;
            }
            throw th;
        }
        return d;
    }

    private static long a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static ActivityManager.RunningAppProcessInfo a(String str, Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String a(InputStream inputStream) {
        Scanner scannerUseDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
    }

    public static String a(String str) {
        return a(str.getBytes(), "SHA-1");
    }

    private static String b(InputStream inputStream) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    return a(messageDigest.digest());
                }
            }
        } catch (Exception e2) {
            cm.a().b().a("Crashlytics", "Could not calculate hash for app icon.", e2);
            return "";
        }
    }

    private static String a(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException e2) {
            cm.a().b().a("Crashlytics", "Could not create hashing algorithm: " + str + ", returning empty string.", e2);
            return "";
        }
    }

    public static String a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
        }
        String string = sb.toString();
        if (string.length() > 0) {
            return a(string);
        }
        return null;
    }

    public static long a(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long b(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static float b(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return intentRegisterReceiver.getIntExtra("level", -1) / intentRegisterReceiver.getIntExtra("scale", -1);
    }

    public static boolean c(Context context) {
        return (d() || ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) == null) ? false : true;
    }

    public static void c(String str) {
        if (e(cm.a().w())) {
            cm.a().b().a("Crashlytics", str);
        }
    }

    public static void d(String str) {
        if (e(cm.a().w())) {
            cm.a().b().d("Crashlytics", str);
        }
    }

    public static void a(int i, String str) {
        if (e(cm.a().w())) {
            cm.a().b().a(4, "Crashlytics", str);
        }
    }

    public static boolean d(Context context) {
        if (e == null) {
            e = Boolean.valueOf(a(context, "com.crashlytics.SilenceCrashlyticsLogCat", false) ? false : true);
        }
        return e.booleanValue();
    }

    public static boolean e(Context context) {
        if (b == null) {
            b = Boolean.valueOf(a(context, "com.crashlytics.Trace", false));
        }
        return b.booleanValue();
    }

    public static boolean a(Context context, String str, boolean z) {
        Resources resources;
        if (context != null && (resources = context.getResources()) != null) {
            int iA = a(context, str, "bool");
            if (iA > 0) {
                return resources.getBoolean(iA);
            }
            int iA2 = a(context, str, "string");
            if (iA2 > 0) {
                return Boolean.parseBoolean(context.getString(iA2));
            }
            return z;
        }
        return z;
    }

    public static int a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        int i = context.getApplicationContext().getApplicationInfo().icon;
        return resources.getIdentifier(str, str2, i > 0 ? context.getResources().getResourcePackageName(i) : context.getPackageName());
    }

    public static boolean d() {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Settings.Secure.getString(cm.a().w().getContentResolver(), "android_id") == null;
    }

    public static boolean e() {
        boolean zD = d();
        String str = Build.TAGS;
        if ((zD || str == null || !str.contains("test-keys")) && !new File("/system/app/Superuser.apk").exists()) {
            return !zD && new File("/system/xbin/su").exists();
        }
        return true;
    }

    public static int f() {
        int i = d() ? 1 : 0;
        if (e()) {
            i |= 2;
        }
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            return i | 4;
        }
        return i;
    }

    public static int a(boolean z) {
        float fB = b(cm.a().w());
        if (!z) {
            return 1;
        }
        if (z && fB >= 99.0d) {
            return 3;
        }
        if (z && fB < 99.0d) {
            return 2;
        }
        return 0;
    }

    public static Cipher b(int i, String str) {
        if (str.length() < 32) {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(1, secretKeySpec);
            return cipher;
        } catch (GeneralSecurityException e2) {
            cm.a().b().a("Crashlytics", "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", e2);
            throw new RuntimeException(e2);
        }
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & Constants.UNKNOWN;
            cArr[i << 1] = c[i2 >>> 4];
            cArr[(i << 1) + 1] = c[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean f(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String a(Context context, String str) {
        int iA = a(context, str, "string");
        return iA > 0 ? context.getString(iA) : "";
    }

    public static void a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                cm.a().b().a("Crashlytics", str, e2);
            }
        }
    }

    public static void a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e2) {
                cm.a().b().a("Crashlytics", str, e2);
            }
        }
    }

    public static boolean e(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", Integer.valueOf(i)).replace(' ', '0');
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                return;
            }
        }
    }

    public static String g(Context context) throws Throwable {
        InputStream inputStreamOpenRawResource;
        Throwable th;
        String str = null;
        try {
            inputStreamOpenRawResource = context.getResources().openRawResource(h(context));
            try {
                try {
                    String strB = b(inputStreamOpenRawResource);
                    str = e(strB) ? null : strB;
                    a(inputStreamOpenRawResource, "Failed to close icon input stream.");
                } catch (Exception e2) {
                    e = e2;
                    cm.a().b().a("Crashlytics", "Could not calculate hash for app icon.", e);
                    a(inputStreamOpenRawResource, "Failed to close icon input stream.");
                }
            } catch (Throwable th2) {
                th = th2;
                a(inputStreamOpenRawResource, "Failed to close icon input stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            inputStreamOpenRawResource = null;
        } catch (Throwable th3) {
            inputStreamOpenRawResource = null;
            th = th3;
            a(inputStreamOpenRawResource, "Failed to close icon input stream.");
            throw th;
        }
        return str;
    }

    public static int h(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String i(Context context) {
        int iA = a(context, "com.crashlytics.android.build_id", "string");
        if (iA == 0) {
            return null;
        }
        String string = context.getResources().getString(iA);
        cm.a().b().a("Crashlytics", "Build ID is: " + string);
        return string;
    }
}
