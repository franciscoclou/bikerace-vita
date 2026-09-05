package com.b.a.a;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final cj f284a;
    private af b;
    private SSLSocketFactory c;
    private boolean d;

    public static X509Certificate[] a(X509Certificate[] x509CertificateArr, ah ahVar) throws CertificateException {
        boolean z = true;
        LinkedList linkedList = new LinkedList();
        boolean z2 = ahVar.a(x509CertificateArr[0]);
        linkedList.add(x509CertificateArr[0]);
        boolean z3 = z2;
        int i = 1;
        while (i < x509CertificateArr.length) {
            if (ahVar.a(x509CertificateArr[i])) {
                z3 = true;
            }
            if (!a(x509CertificateArr[i], x509CertificateArr[i - 1])) {
                break;
            }
            linkedList.add(x509CertificateArr[i]);
            i++;
        }
        X509Certificate x509CertificateB = ahVar.b(x509CertificateArr[i - 1]);
        if (x509CertificateB != null) {
            linkedList.add(x509CertificateB);
        } else {
            z = z3;
        }
        if (z) {
            return (X509Certificate[]) linkedList.toArray(new X509Certificate[linkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    public bu() {
        this(new ck());
    }

    public bu(cj cjVar) {
        this.f284a = cjVar;
    }

    private static boolean a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getIssuerX500Principal())) {
            return false;
        }
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }

    public bx a(bw bwVar, String str, Map<String, String> map) {
        bx bxVarB;
        SSLSocketFactory sSLSocketFactoryA;
        switch (bv.f285a[bwVar.ordinal()]) {
            case 1:
                bxVarB = bx.a((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case 2:
                bxVarB = bx.b((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case 3:
                bxVarB = bx.a((CharSequence) str);
                break;
            case 4:
                bxVarB = bx.b((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if ((str == null ? false : str.toLowerCase().startsWith("https")) && this.b != null && (sSLSocketFactoryA = a()) != null) {
            ((HttpsURLConnection) bxVarB.a()).setSSLSocketFactory(sSLSocketFactoryA);
        }
        return bxVarB;
    }

    private synchronized SSLSocketFactory a() {
        if (this.c == null && !this.d) {
            this.c = b();
        }
        return this.c;
    }

    private synchronized SSLSocketFactory b() {
        SSLSocketFactory socketFactory;
        try {
            this.d = true;
            try {
                af afVar = this.b;
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, new TrustManager[]{new ag(new ah(afVar.a(), afVar.b()), afVar)}, null);
                socketFactory = sSLContext.getSocketFactory();
                this.f284a.a("Crashlytics", "Custom SSL pinning enabled");
            } catch (Exception e) {
                this.f284a.a("Crashlytics", "Exception while validating pinned certs", e);
                socketFactory = null;
            }
        } catch (Throwable th) {
            throw th;
        }
        return socketFactory;
    }
}
