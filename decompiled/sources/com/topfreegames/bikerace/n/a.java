package com.topfreegames.bikerace.n;

import com.facebook.internal.NativeProtocol;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.aq;
import com.topfreegames.bikerace.t;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ABTestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f1341a = aq.a();
    private static a e = null;
    private b b = new b(null);
    private BikeRaceApplication c;
    private t d;

    public static void a(BikeRaceApplication bikeRaceApplication, c cVar) {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new a(bikeRaceApplication);
                }
                e.a(cVar);
            }
        }
    }

    public static a a() {
        if (e == null) {
            throw new IllegalStateException("Call init() first!");
        }
        return e;
    }

    private a(BikeRaceApplication bikeRaceApplication) {
        this.c = null;
        this.d = null;
        if (bikeRaceApplication == null) {
            throw new IllegalArgumentException("Application cannot be null!");
        }
        this.c = bikeRaceApplication;
        this.d = bikeRaceApplication.d();
    }

    public synchronized int a(String str) {
        Integer num;
        num = (Integer) this.b.f1342a.get(str);
        if (num == null) {
            num = 0;
        }
        return num.intValue();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002f A[DONT_INVERT, PHI: r0
      0x002f: PHI (r0v34 boolean) = 
      (r0v13 boolean)
      (r0v14 boolean)
      (r0v19 boolean)
      (r0v20 boolean)
      (r0v25 boolean)
      (r0v26 boolean)
      (r0v27 boolean)
      (r0v32 boolean)
      (r0v33 boolean)
      (r0v39 boolean)
      (r0v41 boolean)
      (r0v39 boolean)
     binds: [B:109:0x01db, B:98:0x019d, B:87:0x0178, B:76:0x013a, B:65:0x0115, B:54:0x00d7, B:160:0x029a, B:43:0x00b2, B:28:0x0072, B:12:0x002a, B:136:0x0255, B:13:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:160:0x029a  */
    /* JADX WARN: Code duplicated, block: B:162:0x00d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:173:0x019a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x01eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x002c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:0x0031 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:188:0x0137 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private synchronized void b() {
        FileOutputStream fileOutputStreamOpenFileOutput;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        synchronized (this) {
            boolean z = true;
            try {
                try {
                    if (this.b != null) {
                        fileOutputStreamOpenFileOutput = this.c.openFileOutput("BikeRaceAb.dat", 0);
                        try {
                            objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                            try {
                                this.b.c = false;
                                objectOutputStream.writeObject(this.b);
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                objectOutputStream2 = objectOutputStream;
                                if (ap.d()) {
                                    e.printStackTrace();
                                }
                                this.c.d().b(e);
                                if (fileOutputStreamOpenFileOutput != null) {
                                    try {
                                        fileOutputStreamOpenFileOutput.getFD().sync();
                                    } catch (IOException e3) {
                                        if (ap.d()) {
                                            e3.printStackTrace();
                                        }
                                        this.c.d().a(getClass().getName(), "saveData", e3);
                                    }
                                    if (objectOutputStream2 != null) {
                                        try {
                                            objectOutputStream2.close();
                                            z = false;
                                        } catch (IOException e4) {
                                            if (ap.d()) {
                                                e4.printStackTrace();
                                            }
                                            this.c.d().a(getClass().getName(), "saveData", e4);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream2 != null) {
                                    objectOutputStream2.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (IOException e5) {
                                e = e5;
                                objectOutputStream2 = objectOutputStream;
                                if (ap.d()) {
                                    e.printStackTrace();
                                }
                                this.c.d().b(e);
                                if (fileOutputStreamOpenFileOutput != null) {
                                    try {
                                        fileOutputStreamOpenFileOutput.getFD().sync();
                                    } catch (IOException e6) {
                                        if (ap.d()) {
                                            e6.printStackTrace();
                                        }
                                        this.c.d().a(getClass().getName(), "saveData", e6);
                                    }
                                    if (objectOutputStream2 != null) {
                                        try {
                                            objectOutputStream2.close();
                                            z = false;
                                        } catch (IOException e7) {
                                            if (ap.d()) {
                                                e7.printStackTrace();
                                            }
                                            this.c.d().a(getClass().getName(), "saveData", e7);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream2 != null) {
                                    objectOutputStream2.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (NullPointerException e8) {
                                e = e8;
                                objectOutputStream2 = objectOutputStream;
                                if (ap.d()) {
                                    e.printStackTrace();
                                }
                                this.c.d().b(e);
                                if (fileOutputStreamOpenFileOutput != null) {
                                    try {
                                        fileOutputStreamOpenFileOutput.getFD().sync();
                                    } catch (IOException e9) {
                                        if (ap.d()) {
                                            e9.printStackTrace();
                                        }
                                        this.c.d().a(getClass().getName(), "saveData", e9);
                                    }
                                    if (objectOutputStream2 != null) {
                                        try {
                                            objectOutputStream2.close();
                                            z = false;
                                        } catch (IOException e10) {
                                            if (ap.d()) {
                                                e10.printStackTrace();
                                            }
                                            this.c.d().a(getClass().getName(), "saveData", e10);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream2 != null) {
                                    objectOutputStream2.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (Exception e11) {
                                e = e11;
                                objectOutputStream2 = objectOutputStream;
                                if (ap.d()) {
                                    e.printStackTrace();
                                }
                                this.c.d().b(e);
                                if (fileOutputStreamOpenFileOutput != null) {
                                    try {
                                        fileOutputStreamOpenFileOutput.getFD().sync();
                                    } catch (IOException e12) {
                                        if (ap.d()) {
                                            e12.printStackTrace();
                                        }
                                        this.c.d().a(getClass().getName(), "saveData", e12);
                                    }
                                    if (objectOutputStream2 != null) {
                                        try {
                                            objectOutputStream2.close();
                                            z = false;
                                        } catch (IOException e13) {
                                            if (ap.d()) {
                                                e13.printStackTrace();
                                            }
                                            this.c.d().a(getClass().getName(), "saveData", e13);
                                            z = false;
                                        }
                                    } else {
                                        z = false;
                                    }
                                } else if (objectOutputStream2 != null) {
                                    objectOutputStream2.close();
                                    z = false;
                                } else {
                                    z = false;
                                }
                                throw th;
                            } catch (Throwable th) {
                                th = th;
                                objectOutputStream2 = objectOutputStream;
                                if (fileOutputStreamOpenFileOutput == null) {
                                    if (objectOutputStream2 != null) {
                                        objectOutputStream2.close();
                                    }
                                    throw th;
                                }
                                try {
                                    fileOutputStreamOpenFileOutput.getFD().sync();
                                } catch (IOException e14) {
                                    if (ap.d()) {
                                        e14.printStackTrace();
                                    }
                                    this.c.d().a(getClass().getName(), "saveData", e14);
                                }
                                if (objectOutputStream2 != null) {
                                    try {
                                        objectOutputStream2.close();
                                    } catch (IOException e15) {
                                        if (ap.d()) {
                                            e15.printStackTrace();
                                        }
                                        this.c.d().a(getClass().getName(), "saveData", e15);
                                    }
                                }
                                throw th;
                            }
                        } catch (FileNotFoundException e16) {
                            e = e16;
                        } catch (IOException e17) {
                            e = e17;
                        } catch (NullPointerException e18) {
                            e = e18;
                        } catch (Exception e19) {
                            e = e19;
                        }
                    } else {
                        fileOutputStreamOpenFileOutput = null;
                        objectOutputStream = null;
                    }
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.getFD().sync();
                        } catch (IOException e20) {
                            if (ap.d()) {
                                e20.printStackTrace();
                            }
                            this.c.d().a(getClass().getName(), "saveData", e20);
                            z = false;
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e21) {
                                if (ap.d()) {
                                    e21.printStackTrace();
                                }
                                this.c.d().a(getClass().getName(), "saveData", e21);
                                z = false;
                            }
                            if (z) {
                                try {
                                    new File(this.c.getFilesDir(), "BikeRaceAb.temp").renameTo(new File(this.c.getFilesDir(), "BikeRaceAb.dat"));
                                } catch (NullPointerException e22) {
                                    this.c.d().b(e22);
                                }
                            }
                        } else if (z) {
                            new File(this.c.getFilesDir(), "BikeRaceAb.temp").renameTo(new File(this.c.getFilesDir(), "BikeRaceAb.dat"));
                        }
                    } else if (objectOutputStream != null) {
                        objectOutputStream.close();
                        if (z) {
                            new File(this.c.getFilesDir(), "BikeRaceAb.temp").renameTo(new File(this.c.getFilesDir(), "BikeRaceAb.dat"));
                        }
                    } else if (z) {
                        new File(this.c.getFilesDir(), "BikeRaceAb.temp").renameTo(new File(this.c.getFilesDir(), "BikeRaceAb.dat"));
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e23) {
                e = e23;
                fileOutputStreamOpenFileOutput = null;
            } catch (IOException e24) {
                e = e24;
                fileOutputStreamOpenFileOutput = null;
            } catch (NullPointerException e25) {
                e = e25;
                fileOutputStreamOpenFileOutput = null;
            } catch (Exception e26) {
                e = e26;
                fileOutputStreamOpenFileOutput = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStreamOpenFileOutput = null;
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:70:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x009e: MOVE (r2 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:61:0x009e */
    private synchronized void a(c cVar) {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        ObjectInputStream objectInputStream3 = null;
        try {
            try {
                try {
                    try {
                        objectInputStream = new ObjectInputStream(this.c.openFileInput("BikeRaceAb.dat"));
                        try {
                            this.b = (b) objectInputStream.readObject();
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e2) {
                                }
                            }
                        } catch (FileNotFoundException e3) {
                            objectInputStream3 = objectInputStream;
                            this.b.c = true;
                            if (objectInputStream3 != null) {
                                try {
                                    objectInputStream3.close();
                                } catch (IOException e4) {
                                }
                            }
                        } catch (StreamCorruptedException e5) {
                            e = e5;
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            this.c.d().a(e);
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e6) {
                                }
                            }
                        } catch (IOException e7) {
                            e = e7;
                            objectInputStream3 = objectInputStream;
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            this.c.d().a(e);
                            if (objectInputStream3 != null) {
                                try {
                                    objectInputStream3.close();
                                } catch (IOException e8) {
                                }
                            }
                        } catch (ClassNotFoundException e9) {
                            e = e9;
                            objectInputStream3 = objectInputStream;
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            this.c.d().a(e);
                            if (objectInputStream3 != null) {
                                try {
                                    objectInputStream3.close();
                                } catch (IOException e10) {
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (objectInputStream3 != null) {
                            try {
                                objectInputStream3.close();
                            } catch (IOException e11) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectInputStream3 = objectInputStream2;
                    if (objectInputStream3 != null) {
                        objectInputStream3.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e12) {
            } catch (StreamCorruptedException e13) {
                e = e13;
                objectInputStream = null;
            } catch (IOException e14) {
                e = e14;
            } catch (ClassNotFoundException e15) {
                e = e15;
            }
            new d(this, cVar).execute(f1341a);
        } catch (Throwable th3) {
            throw th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x005d  */
    public void b(String str) throws JSONException, IOException {
        int i;
        boolean z;
        InputStream inputStreamC = null;
        try {
            try {
                inputStreamC = c(str);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamC, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        sb.append(String.valueOf(line) + "\n");
                    }
                    if (inputStreamC != null) {
                        inputStreamC.close();
                    }
                    throw th;
                }
                String string = sb.toString();
                if (inputStreamC != null) {
                    inputStreamC.close();
                }
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        JSONArray jSONArrayNames = jSONObject.names();
                        for (int i2 = 0; i2 < jSONArrayNames.length(); i2++) {
                            try {
                                String str2 = (String) jSONArrayNames.get(i2);
                                JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                                try {
                                    i = jSONObject2.getInt(NativeProtocol.PLATFORM_PROVIDER_VERSION_COLUMN);
                                } catch (JSONException e2) {
                                    i = -1;
                                }
                                try {
                                    z = jSONObject2.getBoolean("reset");
                                } catch (JSONException e3) {
                                    z = false;
                                }
                                JSONObject jSONObject3 = jSONObject2.getJSONObject("percentage");
                                JSONArray jSONArrayNames2 = jSONObject3.names();
                                HashMap map = new HashMap();
                                for (int i3 = 0; i3 < jSONArrayNames2.length(); i3++) {
                                    String str3 = (String) jSONArrayNames2.get(i3);
                                    map.put(str3, Integer.valueOf(jSONObject3.getInt(str3)));
                                }
                                a(str2, i, z, map);
                            } catch (JSONException e4) {
                            }
                        }
                    } catch (JSONException e5) {
                        if (ap.d()) {
                            e5.printStackTrace();
                        }
                        throw new IOException();
                    }
                } catch (NullPointerException e6) {
                    if (ap.d()) {
                        e6.printStackTrace();
                    }
                    throw new IOException();
                } catch (Exception e7) {
                    if (ap.d()) {
                        e7.printStackTrace();
                    }
                    throw new IOException();
                }
            } catch (Exception e8) {
                e8.printStackTrace();
                throw new IOException();
            }
        } catch (Throwable th) {
            if (inputStreamC != null) {
                inputStreamC.close();
            }
            throw th;
        }
    }

    private void a(String str, int i, boolean z, Map<String, Integer> map) {
        String next;
        boolean z2 = false;
        Integer numDecode = (Integer) this.b.f1342a.get(str);
        Integer num = (Integer) this.b.b.get(str);
        Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : -1);
        if (((numDecode == null && this.b.c) || z) && i > numValueOf.intValue()) {
            int iNextInt = new Random().nextInt(100);
            Iterator<String> it = map.keySet().iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                Integer num2 = map.get(next);
                if (num2 == null) {
                    num2 = 0;
                }
                int iIntValue = num2.intValue() + i2;
                if (iNextInt < iIntValue) {
                    break;
                } else {
                    i2 = iIntValue;
                }
            }
            if (next != null) {
                try {
                    numDecode = Integer.decode(next);
                } catch (NumberFormatException e2) {
                    numDecode = null;
                }
                if (numDecode == null) {
                    numDecode = 0;
                }
                this.d.a(str, numDecode.intValue(), i);
                z2 = true;
            } else {
                numDecode = 0;
                z2 = true;
            }
        } else if (numDecode == null) {
            numDecode = 0;
            z2 = true;
        }
        if (z2 && numDecode != null) {
            this.b.f1342a.put(str, numDecode);
            this.b.b.put(str, Integer.valueOf(i));
            b();
        }
    }

    private InputStream c(String str) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }
}
