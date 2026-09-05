package com.b.a.a;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class by extends z<bx> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ InputStream f288a;
    private /* synthetic */ OutputStream b;
    private /* synthetic */ bx c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    by(bx bxVar, Closeable closeable, boolean z, InputStream inputStream, OutputStream outputStream) {
        super(closeable, z);
        this.c = bxVar;
        this.f288a = inputStream;
        this.b = outputStream;
    }

    @Override // com.b.a.a.ad
    public final /* synthetic */ Object b() throws IOException {
        byte[] bArr = new byte[this.c.i];
        while (true) {
            int i = this.f288a.read(bArr);
            if (i == -1) {
                return this.c;
            }
            this.b.write(bArr, 0, i);
        }
    }
}
