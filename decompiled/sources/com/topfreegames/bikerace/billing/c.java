package com.topfreegames.bikerace.billing;

import android.content.Context;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

/* JADX INFO: compiled from: PurchaseDatabase.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {
    private static /* synthetic */ int[] h;
    Context c;
    private final String d = "wug10.dat";
    private final String e = "PurchaseDatabase";
    private final String f = ";";
    private final String g = "=";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Map<String, Integer> f1144a = new HashMap();
    List<String> b = new LinkedList();

    static /* synthetic */ int[] c() {
        int[] iArr = h;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.billing.google.b.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.billing.google.b.CANCELED.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.billing.google.b.PURCHASED.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.billing.google.b.REFUNDED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            h = iArr;
        }
        return iArr;
    }

    public c(Context context, List<String> list) throws Throwable {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.f1144a.put(it.next(), 0);
        }
        this.c = context;
        b();
    }

    public Map<String, Integer> a() {
        return this.f1144a;
    }

    public int a(String str) {
        try {
            return this.f1144a.get(str).intValue();
        } catch (Exception e) {
            Log.e("PurchaseDatabase", e.toString());
            return 0;
        }
    }

    public boolean a(String str, int i) {
        try {
            this.f1144a.put(str, Integer.valueOf(i));
            d();
            return true;
        } catch (Exception e) {
            Log.e("PurchaseDatabase", e.toString());
            return false;
        }
    }

    public synchronized int a(String str, String str2, com.topfreegames.bikerace.billing.google.b bVar, long j, String str3) {
        int iIntValue = 0;
        synchronized (this) {
            switch (c()[bVar.ordinal()]) {
                case 1:
                    if (!this.b.contains(str)) {
                        if (this.f1144a.containsKey(str2)) {
                            iIntValue = this.f1144a.get(str2).intValue() + 1;
                            this.f1144a.put(str2, Integer.valueOf(iIntValue));
                            this.b.add(str);
                            d();
                        } else {
                            Log.e("PurchaseDatabase", "The specified product does not exist in the database");
                        }
                    } else {
                        iIntValue = this.f1144a.get(str2).intValue();
                    }
                    break;
                case 2:
                case 3:
                    if (this.f1144a.containsKey(str2)) {
                        this.f1144a.put(str2, 0);
                        d();
                    }
                    break;
            }
        }
        return iIntValue;
    }

    private void d() {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.c.openFileOutput("wug10.dat", 0));
            Cipher cipherA = com.topfreegames.bikerace.d.a.a(this.c);
            Cipher cipherB = com.topfreegames.bikerace.d.a.b(this.c);
            CipherOutputStream cipherOutputStream = null;
            if (cipherA != null && cipherB != null) {
                cipherOutputStream = new CipherOutputStream(bufferedOutputStream, cipherA);
            }
            OutputStream outputStream = cipherOutputStream == null ? bufferedOutputStream : cipherOutputStream;
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Integer> entry : this.f1144a.entrySet()) {
                sb.append(String.valueOf(entry.getKey()) + "=" + entry.getValue() + ";");
            }
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("PurchaseDatabase", "Could not persist database. Exception: " + e.toString());
        }
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public boolean b() throws Throwable {
        InputStream inputStreamOpenFileInput;
        int i;
        InputStream cipherInputStream = null;
        try {
            inputStreamOpenFileInput = this.c.openFileInput("wug10.dat");
            Cipher cipherA = com.topfreegames.bikerace.d.a.a(this.c);
            Cipher cipherB = com.topfreegames.bikerace.d.a.b(this.c);
            if (cipherA != null && cipherB != null) {
                cipherInputStream = new CipherInputStream(inputStreamOpenFileInput, cipherB);
            }
            if (cipherInputStream != null) {
                inputStreamOpenFileInput = cipherInputStream;
            }
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    boolean z = false;
                    while (!z) {
                        int i2 = inputStreamOpenFileInput.read();
                        if (i2 == -1) {
                            z = true;
                        } else {
                            sb.append((char) i2);
                        }
                    }
                    StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), ";", false);
                    while (stringTokenizer.hasMoreTokens()) {
                        String strNextToken = stringTokenizer.nextToken();
                        String strSubstring = strNextToken.substring(0, strNextToken.indexOf("="));
                        try {
                            i = Integer.parseInt(strNextToken.substring(strNextToken.indexOf("=") + 1, strNextToken.length()));
                        } catch (Exception e) {
                            Log.e("PurchaseDatabase", "Could not retrieve quantity. Exception: " + e.toString());
                            i = 0;
                        }
                        this.f1144a.put(strSubstring, Integer.valueOf(i));
                    }
                    if (inputStreamOpenFileInput == null) {
                        return true;
                    }
                    try {
                        inputStreamOpenFileInput.close();
                        return true;
                    } catch (IOException e2) {
                        return true;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (inputStreamOpenFileInput != null) {
                        try {
                            inputStreamOpenFileInput.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                cipherInputStream = inputStreamOpenFileInput;
                try {
                    Log.e("PurchaseDatabase", "Could not retrieve database information. Exception: " + e.toString());
                    if (cipherInputStream == null) {
                        return false;
                    }
                    try {
                        cipherInputStream.close();
                        return false;
                    } catch (IOException e5) {
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamOpenFileInput = cipherInputStream;
                    if (inputStreamOpenFileInput != null) {
                        inputStreamOpenFileInput.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpenFileInput = null;
        }
    }
}
