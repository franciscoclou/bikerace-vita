package com.b.a.a;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ae extends BufferedOutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CharsetEncoder f254a;

    public ae(OutputStream outputStream, String str, int i) {
        super(outputStream, i);
        this.f254a = Charset.forName(bx.c(str)).newEncoder();
    }

    public final ae a(String str) {
        ByteBuffer byteBufferEncode = this.f254a.encode(CharBuffer.wrap(str));
        super.write(byteBufferEncode.array(), 0, byteBufferEncode.limit());
        return this;
    }
}
