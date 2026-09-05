package com.flurry.sdk;

import android.provider.Settings;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class eo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f541a = eo.class.getSimpleName();
    private static final Set<String> b = i();
    private static String c;

    public static synchronized String a() {
        if (TextUtils.isEmpty(c)) {
            c = g();
        }
        return c;
    }

    private static String g() {
        String strB = b();
        return !TextUtils.isEmpty(strB) ? strB : c();
    }

    static String b() {
        String string = Settings.Secure.getString(eg.a().b().getContentResolver(), "android_id");
        if (a(string)) {
            return "AND" + string;
        }
        return null;
    }

    static String c() throws Throwable {
        String strE = e();
        if (TextUtils.isEmpty(strE)) {
            strE = f();
            if (TextUtils.isEmpty(strE)) {
                strE = d();
            }
            b(strE);
        }
        return strE;
    }

    static boolean a(String str) {
        return (TextUtils.isEmpty(str) || c(str.toLowerCase(Locale.US))) ? false : true;
    }

    static String d() {
        return "ID" + Long.toString(Double.doubleToLongBits(Math.random()) + (37 * (System.nanoTime() + ((long) (el.c(eg.a().b()).hashCode() * 37)))), 16);
    }

    static void b(String str) throws Throwable {
        if (!TextUtils.isEmpty(str)) {
            File fileStreamPath = eg.a().b().getFileStreamPath(h());
            if (et.a(fileStreamPath)) {
                a(str, fileStreamPath);
            }
        }
    }

    static void a(String str, File file) throws Throwable {
        DataOutputStream dataOutputStream;
        try {
            try {
                dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                try {
                    a(str, dataOutputStream);
                    fh.a(dataOutputStream);
                } catch (Throwable th) {
                    th = th;
                    ex.a(6, f541a, "Error when saving phoneId", th);
                    fh.a(dataOutputStream);
                }
            } catch (Throwable th2) {
                th = th2;
                fh.a(dataOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    static String e() throws Throwable {
        DataInputStream dataInputStream;
        String strA = null;
        File fileStreamPath = eg.a().b().getFileStreamPath(h());
        if (fileStreamPath != null) {
            ?? Exists = fileStreamPath.exists();
            try {
                if (Exists != 0) {
                    try {
                        dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                        try {
                            strA = a(dataInputStream);
                            fh.a(dataInputStream);
                            Exists = dataInputStream;
                        } catch (Throwable th) {
                            th = th;
                            ex.a(6, f541a, "Error when loading phoneId", th);
                            fh.a(dataInputStream);
                            Exists = dataInputStream;
                        }
                    } catch (Throwable th2) {
                        Exists = 0;
                        th = th2;
                        fh.a((Closeable) Exists);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return strA;
    }

    static String f() throws Throwable {
        String[] list;
        DataInputStream dataInputStream;
        Throwable th;
        String strB = null;
        File filesDir = eg.a().b().getFilesDir();
        if (filesDir != null && (list = filesDir.list(new FilenameFilter() { // from class: com.flurry.sdk.eo.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith(".flurryagent.");
            }
        })) != null && list.length != 0) {
            File fileStreamPath = eg.a().b().getFileStreamPath(list[0]);
            if (fileStreamPath != null && fileStreamPath.exists()) {
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                    try {
                        try {
                            strB = b(dataInputStream);
                            fh.a(dataInputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            ex.a(6, f541a, "Error when loading phoneId", th);
                            fh.a(dataInputStream);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fh.a(dataInputStream);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    dataInputStream = null;
                }
            }
        }
        return strB;
    }

    private static void a(String str, DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(1);
        dataOutput.writeUTF(str);
    }

    private static String a(DataInput dataInput) {
        if (1 != dataInput.readInt()) {
            return null;
        }
        return dataInput.readUTF();
    }

    private static String b(DataInput dataInput) throws IOException {
        if (46586 != dataInput.readUnsignedShort() || 2 != dataInput.readUnsignedShort()) {
            return null;
        }
        dataInput.readUTF();
        return dataInput.readUTF();
    }

    private static String h() {
        return ".flurryb.";
    }

    private static boolean c(String str) {
        return b.contains(str);
    }

    private static Set<String> i() {
        HashSet hashSet = new HashSet();
        hashSet.add("null");
        hashSet.add("9774d56d682e549c");
        hashSet.add("dead00beef");
        return Collections.unmodifiableSet(hashSet);
    }
}
