package com.b.a.a;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ah {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final KeyStore f256a;
    private final HashMap<Principal, X509Certificate> b;

    public ah(InputStream inputStream, String str) {
        KeyStore keyStoreA = a(inputStream, str);
        this.b = a(keyStoreA);
        this.f256a = keyStoreA;
    }

    public final boolean a(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.b.get(x509Certificate.getSubjectX500Principal());
        return x509Certificate2 != null && x509Certificate2.getPublicKey().equals(x509Certificate.getPublicKey());
    }

    public final X509Certificate b(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.b.get(x509Certificate.getIssuerX500Principal());
        if (x509Certificate2 != null && !x509Certificate2.getSubjectX500Principal().equals(x509Certificate.getSubjectX500Principal())) {
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return x509Certificate2;
            } catch (GeneralSecurityException e) {
                return null;
            }
        }
        return null;
    }

    private static HashMap<Principal, X509Certificate> a(KeyStore keyStore) {
        try {
            HashMap<Principal, X509Certificate> map = new HashMap<>();
            Enumeration<String> enumerationAliases = keyStore.aliases();
            while (enumerationAliases.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(enumerationAliases.nextElement());
                if (x509Certificate != null) {
                    map.put(x509Certificate.getSubjectX500Principal(), x509Certificate);
                }
            }
            return map;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    private static KeyStore a(InputStream inputStream, String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("BKS");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                keyStore.load(bufferedInputStream, str.toCharArray());
                return keyStore;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AssertionError(e3);
        } catch (CertificateException e4) {
            throw new AssertionError(e4);
        }
    }
}
