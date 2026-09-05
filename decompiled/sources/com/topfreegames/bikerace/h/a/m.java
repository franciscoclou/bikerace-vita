package com.topfreegames.bikerace.h.a;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: UserLevelImporter.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m {
    public static c a(byte[] bArr) {
        return a(bArr, null);
    }

    public static c a(byte[] bArr, com.topfreegames.bikerace.c.a[] aVarArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order();
        float f = byteBufferWrap.getFloat();
        int i = byteBufferWrap.getInt();
        float f2 = byteBufferWrap.getFloat();
        float[] fArr = {byteBufferWrap.getFloat(), byteBufferWrap.getFloat(), byteBufferWrap.getFloat()};
        com.topfreegames.engine.a.b bVar = new com.topfreegames.engine.a.b(byteBufferWrap.getFloat(), byteBufferWrap.getFloat());
        com.topfreegames.engine.a.b bVar2 = new com.topfreegames.engine.a.b(byteBufferWrap.getFloat(), byteBufferWrap.getFloat());
        int length = (int) ((bArr.length - f) / 16.0f);
        com.topfreegames.bikerace.h.b bVar3 = new com.topfreegames.bikerace.h.b();
        for (int i2 = 0; i2 < length; i2++) {
            bVar3.a(new com.topfreegames.bikerace.d(new com.topfreegames.engine.a.b(byteBufferWrap.getFloat(), byteBufferWrap.getFloat()), new com.topfreegames.engine.a.b(byteBufferWrap.getFloat(), byteBufferWrap.getFloat())));
        }
        return new c(bVar3, fArr, bVar, bVar2, f2, i, aVarArr);
    }
}
