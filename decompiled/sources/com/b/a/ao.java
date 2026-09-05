package com.b.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ao extends FileOutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final FilenameFilter f324a = new ap();
    private final String b;
    private File c;
    private boolean d;

    public ao(File file, String str) {
        super(new File(file, str + ".cls_temp"));
        this.d = false;
        this.b = file + File.separator + str;
        this.c = new File(this.b + ".cls_temp");
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!this.d) {
            this.d = true;
            super.flush();
            super.close();
            File file = new File(this.b + ".cls");
            if (this.c.renameTo(file)) {
                this.c = null;
            } else {
                String str = "";
                if (file.exists()) {
                    str = " (target already exists)";
                } else if (!this.c.exists()) {
                    str = " (source does not exist)";
                }
                throw new IOException("Could not rename temp file: " + this.c + " -> " + file + str);
            }
        }
    }

    public final void a() {
        if (!this.d) {
            this.d = true;
            super.flush();
            super.close();
        }
    }
}
