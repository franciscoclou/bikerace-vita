package org.a.a.a.a.a.a;

import com.flurry.android.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.a.a.a.a.o;

/* JADX INFO: compiled from: SSLSocketFactoryFactory.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f1570a = {"com.ibm.ssl.protocol", "com.ibm.ssl.contextProvider", "com.ibm.ssl.keyStore", "com.ibm.ssl.keyStorePassword", "com.ibm.ssl.keyStoreType", "com.ibm.ssl.keyStoreProvider", "com.ibm.ssl.keyManager", "com.ibm.ssl.trustStore", "com.ibm.ssl.trustStorePassword", "com.ibm.ssl.trustStoreType", "com.ibm.ssl.trustStoreProvider", "com.ibm.ssl.trustManager", "com.ibm.ssl.enabledCipherSuites", "com.ibm.ssl.clientAuthentication"};
    private static final byte[] d = {-99, -89, -39, -128, 5, -72, -119, -100};
    private Properties c;
    private org.a.a.a.a.b.a e = null;
    private Hashtable b = new Hashtable();

    private boolean p(String str) {
        int i = 0;
        while (i < f1570a.length && !f1570a[i].equals(str)) {
            i++;
        }
        return i < f1570a.length;
    }

    private void a(Properties properties) {
        for (String str : properties.keySet()) {
            if (!p(str)) {
                throw new IllegalArgumentException(str + " is not a valid IBM SSL property key.");
            }
        }
    }

    public static char[] a(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length / 2];
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i2 + 1;
            int i4 = bArr[i2] & Constants.UNKNOWN;
            i2 = i3 + 1;
            cArr[i] = (char) (((bArr[i3] & Constants.UNKNOWN) << 8) + i4);
            i++;
        }
        return cArr;
    }

    public static byte[] a(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            return null;
        }
        byte[] bArr = new byte[cArr.length * 2];
        int i2 = 0;
        while (i < cArr.length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (cArr[i] & 255);
            bArr[i3] = (byte) ((cArr[i] >> '\b') & 255);
            i++;
            i2 = i3 + 1;
        }
        return bArr;
    }

    public static String b(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] bArrA = a(cArr);
        for (int i = 0; i < bArrA.length; i++) {
            bArrA[i] = (byte) ((bArrA[i] ^ d[i % d.length]) & 255);
        }
        return "{xor}" + new String(b.a(bArrA));
    }

    public static char[] a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bArrA = b.a(str.substring("{xor}".length()));
            for (int i = 0; i < bArrA.length; i++) {
                bArrA[i] = (byte) ((bArrA[i] ^ d[i % d.length]) & 255);
            }
            return a(bArrA);
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] b(String str) {
        if (str == null) {
            return null;
        }
        Vector vector = new Vector();
        int iIndexOf = str.indexOf(44);
        int i = 0;
        while (iIndexOf > -1) {
            vector.add(str.substring(i, iIndexOf));
            i = iIndexOf + 1;
            iIndexOf = str.indexOf(44, i);
        }
        vector.add(str.substring(i));
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    private void b(Properties properties) {
        String property = properties.getProperty("com.ibm.ssl.keyStorePassword");
        if (property != null && !property.startsWith("{xor}")) {
            properties.put("com.ibm.ssl.keyStorePassword", b(property.toCharArray()));
        }
        String property2 = properties.getProperty("com.ibm.ssl.trustStorePassword");
        if (property2 != null && !property2.startsWith("{xor}")) {
            properties.put("com.ibm.ssl.trustStorePassword", b(property2.toCharArray()));
        }
    }

    public void a(Properties properties, String str) {
        a(properties);
        Properties properties2 = new Properties();
        properties2.putAll(properties);
        b(properties2);
        if (str != null) {
            this.b.put(str, properties2);
        } else {
            this.c = properties2;
        }
    }

    private String a(String str, String str2, String str3) {
        String strA = a(str, str2);
        if (strA == null && str3 != null) {
            return System.getProperty(str3);
        }
        return strA;
    }

    private String a(String str, String str2) {
        String property;
        Properties properties = str != null ? (Properties) this.b.get(str) : null;
        if (properties != null) {
            property = properties.getProperty(str2);
            if (property == null) {
            }
            return property;
        }
        property = null;
        Properties properties2 = this.c;
        if (properties2 == null || (property = properties2.getProperty(str2)) != null) {
        }
        return property;
    }

    public String c(String str) {
        return a(str, "com.ibm.ssl.protocol", null);
    }

    public String d(String str) {
        return a(str, "com.ibm.ssl.contextProvider", null);
    }

    public char[] e(String str) {
        String strA = a(str, "com.ibm.ssl.keyStorePassword", "javax.net.ssl.keyStorePassword");
        if (strA == null) {
            return null;
        }
        if (strA.startsWith("{xor}")) {
            return a(strA);
        }
        return strA.toCharArray();
    }

    public String f(String str) {
        return a(str, "com.ibm.ssl.keyStoreType", "javax.net.ssl.keyStoreType");
    }

    public String g(String str) {
        return a(str, "com.ibm.ssl.keyStoreProvider", null);
    }

    public String h(String str) {
        return a(str, "com.ibm.ssl.keyManager", "ssl.KeyManagerFactory.algorithm");
    }

    public String i(String str) {
        return a(str, "com.ibm.ssl.trustStore", "javax.net.ssl.trustStore");
    }

    public char[] j(String str) {
        String strA = a(str, "com.ibm.ssl.trustStorePassword", "javax.net.ssl.trustStorePassword");
        if (strA == null) {
            return null;
        }
        if (strA.startsWith("{xor}")) {
            return a(strA);
        }
        return strA.toCharArray();
    }

    public String k(String str) {
        return a(str, "com.ibm.ssl.trustStoreType", null);
    }

    public String l(String str) {
        return a(str, "com.ibm.ssl.trustStoreProvider", null);
    }

    public String m(String str) {
        return a(str, "com.ibm.ssl.trustManager", "ssl.TrustManagerFactory.algorithm");
    }

    public String[] n(String str) {
        return b(a(str, "com.ibm.ssl.enabledCipherSuites", null));
    }

    /* JADX WARN: Code duplicated, block: B:192:0x02d7  */
    private SSLContext q(String str) throws o {
        SSLContext sSLContext;
        KeyManager[] keyManagers;
        TrustManager[] trustManagers;
        TrustManagerFactory trustManagerFactory;
        KeyManagerFactory keyManagerFactory;
        String strC = c(str);
        if (strC == null) {
            strC = "TLS";
        }
        if (this.e != null) {
            org.a.a.a.a.b.a aVar = this.e;
            Object[] objArr = new Object[2];
            objArr[0] = str != null ? str : "null (broker defaults)";
            objArr[1] = strC;
            aVar.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12000", objArr);
        }
        String strD = d(str);
        try {
            if (strD == null) {
                sSLContext = SSLContext.getInstance(strC);
            } else {
                sSLContext = SSLContext.getInstance(strC, strD);
            }
            if (this.e != null) {
                org.a.a.a.a.b.a aVar2 = this.e;
                Object[] objArr2 = new Object[2];
                objArr2[0] = str != null ? str : "null (broker defaults)";
                objArr2[1] = sSLContext.getProvider().getName();
                aVar2.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12001", objArr2);
            }
            String strA = a(str, "com.ibm.ssl.keyStore", null);
            if (0 != 0) {
                keyManagers = null;
            } else {
                if (strA == null) {
                    strA = a(str, "com.ibm.ssl.keyStore", "javax.net.ssl.keyStore");
                }
                if (this.e != null) {
                    org.a.a.a.a.b.a aVar3 = this.e;
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = str != null ? str : "null (broker defaults)";
                    objArr3[1] = strA != null ? strA : "null";
                    aVar3.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12004", objArr3);
                }
                char[] cArrE = e(str);
                if (this.e != null) {
                    org.a.a.a.a.b.a aVar4 = this.e;
                    Object[] objArr4 = new Object[2];
                    objArr4[0] = str != null ? str : "null (broker defaults)";
                    objArr4[1] = cArrE != null ? b(cArrE) : "null";
                    aVar4.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12005", objArr4);
                }
                String strF = f(str);
                if (strF == null) {
                    strF = KeyStore.getDefaultType();
                }
                if (this.e != null) {
                    org.a.a.a.a.b.a aVar5 = this.e;
                    Object[] objArr5 = new Object[2];
                    objArr5[0] = str != null ? str : "null (broker defaults)";
                    objArr5[1] = strF != null ? strF : "null";
                    aVar5.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12006", objArr5);
                }
                String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
                String strG = g(str);
                String strH = h(str);
                if (strH == null) {
                    strH = defaultAlgorithm;
                }
                if (strA == null || strF == null || strH == null) {
                    keyManagers = null;
                } else {
                    try {
                        KeyStore keyStore = KeyStore.getInstance(strF);
                        keyStore.load(new FileInputStream(strA), cArrE);
                        if (strG != null) {
                            keyManagerFactory = KeyManagerFactory.getInstance(strH, strG);
                        } else {
                            keyManagerFactory = KeyManagerFactory.getInstance(strH);
                        }
                        if (this.e != null) {
                            org.a.a.a.a.b.a aVar6 = this.e;
                            Object[] objArr6 = new Object[2];
                            objArr6[0] = str != null ? str : "null (broker defaults)";
                            if (strH == null) {
                                strH = "null";
                            }
                            objArr6[1] = strH;
                            aVar6.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12010", objArr6);
                            org.a.a.a.a.b.a aVar7 = this.e;
                            Object[] objArr7 = new Object[2];
                            objArr7[0] = str != null ? str : "null (broker defaults)";
                            objArr7[1] = keyManagerFactory.getProvider().getName();
                            aVar7.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12009", objArr7);
                        }
                        keyManagerFactory.init(keyStore, cArrE);
                        keyManagers = keyManagerFactory.getKeyManagers();
                    } catch (FileNotFoundException e) {
                        throw new o(e);
                    } catch (IOException e2) {
                        throw new o(e2);
                    } catch (KeyStoreException e3) {
                        throw new o(e3);
                    } catch (UnrecoverableKeyException e4) {
                        throw new o(e4);
                    } catch (CertificateException e5) {
                        throw new o(e5);
                    }
                }
            }
            String strI = i(str);
            if (this.e != null) {
                org.a.a.a.a.b.a aVar8 = this.e;
                Object[] objArr8 = new Object[2];
                objArr8[0] = str != null ? str : "null (broker defaults)";
                objArr8[1] = strI != null ? strI : "null";
                aVar8.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12011", objArr8);
            }
            char[] cArrJ = j(str);
            if (this.e != null) {
                org.a.a.a.a.b.a aVar9 = this.e;
                Object[] objArr9 = new Object[2];
                objArr9[0] = str != null ? str : "null (broker defaults)";
                objArr9[1] = cArrJ != null ? b(cArrJ) : "null";
                aVar9.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12012", objArr9);
            }
            String strK = k(str);
            if (strK == null) {
                strK = KeyStore.getDefaultType();
            }
            if (this.e != null) {
                org.a.a.a.a.b.a aVar10 = this.e;
                Object[] objArr10 = new Object[2];
                objArr10[0] = str != null ? str : "null (broker defaults)";
                objArr10[1] = strK != null ? strK : "null";
                aVar10.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12013", objArr10);
            }
            String defaultAlgorithm2 = TrustManagerFactory.getDefaultAlgorithm();
            String strL = l(str);
            String strM = m(str);
            if (strM == null) {
                strM = defaultAlgorithm2;
            }
            if (strI == null || strK == null || strM == null) {
                trustManagers = null;
            } else {
                try {
                    KeyStore keyStore2 = KeyStore.getInstance(strK);
                    keyStore2.load(new FileInputStream(strI), cArrJ);
                    if (strL != null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(strM, strL);
                    } else {
                        trustManagerFactory = TrustManagerFactory.getInstance(strM);
                    }
                    if (this.e != null) {
                        org.a.a.a.a.b.a aVar11 = this.e;
                        Object[] objArr11 = new Object[2];
                        objArr11[0] = str != null ? str : "null (broker defaults)";
                        if (strM == null) {
                            strM = "null";
                        }
                        objArr11[1] = strM;
                        aVar11.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12017", objArr11);
                        org.a.a.a.a.b.a aVar12 = this.e;
                        Object[] objArr12 = new Object[2];
                        if (str == null) {
                            str = "null (broker defaults)";
                        }
                        objArr12[0] = str;
                        objArr12[1] = trustManagerFactory.getProvider().getName();
                        aVar12.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12016", objArr12);
                    }
                    trustManagerFactory.init(keyStore2);
                    trustManagers = trustManagerFactory.getTrustManagers();
                } catch (FileNotFoundException e6) {
                    throw new o(e6);
                } catch (IOException e7) {
                    throw new o(e7);
                } catch (KeyStoreException e8) {
                    throw new o(e8);
                } catch (CertificateException e9) {
                    throw new o(e9);
                }
            }
            sSLContext.init(keyManagers, trustManagers, null);
            return sSLContext;
        } catch (KeyManagementException e10) {
            throw new o(e10);
        } catch (NoSuchAlgorithmException e11) {
            throw new o(e11);
        } catch (NoSuchProviderException e12) {
            throw new o(e12);
        }
    }

    public SSLSocketFactory o(String str) throws o {
        SSLContext sSLContextQ = q(str);
        if (this.e != null) {
            org.a.a.a.a.b.a aVar = this.e;
            Object[] objArr = new Object[2];
            objArr[0] = str != null ? str : "null (broker defaults)";
            objArr[1] = n(str) != null ? a(str, "com.ibm.ssl.enabledCipherSuites", null) : "null (using platform-enabled cipher suites)";
            aVar.b("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "createSocketFactory", "12020", objArr);
        }
        return sSLContextQ.getSocketFactory();
    }
}
