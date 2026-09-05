package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import com.chartboost.sdk.Chartboost;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static o f476a = null;
    private d b = new d(Chartboost.sharedChartboost().getContext());
    private com.chartboost.sdk.Libraries.a c = new com.chartboost.sdk.Libraries.a();

    public interface b {
        void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle);
    }

    public static synchronized o a() {
        if (f476a == null) {
            f476a = new o();
        }
        return f476a;
    }

    private o() {
    }

    public void b() {
        this.b.a();
        this.c.a();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0026  */
    /* JADX WARN: Code duplicated, block: B:16:0x002c A[Catch: Exception -> 0x0046, TRY_LEAVE, TryCatch #0 {Exception -> 0x0046, blocks: (B:8:0x0016, B:10:0x001e, B:14:0x0027, B:16:0x002c), top: B:28:0x0016 }] */
    /* JADX WARN: Code duplicated, block: B:24:0x0044  */
    public void a(String str, String str2, b bVar, ImageView imageView, Bundle bundle) {
        boolean z;
        com.chartboost.sdk.Libraries.a.C0000a c0000aA = null;
        boolean z2 = bundle != null ? bundle.getBoolean("paramNoMemoryCache") : false;
        float f = bundle != null ? bundle.getFloat("paramAssetScale", 1.0f) : 1.0f;
        if (!z2) {
            try {
                c0000aA = this.c.a(str2);
                if (c0000aA == null && (c0000aA = a(str2, f)) != null) {
                    if (z2) {
                        z = false;
                    } else {
                        z = true;
                    }
                    c0000aA.a(z);
                    if (!z2) {
                        this.c.a(str2, c0000aA);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (c0000aA == null) {
            if (z2) {
                z = false;
            } else {
                z = true;
            }
            c0000aA.a(z);
            if (!z2) {
                this.c.a(str2, c0000aA);
            }
        }
        if (c0000aA != null) {
            if (imageView != null) {
                imageView.setImageBitmap(c0000aA.b());
            }
            if (bVar != null) {
                bVar.a(c0000aA, bundle);
                return;
            }
            return;
        }
        l.a().execute(new a(imageView, bVar, str2, bundle, str));
    }

    class a implements Runnable {
        private String b;
        private final WeakReference<ImageView> c;
        private b d;
        private String e;
        private Bundle f;

        public a(ImageView imageView, b bVar, String str, Bundle bundle, String str2) {
            this.c = new WeakReference<>(imageView);
            c cVar = new c(this);
            if (imageView != null) {
                imageView.setImageDrawable(cVar);
            }
            this.e = str;
            this.d = bVar;
            this.f = bundle;
            this.b = str2;
        }

        /* JADX WARN: Code duplicated, block: B:78:0x014f  */
        @Override // java.lang.Runnable
        public void run() {
            com.chartboost.sdk.Libraries.a.C0000a c0000aA;
            com.chartboost.sdk.Libraries.a.C0000a c0000aA2;
            Throwable th;
            HttpResponse httpResponseExecute;
            com.chartboost.sdk.Libraries.a.C0000a c0000a;
            IOException e;
            HttpResponse httpResponse = null;
            InputStream content = null;
            boolean z = this.f != null ? this.f.getBoolean("paramNoMemoryCache") : false;
            float f = this.f != null ? this.f.getFloat("paramAssetScale", 1.0f) : 1.0f;
            try {
                c0000aA = o.this.a(this.e, f);
                if (c0000aA != null) {
                    try {
                        c0000aA.a(!z);
                        c0000aA2 = c0000aA;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        c0000aA2 = c0000aA;
                    }
                } else {
                    c0000aA2 = c0000aA;
                }
            } catch (Exception e3) {
                e = e3;
                c0000aA = null;
            }
            if (c0000aA2 != null) {
                a(c0000aA2);
                return;
            }
            HttpClient httpClientB = l.b();
            HttpGet httpGet = new HttpGet(this.b);
            try {
                httpResponseExecute = httpClientB.execute(httpGet);
                try {
                    int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
                    if (statusCode != 200) {
                        Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + this.b);
                        com.chartboost.sdk.Libraries.d.a(httpResponseExecute);
                        a((com.chartboost.sdk.Libraries.a.C0000a) null);
                        return;
                    }
                    HttpEntity entity = httpResponseExecute.getEntity();
                    if (entity != null) {
                        try {
                            content = entity.getContent();
                            o.this.a(this.e, new e(content));
                            try {
                                c0000aA2 = o.this.a(this.e, f);
                                if (c0000aA2 == null) {
                                    c0000a = c0000aA2;
                                } else {
                                    c0000aA2.a(z ? false : true);
                                    if (z) {
                                        c0000a = c0000aA2;
                                    } else {
                                        o.this.c.a(this.e, c0000aA2);
                                        c0000a = c0000aA2;
                                    }
                                }
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                c0000a = c0000aA2;
                            }
                            try {
                                if (content != null) {
                                    content.close();
                                } else {
                                    com.chartboost.sdk.Libraries.d.a(entity);
                                }
                            } catch (IOException e5) {
                                e = e5;
                                httpResponse = httpResponseExecute;
                                httpGet.abort();
                                com.chartboost.sdk.Libraries.d.a(httpResponse);
                                Log.w("CBWebImageCache", "I/O error while retrieving bitmap from " + this.b, e);
                            } catch (IllegalStateException e6) {
                                httpGet.abort();
                                com.chartboost.sdk.Libraries.d.a(httpResponseExecute);
                                Log.w("CBWebImageCache", "Incorrect URL: " + this.b);
                            } catch (Throwable th2) {
                                th = th2;
                                httpGet.abort();
                                com.chartboost.sdk.Libraries.d.a(httpResponseExecute);
                                Log.w("CBWebImageCache", "Error while retrieving bitmap from " + this.b, th);
                            }
                        } catch (Throwable th3) {
                            if (content != null) {
                                content.close();
                            } else {
                                com.chartboost.sdk.Libraries.d.a(entity);
                            }
                            throw th3;
                        }
                    } else {
                        c0000a = c0000aA2;
                    }
                    a(c0000a);
                } catch (IOException e7) {
                    e = e7;
                    httpResponse = httpResponseExecute;
                    c0000a = c0000aA2;
                } catch (IllegalStateException e8) {
                    c0000a = c0000aA2;
                } catch (Throwable th4) {
                    th = th4;
                    c0000a = c0000aA2;
                }
            } catch (IOException e9) {
                e = e9;
                c0000a = c0000aA2;
            } catch (IllegalStateException e10) {
                httpResponseExecute = null;
                c0000a = c0000aA2;
            } catch (Throwable th5) {
                th = th5;
                httpResponseExecute = null;
                c0000a = c0000aA2;
            }
        }

        public void a(final com.chartboost.sdk.Libraries.a.C0000a c0000a) {
            Chartboost.sharedChartboost().getHandler().post(new Runnable() { // from class: com.chartboost.sdk.impl.o.a.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean z;
                    if (a.this.f != null) {
                        z = a.this.f.getBoolean("paramNoMemoryCache");
                    } else {
                        z = false;
                    }
                    try {
                        if (c0000a != null && !z) {
                            o.this.c.a(a.this.e, c0000a);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (a.this.c != null) {
                        ImageView imageView = (ImageView) a.this.c.get();
                        if (a.this == o.b(imageView)) {
                            imageView.setImageBitmap(c0000a.b());
                        }
                    }
                    if (a.this.d != null) {
                        a.this.d.a(c0000a, a.this.f);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof c) {
                return ((c) drawable).a();
            }
        }
        return null;
    }

    class c extends BitmapDrawable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<a> f479a;

        public c(a aVar) {
            this.f479a = new WeakReference<>(aVar);
        }

        public a a() {
            return this.f479a.get();
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0078  */
    /* JADX WARN: Code duplicated, block: B:34:? A[RETURN, SYNTHETIC] */
    protected com.chartboost.sdk.Libraries.a.C0000a a(String str, float f) throws IOException {
        Bitmap bitmapDecodeByteArray;
        File fileA = this.b.a(String.valueOf(str) + ".png");
        if (fileA == null || !fileA.exists()) {
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileA));
        long length = fileA.length();
        if (length > 2147483647L) {
            try {
                bufferedInputStream.close();
            } catch (IOException e2) {
            }
            throw new IOException("Cannot read files larger than 2147483647 bytes");
        }
        int i = (int) length;
        byte[] bArr = new byte[i];
        bufferedInputStream.read(bArr, 0, i);
        bufferedInputStream.close();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = false;
        options2.inDither = false;
        options2.inPurgeable = true;
        options2.inInputShareable = true;
        options2.inTempStorage = new byte[32768];
        options2.inSampleSize = 1;
        while (options2.inSampleSize < 32) {
            try {
                bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
                if (bitmapDecodeByteArray != null) {
                    return new com.chartboost.sdk.Libraries.a.C0000a(bitmapDecodeByteArray, options2.inSampleSize, f);
                }
                return null;
            } catch (Exception e3) {
                return null;
            } catch (OutOfMemoryError e4) {
                options2.inSampleSize *= 2;
            }
        }
        bitmapDecodeByteArray = null;
        if (bitmapDecodeByteArray != null) {
            return new com.chartboost.sdk.Libraries.a.C0000a(bitmapDecodeByteArray, options2.inSampleSize, f);
        }
        return null;
    }

    protected boolean a(String str, e eVar) throws Throwable {
        FileOutputStream fileOutputStream;
        File fileA = this.b.a(String.valueOf(str) + ".png");
        if (fileA == null) {
            return false;
        }
        try {
            fileOutputStream = new FileOutputStream(fileA);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = eVar.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                    }
                }
                return true;
            } catch (Exception e3) {
                if (fileOutputStream == null) {
                    return false;
                }
                try {
                    fileOutputStream.close();
                    return false;
                } catch (Exception e4) {
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e5) {
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    class e extends FilterInputStream {
        public e(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long jSkip = this.in.skip(j - j2);
                if (jSkip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    jSkip = 1;
                }
                j2 = jSkip + j2;
            }
            return j2;
        }
    }

    class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private File f480a = null;
        private File b = null;
        private File c = null;

        public d(Context context) {
            a(context);
        }

        private void a(Context context) {
            if (context != null) {
                try {
                    if ((context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) && Environment.getExternalStorageState().equals("mounted")) {
                        this.b = context.getExternalFilesDir("cache");
                    }
                    if (this.b != null) {
                        this.b = new File(this.b, "__chartboost");
                        this.b = new File(this.b, "images");
                        if (this.b != null && !this.b.exists()) {
                            this.b.mkdirs();
                        }
                    }
                } catch (Exception e) {
                    this.b = null;
                }
                this.c = context.getCacheDir();
                if (this.c != null) {
                    this.c = new File(this.c, "__chartboost");
                    this.c = new File(this.c, "images");
                    if (!this.c.exists()) {
                        this.c.mkdirs();
                    }
                }
                this.f480a = this.b != null ? this.b : this.c;
            }
        }

        public File a(String str) {
            if (this.f480a == null) {
                a(Chartboost.sharedChartboost().getContext());
            }
            if (this.f480a == null) {
                return null;
            }
            return new File(this.f480a, str);
        }

        public void a() {
            File[] fileArrListFiles;
            File[] fileArrListFiles2;
            if (this.f480a != null) {
                try {
                    if (this.b != null && (fileArrListFiles2 = this.b.listFiles()) != null) {
                        for (File file : fileArrListFiles2) {
                            file.delete();
                        }
                    }
                    if (this.c != null && (fileArrListFiles = this.c.listFiles()) != null) {
                        for (File file2 : fileArrListFiles) {
                            file2.delete();
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
