package com.amazonaws.javax.xml.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/* JADX INFO: compiled from: SecuritySupport.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e {
    e() {
    }

    ClassLoader a() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.transform.e.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader == null) {
                    return ClassLoader.getSystemClassLoader();
                }
                return contextClassLoader;
            }
        });
    }

    String a(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.transform.e.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(str);
            }
        });
    }

    FileInputStream a(final File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: com.amazonaws.javax.xml.transform.e.3
                @Override // java.security.PrivilegedExceptionAction
                public Object run() {
                    return new FileInputStream(file);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    InputStream a(final ClassLoader classLoader, final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.transform.e.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                if (classLoader == null) {
                    return ClassLoader.getSystemResourceAsStream(str);
                }
                return classLoader.getResourceAsStream(str);
            }
        });
    }

    boolean b(final File file) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.transform.e.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                return new Boolean(file.exists());
            }
        })).booleanValue();
    }
}
