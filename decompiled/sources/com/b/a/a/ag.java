package com.b.a.a;

import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ag implements X509TrustManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TrustManager[] f255a;
    private final ah b;
    private final List<byte[]> d = new LinkedList();
    private final Set<X509Certificate> e = Collections.synchronizedSet(new HashSet());
    private final long c = -1;

    public ag(ah ahVar, af afVar) {
        this.f255a = a(ahVar);
        this.b = ahVar;
        for (String str : afVar.c()) {
            this.d.add(a(str));
        }
    }

    private static TrustManager[] a(ah ahVar) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(ahVar.f256a);
            return trustManagerFactory.getTrustManagers();
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    private boolean a(X509Certificate x509Certificate) throws CertificateException {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            Iterator<byte[]> it = this.d.iterator();
            while (it.hasNext()) {
                if (Arrays.equals(it.next(), bArrDigest)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (!this.e.contains(x509CertificateArr[0])) {
            for (TrustManager trustManager : this.f255a) {
                ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
            }
            if (this.c == -1 || System.currentTimeMillis() - this.c <= 15552000000L) {
                for (X509Certificate x509Certificate : bu.a(x509CertificateArr, this.b)) {
                    if (!a(x509Certificate)) {
                    }
                }
                throw new CertificateException("No valid pins found in chain!");
            }
            cm.a().b().c("Crashlytics", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.c) + " millis vs 15552000000 millis) falling back to system trust.");
            this.e.add(x509CertificateArr[0]);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    private static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
