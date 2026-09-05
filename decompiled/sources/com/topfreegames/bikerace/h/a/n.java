package com.topfreegames.bikerace.h.a;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import com.topfreegames.bikerace.ap;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: compiled from: UserLevelThumbnailProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private android.support.v4.c.e<String, Bitmap> f1249a;
    private Context c;
    private int d;
    private int e;
    private HashMap<String, p> b = new HashMap<>();
    private o f = new o() { // from class: com.topfreegames.bikerace.h.a.n.1
        @Override // com.topfreegames.bikerace.h.a.o
        public void a(a aVar, p pVar, k kVar) {
            synchronized (n.this.b) {
                n.this.b.put(aVar.a(), pVar);
            }
            d.a(aVar, kVar);
        }
    };

    public n(Context context) {
        this.f1249a = null;
        this.c = null;
        this.d = -1;
        this.e = -1;
        this.f1249a = new android.support.v4.c.e<String, Bitmap>((((ActivityManager) context.getSystemService("activity")).getMemoryClass() * 1048576) / 8) { // from class: com.topfreegames.bikerace.h.a.n.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.c.e
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int b(String str, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        this.c = context.getApplicationContext();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        String string = this.c.getString(2131362368);
        String string2 = this.c.getString(2131362369);
        this.d = Integer.parseInt(string.substring(0, string.length() - 5));
        this.d = (int) (((double) (this.d * displayMetrics.density)) + 0.5d);
        this.e = Integer.parseInt(string2.substring(0, string2.length() - 5));
        this.e = (int) (((double) (displayMetrics.density * this.e)) + 0.5d);
    }

    public void a(a aVar, p pVar) {
        if (aVar != null) {
            Bitmap bitmapB = b(aVar.a());
            if (bitmapB == null) {
                a(aVar, pVar, this.f);
            } else if (pVar != null) {
                pVar.a(bitmapB);
            }
        }
    }

    public void a(String str, p pVar) {
        if (str != null) {
            a(new a(str, d.a(str, false)), pVar);
        }
    }

    public void a(String str) {
        if (str != null) {
            synchronized (this.b) {
                this.b.remove(str);
            }
        }
    }

    private Bitmap b(String str) {
        return this.f1249a.a(str);
    }

    private void a(final a aVar, final p pVar, final o oVar) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.h.a.n.3
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                Bitmap bitmapC = n.this.c(aVar.a());
                if (bitmapC == null) {
                    oVar.a(aVar, pVar, this);
                } else {
                    pVar.a(bitmapC);
                }
            }
        }).start();
    }

    public void a() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    @Override // com.topfreegames.bikerace.h.a.k
    public void a(String str, byte[] bArr) {
        Bitmap bitmap;
        p pVarRemove;
        if (bArr == null || str == null) {
            bitmap = null;
        } else {
            Bitmap bitmapA = a(bArr);
            b(str, bArr);
            if (bitmapA != null) {
                this.f1249a.a(str, bitmapA);
            }
            bitmap = bitmapA;
        }
        synchronized (this.b) {
            pVarRemove = this.b.remove(str);
        }
        if (pVarRemove != null) {
            pVarRemove.a(bitmap);
        }
    }

    private void b(final String str, final byte[] bArr) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.h.a.n.4
            @Override // java.lang.Runnable
            public void run() {
                if (n.this.c == null || str == null || bArr == null) {
                    return;
                }
                FileOutputStream fileOutputStreamOpenFileOutput = null;
                try {
                    fileOutputStreamOpenFileOutput = n.this.c.openFileOutput(str, 0);
                    fileOutputStreamOpenFileOutput.write(bArr);
                } catch (FileNotFoundException e) {
                    if (ap.d()) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    if (ap.d()) {
                        e2.printStackTrace();
                    }
                } finally {
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException e3) {
                        }
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:101:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:113:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:135:? A[SYNTHETIC] */
    public Bitmap c(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStreamOpenFileInput;
        DataOutputStream dataOutputStream;
        Throwable th;
        DataOutputStream dataOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream2;
        FileInputStream fileInputStream;
        byte[] byteArray = null;
        if (this.c == null || str == null) {
            return null;
        }
        try {
            fileInputStreamOpenFileInput = this.c.openFileInput(str);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    try {
                        byte[] bArr = new byte[4096];
                        for (int i = fileInputStreamOpenFileInput.read(bArr); i != -1; i = fileInputStreamOpenFileInput.read(bArr)) {
                            dataOutputStream.write(bArr, 0, i);
                        }
                        if (fileInputStreamOpenFileInput != null) {
                            try {
                                fileInputStreamOpenFileInput.close();
                            } catch (IOException e) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArray = byteArrayOutputStream.toByteArray();
                                byteArrayOutputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        if (fileInputStreamOpenFileInput != null) {
                            try {
                                fileInputStreamOpenFileInput.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArray = byteArrayOutputStream.toByteArray();
                                byteArrayOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                    } catch (IOException e8) {
                        fileInputStream = fileInputStreamOpenFileInput;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        dataOutputStream2 = dataOutputStream;
                        e = e8;
                        try {
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e9) {
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArray = byteArrayOutputStream2.toByteArray();
                                    byteArrayOutputStream2.close();
                                } catch (IOException e10) {
                                }
                            }
                            if (dataOutputStream2 != null) {
                                try {
                                    dataOutputStream2.close();
                                } catch (IOException e11) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            dataOutputStream = dataOutputStream2;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            fileInputStreamOpenFileInput = fileInputStream;
                            if (fileInputStreamOpenFileInput != null) {
                                try {
                                    fileInputStreamOpenFileInput.close();
                                } catch (IOException e12) {
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.toByteArray();
                                    byteArrayOutputStream.close();
                                } catch (IOException e13) {
                                }
                            }
                            if (dataOutputStream != null) {
                                throw th;
                            }
                            try {
                                dataOutputStream.close();
                                throw th;
                            } catch (IOException e14) {
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileInputStreamOpenFileInput != null) {
                            fileInputStreamOpenFileInput.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                        }
                        if (dataOutputStream != null) {
                            throw th;
                        }
                        dataOutputStream.close();
                        throw th;
                    }
                } catch (FileNotFoundException e15) {
                    dataOutputStream = null;
                } catch (IOException e16) {
                    e = e16;
                    fileInputStream = fileInputStreamOpenFileInput;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    dataOutputStream2 = null;
                } catch (Throwable th4) {
                    dataOutputStream = null;
                    th = th4;
                }
            } catch (FileNotFoundException e17) {
                dataOutputStream = null;
                byteArrayOutputStream = null;
            } catch (IOException e18) {
                e = e18;
                dataOutputStream2 = null;
                fileInputStream = fileInputStreamOpenFileInput;
                byteArrayOutputStream2 = null;
            } catch (Throwable th5) {
                byteArrayOutputStream = null;
                th = th5;
                dataOutputStream = null;
            }
        } catch (FileNotFoundException e19) {
            dataOutputStream = null;
            byteArrayOutputStream = null;
            fileInputStreamOpenFileInput = null;
        } catch (IOException e20) {
            e = e20;
            dataOutputStream2 = null;
            byteArrayOutputStream2 = null;
            fileInputStream = null;
        } catch (Throwable th6) {
            byteArrayOutputStream = null;
            fileInputStreamOpenFileInput = null;
            dataOutputStream = null;
            th = th6;
        }
        return a(byteArray);
    }

    private Bitmap a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return com.topfreegames.engine.b.a.a(bArr, this.d, this.e);
    }
}
