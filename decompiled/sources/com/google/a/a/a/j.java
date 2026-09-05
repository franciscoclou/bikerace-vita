package com.google.a.a.a;

import android.content.Context;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.AppEventsConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: compiled from: ClientIdDefaultProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class j implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static j f591a;
    private static final Object b = new Object();
    private final Context c;
    private String d;
    private boolean e = false;
    private final Object f = new Object();

    public static void a(Context context) {
        synchronized (b) {
            if (f591a == null) {
                f591a = new j(context);
            }
        }
    }

    public static j a() {
        j jVar;
        synchronized (b) {
            jVar = f591a;
        }
        return jVar;
    }

    protected j(Context context) {
        this.c = context;
        e();
    }

    @Override // com.google.a.a.a.m
    public String a(String str) {
        if ("&cid".equals(str)) {
            return d();
        }
        return null;
    }

    private String d() {
        if (!this.e) {
            synchronized (this.f) {
                if (!this.e) {
                    ah.c("Waiting for clientId to load");
                    do {
                        try {
                            this.f.wait();
                        } catch (InterruptedException e) {
                            ah.a("Exception while waiting for clientId: " + e);
                        }
                    } while (!this.e);
                }
            }
        }
        ah.c("Loaded clientId");
        return this.d;
    }

    private boolean b(String str) {
        try {
            ah.c("Storing clientId.");
            FileOutputStream fileOutputStreamOpenFileOutput = this.c.openFileOutput("gaClientId", 0);
            fileOutputStreamOpenFileOutput.write(str.getBytes());
            fileOutputStreamOpenFileOutput.close();
            return true;
        } catch (FileNotFoundException e) {
            ah.a("Error creating clientId file.");
            return false;
        } catch (IOException e2) {
            ah.a("Error writing to clientId file.");
            return false;
        }
    }

    protected String b() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        if (!b(lowerCase)) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        return lowerCase;
    }

    private void e() {
        new Thread("client_id_fetcher") { // from class: com.google.a.a.a.j.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (j.this.f) {
                    j.this.d = j.this.c();
                    j.this.e = true;
                    j.this.f.notifyAll();
                }
            }
        }.start();
    }

    /* JADX WARN: Code duplicated, block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x002b  */
    String c() {
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = this.c.openFileInput("gaClientId");
            byte[] bArr = new byte[XMLChar.MASK_NCNAME];
            int i = fileInputStreamOpenFileInput.read(bArr, 0, XMLChar.MASK_NCNAME);
            if (fileInputStreamOpenFileInput.available() > 0) {
                ah.a("clientId file seems corrupted, deleting it.");
                fileInputStreamOpenFileInput.close();
                this.c.deleteFile("gaClientId");
            } else if (i <= 0) {
                ah.a("clientId file seems empty, deleting it.");
                fileInputStreamOpenFileInput.close();
                this.c.deleteFile("gaClientId");
            } else {
                String str2 = new String(bArr, 0, i);
                try {
                    fileInputStreamOpenFileInput.close();
                    str = str2;
                } catch (FileNotFoundException e) {
                    str = str2;
                } catch (IOException e2) {
                    str = str2;
                    ah.a("Error reading clientId file, deleting it.");
                    this.c.deleteFile("gaClientId");
                    if (str == null) {
                        return b();
                    }
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
        } catch (IOException e4) {
        }
        if (str == null) {
            return b();
        }
        return str;
    }
}
