package com.b.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class aq implements Flushable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f325a;
    private final int b;
    private int c = 0;
    private final OutputStream d;

    private aq(OutputStream outputStream, byte[] bArr) {
        this.d = outputStream;
        this.f325a = bArr;
        this.b = bArr.length;
    }

    public static aq a(OutputStream outputStream) {
        return new aq(outputStream, new byte[4096]);
    }

    public final void a(int i, float f) {
        g(1, 5);
        int iFloatToRawIntBits = Float.floatToRawIntBits(f);
        d(iFloatToRawIntBits & 255);
        d((iFloatToRawIntBits >> 8) & 255);
        d((iFloatToRawIntBits >> 16) & 255);
        d(iFloatToRawIntBits >>> 24);
    }

    public final void a(int i, long j) {
        g(i, 0);
        a(j);
    }

    public final void a(int i, boolean z) {
        g(i, 0);
        d(z ? 1 : 0);
    }

    public final void a(int i, String str) throws UnsupportedEncodingException {
        g(1, 2);
        byte[] bytes = str.getBytes(XMLStreamWriterImpl.UTF_8);
        b(bytes.length);
        a(bytes);
    }

    public final void a(int i, am amVar) {
        g(i, 2);
        b(amVar.a());
        int iA = amVar.a();
        if (this.b - this.c < iA) {
            int i2 = this.b - this.c;
            amVar.a(this.f325a, 0, this.c, i2);
            int i3 = i2 + 0;
            int i4 = iA - i2;
            this.c = this.b;
            a();
            if (i4 <= this.b) {
                amVar.a(this.f325a, i3, 0, i4);
                this.c = i4;
                return;
            }
            InputStream inputStreamB = amVar.b();
            if (i3 != inputStreamB.skip(i3)) {
                throw new IllegalStateException("Skip failed.");
            }
            while (i4 > 0) {
                int iMin = Math.min(i4, this.b);
                int i5 = inputStreamB.read(this.f325a, 0, iMin);
                if (i5 != iMin) {
                    throw new IllegalStateException("Read failed.");
                }
                this.d.write(this.f325a, 0, i5);
                i4 -= i5;
            }
            return;
        }
        amVar.a(this.f325a, 0, this.c, iA);
        this.c = iA + this.c;
    }

    public final void a(int i, int i2) {
        g(i, 0);
        b(i2);
    }

    public final void b(int i, int i2) throws IOException {
        g(i, 0);
        if (i2 < 0) {
            a(i2);
        } else {
            b(i2);
        }
    }

    public final void c(int i, int i2) {
        g(2, 0);
        b(e(i2));
    }

    public static int b(int i, float f) {
        return a(1) + 4;
    }

    public static int b(int i, long j) {
        int i2;
        int iA = a(i);
        if (((-128) & j) == 0) {
            i2 = 1;
        } else if (((-16384) & j) == 0) {
            i2 = 2;
        } else if (((-2097152) & j) == 0) {
            i2 = 3;
        } else if (((-268435456) & j) == 0) {
            i2 = 4;
        } else if (((-34359738368L) & j) == 0) {
            i2 = 5;
        } else if (((-4398046511104L) & j) == 0) {
            i2 = 6;
        } else if (((-562949953421312L) & j) == 0) {
            i2 = 7;
        } else if (((-72057594037927936L) & j) == 0) {
            i2 = 8;
        } else {
            i2 = (Long.MIN_VALUE & j) == 0 ? 9 : 10;
        }
        return i2 + iA;
    }

    public static int b(int i, boolean z) {
        return a(i) + 1;
    }

    public static int b(int i, am amVar) {
        return a(i) + c(amVar.a()) + amVar.a();
    }

    public static int d(int i, int i2) {
        return a(i) + c(i2);
    }

    public static int e(int i, int i2) {
        return (i2 >= 0 ? c(i2) : 10) + a(i);
    }

    public static int f(int i, int i2) {
        return a(2) + c(e(i2));
    }

    private void a() throws IOException {
        if (this.d == null) {
            throw new ar();
        }
        this.d.write(this.f325a, 0, this.c);
        this.c = 0;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.d != null) {
            a();
        }
    }

    private void d(int i) throws IOException {
        byte b = (byte) i;
        if (this.c == this.b) {
            a();
        }
        byte[] bArr = this.f325a;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = b;
    }

    public final void a(byte[] bArr) {
        int length = bArr.length;
        if (this.b - this.c < length) {
            int i = this.b - this.c;
            System.arraycopy(bArr, 0, this.f325a, this.c, i);
            int i2 = i + 0;
            int i3 = length - i;
            this.c = this.b;
            a();
            if (i3 <= this.b) {
                System.arraycopy(bArr, i2, this.f325a, 0, i3);
                this.c = i3;
                return;
            } else {
                this.d.write(bArr, i2, i3);
                return;
            }
        }
        System.arraycopy(bArr, 0, this.f325a, this.c, length);
        this.c = length + this.c;
    }

    public final void g(int i, int i2) {
        b(aj.a(i, i2));
    }

    public static int a(int i) {
        return c(aj.a(i, 0));
    }

    public final void b(int i) {
        while ((i & (-128)) != 0) {
            d((i & 127) | XMLChar.MASK_NCNAME);
            i >>>= 7;
        }
        d(i);
    }

    public static int c(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return ((-268435456) & i) == 0 ? 4 : 5;
    }

    private void a(long j) throws IOException {
        while (((-128) & j) != 0) {
            d((((int) j) & 127) | XMLChar.MASK_NCNAME);
            j >>>= 7;
        }
        d((int) j);
    }

    private static int e(int i) {
        return (i << 1) ^ (i >> 31);
    }
}
