package org.a.a.a.a;

import java.util.Hashtable;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.a.a.a.a.a.b.r;

/* JADX INFO: compiled from: MqttAsyncClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e implements b {
    static final String b = e.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected org.a.a.a.a.a.a f1601a;
    public org.a.a.a.a.b.a c = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", b);
    private String d;
    private String e;
    private int f;
    private Hashtable g;
    private h h;

    public e(String str, String str2, h hVar) {
        this.c.a(str2);
        if (str2 == null || str2.length() == 0 || str2.length() > 23) {
            throw new IllegalArgumentException();
        }
        this.e = str;
        this.f = b(str);
        this.d = str2;
        this.h = hVar;
        if (this.h == null) {
            this.h = new org.a.a.a.a.c.a();
        }
        this.c.b(b, "MqttAsyncClient", "101", new Object[]{str2, str, hVar});
        this.h.a(str2, str);
        this.f1601a = new org.a.a.a.a.a.a(this, this.h);
        this.h.a();
        this.g = new Hashtable();
    }

    private int b(String str) {
        if (str.startsWith("tcp://")) {
            return 0;
        }
        if (str.startsWith("ssl://")) {
            return 1;
        }
        if (str.startsWith("local://")) {
            return 2;
        }
        throw new IllegalArgumentException();
    }

    protected org.a.a.a.a.a.l a(String str, i iVar) throws k {
        org.a.a.a.a.a.a.a aVar;
        String[] strArrN;
        this.c.b(b, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactoryE = iVar.e();
        switch (this.f) {
            case 0:
                String strSubstring = str.substring(6);
                String strC = c(strSubstring);
                int iA = a(strSubstring, 1883);
                if (socketFactoryE == null) {
                    socketFactoryE = SocketFactory.getDefault();
                    iVar.a(socketFactoryE);
                } else if (socketFactoryE instanceof SSLSocketFactory) {
                    throw org.a.a.a.a.a.i.a(32105);
                }
                org.a.a.a.a.a.o oVar = new org.a.a.a.a.a.o(socketFactoryE, strC, iA, this.d);
                oVar.b(iVar.d());
                return oVar;
            case 1:
                String strSubstring2 = str.substring(6);
                String strC2 = c(strSubstring2);
                int iA2 = a(strSubstring2, 8883);
                if (socketFactoryE == null) {
                    org.a.a.a.a.a.a.a aVar2 = new org.a.a.a.a.a.a.a();
                    Properties propertiesH = iVar.h();
                    if (propertiesH != null) {
                        aVar2.a(propertiesH, (String) null);
                    }
                    aVar = aVar2;
                    socketFactoryE = aVar2.o(null);
                } else {
                    if (!(socketFactoryE instanceof SSLSocketFactory)) {
                        throw org.a.a.a.a.a.i.a(32105);
                    }
                    aVar = null;
                }
                org.a.a.a.a.a.n nVar = new org.a.a.a.a.a.n((SSLSocketFactory) socketFactoryE, strC2, iA2, this.d);
                nVar.a(iVar.d());
                if (aVar != null && (strArrN = aVar.n(null)) != null) {
                    nVar.a(strArrN);
                    return nVar;
                }
                return nVar;
            case 2:
                return new org.a.a.a.a.a.j(str.substring(8));
            default:
                return null;
        }
    }

    private int a(String str, int i) {
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf != -1) {
            return Integer.valueOf(str.substring(iLastIndexOf + 1)).intValue();
        }
        return i;
    }

    private String c(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        int iLastIndexOf2 = str.lastIndexOf(58);
        if (iLastIndexOf2 == -1) {
            iLastIndexOf2 = str.length();
        }
        return str.substring(iLastIndexOf + 1, iLastIndexOf2);
    }

    public d a(i iVar, Object obj, a aVar) throws k {
        if (this.f1601a.b()) {
            throw org.a.a.a.a.a.i.a(32100);
        }
        if (this.f1601a.c()) {
            throw new k(32110);
        }
        if (this.f1601a.e()) {
            throw new k(32102);
        }
        if (this.f1601a.f()) {
            throw new k(32111);
        }
        org.a.a.a.a.b.a aVar2 = this.c;
        String str = b;
        Object[] objArr = new Object[8];
        objArr[0] = new Boolean(iVar.i());
        objArr[1] = new Integer(iVar.d());
        objArr[2] = new Integer(iVar.c());
        objArr[3] = iVar.b();
        objArr[4] = iVar.a() == null ? "[null]" : "[notnull]";
        objArr[5] = iVar.g() == null ? "[null]" : "[notnull]";
        objArr[6] = obj;
        objArr[7] = aVar;
        aVar2.b(str, "connect", "103", objArr);
        this.f1601a.a(a(this.e, iVar));
        this.h.a(this.d, this.e);
        if (iVar.i()) {
            this.h.c();
        }
        p pVar = new p(a());
        pVar.a(aVar);
        pVar.a(obj);
        this.f1601a.a(iVar, pVar);
        return pVar;
    }

    public d a(long j, Object obj, a aVar) throws k {
        this.c.b(b, "disconnect", "104", new Object[]{new Long(j), obj, aVar});
        p pVar = new p(a());
        pVar.a(aVar);
        pVar.a(obj);
        try {
            this.f1601a.a(new org.a.a.a.a.a.b.e(), j, pVar);
            this.c.a(b, "disconnect", "108");
            return pVar;
        } catch (k e) {
            this.c.a(b, "disconnect", "105", null, e);
            throw e;
        }
    }

    @Override // org.a.a.a.a.b
    public String a() {
        return this.d;
    }

    public d a(String[] strArr, int[] iArr, Object obj, a aVar) throws k {
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        String str = "";
        int i = 0;
        while (i < strArr.length) {
            if (i > 0) {
                str = str + ", ";
            }
            String str2 = str + strArr[i] + ":" + iArr[i];
            i++;
            str = str2;
        }
        this.c.b(b, "subscribe", "106", new Object[]{str, obj, aVar});
        p pVar = new p(a());
        pVar.a(aVar);
        pVar.a(obj);
        pVar.f1606a.a(strArr);
        this.f1601a.b(new r(strArr, iArr), pVar);
        this.c.a(b, "subscribe", "109");
        return pVar;
    }

    public void a(f fVar) {
        this.f1601a.a(fVar);
    }

    public static String b() {
        return System.getProperty("user.name") + "." + System.currentTimeMillis();
    }

    public c a(String str, l lVar, Object obj, a aVar) throws k {
        this.c.b(b, "publish", "111", new Object[]{str, obj, aVar});
        a(str);
        j jVar = new j(a());
        jVar.a(aVar);
        jVar.a(obj);
        jVar.a(lVar);
        jVar.f1606a.a(new String[]{str});
        this.f1601a.b(new org.a.a.a.a.a.b.o(str, lVar), jVar);
        this.c.a(b, "publish", "112");
        return jVar;
    }

    public static void a(String str) {
        if (str.indexOf(35) == -1 && str.indexOf(43) == -1) {
        } else {
            throw new IllegalArgumentException();
        }
    }
}
