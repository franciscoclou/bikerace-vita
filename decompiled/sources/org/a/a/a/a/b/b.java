package org.a.a.a.a.b;

import java.util.MissingResourceException;

/* JADX INFO: compiled from: LoggerFactory.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f1598a = b.class.getName();
    private static String b = null;
    private static String c = "org.eclipse.paho.client.mqttv3.logging.JSR47Logger";

    public static a a(String str, String str2) {
        String str3 = b;
        if (str3 == null) {
            str3 = c;
        }
        a aVarA = a(str3, str2, null);
        if (aVarA == null) {
            throw new MissingResourceException("Error locating the logging class", f1598a, str2);
        }
        return aVarA;
    }

    private static a a(String str, String str2, String str3) {
        a aVar;
        try {
            Class<?> cls = Class.forName(str);
            if (cls != null) {
                try {
                    aVar = (a) cls.newInstance();
                    aVar.a(str2, str3);
                } catch (ExceptionInInitializerError e) {
                    return null;
                } catch (IllegalAccessException e2) {
                    return null;
                } catch (InstantiationException e3) {
                    return null;
                } catch (SecurityException e4) {
                    return null;
                }
            } else {
                aVar = null;
            }
            return aVar;
        } catch (ClassNotFoundException e5) {
            return null;
        } catch (NoClassDefFoundError e6) {
            return null;
        }
    }
}
