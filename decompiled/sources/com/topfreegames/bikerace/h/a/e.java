package com.topfreegames.bikerace.h.a;

import android.os.AsyncTask;
import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: UserLevelDownloader.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e extends AsyncTask<Void, Void, i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private j f1245a;
    private String b;

    public e(j jVar, String str) {
        this.f1245a = null;
        this.b = null;
        this.f1245a = jVar;
        this.b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public i doInBackground(Void... voidArr) {
        try {
            return d.b(this.b);
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
    public void onPostExecute(i iVar) {
        synchronized (d.f1244a) {
            d.f1244a.remove(this);
            d.e();
        }
        if (this.f1245a != null) {
            if (iVar != null) {
                this.f1245a.a(this.b, iVar.f1248a, iVar.b);
            } else {
                this.f1245a.a();
            }
        }
    }
}
