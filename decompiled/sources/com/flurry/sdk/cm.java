package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class cm {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f529a = cm.class.getSimpleName();
    private boolean b;
    private List<cj> c;
    private long d = -1;

    public void a(boolean z) {
        this.b = z;
    }

    public boolean a() {
        return this.b;
    }

    public void a(List<cj> list) {
        this.c = list;
    }

    public List<cj> b() {
        return this.c;
    }

    public void a(long j) {
        this.d = j;
    }

    public long c() {
        return this.d;
    }

    public void a(DataOutputStream dataOutputStream, String str, String str2) {
        try {
            try {
                dataOutputStream.writeShort(46586);
                dataOutputStream.writeShort(2);
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeUTF(str2);
                dataOutputStream.writeBoolean(this.b);
                dataOutputStream.writeLong(this.d);
                for (int size = this.c.size() - 1; size >= 0; size--) {
                    byte[] bArrA = this.c.get(size).a();
                    int length = bArrA.length;
                    if (length + 2 + dataOutputStream.size() > 50000) {
                        ex.a(6, f529a, "discarded sessions: " + size);
                        break;
                    } else {
                        dataOutputStream.writeShort(length);
                        dataOutputStream.write(bArrA);
                    }
                }
                dataOutputStream.writeShort(0);
                fh.a(dataOutputStream);
            } catch (Throwable th) {
                ex.a(6, f529a, "", th);
                throw new IOException(th.getMessage());
            }
        } catch (Throwable th2) {
            fh.a(dataOutputStream);
            throw th2;
        }
    }

    public boolean a(DataInputStream dataInputStream, String str) {
        boolean zA = false;
        try {
            try {
                int unsignedShort = dataInputStream.readUnsignedShort();
                ex.a(4, f529a, "Magic: " + unsignedShort);
                if (unsignedShort == 46586) {
                    zA = a(str, dataInputStream);
                } else {
                    ex.a(3, f529a, "Unexpected file type");
                }
                fh.a(dataInputStream);
                return zA;
            } catch (Throwable th) {
                ex.a(6, f529a, "Error when loading persistent file", th);
                throw new IOException(th.getMessage());
            }
        } catch (Throwable th2) {
            fh.a(dataInputStream);
            throw th2;
        }
    }

    private boolean a(String str, DataInputStream dataInputStream) throws IOException {
        int unsignedShort = dataInputStream.readUnsignedShort();
        ex.a(3, f529a, "File version: " + unsignedShort);
        if (unsignedShort > 2) {
            ex.a(6, f529a, "Unknown agent file version: " + unsignedShort);
            throw new IOException("Unknown agent file version: " + unsignedShort);
        }
        if (unsignedShort >= 2) {
            String utf = dataInputStream.readUTF();
            ex.a(3, f529a, "Loading API key: " + a(str));
            if (utf.equals(str)) {
                ArrayList arrayList = new ArrayList();
                dataInputStream.readUTF();
                boolean z = dataInputStream.readBoolean();
                long j = dataInputStream.readLong();
                ex.a(3, f529a, "Loading session reports");
                int i = 0;
                while (true) {
                    int unsignedShort2 = dataInputStream.readUnsignedShort();
                    if (unsignedShort2 != 0) {
                        byte[] bArr = new byte[unsignedShort2];
                        dataInputStream.readFully(bArr);
                        arrayList.add(0, new cj(bArr));
                        i++;
                        ex.a(3, f529a, "Session report added: " + i);
                    } else {
                        ex.a(3, f529a, "Persistent file loaded");
                        a(z);
                        a(j);
                        a(arrayList);
                        return true;
                    }
                }
            } else {
                ex.a(3, f529a, "Api keys do not match, old: " + a(str) + ", new: " + a(utf));
                return false;
            }
        } else {
            ex.a(5, f529a, "Deleting old file version: " + unsignedShort);
            return false;
        }
    }

    private static String a(String str) {
        if (str != null && str.length() > 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length() - 4; i++) {
                sb.append('*');
            }
            sb.append(str.substring(str.length() - 4));
            return sb.toString();
        }
        return str;
    }
}
