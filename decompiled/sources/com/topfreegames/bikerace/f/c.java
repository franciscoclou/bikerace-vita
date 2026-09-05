package com.topfreegames.bikerace.f;

import android.content.Context;
import android.util.Log;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.engine.data.DataNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: GiftData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile DataNode f1225a = null;
    private Context b;
    private String c;

    c(Context context, String str) {
        this.b = null;
        this.c = null;
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        this.b = context.getApplicationContext();
        this.c = str;
    }

    static DataNode a(a aVar) {
        DataNode dataNode;
        Exception e;
        try {
            dataNode = new DataNode(aVar.a());
            try {
                dataNode.putString("Id", aVar.a());
                dataNode.putString("SenderName", aVar.b());
                dataNode.putString("SenderId", aVar.c());
                dataNode.putString("Track", aVar.h());
                dataNode.putInteger("Type", Integer.valueOf(aVar.g().ordinal()));
                if (aVar.d() != null) {
                    dataNode.putLong("DateUsed", Long.valueOf(aVar.d().getTime()));
                }
            } catch (Exception e2) {
                e = e2;
                Log.d(c.class.getSimpleName(), Log.getStackTraceString(e));
            }
        } catch (Exception e3) {
            dataNode = null;
            e = e3;
        }
        return dataNode;
    }

    static a a(DataNode dataNode) {
        try {
            String string = dataNode.getString("Id");
            String string2 = dataNode.getString("SenderName");
            String string3 = dataNode.getString("SenderId");
            String string4 = dataNode.getString("Track");
            b bVar = b.valuesCustom()[dataNode.getInteger("Type").intValue()];
            Long l = dataNode.getLong("DateUsed");
            return new a(string, string3, string2, bVar, string4, l != null ? new Date(l.longValue()) : null);
        } catch (Exception e) {
            Log.d(c.class.getSimpleName(), Log.getStackTraceString(e));
            return null;
        }
    }

    public List<a> a() {
        List<DataNode> listListAllChildren = i().listAllChildren();
        int size = listListAllChildren.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(a(listListAllChildren.get(i)));
        }
        return arrayList;
    }

    public List<a> b() {
        List<DataNode> listListAllChildren = i().listAllChildren();
        int size = listListAllChildren.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            a aVarA = a(listListAllChildren.get(i));
            if (aVarA.e()) {
                arrayList.add(aVarA);
            }
        }
        return arrayList;
    }

    public List<String> c() {
        List<DataNode> listListAllChildren = j().listAllChildren();
        int size = listListAllChildren.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(listListAllChildren.get(i).getKey());
        }
        return arrayList;
    }

    private boolean f(a aVar) {
        if (aVar != null) {
            List<a> listA = a();
            int size = listA.size();
            for (int i = 0; i < size; i++) {
                if (aVar.a().equals(listA.get(i).a())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean g(a aVar) {
        if (aVar == null) {
            return false;
        }
        List<String> listC = c();
        int size = listC.size();
        for (int i = 0; i < size; i++) {
            if (aVar.a().equals(listC.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void b(a aVar) {
        if (aVar != null) {
            DataNode dataNodeI = i();
            if (!f(aVar)) {
                dataNodeI.addChild(a(aVar));
                k();
            }
        }
    }

    public void c(a aVar) {
        if (aVar != null) {
            i().removeChild(aVar.a());
            k();
        }
    }

    public void d(a aVar) {
        if (aVar != null) {
            DataNode dataNodeI = i();
            if (f(aVar)) {
                dataNodeI.removeChild(aVar.a());
                dataNodeI.addChild(a(aVar));
                k();
            }
        }
    }

    public void e(a aVar) {
        if (aVar != null) {
            DataNode dataNodeJ = j();
            if (!g(aVar)) {
                dataNodeJ.addChild(new DataNode(aVar.a()));
                k();
            }
        }
    }

    public void a(String str) {
        if (str != null) {
            j().removeChild(str);
            k();
        }
    }

    private void e() {
        this.f1225a = new DataNode("BikeRaceData");
        this.f1225a.addChild(f());
        this.f1225a.addChild(g());
        k();
    }

    private static DataNode f() {
        return new DataNode("Gifts");
    }

    private static DataNode g() {
        return new DataNode("Delete");
    }

    private synchronized DataNode h() {
        if (this.f1225a == null) {
            d();
        }
        if (this.f1225a == null) {
            e();
        }
        return this.f1225a;
    }

    private synchronized DataNode i() {
        DataNode child;
        DataNode dataNodeH = h();
        child = dataNodeH.getChild("Gifts");
        if (child == null) {
            child = f();
            dataNodeH.addChild(child);
        }
        return child;
    }

    private synchronized DataNode j() {
        DataNode child;
        child = h().getChild("Delete");
        if (child == null) {
            child = g();
            this.f1225a.addChild(child);
        }
        return child;
    }

    public synchronized void d() {
        ClassNotFoundException classNotFoundException;
        IOException iOException;
        StreamCorruptedException streamCorruptedException;
        ObjectInputStream objectInputStream = null;
        try {
            try {
                try {
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(this.b.openFileInput(m()));
                    try {
                        this.f1225a = (DataNode) objectInputStream2.readObject();
                        if (objectInputStream2 != null) {
                            try {
                                objectInputStream2.close();
                            } catch (IOException e) {
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        objectInputStream = objectInputStream2;
                        e();
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
        } catch (Throwable th3) {
            throw th3;
        }
    }

    private synchronized void k() {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.f.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.l();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:139:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:141:0x0028 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:146:0x002d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:148:0x01c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:14:0x002b A[DONT_INVERT, PHI: r0
      0x002b: PHI (r0v77 boolean) = 
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
     binds: [B:90:0x01b4, B:78:0x0169, B:67:0x013d, B:55:0x00f2, B:139:0x02a2, B:44:0x00c6, B:28:0x0079, B:12:0x0026, B:117:0x0243, B:13:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:157:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:0x0166 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:163:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:? A[Catch: all -> 0x00a0, SYNTHETIC, TRY_ENTER, TryCatch #1 {, blocks: (B:11:0x001f, B:13:0x0028, B:15:0x002d, B:119:0x0260, B:114:0x023a, B:116:0x0240, B:117:0x0243, B:109:0x0213, B:111:0x021a, B:112:0x021d, B:94:0x01bc, B:96:0x01c5, B:97:0x01c8, B:104:0x01ef, B:106:0x01f5, B:107:0x01f8, B:99:0x01ca, B:101:0x01d1, B:102:0x01d4, B:75:0x015d, B:77:0x0166, B:86:0x0192, B:88:0x0198, B:89:0x019b, B:81:0x016e, B:83:0x0174, B:84:0x0177, B:52:0x00e6, B:54:0x00ef, B:63:0x011b, B:65:0x0121, B:66:0x0124, B:58:0x00f7, B:60:0x00fd, B:61:0x0100, B:25:0x006d, B:27:0x0076, B:40:0x00a4, B:42:0x00aa, B:43:0x00ad, B:31:0x007d, B:33:0x0083, B:34:0x0086), top: B:143:0x0004, inners: #0, #2, #3, #5, #7, #8, #15, #16, #19, #20, #24 }] */
    public synchronized void l() {
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
                    if (this.f1225a != null) {
                        fileOutputStreamOpenFileOutput = this.b.openFileOutput(n(), 0);
                        try {
                            objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                            try {
                                objectOutputStream.writeObject(this.f1225a);
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
                            ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e15);
                            z = false;
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e16) {
                                if (ap.d()) {
                                    e16.printStackTrace();
                                }
                                ((BikeRaceApplication) this.b.getApplicationContext()).d().a(getClass().getName(), "saveData", e16);
                                z = false;
                            }
                            if (z) {
                                try {
                                    new File(this.b.getFilesDir(), n()).renameTo(new File(this.b.getFilesDir(), m()));
                                } catch (NullPointerException e17) {
                                    ((BikeRaceApplication) this.b.getApplicationContext()).d().b(e17);
                                }
                            }
                        } else if (z) {
                            new File(this.b.getFilesDir(), n()).renameTo(new File(this.b.getFilesDir(), m()));
                        }
                    } else if (objectOutputStream != null) {
                        objectOutputStream.close();
                        if (z) {
                            new File(this.b.getFilesDir(), n()).renameTo(new File(this.b.getFilesDir(), m()));
                        }
                    } else if (z) {
                        new File(this.b.getFilesDir(), n()).renameTo(new File(this.b.getFilesDir(), m()));
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
                } catch (Throwable th4) {
                    th = th4;
                    objectOutputStream = null;
                }
            } catch (Throwable th5) {
                th = th5;
            }
            throw th;
        }
    }

    private String m() {
        return String.format("BikeRaceGt_%s.dat", this.c);
    }

    private String n() {
        return String.format("BikeRaceGt_%s.temp", this.c);
    }
}
