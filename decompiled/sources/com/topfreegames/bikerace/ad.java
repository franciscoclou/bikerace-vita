package com.topfreegames.bikerace;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.engine.data.DataNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: GameSinglePlayerBestRacesData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ad {
    private static ad c = null;
    private static boolean f = true;
    private Context b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile DataNode f1115a = null;
    private ExecutorService d = null;
    private ae e = new ae(this);

    public static ad a(Context context) {
        synchronized (ad.class) {
            if (c == null) {
                if (c == null) {
                    c = new ad(context);
                }
                if (f) {
                    if (!c.h()) {
                        c.f();
                        c.i();
                    }
                } else {
                    c.e();
                }
            }
        }
        return c;
    }

    public static ExecutorService a() {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.topfreegames.bikerace.ad.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setPriority(4);
                return thread;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(int i, int i2) {
        return String.format("%d_%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    private ad(Context context) {
        this.b = null;
        this.b = context;
    }

    public ae b() {
        return this.e;
    }

    public synchronized com.topfreegames.bikerace.multiplayer.ak a(int i, int i2) {
        DataNode child;
        ArrayList arrayList;
        try {
            child = this.f1115a.getChild(e(i, i2));
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "Null pointer at getTime");
            if (!f) {
                e();
            } else {
                g(i, i2);
            }
            child = this.f1115a != null ? this.f1115a.getChild(e(i, i2)) : null;
        }
        if (child == null && f) {
            g(i, i2);
            child = this.f1115a.getChild(e(i, i2));
        }
        try {
            arrayList = (ArrayList) child.getObject("SavedRace");
        } catch (NullPointerException e2) {
            arrayList = new ArrayList();
            this.f1115a.addChild(f(i, i2));
        }
        return new com.topfreegames.bikerace.multiplayer.ak(arrayList);
    }

    public synchronized float b(int i, int i2) {
        DataNode child;
        Float fValueOf;
        try {
            child = this.f1115a.getChild(e(i, i2));
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "Null pointer at getTime");
            if (!f) {
                e();
            } else {
                g(i, i2);
            }
            child = this.f1115a != null ? this.f1115a.getChild(e(i, i2)) : null;
        }
        if (child == null && f) {
            g(i, i2);
            child = this.f1115a.getChild(e(i, i2));
        }
        try {
            fValueOf = child.getFloat("Time");
        } catch (NullPointerException e2) {
            fValueOf = Float.valueOf(-1.0f);
            this.f1115a.addChild(f(i, i2));
        }
        return fValueOf.floatValue();
    }

    public synchronized c c(int i, int i2) {
        DataNode child;
        Integer numValueOf;
        try {
            child = this.f1115a.getChild(e(i, i2));
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "Null pointer at getTime");
            if (!f) {
                e();
            } else {
                g(i, i2);
            }
            child = this.f1115a != null ? this.f1115a.getChild(e(i, i2)) : null;
        }
        if (child == null && f) {
            g(i, i2);
            child = this.f1115a.getChild(e(i, i2));
        }
        try {
            numValueOf = child.getInteger("Bike");
        } catch (NullPointerException e2) {
            numValueOf = Integer.valueOf(c.REGULAR.ordinal());
            this.f1115a.addChild(f(i, i2));
        }
        return c.valuesCustom()[numValueOf.intValue()];
    }

    public synchronized void a(int i, int i2, com.topfreegames.bikerace.multiplayer.ak akVar, float f2, c cVar) {
        DataNode child;
        String strE = e(i, i2);
        try {
            child = this.f1115a.getChild(strE);
        } catch (NullPointerException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "Null pointer at saveRace");
            if (!f) {
                e();
            } else {
                g(i, i2);
            }
            if (this.f1115a == null) {
                child = null;
            } else {
                child = this.f1115a.getChild(strE);
            }
        }
        try {
            child.putFloat("Time", Float.valueOf(f2));
            child.putObject("SavedRace", akVar.c());
            child.putInteger("Bike", Integer.valueOf(cVar.ordinal()));
        } catch (NullPointerException e2) {
            DataNode dataNodeF = f(i, i2);
            dataNodeF.putFloat("Time", Float.valueOf(f2));
            dataNodeF.putObject("SavedRace", akVar.c());
            dataNodeF.putInteger("Bike", Integer.valueOf(cVar.ordinal()));
            this.f1115a.addChild(dataNodeF);
        }
        if (f) {
            a(strE);
        } else {
            g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:138:0x0292  */
    /* JADX WARN: Code duplicated, block: B:13:0x0023 A[DONT_INVERT, PHI: r0
      0x0023: PHI (r0v85 boolean) = 
      (r0v36 boolean)
      (r0v37 boolean)
      (r0v55 boolean)
      (r0v56 boolean)
      (r0v57 boolean)
      (r0v75 boolean)
      (r0v76 boolean)
      (r0v99 boolean)
      (r0v105 boolean)
      (r0v99 boolean)
     binds: [B:89:0x01a4, B:77:0x0159, B:66:0x012d, B:54:0x00e2, B:138:0x0292, B:43:0x00b6, B:27:0x0069, B:11:0x001e, B:116:0x0233, B:12:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:145:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:147:0x0025 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:153:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x0020 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:0x01b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:? A[Catch: all -> 0x0090, SYNTHETIC, TRY_ENTER, TryCatch #18 {, blocks: (B:10:0x0017, B:12:0x0020, B:14:0x0025, B:118:0x0250, B:113:0x022a, B:115:0x0230, B:116:0x0233, B:108:0x0203, B:110:0x020a, B:111:0x020d, B:93:0x01ac, B:95:0x01b5, B:96:0x01b8, B:103:0x01df, B:105:0x01e5, B:106:0x01e8, B:98:0x01ba, B:100:0x01c1, B:101:0x01c4, B:74:0x014d, B:76:0x0156, B:85:0x0182, B:87:0x0188, B:88:0x018b, B:80:0x015e, B:82:0x0164, B:83:0x0167, B:51:0x00d6, B:53:0x00df, B:62:0x010b, B:64:0x0111, B:65:0x0114, B:57:0x00e7, B:59:0x00ed, B:60:0x00f0, B:24:0x005d, B:26:0x0066, B:39:0x0094, B:41:0x009a, B:42:0x009d, B:30:0x006d, B:32:0x0073, B:33:0x0076), top: B:142:0x0004, inners: #0, #6, #7, #8, #9, #10, #16, #17, #19, #22, #23 }] */
    public synchronized void a(DataNode dataNode, String str, String str2) {
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
                if (dataNode != null) {
                    try {
                        fileOutputStreamOpenFileOutput = this.b.openFileOutput(str2, 0);
                        try {
                            objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                            try {
                                objectOutputStream.writeObject(dataNode);
                            } catch (FileNotFoundException e) {
                                fileOutputStream = fileOutputStreamOpenFileOutput;
                                fileNotFoundException = e;
                                if (ap.d()) {
                                    fileNotFoundException.printStackTrace();
                                }
                                ((BikeRaceApplication) this.b.getApplicationContext()).d().b(fileNotFoundException);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.getFD().sync();
                                    } catch (IOException e2) {
                                        if (ap.d()) {
                                            e2.printStackTrace();
                                        }
                                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e2);
                                    }
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                            z = false;
                                        } catch (IOException e3) {
                                            if (ap.d()) {
                                                e3.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e3);
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
                                ((BikeRaceApplication) this.b.getApplicationContext()).d().b(iOException);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.getFD().sync();
                                    } catch (IOException e5) {
                                        if (ap.d()) {
                                            e5.printStackTrace();
                                        }
                                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e5);
                                    }
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                            z = false;
                                        } catch (IOException e6) {
                                            if (ap.d()) {
                                                e6.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e6);
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
                                ((BikeRaceApplication) this.b.getApplicationContext()).d().b(nullPointerException);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.getFD().sync();
                                    } catch (IOException e8) {
                                        if (ap.d()) {
                                            e8.printStackTrace();
                                        }
                                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e8);
                                    }
                                    if (objectOutputStream != null) {
                                        try {
                                            objectOutputStream.close();
                                            z = false;
                                        } catch (IOException e9) {
                                            if (ap.d()) {
                                                e9.printStackTrace();
                                            }
                                            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e9);
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
                                    ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e10);
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
                                    ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e11);
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
                    } catch (FileNotFoundException e15) {
                        fileNotFoundException = e15;
                        objectOutputStream = null;
                    } catch (IOException e16) {
                        iOException = e16;
                        objectOutputStream = null;
                    } catch (NullPointerException e17) {
                        nullPointerException = e17;
                        objectOutputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        objectOutputStream = null;
                    }
                } else {
                    fileOutputStreamOpenFileOutput = null;
                    objectOutputStream = null;
                }
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.getFD().sync();
                    } catch (IOException e18) {
                        if (ap.d()) {
                            e18.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e18);
                        z = false;
                    }
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e19) {
                            if (ap.d()) {
                                e19.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e19);
                            z = false;
                        }
                        if (z) {
                            try {
                                new File(this.b.getFilesDir(), str2).renameTo(new File(this.b.getFilesDir(), str));
                            } catch (NullPointerException e20) {
                                ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e20);
                            }
                        }
                    } else if (z) {
                        new File(this.b.getFilesDir(), str2).renameTo(new File(this.b.getFilesDir(), str));
                    }
                } else if (objectOutputStream != null) {
                    objectOutputStream.close();
                    if (z) {
                        new File(this.b.getFilesDir(), str2).renameTo(new File(this.b.getFilesDir(), str));
                    }
                } else if (z) {
                    new File(this.b.getFilesDir(), str2).renameTo(new File(this.b.getFilesDir(), str));
                }
            } catch (Throwable th5) {
                th = th5;
            }
            throw th;
        }
    }

    private synchronized void d() {
        this.f1115a = new DataNode("BikeRaceSPBRData");
        for (int i : com.topfreegames.bikerace.h.y.b) {
            int i2 = i - 1;
            for (int i3 = 0; i3 < 8; i3++) {
                this.f1115a.addChild(f(i2, i3));
                if (f) {
                    a(e(i2, i3));
                }
            }
        }
        if (!f) {
            g();
        }
    }

    private synchronized DataNode f(int i, int i2) {
        DataNode dataNode;
        dataNode = new DataNode(e(i, i2));
        dataNode.putFloat("Time", Float.valueOf(-1.0f));
        dataNode.putObject("SavedRace", new ArrayList());
        dataNode.putInteger("Bike", Integer.valueOf(c.REGULAR.ordinal()));
        return dataNode;
    }

    private synchronized void e() {
        ClassNotFoundException classNotFoundException;
        IOException iOException;
        StreamCorruptedException streamCorruptedException;
        ObjectInputStream objectInputStream = null;
        synchronized (this) {
            this.f1115a = null;
            try {
                try {
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(this.b.openFileInput("BikeRaceSPBR.dat"));
                    try {
                        this.f1115a = (DataNode) objectInputStream2.readObject();
                        if (objectInputStream2 != null) {
                            try {
                                objectInputStream2.close();
                            } catch (IOException e) {
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        objectInputStream = objectInputStream2;
                        d();
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
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(streamCorruptedException);
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
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(iOException);
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
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(classNotFoundException);
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
            if (this.f1115a == null) {
                d();
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private synchronized void g(int i, int i2) {
        ClassNotFoundException classNotFoundException;
        IOException iOException;
        StreamCorruptedException streamCorruptedException;
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        dataNodeF = null;
        ObjectInputStream objectInputStream3 = null;
        DataNode dataNodeF = null;
        ObjectInputStream objectInputStream4 = null;
        ObjectInputStream objectInputStream5 = null;
        synchronized (this) {
            if (this.f1115a == null) {
                this.f1115a = new DataNode("BikeRaceSPBRData");
            }
            try {
                try {
                    String strE = e(i, i2);
                    objectInputStream = new ObjectInputStream(this.b.openFileInput(String.format("BikeRaceSPBR_%s.dat", strE)));
                    try {
                        try {
                            try {
                                DataNode dataNode = (DataNode) objectInputStream.readObject();
                                try {
                                    this.f1115a.addChild(dataNode);
                                    if (!strE.equals(dataNode.getKey())) {
                                        this.f1115a.changeChildKey(dataNode.getKey(), strE);
                                    }
                                    if (objectInputStream != null) {
                                        try {
                                            objectInputStream.close();
                                        } catch (IOException e) {
                                        }
                                    }
                                } catch (FileNotFoundException e2) {
                                    dataNodeF = dataNode;
                                    if (dataNodeF == null) {
                                        dataNodeF = f(i, i2);
                                    }
                                    this.f1115a.addChild(dataNodeF);
                                    if (objectInputStream != null) {
                                        try {
                                            objectInputStream.close();
                                        } catch (IOException e3) {
                                        }
                                    }
                                }
                            } catch (FileNotFoundException e4) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            objectInputStream3 = objectInputStream;
                            if (objectInputStream3 != null) {
                                try {
                                    objectInputStream3.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    } catch (StreamCorruptedException e6) {
                        objectInputStream4 = objectInputStream;
                        streamCorruptedException = e6;
                        if (ap.d()) {
                            streamCorruptedException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(streamCorruptedException);
                        if (objectInputStream4 != null) {
                            try {
                                objectInputStream4.close();
                            } catch (IOException e7) {
                            }
                        }
                    } catch (IOException e8) {
                        objectInputStream5 = objectInputStream;
                        iOException = e8;
                        if (ap.d()) {
                            iOException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(iOException);
                        if (objectInputStream5 != null) {
                            try {
                                objectInputStream5.close();
                            } catch (IOException e9) {
                            }
                        }
                    } catch (ClassNotFoundException e10) {
                        objectInputStream2 = objectInputStream;
                        classNotFoundException = e10;
                        if (ap.d()) {
                            classNotFoundException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(classNotFoundException);
                        if (objectInputStream2 != null) {
                            try {
                                objectInputStream2.close();
                            } catch (IOException e11) {
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e12) {
                objectInputStream = null;
            } catch (StreamCorruptedException e13) {
                streamCorruptedException = e13;
            } catch (IOException e14) {
                iOException = e14;
            } catch (ClassNotFoundException e15) {
                classNotFoundException = e15;
            }
        }
    }

    private synchronized void f() {
        for (int i : com.topfreegames.bikerace.h.y.b) {
            int i2 = i - 1;
            for (int i3 = 0; i3 < 8; i3++) {
                g(i2, i3);
            }
        }
    }

    private synchronized void g() {
        Thread thread = new Thread(new Runnable() { // from class: com.topfreegames.bikerace.ad.2
            @Override // java.lang.Runnable
            public void run() {
                ad.this.a(ad.this.f1115a, "BikeRaceSPBR.dat", "BikeRaceSPBR.temp");
            }
        });
        thread.setPriority(4);
        thread.start();
    }

    private synchronized void a(final String str) {
        if (this.d == null) {
            this.d = a();
        }
        try {
            this.d.execute(new Runnable() { // from class: com.topfreegames.bikerace.ad.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ad.this.a(ad.this.f1115a.getChild(str), String.format("BikeRaceSPBR_%s.dat", str), String.format("BikeRaceSPBR_%s.temp", str));
                    } catch (Exception e) {
                        ((BikeRaceApplication) ad.this.b.getApplicationContext()).d().b(e);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("ghost", Log.getStackTraceString(e));
        }
    }

    /* JADX WARN: Code duplicated, block: B:150:0x0233  */
    private synchronized boolean h() {
        ClassNotFoundException classNotFoundException;
        IOException iOException;
        StreamCorruptedException streamCorruptedException;
        boolean z;
        ObjectInputStream objectInputStream = null;
        synchronized (this) {
            this.f1115a = null;
            try {
                try {
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(this.b.openFileInput("BikeRaceSPBR.dat"));
                    try {
                        this.f1115a = (DataNode) objectInputStream2.readObject();
                        if (objectInputStream2 != null) {
                            try {
                                objectInputStream2.close();
                            } catch (IOException e) {
                            }
                        }
                        if (this.f1115a != null) {
                            for (int i : com.topfreegames.bikerace.h.y.d) {
                                for (int i2 = 0; i2 < 8; i2++) {
                                    a(e(i, i2));
                                }
                            }
                            try {
                                new File(this.b.getFilesDir(), "BikeRaceSPBR.dat").renameTo(new File(this.b.getFilesDir(), "BikeRaceSPBR_mig.dat"));
                            } catch (Exception e2) {
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (FileNotFoundException e3) {
                        objectInputStream = objectInputStream2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        if (this.f1115a != null) {
                            for (int i3 : com.topfreegames.bikerace.h.y.d) {
                                for (int i4 = 0; i4 < 8; i4++) {
                                    a(e(i3, i4));
                                }
                            }
                            try {
                                new File(this.b.getFilesDir(), "BikeRaceSPBR.dat").renameTo(new File(this.b.getFilesDir(), "BikeRaceSPBR_mig.dat"));
                            } catch (Exception e5) {
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (StreamCorruptedException e6) {
                        objectInputStream = objectInputStream2;
                        streamCorruptedException = e6;
                        if (ap.d()) {
                            streamCorruptedException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(streamCorruptedException);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (this.f1115a != null) {
                            for (int i5 : com.topfreegames.bikerace.h.y.d) {
                                for (int i6 = 0; i6 < 8; i6++) {
                                    a(e(i5, i6));
                                }
                            }
                            try {
                                new File(this.b.getFilesDir(), "BikeRaceSPBR.dat").renameTo(new File(this.b.getFilesDir(), "BikeRaceSPBR_mig.dat"));
                            } catch (Exception e8) {
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (IOException e9) {
                        objectInputStream = objectInputStream2;
                        iOException = e9;
                        if (ap.d()) {
                            iOException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(iOException);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e10) {
                            }
                        }
                        if (this.f1115a != null) {
                            for (int i7 : com.topfreegames.bikerace.h.y.d) {
                                for (int i8 = 0; i8 < 8; i8++) {
                                    a(e(i7, i8));
                                }
                            }
                            try {
                                new File(this.b.getFilesDir(), "BikeRaceSPBR.dat").renameTo(new File(this.b.getFilesDir(), "BikeRaceSPBR_mig.dat"));
                            } catch (Exception e11) {
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (ClassNotFoundException e12) {
                        objectInputStream = objectInputStream2;
                        classNotFoundException = e12;
                        if (ap.d()) {
                            classNotFoundException.printStackTrace();
                        }
                        ((BikeRaceApplication) this.b.getApplicationContext()).d().a(classNotFoundException);
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e13) {
                            }
                        }
                        if (this.f1115a != null) {
                            for (int i9 : com.topfreegames.bikerace.h.y.d) {
                                for (int i10 = 0; i10 < 8; i10++) {
                                    a(e(i9, i10));
                                }
                            }
                            try {
                                new File(this.b.getFilesDir(), "BikeRaceSPBR.dat").renameTo(new File(this.b.getFilesDir(), "BikeRaceSPBR_mig.dat"));
                            } catch (Exception e14) {
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (Throwable th) {
                        th = th;
                        objectInputStream = objectInputStream2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e15) {
                            }
                        }
                        if (this.f1115a == null) {
                            throw th;
                        }
                        for (int i11 : com.topfreegames.bikerace.h.y.d) {
                            for (int i12 = 0; i12 < 8; i12++) {
                                a(e(i11, i12));
                            }
                        }
                        try {
                            new File(this.b.getFilesDir(), "BikeRaceSPBR.dat").renameTo(new File(this.b.getFilesDir(), "BikeRaceSPBR_mig.dat"));
                            throw th;
                        } catch (Exception e16) {
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e17) {
            } catch (StreamCorruptedException e18) {
                streamCorruptedException = e18;
            } catch (IOException e19) {
                iOException = e19;
            } catch (ClassNotFoundException e20) {
                classNotFoundException = e20;
            }
        }
        return z;
    }

    public void c() {
        if (this.d != null) {
            this.d.shutdown();
            this.d = null;
        }
    }

    private void i() throws Throwable {
        Throwable th;
        ObjectInputStream objectInputStream;
        ClassNotFoundException classNotFoundException;
        ObjectInputStream objectInputStream2;
        IOException iOException;
        ObjectInputStream objectInputStream3;
        StreamCorruptedException streamCorruptedException;
        ObjectInputStream objectInputStream4;
        ObjectInputStream objectInputStream5;
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("com.topfreegames.bikerace.spbr", 0);
        if (sharedPreferences.getBoolean("RestoredOnce", false)) {
            return;
        }
        DataNode dataNode = null;
        try {
            try {
                objectInputStream5 = new ObjectInputStream(this.b.openFileInput("BikeRaceSPBR_mig.dat"));
                try {
                    DataNode dataNode2 = (DataNode) objectInputStream5.readObject();
                    if (objectInputStream5 != null) {
                        try {
                            objectInputStream5.close();
                        } catch (IOException e) {
                        }
                    }
                    if (dataNode2 != null) {
                        try {
                            if (this.f1115a != null) {
                                for (int i : com.topfreegames.bikerace.h.y.d) {
                                    for (int i2 = 0; i2 < com.topfreegames.bikerace.h.y.b(i); i2++) {
                                        String strE = e(i, i2);
                                        DataNode child = dataNode2.getChild(strE);
                                        DataNode child2 = this.f1115a.getChild(strE);
                                        if (child != null && (child2 == null || (child2.getFloat("Time").floatValue() < 0.0f && child.getFloat("Time").floatValue() >= 0.0f))) {
                                            this.f1115a.addChild(child);
                                            a(strE);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            if (ap.d()) {
                                e2.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e2);
                            return;
                        }
                    }
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putBoolean("RestoredOnce", true);
                    editorEdit.commit();
                } catch (FileNotFoundException e3) {
                    if (objectInputStream5 != null) {
                        try {
                            objectInputStream5.close();
                        } catch (IOException e4) {
                        }
                    }
                    if (0 != 0) {
                        try {
                            if (this.f1115a != null) {
                                for (int i3 : com.topfreegames.bikerace.h.y.d) {
                                    for (int i4 = 0; i4 < com.topfreegames.bikerace.h.y.b(i3); i4++) {
                                        String strE2 = e(i3, i4);
                                        DataNode child3 = dataNode.getChild(strE2);
                                        DataNode child4 = this.f1115a.getChild(strE2);
                                        if (child3 != null && (child4 == null || (child4.getFloat("Time").floatValue() < 0.0f && child3.getFloat("Time").floatValue() >= 0.0f))) {
                                            this.f1115a.addChild(child3);
                                            a(strE2);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e5) {
                            if (ap.d()) {
                                e5.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e5);
                            return;
                        }
                    }
                    SharedPreferences.Editor editorEdit2 = sharedPreferences.edit();
                    editorEdit2.putBoolean("RestoredOnce", true);
                    editorEdit2.commit();
                } catch (StreamCorruptedException e6) {
                    objectInputStream4 = objectInputStream5;
                    streamCorruptedException = e6;
                    if (ap.d()) {
                        streamCorruptedException.printStackTrace();
                    }
                    ((BikeRaceApplication) this.b.getApplicationContext()).d().a(streamCorruptedException);
                    if (objectInputStream4 != null) {
                        try {
                            objectInputStream4.close();
                        } catch (IOException e7) {
                        }
                    }
                    if (0 != 0) {
                        try {
                            if (this.f1115a != null) {
                                for (int i5 : com.topfreegames.bikerace.h.y.d) {
                                    for (int i6 = 0; i6 < com.topfreegames.bikerace.h.y.b(i5); i6++) {
                                        String strE3 = e(i5, i6);
                                        DataNode child5 = dataNode.getChild(strE3);
                                        DataNode child6 = this.f1115a.getChild(strE3);
                                        if (child5 != null && (child6 == null || (child6.getFloat("Time").floatValue() < 0.0f && child5.getFloat("Time").floatValue() >= 0.0f))) {
                                            this.f1115a.addChild(child5);
                                            a(strE3);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e8) {
                            if (ap.d()) {
                                e8.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e8);
                            return;
                        }
                    }
                    SharedPreferences.Editor editorEdit3 = sharedPreferences.edit();
                    editorEdit3.putBoolean("RestoredOnce", true);
                    editorEdit3.commit();
                } catch (IOException e9) {
                    objectInputStream3 = objectInputStream5;
                    iOException = e9;
                    if (ap.d()) {
                        iOException.printStackTrace();
                    }
                    ((BikeRaceApplication) this.b.getApplicationContext()).d().a(iOException);
                    if (objectInputStream3 != null) {
                        try {
                            objectInputStream3.close();
                        } catch (IOException e10) {
                        }
                    }
                    if (0 != 0) {
                        try {
                            if (this.f1115a != null) {
                                for (int i7 : com.topfreegames.bikerace.h.y.d) {
                                    for (int i8 = 0; i8 < com.topfreegames.bikerace.h.y.b(i7); i8++) {
                                        String strE4 = e(i7, i8);
                                        DataNode child7 = dataNode.getChild(strE4);
                                        DataNode child8 = this.f1115a.getChild(strE4);
                                        if (child7 != null && (child8 == null || (child8.getFloat("Time").floatValue() < 0.0f && child7.getFloat("Time").floatValue() >= 0.0f))) {
                                            this.f1115a.addChild(child7);
                                            a(strE4);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e11) {
                            if (ap.d()) {
                                e11.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e11);
                            return;
                        }
                    }
                    SharedPreferences.Editor editorEdit4 = sharedPreferences.edit();
                    editorEdit4.putBoolean("RestoredOnce", true);
                    editorEdit4.commit();
                } catch (ClassNotFoundException e12) {
                    objectInputStream2 = objectInputStream5;
                    classNotFoundException = e12;
                    if (ap.d()) {
                        classNotFoundException.printStackTrace();
                    }
                    ((BikeRaceApplication) this.b.getApplicationContext()).d().a(classNotFoundException);
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException e13) {
                        }
                    }
                    if (0 != 0) {
                        try {
                            if (this.f1115a != null) {
                                for (int i9 : com.topfreegames.bikerace.h.y.d) {
                                    for (int i10 = 0; i10 < com.topfreegames.bikerace.h.y.b(i9); i10++) {
                                        String strE5 = e(i9, i10);
                                        DataNode child9 = dataNode.getChild(strE5);
                                        DataNode child10 = this.f1115a.getChild(strE5);
                                        if (child9 != null && (child10 == null || (child10.getFloat("Time").floatValue() < 0.0f && child9.getFloat("Time").floatValue() >= 0.0f))) {
                                            this.f1115a.addChild(child9);
                                            a(strE5);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e14) {
                            if (ap.d()) {
                                e14.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e14);
                            return;
                        }
                    }
                    SharedPreferences.Editor editorEdit5 = sharedPreferences.edit();
                    editorEdit5.putBoolean("RestoredOnce", true);
                    editorEdit5.commit();
                } catch (Throwable th2) {
                    objectInputStream = objectInputStream5;
                    th = th2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e15) {
                        }
                    }
                    if (0 != 0) {
                        try {
                            if (this.f1115a != null) {
                                for (int i11 : com.topfreegames.bikerace.h.y.d) {
                                    for (int i12 = 0; i12 < com.topfreegames.bikerace.h.y.b(i11); i12++) {
                                        String strE6 = e(i11, i12);
                                        DataNode child11 = dataNode.getChild(strE6);
                                        DataNode child12 = this.f1115a.getChild(strE6);
                                        if (child11 != null && (child12 == null || (child12.getFloat("Time").floatValue() < 0.0f && child11.getFloat("Time").floatValue() >= 0.0f))) {
                                            this.f1115a.addChild(child11);
                                            a(strE6);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e16) {
                            if (ap.d()) {
                                e16.printStackTrace();
                            }
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e16);
                            throw th;
                        }
                    }
                    SharedPreferences.Editor editorEdit6 = sharedPreferences.edit();
                    editorEdit6.putBoolean("RestoredOnce", true);
                    editorEdit6.commit();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (FileNotFoundException e17) {
            objectInputStream5 = null;
        } catch (StreamCorruptedException e18) {
            streamCorruptedException = e18;
            objectInputStream4 = null;
        } catch (IOException e19) {
            iOException = e19;
            objectInputStream3 = null;
        } catch (ClassNotFoundException e20) {
            classNotFoundException = e20;
            objectInputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            objectInputStream = null;
        }
    }
}
