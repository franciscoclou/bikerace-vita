package com.flurry.sdk;

import android.os.Build;
import com.flurry.android.FlurryAgent;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.security.DigestOutputStream;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class cc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f509a = cc.class.getSimpleName();
    private byte[] b;

    public cc(String str, String str2, String str3, boolean z, long j, long j2, List<cj> list, Map<ej, ByteBuffer> map, Map<String, List<String>> map2, Map<String, List<String>> map3, long j3) throws Throwable {
        DataOutputStream dataOutputStream;
        byte[] byteArray;
        this.b = null;
        DataOutputStream dataOutputStream2 = null;
        try {
            er erVar = new er();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DigestOutputStream digestOutputStream = new DigestOutputStream(byteArrayOutputStream, erVar);
            dataOutputStream = new DataOutputStream(digestOutputStream);
            try {
                dataOutputStream.writeShort(27);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeLong(0L);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(3);
                dataOutputStream.writeShort(FlurryAgent.getAgentVersion());
                dataOutputStream.writeLong(j3);
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeUTF(str2);
                dataOutputStream.writeShort(map.size() + 1);
                dataOutputStream.writeShort(bx.m());
                dataOutputStream.writeUTF(str3);
                if (!map.isEmpty()) {
                    for (Map.Entry<ej, ByteBuffer> entry : map.entrySet()) {
                        dataOutputStream.writeShort(entry.getKey().c);
                        byte[] bArrArray = entry.getValue().array();
                        dataOutputStream.writeShort(bArrArray.length);
                        dataOutputStream.write(bArrArray);
                    }
                }
                dataOutputStream.writeByte(0);
                dataOutputStream.writeBoolean(z);
                dataOutputStream.writeLong(j);
                dataOutputStream.writeLong(j2);
                dataOutputStream.writeShort(6);
                dataOutputStream.writeUTF("device.model");
                dataOutputStream.writeUTF(Build.MODEL);
                dataOutputStream.writeUTF("build.brand");
                dataOutputStream.writeUTF(Build.BRAND);
                dataOutputStream.writeUTF("build.id");
                dataOutputStream.writeUTF(Build.ID);
                dataOutputStream.writeUTF("version.release");
                dataOutputStream.writeUTF(Build.VERSION.RELEASE);
                dataOutputStream.writeUTF("build.device");
                dataOutputStream.writeUTF(Build.DEVICE);
                dataOutputStream.writeUTF("build.product");
                dataOutputStream.writeUTF(Build.PRODUCT);
                dataOutputStream.writeShort(map2 != null ? map2.keySet().size() : 0);
                if (map2 != null) {
                    ex.a(3, f509a, "sending referrer values because it exists");
                    for (Map.Entry<String, List<String>> entry2 : map2.entrySet()) {
                        ex.a(3, f509a, "Referrer Entry:  " + entry2.getKey() + "=" + entry2.getValue());
                        dataOutputStream.writeUTF(entry2.getKey());
                        ex.a(3, f509a, "referrer key is :" + entry2.getKey());
                        dataOutputStream.writeShort(entry2.getValue().size());
                        for (String str4 : entry2.getValue()) {
                            dataOutputStream.writeUTF(str4);
                            ex.a(3, f509a, "referrer value is :" + str4);
                        }
                    }
                }
                dataOutputStream.writeBoolean(false);
                int size = map3 != null ? map3.keySet().size() : 0;
                ex.a(3, f509a, "optionsMapSize is:  " + size);
                dataOutputStream.writeShort(size);
                if (map3 != null) {
                    ex.a(3, f509a, "sending launch options");
                    for (Map.Entry<String, List<String>> entry3 : map3.entrySet()) {
                        ex.a(3, f509a, "Launch Options Key:  " + entry3.getKey());
                        dataOutputStream.writeUTF(entry3.getKey());
                        dataOutputStream.writeShort(entry3.getValue().size());
                        for (String str5 : entry3.getValue()) {
                            dataOutputStream.writeUTF(str5);
                            ex.a(3, f509a, "Launch Options value is :" + str5);
                        }
                    }
                }
                int size2 = list.size();
                dataOutputStream.writeShort(size2);
                for (int i = 0; i < size2; i++) {
                    dataOutputStream.write(list.get(i).a());
                }
                digestOutputStream.on(false);
                dataOutputStream.write(erVar.a());
                dataOutputStream.close();
                byteArray = byteArrayOutputStream.toByteArray();
                fh.a(dataOutputStream);
            } catch (Throwable th) {
                th = th;
                dataOutputStream2 = dataOutputStream;
                try {
                    ex.a(6, f509a, "Error when generating report", th);
                    fh.a(dataOutputStream2);
                    byteArray = null;
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = dataOutputStream2;
                    fh.a(dataOutputStream);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
        }
        this.b = byteArray;
    }

    public byte[] a() {
        return this.b;
    }
}
