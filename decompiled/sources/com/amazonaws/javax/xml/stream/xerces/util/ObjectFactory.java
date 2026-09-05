package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ObjectFactory {
    private static final boolean DEBUG = false;
    private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";
    static /* synthetic */ Class class$com$sun$xml$stream$xerces$util$ObjectFactory;

    public static Object createObject(String str, String str2) {
        return createObject(str, null, str2);
    }

    public static Object createObject(String str, String str2, String str3) throws Throwable {
        debugPrintln("debug is on");
        SecuritySupport securitySupport = SecuritySupport.getInstance();
        ClassLoader classLoaderFindClassLoader = findClassLoader();
        try {
            String systemProperty = securitySupport.getSystemProperty(str);
            if (systemProperty != null) {
                debugPrintln(new StringBuffer().append("found system property, value=").append(systemProperty).toString());
                return newInstance(systemProperty, classLoaderFindClassLoader, true);
            }
        } catch (SecurityException e) {
        }
        if (str2 == null) {
            try {
                str2 = new StringBuffer().append(securitySupport.getSystemProperty("java.home")).append(File.separator).append("lib").append(File.separator).append(DEFAULT_PROPERTIES_FILENAME).toString();
            } catch (Exception e2) {
            }
        }
        FileInputStream fileInputStream = securitySupport.getFileInputStream(new File(str2));
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String property = properties.getProperty(str);
        if (property != null) {
            debugPrintln(new StringBuffer().append("found in ").append(str2).append(", value=").append(property).toString());
            return newInstance(property, classLoaderFindClassLoader, true);
        }
        Object objFindJarServiceProvider = findJarServiceProvider(str);
        if (objFindJarServiceProvider == null) {
            if (str3 == null) {
                throw new ConfigurationError(new StringBuffer().append("Provider for ").append(str).append(" cannot be found").toString(), null);
            }
            debugPrintln(new StringBuffer().append("using fallback, value=").append(str3).toString());
            return newInstance(str3, classLoaderFindClassLoader, true);
        }
        return objFindJarServiceProvider;
    }

    private static void debugPrintln(String str) {
    }

    public static ClassLoader findClassLoader() throws Throwable {
        Class clsClass$;
        ClassLoader contextClassLoader = SecuritySupport.getInstance().getContextClassLoader();
        if (contextClassLoader == null) {
            if (class$com$sun$xml$stream$xerces$util$ObjectFactory == null) {
                clsClass$ = class$("com.amazonaws.javax.xml.stream.xerces.util.ObjectFactory");
                class$com$sun$xml$stream$xerces$util$ObjectFactory = clsClass$;
            } else {
                clsClass$ = class$com$sun$xml$stream$xerces$util$ObjectFactory;
            }
            return clsClass$.getClassLoader();
        }
        return contextClassLoader;
    }

    static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static Object newInstance(String str, ClassLoader classLoader, boolean z) throws Throwable {
        try {
            Class clsFindProviderClass = findProviderClass(str, classLoader, z);
            Object objNewInstance = clsFindProviderClass.newInstance();
            debugPrintln(new StringBuffer().append("created new instance of ").append(clsFindProviderClass).append(" using ClassLoader: ").append(classLoader).toString());
            return objNewInstance;
        } catch (ClassNotFoundException e) {
            throw new ConfigurationError(new StringBuffer().append("Provider ").append(str).append(" not found").toString(), e);
        } catch (Exception e2) {
            throw new ConfigurationError(new StringBuffer().append("Provider ").append(str).append(" could not be instantiated: ").append(e2).toString(), e2);
        }
    }

    public static Class findProviderClass(String str, ClassLoader classLoader, boolean z) throws Throwable {
        Class clsClass$;
        if (classLoader == null) {
            return Class.forName(str);
        }
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException e) {
            if (z) {
                if (class$com$sun$xml$stream$xerces$util$ObjectFactory == null) {
                    clsClass$ = class$("com.amazonaws.javax.xml.stream.xerces.util.ObjectFactory");
                    class$com$sun$xml$stream$xerces$util$ObjectFactory = clsClass$;
                } else {
                    clsClass$ = class$com$sun$xml$stream$xerces$util$ObjectFactory;
                }
                return clsClass$.getClassLoader().loadClass(str);
            }
            throw e;
        }
    }

    private static Object findJarServiceProvider(String str) throws Throwable {
        Class clsClass$;
        InputStream resourceAsStream;
        ClassLoader classLoader;
        BufferedReader bufferedReader;
        Class clsClass$2;
        SecuritySupport securitySupport = SecuritySupport.getInstance();
        String string = new StringBuffer().append("META-INF/services/").append(str).toString();
        ClassLoader contextClassLoader = securitySupport.getContextClassLoader();
        if (contextClassLoader != null) {
            InputStream resourceAsStream2 = securitySupport.getResourceAsStream(contextClassLoader, string);
            if (resourceAsStream2 == null) {
                if (class$com$sun$xml$stream$xerces$util$ObjectFactory == null) {
                    clsClass$2 = class$("com.amazonaws.javax.xml.stream.xerces.util.ObjectFactory");
                    class$com$sun$xml$stream$xerces$util$ObjectFactory = clsClass$2;
                } else {
                    clsClass$2 = class$com$sun$xml$stream$xerces$util$ObjectFactory;
                }
                ClassLoader classLoader2 = clsClass$2.getClassLoader();
                resourceAsStream = securitySupport.getResourceAsStream(classLoader2, string);
                classLoader = classLoader2;
            } else {
                resourceAsStream = resourceAsStream2;
                classLoader = contextClassLoader;
            }
        } else {
            if (class$com$sun$xml$stream$xerces$util$ObjectFactory == null) {
                clsClass$ = class$("com.amazonaws.javax.xml.stream.xerces.util.ObjectFactory");
                class$com$sun$xml$stream$xerces$util$ObjectFactory = clsClass$;
            } else {
                clsClass$ = class$com$sun$xml$stream$xerces$util$ObjectFactory;
            }
            ClassLoader classLoader3 = clsClass$.getClassLoader();
            resourceAsStream = securitySupport.getResourceAsStream(classLoader3, string);
            classLoader = classLoader3;
        }
        if (resourceAsStream == null) {
            return null;
        }
        debugPrintln(new StringBuffer().append("found jar resource=").append(string).append(" using ClassLoader: ").append(classLoader).toString());
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, XMLStreamWriterImpl.UTF_8));
        } catch (UnsupportedEncodingException e) {
            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        }
        try {
            String line = bufferedReader.readLine();
            bufferedReader.close();
            if (line == null || "".equals(line)) {
                return null;
            }
            debugPrintln(new StringBuffer().append("found in resource, value=").append(line).toString());
            return newInstance(line, classLoader, DEBUG);
        } catch (IOException e2) {
            return null;
        }
    }

    public class ConfigurationError extends Error {
        private Exception exception;

        public ConfigurationError(String str, Exception exc) {
            super(str);
            this.exception = exc;
        }

        public Exception getException() {
            return this.exception;
        }
    }
}
