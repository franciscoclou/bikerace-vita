package org.a.a.a.a.a.b;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: CountingInputStream.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private InputStream f1573a;
    private int b = 0;

    public a(InputStream inputStream) {
        this.f1573a = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.f1573a.read();
        if (i != -1) {
            this.b++;
        }
        return i;
    }

    public int a() {
        return this.b;
    }
}
