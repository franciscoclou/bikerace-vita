package org.a.a.a.a.a.b;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: MqttWireMessage.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte f1583a;
    protected boolean c = false;
    private byte[] d = null;
    protected int b = 0;

    protected abstract byte d_();

    protected abstract byte[] f_();

    public t(byte b) {
        this.f1583a = b;
    }

    public byte[] e_() {
        return new byte[0];
    }

    public byte i() {
        return this.f1583a;
    }

    public int j() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String e() {
        return new Integer(j()).toString();
    }

    public byte[] k() throws org.a.a.a.a.k {
        if (this.d == null) {
            try {
                int i = ((i() & 15) << 4) ^ (d_() & 15);
                byte[] bArrF_ = f_();
                int length = bArrF_.length + e_().length;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeByte(i);
                dataOutputStream.write(a(length));
                dataOutputStream.write(bArrF_);
                dataOutputStream.flush();
                this.d = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new org.a.a.a.a.k(e);
            }
        }
        return this.d;
    }

    public boolean g_() {
        return true;
    }

    public static t a(org.a.a.a.a.m mVar) {
        byte[] bArrD = mVar.d();
        if (bArrD == null) {
            bArrD = new byte[0];
        }
        return a(new u(mVar.a(), mVar.c(), mVar.b(), bArrD, mVar.f(), mVar.c_()));
    }

    public static t a(byte[] bArr) {
        return a(new ByteArrayInputStream(bArr));
    }

    private static t a(InputStream inputStream) throws org.a.a.a.a.k {
        try {
            a aVar = new a(inputStream);
            DataInputStream dataInputStream = new DataInputStream(aVar);
            int unsignedByte = dataInputStream.readUnsignedByte();
            byte b = (byte) (unsignedByte >> 4);
            byte b2 = (byte) (unsignedByte & 15);
            long jA = (a(dataInputStream).a() + ((long) aVar.a())) - ((long) aVar.a());
            byte[] bArr = new byte[0];
            if (jA > 0) {
                bArr = new byte[(int) jA];
                dataInputStream.readFully(bArr, 0, bArr.length);
            }
            byte[] bArr2 = bArr;
            if (b == 3) {
                return new o(b2, bArr2);
            }
            if (b == 4) {
                return new k(b2, bArr2);
            }
            if (b == 7) {
                return new l(b2, bArr2);
            }
            if (b == 2) {
                return new c(b2, bArr2);
            }
            if (b == 13) {
                return new j(b2, bArr2);
            }
            if (b == 9) {
                return new q(b2, bArr2);
            }
            if (b == 11) {
                return new s(b2, bArr2);
            }
            if (b == 6) {
                return new n(b2, bArr2);
            }
            if (b == 5) {
                return new m(b2, bArr2);
            }
            throw org.a.a.a.a.a.i.a(6);
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }

    protected static byte[] a(long j) {
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        do {
            byte b = (byte) (j % 128);
            j /= 128;
            if (j > 0) {
                b = (byte) (b | 128);
            }
            byteArrayOutputStream.write(b);
            i++;
            if (j <= 0) {
                break;
            }
        } while (i < 4);
        return byteArrayOutputStream.toByteArray();
    }

    protected static v a(DataInputStream dataInputStream) throws IOException {
        byte b;
        long j = 0;
        int i = 1;
        int i2 = 0;
        do {
            b = dataInputStream.readByte();
            i2++;
            j += (long) ((b & 127) * i);
            i *= XMLChar.MASK_NCNAME;
        } while ((b & 128) != 0);
        return new v(j, i2);
    }

    protected byte[] l() throws org.a.a.a.a.k {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.b);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }

    public void a(boolean z) {
        this.c = z;
    }

    protected void a(DataOutputStream dataOutputStream, String str) throws org.a.a.a.a.k {
        try {
            byte[] bytes = str.getBytes(XMLStreamWriterImpl.UTF_8);
            byte length = (byte) ((bytes.length >>> 8) & 255);
            byte length2 = (byte) ((bytes.length >>> 0) & 255);
            dataOutputStream.write(length);
            dataOutputStream.write(length2);
            dataOutputStream.write(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new org.a.a.a.a.k(e);
        } catch (IOException e2) {
            throw new org.a.a.a.a.k(e2);
        }
    }

    protected String b(DataInputStream dataInputStream) throws org.a.a.a.a.k {
        try {
            byte[] bArr = new byte[dataInputStream.readUnsignedShort()];
            dataInputStream.readFully(bArr);
            return new String(bArr, XMLStreamWriterImpl.UTF_8);
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }
}
