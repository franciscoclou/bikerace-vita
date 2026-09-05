package com.b.a.a;

import com.flurry.android.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bp implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f280a = Logger.getLogger(bp.class.getName());
    private final RandomAccessFile b;
    private int c;
    private int d;
    private br e;
    private br f;
    private final byte[] g = new byte[16];

    public bp(File file) throws IOException {
        if (!file.exists()) {
            File file2 = new File(file.getPath() + ".tmp");
            RandomAccessFile randomAccessFileA = a(file2);
            try {
                randomAccessFileA.setLength(4096L);
                randomAccessFileA.seek(0L);
                byte[] bArr = new byte[16];
                a(bArr, 4096, 0, 0, 0);
                randomAccessFileA.write(bArr);
                randomAccessFileA.close();
                if (!file2.renameTo(file)) {
                    throw new IOException("Rename failed!");
                }
            } catch (Throwable th) {
                randomAccessFileA.close();
                throw th;
            }
        }
        this.b = a(file);
        this.b.seek(0L);
        this.b.readFully(this.g);
        this.c = a(this.g, 0);
        if (this.c > this.b.length()) {
            throw new IOException("File is truncated. Expected length: " + this.c + ", Actual length: " + this.b.length());
        }
        this.d = a(this.g, 4);
        int iA = a(this.g, 8);
        int iA2 = a(this.g, 12);
        this.e = a(iA);
        this.f = a(iA2);
    }

    private static void a(byte[] bArr, int i, int i2) {
        bArr[i] = i2 >> 24;
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        for (int i2 : iArr) {
            a(bArr, i, i2);
            i += 4;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((bArr[i] & Constants.UNKNOWN) << 24) + ((bArr[i + 1] & Constants.UNKNOWN) << 16) + ((bArr[i + 2] & Constants.UNKNOWN) << 8) + (bArr[i + 3] & Constants.UNKNOWN);
    }

    private void a(int i, int i2, int i3, int i4) throws IOException {
        a(this.g, i, i2, i3, i4);
        this.b.seek(0L);
        this.b.write(this.g);
    }

    private br a(int i) throws IOException {
        if (i == 0) {
            return br.f282a;
        }
        this.b.seek(i);
        return new br(i, this.b.readInt());
    }

    private static RandomAccessFile a(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i) {
        return i < this.c ? i : (i + 16) - this.c;
    }

    private void a(int i, byte[] bArr, int i2, int i3) throws IOException {
        int iB = b(i);
        if (iB + i3 <= this.c) {
            this.b.seek(iB);
            this.b.write(bArr, i2, i3);
            return;
        }
        int i4 = this.c - iB;
        this.b.seek(iB);
        this.b.write(bArr, i2, i4);
        this.b.seek(16L);
        this.b.write(bArr, i2 + i4, i3 - i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int iB = b(i);
        if (iB + i3 <= this.c) {
            this.b.seek(iB);
            this.b.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.c - iB;
        this.b.seek(iB);
        this.b.readFully(bArr, i2, i4);
        this.b.seek(16L);
        this.b.readFully(bArr, i2 + i4, i3 - i4);
    }

    public final void a(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    private synchronized void b(byte[] bArr, int i, int i2) {
        b(bArr, "buffer");
        if ((i2 | 0) < 0 || i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        c(i2);
        boolean zB = b();
        br brVar = new br(zB ? 16 : b(this.f.b + 4 + this.f.c), i2);
        a(this.g, 0, i2);
        a(brVar.b, this.g, 0, 4);
        a(brVar.b + 4, bArr, 0, i2);
        a(this.c, this.d + 1, zB ? brVar.b : this.e.b, brVar.b);
        this.f = brVar;
        this.d++;
        if (zB) {
            this.e = this.f;
        }
    }

    public final int a() {
        if (this.d == 0) {
            return 16;
        }
        if (this.f.b >= this.e.b) {
            return (this.f.b - this.e.b) + 4 + this.f.c + 16;
        }
        return (((this.f.b + 4) + this.f.c) + this.c) - this.e.b;
    }

    public final synchronized boolean b() {
        return this.d == 0;
    }

    private void c(int i) throws IOException {
        int i2;
        int i3 = i + 4;
        int iA = this.c - a();
        if (iA < i3) {
            int i4 = this.c;
            while (true) {
                iA += i4;
                i2 = i4 << 1;
                if (iA >= i3) {
                    break;
                } else {
                    i4 = i2;
                }
            }
            d(i2);
            int iB = b(this.f.b + 4 + this.f.c);
            if (iB < this.e.b) {
                FileChannel channel = this.b.getChannel();
                channel.position(this.c);
                int i5 = iB - 4;
                if (channel.transferTo(16L, i5, channel) != i5) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f.b < this.e.b) {
                int i6 = (this.c + this.f.b) - 16;
                a(i2, this.d, this.e.b, i6);
                this.f = new br(i6, this.f.c);
            } else {
                a(i2, this.d, this.e.b, this.f.b);
            }
            this.c = i2;
        }
    }

    private void d(int i) throws IOException {
        this.b.setLength(i);
        this.b.getChannel().force(true);
    }

    public final synchronized void a(bt btVar) {
        synchronized (this) {
            int iB = this.e.b;
            for (int i = 0; i < this.d; i++) {
                br brVarA = a(iB);
                btVar.a(new bs(this, brVarA, (byte) 0), brVarA.c);
                iB = b(brVarA.c + brVarA.b + 4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T b(T t, String str) {
        if (t == null) {
            throw new NullPointerException(str);
        }
        return t;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.b.close();
    }

    public final boolean a(int i, int i2) {
        return (a() + 4) + i <= i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('[');
        sb.append("fileLength=").append(this.c);
        sb.append(", size=").append(this.d);
        sb.append(", first=").append(this.e);
        sb.append(", last=").append(this.f);
        sb.append(", element lengths=[");
        try {
            a(new bq(this, sb));
        } catch (IOException e) {
            f280a.log(Level.WARNING, "read error", (Throwable) e);
        }
        sb.append("]]");
        return sb.toString();
    }
}
