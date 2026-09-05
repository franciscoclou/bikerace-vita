package com.b.a.a;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final File f274a;
    private final String b;
    private bp c;
    private File d;
    private File e;

    public bi(File file, String str, String str2) {
        this.f274a = file;
        this.b = str2;
        this.d = new File(file, str);
        this.c = new bp(this.d);
        this.e = new File(this.f274a, this.b);
        if (!this.e.exists()) {
            this.e.mkdirs();
        }
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
    }

    public int a() {
        return this.c.a();
    }

    public void a(String str) throws Throwable {
        FileInputStream fileInputStream;
        GZIPOutputStream gZIPOutputStream = null;
        this.c.close();
        File file = this.d;
        File file2 = new File(this.e, str);
        try {
            fileInputStream = new FileInputStream(file);
            try {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(new FileOutputStream(file2));
                try {
                    ba.a(fileInputStream, gZIPOutputStream2, new byte[1024]);
                    ba.a(fileInputStream, "Failed to close file input stream");
                    ba.a((Closeable) gZIPOutputStream2, "Failed to close gzip output stream");
                    file.delete();
                    this.c = new bp(this.d);
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    ba.a(fileInputStream, "Failed to close file input stream");
                    ba.a((Closeable) gZIPOutputStream, "Failed to close gzip output stream");
                    file.delete();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public List<File> a(int i) {
        ArrayList arrayList = new ArrayList();
        for (File file : this.e.listFiles()) {
            arrayList.add(file);
            if (arrayList.size() > 0) {
                break;
            }
        }
        return arrayList;
    }

    public void a(List<File> list) {
        for (File file : list) {
            ba.c(String.format("deleting sent analytics file %s", file.getName()));
            file.delete();
        }
    }

    public List<File> c() {
        return Arrays.asList(this.e.listFiles());
    }

    public void d() {
        try {
            this.c.close();
        } catch (IOException e) {
        }
        this.d.delete();
    }

    public boolean b() {
        return this.c.b();
    }

    public boolean a(int i, int i2) {
        return this.c.a(i, i2);
    }
}
