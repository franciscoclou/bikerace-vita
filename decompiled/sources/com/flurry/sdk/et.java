package com.flurry.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class et {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f553a = et.class.getSimpleName();

    public static boolean a(File file) {
        if (file == null || file.getAbsoluteFile() == null) {
            return false;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return true;
        }
        if (parentFile.mkdirs() || parentFile.isDirectory()) {
            return true;
        }
        ex.a(6, f553a, "Unable to create persistent dir: " + parentFile);
        return false;
    }

    @Deprecated
    public static String b(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        StringBuilder sb;
        if (file == null || !file.exists()) {
            ex.a(4, f553a, "Persistent file doesn't exist.");
            return null;
        }
        ex.a(4, f553a, "Loading persistent data: " + file.getAbsolutePath());
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    sb = new StringBuilder();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        sb.append(new String(bArr, 0, i));
                    }
                    fh.a(fileInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    ex.a(6, f553a, "Error when loading persistent file", th);
                    fh.a(fileInputStream);
                    sb = null;
                }
            } catch (Throwable th3) {
                th = th3;
                fh.a(fileInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            fh.a(fileInputStream);
            throw th;
        }
        if (sb != null) {
            return sb.toString();
        }
        return null;
    }

    @Deprecated
    public static void a(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        if (file == null) {
            ex.a(4, f553a, "No persistent file specified.");
            return;
        }
        if (str == null) {
            ex.a(4, f553a, "No data specified; deleting persistent file: " + file.getAbsolutePath());
            file.delete();
            return;
        }
        ex.a(4, f553a, "Writing persistent data: " + file.getAbsolutePath());
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    fileOutputStream.write(str.getBytes());
                    fh.a(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    ex.a(6, f553a, "Error writing persistent file", th);
                    fh.a(fileOutputStream);
                }
            } catch (Throwable th2) {
                th = th2;
                fh.a(fileOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            fh.a(fileOutputStream);
            throw th;
        }
    }
}
