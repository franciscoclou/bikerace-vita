package com.flurry.sdk;

import android.content.Intent;
import android.text.TextUtils;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.facebook.internal.NativeProtocol;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class fh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f565a = fh.class.getSimpleName();

    public static String a(String str) {
        return a(str, 255);
    }

    public static String a(String str, int i) {
        if (str == null) {
            return "";
        }
        return str.length() > i ? str.substring(0, i) : str;
    }

    public static String b(String str) {
        try {
            return URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8);
        } catch (UnsupportedEncodingException e) {
            ex.a(5, f565a, "Cannot encode '" + str + "'");
            return "";
        }
    }

    public static String c(String str) {
        try {
            return URLDecoder.decode(str, XMLStreamWriterImpl.UTF_8);
        } catch (UnsupportedEncodingException e) {
            ex.a(5, f565a, "Cannot decode '" + str + "'");
            return "";
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    public static byte[] d(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes(), 0, str.length());
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            ex.a(6, f565a, "Unsupported SHA1: " + e.getMessage());
            return null;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (byte b : bArr) {
            sb.append(cArr[(byte) ((b & 240) >> 4)]);
            sb.append(cArr[(byte) (b & 15)]);
        }
        return sb.toString();
    }

    public static boolean a(long j) {
        if (j != 0 && System.currentTimeMillis() > j) {
            return false;
        }
        return true;
    }

    public static boolean a(Intent intent) {
        return eg.a().c().queryIntentActivities(intent, NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST).size() > 0;
    }

    public static boolean b(Intent intent) {
        if (intent == null) {
            return false;
        }
        return eg.a().b().getPackageName().equals(intent.resolveActivity(eg.a().c()).getPackageName());
    }

    public static String e(String str) {
        return str.replace("'", "\\'").replace("\\n", "").replace("\\r", "").replace("\\t", "");
    }

    public static Map<String, String> f(String str) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split("=");
                if (!strArrSplit[0].equals("event")) {
                    map.put(c(strArrSplit[0]), c(strArrSplit[1]));
                }
            }
        }
        return map;
    }
}
