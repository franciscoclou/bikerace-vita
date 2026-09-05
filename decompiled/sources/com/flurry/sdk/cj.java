package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class cj {
    private static final String b = cj.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    byte[] f522a;

    public cj(byte[] bArr) {
        this.f522a = bArr;
    }

    public cj(ck ckVar) throws Throwable {
        DataOutputStream dataOutputStream;
        int i;
        DataOutputStream dataOutputStream2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(1);
                dataOutputStream.writeUTF(ckVar.a());
                dataOutputStream.writeLong(ckVar.b());
                dataOutputStream.writeLong(ckVar.c());
                dataOutputStream.writeLong(ckVar.d());
                dataOutputStream.writeUTF(ckVar.e());
                dataOutputStream.writeUTF(ckVar.f());
                dataOutputStream.writeByte(ckVar.g());
                dataOutputStream.writeUTF(ckVar.h());
                if (ckVar.i() == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeDouble(a(ckVar.i().getLatitude()));
                    dataOutputStream.writeDouble(a(ckVar.i().getLongitude()));
                    dataOutputStream.writeFloat(ckVar.i().getAccuracy());
                }
                dataOutputStream.writeInt(ckVar.j());
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(ckVar.k());
                if (ckVar.l() == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeLong(ckVar.l().longValue());
                }
                Map<String, bx.a> mapM = ckVar.m();
                if (mapM == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(mapM.size());
                    for (Map.Entry<String, bx.a> entry : mapM.entrySet()) {
                        dataOutputStream.writeUTF(entry.getKey());
                        dataOutputStream.writeInt(entry.getValue().f504a);
                    }
                }
                List<cb> listN = ckVar.n();
                if (listN == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(listN.size());
                    Iterator<cb> it = listN.iterator();
                    while (it.hasNext()) {
                        dataOutputStream.write(it.next().e());
                    }
                }
                dataOutputStream.writeBoolean(ckVar.o());
                List<ca> listQ = ckVar.q();
                if (listQ != null) {
                    int i2 = 0;
                    int iA = 0;
                    int i3 = 0;
                    while (true) {
                        if (i2 >= listQ.size()) {
                            i = i3;
                            break;
                        }
                        iA += listQ.get(i2).a();
                        if (iA > 160000) {
                            ex.a(5, b, "Error Log size exceeded. No more event details logged.");
                            i = i3;
                            break;
                        } else {
                            i3++;
                            i2++;
                        }
                    }
                } else {
                    i = 0;
                }
                dataOutputStream.writeInt(ckVar.p());
                dataOutputStream.writeShort(i);
                for (int i4 = 0; i4 < i; i4++) {
                    dataOutputStream.write(listQ.get(i4).b());
                }
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                this.f522a = byteArrayOutputStream.toByteArray();
                fh.a(dataOutputStream);
            } catch (IOException e) {
                e = e;
                dataOutputStream2 = dataOutputStream;
                try {
                    ex.a(6, b, "", e);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    fh.a(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fh.a(dataOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
        }
    }

    public byte[] a() {
        return this.f522a;
    }

    double a(double d) {
        return Math.round(d * 1000.0d) / 1000.0d;
    }
}
