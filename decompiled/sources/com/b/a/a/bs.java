package com.b.a.a;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bs extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f283a;
    private int b;
    private /* synthetic */ bp c;

    /* synthetic */ bs(bp bpVar, br brVar, byte b) {
        this(bpVar, brVar);
    }

    private bs(bp bpVar, br brVar) {
        this.c = bpVar;
        this.f283a = bpVar.b(brVar.b + 4);
        this.b = brVar.c;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        bp.b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (this.b <= 0) {
            return -1;
        }
        if (i2 > this.b) {
            i2 = this.b;
        }
        this.c.b(this.f283a, bArr, i, i2);
        this.f283a = this.c.b(this.f283a + i2);
        this.b -= i2;
        return i2;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.b != 0) {
            this.c.b.seek(this.f283a);
            int i = this.c.b.read();
            this.f283a = this.c.b(this.f283a + 1);
            this.b--;
            return i;
        }
        return -1;
    }
}
