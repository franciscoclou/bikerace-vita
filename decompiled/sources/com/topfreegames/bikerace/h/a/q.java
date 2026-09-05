package com.topfreegames.bikerace.h.a;

import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.engine.data.DataNode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: UserLevelsData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q {
    private static q b = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1254a;
    private ExecutorService c = null;
    private DataNode d = null;

    public static void a(Context context) {
        if (b == null) {
            b = new q(context.getApplicationContext());
            b.d();
        }
    }

    public static q a() {
        if (b == null) {
            throw new IllegalStateException("Call init() first!");
        }
        return b;
    }

    private q(Context context) {
        this.f1254a = null;
        this.f1254a = context;
    }

    public static ExecutorService b() {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.topfreegames.bikerace.h.a.q.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setPriority(4);
                return thread;
            }
        });
    }

    private static String d(int i) {
        return String.format("Level_%s", Integer.valueOf(i));
    }

    private static String e(int i) {
        return String.format("UserLevel_%d.dat", Integer.valueOf(i));
    }

    public synchronized String a(int i) {
        String string;
        try {
            f(i);
            try {
                string = this.d.getChild(d(i)).getString("Id");
            } catch (NullPointerException e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
                string = null;
            }
        } catch (Throwable th) {
            throw th;
        }
        return string;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public synchronized c b(int i) {
        FileInputStream fileInputStreamOpenFileInput;
        IOException iOException;
        FileInputStream fileInputStream;
        c cVarA;
        f(i);
        try {
            fileInputStreamOpenFileInput = this.f1254a.openFileInput(e(i));
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[4096];
                while (true) {
                    int i2 = fileInputStreamOpenFileInput.read(bArr);
                    if (i2 < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                cVarA = m.a(byteArrayOutputStream.toByteArray());
                if (fileInputStreamOpenFileInput != null) {
                    try {
                        fileInputStreamOpenFileInput.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                fileInputStream = fileInputStreamOpenFileInput;
                iOException = e2;
                try {
                    if (ap.d()) {
                        iOException.printStackTrace();
                    }
                    ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(iOException);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            cVarA = null;
                        } catch (IOException e3) {
                            cVarA = null;
                        }
                    } else {
                        cVarA = null;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStreamOpenFileInput = fileInputStream;
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStreamOpenFileInput != null) {
                    fileInputStreamOpenFileInput.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            iOException = e5;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStreamOpenFileInput = null;
        }
        return cVarA;
    }

    public synchronized void c(int i) {
        f(i);
        try {
            int iC = c();
            this.f1254a.deleteFile(e(i));
            this.d.removeChild(d(i));
            f();
            for (int i2 = i + 1; i2 < iC; i2++) {
                File file = new File(this.f1254a.getFilesDir(), e(i2));
                if (file != null) {
                    file.renameTo(new File(this.f1254a.getFilesDir(), e(i2 - 1)));
                }
                this.d.changeChildKey(d(i2), d(i2 - 1));
            }
            g();
        } catch (Exception e) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:62:0x00a6  */
    public synchronized boolean a(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        IOException iOException;
        FileOutputStream fileOutputStream3;
        FileNotFoundException fileNotFoundException;
        boolean z = true;
        synchronized (this) {
            try {
                if (bArr == null) {
                    throw new IllegalArgumentException("Data cannot be null!");
                }
                if (!b(str)) {
                    int iC = c();
                    try {
                        try {
                            FileOutputStream fileOutputStreamOpenFileOutput = this.f1254a.openFileOutput(e(iC), 0);
                            try {
                                fileOutputStreamOpenFileOutput.write(bArr);
                                if (fileOutputStreamOpenFileOutput != null) {
                                    try {
                                        fileOutputStreamOpenFileOutput.close();
                                    } catch (IOException e) {
                                    }
                                }
                            } catch (FileNotFoundException e2) {
                                fileOutputStream3 = fileOutputStreamOpenFileOutput;
                                fileNotFoundException = e2;
                                if (ap.d()) {
                                    fileNotFoundException.printStackTrace();
                                }
                                ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(fileNotFoundException);
                                if (fileOutputStream3 != null) {
                                    try {
                                        fileOutputStream3.close();
                                        z = false;
                                    } catch (IOException e3) {
                                        z = false;
                                    }
                                } else {
                                    z = false;
                                }
                            } catch (IOException e4) {
                                fileOutputStream2 = fileOutputStreamOpenFileOutput;
                                iOException = e4;
                                if (ap.d()) {
                                    iOException.printStackTrace();
                                }
                                ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(iOException);
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                        z = false;
                                    } catch (IOException e5) {
                                        z = false;
                                    }
                                } else {
                                    z = false;
                                }
                            } catch (Throwable th) {
                                th = th;
                                fileOutputStream = fileOutputStreamOpenFileOutput;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (FileNotFoundException e7) {
                        fileOutputStream3 = null;
                        fileNotFoundException = e7;
                    } catch (IOException e8) {
                        fileOutputStream2 = null;
                        iOException = e8;
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                    }
                    if (z) {
                        this.d.addChild(a(iC, str));
                        e();
                        a(iC, 0);
                    }
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
        return z;
    }

    public synchronized void a(int i, int i2) {
        f(i);
        if (i2 < 0) {
            throw new IllegalArgumentException("NumStars cannot be null!");
        }
        try {
            DataNode child = this.d.getChild(d(i));
            if (i2 > child.getInteger("Stars").intValue()) {
                child.putInteger("Stars", Integer.valueOf(i2));
            }
            g();
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void e() {
        try {
            this.d.putInteger("NumLevels", Integer.valueOf(this.d.getInteger("NumLevels").intValue() + 1));
            g();
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void f() {
        try {
            int iIntValue = this.d.getInteger("NumLevels").intValue() - 1;
            if (iIntValue < 0) {
                iIntValue = 0;
            }
            this.d.putInteger("NumLevels", Integer.valueOf(iIntValue));
            g();
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int c() {
        int iIntValue;
        try {
            iIntValue = this.d.getInteger("NumLevels").intValue();
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            iIntValue = -1;
        }
        return iIntValue;
    }

    public synchronized int a(String str) {
        int i;
        int iC = c();
        i = 0;
        while (i < iC) {
            if (!str.equals(a(i))) {
                i++;
            }
        }
        i = -1;
        return i;
    }

    public synchronized boolean b(String str) {
        return a(str) >= 0;
    }

    public synchronized void d() {
        ClassNotFoundException classNotFoundException;
        IOException iOException;
        StreamCorruptedException streamCorruptedException;
        ObjectInputStream objectInputStream = null;
        try {
            try {
                try {
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(this.f1254a.openFileInput("BikeRaceUL.dat"));
                    try {
                        this.d = (DataNode) objectInputStream2.readObject();
                        if (objectInputStream2 != null) {
                            try {
                                objectInputStream2.close();
                            } catch (IOException e) {
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        objectInputStream = objectInputStream2;
                        i();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                    } catch (StreamCorruptedException e4) {
                        objectInputStream = objectInputStream2;
                        streamCorruptedException = e4;
                        if (ap.d()) {
                            streamCorruptedException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(streamCorruptedException);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                    } catch (IOException e6) {
                        objectInputStream = objectInputStream2;
                        iOException = e6;
                        if (ap.d()) {
                            iOException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(iOException);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                    } catch (ClassNotFoundException e8) {
                        objectInputStream = objectInputStream2;
                        classNotFoundException = e8;
                        if (ap.d()) {
                            classNotFoundException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(classNotFoundException);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e9) {
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        objectInputStream = objectInputStream2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e10) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e11) {
            } catch (StreamCorruptedException e12) {
                streamCorruptedException = e12;
            } catch (IOException e13) {
                iOException = e13;
            } catch (ClassNotFoundException e14) {
                classNotFoundException = e14;
            }
        } catch (Throwable th3) {
            throw th3;
        }
    }

    private synchronized void g() {
        if (this.c == null) {
            this.c = b();
        }
        try {
            this.c.execute(new Runnable() { // from class: com.topfreegames.bikerace.h.a.q.2
                @Override // java.lang.Runnable
                public void run() {
                    q.this.h();
                }
            });
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:139:0x029c  */
    /* JADX WARN: Code duplicated, block: B:143:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:14:0x0029 A[DONT_INVERT, PHI: r0
      0x0029: PHI (r0v77 boolean) = 
      (r0v36 boolean)
      (r0v37 boolean)
      (r0v55 boolean)
      (r0v56 boolean)
      (r0v57 boolean)
      (r0v75 boolean)
      (r0v76 boolean)
      (r0v91 boolean)
      (r0v97 boolean)
      (r0v91 boolean)
     binds: [B:90:0x01ae, B:78:0x0163, B:67:0x0137, B:55:0x00ec, B:139:0x029c, B:44:0x00c0, B:28:0x0073, B:12:0x0024, B:117:0x023d, B:13:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:150:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:154:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:158:0x01bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:163:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:? A[Catch: all -> 0x009a, SYNTHETIC, TRY_ENTER, TryCatch #22 {, blocks: (B:11:0x001d, B:13:0x0026, B:15:0x002b, B:119:0x025a, B:114:0x0234, B:116:0x023a, B:117:0x023d, B:109:0x020d, B:111:0x0214, B:112:0x0217, B:94:0x01b6, B:96:0x01bf, B:97:0x01c2, B:104:0x01e9, B:106:0x01ef, B:107:0x01f2, B:99:0x01c4, B:101:0x01cb, B:102:0x01ce, B:75:0x0157, B:77:0x0160, B:86:0x018c, B:88:0x0192, B:89:0x0195, B:81:0x0168, B:83:0x016e, B:84:0x0171, B:52:0x00e0, B:54:0x00e9, B:63:0x0115, B:65:0x011b, B:66:0x011e, B:58:0x00f1, B:60:0x00f7, B:61:0x00fa, B:25:0x0067, B:27:0x0070, B:40:0x009e, B:42:0x00a4, B:43:0x00a7, B:31:0x0077, B:33:0x007d, B:34:0x0080), top: B:162:0x0004, inners: #0, #1, #2, #9, #12, #16, #17, #18, #20, #21, #23 }] */
    public synchronized void h() {
        Throwable th;
        ObjectOutputStream objectOutputStream;
        NullPointerException nullPointerException;
        IOException iOException;
        FileNotFoundException fileNotFoundException;
        FileOutputStream fileOutputStreamOpenFileOutput;
        FileOutputStream fileOutputStream = null;
        synchronized (this) {
            boolean z = true;
            try {
                try {
                    if (this.d != null) {
                        fileOutputStreamOpenFileOutput = this.f1254a.openFileOutput("BikeRaceUL.temp", 0);
                        try {
                            objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                            try {
                                objectOutputStream.writeObject(this.d);
                            } catch (FileNotFoundException e) {
                                fileOutputStream = fileOutputStreamOpenFileOutput;
                                fileNotFoundException = e;
                                if (ap.d()) {
                                    fileNotFoundException.printStackTrace();
                                }
                                ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(fileNotFoundException);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.getFD().sync();
                                    } catch (IOException e2) {
                                        if (ap.d()) {
                                            e2.printStackTrace();
                                        }
                                        ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e2);
                                    }
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                            z = false;
                                        } catch (IOException e3) {
                                            if (ap.d()) {
                                                e3.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e3);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (IOException e4) {
                                fileOutputStream = fileOutputStreamOpenFileOutput;
                                iOException = e4;
                                if (ap.d()) {
                                    iOException.printStackTrace();
                                }
                                ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(iOException);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.getFD().sync();
                                    } catch (IOException e5) {
                                        if (ap.d()) {
                                            e5.printStackTrace();
                                        }
                                        ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e5);
                                    }
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                            z = false;
                                        } catch (IOException e6) {
                                            if (ap.d()) {
                                                e6.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e6);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (NullPointerException e7) {
                                fileOutputStream = fileOutputStreamOpenFileOutput;
                                nullPointerException = e7;
                                if (ap.d()) {
                                    nullPointerException.printStackTrace();
                                }
                                ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(nullPointerException);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.getFD().sync();
                                    } catch (IOException e8) {
                                        if (ap.d()) {
                                            e8.printStackTrace();
                                        }
                                        ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e8);
                                    }
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                            z = false;
                                        } catch (IOException e9) {
                                            if (ap.d()) {
                                                e9.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e9);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (Throwable th2) {
                                fileOutputStream = fileOutputStreamOpenFileOutput;
                                th = th2;
                                if (fileOutputStream == null) {
                                    if (objectOutputStream != null) {
                                        throw th;
                                    }
                                    objectOutputStream.close();
                                    throw th;
                                }
                                try {
                                    fileOutputStream.getFD().sync();
                                } catch (IOException e10) {
                                    if (ap.d()) {
                                        e10.printStackTrace();
                                    }
                                    ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e10);
                                }
                                if (objectOutputStream != null) {
                                    throw th;
                                }
                                try {
                                    objectOutputStream.close();
                                    throw th;
                                } catch (IOException e11) {
                                    if (ap.d()) {
                                        e11.printStackTrace();
                                    }
                                    ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e11);
                                    throw th;
                                }
                            }
                        } catch (FileNotFoundException e12) {
                            objectOutputStream = null;
                            fileOutputStream = fileOutputStreamOpenFileOutput;
                            fileNotFoundException = e12;
                        } catch (IOException e13) {
                            objectOutputStream = null;
                            fileOutputStream = fileOutputStreamOpenFileOutput;
                            iOException = e13;
                        } catch (NullPointerException e14) {
                            objectOutputStream = null;
                            fileOutputStream = fileOutputStreamOpenFileOutput;
                            nullPointerException = e14;
                        } catch (Throwable th3) {
                            objectOutputStream = null;
                            fileOutputStream = fileOutputStreamOpenFileOutput;
                            th = th3;
                        }
                    } else {
                        fileOutputStreamOpenFileOutput = null;
                        objectOutputStream = null;
                    }
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.getFD().sync();
                        } catch (IOException e15) {
                            if (ap.d()) {
                                e15.printStackTrace();
                            }
                            ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e15);
                            z = false;
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e16) {
                                if (ap.d()) {
                                    e16.printStackTrace();
                                }
                                ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().a(getClass().getName(), "saveData", e16);
                                z = false;
                            }
                            if (z) {
                                try {
                                    new File(this.f1254a.getFilesDir(), "BikeRaceUL.temp").renameTo(new File(this.f1254a.getFilesDir(), "BikeRaceUL.dat"));
                                } catch (NullPointerException e17) {
                                    ((BikeRaceApplication) this.f1254a.getApplicationContext()).d().b(e17);
                                }
                            }
                        } else if (z) {
                            new File(this.f1254a.getFilesDir(), "BikeRaceUL.temp").renameTo(new File(this.f1254a.getFilesDir(), "BikeRaceUL.dat"));
                        }
                    } else if (objectOutputStream != null) {
                        objectOutputStream.close();
                        if (z) {
                            new File(this.f1254a.getFilesDir(), "BikeRaceUL.temp").renameTo(new File(this.f1254a.getFilesDir(), "BikeRaceUL.dat"));
                        }
                    } else if (z) {
                        new File(this.f1254a.getFilesDir(), "BikeRaceUL.temp").renameTo(new File(this.f1254a.getFilesDir(), "BikeRaceUL.dat"));
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (FileNotFoundException e18) {
                fileNotFoundException = e18;
                objectOutputStream = null;
            } catch (IOException e19) {
                iOException = e19;
                objectOutputStream = null;
            } catch (NullPointerException e20) {
                nullPointerException = e20;
                objectOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                objectOutputStream = null;
            }
            throw th;
        }
    }

    private synchronized DataNode a(int i, String str) {
        DataNode dataNode;
        dataNode = new DataNode(d(i));
        dataNode.putString("Id", str);
        dataNode.putInteger("Stars", 0);
        return dataNode;
    }

    private synchronized void i() {
        this.d = new DataNode("BikeRaceULData");
        this.d.putInteger("NumLevels", 0);
        g();
    }

    private synchronized void f(int i) {
        if (i >= 0) {
            if (i < c()) {
            }
            throw new IllegalArgumentException("Invalid level index!");
        }
        throw new IllegalArgumentException("Invalid level index!");
        throw th;
    }
}
