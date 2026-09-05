package com.topfreegames.e.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookPictureRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f1503a;
    private String b;
    private Timer c;
    private long d;

    public a(b bVar, String str, long j) {
        this.f1503a = bVar;
        this.d = j;
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    b b() {
        return this.f1503a;
    }

    void a(b bVar) {
        this.f1503a = bVar;
    }

    public void c() {
        final String str = this.b;
        this.c = new Timer();
        this.c.schedule(new TimerTask() { // from class: com.topfreegames.e.b.a.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                synchronized (this) {
                    a.this.c = null;
                    a.this.a(null, str, true);
                }
            }
        }, this.d);
        a(this.b);
    }

    private void a(final String str) {
        new Thread(new Runnable() { // from class: com.topfreegames.e.b.a.2
            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmapDecodeStream;
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://graph.facebook.com/" + str + "/picture").openConnection();
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    URL url = httpURLConnection.getURL();
                    httpURLConnection.disconnect();
                    inputStream.close();
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.connect();
                    InputStream inputStream2 = httpURLConnection2.getInputStream();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStream2, null, options);
                } catch (Exception e) {
                    Log.e("TopFacebookPictureRequestListener", "An error ocurred while retrieving the picture" + e);
                    bitmapDecodeStream = null;
                }
                a.this.a(bitmapDecodeStream, str, false);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bitmap bitmap, String str, boolean z) {
        synchronized (this) {
            if (this.f1503a != null) {
                this.f1503a.a(bitmap, str, this, false);
            }
            if (this.c != null) {
                this.c.cancel();
                this.c = null;
            }
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f1503a = null;
    }
}
