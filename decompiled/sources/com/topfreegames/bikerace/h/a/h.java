package com.topfreegames.bikerace.h.a;

import android.os.AsyncTask;
import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: UserLevelDownloader.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h extends AsyncTask<Void, Void, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private k f1247a;
    private String b;
    private String c;

    public h(k kVar, String str, String str2) {
        this.f1247a = null;
        this.b = null;
        this.c = null;
        this.f1247a = kVar;
        this.b = str;
        this.c = str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public byte[] doInBackground(Void... voidArr) {
        try {
            return d.b(this.b, this.c);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(byte[] bArr) {
        synchronized (d.f1244a) {
            d.f1244a.remove(this);
            d.e();
        }
        if (this.f1247a != null) {
            this.f1247a.a(this.b, bArr);
        }
    }
}
