package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class cb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f508a;
    private String b;
    private Map<String, String> c;
    private long d;
    private boolean e;
    private boolean f;
    private long g;

    public cb(int i, String str, Map<String, String> map, long j, boolean z) {
        this.f508a = i;
        this.b = str;
        this.c = map;
        this.d = j;
        this.e = z;
        if (this.e) {
            this.f = false;
        } else {
            this.f = true;
        }
    }

    public boolean a() {
        return this.e;
    }

    public boolean b() {
        return this.f;
    }

    public boolean a(String str) {
        return this.e && this.g == 0 && this.b.equals(str);
    }

    public void a(long j) {
        this.f = true;
        this.g = j - this.d;
        ex.a(3, "FlurryAgent", "Ended event '" + this.b + "' (" + this.d + ") after " + this.g + "ms");
    }

    public void a(Map<String, String> map) {
        if (this.c == null || this.c.size() == 0) {
            this.c = map;
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (this.c.containsKey(entry.getKey())) {
                this.c.remove(entry.getKey());
                this.c.put(entry.getKey(), entry.getValue());
            } else {
                this.c.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, String> c() {
        return this.c;
    }

    public void b(Map<String, String> map) {
        this.c = map;
    }

    public int d() {
        return e().length;
    }

    public byte[] e() throws Throwable {
        DataOutputStream dataOutputStream;
        Throwable th;
        DataOutputStream dataOutputStream2;
        byte[] byteArray;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(this.f508a);
                dataOutputStream.writeUTF(this.b);
                if (this.c == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(this.c.size());
                    for (Map.Entry<String, String> entry : this.c.entrySet()) {
                        dataOutputStream.writeUTF(fh.a(entry.getKey()));
                        dataOutputStream.writeUTF(fh.a(entry.getValue()));
                    }
                }
                dataOutputStream.writeLong(this.d);
                dataOutputStream.writeLong(this.g);
                dataOutputStream.flush();
                byteArray = byteArrayOutputStream.toByteArray();
                fh.a(dataOutputStream);
            } catch (IOException e) {
                dataOutputStream2 = dataOutputStream;
                try {
                    byteArray = new byte[0];
                    fh.a(dataOutputStream2);
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = dataOutputStream2;
                    fh.a(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fh.a(dataOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            dataOutputStream2 = null;
        } catch (Throwable th4) {
            dataOutputStream = null;
            th = th4;
        }
        return byteArray;
    }
}
