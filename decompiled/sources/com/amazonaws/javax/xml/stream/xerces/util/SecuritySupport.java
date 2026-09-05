package com.amazonaws.javax.xml.stream.xerces.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class SecuritySupport {
    private static final Object securitySupport;

    SecuritySupport() {
    }

    static {
        Object securitySupport2 = null;
        try {
            Class.forName("java.security.AccessController");
            Object securitySupport12 = new SecuritySupport12();
            if (securitySupport12 == null) {
            }
        } catch (Exception e) {
            if (0 != 0) {
                SecuritySupport securitySupport3 = null;
            }
        } finally {
            if (0 == 0) {
                securitySupport2 = new SecuritySupport();
            }
            securitySupport = securitySupport2;
        }
    }

    public static SecuritySupport getInstance() {
        return (SecuritySupport) securitySupport;
    }

    public ClassLoader getContextClassLoader() {
        return null;
    }

    public String getSystemProperty(String str) {
        return System.getProperty(str);
    }

    public FileInputStream getFileInputStream(File file) {
        return new FileInputStream(file);
    }

    public InputStream getResourceAsStream(ClassLoader classLoader, String str) {
        if (classLoader == null) {
            return ClassLoader.getSystemResourceAsStream(str);
        }
        return classLoader.getResourceAsStream(str);
    }
}
