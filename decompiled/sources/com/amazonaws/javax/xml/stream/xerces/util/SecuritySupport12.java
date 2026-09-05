package com.amazonaws.javax.xml.stream.xerces.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class SecuritySupport12 extends SecuritySupport {
    SecuritySupport12() {
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport
    public ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport12.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException e) {
                    return null;
                }
            }
        });
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport
    public String getSystemProperty(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport12.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(str);
            }
        });
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport
    public FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport12.3
                @Override // java.security.PrivilegedExceptionAction
                public Object run() {
                    return new FileInputStream(file);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((FileNotFoundException) e.getException());
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport
    public InputStream getResourceAsStream(final ClassLoader classLoader, final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.stream.xerces.util.SecuritySupport12.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                if (classLoader == null) {
                    return ClassLoader.getSystemResourceAsStream(str);
                }
                return classLoader.getResourceAsStream(str);
            }
        });
    }
}
