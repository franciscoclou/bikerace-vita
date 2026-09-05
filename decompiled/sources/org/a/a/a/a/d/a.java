package org.a.a.a.a.d;

import java.util.Enumeration;
import java.util.Properties;

/* JADX INFO: compiled from: Debug.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f1600a = org.a.a.a.a.a.a.class.getName();
    static String b = "==============";
    static String c = System.getProperty("line.separator", "\n");

    public static String a(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        stringBuffer.append(c + b + " " + str + " " + b + c);
        while (enumerationPropertyNames.hasMoreElements()) {
            String str2 = (String) enumerationPropertyNames.nextElement();
            stringBuffer.append(a(str2, 28, ' ') + ":  " + properties.get(str2) + c);
        }
        stringBuffer.append(b + b + b + c);
        return stringBuffer.toString();
    }

    public static String a(String str, int i, char c2) {
        if (str.length() < i) {
            StringBuffer stringBuffer = new StringBuffer(i);
            stringBuffer.append(str);
            int length = i - str.length();
            while (true) {
                length--;
                if (length >= 0) {
                    stringBuffer.append(c2);
                } else {
                    return stringBuffer.toString();
                }
            }
        } else {
            return str;
        }
    }
}
