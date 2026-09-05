package com.applovin.impl.a;

import android.content.Context;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ah extends com.applovin.a.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f183a = "0123456789abcdef".toCharArray();
    private static final char[] b = "-'".toCharArray();

    public static double a(long j) {
        return j / 1000.0d;
    }

    public static float a(float f) {
        return 1000.0f * f;
    }

    public static a a() {
        return new c().a("").a(com.applovin.a.f.f155a).a(com.applovin.a.g.f156a).a(new ArrayList()).b("").a(b.DEFAULT).a(com.applovin.impl.adview.ad.WhiteXOnOpaqueBlack).a(0.0f).b(0.0f).a(0).b(-1).c("").a();
    }

    public static File a(String str, Context context, boolean z) {
        boolean zA = aq.a("android.permission.WRITE_EXTERNAL_STORAGE", context);
        if (!zA && !z) {
            return null;
        }
        if (str.contains("icon")) {
            str = str.replace("/", "_").replace(".", "_");
        }
        File file = zA ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
        File file2 = new File(file, str);
        file.mkdirs();
        return file2;
    }

    public static String a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    public static String a(String str, d dVar) {
        return a(str, (Integer) (-1), (String) dVar.a(j.r));
    }

    private static String a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        }
        if (str == null || str.length() < 1) {
            return "";
        }
        if (str2.length() < 1 || "none".equals(str2)) {
            return str;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes(XMLStreamWriterImpl.UTF_8));
            String strA = a(messageDigest.digest());
            return (strA == null || num.intValue() <= 0) ? strA : strA.substring(0, Math.min(num.intValue(), strA.length()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Programming error: UTF-8 is not know encoding", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e2);
        }
    }

    static String a(Collection collection, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("No glue specified");
        }
        if (collection == null || collection.size() < 1) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (i2 >= i) {
                break;
            }
            i2++;
            stringBuffer.append(str2).append(str);
        }
        if (stringBuffer.length() > str.length()) {
            stringBuffer.setLength(stringBuffer.length() - str.length());
        }
        return stringBuffer.toString();
    }

    static String a(Map map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i * 2] = f183a[(bArr[i] & 240) >>> 4];
            cArr[(i * 2) + 1] = f183a[bArr[i] & 15];
        }
        return new String(cArr);
    }

    public static boolean a(l lVar, d dVar) {
        return a(lVar, dVar.g());
    }

    public static boolean a(l lVar, m mVar) {
        return ((float) System.currentTimeMillis()) > a((float) ((Long) mVar.a(lVar)).longValue());
    }

    public static boolean a(String str, Context context) {
        return b(str, context, false);
    }

    public static long b(float f) {
        return Math.round(f);
    }

    public static String b(String str) {
        return (str == null || str.length() < 1) ? "" : str.trim().toLowerCase();
    }

    public static String b(String str, d dVar) {
        return a(str, (Integer) dVar.a(j.s), (String) dVar.a(j.r));
    }

    public static boolean b(String str, Context context, boolean z) {
        File fileA = a(str, context, z);
        return (fileA == null || !fileA.exists() || fileA.isDirectory()) ? false : true;
    }

    public static long c(float f) {
        return b(a(f));
    }

    static String c(String str) {
        if (!d(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static boolean d(String str) {
        return str != null && str.length() > 1;
    }
}
