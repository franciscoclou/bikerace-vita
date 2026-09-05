package com.amazonaws.f;

import com.facebook.internal.NativeProtocol;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f132a = null;
    private static String b = null;
    private static String c = null;
    private static Log d = LogFactory.getLog(m.class);

    public static String a() {
        if (f132a == null) {
            d();
        }
        return f132a;
    }

    public static String b() {
        if (b == null) {
            d();
        }
        return b;
    }

    public static String c() {
        if (c == null) {
            e();
        }
        return c;
    }

    private static void d() {
        InputStream resourceAsStream = m.class.getClassLoader().getResourceAsStream("com/amazonaws/sdk/versionInfo.properties");
        Properties properties = new Properties();
        try {
            if (resourceAsStream == null) {
                throw new Exception("com/amazonaws/sdk/versionInfo.properties not found on classpath");
            }
            properties.load(resourceAsStream);
            f132a = properties.getProperty(NativeProtocol.PLATFORM_PROVIDER_VERSION_COLUMN);
            b = properties.getProperty("platform");
        } catch (Exception e) {
            d.info("Unable to load version information for the running SDK: " + e.getMessage());
            f132a = "unknown-version";
            b = "java";
        }
    }

    private static void e() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("aws-sdk-" + b().toLowerCase() + "/");
        sb.append(a());
        sb.append(" ");
        sb.append(System.getProperty("os.name").replace(' ', '_') + "/" + System.getProperty("os.version").replace(' ', '_'));
        sb.append(" ");
        sb.append(System.getProperty("java.vm.name").replace(' ', '_') + "/" + System.getProperty("java.vm.version").replace(' ', '_'));
        String str = "";
        try {
            str = " " + System.getProperty("user.language").replace(' ', '_') + "_" + System.getProperty("user.region").replace(' ', '_');
        } catch (Exception e) {
        }
        sb.append(str);
        c = sb.toString();
    }
}
