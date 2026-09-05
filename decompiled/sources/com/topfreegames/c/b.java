package com.topfreegames.c;

import android.os.SystemClock;
import android.util.Log;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* JADX INFO: compiled from: SntpClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f1489a;
    private long b;
    private long c;

    b() {
    }

    public boolean a(String str, int i) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setSoTimeout(i);
            byte[] bArr = new byte[48];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, InetAddress.getByName(str), 123);
            bArr[0] = 27;
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            a(bArr, 40, jCurrentTimeMillis);
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(new DatagramPacket(bArr, bArr.length));
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            long j = jCurrentTimeMillis + (jElapsedRealtime2 - jElapsedRealtime);
            datagramSocket.close();
            long jB = b(bArr, 24);
            long jB2 = b(bArr, 32);
            long jB3 = b(bArr, 40);
            this.f1489a = (((jB2 - jB) + (jB3 - j)) / 2) + j;
            this.b = jElapsedRealtime2;
            this.c = (jElapsedRealtime2 - jElapsedRealtime) - (jB3 - jB2);
            return true;
        } catch (Exception e) {
            Log.d("NtpClient", "request time failed: " + e);
            return false;
        }
    }

    public long a() {
        return this.f1489a;
    }

    public long b() {
        return this.b;
    }

    private long a(byte[] bArr, int i) {
        int i2 = bArr[i];
        int i3 = bArr[i + 1];
        int i4 = bArr[i + 2];
        int i5 = bArr[i + 3];
        if ((i2 & XMLChar.MASK_NCNAME) == 128) {
            i2 = (i2 & 127) + XMLChar.MASK_NCNAME;
        }
        if ((i3 & XMLChar.MASK_NCNAME) == 128) {
            i3 = (i3 & 127) + XMLChar.MASK_NCNAME;
        }
        if ((i4 & XMLChar.MASK_NCNAME) == 128) {
            i4 = (i4 & 127) + XMLChar.MASK_NCNAME;
        }
        if ((i5 & XMLChar.MASK_NCNAME) == 128) {
            i5 = (i5 & 127) + XMLChar.MASK_NCNAME;
        }
        return (((long) i2) << 24) + (((long) i3) << 16) + (((long) i4) << 8) + ((long) i5);
    }

    private long b(byte[] bArr, int i) {
        return ((a(bArr, i) - 2208988800L) * 1000) + ((a(bArr, i + 4) * 1000) / 4294967296L);
    }

    private void a(byte[] bArr, int i, long j) {
        long j2 = j / 1000;
        long j3 = j - (1000 * j2);
        long j4 = j2 + 2208988800L;
        int i2 = i + 1;
        bArr[i] = (byte) (j4 >> 24);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j4 >> 16);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j4 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j4 >> 0);
        long j5 = (4294967296L * j3) / 1000;
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j5 >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j5 >> 16);
        int i8 = i7 + 1;
        bArr[i7] = (byte) (j5 >> 8);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (Math.random() * 255.0d);
    }
}
