package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class FactoryFinder {
    private static boolean debug;
    static Properties cacheProps = new Properties();
    static boolean firstTime = true;
    static SecuritySupport ss = new SecuritySupport();

    FactoryFinder() {
    }

    static {
        debug = false;
        try {
            String systemProperty = ss.getSystemProperty("jaxp.debug");
            debug = (systemProperty == null || "false".equals(systemProperty)) ? false : true;
        } catch (SecurityException e) {
            debug = false;
        }
    }

    private static void dPrint(String str) {
        if (debug) {
            System.err.println("JAXP: " + str);
        }
    }

    private static Class getProviderClass(String str, ClassLoader classLoader, boolean z) throws ClassNotFoundException {
        try {
            if (classLoader == null) {
                ClassLoader contextClassLoader = ss.getContextClassLoader();
                if (contextClassLoader == null) {
                    throw new ClassNotFoundException();
                }
                return contextClassLoader.loadClass(str);
            }
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException e) {
            if (z) {
                return Class.forName(str, true, FactoryFinder.class.getClassLoader());
            }
            throw e;
        }
    }

    static Object newInstance(String str, ClassLoader classLoader, boolean z) {
        try {
            Class providerClass = getProviderClass(str, classLoader, z);
            Object objNewInstance = providerClass.newInstance();
            if (debug) {
                dPrint("created new instance of " + providerClass + " using ClassLoader: " + classLoader);
            }
            return objNewInstance;
        } catch (ClassNotFoundException e) {
            throw new ConfigurationError("Provider " + str + " not found", e);
        } catch (Exception e2) {
            throw new ConfigurationError("Provider " + str + " could not be instantiated: " + e2, e2);
        }
    }

    static Object find(String str, String str2) {
        return find(str, null, str2);
    }

    static Object find(String str, ClassLoader classLoader, String str2) {
        String str3;
        dPrint("find factoryId =" + str);
        try {
            String systemProperty = ss.getSystemProperty(str);
            if (systemProperty != null) {
                dPrint("found system property, value=" + systemProperty);
                return newInstance(systemProperty, null, true);
            }
        } catch (SecurityException e) {
            if (debug) {
                e.printStackTrace();
            }
        }
        try {
            if (firstTime) {
                synchronized (cacheProps) {
                    if (firstTime) {
                        str3 = ss.getSystemProperty("java.home") + File.separator + "lib" + File.separator + "stax.properties";
                        File file = new File(str3);
                        firstTime = false;
                        if (ss.doesFileExist(file)) {
                            dPrint("Read properties file " + file);
                            cacheProps.load(ss.getFileInputStream(file));
                        } else {
                            str3 = ss.getSystemProperty("java.home") + File.separator + "lib" + File.separator + "jaxp.properties";
                            File file2 = new File(str3);
                            if (ss.doesFileExist(file2)) {
                                dPrint("Read properties file " + file2);
                                cacheProps.load(ss.getFileInputStream(file2));
                            }
                        }
                    } else {
                        str3 = null;
                    }
                }
            } else {
                str3 = null;
            }
            String property = cacheProps.getProperty(str);
            if (property != null) {
                dPrint("found in " + str3 + " value=" + property);
                return newInstance(property, null, true);
            }
        } catch (Exception e2) {
            if (debug) {
                e2.printStackTrace();
            }
        }
        Object objFindJarServiceProvider = findJarServiceProvider(str);
        if (objFindJarServiceProvider == null) {
            if (str2 == null) {
                throw new ConfigurationError("Provider for " + str + " cannot be found", null);
            }
            dPrint("loaded from fallback value: " + str2);
            return newInstance(str2, classLoader, true);
        }
        return objFindJarServiceProvider;
    }

    private static Object findJarServiceProvider(String str) {
        InputStream resourceAsStream;
        ClassLoader classLoader;
        BufferedReader bufferedReader;
        String str2 = "META-INF/services/" + str;
        ClassLoader contextClassLoader = ss.getContextClassLoader();
        if (contextClassLoader != null) {
            InputStream resourceAsStream2 = ss.getResourceAsStream(contextClassLoader, str2);
            if (resourceAsStream2 == null) {
                ClassLoader classLoader2 = FactoryFinder.class.getClassLoader();
                resourceAsStream = ss.getResourceAsStream(classLoader2, str2);
                classLoader = classLoader2;
            } else {
                resourceAsStream = resourceAsStream2;
                classLoader = contextClassLoader;
            }
        } else {
            ClassLoader classLoader3 = FactoryFinder.class.getClassLoader();
            resourceAsStream = ss.getResourceAsStream(classLoader3, str2);
            classLoader = classLoader3;
        }
        if (resourceAsStream == null) {
            return null;
        }
        if (debug) {
            dPrint("found jar resource=" + str2 + " using ClassLoader: " + classLoader);
        }
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
            dPrint("found in resource, value=" + line);
            return newInstance(line, classLoader, false);
        } catch (IOException e2) {
            return null;
        }
    }

    class ConfigurationError extends Error {
        private Exception exception;

        ConfigurationError(String str, Exception exc) {
            super(str);
            this.exception = exc;
        }

        Exception getException() {
            return this.exception;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.exception;
        }
    }
}
