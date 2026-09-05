package com.google.a.a.a;

import android.text.TextUtils;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.facebook.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f587a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static Map<String, String> a(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length > 1) {
                map.put(strArrSplit[0], strArrSplit[1]);
            } else if (strArrSplit.length == 1 && strArrSplit[0].length() != 0) {
                map.put(strArrSplit[0], null);
            }
        }
        return map;
    }

    public static double a(String str, double d) {
        if (str != null) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return d;
            }
        }
        return d;
    }

    public static boolean a(String str, boolean z) {
        if (str != null) {
            if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                return true;
            }
            if (str.equalsIgnoreCase("false") || str.equalsIgnoreCase("no") || str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                return false;
            }
            return z;
        }
        return z;
    }

    public static String b(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains("?")) {
            String[] strArrSplit = str.split("[\\?]");
            if (strArrSplit.length > 1) {
                str = strArrSplit[1];
            }
        }
        if (str.contains("%3D")) {
            try {
                str = URLDecoder.decode(str, XMLStreamWriterImpl.UTF_8);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else if (!str.contains("=")) {
            return null;
        }
        Map<String, String> mapA = a(str);
        String[] strArr = {"dclid", "utm_source", "gclid", "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "gmob_t"};
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = i;
            if (i2 < strArr.length) {
                if (!TextUtils.isEmpty(mapA.get(strArr[i2]))) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(strArr[i2]).append("=").append(mapA.get(strArr[i2]));
                }
                i = i2 + 1;
            } else {
                return sb.toString();
            }
        }
    }

    static String a(Locale locale) {
        if (locale == null || TextUtils.isEmpty(locale.getLanguage())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage().toLowerCase());
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-").append(locale.getCountry().toLowerCase());
        }
        return sb.toString();
    }

    public static void a(Map<String, String> map, String str, String str2) {
        if (!map.containsKey(str)) {
            map.put(str, str2);
        }
    }
}
