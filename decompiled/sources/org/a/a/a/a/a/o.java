package org.a.a.a.a.a;

import com.facebook.widget.PlacePickerFragment;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;

/* JADX INFO: compiled from: TCPNetworkModule.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o implements l {
    static final String d = o.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SocketFactory f1596a;
    private String b;
    protected Socket c;
    org.a.a.a.a.b.a e = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", d);
    private int f;
    private int g;

    public o(SocketFactory socketFactory, String str, int i, String str2) {
        this.e.a(str2);
        this.f1596a = socketFactory;
        this.b = str;
        this.f = i;
    }

    @Override // org.a.a.a.a.a.l
    public void a() throws org.a.a.a.a.k, IOException {
        try {
            this.e.b(d, "start", "252", new Object[]{this.b, new Integer(this.f), new Long(this.g * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS)});
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.b, this.f);
            this.c = this.f1596a.createSocket();
            this.c.connect(inetSocketAddress, this.g * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        } catch (ConnectException e) {
            this.e.a(d, "start", "250", null, e);
            throw new org.a.a.a.a.k(32103, e);
        }
    }

    @Override // org.a.a.a.a.a.l
    public InputStream b() {
        return this.c.getInputStream();
    }

    @Override // org.a.a.a.a.a.l
    public OutputStream c() {
        return this.c.getOutputStream();
    }

    @Override // org.a.a.a.a.a.l
    public void d() throws IOException {
        if (this.c != null) {
            this.c.close();
        }
    }

    public void b(int i) {
        this.g = i;
    }
}
