package com.amazonaws.javax.xml.transform;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/* JADX INFO: compiled from: FactoryFinder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static Properties f139a = new Properties();
    static boolean b = true;
    static e c = new e();
    private static boolean d;

    b() {
    }

    static {
        d = false;
        try {
            String strA = c.a("jaxp.debug");
            d = (strA == null || "false".equals(strA)) ? false : true;
        } catch (SecurityException e) {
            d = false;
        }
    }

    private static void a(String str) {
        if (d) {
            System.err.println("JAXP: " + str);
        }
    }

    private static Class b(String str, ClassLoader classLoader, boolean z) throws ClassNotFoundException {
        try {
            if (classLoader == null) {
                ClassLoader classLoaderA = c.a();
                if (classLoaderA == null) {
                    throw new ClassNotFoundException();
                }
                return classLoaderA.loadClass(str);
            }
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException e) {
            if (z) {
                return Class.forName(str, true, b.class.getClassLoader());
            }
            throw e;
        }
    }

    static Object a(String str, ClassLoader classLoader, boolean z) {
        try {
            Class clsB = b(str, classLoader, z);
            Object objNewInstance = clsB.newInstance();
            if (d) {
                a("created new instance of " + clsB + " using ClassLoader: " + classLoader);
            }
            return objNewInstance;
        } catch (ClassNotFoundException e) {
            throw new c("Provider " + str + " not found", e);
        } catch (Exception e2) {
            throw new c("Provider " + str + " could not be instantiated: " + e2, e2);
        }
    }

    static Object a(String str, String str2) {
        a("find factoryId =" + str);
        try {
            String strA = c.a(str);
            if (strA != null) {
                a("found system property, value=" + strA);
                return a(strA, null, true);
            }
        } catch (SecurityException e) {
            if (d) {
                e.printStackTrace();
            }
        }
        try {
            if (b) {
                synchronized (f139a) {
                    if (b) {
                        File file = new File(c.a("java.home") + File.separator + "lib" + File.separator + "jaxp.properties");
                        b = false;
                        if (c.b(file)) {
                            a("Read properties file " + file);
                            f139a.load(c.a(file));
                        }
                    }
                }
            }
            String property = f139a.getProperty(str);
            if (property != null) {
                a("found in $java.home/jaxp.properties, value=" + property);
                return a(property, null, true);
            }
        } catch (Exception e2) {
            if (d) {
                e2.printStackTrace();
            }
        }
        Object objB = b(str);
        if (objB == null) {
            if (str2 == null) {
                throw new c("Provider for " + str + " cannot be found", null);
            }
            a("loaded from fallback value: " + str2);
            return a(str2, null, true);
        }
        return objB;
    }

    private static Object b(String str) {
        InputStream inputStreamA;
        ClassLoader classLoader;
        BufferedReader bufferedReader;
        String str2 = "META-INF/services/" + str;
        ClassLoader classLoaderA = c.a();
        if (classLoaderA != null) {
            InputStream inputStreamA2 = c.a(classLoaderA, str2);
            if (inputStreamA2 == null) {
                ClassLoader classLoader2 = b.class.getClassLoader();
                inputStreamA = c.a(classLoader2, str2);
                classLoader = classLoader2;
            } else {
                inputStreamA = inputStreamA2;
                classLoader = classLoaderA;
            }
        } else {
            ClassLoader classLoader3 = b.class.getClassLoader();
            inputStreamA = c.a(classLoader3, str2);
            classLoader = classLoader3;
        }
        if (inputStreamA == null) {
            return null;
        }
        if (d) {
            a("found jar resource=" + str2 + " using ClassLoader: " + classLoader);
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStreamA, XMLStreamWriterImpl.UTF_8));
        } catch (UnsupportedEncodingException e) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStreamA));
        }
        try {
            String line = bufferedReader.readLine();
            bufferedReader.close();
            if (line == null || "".equals(line)) {
                return null;
            }
            a("found in resource, value=" + line);
            return a(line, classLoader, false);
        } catch (IOException e2) {
            return null;
        }
    }
}
