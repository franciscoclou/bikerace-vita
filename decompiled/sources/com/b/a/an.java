package com.b.a;

import android.os.Process;
import com.b.a.a.bn;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class an {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicLong f323a = new AtomicLong(0);
    private static String b;

    public an(bn bnVar) {
        long time = new Date().getTime();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt((int) (time / 1000));
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.position(0);
        byte[] bArrArray = byteBufferAllocate.array();
        byte[] bArrA = a(time % 1000);
        byte[] bArrA2 = a(f323a.incrementAndGet());
        byte[] bArrA3 = a(Integer.valueOf(Process.myPid()).shortValue());
        byte[] bArr = {bArrArray[0], bArrArray[1], bArrArray[2], bArrArray[3], bArrA[0], bArrA[1], bArrA2[0], bArrA2[1], bArrA3[0], bArrA3[1]};
        String strA = com.b.a.a.ba.a(bnVar.b());
        String strA2 = com.b.a.a.ba.a(bArr);
        b = String.format(Locale.US, "%s-%s-%s-%s", strA2.substring(0, 12), strA2.substring(12, 16), strA2.subSequence(16, 20), strA.substring(0, 12)).toUpperCase(Locale.US);
    }

    private static byte[] a(long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2);
        byteBufferAllocate.putShort((short) j);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.position(0);
        return byteBufferAllocate.array();
    }

    public final String toString() {
        return b;
    }
}
