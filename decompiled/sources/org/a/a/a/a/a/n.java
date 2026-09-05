package org.a.a.a.a.a;

import com.facebook.widget.PlacePickerFragment;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: SSLNetworkModule.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f1595a = n.class.getName();
    org.a.a.a.a.b.a b;
    private String[] f;
    private int g;

    public n(SSLSocketFactory sSLSocketFactory, String str, int i, String str2) {
        super(sSLSocketFactory, str, i, str2);
        this.b = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", f1595a);
        this.b.a(str2);
    }

    public void a(String[] strArr) {
        this.f = strArr;
        if (this.c != null && strArr != null) {
            if (this.b.a(5)) {
                String str = "";
                int i = 0;
                while (i < strArr.length) {
                    if (i > 0) {
                        str = str + ",";
                    }
                    String str2 = str + strArr[i];
                    i++;
                    str = str2;
                }
                this.b.b(f1595a, "setEnabledCiphers", "260", new Object[]{str});
            }
            ((SSLSocket) this.c).setEnabledCipherSuites(strArr);
        }
    }

    public void a(int i) {
        this.g = i;
    }

    @Override // org.a.a.a.a.a.o, org.a.a.a.a.a.l
    public void a() throws org.a.a.a.a.k, IOException {
        super.a();
        a(this.f);
        int soTimeout = this.c.getSoTimeout();
        if (soTimeout == 0) {
            this.c.setSoTimeout(this.g * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        }
        ((SSLSocket) this.c).startHandshake();
        this.c.setSoTimeout(soTimeout);
    }
}
