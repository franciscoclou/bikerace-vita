package com.b.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class am {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f322a;
    private volatile int b = 0;

    private am(byte[] bArr) {
        this.f322a = bArr;
    }

    public final int a() {
        return this.f322a.length;
    }

    static {
        new am(new byte[0]);
    }

    public static am a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return new am(bArr2);
    }

    public static am a(String str) {
        try {
            return new am(str.getBytes(XMLStreamWriterImpl.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public final void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.f322a, i, bArr, i2, i3);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof am)) {
            return false;
        }
        am amVar = (am) obj;
        int length = this.f322a.length;
        if (length != amVar.f322a.length) {
            return false;
        }
        byte[] bArr = this.f322a;
        byte[] bArr2 = amVar.f322a;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = this.b;
        if (i == 0) {
            byte[] bArr = this.f322a;
            int length = this.f322a.length;
            int i2 = 0;
            i = length;
            while (i2 < length) {
                int i3 = bArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
            if (i == 0) {
                i = 1;
            }
            this.b = i;
        }
        return i;
    }

    public final InputStream b() {
        return new ByteArrayInputStream(this.f322a);
    }
}
